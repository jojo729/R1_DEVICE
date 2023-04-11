package com.phicomm.speaker.device.custom.handler;

import android.content.Context;
import android.os.SysPrivateManager;
import android.util.Log;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.Receiver.MessageReceiver;
import com.phicomm.speaker.device.custom.ipc.PhicommLightController;
import com.phicomm.speaker.device.custom.keyevent.PhicommKeyEventController;
import com.phicomm.speaker.device.custom.keyevent.PhicommKeyEventProcessor;
import com.phicomm.speaker.device.custom.match.MatchProcessor;
import com.phicomm.speaker.device.custom.speech.SpeechManager;
import com.phicomm.speaker.device.custom.status.PhicommDeviceStatusProcessor;
import com.phicomm.speaker.device.custom.udid.UDIDProcessor;
import com.phicomm.speaker.device.utils.LogUtils;
import com.unisound.ant.device.bean.DstServiceName;
import com.unisound.ant.device.bean.SelfDefinationRequestInfo;
import com.unisound.ant.device.bean.SelfDefinationResponseInfo;
import com.unisound.ant.device.sessionlayer.SessionRegister;
import com.unisound.vui.common.media.IMediaPlayerStateListener;
import com.unisound.vui.common.media.UniMediaPlayer;
import com.unisound.vui.common.network.NetUtil;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.ANTEventDispatcher;
import com.unisound.vui.util.UserPerferenceUtil;
import java.util.HashMap;
import java.util.Map;

public class PhicommInitializeHandler extends ANTEventDispatcher implements PhicommDeviceStatusProcessor.OnDeviceStatusChangedListener {
    private static final String FX_KEY_OTA_MODE = "fxotamode";
    private static final String FX_OTA_MODE_VALUE_SILENT = "silent";
    private static final String FX_SYSTEM_PRIVATE = "FXSystemPrivate";
    private static final String TAG = PhicommInitializeHandler.class.getSimpleName();
    private boolean isFristBoot = false;
    private ANTEngine mANTEngine;
    private Context mContext;
    private PhicommDeviceStatusProcessor mDeviceStatusProcessor;
    private PhicommKeyEventProcessor mKeyEventProcessor;
    private PhicommLightController mLightController;
    private SpeechManager mSpeechManager;
    private SysPrivateManager mSysPrivateManager = null;
    private MatchProcessor matchProcessor;
    private UDIDProcessor udidProcessor;

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        this.mContext = ctx.androidContext();
        this.mANTEngine = ctx.engine();
        this.mSpeechManager = new SpeechManager(this.mANTEngine);
        this.mLightController = new PhicommLightController(this.mContext);
        playInitDoneTips();
        return super.onASREventEngineInitDone(ctx);
    }

    private void playInitDoneTips() {
        try {
            this.mSysPrivateManager = (SysPrivateManager) this.mContext.getSystemService(FX_SYSTEM_PRIVATE);
            if (this.mSysPrivateManager != null && FX_OTA_MODE_VALUE_SILENT.equals(this.mSysPrivateManager.getBootProp(FX_KEY_OTA_MODE))) {
                Log.d(TAG, "playInitDoneTips, silent update");
                this.mSysPrivateManager.setBootProp(FX_KEY_OTA_MODE, "");
                initPhicommBusiness();
                return;
            }
        } catch (Throwable e) {
            LogUtils.e(TAG, "查询静默升级状态出错: ", e);
        }
        if (!NetUtil.isNetworkConnected(this.mContext)) {
            UniMediaPlayer.getInstance().playBeepSound("", R.raw.bootloader_completed, new IMediaPlayerStateListener() {
                /* class com.phicomm.speaker.device.custom.handler.PhicommInitializeHandler.AnonymousClass1 */

                @Override // com.unisound.vui.common.media.IMediaPlayerStateListener
                public void onPlayerState(int state, String tag) {
                    if (state == 4) {
                        PhicommInitializeHandler.this.isFristBoot = true;
                        if (UserPerferenceUtil.getDeviceBindState(PhicommInitializeHandler.this.mContext)) {
                            PhicommInitializeHandler.this.mANTEngine.playTTS(PhicommInitializeHandler.this.mContext.getString(R.string.tts_net_is_weak));
                        } else {
                            PhicommInitializeHandler.this.mANTEngine.playTTS(PhicommInitializeHandler.this.mContext.getString(R.string.tts_bootloader_welcome));
                        }
                        UniMediaPlayer.getInstance().removeIMediaPlayerStateListener(this);
                    } else if (state == 3) {
                        UniMediaPlayer.getInstance().removeIMediaPlayerStateListener(this);
                    }
                }
            }, false);
            return;
        }
        UniMediaPlayer.getInstance().playBeepSound(R.raw.bootloader_completed);
        initPhicommBusiness();
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventPlayingStart(ANTHandlerContext ctx) {
        Log.d(TAG, "onTTSEventPlayingStart, isFristBoot : " + this.isFristBoot);
        if (this.isFristBoot) {
            this.mSpeechManager.stopWakeup();
        }
        return super.onTTSEventPlayingStart(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        Log.d(TAG, "onTTSEventPlayingEnd, isFristBoot : " + this.isFristBoot);
        if (this.isFristBoot) {
            this.isFristBoot = false;
            this.mSpeechManager.startWakeup();
            initPhicommBusiness();
        }
        return super.onTTSEventPlayingEnd(ctx);
    }

    @Override // com.phicomm.speaker.device.custom.status.PhicommDeviceStatusProcessor.OnDeviceStatusChangedListener
    public void onDeviceStatusChanged(int prevStatus, int status) {
        if (prevStatus == 5 && status != 5) {
            this.mLightController.turnOffDormantLight();
            UserPerferenceUtil.setDormantLightState(this.mContext, false);
            onDormantStatusChanged(false);
        }
        if (prevStatus != 5 && status == 5) {
            UserPerferenceUtil.setDormantLightState(this.mContext, true);
            onDormantStatusChanged(true);
        }
        if (MessageReceiver.isSystemBootloader(this.mContext)) {
            LogUtils.d(TAG, "system boot finish, ignore");
            MessageReceiver.setSystemBootloader(this.mContext, false);
            return;
        }
        if (status == 0 && prevStatus == 2) {
            this.mSpeechManager.playTTS(R.string.tts_stop_match_net);
        } else if (status == 0 && prevStatus == 3) {
            this.mSpeechManager.playTTS(R.string.tts_close_bluetooth_for_phicomm);
        }
        if (prevStatus != 3 && status == 3) {
            LogUtils.d(TAG, "-----已切换成蓝牙模式, ASR 换成 local 模式-----");
            switchASRModeType(2);
        } else if (prevStatus == 3 && status != 3) {
            LogUtils.d(TAG, "-----已切换成非蓝牙模式, ASR 换成 mix 模式-----");
            switchASRModeType(0);
        }
        if (prevStatus == 5 && status != 2 && status != 5) {
            UserPerferenceUtil.setStartWakeupAfterSetWakeupWord(this.mContext, true);
        } else if (prevStatus == 2 && status != 5 && status != 2) {
            UserPerferenceUtil.setStartWakeupAfterSetWakeupWord(this.mContext, true);
        }
    }

    private void switchASRModeType(int type) {
        this.mANTEngine.unsafe().setASROption(1001, Integer.valueOf(type));
    }

    private void initPhicommBusiness() {
        initDeviceStatusListener();
        initKeyEventProcess();
        initCustomUDIDProcess();
        initCustomMatchProcess();
        PhicommDeviceStatusProcessor.getInstance().startMonitorStatus();
    }

    private void initDeviceStatusListener() {
        this.mDeviceStatusProcessor = PhicommDeviceStatusProcessor.getInstance();
        this.mDeviceStatusProcessor.addDeviceStatusChangedListener(this);
    }

    private void initCustomUDIDProcess() {
        this.udidProcessor = new UDIDProcessor(this.mContext, this.mANTEngine);
        this.udidProcessor.register();
    }

    private void initCustomMatchProcess() {
        this.matchProcessor = new MatchProcessor(this.mContext, this.mANTEngine);
        this.matchProcessor.register();
    }

    private void initKeyEventProcess() {
        this.mKeyEventProcessor = new PhicommKeyEventProcessor(new PhicommKeyEventController(this.mANTEngine, this.mContext), this.mContext);
        this.mKeyEventProcessor.register();
    }

    private void onDormantStatusChanged(boolean isDormant) {
        Map<String, String> content = new HashMap<>();
        content.put(SelfDefinationRequestInfo.CURRENT_DORMANT_STATUS, isDormant ? "1" : "0");
        SessionRegister.getUpDownMessageManager().onReportStatus(DstServiceName.DST_SERVICE_SELF_DEFINATION, null, DstServiceName.DST_SERVICE_SELF_DEFINATION, DstServiceName.DST_SERVICE_SELF_DEFINATION, new SelfDefinationResponseInfo(SelfDefinationRequestInfo.MODIFY_DORMANT_STATUS, 0, content));
    }
}

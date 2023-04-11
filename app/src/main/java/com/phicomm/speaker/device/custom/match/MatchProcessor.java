package com.phicomm.speaker.device.custom.match;

import android.content.Context;
import android.util.Log;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.ipc.PhicommLightController;
import com.phicomm.speaker.device.custom.ipc.PhicommXController;
import com.phicomm.speaker.device.custom.outputevents.NetworkConfigOutputEvent;
import com.phicomm.speaker.device.custom.speech.SpeechManager;
import com.phicomm.speaker.device.custom.status.PhicommDeviceStatusProcessor;
import com.phicomm.speaker.device.ipc.IpcManager;
import com.phicomm.speaker.device.ipc.IpcReceiver;
import com.phicomm.speaker.device.utils.LogUtils;
import com.phicomm.speaker.device.utils.PhicommMessageManager;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.handler.session.memo.DefaultMemoRingingHandler;
import com.unisound.vui.util.UserPerferenceUtil;

public class MatchProcessor implements IpcReceiver {
    public static final String TAG = "MatchProcessor";
    private Context mContext;
    private ANTEngine mEngine;
    private IpcManager mMsgCenter;
    protected PhicommLightController mPhicommLightController;
    protected PhicommXController mPhicommXController;
    protected SpeechManager mSpeechManager;
    private PhicommDeviceStatusProcessor mStatusProcessor = PhicommDeviceStatusProcessor.getInstance();

    public MatchProcessor(Context context, ANTEngine engine) {
        this.mContext = context;
        this.mMsgCenter = IpcManager.defaultInstance(context);
        this.mEngine = engine;
        this.mSpeechManager = new SpeechManager(engine);
        this.mPhicommXController = new PhicommXController(context);
        this.mPhicommLightController = new PhicommLightController(context);
    }

    public void register() {
        Log.d(TAG, "regitster");
        this.mMsgCenter.registerReceiver(this, 262144);
    }

    @Override // com.phicomm.speaker.device.ipc.IpcReceiver
    public void onReceive(int domain, int subDomain, int sessionId, Object data) {
        if (subDomain == PhicommMessageManager.DOMAIN_MATCH_PHIJOIN_CONFIG) {
            LogUtils.d(TAG, "onReceive phijoin config");
            processPhijoinConnectNet(data);
        }
    }

    public void unregister() {
        Log.d(TAG, "regitster");
        this.mMsgCenter.unRegisterReceiver(this);
    }

    private void startPhijoinConnectNet(Object data) {
        LogUtils.d(TAG, "startPhijoinConnectNet");
        this.mEngine.pipeline().write(new NetworkConfigOutputEvent(true));
        UserPerferenceUtil.setStartWakeupAfterSetWakeupWord(this.mContext, false);
        this.mPhicommXController.openPhijoinConnectNetStatus(data);
        this.mSpeechManager.stopWakeup();
    }

    private void processPhijoinConnectNet(Object data) {
        int status = this.mStatusProcessor.getDeviceStatus();
        LogUtils.d(TAG, "processPhijoinConnectNet device status : " + status);
        if (isRingingStatus()) {
            onRingingStatus(data);
            return;
        }
        switch (status) {
            case 0:
                LogUtils.d(TAG, "onReadyStatus");
                dispatchReadyStatus(data);
                return;
            case 1:
                LogUtils.d(TAG, "onMusicStatus");
                turnOffWakeupLightsIfNeeds();
                this.mSpeechManager.exitMusicDomain();
                this.mSpeechManager.playTTS(R.string.tts_start_match_net);
                startPhijoinConnectNet(data);
                return;
            case 2:
                LogUtils.d(TAG, "onNetStatus");
                return;
            case 3:
                LogUtils.d(TAG, "onBlueToothStatus");
                turnOffWakeupLightsIfNeeds();
                this.mSpeechManager.playTTS(R.string.tts_start_match_net);
                this.mPhicommXController.closeBlueToothStatus();
                startPhijoinConnectNet(data);
                return;
            case 4:
            default:
                return;
            case 5:
                LogUtils.d(TAG, "onDormantStatus");
                this.mSpeechManager.playTTS(R.string.tts_start_match_net);
                startPhijoinConnectNet(data);
                return;
        }
    }

    private boolean isRingingStatus() {
        return this.mEngine.hasAttr(DefaultMemoRingingHandler.ALARM_PLAYING) && ((Boolean) this.mEngine.attr(DefaultMemoRingingHandler.ALARM_PLAYING).get()).booleanValue();
    }

    private void onRingingStatus(Object data) {
        LogUtils.d(TAG, "onRingingStatus");
        this.mSpeechManager.exitMusicDomain();
        this.mSpeechManager.stopRinging();
        this.mSpeechManager.playTTS(R.string.tts_start_match_net);
        startPhijoinConnectNet(data);
    }

    private void dispatchReadyStatus(Object data) {
        if (isRecordingStatus(this.mEngine)) {
            onRecordingStatus(data);
        } else if (isSpeechHandling(this.mEngine)) {
            onSpeechHandingStatus(data);
        } else {
            onReadyStatus(data);
        }
    }

    private boolean isRecordingStatus(ANTEngine mANTEngine) {
        return mANTEngine.isASR();
    }

    private boolean isSpeechHandling(ANTEngine mANTEngine) {
        return mANTEngine.isRecognition() || mANTEngine.isTTSPlaying();
    }

    public void onSpeechHandingStatus(Object data) {
        LogUtils.d(TAG, "onSpeechHandingStatus");
        this.mPhicommLightController.turnOffALLWakeupLight();
        this.mPhicommLightController.turnOffLoadingLight();
        this.mSpeechManager.playTTS(R.string.tts_start_match_net);
        this.mSpeechManager.cancelRecognize();
        startPhijoinConnectNet(data);
    }

    public void onRecordingStatus(Object data) {
        LogUtils.d(TAG, "onRecordingStatus");
        onSpeechHandingStatus(data);
    }

    public void onReadyStatus(Object data) {
        LogUtils.d(TAG, "onReadyStatus");
        this.mSpeechManager.playTTS(R.string.tts_start_match_net);
        startPhijoinConnectNet(data);
    }

    private void turnOffWakeupLightsIfNeeds() {
        if (this.mEngine.isASR() || this.mEngine.isRecognition()) {
            this.mPhicommLightController.turnOffALLWakeupLight();
        }
    }
}

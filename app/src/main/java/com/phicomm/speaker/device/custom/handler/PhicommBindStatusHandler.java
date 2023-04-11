package com.phicomm.speaker.device.custom.handler;

import android.content.Context;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.ipc.PhicommLightController;
import com.phicomm.speaker.device.utils.LogUtils;
import com.unisound.ant.device.netmodule.DeviceInfoUtils;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.engine.ANTEngineOption;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.engine.ANTOutboundHandlerAdapter;
import com.unisound.vui.transport.out.BindStatusEvent;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.ThreadUtils;
import com.unisound.vui.util.UserPerferenceUtil;
import java.util.Arrays;

public class PhicommBindStatusHandler extends ANTOutboundHandlerAdapter {
    private static final String TAG = PhicommBindStatusHandler.class.getSimpleName();
    private ANTEngine mANTEngine;
    private Context mContext;
    private final PhicommLightController mLightController;

    public PhicommBindStatusHandler(Context context, ANTEngine engine) {
        this.mContext = context;
        this.mANTEngine = engine;
        this.mLightController = new PhicommLightController(context);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandlerAdapter, com.unisound.vui.engine.ANTOutboundHandler
    public void write(ANTHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof BindStatusEvent)) {
            super.write(ctx, msg);
        } else if (((Boolean) ((BindStatusEvent) msg).getData()).booleanValue()) {
            onBound();
        } else {
            onUnbound();
        }
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandlerAdapter, com.unisound.vui.engine.ANTOutboundHandler
    public void enterASR(ANTHandlerContext ctx) throws Exception {
        ThreadUtils.execute(new Runnable() {
            /* class com.phicomm.speaker.device.custom.handler.PhicommBindStatusHandler.AnonymousClass1 */

            public void run() {
                PhicommBindStatusHandler.this.queryDeviceBoundStatus();
            }
        });
        super.enterASR(ctx);
    }

    private void onBound() {
        LogUtils.d(TAG, "device is bound");
        UserPerferenceUtil.setDeviceBindState(this.mContext, true);
    }

    private void onUnbound() {
        LogUtils.d(TAG, "device is unbound");
        UserPerferenceUtil.setDeviceBindState(this.mContext, false);
        clearWakeupWord();
        if (this.mANTEngine.isASR()) {
            this.mLightController.turnOffALLWakeupLight();
            this.mANTEngine.cancelASR();
            this.mANTEngine.enterWakeup(false);
        } else if (this.mANTEngine.isTTSPlaying()) {
            this.mANTEngine.cancelTTS();
            this.mANTEngine.pipeline().fireTTSEvent(2107);
        } else {
            this.mANTEngine.pipeline().fireUserEventTriggered(ExoConstants.DO_FINISH_ALL_INTERRUPT);
        }
    }

    private void clearWakeupWord() {
        this.mANTEngine.cancelTTS();
        this.mANTEngine.cancelASR();
        this.mANTEngine.stopWakeup();
        this.mANTEngine.updateWakeupWord(Arrays.asList(this.mContext.getResources().getStringArray(R.array.default_wakeup_words)));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void queryDeviceBoundStatus() {
        LogMgr.d(TAG, "query device bound status");
        try {
            if (DeviceInfoUtils.isDeviceBounded(this.mANTEngine.config().getOption(ANTEngineOption.GENERAL_UDID))) {
                onBound();
                return;
            }
            onUnbound();
            this.mANTEngine.playTTS(this.mContext.getString(R.string.tts_not_bind));
        } catch (Exception e) {
            LogMgr.e(TAG, "queryDeviceBoundStatus error, " + e.getMessage());
        }
    }
}

package com.unisound.vui.handler.session.light;

import android.content.Context;
import com.unisound.ant.device.event.TurnOffWakeLightEvent;
import com.unisound.ant.device.netmodule.NetChangeReceiver;
import com.unisound.vui.engine.ANTEngineOption;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleSessionManagementHandler;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.ThreadUtils;

public class DefaultLightsHandler extends SimpleSessionManagementHandler implements NetChangeReceiver.NetStateListener {
    private static final String TAG = DefaultLightsHandler.class.getSimpleName();
    private LightListener mLightListener;
    private NetChangeReceiver mNetChangeReceiver = new NetChangeReceiver();

    public DefaultLightsHandler(LightListener lightListener, Context context) {
        this.mLightListener = lightListener;
        this.mNetChangeReceiver.setStateListener(this);
        this.mNetChangeReceiver.registerNetReceiverAndCheck(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx, String interruptType) {
        this.mLightListener.onInterrupt();
        super.doInterrupt(ctx, interruptType);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onWakeupResult(final ANTHandlerContext ctx, String result) {
        ThreadUtils.execute(new Runnable() {
            /* class com.unisound.vui.handler.session.light.DefaultLightsHandler.AnonymousClass1 */

            public void run() {
                DefaultLightsHandler.this.mLightListener.onWakeupSuccess(((Integer) ctx.engine().config().getOption(ANTEngineOption.ASR_FOURMIC_DOA_RESULT)).intValue());
            }
        });
        return false;
    }

    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void userEventTriggered(Object evt, ANTHandlerContext ctx) throws Exception {
        super.userEventTriggered(evt, ctx);
        if (evt instanceof TurnOffWakeLightEvent) {
            this.mLightListener.onInterrupt();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventRecordingStop(ANTHandlerContext ctx) {
        this.mLightListener.onRecognizeStart();
        return super.onASREventRecordingStop(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventRecognitionEnd(ANTHandlerContext ctx) {
        this.mLightListener.onRecognizeStop();
        return super.onASREventRecognitionEnd(ctx);
    }

    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onASRError(int type, String error, ANTHandlerContext ctx) throws Exception {
        String mErrorCode = JsonTool.getJsonValue(JsonTool.parseToJSONObject(error), "errorCode");
        if (!"-90002".equals(mErrorCode) && !"-90005".equals(mErrorCode)) {
            this.mLightListener.onRecognizeStop();
        }
        super.onASRError(type, error, ctx);
    }

    @Override // com.unisound.ant.device.netmodule.NetChangeReceiver.NetStateListener
    public void onNetConnecting() {
        this.mLightListener.onNetConnected();
    }

    @Override // com.unisound.ant.device.netmodule.NetChangeReceiver.NetStateListener
    public void onNetConnected() {
        this.mLightListener.onNetDisconnected();
    }

    @Override // com.unisound.ant.device.netmodule.NetChangeReceiver.NetStateListener
    public void onNetDisconnected() {
        this.mLightListener.onNetDisconnected();
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        this.mLightListener.onTTSEnd();
        return false;
    }
}

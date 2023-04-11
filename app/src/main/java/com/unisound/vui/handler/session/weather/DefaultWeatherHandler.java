package com.unisound.vui.handler.session.weather;

import com.phicomm.speaker.device.R;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SessionRegister;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.SpeakerTTSUtil;
import com.unisound.vui.util.UserPerferenceUtil;
import nluparser.scheme.NLU;
import nluparser.scheme.SName;
import nluparser.scheme.WeatherIntent;
import nluparser.scheme.WeatherResult;

public class DefaultWeatherHandler extends SimpleUserEventInboundHandler<NLU<WeatherIntent, WeatherResult>> {
    private static final String TAG = "DefaultWeatherHandler";
    private String content;
    private boolean isDelayNeeded = true;

    public DefaultWeatherHandler() {
        this.sessionName = SessionRegister.SESSION_WEATHER;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    /* access modifiers changed from: protected */
    @Override
    public boolean acceptInboundEvent0(NLU<WeatherIntent, WeatherResult> evt) throws Exception {
        return evt.getService().equals(SName.WEATHER);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        this.ctx = ctx;
        return super.onASREventEngineInitDone(ctx);
    }

    /* access modifiers changed from: protected */
    @Override
    public void eventReceived(NLU<WeatherIntent, WeatherResult> evt, ANTHandlerContext ctx) throws Exception {
        super.eventReceived(evt, ctx);
        afterEventRecived(evt, ctx);
    }

    private void afterEventRecived(NLU<WeatherIntent, WeatherResult> evt, ANTHandlerContext ctx) {
        if (evt.getData() == null || evt.getData().getResult() == null) {
            this.content = SpeakerTTSUtil.getTTSString(R.array.tts_weather_no_result, 0, ctx.androidContext());
            doCtxAction(evt, ctx);
            this.isDelayNeeded = false;
            return;
        }
        this.content = evt.getData().getHeader();
        doCtxAction(evt, ctx);
        this.isDelayNeeded = true;
    }

    private void doCtxAction(NLU<WeatherIntent, WeatherResult> evt, ANTHandlerContext ctx) {
        ctx.stopWakeup();
        ctx.stopASR();
        ctx.playTTS(this.content);
        sendFullLogToDeviceCenter(evt, this.content);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (!this.eventReceived) {
            return super.onTTSEventPlayingEnd(ctx);
        }
        exit(true);
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx, String interruptType) {
        if (this.eventReceived) {
            interrupt(interruptType);
        }
    }

    private void exit(boolean fireResume) {
        this.ctx.cancelTTS();
        this.ctx.cancelEngine();
        this.ctx.setWakeupWord(UserPerferenceUtil.getWakeupWord(this.ctx.androidContext()), false);
        reset();
        if (fireResume) {
            fireResume(this.ctx);
        }
    }

    private void interrupt(String interruptType) {
        LogMgr.d(TAG, "----interruptType----" + interruptType);
        this.ctx.cancelTTS();
        if (!ExoConstants.DO_ONE_SHOT_INTERRUPT.equals(interruptType)) {
            this.ctx.enterWakeup(false);
        }
        reset();
    }
}

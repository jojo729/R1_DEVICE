package com.unisound.vui.handler.session;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.phicomm.speaker.device.R;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SessionRegister;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.SpeakerTTSUtil;
import com.unisound.vui.util.UserPerferenceUtil;
import com.unisound.vui.util.e;
import com.unisound.vui.util.internal.RandomHelper;
import nluparser.scheme.Error;
import nluparser.scheme.LocalASR;
import nluparser.scheme.NLU;
import nluparser.scheme.SName;

public class DefaultUnSupportHandler extends SimpleUserEventInboundHandler<NLU> {
    public DefaultUnSupportHandler(Context context) {
        this.sessionName = SessionRegister.SESSION_UNSUPPORT;
    }

    private void a(String str) {
        reset();
        this.playCancelTTS = false;
        this.ctx.cancelTTS();
    }

    private void a(boolean z, boolean z2, String str) {
        this.ctx.cancelTTS();
        this.ctx.cancelEngine();
        resetErrorTime();
        this.ctx.setWakeupWord(UserPerferenceUtil.getWakeupWord(this.ctx.androidContext()), false);
        if (!TextUtils.isEmpty(str)) {
            this.playCancelTTS = true;
            this.ctx.playTTS(str);
        } else {
            reset();
        }
        if (z2) {
            fireResume(this.ctx);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    @Override
    public void eventReceived(NLU nlu, ANTHandlerContext aNTHandlerContext) throws Exception {
        String random;
        super.eventReceived(nlu, aNTHandlerContext);
        LogMgr.d("DefaultUnSupportHandler", "unsupport = " + nlu.getService());
        increaseErrorTime();
        Error error = nlu.getError();
        String str = null;
        if (error != null) {
            str = error.getCode();
        }
        if ("unsupportedDomain".equals(nlu.getCode())) {
            random = RandomHelper.getRandom(R.array.tts_unsupport_answer, aNTHandlerContext.androidContext());
        } else if (e.a(nlu.getCode())) {
            random = aNTHandlerContext.androidContext().getString(R.string.tts_net_is_weak);
        } else if ("bluetooth_error".equals(nlu.getCode())) {
            random = nlu.getText();
        } else if ("".equals(nlu.getText()) || "-63551".equals(nlu.getCode())) {
            aNTHandlerContext.pipeline().fireTTSEvent(2107);
            return;
        } else {
            random = getErrorTime() > 0 ? RandomHelper.getRandom(R.array.tts_unsupport_answer, aNTHandlerContext.androidContext()) : "2040".equals(str) ? RandomHelper.getRandom(R.array.tts_no_voice_input, aNTHandlerContext.androidContext()) : RandomHelper.getRandom(R.array.tts_no_voice_input, aNTHandlerContext.androidContext());
        }
        aNTHandlerContext.playTTS(random);
        sendFullLogToDeviceCenter(nlu, random);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    @Override
    public boolean acceptInboundEvent0(NLU nlu) throws Exception {
        Log.d("DefaultUnSupportHandler", "acceptInboundEvent0: unsupport");
        if (!SName.SHOW_PUSH_SERVER.equals(nlu.getService()) && !SName.WECHAT.equals(nlu.getService())) {
            return true;
        }
        Log.d("DefaultUnSupportHandler", "acceptInboundEvent0: pushserver");
        return false;
    }

    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void cancelAsrEventTriggered(LocalASR localASR, ANTHandlerContext ctx) {
        if (hasActiveHandlers()) {
            if (!this.eventReceived) {
                fireActiveInterrupt(ctx);
            }
            mark(true);
            a(true, false, SpeakerTTSUtil.getTTSString(R.string.tts_cancel, 0, ctx.androidContext()));
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx, String interruptType) {
        LogMgr.d("DefaultUnSupportHandler", "-doInterrupt-" + this.eventReceived + "; errorTime = " + this.errorTime);
        if (this.eventReceived) {
            a(interruptType);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doResume(ANTHandlerContext ctx) {
        ctx.pipeline().fireUserEventTriggered(new ExoConstants.b());
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (this.playCancelTTS) {
            this.playCancelTTS = false;
            reset();
            fireResume(ctx);
            return true;
        } else if (!this.eventReceived) {
            return super.onTTSEventPlayingEnd(ctx);
        } else {
            a(true, true, null);
            return true;
        }
    }
}

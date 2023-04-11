package com.unisound.ant.platform.illeagechat;

import com.phicomm.speaker.device.R;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.util.SpeakerTTSUtil;
import nluparser.scheme.NLU;
import nluparser.scheme.SCode;
import nluparser.scheme.SName;

public class DefaultIlleageChatHandler extends SimpleUserEventInboundHandler<NLU> {
    private static final String TAG = "DefaultIlleageChatHandler";

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    /* access modifiers changed from: protected */
    @Override
    public boolean acceptInboundEvent0(NLU evt) throws Exception {
        return SName.CHAT_ILLEGAL.equals(evt.getService());
    }

    /* access modifiers changed from: protected */
    @Override
    public void eventReceived(NLU evt, ANTHandlerContext ctx) throws Exception {
        super.eventReceived(evt, ctx);
        String playContent = SpeakerTTSUtil.getTTSString(R.string.tts_unsupport, -1, ctx.androidContext());
        if ((SCode.ILLEGAL_DIRTY.equals(evt.getCode()) || SCode.ILLEGAL_YELLOW.equals(evt.getCode()) || SCode.ILLEGAL_SENSITIVE.equals(evt.getCode())) && evt.getGeneral() != null) {
            playContent = evt.getGeneral().getText();
        }
        ctx.playTTS(playContent);
        sendFullLogToDeviceCenter(evt, playContent);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (this.eventReceived) {
            exitSession();
        }
        return super.onTTSEventPlayingEnd(ctx);
    }

    private void exitSession() {
        reset();
    }
}

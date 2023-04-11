package com.phicomm.speaker.device.custom.handler;

import com.phicomm.speaker.device.custom.tts.PhicommEvent;
import com.unisound.vui.engine.ANTDuplexHandler;
import com.unisound.vui.engine.ANTHandlerContext;

public class PhicommTTSHandler extends ANTDuplexHandler {
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.engine.ANTOutboundHandler
    public void write(ANTHandlerContext ctx, Object msg) throws Exception {
        if (isPhicommTTSEventFormat(msg)) {
            playTTS(ctx, ((PhicommEvent) msg).getTtsContent());
        } else {
            super.write(ctx, msg);
        }
    }

    private boolean isPhicommTTSEventFormat(Object msg) {
        return (msg instanceof PhicommEvent) && "playTTS".equals(((PhicommEvent) msg).getType());
    }
}

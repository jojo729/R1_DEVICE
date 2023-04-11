package com.unisound.vui.engine;

import java.util.List;

public class ANTOutboundHandlerAdapter extends ANTHandlerAdapter implements ANTOutboundHandler {
    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void cancelASR(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.cancelASR();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void cancelEngine(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.cancelEngine();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void cancelTTS(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.cancelTTS();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void doPPTAction(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.doPPTAction();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void enterASR(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.enterASR();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void enterWakeup(ANTHandlerContext aNTHandlerContext, boolean z) throws Exception {
        aNTHandlerContext.enterWakeup(z);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void initializeMode(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.initializeMode();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void initializeSdk(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.initializeSdk();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void playBuffer(ANTHandlerContext aNTHandlerContext, byte[] bArr) throws Exception {
        aNTHandlerContext.playBuffer(bArr);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void playTTS(ANTHandlerContext aNTHandlerContext, String str) throws Exception {
        aNTHandlerContext.playTTS(str);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void setWakeupWord(ANTHandlerContext aNTHandlerContext, List<String> list, boolean z) throws Exception {
        aNTHandlerContext.setWakeupWord(list, z);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void stopASR(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.stopASR();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void stopWakeup(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.stopWakeup();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void updateWakeupWord(ANTHandlerContext aNTHandlerContext, List<String> list) throws Exception {
        aNTHandlerContext.updateWakeupWord(list);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void write(ANTHandlerContext aNTHandlerContext, Object obj) throws Exception {
        aNTHandlerContext.write(obj);
    }
}

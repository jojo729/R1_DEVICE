package com.unisound.vui.engine;

public class ANTInboundHandlerAdapter extends ANTHandlerAdapter implements ANTInboundHandler {
    @Override // com.unisound.vui.engine.ANTInboundHandler
    public void onASRError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.fireASRError(i, str);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler
    public void onASREvent(int i, ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.fireASREvent(i);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler
    public void onASRResult(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.fireASRResult(i, str);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler
    public void onNLUError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.fireNLUError(i, str);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler
    public void onNLUEvent(int i, ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.fireNLUEvent(i);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler
    public void onNLUResult(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.fireNLUResult(i, str);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler
    public void onTTSError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.fireTTSError(i, str);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler
    public void onTTSEvent(int i, ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.fireTTSEvent(i);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler
    public void userEventTriggered(Object obj, ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.fireUserEventTriggered(obj);
    }
}

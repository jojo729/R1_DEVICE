package com.unisound.vui.engine;

public abstract class ANTHandlerAdapter implements ANTHandler {
    @Override // com.unisound.vui.engine.ANTHandler
    public void exceptionCaught(ANTHandlerContext aNTHandlerContext, Throwable th) throws Exception {
        aNTHandlerContext.fireExceptionCaught(th);
    }
}

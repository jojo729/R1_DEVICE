package com.unisound.vui.engine;

public interface ANTInboundHandler extends ANTHandler {
    void onASRError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception;

    void onASREvent(int i, ANTHandlerContext aNTHandlerContext) throws Exception;

    void onASRResult(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception;

    void onNLUError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception;

    void onNLUEvent(int i, ANTHandlerContext aNTHandlerContext) throws Exception;

    void onNLUResult(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception;

    void onTTSError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception;

    void onTTSEvent(int i, ANTHandlerContext aNTHandlerContext) throws Exception;

    void userEventTriggered(Object obj, ANTHandlerContext aNTHandlerContext) throws Exception;
}

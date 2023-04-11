package com.unisound.vui.engine;

import java.util.List;

public interface ANTOutboundHandler extends ANTHandler {
    void cancelASR(ANTHandlerContext aNTHandlerContext) throws Exception;

    void cancelEngine(ANTHandlerContext aNTHandlerContext) throws Exception;

    void cancelTTS(ANTHandlerContext aNTHandlerContext) throws Exception;

    void doPPTAction(ANTHandlerContext aNTHandlerContext) throws Exception;

    void enterASR(ANTHandlerContext aNTHandlerContext) throws Exception;

    void enterWakeup(ANTHandlerContext aNTHandlerContext, boolean z) throws Exception;

    void initializeMode(ANTHandlerContext aNTHandlerContext) throws Exception;

    void initializeSdk(ANTHandlerContext aNTHandlerContext) throws Exception;

    void playBuffer(ANTHandlerContext aNTHandlerContext, byte[] bArr) throws Exception;

    void playTTS(ANTHandlerContext aNTHandlerContext, String str) throws Exception;

    void setWakeupWord(ANTHandlerContext aNTHandlerContext, List<String> list, boolean z) throws Exception;

    void stopASR(ANTHandlerContext aNTHandlerContext) throws Exception;

    void stopWakeup(ANTHandlerContext aNTHandlerContext) throws Exception;

    void updateWakeupWord(ANTHandlerContext aNTHandlerContext, List<String> list) throws Exception;

    void write(ANTHandlerContext aNTHandlerContext, Object obj) throws Exception;
}

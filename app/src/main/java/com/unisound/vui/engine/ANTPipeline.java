package com.unisound.vui.engine;

import java.util.List;

public interface ANTPipeline {
    ANTPipeline addAfter(String str, String str2, ANTHandler aNTHandler);

    ANTPipeline addBefore(String str, String str2, ANTHandler aNTHandler);

    ANTPipeline addFirst(String str, ANTHandler aNTHandler);

    ANTPipeline addFirst(ANTHandler... aNTHandlerArr);

    ANTPipeline addLast(String str, ANTHandler aNTHandler);

    ANTPipeline addLast(ANTHandler... aNTHandlerArr);

    void cancelASR();

    void cancelTTS();

    ANTHandlerContext context(ANTHandler aNTHandler);

    ANTHandlerContext context(String str);

    void doPPTAction();

    ANTEngine engine();

    void enterASR();

    void enterWakeup(boolean z);

    ANTPipeline fireASRError(int i, String str);

    ANTPipeline fireASREvent(int i);

    ANTPipeline fireASRResult(int i, String str);

    ANTPipeline fireNLUError(int i, String str);

    ANTPipeline fireNLUEvent(int i);

    ANTPipeline fireNLUResult(int i, String str);

    ANTPipeline fireTTSError(int i, String str);

    ANTPipeline fireTTSEvent(int i);

    ANTPipeline fireUserEventTriggered(Object obj);

    void initializeMode();

    void initializeSdk();

    void playTTS(String str);

    ANTHandler remove(String str);

    ANTPipeline remove(ANTHandler aNTHandler);

    void setWakeupWord(List<String> list, boolean z);

    void stopASR();

    void stopWakeup();

    void updateWakeupWord(List<String> list);

    void write(Object obj);
}

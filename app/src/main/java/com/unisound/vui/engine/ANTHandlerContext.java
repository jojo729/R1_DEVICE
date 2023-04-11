package com.unisound.vui.engine;

import android.content.Context;
import com.unisound.vui.util.AttributeMap;

import java.util.List;

public interface ANTHandlerContext extends AttributeMap {
    Context androidContext();

    void cancelASR();

    void cancelEngine();

    void cancelTTS();

    void doPPTAction();

    ANTEngine engine();

    void enterASR();

    void enterWakeup(boolean z);

    ANTHandlerContext fireASRError(int i, String str);

    ANTHandlerContext fireASREvent(int i);

    ANTHandlerContext fireASRResult(int i, String str);

    ANTHandlerContext fireExceptionCaught(Throwable th);

    ANTHandlerContext fireNLUError(int i, String str);

    ANTHandlerContext fireNLUEvent(int i);

    ANTHandlerContext fireNLUResult(int i, String str);

    ANTHandlerContext fireTTSError(int i, String str);

    ANTHandlerContext fireTTSEvent(int i);

    ANTHandlerContext fireUserEventTriggered(Object obj);

    ANTHandler handler();

    void initializeMode();

    void initializeSdk();

    String name();

    ANTPipeline pipeline();

    void playBuffer(byte[] bArr);

    void playTTS(String str);

    void setWakeupWord(List<String> list, boolean z);

    void stopASR();

    void stopWakeup();

    void updateWakeupWord(List<String> list);

    void write(Object obj);
}

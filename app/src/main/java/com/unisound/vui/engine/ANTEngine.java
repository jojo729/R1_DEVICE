package com.unisound.vui.engine;

import android.content.Context;
import com.unisound.vui.util.AttributeKey;
import com.unisound.vui.util.AttributeMap;

import java.util.List;
import java.util.Map;

public interface ANTEngine extends AttributeMap {
    public static final AttributeKey<Object> AUDIO_FOCUS_HELPER = AttributeKey.newInstance("AudioFocusHelper");
    public static final int ENGINE_ASR = 2;
    public static final int ENGINE_IDLE = 0;
    public static final int ENGINE_RECOGNITION = 3;
    public static final int ENGINE_WAKEUP = 1;

    Context androidContext();

    void cancelASR();

    void cancelTTS();

    void closeReleaseStatus();

    ANTEngineConfig config();

    void doPPTAction();

    void enableOneshot(boolean z);

    void enterASR();

    void enterWakeup(boolean z);

    boolean getAecEnable();

    int getEngineState();

    void initializeMode();

    void initializeSdk();

    void insertVocab(Map<String, List<String>> map, String str);

    boolean isASR();

    boolean isIdle();

    boolean isModeInitialized();

    boolean isNeedRecoveryWakeUp();

    boolean isOneshot();

    boolean isRecognition();

    boolean isShowVolume();

    boolean isTTSPlaying();

    boolean isWakeup();

    boolean isWakeupRecord();

    ANTPipeline pipeline();

    void playTTS(String str);

    void releaseAudioRecord();

    void setNeedRecoveryWakeUp(boolean z);

    void setWakeupRecord(boolean z);

    void setWakeupWord(List<String> list, boolean z);

    void stopASR();

    void stopWakeup();

    Unsafe unsafe();

    void updateWakeupWord(List<String> list);

    List<String> wakeupWord();

    void write(Object obj);

    public interface Unsafe {
        void cancelASR();

        void cancelEngine();

        void cancelTTS();

        void enterASR();

        void enterWakeup(boolean z);

        Object getOption(int i);

        String getVersion();

        void initEngine();

        void initializeMode();

        void initializeSdk();

        void insertVocab(Map<String, List<String>> map, String str);

        int loadCompiledJsgf(String str, String str2);

        int loadGrammar(String str, String str2);

        void markModeInitialized();

        void playBuffer(byte[] bArr);

        void playTTS(String str);

        void release(int i, String str);

        void setASROption(int i, Object obj);

        void setAndroidContext(Context context);

        void setMainTag(String str);

        void setNLUOption(int i, Object obj);

        void setOption(String str, Object obj);

        void setTTSOption(int i, Object obj);

        void setWakeupWord(List<String> list, boolean z);

        void stopASR();

        void stopWakeup();

        int unloadGrammar(String str);

        void updateCustomWakeupWord(List<String> list);

        void updateWakeupWord(List<String> list);

        void uploadUserData(Map<Integer, List<String>> map);

        void write(Object obj);
    }
}

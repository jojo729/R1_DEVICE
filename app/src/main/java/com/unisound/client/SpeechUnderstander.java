package com.unisound.client;

import android.content.Context;
import com.unisound.sdk.bg;
import java.util.List;
import java.util.Map;

public class SpeechUnderstander extends bg {
    public SpeechUnderstander(Context var1, String var2, String var3) {
        super(var1, var2, var3);
    }

    public void cancel() {
        super.cancel();
    }

    public String convertToArabicNumber(String var1) {
        return super.convertToArabicNumber(var1);
    }

    public String getFixEngineVersion() {
        return super.getFixEngineVersion();
    }

    public Object getOption(int var1) {
        return super.getOption(var1);
    }

    public String getVersion() {
        return super.getVersion();
    }

    public void init(String var1) {
        super.init(var1);
    }

    public int insertVocab(List var1, String var2) {
        return super.insertVocab(var1, var2);
    }

    public int insertVocab(Map var1, String var2) {
        return super.insertVocab(var1, var2);
    }

    public int insertVocab_ext(String var1, String var2, String var3) {
        return super.insertVocab_ext(var1, var2, var3);
    }

    public int insertVocab_ext(String var1, String var2, Map var3) {
        return super.insertVocab_ext(var1, var2, var3);
    }

    public int loadCompiledJsgf(String var1, String var2) {
        return super.loadCompiledJsgf(var1, var2);
    }

    public int loadGrammar(String var1, String var2) {
        return super.loadGrammar(var1, var2);
    }

    public int release(int var1, String var2) {
        return super.release(var1, var2);
    }

    public int setAudioSource(IAudioSource var1) {
        return super.setAudioSource(var1);
    }

    public void setListener(SpeechUnderstanderListener var1) {
        super.setListener(var1);
    }

    public String setOnlineWakeupWord(List var1) {
        return super.setOnlineWakeupWord(var1);
    }

    public void setOption(int var1, Object var2) {
        super.setOption(var1, var2);
    }

    public int setWakeupWord(List var1) {
        return super.setWakeupWord(var1);
    }

    public int setWakeupWord(Map var1) {
        return super.setWakeupWord(var1);
    }

    public void start() {
        super.start();
    }

    public void start(String var1) {
        super.start(var1);
    }

    public void stop() {
        super.stop();
    }

    public int unloadGrammar(String var1) {
        return super.unloadGrammar(var1);
    }

    public void uploadUserData(Map var1) {
        super.uploadUserData(var1);
    }
}

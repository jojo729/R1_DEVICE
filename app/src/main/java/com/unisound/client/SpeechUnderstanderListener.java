package com.unisound.client;

public interface SpeechUnderstanderListener {
    void onError(int i, String str);

    void onEvent(int i, int i2);

    void onResult(int i, String str);
}

package com.unisound.client;

public interface SpeechSynthesizerListener {
    void onError(int i, String str);

    void onEvent(int i);
}

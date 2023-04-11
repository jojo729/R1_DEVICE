package com.unisound.client;

public interface FourMicAudioManagerListener {
    void onError(String str);

    void onEvent(int i);

    void onRecordingData(byte[] bArr, int i);
}

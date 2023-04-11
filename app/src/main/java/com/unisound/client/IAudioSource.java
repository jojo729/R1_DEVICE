package com.unisound.client;

public interface IAudioSource {
    void closeAudioIn();

    void closeAudioOut();

    int openAudioIn();

    int openAudioOut();

    int readData(byte[] bArr, int i);

    int writeData(byte[] bArr, int i);
}

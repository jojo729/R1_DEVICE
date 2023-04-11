package com.phicomm.speaker.device.custom.keyevent.processor;

public interface PhicommStatusListener {
    void onBlueToothStatus();

    void onDormantStatus();

    void onMusicStatus();

    void onNetStatus();

    void onReadyStatus();

    void onRecordingStatus();

    void onRingingStatus();

    void onSpeechHandingStatus();
}

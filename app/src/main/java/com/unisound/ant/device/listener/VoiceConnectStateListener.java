package com.unisound.ant.device.listener;

public interface VoiceConnectStateListener {
    void onStartVoiceConnect();

    void onVoiceConnectFailEnd();

    void onVoiceConnectFailStart();

    void onVoiceConnectSuccess();
}

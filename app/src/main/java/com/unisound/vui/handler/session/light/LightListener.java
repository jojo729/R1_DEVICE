package com.unisound.vui.handler.session.light;

public interface LightListener {
    void onInterrupt();

    void onNetConnected();

    void onNetDisconnected();

    void onRecognizeStart();

    void onRecognizeStop();

    void onTTSEnd();

    void onWakeupResume();

    void onWakeupStop();

    void onWakeupSuccess(int i);
}

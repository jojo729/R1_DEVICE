package com.phicomm.speaker.device.custom.api;

public interface CustomApiListener {
    public static final int EVENT_TYPE_RINGING_LISTENING = 1;

    void onCustomEvent(int i, Object obj);
}

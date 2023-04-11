package com.phicomm.speaker.device.custom.keyevent.processor;

public class SessionIdCreator {
    private static int sessionId = 10000;

    public static int get() {
        int i = sessionId;
        sessionId = i + 1;
        return i;
    }
}

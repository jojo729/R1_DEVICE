package com.phicomm.speaker.device.utils;

import android.os.MessageDispatchManager;
import com.unisound.vui.util.LogMgr;

public class PhicommPlayerUtils {
    public static final int MESSAGE_PAUSE = 2;
    public static final int MESSAGE_PLAY_NEXT = 6;
    public static final int MESSAGE_PLAY_PREV = 5;
    public static final int MESSAGE_RESUME = 3;
    public static final int MESSAGE_STOP = 4;
    private static final String TAG = "PhicommPlayerUtils";

    private PhicommPlayerUtils() {
    }

    public static void pause(MessageDispatchManager messageDispatchManager) {
        LogMgr.d(TAG, "pause: start to pause");
        messageDispatchManager.sendMessage(4, 2, -1, null);
    }

    public static void resume(MessageDispatchManager messageDispatchManager) {
        LogMgr.d(TAG, "resume: start to resume");
        messageDispatchManager.sendMessage(4, 3, -1, null);
    }

    public static void stop(MessageDispatchManager messageDispatchManager) {
        LogMgr.d(TAG, "stop: start to stop");
        messageDispatchManager.sendMessage(4, 4, -1, null);
    }

    public static void playPrev(MessageDispatchManager messageDispatchManager) {
        LogMgr.d(TAG, "playPrev: start to play prev");
        messageDispatchManager.sendMessage(4, 5, -1, null);
    }

    public static void playNext(MessageDispatchManager messageDispatchManager) {
        LogMgr.d(TAG, "playNext: start to play next");
        messageDispatchManager.sendMessage(4, 6, -1, null);
    }
}

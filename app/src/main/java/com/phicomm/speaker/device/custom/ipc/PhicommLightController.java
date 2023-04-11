package com.phicomm.speaker.device.custom.ipc;

import android.content.Context;

public class PhicommLightController {
    private static final int LIGHT_ACTION_CLOSE = 1;
    private static final int LIGHT_ACTION_OPEN = 0;
    private static final int LIGHT_ID_DORMANT = 100;
    private static final int LIGHT_ID_LOADING = 203;
    private static final int LIGHT_ID_NET_DISCONNECT = 254;
    private static final int LIGHT_WHAT = 4096;
    private static int lastLightIndex = -1;
    private PhicommIpcSender mPhicommIpcSender;

    public PhicommLightController(Context context) {
        this.mPhicommIpcSender = new PhicommIpcSender(context);
    }

    public void turnOnWakeupIndexLight(int index) {
        lastLightIndex = index;
        this.mPhicommIpcSender.sendMessage(4096, index, 0, null);
    }

    public void turnOnWakeupLastLight() {
        this.mPhicommIpcSender.sendMessage(4096, lastLightIndex, 0, null);
    }

    public void turnOffALLWakeupLight() {
        for (int index = 1; index <= 24; index++) {
            this.mPhicommIpcSender.sendMessage(4096, index, 1, null);
        }
    }

    public void turnOnLoadingLight() {
        this.mPhicommIpcSender.sendMessage(4096, 203, 0, null);
    }

    public void turnOffLoadingLight() {
        this.mPhicommIpcSender.sendMessage(4096, 203, 1, null);
    }

    public void turnOnDormantLight() {
        this.mPhicommIpcSender.sendMessage(4096, 100, 0, null);
    }

    public void turnOffDormantLight() {
        this.mPhicommIpcSender.sendMessage(4096, 100, 1, null);
    }

    public void turnOnNetDisconnectedLight() {
        this.mPhicommIpcSender.sendMessage(4096, LIGHT_ID_NET_DISCONNECT, 0, null);
    }

    public void turnOffNetDisconenctedLight() {
        this.mPhicommIpcSender.sendMessage(4096, LIGHT_ID_NET_DISCONNECT, 1, null);
    }
}

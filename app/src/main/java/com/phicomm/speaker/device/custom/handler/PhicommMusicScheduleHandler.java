package com.phicomm.speaker.device.custom.handler;

import com.phicomm.speaker.device.custom.status.PhicommDeviceStatusProcessor;
import com.unisound.vui.handler.session.music.AbstractMusicScheduleHandler;
import com.unisound.vui.util.LogMgr;

public class PhicommMusicScheduleHandler extends AbstractMusicScheduleHandler {
    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.session.music.AbstractMusicScheduleHandler
    public boolean isInMusicStatus() {
        int deviceStatus = PhicommDeviceStatusProcessor.getInstance().getDeviceStatus();
        LogMgr.d("AbstractMusicScheduleHandler", "deviceStatus:" + deviceStatus);
        if (deviceStatus == 1) {
            return true;
        }
        return false;
    }
}

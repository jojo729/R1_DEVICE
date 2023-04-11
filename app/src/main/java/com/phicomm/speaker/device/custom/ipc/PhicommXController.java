package com.phicomm.speaker.device.custom.ipc;

import android.content.Context;
import android.os.Parcelable;
import android.os.ParcelableUtil;
import com.phicomm.speaker.device.utils.LogUtils;

public class PhicommXController {
    private static final String TAG = "PhicommXController";
    private PhicommIpcSender mPhicommIpcSender;

    public enum DeviceStatus {
        Speech,
        Music,
        ConnectNet,
        BlueTooth,
        Dormant
    }

    public PhicommXController(Context context) {
        this.mPhicommIpcSender = new PhicommIpcSender(context);
    }

    public void openBlueToothStatus() {
        LogUtils.d(TAG, "enter buletooth status");
        this.mPhicommIpcSender.sendMessage(64, 1, -1, null);
    }

    public void closeBlueToothStatus() {
        LogUtils.d(TAG, "close buletooth status");
        this.mPhicommIpcSender.sendMessage(64, 2, -1, null);
    }

    public void resetBlueToothStatus() {
        LogUtils.d(TAG, "reset buletooth status");
        this.mPhicommIpcSender.sendMessage(64, 3, -1, null);
    }

    public void openConnectNetStatus() {
        LogUtils.d(TAG, "open connect net status");
        this.mPhicommIpcSender.sendMessage(262144, 1, -1, null);
    }

    public void closeConnectNetStatus() {
        LogUtils.d(TAG, "close connect net status");
        this.mPhicommIpcSender.sendMessage(262144, 2, -1, null);
    }

    public void openPhijoinConnectNetStatus(Object data) {
        LogUtils.d(TAG, "open phijoin connect net status");
        this.mPhicommIpcSender.sendMessage(262144, 3, -1, (ParcelableUtil) data);
    }

    public void syncDeviceStatus(DeviceStatus status) {
        Parcelable data = null;
        switch (status) {
            case Speech:
                data = ParcelableUtil.obtain(0);
                break;
            case Music:
                data = ParcelableUtil.obtain(1);
                break;
            case ConnectNet:
                data = ParcelableUtil.obtain(2);
                break;
            case BlueTooth:
                data = ParcelableUtil.obtain(3);
                break;
            case Dormant:
                data = ParcelableUtil.obtain(5);
                break;
        }
        LogUtils.d(TAG, "sync " + status + " status");
        this.mPhicommIpcSender.send(true, 32768, 8, 16384, 8, -1, data);
    }

    public void closeMusic() {
        this.mPhicommIpcSender.sendMessage(4, 4, -1, null);
    }

    public void closeMusicFadeout() {
        this.mPhicommIpcSender.sendMessage(4, 10, -1, null);
    }

    public void resetDevice() {
        this.mPhicommIpcSender.sendMessage(16384, 8, -1, null);
    }

    public void triggeredTropleClickEvent() {
        this.mPhicommIpcSender.sendMessage(256, 3, -1, null);
    }

    public void triggeredDoubleClickEvent() {
        this.mPhicommIpcSender.sendMessage(256, 2, -1, null);
    }

    public void triggeredOneClickEvent() {
        this.mPhicommIpcSender.sendMessage(256, 1, -1, null);
    }

    public void syncBindSuccessEvent() {
        this.mPhicommIpcSender.sendMessage(2097152, 100, -1, null);
    }

    public void syncBindFailEvent(int errorCode) {
        this.mPhicommIpcSender.sendMessage(2097152, 101, -1, ParcelableUtil.obtain(errorCode));
    }

    public void openAmbientLight() {
        this.mPhicommIpcSender.sendMessage(4, 64, 1, null);
    }

    public void closeAmbientLight() {
        this.mPhicommIpcSender.sendMessage(4, 64, 0, null);
    }

    public void openSoundEffect() {
        this.mPhicommIpcSender.sendMessage(16384, 100, 0, ParcelableUtil.obtain(1));
    }

    public void closeSoundEffect() {
        this.mPhicommIpcSender.sendMessage(16384, 100, 0, ParcelableUtil.obtain(0));
    }
}

package com.phicomm.speaker.device.ipc;

import android.os.Parcelable;

public interface IpcSender {
    void sendMessage(int i, int i2, int i3, Parcelable parcelable);
}

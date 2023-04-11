package com.phicomm.speaker.device.ipc;

public interface IpcRegister {
    void registerReceiver(IpcReceiver ipcReceiver, int i);

    void unRegisterReceiver(IpcReceiver ipcReceiver);
}

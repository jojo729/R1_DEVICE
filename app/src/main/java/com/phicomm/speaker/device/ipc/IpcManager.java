package com.phicomm.speaker.device.ipc;

import android.content.Context;
import android.os.Parcelable;
import com.phicomm.speaker.device.custom.ipc.PhicommIpcRegister;
import com.phicomm.speaker.device.custom.ipc.PhicommIpcSender;

public class IpcManager extends AbsIpcManager {
    private IpcRegister mIpcReceiver;
    private IpcSender mIpcSender;
    private IpcReceiver mReceiver;

    public IpcManager(IpcSender ipcSender, IpcRegister ipcRegister) {
        this.mIpcSender = ipcSender;
        this.mIpcReceiver = ipcRegister;
    }

    public static IpcManager defaultInstance(Context context) {
        return new IpcManager(new PhicommIpcSender(context), new PhicommIpcRegister(context));
    }

    public void registerReceiver(IpcReceiver receiver, int domain) {
        this.mReceiver = receiver;
        this.mIpcReceiver.registerReceiver(receiver, domain);
    }

    @Override // com.phicomm.speaker.device.ipc.AbsIpcManager, com.phicomm.speaker.device.ipc.IpcSender
    public void sendMessage(int what, int subDomain, int sessionId, Parcelable data) {
        super.sendMessage(what, subDomain, sessionId, data);
        this.mIpcSender.sendMessage(what, subDomain, sessionId, data);
    }

    @Override // com.phicomm.speaker.device.ipc.AbsIpcManager, android.os.MessageDispatchManager.MessageReceiver
    public void notifyMsg(int domain, int subDomain, int sessionId, Object data) {
        super.notifyMsg(domain, subDomain, sessionId, data);
        this.mReceiver.onReceive(domain, subDomain, sessionId, data);
    }

    public void unRegisterReceiver(IpcReceiver receiver) {
        this.mIpcReceiver.unRegisterReceiver(receiver);
    }
}

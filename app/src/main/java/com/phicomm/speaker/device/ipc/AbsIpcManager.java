package com.phicomm.speaker.device.ipc;

import android.os.MessageDispatchManager;
import android.os.Parcelable;
import com.unisound.vui.util.LogMgr;

public class AbsIpcManager implements IpcSender, MessageDispatchManager.MessageReceiver {
    public static final String TAG = "IpcManagerAOP";

    @Override // com.phicomm.speaker.device.ipc.IpcSender
    public void sendMessage(int domain, int subDomain, int sessionId, Parcelable data) {
        dobeforeSendMessage(domain, subDomain, sessionId, data);
    }

    @Override // android.os.MessageDispatchManager.MessageReceiver
    public void notifyMsg(int what, int arg1, int arg2, Object obj) {
        dobeforeNotifyMsg(what, arg1, arg2, obj);
    }

    private void dobeforeSendMessage(int domain, int subDomain, int sessionId, Parcelable data) {
        LogMgr.d(TAG, "send message : domain " + domain + " subDomain : " + subDomain + " sessionId : " + sessionId + " data : " + (data != null ? data.toString() : null));
    }

    private void dobeforeNotifyMsg(int what, int arg1, int arg2, Object obj) {
        LogMgr.d(TAG, "receive message : domain " + what + " subDomain : " + arg1 + " sessionId : " + arg2 + " data : " + (obj != null ? obj.toString() : null));
    }
}

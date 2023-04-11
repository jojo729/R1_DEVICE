package com.phicomm.speaker.device.custom.ipc;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;
import com.phicomm.speaker.device.custom.message.MessageSenderDelegate;
import com.phicomm.speaker.device.ipc.IpcSender;

public class PhicommIpcSender implements IpcSender {
    private static final String TAG = "PhicommIpcSender";
    private MessageSenderDelegate mSender = MessageSenderDelegate.getInstance();

    public PhicommIpcSender(Context context) {
    }

    @Override // com.phicomm.speaker.device.ipc.IpcSender
    public void sendMessage(int what, int subDomain, int sessionId, Parcelable data) {
//        Log.d(TAG, "what : " + what + " subDomain : " + subDomain + " sessionId : " + sessionId);
        send(what, subDomain, sessionId, data);
    }

    public void send(int type, int subType, Parcelable data) {
        send(type, subType, -1, data);
    }

    public void send(int type, int subType, int msgId) {
        send(type, subType, msgId, null);
    }

    public void send(int type, int subType, int msgId, Parcelable data) {
        send(false, -1, -1, type, subType, msgId, data);
    }

    public void send(boolean loopUntilReply, int replyType, int subReplyType, int type, int subType, int msgId, Parcelable data) {
        this.mSender.send(loopUntilReply, replyType, subReplyType, type, subType, msgId, data);
    }
}

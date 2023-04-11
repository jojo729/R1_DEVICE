package com.phicomm.speaker.device.custom.message;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.MessageDispatchManager;
import android.os.Parcelable;
import cn.yunzhisheng.asr.JniUscClient;
import com.phicomm.speaker.device.utils.LogUtils;
import com.phicomm.speaker.device.utils.PhicommMessageManager;
import java.util.HashMap;
import java.util.Map;

public class MessageSenderDelegate implements MessageDispatchManager.MessageReceiver {
    private static final int MSG_SEND_INTERVAL = 3000;
    private static final String TAG = MessageSenderDelegate.class.getSimpleName();
    @SuppressLint({"StaticFieldLeak"})
    private static Context sContext;
    private MessageDispatchManager mMessageManager;
    private Handler mSendHandler;
    private Map<String, PhicommMessage> mSendMap;

    private MessageSenderDelegate() {
        this.mMessageManager = PhicommMessageManager.messageCenter(sContext);
        this.mSendHandler = new LoopSendHandler();
        this.mSendMap = new HashMap();
    }

    public static void init(Context context) {
        sContext = context;
    }

    public static MessageSenderDelegate getInstance() {
        return Holder.sDelegate;
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
        if (loopUntilReply) {
            PhicommMessage msg = new PhicommMessage(type, subType, msgId, data, replyType, subReplyType);
            this.mSendMap.put(getSentMsgKey(replyType, subReplyType), msg);
            this.mMessageManager.registerMessageReceiver(this, replyType);
            int what = getMessageWhat(type, subType);
            if (this.mSendHandler.hasMessages(what)) {
                this.mSendHandler.removeMessages(what);
            }
            this.mSendHandler.sendMessageDelayed(this.mSendHandler.obtainMessage(what, msg), 3000);
        }
//        LogUtils.d(TAG, "send message to phicomm, type = " + type + ", subType = " + subType + ", needReply = " + loopUntilReply + ", replyType = " + replyType + ", subReplyType = " + subReplyType + ", msgId = " + msgId + ", data = " + (data == null ? JniUscClient.az : data.toString()));
        this.mMessageManager.sendMessage(type, subType, msgId, data);
    }

    @Override // android.os.MessageDispatchManager.MessageReceiver
    public void notifyMsg(int replyType, int subReplyType, int msgId, Object obj) {
        String key = getSentMsgKey(replyType, subReplyType);
        PhicommMessage send = this.mSendMap.get(key);
        LogUtils.d(TAG, "received message, type = " + replyType + ", subType = " + subReplyType + ", msgId = " + msgId + ", sent message = " + send);
        if (send != null && subReplyType == send.getSubReplyType()) {
            this.mSendHandler.removeMessages(getMessageWhat(send.getType(), send.getSubType()));
            this.mSendMap.remove(key);
        }
    }

    private int getMessageWhat(int type, int subType) {
        return (type * 100) + subType;
    }

    private String getSentMsgKey(int replyType, int subReplyType) {
        return String.valueOf(replyType) + subReplyType;
    }

    private static class Holder {
        @SuppressLint({"StaticFieldLeak"})
        private static MessageSenderDelegate sDelegate = new MessageSenderDelegate();

        private Holder() {
        }
    }

    @SuppressLint({"HandlerLeak"})
    private class LoopSendHandler extends Handler {
        private LoopSendHandler() {
        }

        public void handleMessage(Message msg) {
            PhicommMessage phicommMsg = (PhicommMessage) msg.obj;
               send(true, phicommMsg.getReplyType(), phicommMsg.getSubReplyType(), phicommMsg.getType(), phicommMsg.getSubType(), phicommMsg.getMsgId(), phicommMsg.getData());
        }
    }
}

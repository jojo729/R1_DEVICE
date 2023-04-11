package com.phicomm.speaker.device.custom.message;

import android.os.Parcelable;

public class PhicommMessage {
    private Parcelable data;
    private int msgId;
    private int replyType;
    private int subReplyType;
    private int subType;
    private int type;

    public PhicommMessage(int type2, int subType2) {
        this.type = type2;
        this.subType = subType2;
    }

    public PhicommMessage(int type2, int subType2, int msgId2) {
        this.type = type2;
        this.subType = subType2;
        this.msgId = msgId2;
    }

    public PhicommMessage(int type2, int subType2, int msgId2, Parcelable data2) {
        this.type = type2;
        this.subType = subType2;
        this.msgId = msgId2;
        this.data = data2;
    }

    public PhicommMessage(int type2, int subType2, int msgId2, Parcelable data2, int replyType2, int subReplyType2) {
        this.type = type2;
        this.subType = subType2;
        this.msgId = msgId2;
        this.data = data2;
        this.replyType = replyType2;
        this.subReplyType = subReplyType2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public int getSubType() {
        return this.subType;
    }

    public void setSubType(int subType2) {
        this.subType = subType2;
    }

    public int getMsgId() {
        return this.msgId;
    }

    public void setMsgId(int msgId2) {
        this.msgId = msgId2;
    }

    public Parcelable getData() {
        return this.data;
    }

    public void setData(Parcelable data2) {
        this.data = data2;
    }

    public int getReplyType() {
        return this.replyType;
    }

    public void setReplyType(int replyType2) {
        this.replyType = replyType2;
    }

    public int getSubReplyType() {
        return this.subReplyType;
    }

    public void setSubReplyType(int subReplyType2) {
        this.subReplyType = subReplyType2;
    }

    public String toString() {
        return "PhicommMessage{type=" + this.type + ", subType=" + this.subType + ", msgId=" + this.msgId + ", data=" + this.data + ", replyType=" + this.replyType + ", subReplyType=" + this.subReplyType + '}';
    }
}

package com.unisound.ant.device.mqtt.bean;

public class ChangeMessage extends SupMessage {
    private String msgId;
    private String msgType;
    private long ts;

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId2) {
        this.msgId = msgId2;
    }

    public long getTs() {
        return this.ts;
    }

    public void setTs(long ts2) {
        this.ts = ts2;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String msgType2) {
        this.msgType = msgType2;
    }
}

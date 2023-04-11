package com.unisound.ant.device.mqtt.bean;

public class ResponseMsg {
    private String clientId;
    private int eventType;
    private String msgId;
    private String sendStatus;

    public int getEventType() {
        return this.eventType;
    }

    public void setEventType(int eventType2) {
        this.eventType = eventType2;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId2) {
        this.clientId = clientId2;
    }

    public String getSendStatus() {
        return this.sendStatus;
    }

    public void setSendStatus(String sendStatus2) {
        this.sendStatus = sendStatus2;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId2) {
        this.msgId = msgId2;
    }
}

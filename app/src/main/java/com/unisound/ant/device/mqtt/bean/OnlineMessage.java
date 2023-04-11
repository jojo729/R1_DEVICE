package com.unisound.ant.device.mqtt.bean;

public class OnlineMessage extends SupMessage {
    private String clientId;
    private ClientInfo clientInfo;
    private int eventType;

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId2) {
        this.clientId = clientId2;
    }

    public int getEventType() {
        return this.eventType;
    }

    public void setEventType(int eventType2) {
        this.eventType = eventType2;
    }

    public ClientInfo getClientInfo() {
        return this.clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo2) {
        this.clientInfo = clientInfo2;
    }
}

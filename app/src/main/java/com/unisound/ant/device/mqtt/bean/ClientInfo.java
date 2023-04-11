package com.unisound.ant.device.mqtt.bean;

public class ClientInfo {
    private String appKey;
    private String extras;
    private long passportId;
    private int subsystemId;
    private String udid;

    public String getExtras() {
        return this.extras;
    }

    public void setExtras(String extras2) {
        this.extras = extras2;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String appKey2) {
        this.appKey = appKey2;
    }

    public String getUdid() {
        return this.udid;
    }

    public void setUdid(String udid2) {
        this.udid = udid2;
    }

    public int getSubsystemId() {
        return this.subsystemId;
    }

    public void setSubsystemId(int subsystemId2) {
        this.subsystemId = subsystemId2;
    }

    public long getPassportId() {
        return this.passportId;
    }

    public void setPassportId(long passportId2) {
        this.passportId = passportId2;
    }
}

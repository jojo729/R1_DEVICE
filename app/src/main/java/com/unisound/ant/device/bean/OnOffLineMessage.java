package com.unisound.ant.device.bean;

public class OnOffLineMessage extends Parameter {
    String connAccessId;
    String phoneUdid;
    String tdid;
    String token;
    String udid;

    public String getUdid() {
        return this.udid;
    }

    public void setUdid(String udid2) {
        this.udid = udid2;
    }

    public String getPhoneUdid() {
        return this.phoneUdid;
    }

    public void setPhoneUdid(String phoneUdid2) {
        this.phoneUdid = phoneUdid2;
    }

    public String getTdid() {
        return this.tdid;
    }

    public void setTdid(String tdid2) {
        this.tdid = tdid2;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token2) {
        this.token = token2;
    }

    public String getConnAccessId() {
        return this.connAccessId;
    }

    public void setConnAccessId(String connAccessId2) {
        this.connAccessId = connAccessId2;
    }
}

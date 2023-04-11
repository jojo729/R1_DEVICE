package com.unisound.ant.device.mqtt.bean;

import java.util.Arrays;

public class MqttClientParam {
    private String clientid;
    private String connectUrl;
    private String passWord;
    private String publish;
    private String[] subscribe;
    private String userName;

    public String getConnectUrl() {
        return this.connectUrl;
    }

    public void setConnectUrl(String connectUrl2) {
        this.connectUrl = connectUrl2;
    }

    public String getClientid() {
        return this.clientid;
    }

    public void setClientid(String clientid2) {
        this.clientid = clientid2;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName2) {
        this.userName = userName2;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord2) {
        this.passWord = passWord2;
    }

    public String[] getSubscribe() {
        return this.subscribe;
    }

    public void setSubscribe(String[] subscribe2) {
        this.subscribe = subscribe2;
    }

    public String getPublish() {
        return this.publish;
    }

    public void setPublish(String publish2) {
        this.publish = publish2;
    }

    public String toString() {
        return "MqttClientParam{connectUrl='" + this.connectUrl + '\'' + ", clientid='" + this.clientid + '\'' + ", userName='" + this.userName + '\'' + ", passWord='" + this.passWord + '\'' + ", subscribe=" + Arrays.toString(this.subscribe) + ", publish='" + this.publish + '\'' + '}';
    }
}

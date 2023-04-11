package com.unisound.ant.device.bean;

import java.util.HashMap;

public class PhicommStatisticInfo {
    private HashMap<String, Object> data;
    private String from;
    private String key;
    private String msgId;

    public PhicommStatisticInfo(String msgId2, String key2, String from2, HashMap<String, Object> data2) {
        this.msgId = msgId2;
        this.key = key2;
        this.from = from2;
        this.data = data2;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId2) {
        this.msgId = msgId2;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key2) {
        this.key = key2;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from2) {
        this.from = from2;
    }

    public HashMap<String, Object> getData() {
        return this.data;
    }

    public void setData(HashMap<String, Object> data2) {
        this.data = data2;
    }
}

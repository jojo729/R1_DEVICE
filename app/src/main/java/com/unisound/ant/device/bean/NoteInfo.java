package com.unisound.ant.device.bean;

import java.util.UUID;

public class NoteInfo {
    private String createTime;
    private String id = UUID.randomUUID().toString();
    private String msg;

    public String getId() {
        return this.id;
    }

    public void setId(String id2) {
        this.id = id2;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime2) {
        this.createTime = createTime2;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg2) {
        this.msg = msg2;
    }
}

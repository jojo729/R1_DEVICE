package com.unisound.ant.device.bean;

public class VoiceChatMessage {
    private String createTime;
    private boolean isUserEdited;
    private String logId;
    private String msg;

    public String getLogId() {
        return this.logId;
    }

    public void setLogId(String logId2) {
        this.logId = logId2;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg2) {
        this.msg = msg2;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime2) {
        this.createTime = createTime2;
    }

    public boolean isUserEdited() {
        return this.isUserEdited;
    }

    public void setUserEdited(boolean userEdited) {
        this.isUserEdited = userEdited;
    }
}

package com.unisound.ant.device.service;

public class ActionResponse {
    private String actionResponseId;
    private int actionStatus;
    private String actionTimestamp;
    private String detailInfo;

    public String getActionResponseId() {
        return this.actionResponseId;
    }

    public void setActionResponseId(String actionResponseId2) {
        this.actionResponseId = actionResponseId2;
    }

    public String getActionTimestamp() {
        return this.actionTimestamp;
    }

    public void setActionTimestamp(String actionTimestamp2) {
        this.actionTimestamp = actionTimestamp2;
    }

    public int getActionStatus() {
        return this.actionStatus;
    }

    public void setActionStatus(int actionStatus2) {
        this.actionStatus = actionStatus2;
    }

    public String getDetailInfo() {
        return this.detailInfo;
    }

    public void setDetailInfo(String detailInfo2) {
        this.detailInfo = detailInfo2;
    }

    public String toString() {
        return "ActionResponse{actionResponseId='" + this.actionResponseId + '\'' + ", actionTimestamp='" + this.actionTimestamp + '\'' + ", actionStatus=" + this.actionStatus + ", detailInfo='" + this.detailInfo + '\'' + '}';
    }
}

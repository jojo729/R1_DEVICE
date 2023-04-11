package com.unisound.ant.device.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

public class AlarmReminder {
    public static final String MEMO_STATUS_CANCEL = "cancel";
    public static final String MEMO_STATUS_FINISH = "finished";
    public static final String MEMO_STATUS_RUNNING = "running";
    public static final String MEMO_STATUS_START = "start";
    @SerializedName("countDownTime")
    @JSONField(name = "countDownTime")
    private int countDown;
    private String date;
    private String id;
    private boolean isOpen;
    private String label;
    private String repeatDate;
    @SerializedName("alarmName")
    @JSONField(name = "alarmName")
    private String ringing;
    private String status;
    private String time;
    private String type;

    public String getId() {
        return this.id;
    }

    public void setId(String id2) {
        this.id = id2;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean isOpen2) {
        this.isOpen = isOpen2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type2) {
        this.type = type2;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date2) {
        this.date = date2;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time2) {
        this.time = time2;
    }

    public String getRepeatDate() {
        return this.repeatDate;
    }

    public void setRepeatDate(String repeatDate2) {
        this.repeatDate = repeatDate2;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label2) {
        this.label = label2;
    }

    public String getRinging() {
        return this.ringing;
    }

    public void setRinging(String ringing2) {
        this.ringing = ringing2;
    }

    public int getCountDown() {
        return this.countDown;
    }

    public void setCountDown(int countDown2) {
        this.countDown = countDown2;
    }
}

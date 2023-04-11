package com.unisound.vui.message;

public class MessageBeanHandlerGui<T> {
    private String action;
    private T data;
    private int duration;
    private String extra;
    private String sessionName;
    private VoiceTip voiceTip;

    public String getAction() {
        return this.action;
    }

    public T getData() {
        return this.data;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getExtra() {
        return this.extra;
    }

    public String getSessionName() {
        return this.sessionName;
    }

    public VoiceTip getVoiceTip() {
        return this.voiceTip;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setData(T t) {
        this.data = t;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public void setSessionName(String str) {
        this.sessionName = str;
    }

    public void setVoiceTip(VoiceTip voiceTip2) {
        this.voiceTip = voiceTip2;
    }
}

package com.unisound.ant.device.bean;

public class SceneModeInfo {
    private boolean isOpen;
    private String modeState;
    private String modeTip;
    private String modeType;

    public String getModeType() {
        return this.modeType;
    }

    public void setModeType(String modeType2) {
        this.modeType = modeType2;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public String getModeState() {
        return this.modeState;
    }

    public void setModeState(String modeState2) {
        this.modeState = modeState2;
    }

    public String getModeTip() {
        return this.modeTip;
    }

    public void setModeTip(String modeTip2) {
        this.modeTip = modeTip2;
    }

    public String toString() {
        return "SceneModeInfo{modeType='" + this.modeType + '\'' + ", isOpen=" + this.isOpen + ", modeState='" + this.modeState + '\'' + ", modeTip='" + this.modeTip + '\'' + '}';
    }
}

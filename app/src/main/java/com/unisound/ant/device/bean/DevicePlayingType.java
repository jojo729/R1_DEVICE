package com.unisound.ant.device.bean;

public class DevicePlayingType {
    private String currentState;

    public DevicePlayingType(String currentState2) {
        this.currentState = currentState2;
    }

    public String getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(String currentState2) {
        this.currentState = currentState2;
    }
}

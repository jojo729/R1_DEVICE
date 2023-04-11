package com.unisound.ant.device.bean;

public class UserBinderStatus extends Parameter {
    String owner;
    String state;

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner2) {
        this.owner = owner2;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state2) {
        this.state = state2;
    }
}

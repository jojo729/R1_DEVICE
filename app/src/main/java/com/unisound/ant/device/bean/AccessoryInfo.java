package com.unisound.ant.device.bean;

public class AccessoryInfo {
    private String controllerFlag;
    private String token;

    public AccessoryInfo(String controllerFlag2, String token2) {
        this.controllerFlag = controllerFlag2;
        this.token = token2;
    }

    public String getControllerFlag() {
        return this.controllerFlag;
    }

    public void setControllerFlag(String controllerFlag2) {
        this.controllerFlag = controllerFlag2;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token2) {
        this.token = token2;
    }
}

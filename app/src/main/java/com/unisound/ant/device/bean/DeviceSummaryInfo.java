package com.unisound.ant.device.bean;

public class DeviceSummaryInfo extends Parameter {
    String deviceNickName;
    String environmentLocation;

    public DeviceSummaryInfo(String deviceNickName2, String environmentLocation2) {
        this.deviceNickName = deviceNickName2;
        this.environmentLocation = environmentLocation2;
    }

    public String getDeviceNickName() {
        return this.deviceNickName;
    }

    public void setDeviceNickName(String deviceNickName2) {
        this.deviceNickName = deviceNickName2;
    }

    public String getEnvironmentLocation() {
        return this.environmentLocation;
    }

    public void setEnvironmentLocation(String environmentLocation2) {
        this.environmentLocation = environmentLocation2;
    }
}

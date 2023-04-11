package com.unisound.ant.platform.smarthome.bean;

public class SmartDevice {
    private String deviceCode;
    private String deviceName;
    private String homeCode;
    private String homeName;
    private String roomCode;
    private String roomName;
    private SmartStateParamter stateInfo;
    private String vendorName;
    private String zoneCode;
    private String zoneName;

    public String getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(String deviceCode2) {
        this.deviceCode = deviceCode2;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public void setVendorName(String vendorName2) {
        this.vendorName = vendorName2;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String deviceName2) {
        this.deviceName = deviceName2;
    }

    public SmartStateParamter getStateInfo() {
        return this.stateInfo;
    }

    public void setStateInfo(SmartStateParamter stateInfo2) {
        this.stateInfo = stateInfo2;
    }

    public String getHomeName() {
        return this.homeName;
    }

    public void setHomeName(String homeName2) {
        this.homeName = homeName2;
    }

    public String getHomeCode() {
        return this.homeCode;
    }

    public void setHomeCode(String homeCode2) {
        this.homeCode = homeCode2;
    }

    public String getZoneName() {
        return this.zoneName;
    }

    public void setZoneName(String zoneName2) {
        this.zoneName = zoneName2;
    }

    public String getZoneCode() {
        return this.zoneCode;
    }

    public void setZoneCode(String zoneCode2) {
        this.zoneCode = zoneCode2;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName2) {
        this.roomName = roomName2;
    }

    public String getRoomCode() {
        return this.roomCode;
    }

    public void setRoomCode(String roomCode2) {
        this.roomCode = roomCode2;
    }
}

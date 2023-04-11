package com.unisound.ant.platform.smarthome.bean;

import nluparser.scheme.MusicResult;

import java.util.List;

public class SmartAction {
    private String anchorTime;
    private String datatype;
    private String device;
    private String deviceCode;
    private String deviceExpr;
    private String deviceType;
    private String duration;
    private String endTime;
    private String home;
    private String homeExpr;
    private String offsetTime;
    private String operands;
    private String operator;
    private String percentValue;
    private String percentValueDelta;
    private String repeat;
    private String room;
    private String roomExpr;
    private String roomType;
    private String time;
    private String timeDelta;
    private String timeExpr;
    private List<MusicResult.Music> value;
    private String valueDelta;
    private String vendorName;
    private String zone;
    private String zoneExpr;
    private String zoneType;

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator2) {
        this.operator = operator2;
    }

    public String getOperands() {
        return this.operands;
    }

    public void setOperands(String operands2) {
        this.operands = operands2;
    }

    public List<MusicResult.Music> getValue() {
        return this.value;
    }

    public void setValue(List<MusicResult.Music> value2) {
        this.value = value2;
    }

    public String getValueDelta() {
        return this.valueDelta;
    }

    public void setValueDelta(String valueDelta2) {
        this.valueDelta = valueDelta2;
    }

    public String getDatatype() {
        return this.datatype;
    }

    public void setDatatype(String datatype2) {
        this.datatype = datatype2;
    }

    public String getPercentValue() {
        return this.percentValue;
    }

    public void setPercentValue(String percentValue2) {
        this.percentValue = percentValue2;
    }

    public String getPercentValueDelta() {
        return this.percentValueDelta;
    }

    public void setPercentValueDelta(String percentValueDelta2) {
        this.percentValueDelta = percentValueDelta2;
    }

    public String getHome() {
        return this.home;
    }

    public void setHome(String home2) {
        this.home = home2;
    }

    public String getHomeExpr() {
        return this.homeExpr;
    }

    public void setHomeExpr(String homeExpr2) {
        this.homeExpr = homeExpr2;
    }

    public String getZone() {
        return this.zone;
    }

    public void setZone(String zone2) {
        this.zone = zone2;
    }

    public String getZoneType() {
        return this.zoneType;
    }

    public void setZoneType(String zoneType2) {
        this.zoneType = zoneType2;
    }

    public String getZoneExpr() {
        return this.zoneExpr;
    }

    public void setZoneExpr(String zoneExpr2) {
        this.zoneExpr = zoneExpr2;
    }

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room2) {
        this.room = room2;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public void setRoomType(String roomType2) {
        this.roomType = roomType2;
    }

    public String getRoomExpr() {
        return this.roomExpr;
    }

    public void setRoomExpr(String roomExpr2) {
        this.roomExpr = roomExpr2;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time2) {
        this.time = time2;
    }

    public String getTimeDelta() {
        return this.timeDelta;
    }

    public void setTimeDelta(String timeDelta2) {
        this.timeDelta = timeDelta2;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime2) {
        this.endTime = endTime2;
    }

    public String getRepeat() {
        return this.repeat;
    }

    public void setRepeat(String repeat2) {
        this.repeat = repeat2;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration2) {
        this.duration = duration2;
    }

    public String getTimeExpr() {
        return this.timeExpr;
    }

    public void setTimeExpr(String timeExpr2) {
        this.timeExpr = timeExpr2;
    }

    public String getOffsetTime() {
        return this.offsetTime;
    }

    public void setOffsetTime(String offsetTime2) {
        this.offsetTime = offsetTime2;
    }

    public String getAnchorTime() {
        return this.anchorTime;
    }

    public void setAnchorTime(String anchorTime2) {
        this.anchorTime = anchorTime2;
    }

    public String getDevice() {
        return this.device;
    }

    public void setDevice(String device2) {
        this.device = device2;
    }

    public String getDeviceExpr() {
        return this.deviceExpr;
    }

    public void setDeviceExpr(String deviceExpr2) {
        this.deviceExpr = deviceExpr2;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String deviceType2) {
        this.deviceType = deviceType2;
    }

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
}

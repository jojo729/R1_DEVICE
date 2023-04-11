package com.unisound.ant.device.profile;

import com.unisound.ant.device.bean.DeviceCapability;
import com.unisound.ant.device.bean.GeneralInfo;
import com.unisound.ant.device.bean.Parameter;
import java.util.ArrayList;
import java.util.List;

public class DeviceProfileInfo extends Parameter {
    private String aiChip;
    private List<DeviceCapability> capabilites = new ArrayList();
    private String category;
    private String deviceModel;
    private String deviceType;
    private GeneralInfo generalInfo;
    private String udid;

    public String getUdid() {
        return this.udid;
    }

    public void setUdid(String udid2) {
        this.udid = udid2;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String deviceType2) {
        this.deviceType = deviceType2;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String deviceModel2) {
        this.deviceModel = deviceModel2;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category2) {
        this.category = category2;
    }

    public String getAiChip() {
        return this.aiChip;
    }

    public void setAiChip(String aiChip2) {
        this.aiChip = aiChip2;
    }

    public List<DeviceCapability> getCapabilites() {
        return this.capabilites;
    }

    public void setCapabilites(List<DeviceCapability> capabilites2) {
        this.capabilites = capabilites2;
    }

    public GeneralInfo getGeneralInfo() {
        return this.generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo2) {
        this.generalInfo = generalInfo2;
    }
}

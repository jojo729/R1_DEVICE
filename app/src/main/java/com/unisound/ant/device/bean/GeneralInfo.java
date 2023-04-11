package com.unisound.ant.device.bean;

import androidx.annotation.Nullable;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GeneralInfo {
    @SerializedName("availableMemory")
    @Nullable
    @JSONField(name = "availableMemory")
    String availableMemory;
    @SerializedName("cpuSize")
    @Nullable
    @JSONField(name = "cpuSize")
    String cpuSize;
    @SerializedName("isRetainCamera")
    @Nullable
    @JSONField(name = "isRetainCamera")
    boolean isRetainCamera;
    @SerializedName("isRetainScreen")
    @Nullable
    @JSONField(name = "isRetainScreen")
    boolean isRetainScreen;
    @SerializedName("isRetainSpeaker")
    @Nullable
    @JSONField(name = "isRetainSpeaker")
    boolean isRetainSpeaker;
    @SerializedName("isSurpportBluetooth")
    @Nullable
    @JSONField(name = "isSurpportBluetooth")
    boolean isSurpportBluetooth;
    @SerializedName("isSurpportDirectionBySound")
    @Nullable
    @JSONField(name = "isSurpportDirectionBySound")
    boolean isSurpportDirectionBySound;
    @SerializedName("isSurpportGps")
    @Nullable
    @JSONField(name = "isSurpportGps")
    boolean isSurpportGps;
    @SerializedName("isSurpportIndoorGps")
    @Nullable
    @JSONField(name = "isSurpportIndoorGps")
    boolean isSurpportIndoorGps;
    @SerializedName("otherSensor")
    @Nullable
    @JSONField(name = "otherSensor")
    List<String> otherSensor;
    @SerializedName("totalMemory")
    @Nullable
    @JSONField(name = "totalMemory")
    String totalMemory;

    @Nullable
    public String getTotalMemory() {
        return this.totalMemory;
    }

    public void setTotalMemory(@Nullable String totalMemory2) {
        this.totalMemory = totalMemory2;
    }

    @Nullable
    public String getAvailableMemory() {
        return this.availableMemory;
    }

    public void setAvailableMemory(@Nullable String availableMemory2) {
        this.availableMemory = availableMemory2;
    }

    @Nullable
    public String getCpuSize() {
        return this.cpuSize;
    }

    public void setCpuSize(@Nullable String cpuSize2) {
        this.cpuSize = cpuSize2;
    }

    @Nullable
    public boolean isRetainScreen() {
        return this.isRetainScreen;
    }

    public void setRetainScreen(@Nullable boolean retainScreen) {
        this.isRetainScreen = retainScreen;
    }

    @Nullable
    public boolean isRetainCamera() {
        return this.isRetainCamera;
    }

    public void setRetainCamera(@Nullable boolean retainCamera) {
        this.isRetainCamera = retainCamera;
    }

    @Nullable
    public boolean isSurpportGps() {
        return this.isSurpportGps;
    }

    public void setSurpportGps(@Nullable boolean surpportGps) {
        this.isSurpportGps = surpportGps;
    }

    @Nullable
    public boolean isRetainSpeaker() {
        return this.isRetainSpeaker;
    }

    public void setRetainSpeaker(@Nullable boolean retainSpeaker) {
        this.isRetainSpeaker = retainSpeaker;
    }

    @Nullable
    public boolean isSurpportBluetooth() {
        return this.isSurpportBluetooth;
    }

    public void setSurpportBluetooth(@Nullable boolean surpportBluetooth) {
        this.isSurpportBluetooth = surpportBluetooth;
    }

    @Nullable
    public boolean isSurpportIndoorGps() {
        return this.isSurpportIndoorGps;
    }

    public void setSurpportIndoorGps(@Nullable boolean surpportIndoorGps) {
        this.isSurpportIndoorGps = surpportIndoorGps;
    }

    @Nullable
    public boolean isSurpportDirectionBySound() {
        return this.isSurpportDirectionBySound;
    }

    public void setSurpportDirectionBySound(@Nullable boolean surpportDirectionBySound) {
        this.isSurpportDirectionBySound = surpportDirectionBySound;
    }

    @Nullable
    public List<String> getOtherSensor() {
        return this.otherSensor;
    }

    public void setOtherSensor(@Nullable List<String> otherSensor2) {
        this.otherSensor = otherSensor2;
    }
}

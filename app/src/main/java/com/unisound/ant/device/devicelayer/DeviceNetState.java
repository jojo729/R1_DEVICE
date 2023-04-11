package com.unisound.ant.device.devicelayer;

import androidx.annotation.Nullable;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

public class DeviceNetState {
    @SerializedName("inNetIp")
    @Nullable
    @JSONField(name = "inNetIp")
    String inNetIp;
    @SerializedName("netType")
    @Nullable
    @JSONField(name = "netType")
    String netType;
    @SerializedName("outNetIp")
    @Nullable
    @JSONField(name = "outNetIp")
    String outNetIp;

    @Nullable
    public String getNetType() {
        return this.netType;
    }

    public void setNetType(@Nullable String netType2) {
        this.netType = netType2;
    }

    @Nullable
    public String getInNetIp() {
        return this.inNetIp;
    }

    public void setInNetIp(@Nullable String inNetIp2) {
        this.inNetIp = inNetIp2;
    }

    @Nullable
    public String getOutNetIp() {
        return this.outNetIp;
    }

    public void setOutNetIp(@Nullable String outNetIp2) {
        this.outNetIp = outNetIp2;
    }
}

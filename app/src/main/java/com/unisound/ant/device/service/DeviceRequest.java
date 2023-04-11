package com.unisound.ant.device.service;

import androidx.annotation.Nullable;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import com.phicomm.speaker.device.custom.udid.DeviceIdProcessor;
import com.unisound.ant.device.profile.AgentProfileInfo;
import com.unisound.ant.device.profile.DeviceProfileInfo;
import com.unisound.ant.device.profile.DstServiceProfile;
import com.unisound.ant.device.profile.EnvironmentProfileInfo;
import com.unisound.ant.device.profile.UserProfileInfo;
import com.unisound.ant.device.sessionlayer.DialogProfile;
import com.unisound.sdk.ca;

public class DeviceRequest {
    @SerializedName("agentProfile")
    @Nullable
    @JSONField(name = "agentProfile")
    AgentProfileInfo agentProfile;
    @SerializedName(ca.c)
    @Nullable
    @JSONField(name = ca.c)
    String appkey;
    @SerializedName(DeviceIdProcessor.SP_DEVICE_ID)
    @Nullable
    @JSONField(name = DeviceIdProcessor.SP_DEVICE_ID)
    String deviceId;
    @SerializedName("deviceProfile")
    @Nullable
    @JSONField(name = "deviceProfile")
    DeviceProfileInfo deviceProfile;
    @SerializedName("dialogProfile")
    @Nullable
    @JSONField(name = "dialogProfile")
    DialogProfile dialogProfile;
    @SerializedName("enviromentProfile")
    @Nullable
    @JSONField(name = "enviromentProfile")
    EnvironmentProfileInfo enviromentProfile;
    @SerializedName("messageType")
    @Nullable
    @JSONField(name = "messageType")
    String messageType;
    @SerializedName("reqId")
    @Nullable
    @JSONField(name = "reqId")
    String reqId;
    @SerializedName("serviceProfile")
    @Nullable
    @JSONField(name = "serviceProfile")
    DstServiceProfile serviceProfile;
    @SerializedName("userProfile")
    @Nullable
    @JSONField(name = "userProfile")
    UserProfileInfo userProfile;
    @SerializedName("version")
    @Nullable
    @JSONField(name = "version")
    String version;

    @Nullable
    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(@Nullable String deviceId2) {
        this.deviceId = deviceId2;
    }

    @Nullable
    public String getAppkey() {
        return this.appkey;
    }

    public void setAppkey(@Nullable String appkey2) {
        this.appkey = appkey2;
    }

    @Nullable
    public String getVersion() {
        return this.version;
    }

    public void setVersion(@Nullable String version2) {
        this.version = version2;
    }

    @Nullable
    public String getReqId() {
        return this.reqId;
    }

    public void setReqId(@Nullable String reqId2) {
        this.reqId = reqId2;
    }

    @Nullable
    public String getMessageType() {
        return this.messageType;
    }

    public void setMessageType(@Nullable String messageType2) {
        this.messageType = messageType2;
    }

    @Nullable
    public DeviceProfileInfo getDeviceProfile() {
        return this.deviceProfile;
    }

    public void setDeviceProfile(@Nullable DeviceProfileInfo deviceProfile2) {
        this.deviceProfile = deviceProfile2;
    }

    @Nullable
    public DstServiceProfile getServiceProfile() {
        return this.serviceProfile;
    }

    public void setServiceProfile(@Nullable DstServiceProfile serviceProfile2) {
        this.serviceProfile = serviceProfile2;
    }

    @Nullable
    public DialogProfile getDialogProfile() {
        return this.dialogProfile;
    }

    public void setDialogProfile(@Nullable DialogProfile dialogProfile2) {
        this.dialogProfile = dialogProfile2;
    }

    @Nullable
    public EnvironmentProfileInfo getEnviromentProfile() {
        return this.enviromentProfile;
    }

    public void setEnviromentProfile(@Nullable EnvironmentProfileInfo enviromentProfile2) {
        this.enviromentProfile = enviromentProfile2;
    }

    @Nullable
    public AgentProfileInfo getAgentProfile() {
        return this.agentProfile;
    }

    public void setAgentProfile(@Nullable AgentProfileInfo agentProfile2) {
        this.agentProfile = agentProfile2;
    }

    @Nullable
    public UserProfileInfo getUserProfile() {
        return this.userProfile;
    }

    public void setUserProfile(@Nullable UserProfileInfo userProfile2) {
        this.userProfile = userProfile2;
    }
}

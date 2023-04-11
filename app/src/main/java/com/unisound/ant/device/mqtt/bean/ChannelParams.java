package com.unisound.ant.device.mqtt.bean;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ChannelParams {
    private String appKey;
    private String appSecret;
    public String connAccessId;
    public String mHBOwnerId;
    public String mHBServerIp;
    public int mHBServerPort;
    public String mSRDeviceId;
    public String mSROwnerId;
    public String mSRServerIp = "10.10.11.2";
    public int mSRServerPort = 8090;
    private String tcDeviceId = "kDzBV2xaZnShsidc6FD+swkDpKV54renxqDEH+OmemY=";
    private String token = null;
    public String udid;

    public ChannelParams(Context context, String udid2) {
        udid2 = TextUtils.isEmpty(udid2) ? ((TelephonyManager) context.getSystemService("phone")).getDeviceId() : udid2;
        this.udid = udid2;
        this.mHBOwnerId = udid2 + "_owner";
        this.mSRDeviceId = udid2;
        this.mSROwnerId = udid2 + "_owner";
    }

    public String getUdid() {
        return this.udid;
    }

    public void setUdid(String udid2) {
        this.udid = udid2;
    }

    public String getConnAccessId() {
        return this.connAccessId;
    }

    public void setConnAccessId(String connAccessId2) {
        this.connAccessId = connAccessId2;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String appKey2) {
        this.appKey = appKey2;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String appSecret2) {
        this.appSecret = appSecret2;
    }

    public String getTcDeviceId() {
        return this.tcDeviceId;
    }

    public void setTcDeviceId(String tcDeviceId2) {
        this.tcDeviceId = tcDeviceId2;
    }

    public String getToken() {
        if (TextUtils.isEmpty(this.token)) {
            return "";
        }
        try {
            URLEncoder.encode(this.token, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this.token;
    }

    public void setToken(String token2) {
        this.token = token2;
    }

    public String toString() {
        return "ChannelParams{mHBServerIp='" + this.mHBServerIp + '\'' + ", mHBServerPort=" + this.mHBServerPort + ", udid='" + this.udid + '\'' + ", mHBOwnerId='" + this.mHBOwnerId + '\'' + ", mSRServerIp='" + this.mSRServerIp + '\'' + ", mSRServerPort=" + this.mSRServerPort + ", mSRDeviceId='" + this.mSRDeviceId + '\'' + ", mSROwnerId='" + this.mSROwnerId + '\'' + ", connAccessId='" + this.connAccessId + '\'' + ", appKey='" + this.appKey + '\'' + ", appSecret='" + this.appSecret + '\'' + ", tcDeviceId='" + this.tcDeviceId + '\'' + ", token='" + this.token + '\'' + '}';
    }
}

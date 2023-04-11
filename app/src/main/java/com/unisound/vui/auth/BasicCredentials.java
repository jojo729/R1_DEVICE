package com.unisound.vui.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

public final class BasicCredentials implements UNIOSCredentials {
    @SerializedName("accessKey")
    @JSONField(name = "accessKey")
    private String accessKey;
    @SerializedName("secretKey")
    @JSONField(name = "secretKey")
    private String secretKey;

    public BasicCredentials() {
    }

    public BasicCredentials(String accessKey2, String secretKey2) {
        if (accessKey2 == null) {
            throw new IllegalArgumentException("Access key cannot be null.");
        } else if (secretKey2 == null) {
            throw new IllegalArgumentException("Secret key cannot be null.");
        } else {
            this.accessKey = accessKey2;
            this.secretKey = secretKey2;
        }
    }

    @Override // com.unisound.vui.auth.UNIOSCredentials
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BasicCredentials basicCredentials = (BasicCredentials) o;
        if (!this.accessKey.equals(basicCredentials.accessKey)) {
            return false;
        }
        return !this.secretKey.equals(basicCredentials.secretKey);
    }

    @Override // com.unisound.vui.auth.UNIOSCredentials
    public String getAccessKey() {
        return this.accessKey;
    }

    @Override // com.unisound.vui.auth.UNIOSCredentials
    public String getSecretKey() {
        return this.secretKey;
    }

    @Override // com.unisound.vui.auth.UNIOSCredentials
    public int hashCode() {
        return (this.accessKey.hashCode() * 31) + this.secretKey.hashCode();
    }

    public void setAccessKey(String accessKey2) {
        this.accessKey = accessKey2;
    }

    public void setSecretKey(String secretKey2) {
        this.secretKey = secretKey2;
    }
}

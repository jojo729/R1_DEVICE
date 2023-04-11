package com.unisound.ant.device.mqtt;

public interface OnMQTTStatusChangeListener {
    void onFail(String str);

    void onSuccess();
}

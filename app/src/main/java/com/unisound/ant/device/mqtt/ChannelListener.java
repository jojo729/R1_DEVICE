package com.unisound.ant.device.mqtt;

public interface ChannelListener {
    void onChannelClose();

    void onChannelConnected();

    void onChannelDisConnected();

    void onReceivedMsg(int i, String str);

    void onSendDataResult(int i, String str);
}

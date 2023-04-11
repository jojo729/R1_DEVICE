package com.unisound.ant.device.mqtt;

import android.content.Context;
import com.unisound.ant.device.mqtt.bean.ChannelParams;

public abstract class BaseTransportChannel<T, R> {
    protected ChannelListener listener;
    protected ChannelParams mChannelParams;

    public abstract void closeChannel();

    public abstract void createChannel(Context context, OnMQTTStatusChangeListener onMQTTStatusChangeListener);

    public abstract void createChannel(Context context, ChannelParams channelParams, OnMQTTStatusChangeListener onMQTTStatusChangeListener);

    public abstract void receivedData(R r);

    public abstract void sendData(T t);

    public void setChannelListener(ChannelListener listener2) {
        this.listener = listener2;
    }
}

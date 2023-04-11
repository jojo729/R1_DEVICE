package com.unisound.ant.device.mqtt;

import android.content.Context;
import com.unisound.ant.device.mqtt.bean.ChannelParams;

public class AliveTransportChannel {

    /* access modifiers changed from: private */
    public static class ChannelFactory {
        public static BaseTransportChannel channel = MqttTransportChannel.getMqttTransportChannel();
        public static AliveTransportChannel instance = new AliveTransportChannel();

        private ChannelFactory() {
        }
    }

    public static AliveTransportChannel getChannelInstance() {
        return ChannelFactory.instance;
    }

    private BaseTransportChannel getChannel() {
        return ChannelFactory.channel;
    }

    public void openChannel(Context context, ChannelParams channelParams, OnMQTTStatusChangeListener phicommMQTTStatausChange) {
        getChannel().createChannel(context, channelParams, phicommMQTTStatausChange);
    }

    public void setChannelListener(ChannelListener listener) {
        getChannel().setChannelListener(listener);
    }

    public void sendData(String message) {
//        getChannel().sendData(message);
    }

    public void closeChannel() {
        getChannel().closeChannel();
    }
}

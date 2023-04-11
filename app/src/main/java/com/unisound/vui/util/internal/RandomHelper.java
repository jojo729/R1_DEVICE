package com.unisound.vui.util.internal;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class RandomHelper {
    private RandomHelper() {
    }

    public static String getRandom(int resId, Context context) {
        return getRandom(context.getResources().getStringArray(resId));
    }

    public static String getRandom(String input) {
        return getRandom(input.split(MqttTopic.MULTI_LEVEL_WILDCARD));
    }

    public static String getRandom(String[] inputs) {
        return inputs[(int) (Math.random() * ((double) inputs.length))];
    }

    public static String getRandomID() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + new Random().nextInt(1000);
    }
}

package com.unisound.ant.device.mqtt.bean;

public final class ParamConfig {
    public static final String DEVICE_TYPE_GENERAL = "generalDevice";
    public static final String DEVICE_TYPE_PHONE = "phone";
    private static boolean isOutNet;
    private static LconRequest<LconInfo> lconRequest;

    public static LconRequest<LconInfo> getLconRequest() {
        return lconRequest;
    }

    public static void setLconRequest(LconRequest<LconInfo> info) {
        lconRequest = info;
    }

    public static boolean isOutNet() {
        return isOutNet;
    }

    public static void setIsOutNet(boolean isOutNet2) {
        isOutNet = isOutNet2;
    }
}

package com.unisound.vui.transport.config;

public final class RuntimeConfig {
    private static boolean isConnectedWifi = false;
    private static boolean isOnceConnectSuc = false;
    private float fixAsrResBenchmark = -6.25f;
    private boolean isUseOutRecordSource = false;
    private float wakUpBenchmark = -3.0f;

    public static boolean isConnectedWifi() {
        return isConnectedWifi;
    }

    public static boolean isOnceConnectSuc() {
        return isOnceConnectSuc;
    }

    public static void setConnectedWifi(boolean z) {
        isConnectedWifi = z;
    }

    public static void setOnceConnectSuc(boolean z) {
        isOnceConnectSuc = z;
    }

    public float getFixAsrResBenchmark() {
        return this.fixAsrResBenchmark;
    }

    public float getWakUpBenchmark() {
        return this.wakUpBenchmark;
    }

    public boolean isUseOutRecordSource() {
        return this.isUseOutRecordSource;
    }

    public void setFixAsrResBenchmark(float f) {
        this.fixAsrResBenchmark = f;
    }

    public void setIsUseOutRecordSource(boolean z) {
        this.isUseOutRecordSource = z;
    }

    public void setWakUpBenchmark(float f) {
        this.wakUpBenchmark = f;
    }
}

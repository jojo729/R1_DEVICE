package com.unisound.jni;

public class Uni4micHalJNI {

    /* renamed from: a  reason: collision with root package name */
    private static Uni4micHalJNI a;
    private boolean b = false;

    static {
        System.loadLibrary("Uni4micHalJNI");
    }

    private Uni4micHalJNI() {
    }

    public static Uni4micHalJNI getInstance() {
        if (a == null) {
            a = new Uni4micHalJNI();
        }
        return a;
    }

    private native int initHal(int i);

    private native int releaseHal();

    public native int close4MicAlgorithm(int i);

    public native int closeAudioIn(long j);

    public native String get4MicBoardVersion();

    public native int get4MicDoaResult();

    public native int get4MicOneShotReady();

    public int init(int i) {
        if (this.b) {
            return 0;
        }
        int initHal = a.initHal(i);
        if (initHal != 0) {
            return initHal;
        }
        this.b = true;
        return initHal;
    }

    public native long openAudioIn(int i);

    public native int readData(long j, byte[] bArr, int i);

    public int release() {
        if (!this.b) {
            return -1;
        }
        int releaseHal = a.releaseHal();
        if (releaseHal != 0) {
            return releaseHal;
        }
        this.b = false;
        return releaseHal;
    }

    public native int set4MicDebugMode(int i);

    public native int set4MicDelayTime(int i);

    public native int set4MicOneShotReady(int i);

    public native int set4MicOneShotStartLen(int i);

    public native int set4MicUtteranceTimeLen(int i);

    public native int set4MicWakeUpStatus(int i);

    public native int set4MicWakeupStartLen(int i);

    public native int startRecorder(long j);

    public native int stopRecorder(long j);
}

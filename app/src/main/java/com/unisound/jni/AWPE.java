package com.unisound.jni;

public class AWPE {

    /* renamed from: a  reason: collision with root package name */
    int f190a = init(16000);

    static {
        System.loadLibrary("awpe");
    }

    private native int process(byte[] bArr, byte[] bArr2);

    private native byte[] process2(byte[] bArr);

    public native int init(int i);

    public byte[] process(byte[] bArr) {
        return process2(bArr);
    }

    public native void release();

    public native void reset();
}

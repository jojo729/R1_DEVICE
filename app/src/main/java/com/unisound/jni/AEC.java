package com.unisound.jni;

public class AEC {

    /* renamed from: a  reason: collision with root package name */
    int f189a;

    static {
        System.loadLibrary("aec");
    }

    public AEC(int i, int i2) {
        this.f189a = init(i, i2);
    }

    private native int init(int i, int i2);

    public native byte[] getlast();

    public native byte[] process(byte[] bArr, byte[] bArr2);

    public native int process2(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3);

    public native void release();

    public native void reset(float f, float f2);

    public native int setOptionInt(int i, int i2);

    public byte[] write(byte[] bArr, byte[] bArr2) {
        return process(bArr, bArr2);
    }
}

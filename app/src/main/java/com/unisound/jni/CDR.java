package com.unisound.jni;

public class CDR {

    /* renamed from: a  reason: collision with root package name */
    int f191a = init();

    static {
        System.loadLibrary("cdr");
    }

    public native int init();

    public native byte[] process(byte[] bArr);

    public void release() {
        release(this.f191a);
    }

    public native void release(int i);

    public byte[] write(byte[] bArr) {
        return process(bArr);
    }
}

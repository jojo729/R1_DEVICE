package cn.yunzhisheng.tts;

import com.unisound.client.ErrorCode;

public class JniClient {

    /* renamed from: a  reason: collision with root package name */
    public static final int f33a = 0;
    public static final int b = 1;
    public static int c = 0;
    public static int d = 0;
    public static final int e = 0;
    public static final int f = 8;
    public static final int g = 14;
    public static final int h = 15;
    public static final int i = 22;
    public static final int j = 104;
    public static final int k = 203;
    public static final int l = 204;
    public a m = new a(this);
    private long n = 0;
    private int[] o = new int[2];
    private int[] p = new int[2];
    private int[] q = new int[2];

    static {
        System.loadLibrary("uscasr");
    }

    public static String e() {
        return nativeGetVersion();
    }

    public static native String nativeGetVersion();

    public int a(int i2, String str) {
        return a() ? nativeSetOption(this.n, i2, str) : ErrorCode.NO_INIT_ERROR;
    }

    public int a(String str, String str2) {
        return a() ? nativeStart(this.n, str, str2) : ErrorCode.NO_INIT_ERROR;
    }

    public String a(int i2) {
        return a() ? nativeGetOption(this.n, i2) : "";
    }

    public boolean a() {
        return this.n != 0;
    }

    public boolean a(String str) {
        b();
        this.n = nativeCreate(str);
        return a();
    }

    public boolean a(String str, String str2, int i2) {
        b();
        this.n = nativeCreateExt(str, str2, i2 + "");
        return a();
    }

    public int b() {
        if (!a()) {
            return 0;
        }
        int nativeRelease = nativeRelease(this.n);
        this.n = 0;
        return nativeRelease;
    }

    public int b(String str) {
        return a() ? nativeTextPut(this.n, str) : ErrorCode.NO_INIT_ERROR;
    }

    public int c() {
        return a() ? nativeStop(this.n) : ErrorCode.NO_INIT_ERROR;
    }

    public byte[] d() {
        if (a()) {
            byte[] nativeGetResult = nativeGetResult(this.n, this.o, this.p, this.q);
            this.m.f34a = this.o[0];
            this.m.b = this.p[0];
            this.m.c = this.q[0];
            if (this.m.c == 0) {
                return nativeGetResult;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        b();
        super.finalize();
    }

    public native long nativeCreate(String str);

    public native long nativeCreateExt(String str, String str2, String str3);

    public native String nativeGetOption(long j2, int i2);

    public native byte[] nativeGetResult(long j2, int[] iArr, int[] iArr2, int[] iArr3);

    public native int nativeRelease(long j2);

    public native int nativeSetOption(long j2, int i2, String str);

    public native int nativeStart(long j2, String str, String str2);

    public native int nativeStop(long j2);

    public native int nativeTextPut(long j2, String str);
}

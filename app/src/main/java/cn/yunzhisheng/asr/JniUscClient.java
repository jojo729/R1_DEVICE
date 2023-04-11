package cn.yunzhisheng.asr;

import com.unisound.common.y;
import com.unisound.sdk.c;

public class JniUscClient {
    public static final int A = 7;
    public static final int B = 8;
    public static final int C = 9;
    public static final int D = 10;
    public static final int E = 11;
    public static final int F = 12;
    public static final int G = 13;
    public static final int H = 14;
    public static final int I = 15;
    public static final int J = 16;
    public static final int K = 17;
    public static final int L = 18;
    public static final int M = 19;
    public static final int N = 31;
    public static final int O = 21;
    public static final int P = 22;
    public static final int Q = 25;
    public static final int R = 26;
    public static final int S = 0;
    public static final int T = 1;
    public static final int U = 20;
    public static final int V = 21;
    public static final int W = 22;
    public static final int X = 24;
    public static final int Y = 32;
    public static final int Z = 33;

    /* renamed from: a  reason: collision with root package name */
    public static final int f27a = 0;
    public static final String aA = "no";
    public static final String aB = "variable";
    public static final String aC = "formal";
    public static final int aa = 34;
    public static final int ab = 35;
    public static final int ac = 151;
    public static final int ad = 23;
    public static final int ae = 26;
    public static final int af = 27;
    public static final int ag = 28;
    public static final int ah = 34;
    public static final int ai = 201;
    public static final int aj = 206;
    public static final int ak = 204;
    public static final int al = 207;
    public static final int am = 0;
    public static final int an = 1;
    public static final int ao = 0;
    public static final int ap = 1;
    public static final int aq = 2;
    public static final int ar = 3;
    public static final int as = 4;
    public static final int at = 1015;
    public static final int au = 320;
    public static final int av = 321;
    public static final int aw = 60;
    public static final int ax = 61;
    public static final int ay = 62;
    public static final String az = "null";
    public static final int b = 0;
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 5;
    public static final int g = 1002;
    public static final int h = 1015;
    public static final int i = 201;
    public static final int j = 1020;
    public static final int k = 1019;
    public static int l = 0;
    public static int m = 0;
    public static final String n = "opus";
    public static final String o = "opus-nb";
    public static final String p = "req_audio_url";
    public static final String q = "get_variable";
    public static final String r = "open";
    public static final String s = "close";
    public static final int t = 0;
    public static final int u = 1;
    public static final int v = 2;
    public static final int w = 3;
    public static final int x = 4;
    public static final int y = 5;
    public static final int z = 6;
    private long aD = 0;

    public static String b(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "NETWORK_TYPE_NONE" : "NETWORK_TYPE_MOBILE" : "NETWORK_TYPE_2G" : "NETWORK_TYPE_3G" : "NETWORK_TYPE_WIFI" : "NETWORK_TYPE_NONE";
    }

    private native int cancel(long j2);

    private native long createNative(String str, int i2);

    private native void destroyNative(long j2);

    private native int getLastErrno(long j2);

    private native String getOptionValue(long j2, int i2);

    private native String getResult(long j2);

    private native int login(long j2);

    private native int recognize(long j2, byte[] bArr, int i2);

    private native int setOptionInt(long j2, int i2, int i3);

    private native int setOptionString(long j2, int i2, String str);

    private native int start(long j2);

    private native int stop(long j2);

    public int a() {
        long j2 = this.aD;
        if (j2 == 0) {
            return -1;
        }
        int start = start(j2);
        a(start);
        return start;
    }

    public int a(int i2, int i3) {
        if (this.aD == 0) {
            return -1;
        }
         com.unisound.common.y.d("key = " + i2, "value = " + i3);
        return setOptionInt(this.aD, i2, i3);
    }

    public int a(int i2, String str) {
        if (this.aD != 0) {
            if (str == null) {
                 com.unisound.common.y.a("JniUscClient setOptionString error : s is null!");
            } else {
                 com.unisound.common.y.d("key = " + i2, "value = " + str);
                return setOptionString(this.aD, i2, str);
            }
        }
        return -1;
    }

    public int a(String str) {
        if (!str.equals("pcm")) {
            return -1;
        }
        a(24, 0);
        a(101, 4800);
        a(16, str);
        return 0;
    }

    public int a(boolean z2) {
        if (z2) {
            return a(35, q);
        }
        return 0;
    }

    public int a(byte[] bArr, int i2) {
        long j2 = this.aD;
        if (j2 == 0) {
            return -1;
        }
        int recognize = recognize(j2, bArr, i2);
        a(recognize);
        return recognize;
    }

    public long a(String str, int i2) {
        if (this.aD == 0) {
            this.aD = createNative(str, i2);
        }
        return this.aD;
    }

    public void a(int i2) {
        l = i2;
        m = i2 < 0 ? f() : 0;
    }

    public int b() {
        long j2 = this.aD;
        if (j2 == 0) {
            return -1;
        }
        int stop = stop(j2);
        a(stop);
        return stop;
    }

    public String c() {
        long j2 = this.aD;
        return j2 != 0 ? getResult(j2) : "";
    }

    public String c(int i2) {
        long j2 = this.aD;
        return j2 != 0 ? getOptionValue(j2, i2) : "";
    }

    public int d() {
        long j2 = this.aD;
        if (j2 != 0) {
            return cancel(j2);
        }
        return 0;
    }

    public void e() {
        long j2 = this.aD;
        if (j2 != 0) {
            destroyNative(j2);
            this.aD = 0;
        }
    }

    public int f() {
        long j2 = this.aD;
        if (j2 != 0) {
            return getLastErrno(j2);
        }
        return 0;
    }

    public int g() {
        long j2 = this.aD;
        if (j2 != 0) {
            return login(j2);
        }
        return -1;
    }
}

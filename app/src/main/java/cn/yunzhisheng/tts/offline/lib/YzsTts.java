package cn.yunzhisheng.tts.offline.lib;

import com.unisound.client.ErrorCode;
import com.unisound.common.y;

public class YzsTts {

    /* renamed from: a  reason: collision with root package name */
    private static YzsTts f35a = null;
    private static volatile boolean d = false;
    private long b = 0;
    private long c = 0;
    private Object e = new Object();

    static {
        System.loadLibrary("yzstts");
    }

    public static YzsTts b() {
        if (f35a == null) {
            f35a = new YzsTts();
        }
        return f35a;
    }

    private native void cancel(long j);

    private native int changeSpeaker(long j, String str);

    private native long create(long j);

    private native long createbase(String str, String str2, String str3, String str4);

    private native String getCheckInfo(Object obj);

    private native String getOption(long j, int i);

    private native void release(long j);

    private native void releasebase(long j);

    private native int setOption(long j, int i, String str);

    public int a(long j, byte[] bArr) {
        int receiveSamples;
        if (y.l) {
            y.d("YzsTts process receiveData start");
        }
        if (j != 0) {
            synchronized (this.e) {
                if (y.l) {
                    y.d("YzsTts receiveSamples start");
                }
                receiveSamples = receiveSamples(j, bArr);
                if (y.l) {
                    y.d("YzsTts receiveSamples end handle = ", Long.valueOf(j), "; audioLength = ", Integer.valueOf(bArr.length), "; first byte", Byte.valueOf(bArr[0]));
                }
            }
            return receiveSamples;
        } else if (!y.l) {
            return 0;
        } else {
            y.d("YzsTts process receiveData end");
            return 0;
        }
    }

    public int a(String str) {
        if (!d()) {
            return ErrorCode.TTS_ERROR_OFFLINE_ENGINE_NOT_INIT;
        }
        if (d) {
            return ErrorCode.TTS_ERROR_OFFLINE_ENGINE_IS_PROCESSING;
        }
        synchronized (this.e) {
            if (changeSpeaker(this.c, str) == -1) {
                return ErrorCode.TTS_ERROR_OFFLINE_CHANGE_SPEAKER_FAIL;
            }
            return 114;
        }
    }

    public long a() {
        return this.c;
    }

    public void a(float f) {
        if (d()) {
            if (setOption(this.c, 0, String.format("%1$.1f", Float.valueOf(f))) != 0) {
                y.a("YzsTts setLog : error");
            }
        }
    }

    public void a(int i) {
        if (d() && setOption(this.c, 6, String.valueOf(i)) != 0) {
            y.a("YzsTts setFrontSilence : error");
        }
    }

    public void a(Boolean bool) {
        d = bool.booleanValue();
    }

    public boolean a(String str, String str2, String str3, String str4) {
        c();
        long createbase = createbase(str, str2, str3, str4);
        this.b = createbase;
        if (createbase != 0) {
            synchronized (this.e) {
                long create = create(this.b);
                this.c = create;
                if (create != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public int b(long j, byte[] bArr) {
        int receiveSamples2;
        y.d("YzsTts process receiveData2 start");
        if (j != 0) {
            synchronized (this.e) {
                y.d("YzsTts receiveSamples start");
                receiveSamples2 = receiveSamples2(j, bArr);
                y.d("YzsTts receiveSamples end handle = " + j + "; audioLength = " + bArr.length + "; first byte" + ((int) bArr[0]));
            }
            return receiveSamples2;
        }
        y.d("YzsTts process receiveData2 end");
        return 0;
    }

    public void b(float f) {
        if (d()) {
            if (setOption(this.c, 1, String.format("%1$.1f", Float.valueOf(f))) != 0) {
                y.a("YzsTts setVoiceSpeed : error");
            }
        }
    }

    public void b(int i) {
        if (d() && setOption(this.c, 7, String.valueOf(i)) != 0) {
            y.a("YzsTts setBackSilence : error");
        }
    }

    public void b(Boolean bool) {
        if (d() && setOption(this.c, 5, String.valueOf(bool)) != 0) {
            y.a("YzsTts setIsReadEnglishInPinyin : error");
        }
    }

    public void c() {
        if (d()) {
            synchronized (this.e) {
                release(this.c);
                this.c = 0;
            }
            releasebase(this.b);
            this.b = 0;
            d = false;
        }
    }

    public void c(float f) {
        if (d()) {
            if (setOption(this.c, 3, String.format("%1$.1f", Float.valueOf(f))) != 0) {
                y.a("YzsTts setVoiceVolume : error");
            }
        }
    }

    public void c(int i) {
        if (d()) {
            setOption(this.c, 5, String.valueOf(i));
        }
    }

    public void d(float f) {
        if (d()) {
            if (setOption(this.c, 2, String.format("%1$.1f", Float.valueOf(f))) != 0) {
                y.a("YzsTts setVoicePitch : error");
            }
        }
    }

    public boolean d() {
        return this.c != 0;
    }

    public boolean d(int i) {
        return d() && setOption(this.c, 6, String.valueOf(i)) == 0;
    }

    public void e() {
        if (y.l) {
            y.d("YzsTts process cancel start");
        }
        if (d() && d) {
            synchronized (this.e) {
                if (y.l) {
                    y.d("YzsTts cancel start");
                }
                cancel(this.c);
                if (y.l) {
                    y.d("YzsTts cancel end");
                }
            }
            d = false;
        }
        if (y.l) {
            y.d("YzsTts process cancel end");
        }
    }

    public void e(int i) {
        if (d()) {
            setOption(this.c, 10, String.valueOf(i));
        }
    }

    public String f() {
        String checkInfo = getCheckInfo(new Object());
        return checkInfo == null ? "" : checkInfo;
    }

    public void f(int i) {
        if (d()) {
            setOption(this.c, 11, String.valueOf(i));
        }
    }

    public void g(int i) {
        if (d()) {
            setOption(this.c, 12, String.valueOf(i));
        }
    }

    public native int receiveSamples(long j, byte[] bArr);

    public native int receiveSamples2(long j, byte[] bArr);

    public native int setText(long j, String str);
}

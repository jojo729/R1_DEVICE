package cn.yunzhisheng.asr;

import com.unisound.common.j;
import com.unisound.common.y;
import com.unisound.sdk.cr;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class VAD {
    public static final int a = -1;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static int f = 0;
    private static final int l = 0;
    private static final int m = 1;
    private static final int n = -1001;
    private ArrayList A = new ArrayList();
    private boolean B = false;
    private double C;
    public List g = new LinkedList();
    public boolean h = false;
    protected long i = 0L;
    public boolean j = true;
    public boolean k = false;
    private ByteArrayOutputStream o = new ByteArrayOutputStream(20480);
    private a p;
    private cr q;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private byte[] v = new byte[]{99};
    private boolean w = false;
    private int x = 0;
    private ArrayList y = new ArrayList();
    private boolean z = false;

    public VAD(a var1, cr var2) {
        this.p = var1;
        this.q = var2;
        this.i = this.create();
        if (this.i == 0L) {
            com.unisound.common.y.a("jni VAD create fail!");
        } else {
            if (this.p.c.a()) {
                this.p.n(true);
                this.p.S();
                if (this.p.c != null) {
                    this.p.c.a(String.valueOf(System.currentTimeMillis()));
                }
            }

            if (this.p.av()) {
                this.p.af();
            }

            this.p.l(false);
            this.a(this.p.n());
            this.init(this.i);
        }

    }

    private double a(double var1) {
        return var1 / 32.0D;
    }

    private int a(byte[] var1, int var2, byte[] var3, int var4) {
        var4 = 0;

        int var8;
        byte var9;
        for(int var5 = 0; var5 < var2 - 1; var3[var8] = (byte)var9) {
            int var6 = var5 + 1;
            byte var7 = var1[var5];
            var5 = var6 + 1;
            var9 = var1[var6];
            var8 = var4 + 1;
            var3[var4] = (byte)var7;
            var4 = var8 + 1;
            var3[var8] = (byte)var9;
            var8 = var4 + 1;
            var3[var4] = (byte)var7;
            var4 = var8 + 1;
        }

        return var4;
    }

    private String b(int var1, String var2) {
        JSONObject var3 = new JSONObject();

        try {
            var3.put("timeout", var1);
            var3.put("afterTimeoutVoice", var2);
        } catch (JSONException var4) {
            var4.printStackTrace();
        }

        return var3.toString();
    }

    private void b(int var1) {
        cr var2 = this.q;
        if (var2 != null) {
            var2.b(var1);
        }

    }

    private void b(boolean var1) {
        if (var1 != this.s && this.a()) {
            this.s = var1;
            if (this.s) {
                this.setTime(this.i, -1001, 1);
            } else {
                this.setTime(this.i, -1001, 0);
            }
        }

    }

    private void c(int var1) {
        this.a(var1);
        if (this.p.b() && this.p.z()) {
            com.unisound.common.j.a(false, this.p.c());
        }

        cr var2 = this.q;
        if (var2 != null) {
            var2.a(this);
            this.p.v(true);
        }

        if (var1 != 2) {
            com.unisound.common.y.c(new Object[]{"VAD >>", "TimeOut"});
        }

    }

    private void d(int var1) {
        this.C += (double)var1;
    }

    private void d(byte[] var1) throws Throwable {
        byte var2 = 0;
        synchronized(this){}

        Throwable var10000;
        label198: {
            boolean var10001;
            int var3;
            try {
                this.g.add(var1);
                var3 = this.g.size() - 1;
            } catch (Throwable var16) {
                var10000 = var16;
                var10001 = false;
                break label198;
            }

            int var4 = 0;

            while(true) {
                if (var3 < 0) {
                    var3 = 0;
                    var4 = var2;
                    break;
                }

                label185: {
                    try {
                        var4 += ((byte[])this.g.get(var3)).length;
                        if (var4 >= this.p.ad) {
                            break label185;
                        }
                    } catch (Throwable var15) {
                        var10000 = var15;
                        var10001 = false;
                        break label198;
                    }

                    --var3;
                    continue;
                }

                var4 = var2;
                break;
            }

            while(true) {
                if (var4 >= var3) {
                    return;
                }

                try {
                    var1 = (byte[])this.g.remove(0);
                    this.a(false, var1, 0, var1.length);
                } catch (Throwable var14) {
                    var10000 = var14;
                    var10001 = false;
                    break;
                }

                ++var4;
            }
        }

        Throwable var17 = var10000;
        throw var17;
    }

    private void f() {
        cr var1 = this.q;
        if (var1 != null) {
            var1.n();
        }

    }

    private void g() {
        cr var1 = this.q;
        if (var1 != null) {
            var1.o();
        }

    }

    private void h() {
        this.y.clear();
        this.z = false;
    }

    private void i() {
        this.A.clear();
        this.B = false;
    }

    private void j() {
        this.C = 0.0D;
    }

    private double k() {
        return this.C;
    }

    public int a(int var1, String var2) {
        byte var3 = 0;
        int var4;
        if (this.i == 0L) {
            var4 = -1;
        } else {
            var4 = var3;
            if (var2 != null) {
                var4 = var3;
                if (var2.length() != 0) {
                    var4 = this.nativeSetOption(this.i, var1, var2);
                }
            }
        }

        return var4;
    }

    public int a(b var1) {
        int var2;
        if (!var1.c()) {
            var2 = 0;
        } else {
            var2 = this.a(var1.b, var1.toString());
        }

        return var2;
    }

    public int a(byte[] var1, int var2) {
        if (!this.a()) {
            var2 = 0;
        } else {
            var2 = this.checkPitchOffset(this.i, var1, var2);
        }

        return var2;
    }

    public int a(byte[] var1, int var2, int var3) throws Throwable {
        synchronized(this){}

        Throwable var10000;
        label23098: {
            label23099: {
                boolean var10001;
                label23040: {
                    try {
                        if (var1.length != 1) {
                            break label23040;
                        }
                    } catch (Throwable var2079) {
                        var10000 = var2079;
                        var10001 = false;
                        break label23098;
                    }

                    if (var1[0] == 99) {
                        break label23099;
                    }

                    byte var2081 = var1[0];
                    if (var2081 == 100) {
                        break label23099;
                    }
                }

                try {
                    if (this.p.b()) {
                        com.unisound.common.j.a(var1, this.p.c());
                    }
                } catch (Throwable var2078) {
                    var10000 = var2078;
                    var10001 = false;
                    break label23098;
                }

                try {
                    this.p.c.b(var1);
                    if (com.unisound.common.y.l) {
                        com.unisound.common.y.d(new Object[]{"VAD::write size =", var1.length, " first byte ", var1[0]});
                    }
                } catch (Throwable var2077) {
                    var10000 = var2077;
                    var10001 = false;
                    break label23098;
                }

                label23042: {
                    try {
                        if (var1.length != 1) {
                            break label23042;
                        }
                    } catch (Throwable var2076) {
                        var10000 = var2076;
                        var10001 = false;
                        break label23098;
                    }

                    if (var1[0] == 100 || var1[0] == 99) {
                        try {
                            this.a(true, var1, 0, var3);
                        } catch (Throwable var2035) {
                            var10000 = var2035;
                            var10001 = false;
                            break label23098;
                        }

                        var2 = 0;
                        return var2;
                    }
                }

                if (var3 <= 0) {
                    var2 = 0;
                    return var2;
                }

                byte[][] var4;
                label23043: {
                    label23044: {
                        label23002: {
                            label23077: {
                                try {
                                    if (this.p.c != null && this.p.c.a()) {
                                        if (!this.p.c.s()) {
                                            break label23044;
                                        }

                                        if (!this.p.c.t()) {
                                            break label23002;
                                        }

                                        var2 = var1.length;
                                        var4 = new byte[2][var2];
                                        break label23077;
                                    }
                                } catch (Throwable var2075) {
                                    var10000 = var2075;
                                    var10001 = false;
                                    break label23098;
                                }

                                try {
                                    if (this.p.av()) {
                                        var2 = var1.length / 2;
                                        var4 = new byte[2][var2];
                                        var4 = this.p.c.a(var1);
                                        break label23043;
                                    }
                                } catch (Throwable var2074) {
                                    var10000 = var2074;
                                    var10001 = false;
                                    break label23098;
                                }

                                try {
                                    var2 = var1.length;
                                    var4 = new byte[2][var2];
                                } catch (Throwable var2071) {
                                    var10000 = var2071;
                                    var10001 = false;
                                    break label23098;
                                }

                                var4[1] = var1;
                                var4[0] = var1;
                                break label23043;
                            }

                            var4[1] = var1;
                            var4[0] = var1;
                            break label23043;
                        }

                        try {
                            var2 = var1.length / 2;
                            var4 = new byte[2][var2];
                            var4 = this.p.c.a(var1);
                            break label23043;
                        } catch (Throwable var2073) {
                            var10000 = var2073;
                            var10001 = false;
                            break label23098;
                        }
                    }

                    try {
                        var2 = var1.length / 2;
                        var4 = new byte[2][var2];
                        var4 = this.p.c.a(var1);
                    } catch (Throwable var2072) {
                        var10000 = var2072;
                        var10001 = false;
                        break label23098;
                    }
                }

                byte[] var5;
                byte[] var2082;
                label23047: {
                    label23048: {
                        label23078: {
                            try {
                                if (this.p.ag) {
                                    break label23078;
                                }

                                if (this.p.c.i() == 0) {
                                    if (!this.p.c.o()) {
                                        break label23048;
                                    }

                                    var5 = Arrays.copyOfRange(var4[0], 0, var4[0].length);
                                    var2082 = Arrays.copyOfRange(var4[0], 0, var4[0].length);
                                    var2 = var5.length;
                                    break label23047;
                                }
                            } catch (Throwable var2070) {
                                var10000 = var2070;
                                var10001 = false;
                                break label23098;
                            }

                            try {
                                if (this.p.c.o()) {
                                    var5 = Arrays.copyOfRange(var4[1], 0, var4[1].length);
                                    var2082 = Arrays.copyOfRange(var4[1], 0, var4[1].length);
                                    var2 = var5.length;
                                    break label23047;
                                }
                            } catch (Throwable var2069) {
                                var10000 = var2069;
                                var10001 = false;
                                break label23098;
                            }

                            try {
                                var5 = Arrays.copyOfRange(var4[0], 0, var4[0].length);
                                var2082 = Arrays.copyOfRange(var4[1], 0, var4[1].length);
                                var2 = var5.length;
                                break label23047;
                            } catch (Throwable var2065) {
                                var10000 = var2065;
                                var10001 = false;
                                break label23098;
                            }
                        }

                        byte[] var6;
                        label22950: {
                            label23051: {
                                try {
                                    if (this.p.c.i() != 0) {
                                        break label22950;
                                    }

                                    if (this.p.c.o()) {
                                        var3 = var4[1].length * 2;
                                        var5 = new byte[var3];
                                        var2 = this.a(var4[0], var4[0].length, var5, var3);
                                        var6 = new byte[var3];
                                        this.a(var4[0], var4[0].length, var6, var3);
                                        break label23051;
                                    }
                                } catch (Throwable var2068) {
                                    var10000 = var2068;
                                    var10001 = false;
                                    break label23098;
                                }

                                try {
                                    var3 = var4[1].length * 2;
                                    var5 = new byte[var3];
                                    var2 = this.a(var4[1], var4[1].length, var5, var3);
                                    var6 = new byte[var3];
                                    this.a(var4[0], var4[0].length, var6, var3);
                                } catch (Throwable var2064) {
                                    var10000 = var2064;
                                    var10001 = false;
                                    break label23098;
                                }

                                var2082 = var6;
                                break label23047;
                            }

                            var2082 = var6;
                            break label23047;
                        }

                        label23053: {
                            try {
                                if (this.p.c.o()) {
                                    var3 = var4[0].length * 2;
                                    var5 = new byte[var3];
                                    var2 = this.a(var4[1], var4[1].length, var5, var3);
                                    var6 = new byte[var3];
                                    this.a(var4[1], var4[1].length, var6, var3);
                                    break label23053;
                                }
                            } catch (Throwable var2067) {
                                var10000 = var2067;
                                var10001 = false;
                                break label23098;
                            }

                            try {
                                var3 = var4[0].length * 2;
                                var5 = new byte[var3];
                                var2 = this.a(var4[0], var4[0].length, var5, var3);
                                var6 = new byte[var3];
                                this.a(var4[1], var4[1].length, var6, var3);
                            } catch (Throwable var2063) {
                                var10000 = var2063;
                                var10001 = false;
                                break label23098;
                            }

                            var2082 = var6;
                            break label23047;
                        }

                        var2082 = var6;
                        break label23047;
                    }

                    try {
                        var5 = Arrays.copyOfRange(var4[1], 0, var4[1].length);
                        var2082 = Arrays.copyOfRange(var4[0], 0, var4[0].length);
                        var2 = var5.length;
                    } catch (Throwable var2066) {
                        var10000 = var2066;
                        var10001 = false;
                        break label23098;
                    }
                }

                try {
                    if (!this.p.D()) {
                        this.p.e((long)var2082.length);
                    }
                } catch (Throwable var2062) {
                    var10000 = var2062;
                    var10001 = false;
                    break label23098;
                }

                try {
                    this.p.c.c(var2082);
                    this.p.c.d(var5);
                    if (this.p.A() && this.p.B()) {
                        this.x += var1.length;
                        if ((long)this.x >= this.p.aj()) {
                            this.w = true;
                        }
                    }
                } catch (Throwable var2061) {
                    var10000 = var2061;
                    var10001 = false;
                    break label23098;
                }

                label23101: {
                    try {
                        if (this.j && (!this.p.A() || this.w || !this.p.B())) {
                            break label23101;
                        }
                    } catch (Throwable var2060) {
                        var10000 = var2060;
                        var10001 = false;
                        break label23098;
                    }

                    try {
                        this.a(true, var2082, 0, var2082.length);
                        this.b(this.c(var2082, var2082.length));
                    } catch (Throwable var2059) {
                        var10000 = var2059;
                        var10001 = false;
                        break label23098;
                    }

                    var2 = 0;
                    return var2;
                }

                label22899: {
                    label23076: {
                        try {
                            if (this.p.m() && !this.r) {
                                this.a(var2082);
                                this.b(this.c());
                                break label23076;
                            }
                        } catch (Throwable var2058) {
                            var10000 = var2058;
                            var10001 = false;
                            break label23098;
                        }

                        int var7;
                        try {
                            var7 = this.b(var5, var2);
                        } catch (Throwable var2047) {
                            var10000 = var2047;
                            var10001 = false;
                            break label23098;
                        }

                        if (var7 != 0) {
                            if (var7 == 1) {
                                label23059: {
                                    label23060: {
                                        try {
                                            if (!this.p.A()) {
                                                break label23060;
                                            }

                                            if (!this.u) {
                                                this.z = true;
                                                break label23059;
                                            }
                                        } catch (Throwable var2054) {
                                            var10000 = var2054;
                                            var10001 = false;
                                            break label23098;
                                        }

                                        try {
                                            this.B = true;
                                            break label23059;
                                        } catch (Throwable var2046) {
                                            var10000 = var2046;
                                            var10001 = false;
                                            break label23098;
                                        }
                                    }

                                    try {
                                        this.B = true;
                                    } catch (Throwable var2045) {
                                        var10000 = var2045;
                                        var10001 = false;
                                        break label23098;
                                    }
                                }

                                try {
                                    if (this.p.b() && this.p.z()) {
                                        com.unisound.common.j.b(false, this.p.c());
                                    }
                                } catch (Throwable var2053) {
                                    var10000 = var2053;
                                    var10001 = false;
                                    break label23098;
                                }

                                try {
                                    com.unisound.common.y.c(new Object[]{"VAD >>", "ASR_VAD_BACK_END ", "param = ", this.u});
                                    this.g();
                                } catch (Throwable var2044) {
                                    var10000 = var2044;
                                    var10001 = false;
                                    break label23098;
                                }
                            } else if (var7 == 2) {
                                try {
                                    this.p.c(this.b(0, ""));
                                    this.c(2);
                                    com.unisound.common.y.c(new Object[]{"VAD >>", "ASR_VAD_MAX_SIL1"});
                                } catch (Throwable var2043) {
                                    var10000 = var2043;
                                    var10001 = false;
                                    break label23098;
                                }
                            } else if (var7 == 3) {
                                label23062: {
                                    label23063: {
                                        try {
                                            if (this.p.A()) {
                                                if (this.p.B()) {
                                                    this.u = true;
                                                }
                                                break label23063;
                                            }
                                        } catch (Throwable var2057) {
                                            var10000 = var2057;
                                            var10001 = false;
                                            break label23098;
                                        }

                                        try {
                                            this.i();
                                            break label23062;
                                        } catch (Throwable var2041) {
                                            var10000 = var2041;
                                            var10001 = false;
                                            break label23098;
                                        }
                                    }

                                    try {
                                        if (!this.u) {
                                            this.h();
                                            break label23062;
                                        }
                                    } catch (Throwable var2056) {
                                        var10000 = var2056;
                                        var10001 = false;
                                        break label23098;
                                    }

                                    try {
                                        this.i();
                                    } catch (Throwable var2042) {
                                        var10000 = var2042;
                                        var10001 = false;
                                        break label23098;
                                    }
                                }

                                try {
                                    if (this.p.b() && this.p.z()) {
                                        com.unisound.common.j.a(true, this.p.c());
                                    }
                                } catch (Throwable var2055) {
                                    var10000 = var2055;
                                    var10001 = false;
                                    break label23098;
                                }

                                try {
                                    this.h = true;
                                    com.unisound.common.y.c(new Object[]{"VAD >>", "ASR_VAD_FRONT_END ", "param = ", this.u});
                                    this.f();
                                } catch (Throwable var2040) {
                                    var10000 = var2040;
                                    var10001 = false;
                                    break label23098;
                                }
                            }
                        }

                        label23069: {
                            label22841: {
                                try {
                                    if (this.h || !this.p.Y || !this.p.at()) {
                                        break label22841;
                                    }

                                    if (this.p.A() && this.p.B()) {
                                        break label23069;
                                    }
                                } catch (Throwable var2052) {
                                    var10000 = var2052;
                                    var10001 = false;
                                    break label23098;
                                }

                                try {
                                    this.d(var2082);
                                    break label23069;
                                } catch (Throwable var2039) {
                                    var10000 = var2039;
                                    var10001 = false;
                                    break label23098;
                                }
                            }

                            try {
                                if (this.p.Y && !this.t && this.p.at()) {
                                    this.g.add(var2082);
                                    this.t = this.h;
                                    break label23069;
                                }
                            } catch (Throwable var2051) {
                                var10000 = var2051;
                                var10001 = false;
                                break label23098;
                            }

                            try {
                                this.a(true, var2082, 0, var2);
                            } catch (Throwable var2038) {
                                var10000 = var2038;
                                var10001 = false;
                                break label23098;
                            }
                        }

                        try {
                            com.unisound.common.y.f(new Object[]{"VAD done        1"});
                            this.b(this.c());
                            if (this.p.A() && this.p.B()) {
                                this.d(var1.length);
                            }
                        } catch (Throwable var2037) {
                            var10000 = var2037;
                            var10001 = false;
                            break label23098;
                        }

                        double var8;
                        label23067: {
                            try {
                                if (!this.p.A() || this.u || this.p.c.a()) {
                                    break label23067;
                                }
                            } catch (Throwable var2050) {
                                var10000 = var2050;
                                var10001 = false;
                                break label23098;
                            }

                            var3 = var7;

                            try {
                                if (!this.b(var5)) {
                                    break label22899;
                                }

                                var8 = this.k();
                                this.p.c(this.b(2, String.valueOf(this.a(var8))));
                                this.c(var7);
                                this.j();
                                this.h();
                            } catch (Throwable var2049) {
                                var10000 = var2049;
                                var10001 = false;
                                break label23098;
                            }

                            var3 = var7;
                            break label22899;
                        }

                        var3 = var7;

                        try {
                            if (!this.c(var5)) {
                                break label22899;
                            }

                            var8 = this.k();
                            this.p.c(this.b(1, String.valueOf(this.a(var8))));
                            this.c(var7);
                            this.j();
                            this.i();
                        } catch (Throwable var2048) {
                            var10000 = var2048;
                            var10001 = false;
                            break label23098;
                        }

                        var3 = var7;
                        break label22899;
                    }

                    var3 = 0;
                }

                var2 = var3;

                try {
                    if (!com.unisound.common.y.l) {
                        return var2;
                    }

                    com.unisound.common.y.d(new Object[]{"AF_VAD::read size =", var1.length, " first byte ", var1[0]});
                } catch (Throwable var2036) {
                    var10000 = var2036;
                    var10001 = false;
                    break label23098;
                }

                var2 = var3;
                return var2;
            }

            var2 = 0;
            return var2;
        }

        Throwable var2080 = var10000;
        throw var2080;
    }

    public void a(int var1){
        synchronized(this){}

        Throwable var10000;
        label149: {
            boolean var10001;
            try {
                this.h = false;
                this.r = false;
                this.t = false;
                this.g.clear();
                this.o.reset();
                if (this.p.A()) {
                    this.u = false;
                }
            } catch (Throwable var15) {
                var10000 = var15;
                var10001 = false;
                break label149;
            }

            boolean var2;
            try {
                var2 = this.a();
            } catch (Throwable var14) {
                var10000 = var14;
                var10001 = false;
                break label149;
            }

            if (!var2 || var1 != 2) {
                return;
            }

            label137:
            try {
                this.reset(this.i);
                return;
            } catch (Throwable var13) {
                var10000 = var13;
                var10001 = false;
                break label137;
            }
        }


    }

    public void a(int var1, int var2) {
        if (this.a()) {
            var1 /= 10;
            var2 /= 10;
            this.setTime(this.i, var1, var2);
        }

    }

    public void a(boolean var1) {
        this.j = var1;
    }

    public void a(boolean var1, byte[] var2, int var3, int var4) {
        if (com.unisound.common.y.l) {
            com.unisound.common.y.d(new Object[]{"VAD::onVadData size =", var2.length, " first byte ", var2[0], "enabled = ", var1});
        }

        cr var5 = this.q;
        if (var1 && this.p.d() != null && !this.p.d().equals("") && (var2.length != 1 || var2[0] != 100 && var2[0] != 99)) {
            com.unisound.common.j.a(var2, this.p.d());
        }

        if (var5 != null) {
            var5.b(var1, var2, var3, var4);
        }

    }

    protected void a(byte[] var1) {
        synchronized(this){}

        Throwable var10000;
        label205: {
            boolean var10001;
            byte[] var2;
            int var3;
            try {
                this.o.write(var1, 0, var1.length);
                if (this.o.size() < this.p.ae) {
                    return;
                }

                var2 = this.o.toByteArray();
                this.o.reset();
                var3 = this.checkPitchOffset(this.i, var2, var2.length);
            } catch (Throwable var23) {
                var10000 = var23;
                var10001 = false;
                break label205;
            }

            var1 = var2;
            if (var3 > 0) {
                try {
                    var1 = new byte[var3];
                    System.arraycopy(var2, 0, var1, 0, var3);
                    this.a(false, var1, 0, var1.length);
                    this.o.write(var2, var3, var2.length - var3);
                    var1 = this.o.toByteArray();
                    this.o.reset();
                } catch (Throwable var22) {
                    var10000 = var22;
                    var10001 = false;
                    break label205;
                }
            }

            try {
                f = var3;
                if (var1.length > 0) {
                    this.a(true, var1, 0, var1.length);
                    this.b(var1, var1.length);
                }
            } catch (Throwable var21) {
                var10000 = var21;
                var10001 = false;
                break label205;
            }

            label190:
            try {
                this.r = true;
                this.h = true;
                return;
            } catch (Throwable var20) {
                var10000 = var20;
                var10001 = false;
                break label190;
            }
        }


    }

    public boolean a() {
        boolean var1;
        if (this.i != 0L) {
            var1 = true;
        } else {
            var1 = false;
        }

        return var1;
    }

    public int b(byte[] var1, int var2) {
        if (this.i == 0L) {
            var2 = 0;
        } else {
            var2 = this.isVADTimeout(this.i, var1, var2);
        }

        return var2;
    }

    public void b() {
        com.unisound.common.y.b(new Object[]{"frontSil = ", this.p.ab, " backSil= ", this.p.ac});
        this.a(this.p.ab, this.p.ac);
        if (this.p.h()) {
            com.unisound.common.y.b(new Object[]{"mParams.isFarFeildEnabled() = ", this.p.h()});
            this.b(this.p.h());
        }

        if (this.p.y.a != null && !this.p.y.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.MINBACKENG = ", this.p.y.a});
            this.a((b)this.p.y);
        }

        if (this.p.z.a != null && !this.p.z.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.MINBACKENGH = ", this.p.z.a});
            this.a((b)this.p.z);
        }

        if (this.p.A.a != null && !this.p.A.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.PITCHTH = ", this.p.A.a});
            this.a((b)this.p.A);
        }

        if (this.p.B.a != null && !this.p.B.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.PITCHSTNUMTH = ", this.p.B.a});
            this.a((b)this.p.B);
        }

        if (this.p.C.a != null && !this.p.C.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.PITCHENDNUMTH = ", this.p.C.a});
            this.a((b)this.p.C);
        }

        if (this.p.D.a != null && !this.p.D.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.LOWHIGHTH = ", this.p.D.a});
            this.a((b)this.p.D);
        }

        if (this.p.E.a != null && !this.p.E.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.MINSIGLEN = ", this.p.E.a});
            this.a((b)this.p.E);
        }

        if (this.p.F.a != null && !this.p.F.a.equals("") && !this.p.F.a.equals(this.p.ac / 10 + "")) {
            com.unisound.common.y.b(new Object[]{"mParams.MAXSILLEN = ", this.p.F.a});
            this.a((b)this.p.F);
        }

        if (this.p.G.a != null && !this.p.G.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.SINGLEMAX = ", this.p.G.a});
            this.a((b)this.p.G);
        }

        if (this.p.H.a != null && !this.p.H.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.NOISE2YTH = ", this.p.H.a});
            this.a((b)this.p.H);
        }

        if (this.p.I.a != null && !this.p.I.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.NOISE2YTHVOWEL = ", this.p.I.a});
            this.a((b)this.p.I);
        }

        if (this.p.J.a != null && !this.p.J.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.VOICEPROBTH = ", this.p.J.a});
            this.a((b)this.p.J);
        }

        if (this.p.K.a != null && !this.p.K.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.USEPEAK = ", this.p.K.a});
            this.a((b)this.p.K);
        }

        if (this.p.L.a != null && !this.p.L.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.NOISE2YST = ", this.p.L.a});
            this.a((b)this.p.L);
        }

        if (this.p.M.a != null && !this.p.M.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.PITCHLASTTH = ", this.p.M.a});
            this.a((b)this.p.M);
        }

        if (this.p.N.a != null && !this.p.N.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.DETECTMUSIC = ", this.p.N.a});
            this.a((b)this.p.N);
        }

        if (this.p.O.a != null && !this.p.O.a.equals("")) {
            com.unisound.common.y.b(new Object[]{"mParams.MUSICTH = ", this.p.O.a});
            this.a((b)this.p.O);
        }

        this.j();
    }

    protected boolean b(byte[] var1) {
        boolean var5;
        if (this.z) {
            this.y.add(var1);
            int var2 = this.p.G();
            int var3 = this.y.size() - 1;

            for(int var4 = 0; var3 >= 0; --var3) {
                var4 += ((byte[])this.y.get(var3)).length;
                if (var4 >= var2) {
                    var5 = true;
                    return var5;
                }
            }
        }

        var5 = false;
        return var5;
    }

    public int c() {
        int var1;
        if (!this.a()) {
            var1 = 0;
        } else {
            var1 = this.getVolume(this.i);
        }

        return var1;
    }

    protected int c(byte[] var1, int var2) {
        int var3 = 0;

        float var4;
        for(var4 = 0.0F; var3 < var2; var3 += 2) {
            int var5 = (var1[var3] & 255) + ((var1[var3 + 1] & 255) << 8);
            int var6 = var5;
            if (var5 >= 32768) {
                var6 = '\uffff' - var5;
            }

            var4 += (float)Math.abs(var6);
        }

        var3 = (int)((10.0D * Math.log10((double)(2.0F * var4 / (float)var2 + 1.0F)) - 20.0D) * 5.0D);
        var2 = var3;
        if (var3 < 0) {
            var2 = 0;
        }

        var3 = var2;
        if (var2 > 100) {
            var3 = 100;
        }

        return var3;
    }

    protected boolean c(byte[] var1) {
        boolean var5;
        if (this.B) {
            this.A.add(var1);
            int var2 = this.p.H();
            int var3 = this.A.size() - 1;

            for(int var4 = 0; var3 >= 0; --var3) {
                var4 += ((byte[])this.A.get(var3)).length;
                if (var4 >= var2) {
                    var5 = true;
                    return var5;
                }
            }
        }

        var5 = false;
        return var5;
    }

    protected native int checkPitchOffset(long var1, byte[] var3, int var4);

    protected native long create();

    public void d() {
        // $FF: Couldn't be decompiled
    }

    protected native void destory(long var1);

    public void e() throws Throwable {
        synchronized(this){}

        Throwable var10000;
        label229: {
            boolean var10001;
            try {
                if (this.o.size() > 0) {
                    this.a(this.h, this.o.toByteArray(), 0, this.o.size());
                    this.o.reset();
                }
            } catch (Throwable var23) {
                var10000 = var23;
                var10001 = false;
                break label229;
            }

            int var1;
            try {
                var1 = this.g.size();
            } catch (Throwable var22) {
                var10000 = var22;
                var10001 = false;
                break label229;
            }

            for(int var2 = 0; var2 < var1; ++var2) {
                try {
                    byte[] var3 = (byte[])this.g.remove(0);
                    this.a(this.h, var3, 0, var3.length);
                } catch (Throwable var21) {
                    var10000 = var21;
                    var10001 = false;
                    break label229;
                }
            }

            label209:
            try {
                this.b(0);
                return;
            } catch (Throwable var20) {
                var10000 = var20;
                var10001 = false;
                break label209;
            }
        }

        Throwable var24 = var10000;
        throw var24;
    }

    protected native int getVolume(long var1);

    protected native void init(long var1);

    protected native int isVADTimeout(long var1, byte[] var3, int var4);

    protected native int nativeSetOption(long var1, int var3, String var4);

    protected native void reset(long var1);

    protected native void setTime(long var1, int var3, int var4);
}

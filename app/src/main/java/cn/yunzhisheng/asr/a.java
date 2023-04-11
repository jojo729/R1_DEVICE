package cn.yunzhisheng.asr;

import android.content.Context;
import android.media.AudioManager;
import com.unisound.common.s;
import com.unisound.common.y;
import com.unisound.sdk.ab;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class a {
    public static final int P = 3000;
    public static final int Q = 300;
    public static final int R = 1000;
    public static final int S = 16000;
    public static final int T = 8000;
    public static final int U = 500;
    public static final boolean V = false;
    public static final boolean W = false;
    public static final boolean X = true;

    /* renamed from: a  reason: collision with root package name */
    public static final int f29a = 10000;
    public static final int an = 1;
    public static final int b = 20000;
    private static final int ba = 3840;
    static final int d = 5;
    static final int e = 6;
    static final int f = 7;
    static final int g = 8;
    static final int h = 9;
    static final int i = 10;
    static final int j = 11;
    static final int k = 12;
    static final int l = 13;
    static final int m = 14;
    static final int n = 15;
    static final int o = 16;
    static final int p = 17;
    static final int q = 18;
    static final int r = 19;
    static final int s = 20;
    static final int t = 21;
    static final int u = 22;
    static final int v = 23;
    static final int w = 24;
    static final int x = 25;
    public c A = new c(this, 7);
    public d B = new d(this, 8);
    public d C = new d(this, 9);
    public c D = new c(this, 10);
    public d E = new d(this, 11);
    public d F = new d(this, 12);
    public c G = new c(this, 13);
    public c H = new c(this, 14);
    public c I = new c(this, 15);
    public c J = new c(this, 16);
    public d K = new d(this, 17);
    public d L = new d(this, 18);
    public d M = new d(this, 21);
    public d N = new d(this, 24);
    public c O = new c(this, 25);
    boolean Y = false;
    boolean Z = false;
    private int aA = 350;
    private boolean aB = false;
    private boolean aC = false;
    private String aD = null;
    private String aE = null;
    private boolean aF = false;
    private boolean aG = true;
    private boolean aH = true;
    private int aI = 700;
    private boolean aJ = false;
    private boolean aK = false;
    private boolean aL = false;
    private long aM = 0;
    private boolean aN = false;
    private float aO = -2.7f;
    private int aP = 1000;
    private String aQ = "";
    private boolean aR = false;
    private int aS = 0;
    private int aT = 3000;
    private long aU = 0;
    private boolean aV = true;
    private long aW = 0;
    private int aX = 3000;
    private int aY = 300;
    private int aZ = 0;
    int aa = 16000;
    int ab = 3000;
    int ac = 300;
    int ad = 16000;
    int ae = 38000;
    protected boolean af = false;
    public boolean ag = false;
    int ah = 0;
    int ai = 16000;
    public boolean aj = false;
    boolean ak = false;
    public int al = 3000;
    public String am = "";
    public int ao = 1;
    public int ap = 1;
    public boolean aq = false;
    public boolean ar = false;
    public boolean as = false;
    public Map<String, String> at = new HashMap();
    boolean au = false;
    boolean av = false;
    private boolean aw = false;
    private int ax = 0;
    private long ay = 0;
    private long az = 0;
    private int bb = ba;
    private boolean bc = false;
    private boolean bd = false;
    private String be = "";
    private boolean bf = false;
    private String bg = "";
    public s c;
    public c y = new c(this, 5);
    public c z = new c(this, 6);

    public a(Context context) {
        f(500);
        r();
        this.N.a(0);
        this.O.a(-0.25f);
        try {
            this.c = new s((AudioManager) context.getSystemService("audio"));
        } catch (Exception e2) {
             com.unisound.common.y.c("setfourMicUtil error");
        }
    }

    public int A(int i2) {
        return i2 / ((this.aa / 1000) * 2);
    }

    public void A(boolean z2) {
        this.bf = z2;
    }

    public boolean A() {
        return this.aK;
    }

    public int B(int i2) {
        return (this.aa / 1000) * i2 * 2;
    }

    public void B(boolean z2) {
        this.aH = z2;
    }

    public boolean B() {
        return this.aL;
    }

    public long C() {
        return this.aM;
    }

    public void C(int i2) {
        this.aa = i2;
    }

    public void C(boolean z2) {
        this.aC = z2;
    }

    public boolean D() {
        return this.aN;
    }

    public float E() {
        return this.aO;
    }

    public int F() {
        return this.aP;
    }

    public int G() {
        return (this.aa / 1000) * this.aP * 2;
    }

    public int H() {
        return (this.aa / 1000) * this.aI * 2;
    }

    public int I() {
        return this.al;
    }

    public int J() {
        return (this.aa / 1000) * this.al * 2;
    }

    public String K() {
        return this.am;
    }

    public String L() {
        return this.aQ;
    }

    public boolean M() {
        return this.aR;
    }

    public int N() {
        return this.aS;
    }

    public int O() {
        return this.aT;
    }

    public boolean P() {
        try {
            return Integer.parseInt(this.N.a) == 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public float Q() {
        try {
            return Float.parseFloat(this.O.a);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0f;
        }
    }

    public long R() {
        return this.aU;
    }

    public void S() {
        this.aU = 0;
    }

    public boolean T() {
        return this.aV;
    }

    public long U() {
        return this.aW;
    }

    public void V() {
        this.aW = 0;
    }

    public int W() {
        return this.aX;
    }

    public int X() {
        return this.aY;
    }

    public int Y() {
        return this.ao;
    }

    public int Z() {
        return this.ap;
    }

    public List<String> a(Map<String, List<String>> map) {
        ArrayList arrayList = null;
        try {
            if (map.size() <= 0) {
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            try {
                for (String str : map.keySet()) {
                    List<String> list = map.get(str);
                    if (list != null) {
                        for (String str2 : list) {
                            if (str2 != null) {
                                this.at.put(str2, str);
                                arrayList2.add(str2);
                            }
                        }
                    }
                }
                return arrayList2;
            } catch (Exception e2) {
                arrayList = arrayList2;
                com.unisound.common.y.a("set wakeupWord and property error");
                return arrayList;
            }
        } catch (Exception e3) {
            com.unisound.common.y.a("set wakeupWord and property error");
            return arrayList;
        }
    }

    public void a(float f2) {
        this.aO = f2;
    }

    public void a(int i2) {
        this.ah = i2;
    }

    public void a(int i2, int i3) {
        this.ab = i2;
        if (i3 > 300) {
            this.aI = i3 -300;
            i3 = 300;
        } else {
            this.aI = 0;
        }
        this.ac = i3;
    }

    public void a(long j2) {
        this.aM = j2;
    }

    public void a(String str) {
        this.aD = str;
    }

    public void a(boolean z2) {
        this.aw = z2;
    }

    public void a(byte[] bArr, String str) {
        s sVar = this.c;
        if (sVar != null) {
            sVar.a(bArr, str);
        }
    }

    public boolean a() {
        return this.aw;
    }

    public boolean aa() {
        return this.aq;
    }

    public boolean ab() {
        return this.ar;
    }

    public boolean ac() {
        return this.as;
    }

    public int ad() {
        s sVar = this.c;
        if (sVar != null) {
            return sVar.s() ? this.c.c() : this.c.b();
        }
        return 0;
    }

    public int ae() {
        s sVar = this.c;
        if (sVar != null) {
            return sVar.d();
        }
        return 0;
    }

    public void af() {
        if (this.c.a() || this.aC) {
            this.y.a(5000000.0f);
            this.J.a(0.5f);
            this.F.a(80);
            this.K.a(1);
            this.M.a(1);
            this.B.a(1);
            this.C.a(100);
            this.A.a(-0.3f);
        }
    }

    public int ag() {
        return this.aZ;
    }

    public void ah() {
        this.aZ = 0;
    }

    public int ai() {
         com.unisound.common.y.c("current Recording Length is " + this.bb);
        return this.bb;
    }

    public long aj() {
        return this.ay;
    }

    public long ak() {
        return this.az;
    }

    public boolean al() {
        return this.au;
    }

    public boolean am() {
        return this.aG;
    }

    public boolean an() {
        return this.bc;
    }

    public boolean ao() {
        return this.bd;
    }

    public boolean ap() {
        return this.av;
    }

    public String aq() {
        return this.be;
    }

    public boolean ar() {
        return this.bf;
    }

    public String as() {
        File file = new File(this.bg);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public boolean at() {
        return this.aH;
    }

    public int au() {
        return this.aa;
    }

    public boolean av() {
        return this.aC;
    }

    public void b(float f2) {
        this.O.a(f2);
    }

    public void b(int i2) {
        this.ai = i2;
    }

    public void b(long j2) {
        this.aM += j2;
    }

    public void b(String str) {
        this.aE = str;
    }

    public void b(boolean z2) {
        this.aB = z2;
    }

    public boolean b() {
        return this.aB;
    }

    public long c(long j2) {
        return j2 / ((long) ((this.aa / 1000) * 2));
    }

    public String c() {
        return this.aD;
    }

    public void c(int i2) {
        this.aA = i2;
    }

    public void c(String str) {
        this.am = str;
    }

    public void c(boolean z2) {
        this.af = z2;
    }

    public String d() {
        return this.aE;
    }

    public void d(int i2) {
        this.ab = i2;
    }

    public void d(long j2) {
        this.aU = j2;
    }

    public void d(String str) {
        this.aQ = str;
    }

    public void d(boolean z2) {
        this.Y = z2;
    }

    public int e() {
        return this.ah;
    }

    public void e(int i2) {
        this.aI = i2 > 300 ? i2 - 300 : 0;
    }

    public void e(long j2) {
        this.aU += j2;
    }

    public void e(String str) {
        this.aR = str.equals(this.aQ);
    }

    public void e(boolean z2) {
        this.aj = z2;
    }

    public int f() {
        int i2 = this.ai;
        if (this.ag) {
            return 8000;
        }
        return i2;
    }

    public String f(String str) {
        return this.at.containsKey(str) ? this.at.get(str) : "";
    }

    public void f(int i2) {
        if (i2 < 100) {
            i2 = 100;
        }
        this.ad = (this.aa / 1000) * i2 * 2;
    }

    public void f(long j2) {
        this.aW = j2;
    }

    public void f(boolean z2) {
        this.Z = z2;
    }

    public int g() {
        return this.aA;
    }

    public void g(int i2) {
        this.ax = i2;
    }

    public void g(long j2) {
        this.aW += j2;
    }

    public void g(String str) {
        this.be = str;
    }

    public void g(boolean z2) {
        this.aF = z2;
    }

    public long h(long j2) {
        return j2 / ((long) ((this.aa / 1000) * 2));
    }

    public void h(int i2) {
        this.aP = i2;
    }

    public void h(String str) {
        this.bg = str;
    }

    public void h(boolean z2) {
        this.ak = z2;
    }

    public boolean h() {
        return this.af;
    }

    public int i() {
        return this.aI;
    }

    public long i(long j2) {
        return ((long) (this.aa / 1000)) * j2 * 2;
    }

    public void i(int i2) {
        this.al = i2;
    }

    public void i(boolean z2) {
        this.aJ = z2;
    }

    public void j(int i2) {
        this.aS = i2;
    }

    public void j(boolean z2) {
        this.aK = z2;
    }

    public boolean j() {
        return this.Y;
    }

    public void k(int i2) {
        this.aT = i2;
    }

    public void k(boolean z2) {
        this.aL = z2;
    }

    public boolean k() {
        return this.aj;
    }

    public void l(int i2) {
        this.aX = i2;
    }

    public void l(boolean z2) {
        this.aN = z2;
    }

    public boolean l() {
        return this.Z;
    }

    public void m(int i2) {
        this.aY = i2;
    }

    public void m(boolean z2) {
        d dVar;
        int i2;
        if (z2) {
            dVar = this.N;
            i2 = 1;
        } else {
            dVar = this.N;
            i2 = 0;
        }
        dVar.a(i2);
    }

    public boolean m() {
        return this.Z;
    }

    public void n(int i2) {
        this.ao = i2;
    }

    public void n(boolean z2) {
        this.aV = z2;
    }

    public boolean n() {
        return this.aF;
    }

    public void o() {
        this.y.a();
        this.z.a();
        this.A.a();
        this.B.a();
        this.C.a();
        this.D.a();
        this.E.a();
        this.F.a();
        this.G.a();
        this.H.a();
        this.I.a();
        this.J.a();
        this.K.a();
        this.L.a();
        this.M.a();
        this.N.a();
        this.O.a();
    }

    public void o(int i2) {
        this.ap = i2;
    }

    public void o(boolean z2) {
        s sVar = this.c;
        if (sVar != null) {
            sVar.a(z2);
            af();
            return;
        }
        com.unisound.common.y.a("setUseFourMicStatus error");
    }

    public void p() {
        o();
        this.I.a(0.7f);
        this.H.a(1.0f);
        this.A.a(0.22f);
        this.B.a(5);
        this.J.a(0.6f);
        this.K.a(1);
        this.L.a(3);
        this.y.a(2.0E8f);
        this.G.a(80.0f);
        this.D.a(1000000.0f);
        this.M.a(1);
    }

    public void p(int i2) {
        s sVar = this.c;
        if (sVar == null) {
            return;
        }
        if (sVar.s()) {
            this.c.b(i2);
        } else {
            this.c.a(i2);
        }
    }

    public void p(boolean z2) {
        if (this.c.s()) {
            this.c.c(z2);
        } else {
            this.c.b(z2);
        }
    }

    public void q() {
        o();
    }

    public void q(int i2) {
        p(i2);
    }

    public void q(boolean z2) {
        this.aq = z2;
    }

    public void r() {
        o();
    }

    public void r(int i2) {
        s sVar = this.c;
        if (sVar == null) {
            return;
        }
        if (sVar.s()) {
            this.c.d(i2);
        } else {
            this.c.c(i2);
        }
    }

    public void r(boolean z2) {
        this.ar = z2;
    }

    public void s() {
        o();
    }

    public void s(int i2) {
        r(i2);
    }

    public void s(boolean z2) {
        this.as = z2;
    }

    public String t(boolean z2) {
        s sVar = this.c;
        return sVar != null ? sVar.e() : "";
    }

    public void t() {
        o();
    }

    public void t(int i2) {
        s sVar = this.c;
        if (sVar == null) {
            return;
        }
        if (sVar.s()) {
            this.c.g(i2);
        } else {
            this.c.f(i2);
        }
    }

    public int u() {
        return this.ab;
    }

    public void u(int i2) {
        t(i2);
    }

    public void u(boolean z2) {
        s sVar = this.c;
        if (sVar == null) {
            return;
        }
        if (sVar.s()) {
            this.c.e(z2);
        } else {
            this.c.d(z2);
        }
    }

    public int v() {
        return this.ac;
    }

    public void v(int i2) {
        s sVar = this.c;
        if (sVar != null) {
            sVar.h(i2);
        }
    }

    public void v(boolean z2) {
        this.au = z2;
    }

    public int w() {
        return this.ax;
    }

    public void w(int i2) {
        this.aZ += i2;
    }

    public void w(boolean z2) {
        this.aG = z2;
    }

    public int x() {
        return (this.aa / 1000) * this.ax * 2;
    }

    public void x(int i2) {
        if (i2 <= 0) {
            i2 = ba;
        }
        this.bb = i2;
    }

    public void x(boolean z2) {
        this.bc = z2;
    }

    public void y(int i2) {
        long i3 = i((long) i2);
        if (i3 > 0) {
            this.ay = i3;
        }
    }

    public void y(boolean z2) {
        this.bd = z2;
    }

    public boolean y() {
        return this.ak;
    }

    public void z(int i2) {
        long i3 = i((long) i2);
        if (i3 > 0) {
            this.az = i3;
        }
    }

    public void z(boolean z2) {
        this.av = z2;
    }

    public boolean z() {
        return this.aJ;
    }
}

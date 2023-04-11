package com.unisound.sdk;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import cn.yunzhisheng.asr.VAD;
import cn.yunzhisheng.asrfix.JniAsrFix;
import cn.yunzhisheng.nlu.OfflineNlu;
import com.unisound.client.IAudioSource;
import com.unisound.common.ah;
import com.unisound.common.an;
import com.unisound.common.ar;
import com.unisound.common.b;
import com.unisound.common.d;
import com.unisound.common.e;
import com.unisound.common.g;
import com.unisound.common.h;
import com.unisound.common.j;
import java.util.List;

public class o {
    public static final int OPT_SET_FIX_RESULT_NLU = 5;
    public static final int OPT_SET_FIX_RESULT_NLU_CONFIGPATH = 6;
    public static final int SET_USER_DATA_ERROR = -100;
    public static final int SET_USER_DATA_OK = 0;
    public static final int SET_USER_DATA_WARNING = -200;
    protected l a = new l();
    protected w b;
    protected y c = null;
    protected aa d = null;
    protected m e;
    protected ab f = new ab();
    protected ar g;
    protected n h = new n();
    protected a i = new a();
    protected cs j = new cs();
    protected av k = new av();
    protected Context l;
    protected float m = -8.0F;
    public Looper mLooper;
    public br mSpeechUnderstanderParams;
    protected HandlerThread n;
    private boolean o = false;
    private boolean p = true;
    private g q = new g();
    private boolean r = false;
    private String s = "";
    private IAudioSource t = null;
    private boolean u = false;
    private t v;
    private d w = new q(this);
    private aj x = new r(this);

    protected o(Context var1, String var2) {
        this.b = new w(var1);
        HandlerThread var3 = new HandlerThread("ht_NetAndFix");
        this.n = var3;
        var3.start();
        this.mLooper = this.n.getLooper();
        this.e = new m(this.mLooper, var1);
        this.i.a(this.w);
        this.l = var1;
        this.mSpeechUnderstanderParams = new br();
        StringBuilder var6 = new StringBuilder();
        var6.append(var1.getApplicationContext().getFilesDir());
        var6.append("/YunZhiSheng/asrfix");
        String var7 = var6.toString();
        this.f.a(var7);
        l var8 = this.a;
        StringBuilder var4 = new StringBuilder();
        var4.append(var1.getApplicationContext().getFilesDir());
        var4.append("/YunZhiSheng/");
        var8.c(var4.toString());
        this.q.a(var1);
        this.b.f(false);
        this.b.a(3000, 1000);
        this.b.o(var2);
        this.e.a(this.b);
        t var5 = new t(this, (p)null);
        this.v = var5;
        this.e.a(var5);
        ab.m = 1;
        this.a((ah)(new p(this)));
    }

    protected static boolean b(String var0) {
        return ab.d(var0);
    }

    protected int a(String var1, String var2, String var3) {
        return this.e.b(var1, var2, var3);
    }

    protected int a(String var1, String var2, String var3, String var4) {
        int var5;
        if (!this.f.a(this.l)) {
            var5 = -100;
        } else {
            var5 = this.f.a(var1, var2, var3, var4);
        }

        return var5;
    }

    protected as a() {
        return this.b;
    }

    protected String a(String var1, String var2) {
        return this.e.a(var1, var2);
    }

    protected void a(int var1) {
    }

    protected void a(int var1, int var2) {
        this.b.a(var1, var2);
    }

    protected void a(int var1, int var2, Object var3) {
        ar var4 = this.g;
        if (var4 != null) {
            var4.a(var1, var2, var3);
        }

    }

    protected void a(VAD var1) {
        this.h.c();
    }

    protected void a(ah var1) {
        this.k.a(var1);
    }

    protected void a(b var1) {
        this.i.a(var1);
    }

    protected void a(v var1) {
        this.h.a(var1);
    }

    protected void a(String var1, boolean var2, int var3, int var4) {
    }

    protected void a(String var1, boolean var2, int var3, long var4, long var6, int var8, int var9) {
    }

    protected void a(boolean var1, byte[] var2, int var3, int var4) {
    }

    protected boolean a(Context var1) {
        return com.unisound.common.h.a(var1);
    }

    protected boolean a(Message var1) {
        return false;
    }

    protected boolean a(String var1) {
        return false;
    }

    protected boolean a(String var1, boolean var2) {
        if (var1 != null && var1.length() != 0) {
            StringBuilder var3 = new StringBuilder();
            var3.append(var1);
            var3.append("/YunZhiSheng/asrfix");
            var1 = var3.toString();
        } else {
            StringBuilder var4 = new StringBuilder();
            var4.append(this.l.getApplicationContext().getFilesDir());
            var4.append("/YunZhiSheng/asrfix");
            var1 = var4.toString();
        }

        this.f.a(var1);
        this.f.a(this.l, "ml");
        if (!this.f.a(this.l, var2)) {
            com.unisound.common.y.a("USCFixRecognizer.initByModelDir init data fail!");
            var2 = false;
        } else {
            var2 = true;
        }

        return var2;
    }

    protected boolean a(boolean var1) {
        return this.a((String)null, var1);
    }

    protected int b(String var1, String var2) {
        byte var3;
        if (!this.e.a(this.f.a, var1, var2)) {
            var3 = -1;
        } else {
            var3 = 0;
        }

        return var3;
    }

    protected int b(boolean var1) {
        return this.e.e(var1);
    }

    protected void b() {
        this.v = new t(this, (p)null);
        this.e.a(this.x);
        this.e.a(this.v);
        this.e.a(this.b);
    }

    protected void b(int var1) {
        if (var1 != 0) {
            this.e.a(false);
        }

    }

    protected void b(int var1, int var2) {
    }

    protected void b(String var1, String var2, String var3) {
        this.e.a(this.f.a, var1, var2, var3);
    }

    protected boolean b(Context var1) {
        return com.unisound.common.h.c(var1);
    }

    protected int c(boolean var1) {
        return this.e.f(var1);
    }

    protected void c(int var1) {
        this.h.a(var1);
    }

    protected void c(int var1, int var2) {
    }

    protected boolean c() {
        return this.e.r();
    }

    protected void cancel() {
        this.v = null;
        this.e.a((com.unisound.sdk.ar)null);
        this.e.a(true);
    }

    protected void d(int var1) {
    }

    protected boolean d() {
        return this.e.q();
    }

    protected int e(int var1) {
        return this.e.c(var1);
    }

    protected boolean e() {
        return this.f.c();
    }

    protected int f(int var1) {
        return this.e.d(var1);
    }

    protected void f() {
        m var1 = this.e;
        if (var1 != null) {
            var1.g();
        }

    }

    protected int g() {
        return 0;
    }

    protected int g(int var1) {
        return this.e.e(var1);
    }

    protected String getFixEngineVersion() {
        return JniAsrFix.getVersion();
    }

    protected Object getOption(int var1) {
        boolean var2;
        Object var3;
        if (1055 == var1) {
            var2 = this.b.y();
        } else if (1056 == var1) {
            var2 = this.b.j();
        } else {
            label46: {
                if (1010 == var1) {
                    var1 = this.b.u();
                } else {
                    if (1011 != var1) {
                        if (1015 != var1) {
                            if (1016 == var1) {
                                var3 = this.b.Q();
                            } else {
                                var3 = null;
                            }

                            return var3;
                        }

                        var2 = this.b.P();
                        break label46;
                    }

                    var1 = this.b.v();
                }

                var3 = var1;
                return var3;
            }
        }

        var3 = var2;
        return var3;
    }

    protected String getVersion() {
        return an.a();
    }

    protected int h(int var1) {
        return this.e.f(var1);
    }

    protected void h() {
    }

    protected int i(int var1) {
        return this.e.g(var1);
    }

    protected void i() {
    }

    protected int j(int var1) {
        return this.e.h(var1);
    }

    protected void j() {
    }

    protected int k(int var1) {
        return this.e.i(var1);
    }

    protected boolean k() {
        return false;
    }

    protected void l() {
    }

    protected void m() {
    }

    protected void n() {
        if (this.b.l()) {
            this.q.a();
        }

        this.h.b();
    }

    protected void o() {
    }

    protected void p() {
    }

    public void postRecordingStartStatus() {
    }

    protected void q() {
        this.f.f();
        this.e.p();
    }

    protected void r() {
        com.unisound.common.y.c(new Object[]{"FixRecognizerInterFace : createJniAsrFix"});
        this.f.a(this.l, "ml");
        this.e.s();
        this.e.a(new s(this));
    }

    protected int s() {
        return this.e.t();
    }

    protected int setAudioSource(IAudioSource var1) {
        this.t = var1;
        if (var1 == null) {
            this.t = new e(this.b);
        }

        return 0;
    }

    protected void setOption(int var1, Object var2) {
        label388: {
            String var36;
            if (1051 == var1) {
                try {
                    boolean var3 = (Boolean)var2;
                    this.a.b = var3;
                    return;
                } catch (Exception var5) {
                    var36 = "set asr_result_filter Error.";
                }
            } else if (1053 == var1) {
                try {
                    this.p = (Boolean)var2;
                    return;
                } catch (Exception var6) {
                    var36 = "set asr_recording_enabled Error.";
                }
            } else {
                label389: {
                    boolean var10001;
                    if (1054 == var1) {
                        try {
                            com.unisound.common.y.k = (Boolean)var2;
                            com.unisound.c.a.b((Boolean)var2);
                            return;
                        } catch (Exception var7) {
                            var10001 = false;
                        }
                    } else {
                        if (1063 != var1) {
                            if (1055 == var1) {
                                return;
                            }

                            if (1058 == var1) {
                                try {
                                    this.b.a((String)var2);
                                    return;
                                } catch (Exception var9) {
                                    var36 = "set asr_save_recording_data Error.";
                                    break label389;
                                }
                            } else if (1059 == var1) {
                                try {
                                    this.a.c = (Boolean)var2;
                                    return;
                                } catch (Exception var10) {
                                    var36 = "set asr_result_json Error.";
                                    break label389;
                                }
                            } else if (1010 == var1) {
                                try {
                                    this.b.d((Integer)var2);
                                    return;
                                } catch (Exception var11) {
                                    var36 = "set asr_vad_timeout_frontsil Error.";
                                    break label389;
                                }
                            } else if (1011 == var1) {
                                try {
                                    this.a(this.b.u(), (Integer)var2);
                                    this.b.m((Integer)var2);
                                    this.b.e((Integer)var2);
                                    return;
                                } catch (Exception var12) {
                                    var36 = "set asr_vad_timeout_backsil Error.";
                                    break label389;
                                }
                            } else if (5 == var1) {
                                try {
                                    this.a.f = (Boolean)var2;
                                    if (this.a.f && this.a.h == null) {
                                        l var37 = this.a;
                                        OfflineNlu var4 = new OfflineNlu();
                                        var37.h = var4;
                                    }

                                    return;
                                } catch (Exception var13) {
                                    var36 = "set asr_fix_result_nlu Error.";
                                    break label389;
                                }
                            } else if (6 == var1) {
                                try {
                                    this.s = (String)var2;
                                    this.a.g = (String)var2;
                                    if (this.a.h != null && !this.s.equals("")) {
                                        this.a.h.b(this.s, "");
                                    }

                                    return;
                                } catch (Exception var14) {
                                    var36 = "set asr_fix_result_nlu_configpath Error.";
                                    break label389;
                                }
                            } else if (1062 == var1) {
                                try {
                                    this.b.D((Boolean)var2);
                                    this.e.a((Boolean)var2);
                                    return;
                                } catch (Exception var15) {
                                    var36 = "set asr_print_engine_log Error.";
                                    break label389;
                                }
                            } else if (5000 == var1) {
                                try {
                                    this.b.c((Boolean)var2);
                                    return;
                                } catch (Exception var16) {
                                    var36 = "set setFarFeildEnabled Error. 5000 ";
                                    break label389;
                                }
                            } else if (5001 == var1) {
                                try {
                                    this.b.y.a((Float)var2);
                                    return;
                                } catch (Exception var17) {
                                    var36 = "set min back energy Error. 5001 ";
                                    break label389;
                                }
                            } else if (5002 == var1) {
                                try {
                                    this.b.z.a((Float)var2);
                                    return;
                                } catch (Exception var18) {
                                    var36 = "set min back energy higher TH Error. 5002 ";
                                    break label389;
                                }
                            } else if (5003 == var1) {
                                try {
                                    this.b.A.a((Float)var2);
                                    return;
                                } catch (Exception var19) {
                                    var36 = "set pitch threshold Error. 5003 ";
                                    break label389;
                                }
                            } else if (5004 == var1) {
                                try {
                                    this.b.B.a((Integer)var2);
                                    return;
                                } catch (Exception var20) {
                                    var36 = "set pitch persist length for start usage Error. 5004 ";
                                    break label389;
                                }
                            } else if (5005 == var1) {
                                try {
                                    this.b.C.a((Integer)var2);
                                    return;
                                } catch (Exception var21) {
                                    var36 = "set pitch drop length for end usage Error. 5005 ";
                                    break label389;
                                }
                            } else if (5006 == var1) {
                                try {
                                    this.b.D.a((Float)var2);
                                    return;
                                } catch (Exception var22) {
                                    var36 = "set high freq energy vs low freq energy Error. 5006 ";
                                    break label389;
                                }
                            } else if (5007 == var1) {
                                try {
                                    this.b.E.a((Integer)var2);
                                    return;
                                } catch (Exception var23) {
                                    var36 = "set min signal length for speech Error. 5007 ";
                                    break label389;
                                }
                            } else if (5008 == var1) {
                                try {
                                    this.b.F.a((Integer)var2);
                                    return;
                                } catch (Exception var24) {
                                    var36 = "set max silence length Error. 5008 ";
                                    break label389;
                                }
                            } else if (5009 == var1) {
                                try {
                                    this.b.G.a((Float)var2);
                                    return;
                                } catch (Exception var25) {
                                    var36 = "set max single point max in spectral Error. 5009 ";
                                    break label389;
                                }
                            } else if (5010 == var1) {
                                try {
                                    this.b.H.a((Float)var2);
                                    return;
                                } catch (Exception var26) {
                                    var36 = "set gloable noise to signal value threshold Error. 5010 ";
                                    break label389;
                                }
                            } else if (5011 == var1) {
                                try {
                                    this.b.I.a((Float)var2);
                                    return;
                                } catch (Exception var27) {
                                    var36 = "set gloable noise to signal value threshold for vowel part Error. 5011 ";
                                    break label389;
                                }
                            } else if (5012 == var1) {
                                try {
                                    this.b.J.a((Float)var2);
                                    return;
                                } catch (Exception var28) {
                                    var36 = "set voice freq domain prob Th Error. 5012 ";
                                    break label389;
                                }
                            } else if (5013 == var1) {
                                try {
                                    this.b.K.a((Integer)var2);
                                    return;
                                } catch (Exception var29) {
                                    var36 = "set use pitch or peak Error. 5013 ";
                                    break label389;
                                }
                            } else if (5014 == var1) {
                                try {
                                    this.b.L.a((Integer)var2);
                                    return;
                                } catch (Exception var30) {
                                    var36 = "set noise to y ratio, start point in freq domain Error. 5014 ";
                                    break label389;
                                }
                            } else if (5017 == var1) {
                                try {
                                    this.b.M.a((Integer)var2);
                                    return;
                                } catch (Exception var31) {
                                    var36 = "set PITCHLASTTH Error. 5017 ";
                                    break label389;
                                }
                            } else {
                                if (5021 == var1) {
                                    try {
                                        this.b.n((String)var2);
                                        return;
                                    } catch (Exception var32) {
                                        var10001 = false;
                                        break label388;
                                    }
                                }

                                if (1016 == var1) {
                                    try {
                                        this.b.b((Float)var2);
                                        return;
                                    } catch (Exception var33) {
                                        var36 = "set vad musicth info Error!";
                                        break label389;
                                    }
                                } else {
                                    if (1015 != var1) {
                                        if (20120629 != var1) {
                                            return;
                                        }

                                        try {
                                            com.unisound.common.y.l = (Boolean)var2;
                                            return;
                                        } catch (Exception var35) {
                                            var10001 = false;
                                            break label388;
                                        }
                                    }

                                    try {
                                        this.b.m((Boolean)var2);
                                        return;
                                    } catch (Exception var34) {
                                        var36 = "set vad detectMusic Error!";
                                        break label389;
                                    }
                                }
                            }
                        }

                        try {
                            com.unisound.common.y.m = (Boolean)var2;
                            return;
                        } catch (Exception var8) {
                            var10001 = false;
                        }
                    }

                    com.unisound.common.y.a("set asr_print_log Error.");
                    return;
                }
            }

            com.unisound.common.y.a(var36);
            return;
        }

        com.unisound.common.y.a("set activate info Error.");
    }

    protected void start() {
        this.start(this.f.e);
    }

    protected void start(String var1) {
        this.b();
        this.c = null;
        this.d = null;
        if (this.p) {
            ax.n();
            if (this.t == null) {
                this.t = new e(this.b);
            }

            com.unisound.common.y.g(new Object[]{"FixRecognizerInterface recognizer start"});
            this.e.a(var1, new ax(this.b, this.e, this.t), new aa(this.l, this.b, this.e));
        }

        w var2 = this.b;
        var2.b(com.unisound.common.j.a(var2.c()));
        if (this.b.b()) {
            this.r = com.unisound.common.j.b(this.b.c());
        }

    }

    protected void stop() {
        this.c = null;
        this.e.b();
        if (!this.p) {
            this.e.g();
        }

    }

    protected List t() {
        return this.e.u();
    }

    protected int u() {
        return this.e.v();
    }

    protected String v() {
        return this.e.w();
    }

    protected String w() {
        return this.e.x();
    }

    protected int x() {
        return this.e.y();
    }

    protected int y() {
        return this.e.z();
    }
}

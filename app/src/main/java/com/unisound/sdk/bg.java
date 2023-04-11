package com.unisound.sdk;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import cn.yunzhisheng.asr.JniUscClient;
import cn.yunzhisheng.asr.VAD;
import com.unisound.b.a.a;
import com.unisound.client.ErrorCode;
import com.unisound.client.IAudioSource;
import com.unisound.client.SpeechUnderstanderListener;
import com.unisound.common.aa;
import com.unisound.common.ag;
import com.unisound.common.ao;
import com.unisound.common.au;
import com.unisound.common.av;
import com.unisound.common.ba;
import com.unisound.common.i;
import com.unisound.common.v;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bg extends o {
    private static final int O = 1;
    private static final int P = 5;
    private static final int Q = 6;
    private static final int R = 7;
    private static final int S = 11;
    private static final int T = 12;
    private static final int U = 13;
    private static final int V = 14;
    private static final int W = 15;
    private static final int X = 16;
    private static final int Y = 17;
    private static final int Z = 18;
    private static String aA = "4bd9354d1cf247c93db388257567d0e2";
    private static final int aG = 0;
    private static final int aH = 1;
    private static final int aI = 2;
    private static final int aJ = 3;
    private static final int aa = 20;
    private static final int ab = 21;
    private static final int ac = 22;
    private static final int ad = 23;
    private static final int ae = 24;
    private static final int af = 25;
    private static final int ag = 30;
    private static final int ah = 40;
    private static final int ai = 50;
    private cl A;
    private y B = null;
    private boolean C = true;
    private boolean D = true;
    private String E = "";
    private int F = 1;
    private int G = -1;
    private aw H = new aw();
    private String I = "main";
    private String J = "wakeup";
    private int K = 0;
    private boolean L = false;
    private boolean M = false;
    private boolean N = false;
    private String aB = "";
    public ac aC = new bh(this);
    public ag aD;
    private boolean aE = false;
    public ad aF = new bk(this);
    public a aK;
    public Handler aL;
    public Runnable aM = new bn(this);
    public ao aj;
    public Context ak;
    public HandlerThread al;
    public Handler am;
    public Handler an;
    public int ao;
    public int ap = 5;
    public boolean aq = false;
    public String ar = "";
    public boolean as = false;
    public boolean at = false;
    public boolean au = false;
    public boolean av = false;
    public Object aw = new Object();
    public h ax;
    public g ay;
    public ba az = ba.a();
    protected com.unisound.sdk.ao o;
    protected cw p = new cw();
    protected final int q = 51;
    protected final int r = 52;
    protected final int s = 53;
    protected final int t = 54;
    ArrayList u;
    ArrayList v;
    ArrayList w;
    String x;
    String y;
    private SpeechUnderstanderListener z;

    protected bg(Context var1, String var2, String var3) {
        super(var1, var2);
        this.ak = var1;
        this.x = var2;
        this.y = var3;
        this.aD = new ag(var1);
        this.p.a(this.f.a);
        this.f.a(this.aC);
        cl var4 = this.b.aX();
        this.A = var4;
        var4.b(var2);
        this.A.c(var3);
        this.b.o(var2);
        this.b.O(1);
        this.o = new com.unisound.sdk.ao(this.b, this.mLooper);
        this.L = false;
        this.u = new ArrayList();
        this.v = new ArrayList();
        this.w = new ArrayList();
        ao var5 = new ao(new bi(this), this.mLooper);
        this.aj = var5;
        var5.c();
        this.ax = new h(this);
        this.ay = this.f.h();
    }

    private void C() {
        SpeechUnderstanderListener var1;
        if (this.aD.a("android.permission.RECORD_AUDIO") != 0) {
            com.unisound.common.y.a("no RECORD_AUDIO permission");
            var1 = this.z;
            if (var1 != null) {
                var1.onEvent(1159, (int) System.currentTimeMillis());
            }
        }

        if (this.aD.a("android.permission.READ_CONTACTS") != 0) {
            com.unisound.common.y.a("no READ_CONTACTS permission");
            var1 = this.z;
            if (var1 != null) {
                var1.onEvent(1158, (int) System.currentTimeMillis());
            }
        }

        if (this.aD.a("android.permission.ACCESS_FINE_LOCATION") != 0) {
            com.unisound.common.y.a("no ACCESS_FINE_LOCATION permission");
            var1 = this.z;
            if (var1 != null) {
                var1.onEvent(1153, (int) System.currentTimeMillis());
            }
        }

        if (this.aD.a("android.permission.READ_PHONE_STATE") != 0) {
            com.unisound.common.y.a("no READ_PHONE_STATE permission");
            var1 = this.z;
            if (var1 != null) {
                var1.onEvent(1151, (int) System.currentTimeMillis());
            }
        }

        if (this.aD.a("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            com.unisound.common.y.a("no WRITE_EXTERNAL_STORAGE permission");
            var1 = this.z;
            if (var1 != null) {
                var1.onEvent(1154, (int) System.currentTimeMillis());
            }
        }

    }

    private boolean D() {
        return this.A.y();
    }

    private int E() {
        return this.A.z();
    }

    private boolean F() {
        return this.C;
    }

    private boolean G() {
        return this.D;
    }

    private av H() {
        return this.o.p();
    }

    private au I() {
        return this.o.h();
    }

    private void J() {
        if (this.F() && this.G()) {
            label26:
            {
                int var1 = this.F;
                Handler var2;
                String var3;
                if (var1 == 0) {
                    var2 = this.am;
                    var3 = com.unisound.common.v.a(this.u, this.v, this.w);
                } else if (var1 == 2 && !this.I.equals(this.J)) {
                    var2 = this.am;
                    var3 = com.unisound.common.v.a(this.u, (ArrayList) null, (ArrayList) null);
                } else {
                    if (this.F != 1) {
                        break label26;
                    }

                    var2 = this.am;
                    var3 = com.unisound.common.v.a((ArrayList) null, this.v, this.w);
                }

                var2.obtainMessage(1210, var3).sendToTarget();
            }

            this.au = true;
            this.stop();
            if (this.av) {
                this.am.sendEmptyMessage(5);
            }
        }

    }

    private void K() {
        if (this.v.size() == 0) {
            this.v.add(com.unisound.common.v.a(this.F, "full", "", "", true, (Object) null, (Object) null, (Object) null, (Object) null, (Object) null, (Object) null));
        }

        if (this.A.w() && this.w.size() == 0) {
            this.w.add(com.unisound.common.v.a(-1, (String) null, "", (String) null, (Object) null, (Object) null, (Object) null, (Object) null, (Object) null, (Object) null, (Object) null));
        }

        if (this.u.size() == 0) {
            this.u.add(com.unisound.common.v.a(this.F, "full", "", (String) null, (Object) null, -20.0F, (Object) null, (Object) null, (Object) null, (Object) null, (Object) null));
        }

    }

    private int L() {
        byte var2;
        if (!this.L) {
            StringBuilder var1 = new StringBuilder();
            var1.append("init error ");
            var1.append(ErrorCode.toJsonMessage(-64001));
            com.unisound.common.y.a(var1.toString());
            SpeechUnderstanderListener var3 = this.z;
            if (var3 != null) {
                var3.onError(1300, ErrorCode.toJsonMessage(-64001));
            }

            var2 = -1;
        } else {
            var2 = 0;
        }

        return var2;
    }

    private String M() {
        int var1 = this.u();
        String var2 = "";
        if (var1 != 1) {
            if (var1 != 2) {
                if (var1 == 3) {
                    StringBuilder var4 = new StringBuilder();
                    var4.append("");
                    var4.append(this.v());
                    var4.append("|");
                    String var3 = var4.toString();
                    var4 = new StringBuilder();
                    var4.append(var3);
                    var4.append(this.w());
                    var2 = var4.toString();
                }
            } else {
                var2 = this.w();
            }
        } else {
            var2 = this.v();
        }

        StringBuilder var5 = new StringBuilder();
        var5.append("commit=");
        var5.append(this.getFixEngineVersion());
        var5.append(";");
        var5.append("authorized_status=");
        var5.append(var1);
        var5.append(":");
        var5.append(var2);
        return var5.toString();
    }

    private boolean N() {
        boolean var1;
        if (this.b.c != null) {
            var1 = this.b.c.a();
        } else {
            var1 = false;
        }

        return var1;
    }

    private int O() {
        return this.b.ad();
    }

    private int P() {
        return this.b.ae();
    }

    public void Q() {
        Handler var1 = new Handler(this.mLooper);
        this.aL = var1;
        var1.removeCallbacks(this.aM);
        this.aL.postDelayed(this.aM, 43200000L);
        com.unisound.common.y.c(new Object[]{"USC", "refresh activate timer start"});
    }

    private void R() {
        if (this.ax.a()) {
            try {
                com.unisound.common.y.c(new Object[]{"USC", "unregiste network broadcastReceiver!"});
                this.ak.unregisterReceiver(this.ax);
                this.ax.b(false);
            } catch (Exception var2) {
                com.unisound.common.y.a("USC", new String[]{"unRegisteBroadcast exception"});
                var2.printStackTrace();
            }
        }

    }

    private int S() {
        byte var2;
        if (!this.ay.a()) {
            StringBuilder var1 = new StringBuilder();
            var1.append("init error ");
            var1.append(ErrorCode.toJsonMessage(-64002));
            com.unisound.common.y.a(var1.toString());
            SpeechUnderstanderListener var3 = this.z;
            if (var3 != null) {
                var3.onError(1300, ErrorCode.toJsonMessage(-64002));
            }

            var2 = -1;
        } else {
            var2 = 0;
        }

        return var2;
    }

    // $FF: synthetic method
    static int a(bg var0, List var1) {
        return var0.a(var1);
    }

    private int a(List var1) {
        co var2 = new co(this.l, this.x);
        int var3 = this.L();
        int var4 = -63406;
        if (var3 != 0) {
            var3 = -64001;
        } else {
            int var5 = var1.size();
            if (var5 > this.ap) {
                var3 = -63402;
            } else {
                var3 = var4;
                if (var1 != null) {
                    var3 = var4;
                    if (var5 > 0) {
                        var3 = 0;

                        while (true) {
                            if (var3 >= var5) {
                                String var6 = var2.a(com.unisound.c.a.a(this.x), var2.a(var1));
                                var5 = var4;
                                if (var6.contains("status")) {
                                    try {
                                        JSONObject var7 = new JSONObject(var6);
                                        var5 = Integer.parseInt(var7.getString("status"));
                                    } catch (NumberFormatException var8) {
                                        var8.printStackTrace();
                                        var5 = var4;
                                    } catch (JSONException var9) {
                                        var9.printStackTrace();
                                        var5 = var4;
                                    }
                                }

                                var3 = var5;
                                if (var5 == 0) {
                                    HashSet var10 = new HashSet();
                                    var10.addAll(var1);
                                    var2.a(var10);
                                    var3 = var5;
                                }
                                break;
                            }

                            if (TextUtils.isEmpty((CharSequence) var1.get(var3))) {
                                var3 = -63407;
                                break;
                            }

                            if (co.a((String) var1.get(var3))) {
                                var3 = -63401;
                                break;
                            }

                            ++var3;
                        }
                    }
                }
            }
        }

        return var3;
    }

    // $FF: synthetic method
    static Handler a(bg var0) {
        return var0.an;
    }

    // $FF: synthetic method
    static String a(bg var0, String var1) {
        var0.aB = var1;
        return var1;
    }

    private void a(IntentFilter var1) {
        if (!this.ax.a()) {
            try {
                com.unisound.common.y.a("USC", new String[]{"registe network broadcastReceiver!"});
                this.ak.registerReceiver(this.ax, var1);
                this.ax.b(true);
            } catch (Exception var2) {
                com.unisound.common.y.a("USC", new String[]{"registeBroadcast exception"});
                var2.printStackTrace();
            }
        }

    }

    private void a(al var1) {
        ArrayList var2 = new ArrayList();
        b var3 = new b();
        var3.a(var1);
        var3.a(this.F);
        var3.a(false);
        var3.b(this.b.ao());
        var2.add(com.unisound.common.v.a(var3));
        this.am.obtainMessage(1201, com.unisound.common.v.a((ArrayList) null, var2, (ArrayList) null)).sendToTarget();
    }

    // $FF: synthetic method
    static void a(bg var0, int var1) {
        var0.r(var1);
    }

    // $FF: synthetic method
    static void a(bg var0, al var1) {
        var0.a(var1);
    }

    // $FF: synthetic method
    static void a(bg var0, String var1, String var2, String var3) {
        var0.d(var1, var2, var3);
    }

    private void a(String var1, int var2) {
        this.A.a(var1, var2);
    }

    private void a(String var1, String var2, String var3, String var4, String var5, String var6) {
        (new bl(this, var3, var4, var5, var6, var1, var2)).start();
    }

    private void a(JSONObject var1) {
        this.b.n(var1.toString());
        com.unisound.common.y.c(new Object[]{"USC", "activate params is ", var1.toString()});
        a var2 = com.unisound.b.a.a.a(this.ak, this.b.aV());
        this.aK = var2;
        var2.a(new bm(this));
    }

    // $FF: synthetic method
    static boolean a(bg var0, boolean var1) {
        var0.aE = var1;
        return var1;
    }

    // $FF: synthetic method
    static Context b(bg var0) {
        return var0.ak;
    }

    // $FF: synthetic method
    static String b(bg var0, String var1) {
        var0.E = var1;
        return var1;
    }

    private void b(al var1) {
        if (this.A.y() && this.A.w()) {
            String var2 = com.unisound.common.v.a(var1.a(), this.A.y(), this.b).a();
            if (var2 != null) {
                this.w.clear();
                this.w.add(var2);
                this.am.obtainMessage(1201, com.unisound.common.v.a((ArrayList) null, (ArrayList) null, this.w)).sendToTarget();
            }
        }

        if (var1.c()) {
            if (this.A.w()) {
                String var3 = com.unisound.common.v.a(var1.a(), this.A.y(), this.b).a();
                ArrayList var4 = this.w;
                if (var4 != null && var3 != null) {
                    var4.clear();
                    this.w.add(var3);
                    this.am.obtainMessage(1201, com.unisound.common.v.a((ArrayList) null, (ArrayList) null, this.w)).sendToTarget();
                } else {
                    var4 = this.w;
                    if (var4 != null && var3 == null) {
                        var4.clear();
                        this.q(-63551);
                    }
                }
            }

            this.b.k(System.currentTimeMillis());
        }

    }

    // $FF: synthetic method
    static void b(bg var0, int var1) {
        var0.q(var1);
    }

    // $FF: synthetic method
    static void b(bg var0, al var1) {
        var0.b(var1);
    }

    // $FF: synthetic method
    static boolean b(bg var0, boolean var1) {
        var0.C = var1;
        return var1;
    }

    // $FF: synthetic method
    static ao c(bg var0) {
        return var0.aj;
    }

    private void c(al var1) {
        if (var1.c() && !this.b.ao() && !var1.d()) {
            al var2 = new al();
            var2.a(var1.e());
            var2.a(com.unisound.common.v.b());
            var2.b(var1.b());
            var2.b(var1.d());
            var2.a(var1.c());
            b var3 = new b();
            var3.a(var2);
            var3.a(this.F);
            var3.a(true);
            var3.b(this.b.ao());
            this.v.add(com.unisound.common.v.a(var3));
            this.am.obtainMessage(1201, com.unisound.common.v.a((ArrayList) null, this.v, this.w)).sendToTarget();
        }

    }

    // $FF: synthetic method
    static void c(bg var0, al var1) {
        var0.c(var1);
    }

    // $FF: synthetic method
    static boolean c(bg var0, boolean var1) {
        var0.au = var1;
        return var1;
    }

    // $FF: synthetic method
    static Object d(bg var0) {
        return var0.aw;
    }

    public void d(String var1, String var2, String var3) {
        SharedPreferences var4 = this.l.getSharedPreferences(aA, 0);
        String var5 = var4.getString("deviceInfo", "");


        String result = null;
        if (TextUtils.isEmpty(var5)) {
            try {
                JSONObject var20 = new JSONObject();
                JSONObject var7 = new JSONObject();
                var7.put("appkey", var1);
                var7.put("udid", var2);
                var7.put("token", var3);
                JSONArray var17 = new JSONArray();
                var17.put(var7);
                var20.put("deviceInfo", var17);
                result = var20.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } else {
            try {
                JSONObject var7 = new JSONObject(var5);
                JSONArray var21 = var7.getJSONArray("deviceInfo");
                boolean needAdd = true;
                for (int i = 0; i < var21.length(); i++) {
                    JSONObject jsonObject = var21.getJSONObject(i);
                    if (var1.equals(jsonObject.get("appkey")) && var2.equals(jsonObject.get("udid"))) {
                        if(!TextUtils.isEmpty(var3)) jsonObject.put("token", var3);
                        needAdd = false;
                        break;
                    }
                }
                if (needAdd) {
                    JSONObject var9 = new JSONObject();
                    var9.put("appkey", var1);
                    var9.put("udid", var2);
                    var9.put("token", var3);
                    var21.put(var9);
                    var7.put("deviceInfo", var21);
                }

                result = var7.toString();
            } catch (Exception var13) {
                var13.printStackTrace();
                return;
            }
        }


        try {
            StringBuilder var18 = new StringBuilder();
            com.unisound.common.y.c("saveTokenInfo deviceContent =" + result);
            Editor var19 = var4.edit();
            var19.putString("deviceInfo", result);
            var19.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // $FF: synthetic method
    static boolean d(bg var0, boolean var1) {
        var0.av = var1;
        return var1;
    }

    // $FF: synthetic method
    static void e(bg var0) {
        var0.f();
    }

    private void e(String var1) {
        this.A.k(var1);
    }

    private void e(String var1, String var2) {
        JniUscClient var3 = new JniUscClient();
        com.unisound.common.a var4 = this.b.bb();
        long var5 = var3.a(var4.a(), var4.c());
        var3.a(9, var1);
        var3.a(204, var2);
        com.unisound.common.y.c(new Object[]{"SpeechUnderstanderInterface", "server :", var4.a(), " port: ", var4.c()});
        com.unisound.common.y.c(new Object[]{"SpeechUnderstanderInterface", "juc.create() returns ", var5});
    }

    private void e(boolean var1) {
        this.A.a(var1);
    }

    // $FF: synthetic method
    static boolean e(bg var0, boolean var1) {
        var0.at = var1;
        return var1;
    }

    // $FF: synthetic method
    static String f(bg var0) {
        return var0.aB;
    }

    private void f(String var1) {
        this.A.h(var1);
    }

    private void f(boolean var1) {
        this.A.b(var1);
    }

    private int g(boolean var1) {
        int var2;
        if (!this.f.g && !this.a(var1)) {
            com.unisound.common.y.a("loadModel::isInit=false");
            var2 = -1;
        } else {
            StringBuilder var3 = new StringBuilder();
            var3.append(this.f.a);
            var3.append("wakeup.dat");
            String var6 = var3.toString();
            StringBuilder var4 = new StringBuilder();
            var4.append(this.f.a);
            var4.append(this.f.d);
            var4.append(".dat");
            String var5 = var4.toString();
            var4 = new StringBuilder();
            var4.append(var6);
            var4.append(",");
            var4.append(var5);
            var2 = this.b(var4.toString(),"init_asr");
        }

        return var2;
    }

    private void g(String var1) {
        this.A.i(var1);
    }

    // $FF: synthetic method
    static boolean g(bg var0) {
        return var0.D;
    }

    // $FF: synthetic method
    static SpeechUnderstanderListener h(bg var0) {
        return var0.z;
    }

    private void h(String var1) {
        this.A.j(var1);
    }

    private void h(boolean var1) {
        this.b.o(var1);
    }

    // $FF: synthetic method
    static Handler i(bg var0) {
        return var0.am;
    }

    private void i(String var1) {
        this.A.e(var1);
    }

    private void i(boolean var1) {
        this.b.p(var1);
    }

    public static boolean isNetworkAvailable(Context var0) {
        boolean var1 = false;

        boolean var2;
        label51:
        {
            boolean var10001;
            ConnectivityManager var10;
            try {
                var10 = (ConnectivityManager) var0.getSystemService(Context.CONNECTIVITY_SERVICE);
            } catch (Exception var9) {
                var10001 = false;
                break label51;
            }

            if (var10 == null) {
                label69:
                {
                    try {
                        com.unisound.common.y.a("USC", new String[]{" isNetworkAvailable cm is null!"});
                    } catch (Exception var6) {
                        var10001 = false;
                        break label69;
                    }

                    var2 = var1;
                    return var2;
                }
            } else {
                label56:
                {
                    NetworkInfo[] var3;
                    try {
                        var3 = var10.getAllNetworkInfo();
                    } catch (Exception var8) {
                        var10001 = false;
                        break label56;
                    }

                    var2 = var1;
                    if (var3 == null) {
                        return var2;
                    }

                    int var4 = 0;

                    while (true) {
                        var2 = var1;

                        State var5;
                        State var11;
                        try {
                            if (var4 >= var3.length) {
                                return var2;
                            }

                            var5 = var3[var4].getState();
                            var11 = State.CONNECTED;
                        } catch (Exception var7) {
                            var10001 = false;
                            break;
                        }

                        if (var5 == var11) {
                            var2 = true;
                            return var2;
                        }

                        ++var4;
                    }
                }
            }
        }

        com.unisound.common.y.c(new Object[]{"SpeechUnderstander isNetworkAvailable error"});
        var2 = var1;
        return var2;
    }

    private String j(boolean var1) {
        return this.b.t(true);
    }

    // $FF: synthetic method
    static void j(bg var0) {
        var0.J();
    }

    private void j(String var1) {
        this.A.d(var1);
    }

    // $FF: synthetic method
    static String k(bg var0) {
        return var0.E;
    }

    private void k(String var1) {
        this.A.f(var1);
    }

    private void k(boolean var1) {
        this.b.u(var1);
    }

    // $FF: synthetic method
    static boolean l(bg var0) {
        return var0.as;
    }

    private boolean l(String var1) {
        boolean var2;
        if (this.b.r(var1)) {
            var2 = true;
        } else {
            StringBuilder var3 = new StringBuilder();
            var3.append("setNetEngine::error: unkown param ");
            var3.append(var1);
            com.unisound.common.y.a(var3.toString());
            var2 = false;
        }

        return var2;
    }

    private boolean l(boolean var1) {
        if (this.b.c != null) {
            this.b.c.h(var1);
            var1 = true;
        } else {
            var1 = false;
        }

        return var1;
    }

    // $FF: synthetic method
    static boolean m(bg var0) {
        return var0.au;
    }

    private boolean m(String var1) {
        boolean var2;
        if (this.b.s(var1)) {
            var2 = true;
        } else {
            StringBuilder var3 = new StringBuilder();
            var3.append("setNetEngineSubModel::error: unkown param ");
            var3.append(this.F);
            com.unisound.common.y.a(var3.toString());
            var2 = false;
        }

        return var2;
    }

    private void n(int var1) {
        this.A.a(var1);
    }

    // $FF: synthetic method
    static void n(bg var0) {
        var0.Q();
    }

    private void n(String var1) {
        this.b.p(var1);
    }

    private String o(String var1) {
        String var2 = "";
        String var3 = var2;
        if (var1 != null) {
            if (var1.equals("")) {
                var3 = var2;
            } else {
                var3 = new String(Base64.decode(var1, 0));
                com.unisound.common.y.c(new Object[]{"SpeechUnderstanderInterface -> getversion : SDK_Version = ", var3});
            }
        }

        return var3;
    }

    private void o(int var1) {
        this.b.P(var1);
    }

    // $FF: synthetic method
    static boolean o(bg var0) {
        return var0.at;
    }

    // $FF: synthetic method
    static a p(bg var0) {
        return var0.aK;
    }

    private void p(int var1) {
        this.o.c(var1);
    }

    private boolean p(String var1) {
        String var2 = com.unisound.common.aa.a();
        boolean var3 = true;
        boolean var4;
        if (var1 != null && var2 != null) {
            var4 = var3;
            if (var2.equals("appkey_md5")) {
                return var4;
            }

            String[] var5 = var2.split("#");
            String var6 = com.unisound.common.aa.a(var1);
            int var7 = var5.length;

            for (int var8 = 0; var8 < var7; ++var8) {
                String var9 = var5[var8];
                if (!TextUtils.isEmpty(var9)) {
                    StringBuilder var10 = new StringBuilder();
                    var10.append("checkAppkeyMd5 : value = ");
                    var10.append(var9);
                    var10.append(", appkey = ");
                    var10.append(var1);
                    var10.append(", appkeyMd5 = ");
                    var10.append(var6);
                    com.unisound.common.y.c(new Object[]{var10.toString()});
                    var4 = var3;
                    if (var9.equals(var6)) {
                        return var4;
                    }
                }
            }
        }

        var4 = false;
        return var4;
    }

    // $FF: synthetic method
    static g q(bg var0) {
        return var0.ay;
    }

    public void q(int var1) {
        Message var2 = new Message();
        var2.what = 54;
        var2.obj = var1;
        this.am.sendMessage(var2);
    }

    public void r(int var1) {
        Message var2 = new Message();
        var2.what = 54;
        var2.obj = var1;
        Handler var3 = this.an;
        if (var3 != null) {
            var3.sendMessage(var2);
        }

    }

    private void s(int var1) {
        this.b.v(var1);
    }

    protected int A() {
        return this.K;
    }

    protected String B() {
        return this.p.a();
    }

    protected void a(int var1) {
        Handler var2 = this.am;
        if (var2 != null) {
            var2.sendEmptyMessage(var1);
        }

    }

    protected void a(VAD var1) {
        if (!this.I.equals(this.J) || !this.b.at()) {
            this.am.sendEmptyMessage(18);
        }

    }

    protected void a(ay var1) {
        this.o.a(var1);
    }

    protected void a(String var1, boolean var2, int var3, int var4) {
        this.aE = true;
        if (this.z != null && this.a.a(var1, false, this.b.aH()) && this.mSpeechUnderstanderParams.i() != 1) {
            this.u.clear();

            for (int var5 = 0; var5 < this.a.a.size(); ++var5) {
                com.unisound.common.y.c(new Object[]{"SpeechUnderstanderInterface : recognizeResult.item = ", this.a.a.get(var5), " , times=  ", var5});
                ArrayList var6;
                int var7;
                String var8;
                Float var9;
                Integer var10;
                Integer var11;
                if (this.b.aO()) {
                    var6 = this.u;
                    var7 = this.F;
                    var8 = (String) this.a.a.get(var5);
                    var9 = this.a.e;
                    var10 = var3;
                    var11 = var4;
                } else {
                    var6 = this.u;
                    var7 = this.F;
                    var8 = (String) this.a.a.get(var5);
                    var9 = this.a.e;
                    var10 = null;
                    var11 = null;
                }

                var6.add(com.unisound.common.v.a(var7, "full", var8, (String) null, (Object) null, var9, (Object) null, (Object) null, (Object) null, var10, var11));
            }

            this.am.obtainMessage(1202, com.unisound.common.v.a(this.u, (ArrayList) null, (ArrayList) null)).sendToTarget();
        }

        this.aE = false;
    }

    protected void a(String var1, boolean var2, int var3, long var4, long var6, int var8, int var9) {
        com.unisound.common.y.c(new Object[]{"SpeechUnderstandInterface doWakeupResult => ", var1});
        this.aE = true;
        if (this.z != null && this.a.a(var1, true, this.b.aH())) {
            ArrayList var10 = new ArrayList();
            String var11;
            Float var12;
            Integer var13;
            Long var14;
            Long var15;
            Integer var16;
            Integer var17;
            if (this.b.aO()) {
                var11 = (String) this.a.a.get(0);
                var12 = this.a.e;
                var13 = var3;
                var14 = var4;
                var15 = var6;
                var16 = var8;
                var17 = var9;
            } else {
                var11 = (String) this.a.a.get(0);
                var12 = this.a.e;
                var13 = var3;
                var14 = var4;
                var15 = var6;
                var16 = null;
                var17 = null;
            }

            var10.add(com.unisound.common.v.a(1000, "full", var11, (String) null, (Object) null, var12, var13, var14, var15, var16, var17));
            this.am.obtainMessage(3201, com.unisound.common.v.a(var10, (ArrayList) null, (ArrayList) null)).sendToTarget();
            this.am.sendEmptyMessage(1);
        }

        this.aE = false;
    }

    protected void a(boolean var1, byte[] var2, int var3, int var4) {
        super.a(var1, var2, var3, var4);
        if (this.B != null) {
            this.o.b(var1, var2, var3, var4);
        }

        this.H.a(var1, var4);
    }

    protected void b(int var1) {
        if (var1 == -61002) {
            com.unisound.sdk.ao var2 = this.o;
            if (var2 != null) {
                var2.d();
            }
        }

        this.aE = true;
        if (this.z != null) {
            if (var1 != 0) {
                this.q(var1);
            }

            this.D = true;
            this.am.sendEmptyMessage(6);
            this.J();
        }

        this.aE = false;
    }

    protected void b(int var1, int var2) {
        Handler var3 = this.an;
        if (var3 != null) {
            var3.sendEmptyMessage(var1);
        }

    }

    protected int c(String var1) {
        return 0;
    }

    protected int c(String var1, String var2) {
        return 0;
    }

    protected int c(String var1, String var2, String var3) {
        return 0;
    }

    protected void c(int var1) {
        this.am.sendEmptyMessage(17);
        this.mSpeechUnderstanderParams.b(var1);
    }

    protected void c(int var1, int var2) {
        super.c(var1, var2);
        Message var3 = new Message();
        var3.what = var1;
        var3.obj = var2;
        Handler var4 = this.an;
        if (var4 != null) {
            var4.sendMessage(var3);
        }

    }

    protected void cancel() {
        // $FF: Couldn't be decompiled
    }

    public String convertToArabicNumber(String var1) {
        try {
            var1 = com.unisound.common.i.b(var1);
        } catch (Exception var2) {
            var2.printStackTrace();
            var1 = null;
        }

        return var1;
    }

    protected int d(String var1) {
        return 0;
    }

    protected int d(String var1, String var2) {
        return 0;
    }

    protected void d(int var1) {
        super.d(var1);
        this.K = var1;
    }

    protected boolean d(boolean var1) {
        this.b.L(var1);
        return true;
    }

    public void destoryCompiler() {
        if (!this.b.aJ()) {
            this.ay.b();
        }

    }

    protected int e(int var1) {
        return super.e(var1);
    }

    protected int f(int var1) {
        return super.f(var1);
    }

    protected String getFixEngineVersion() {
        String var1;
        if (this.F != 1) {
            var1 = super.getFixEngineVersion();
        } else {
            var1 = "";
        }

        return var1;
    }

    protected Object getOption(int var1) {
        Object var2;
        boolean var3;
        label164:
        {
            switch (var1) {
                case 1001:
                    var1 = this.mSpeechUnderstanderParams.i();
                    break;
                case 1003:
                    var3 = this.mSpeechUnderstanderParams.c();
                    break label164;
                case 1004:
                    var2 = this.mSpeechUnderstanderParams.b();
                    return var2;
                case 1008:
                    var2 = this.mSpeechUnderstanderParams.d();
                    return var2;
                case 1009:
                    var2 = this.mSpeechUnderstanderParams.a();
                    return var2;
                case 1010:
                    var1 = this.b.u();
                    break;
                case 1011:
                    var1 = this.b.v();
                    break;
                case 1012:
                    var2 = this.b.bj();
                    return var2;
                case 1014:
                    var1 = this.o.b();
                    break;
                case 1020:
                    var3 = this.A.w();
                    break label164;
                case 1021:
                    var2 = this.A.n();
                    return var2;
                case 1023:
                    var2 = this.A.x();
                    return var2;
                case 1030:
                    var2 = this.A.i();
                    return var2;
                case 1031:
                    var2 = this.A.j();
                    return var2;
                case 1032:
                    var2 = this.A.m();
                    return var2;
                case 1036:
                    var2 = com.unisound.c.a.a(this.x);
                    return var2;
                case 1044:
                    var1 = this.b.f();
                    break;
                case 1045:
                    var1 = this.mSpeechUnderstanderParams.h();
                    break;
                case 1069:
                    var1 = this.b.I();
                    break;
                case 1076:
                    var3 = this.b.aH;
                    break label164;
                case 1077:
                    var2 = this.b.ax();
                    return var2;
                case 1078:
                    var2 = this.b.K();
                    return var2;
                case 1082:
                    var1 = this.b.aQ();
                    break;
                case 1083:
                    var1 = this.b.ay();
                    break;
                case 1084:
                    var1 = this.b.aA();
                    break;
                case 1086:
                    var2 = this.b.az();
                    return var2;
                case 1087:
                    var2 = this.mSpeechUnderstanderParams.k();
                    return var2;
                case 1088:
                    var2 = this.M();
                    return var2;
                case 1090:
                case 10011:
                    var1 = this.x();
                    break;
                case 1094:
                    var1 = this.ap;
                    break;
                case 3150:
                    var2 = this.b.E();
                    return var2;
                case 10005:
                    var2 = this.f.g();
                    return var2;
                case 10006:
                    String var4 = this.x;
                    var2 = com.unisound.c.a.a(var4, com.unisound.c.a.a(var4), this.ak);
                    return var2;
                case 10021:
                    var3 = this.D();
                    break label164;
                case 10034:
                    var3 = this.b.bx();
                    break label164;
                case 10196:
                    var1 = this.O();
                    break;
                case 10197:
                    if (this.b.c != null && this.b.c.s()) {
                        var2 = this.j(true);
                        return var2;
                    }

                    var1 = this.P();
                    break;
                case 10199:
                    var3 = this.N();
                    break label164;
                default:
                    var2 = super.getOption(var1);
                    return var2;
            }

            var2 = var1;
            return var2;
        }

        var2 = var3;
        return var2;
    }

    protected String getVersion() {
        AssetManager var1 = this.l.getAssets();
        StringBuffer var2 = new StringBuffer();

        String var11;
        IOException var10000;
        label35:
        {
            InputStream var3;
            BufferedReader var9;
            boolean var10001;
            try {
                var3 = var1.open("version/data");
                InputStreamReader var4 = new InputStreamReader(var3);
                var9 = new BufferedReader(var4);
            } catch (IOException var8) {
                var10000 = var8;
                var10001 = false;
                break label35;
            }

            while (true) {
                String var12;
                try {
                    var12 = var9.readLine();
                } catch (IOException var6) {
                    var10000 = var6;
                    var10001 = false;
                    break;
                }

                if (var12 == null) {
                    try {
                        var11 = this.o(var2.toString());
                        var3.close();
                        var9.close();
                        return var11;
                    } catch (IOException var5) {
                        var10000 = var5;
                        var10001 = false;
                        break;
                    }
                }

                var2.append(var12);
            }
        }

        IOException var10 = var10000;
        var10.printStackTrace();
        var11 = super.getVersion();
        return var11;
    }

    protected void h() {
        this.am.sendEmptyMessage(13);
    }

    protected void i() {
        this.am.sendEmptyMessage(20);
    }

    protected void init(String var1) {
        this.C();

        SpeechUnderstanderListener var2;
        try {
            com.unisound.c.a.a(this.ak);
        } catch (Exception var12) {
            var2 = this.z;
            if (var2 != null) {
                var2.onError(1300, ErrorCode.toJsonMessage(-68001));
            }
        }

        int var3 = this.G;
        if (var3 != -1 && var3 != 2 || this.F != 2) {
            try {
                this.o.a(this.ak, this.aF);
            } catch (Exception var11) {
                var2 = this.z;
                if (var2 != null) {
                    var2.onError(1300, ErrorCode.toJsonMessage(-68001));
                }
            }

            JSONObject var16 = new JSONObject();

            label84:
            {
                Exception var10000;
                label110:
                {
                    boolean var10001;
                    try {
                        var16.put("appKey", this.x);
                        var16.put("appSecret", this.y);
                    } catch (Exception var10) {
                        var10000 = var10;
                        var10001 = false;
                        break label110;
                    }

                    if (var1 == null) {
                        break label84;
                    }

                    JSONObject var4;
                    Iterator var5;
                    try {
                        if (!var1.contains("activate")) {
                            break label84;
                        }

                        var4 = new JSONObject(var1);
                        var5 = var4.keys();
                    } catch (Exception var9) {
                        var10000 = var9;
                        var10001 = false;
                        break label110;
                    }

                    try {
                        String var6;
                        String var7;
                        do {
                            if (!var5.hasNext()) {
                                break label84;
                            }

                            var6 = (String) var5.next();
                            var7 = var4.getString(var6);
                            var16.put(var6, var7);
                        } while (!var6.equals("deviceSn") || com.unisound.c.a.b(var7) != -1);

                        this.q(-69002);
                        return;
                    } catch (Exception var8) {
                        var10000 = var8;
                        var10001 = false;
                    }
                }

                Exception var19 = var10000;
                var19.printStackTrace();
            }

            this.a(var16);
            this.refreshActivate();
        }

        String var17 = var1;
        if (var1 == null) {
            var17 = "";
        }

        if (var17 != "" && !var17.contains("activate")) {
            Map var18 = com.unisound.common.v.a(var17, this.mSpeechUnderstanderParams.l());
            Iterator var13 = var18.keySet().iterator();

            while (var13.hasNext()) {
                var3 = (Integer) var13.next();
                if (var18.get(var3) != null) {
                    this.setOption(var3, var18.get(var3));
                }
            }
        } else {
            com.unisound.common.y.c(new Object[]{"SpeechUnderStanderInterface : init json is an empty string!"});
        }

        bp var15;
        if (this.aq) {
            HandlerThread var14 = new HandlerThread("ht_outer");
            this.al = var14;
            var14.start();
            this.am = new bp(this, this.al.getLooper());
            var15 = new bp(this, this.al.getLooper());
        } else {
            this.am = new bp(this, this.ak.getMainLooper());
            var15 = new bp(this, this.ak.getMainLooper());
        }

        this.an = var15;
        this.z();
    }

    public void initCompiler() {
        if (!this.b.aJ() && !this.ay.a()) {
            (new bo(this)).start();
        }

    }

    public int insertVocab(List<String> var1, String var2) {
        int var3 = this.L();
        int var4 = 0;
        if (var3 != 0) {
            var4 = -64001;
        } else if (this.S() != 0) {
            var4 = -64002;
        } else if (var1 != null && var1.size() >= 1) {
            if (var2 != "" && var2.contains("#") && var2.split("#").length == 2) {
                String[] var5 = var2.split("#");
                var2 = var5[0];
                String var6 = var5[1];
                com.unisound.common.y.c(new Object[]{"SpeechUnderstanderInterface :", "inserVocab --> modelTag = ", var2, ", tagName = ", var6});
                this.a("command", var2, this.f.b(var2), this.f.f(var2), this.f.a(var6, var1), this.f.g(var2));
            } else {
                var4 = -63004;
            }
        } else {
            var4 = -63005;
        }

        return var4;
    }

    public int insertVocab(Map var1, String var2) {
        int var3;
        if (this.L() != 0) {
            var3 = -64001;
        } else if (this.S() != 0) {
            var3 = -64002;
        } else if (var1 != null && var2 != "") {
            StringBuffer var4 = new StringBuffer();
            Iterator var7 = var1.entrySet().iterator();

            while (var7.hasNext()) {
                Entry var5 = (Entry) var7.next();
                StringBuilder var6 = new StringBuilder();
                var6.append(this.f.a((String) var5.getKey(), (List) var5.getValue()));
                var6.append("\n");
                var4.append(var6.toString());
            }

            this.a("command", var2, this.f.b(var2), this.f.f(var2), var4.toString(), this.f.g(var2));
            var3 = 0;
        } else {
            com.unisound.common.y.a("SpeechUnderstanderInterface : insertVocab parmas error!");
            var3 = -1;
        }

        return var3;
    }

    public int insertVocab_ext(String var1, String var2, String var3) {
        int var4;
        if (this.L() != 0) {
            var4 = -64001;
        } else if (this.S() != 0) {
            var4 = -64002;
        } else {
            (new bj(this, var1, var2, var3)).start();
            var4 = 0;
        }

        return var4;
    }

    protected int insertVocab_ext(String var1, String var2, Map var3) {
        var3.entrySet();
        StringBuffer var4 = new StringBuffer();
        Iterator var5 = var3.entrySet().iterator();

        while (var5.hasNext()) {
            Entry var6 = (Entry) var5.next();
            StringBuilder var7 = new StringBuilder();
            var7.append(this.f.a((String) var6.getKey(), (List) var6.getValue()));
            var7.append("\n");
            var4.append(var7.toString());
        }

        String var8 = var4.toString();
        com.unisound.common.y.c(new Object[]{"SpeechUnderstanderInterface --> insertVocab_ext2 : vocabContent = ", var8});
        return this.insertVocab_ext(var1, var2, var8);
    }

    protected void j() {
        if (this.F != 2 && this.b.M()) {
            this.b.F(com.unisound.c.a.a(this.x, this.ak));
            this.o.c();
        }

        if (this.F == 1 && this.b.M()) {
            e(this);
        }

    }

    protected void l() {
        this.o.d();
        this.am.sendEmptyMessage(12);
    }

    protected void l(int var1) {
        Handler var2 = this.am;
        if (var2 == null) {
            com.unisound.common.y.a("SpeechUnderstander -> doUploadUserData handler is null");
        } else if (var1 == 0) {
            var2.sendEmptyMessage(16);
        } else {
            this.q(var1);
        }

    }

    protected int loadCompiledJsgf(String var1, String var2) {
        int var3;
        if (this.L() != 0) {
            var3 = -64001;
        } else {
            var3 = this.f.a(var1, var2);
        }

        return var3;
    }

    protected int loadGrammar(String var1, String var2) {
        int var3;
        if (this.L() != 0) {
            var3 = -64001;
        } else {
            this.b(var2, "command", var1);
            var3 = 0;
        }

        return var3;
    }

    protected void m(int var1) {
        if (var1 != 0) {
            Message var2 = new Message();
            var2.what = 53;
            var2.obj = var1;
            this.am.sendMessage(var2);
        }

    }

    protected void n() {
        if (this.D && this.C) {
            this.e.a(false);
            this.o.c(false);
            com.unisound.common.y.a("SpeechUnderstander fixend&netend doRecordingStart cancel");
        } else {
            SpeechUnderstanderListener var1 = this.z;
        }

    }

    protected void o() {
    }

    protected void p() {
        this.am.sendEmptyMessage(14);
    }

    public void postRecordingStartStatus() {
        super.postRecordingStartStatus();
        this.am.sendEmptyMessage(11);
    }

    public void refreshActivate() {
        synchronized (bg.class) {

            try {
                com.unisound.common.y.c(new Object[]{"USC", "refreshActivate called!"});
                if (!isNetworkAvailable(this.ak)) {
                    com.unisound.common.y.a("USC", new String[]{"refreshActivate network not available!"});
                    IntentFilter var1 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
                    this.ax.a(true);
                    this.a(var1);
                } else {
                    this.ax.a(false);
                    this.R();
                    String var4 = com.unisound.c.a.a(this.x, com.unisound.c.a.a(this.x), this.l);
                    if (!var4.equals("")) {
                        this.at = true;
                        this.aK.q(var4);
                        this.aK.a(1);
                    } else {
                        this.aK.a(0);
                    }
                }
            } finally {

            }
        }
    }

    protected int release(int var1, String var2) {
        Handler var5 = this.aL;
        if (var5 != null) {
            var5.removeCallbacks(this.aM);
        }

        this.R();
        if (this.n != null) {
            this.n.getLooper().quit();
        }

        if (this.mSpeechUnderstanderParams.o()) {
            this.b.c.r();
        }

        byte var3 = 0;
        byte var4;
        if (var1 == 1401 && this.M) {
            this.M = false;
            super.q();
            var4 = var3;
        } else {
            var4 = -1;
        }

        return var4;
    }

    protected int setAudioSource(IAudioSource var1) {
        return super.setAudioSource(var1);
    }

    protected void setListener(SpeechUnderstanderListener var1) {
        this.z = var1;
    }

    protected String setOnlineWakeupWord(List var1) {
        (new bq(this)).execute(new List[]{var1});
        return "";
    }

    protected void setOption(int var1, Object var2) {
        label1071:
        {
            String var114;
            if (var1 != 1003) {
                if (var1 == 1004) {
                    try {
                        var114 = (String) var2;
                        this.n(var114);
                        this.mSpeechUnderstanderParams.b(var114);
                        return;
                    } catch (Exception var111) {
                        var114 = "set asr_language Error.";
                    }
                } else if (var1 != 1024) {
                    if (var1 == 1025) {
                        try {
                            this.k(String.valueOf(var2));
                            return;
                        } catch (Exception var109) {
                            var114 = "set nlu_appver Error.";
                        }
                    } else if (var1 != 1060) {
                        if (var1 == 1061) {
                            try {
                                this.mSpeechUnderstanderParams.c((Boolean) var2);
                                return;
                            } catch (Exception var107) {
                                var114 = "set wakeup_vad_enable Error.";
                            }
                        } else if (var1 != 1203) {
                            label1072:
                            {
                                if (var1 == 1204) {
                                    try {
                                        this.b.T((Integer) var2);
                                    } catch (Exception var6) {
                                        com.unisound.common.y.a("set ASR_DOMAINS_PENALTY Error.");
                                    }
                                    break label1071;
                                }

                                byte var3 = -1;
                                boolean var10001;
                                switch (var1) {
                                    case 1001:
                                        label751:
                                        {
                                            try {
                                                var1 = (Integer) var2;
                                            } catch (Exception var80) {
                                                var10001 = false;
                                                break label751;
                                            }

                                            if (var1 != 0 && var1 != 1 && var1 != 2) {
                                                try {
                                                    StringBuilder var115 = new StringBuilder();
                                                    var115.append("USCMixRecognizer.setOption unkown value ");
                                                    var115.append(var1);
                                                    com.unisound.common.y.a(var115.toString());
                                                    return;
                                                } catch (Exception var76) {
                                                    var10001 = false;
                                                }
                                            } else {
                                                label757:
                                                {
                                                    label625:
                                                    {
                                                        try {
                                                            if (this.F == var1) {
                                                                break label625;
                                                            }

                                                            if (this.G == -1) {
                                                                this.L = false;
                                                            }
                                                        } catch (Exception var79) {
                                                            var10001 = false;
                                                            break label757;
                                                        }

                                                        try {
                                                            this.F = var1;
                                                        } catch (Exception var78) {
                                                            var10001 = false;
                                                            break label757;
                                                        }
                                                    }

                                                    try {
                                                        this.mSpeechUnderstanderParams.c(var1);
                                                        return;
                                                    } catch (Exception var77) {
                                                        var10001 = false;
                                                    }
                                                }
                                            }
                                        }

                                        var114 = "set asr_service_mode Error.";
                                        break label1072;
                                    case 1014:
                                        try {
                                            this.p((Integer) var2);
                                            return;
                                        } catch (Exception var75) {
                                            var114 = "set asr_net_timeOut Error.";
                                            break label1072;
                                        }
                                    case 1044:
                                        try {
                                            this.o((Integer) var2);
                                            return;
                                        } catch (Exception var74) {
                                            var114 = "set asr_sampling_rate Error.";
                                            break label1072;
                                        }
                                    case 1087:
                                        try {
                                            var114 = (String) var2;
                                            this.m(var114);
                                            this.mSpeechUnderstanderParams.g(var114);
                                            return;
                                        } catch (Exception var73) {
                                            var114 = "set asr_subdomain Error.";
                                            break label1072;
                                        }
                                    case 1089:
                                        try {
                                            var114 = (String) var2;
                                            this.b.m(var114);
                                            return;
                                        } catch (Exception var72) {
                                            var114 = "set ASR_OPT_ACTIVATE_MEMO Error.";
                                            break label1072;
                                        }
                                    case 1090:
                                    case 10011:
                                        try {
                                            var1 = (Integer) var2;
                                            this.b.n(var1);
                                            this.b.o(var1);
                                            return;
                                        } catch (Exception var71) {
                                            var114 = "set WAKEUP_WORK_ENGINE Error.";
                                            break label1072;
                                        }
                                    case 1091:
                                        try {
                                            this.b.G((Integer) var2);
                                            return;
                                        } catch (Exception var70) {
                                            var114 = "set WUW_WAKEUP_THRESHOLD Error";
                                            break label1072;
                                        }
                                    case 1092:
                                        try {
                                            this.b.i((String) var2);
                                            return;
                                        } catch (Exception var69) {
                                            var114 = "set ASR_OPT_IGNORE_RESULT_TAG Error.";
                                            break label1072;
                                        }
                                    case 1093:
                                        try {
                                            this.b.x((Boolean) var2);
                                            return;
                                        } catch (Exception var68) {
                                            var114 = "set ASR_OPT_CONTINUE_RECOGNIZE Error.";
                                            break label1072;
                                        }
                                    case 1094:
                                        try {
                                            this.ap = (Integer) var2;
                                            return;
                                        } catch (Exception var67) {
                                            var114 = "set ASR_OPT_MAX_ONLINE_WAKEUPWORDS_NUM Error.";
                                            break label1072;
                                        }
                                    case 1095:
                                        try {
                                            this.b.I((Boolean) var2);
                                            return;
                                        } catch (Exception var66) {
                                            var114 = "set setWxServiceEnabled Error.";
                                            break label1072;
                                        }
                                    case 1096:
                                        try {
                                            this.b.D((String) var2);
                                            return;
                                        } catch (Exception var65) {
                                            var114 = "set ASR_OPT_FILTERURL Error.";
                                            break label1072;
                                        }
                                    case 1097:
                                        try {
                                            this.b.E((String) var2);
                                            return;
                                        } catch (Exception var64) {
                                            var114 = "set ASR_OPT_SUB_SERVICE_PARAM Error.";
                                            break label1072;
                                        }
                                    case 1098:
                                        try {
                                            this.b.A((String) var2);
                                            return;
                                        } catch (Exception var63) {
                                            var114 = "set ASR_OPT_SERVICE_PARAMETER Error";
                                            break label1072;
                                        }
                                    case 1099:
                                        try {
                                            this.b.y((Integer) var2);
                                            return;
                                        } catch (Exception var62) {
                                            var114 = "set wakeup buffer length error!";
                                            break label1072;
                                        }
                                    case 1100:
                                        try {
                                            this.b.R((Integer) var2);
                                            return;
                                        } catch (Exception var61) {
                                            var114 = "set ASR_OPT_REQ_NLU_LENGTH Error.";
                                            break label1072;
                                        }
                                    case 1200:
                                        try {
                                            this.b.R((Boolean) var2);
                                            return;
                                        } catch (Exception var60) {
                                            var114 = "set ASR_BEST_RESULT_RETURN Error.";
                                            break label1072;
                                        }
                                    case 3150:
                                        try {
                                            this.b.a((Float) var2);
                                            return;
                                        } catch (Exception var59) {
                                            var114 = "set wakeup_threshold_value Error.";
                                            break label1072;
                                        }
                                    case 6000:
                                        try {
                                            this.b.B((String) var2);
                                            return;
                                        } catch (Exception var58) {
                                            var114 = "set UploadUserDataServer Error.";
                                            break label1072;
                                        }
                                    case 6001:
                                        try {
                                            this.b.C((String) var2);
                                            return;
                                        } catch (Exception var57) {
                                            var114 = "set setUploadUserDataServerUrl Error.";
                                            break label1072;
                                        }
                                    case 9000:
                                        try {
                                            this.b.n((Boolean) var2);
                                            return;
                                        } catch (Exception var56) {
                                            var114 = "set ContinueReadData Error.";
                                            break label1072;
                                        }
                                    case 9001:
                                        try {
                                            this.b.l((Integer) var2);
                                            return;
                                        } catch (Exception var55) {
                                            var114 = "set OneshotRecognitionBacksil Error.";
                                            break label1072;
                                        }
                                    case 10001:
                                        try {
                                            this.b.z((String) var2);
                                            return;
                                        } catch (Exception var54) {
                                            var114 = "set ASR_OPT_SGN_SETTING Error";
                                            break label1072;
                                        }
                                    case 10002:
                                        label749:
                                        {
                                            try {
                                                var1 = (Integer) var2;
                                            } catch (Exception var53) {
                                                var10001 = false;
                                                break label749;
                                            }

                                            if (var1 < 0) {
                                                var1 = var3;
                                            } else if (var1 > 2) {
                                                var1 = 1;
                                            }

                                            try {
                                                this.G = var1;
                                                return;
                                            } catch (Exception var52) {
                                                var10001 = false;
                                            }
                                        }

                                        var114 = "set ASR_OPT_INIT_MODE Error.";
                                        break label1072;
                                    case 10003:
                                        try {
                                            this.f.a((Integer) var2);
                                            return;
                                        } catch (Exception var51) {
                                            var114 = "set ASR_OPT_SET_COMPILE_MAX_PRONUNCIATION Error.";
                                            break label1072;
                                        }
                                    case 10004:
                                        try {
                                            if (this.f.a((Boolean) var2) != 0) {
                                                com.unisound.common.y.a("set ASR_OPT_SET_OVER_MAX_PRONUNCIATION_INSERT Error.");
                                                return;
                                            }
                                        } catch (Exception var7) {
                                            com.unisound.common.y.a("set ASR_OPT_SET_OVER_MAX_PRONUNCIATION_INSERT Error.");
                                        }

                                        return;
                                    case 10008:
                                        try {
                                            this.b.I((Integer) var2);
                                            return;
                                        } catch (Exception var49) {
                                            var114 = "set ASR_OPT_WUW_NET1_THRESHOLD Error.";
                                            break label1072;
                                        }
                                    case 10009:
                                        try {
                                            this.b.J((Integer) var2);
                                            return;
                                        } catch (Exception var48) {
                                            var114 = "set ASR_OPT_WUW_NET2_THRESHOLD Error.";
                                            break label1072;
                                        }
                                    case 10010:
                                        try {
                                            this.b.K((Integer) var2);
                                            return;
                                        } catch (Exception var47) {
                                            var114 = "set ASR_OPT_WUW_ACTIVE_NET Error.";
                                            break label1072;
                                        }
                                    case 10012:
                                        return;
                                    case 10013:
                                        try {
                                            this.mSpeechUnderstanderParams.d((Boolean) var2);
                                            return;
                                        } catch (Exception var46) {
                                            var114 = "set ASR_OPT_RECOGNIZE_VAD_ENABLE Error.";
                                            break label1072;
                                        }
                                    case 10014:
                                        try {
                                            this.b.H((String) var2);
                                            return;
                                        } catch (Exception var45) {
                                            var114 = "set ASR_OPT_USER_ID Error.";
                                            break label1072;
                                        }
                                    case 10019:
                                        try {
                                            this.b.y((Boolean) var2);
                                            return;
                                        } catch (Exception var44) {
                                            var114 = "set ASR_OPT_RETURN_ORIGIN_FORMAT Error.";
                                            break label1072;
                                        }
                                    case 10021:
                                        try {
                                            this.f((Boolean) var2);
                                            return;
                                        } catch (Exception var43) {
                                            var114 = "set ASR_OPT_USE_PATIAL_NLU Error.";
                                            break label1072;
                                        }
                                    case 10022:
                                        try {
                                            this.n((Integer) var2);
                                            return;
                                        } catch (Exception var42) {
                                            var114 = "set ASR_OPT_APPEND_LENGTH Error.";
                                            break label1072;
                                        }
                                    case 10023:
                                        try {
                                            this.b.E((Boolean) var2);
                                            return;
                                        } catch (Exception var41) {
                                            var114 = "set ASR_OPT_ADVANCE_INIT_COMPILER Error.";
                                            break label1072;
                                        }
                                    case 10030:
                                        try {
                                            this.b.g((String) var2);
                                            return;
                                        } catch (Exception var40) {
                                            var114 = "set ASR_OPT_AUDIO_FORMAT Error.";
                                            break label1072;
                                        }
                                    case 10031:
                                        try {
                                            this.b.A((Boolean) var2);
                                            return;
                                        } catch (Exception var39) {
                                            var114 = "set ASR_OPT_SAVE_SESSION_PCM_ENABLE Error.";
                                            break label1072;
                                        }
                                    case 10032:
                                        try {
                                            this.b.h((String) var2);
                                            return;
                                        } catch (Exception var38) {
                                            var114 = "set ASR_OPT_SAVE_SESSION_PCM_DIR Error.";
                                            break label1072;
                                        }
                                    case 10033:
                                        try {
                                            this.b.a((Boolean) var2);
                                            return;
                                        } catch (Exception var37) {
                                            var114 = "set ASR_OPT_RECORDING_WAV Error.";
                                            break label1072;
                                        }
                                    case 10034:
                                        try {
                                            this.b.T((Boolean) var2);
                                            return;
                                        } catch (Exception var36) {
                                            var114 = "set ASR_OPT_PUNCTUATED Error.";
                                            break label1072;
                                        }
                                    case 10035:
                                        try {
                                            this.b.U((Boolean) var2);
                                            return;
                                        } catch (Exception var35) {
                                            var10001 = false;
                                            break;
                                        }
                                    case 10036:
                                        try {
                                            this.b.V((Boolean) var2);
                                            return;
                                        } catch (Exception var34) {
                                            var10001 = false;
                                            break;
                                        }
                                    case 10037:
                                        try {
                                            this.b.L((Integer) var2);
                                            return;
                                        } catch (Exception var33) {
                                            var114 = "set ASR_OPT_MAX_WAKEUP_END Error.";
                                            break label1072;
                                        }
                                    case 10038:
                                        try {
                                            this.b.F((Boolean) var2);
                                            return;
                                        } catch (Exception var32) {
                                            var114 = "set ASR_OPT_INHIBIT_FRONT_WAKEUP Error.";
                                            break label1072;
                                        }
                                    case 10039:
                                        try {
                                            this.b.G((Boolean) var2);
                                            return;
                                        } catch (Exception var31) {
                                            var114 = "set ASR_OPT_INHIBIT_BACK_WAKEUP Error.";
                                            break label1072;
                                        }
                                    case 10040:
                                        try {
                                            this.b.H((Boolean) var2);
                                            return;
                                        } catch (Exception var30) {
                                            var114 = "set ASR_OPT_LOCAL_RESULT_CONTAINS_UTTERANCETIME Error.";
                                            break label1072;
                                        }
                                    case 10041:
                                        try {
                                            this.b.B((Boolean) var2);
                                            return;
                                        } catch (Exception var29) {
                                            var114 = "set ASR_OPT_VAD_AFFECT_ASR Error.";
                                            break label1072;
                                        }
                                    case 10042:
                                        try {
                                            this.b.N((Integer) var2);
                                            return;
                                        } catch (Exception var28) {
                                            var114 = "set ASR_OPT_INPUT_SAMPLE_RATE Error.";
                                            break label1072;
                                        }
                                    case 10043:
                                        try {
                                            this.b.M((Integer) var2);
                                            return;
                                        } catch (Exception var27) {
                                            var114 = "set ASR_OPT_RK_THREAD_NUM Error.";
                                            break label1072;
                                        }
                                    case 10060:
                                        try {
                                            this.b.S((Boolean) var2);
                                            return;
                                        } catch (Exception var26) {
                                            var114 = "set ASR_OPT_MAXWELL_ENABLE Error.";
                                            break label1072;
                                        }
                                    case 10061:
                                        try {
                                            this.b.I((String) var2);
                                            return;
                                        } catch (Exception var25) {
                                            var114 = "set ASR_OPT_RET_TYPE Error.";
                                            break label1072;
                                        }
                                    case 10062:
                                        try {
                                            this.b.z((Boolean) var2);
                                            return;
                                        } catch (Exception var24) {
                                            var114 = "set ASR_OPT_PRINT_NET_ENGINE_LOG Error.";
                                            break label1072;
                                        }
                                    case 10100:
                                        try {
                                            this.mSpeechUnderstanderParams.g((Boolean) var2);
                                            return;
                                        } catch (Exception var23) {
                                            var114 = "set ASR_FORMIC_CLOSE_4MICALGORITHM Error.";
                                            break label1072;
                                        }
                                    case 10101:
                                        try {
                                            this.b.q((Integer) var2);
                                            return;
                                        } catch (Exception var22) {
                                            var114 = "set ASR_FOURMIC_OUT_SET_4MICWAKEUPFLAG Error.";
                                            break label1072;
                                        }
                                    case 10102:
                                        try {
                                            this.b.s((Integer) var2);
                                            return;
                                        } catch (Exception var21) {
                                            var114 = "set ASR_FOURMIC_OUT_SET_4MICUTTERANCETIME Error.";
                                            break label1072;
                                        }
                                    case 10103:
                                        try {
                                            this.b.u((Integer) var2);
                                            return;
                                        } catch (Exception var20) {
                                            var114 = "set ASR_FOURMIC_OUT_SET_4MICDELAYTIME Error.";
                                            break label1072;
                                        }
                                    case 10104:
                                        try {
                                            this.b.q((Boolean) var2);
                                            return;
                                        } catch (Exception var19) {
                                            var114 = "set ASR_FOURMIC_OUT_SET_4MICWAKEUPSTATUS Error.";
                                            break label1072;
                                        }
                                    case 10105:
                                        try {
                                            this.b.r((Boolean) var2);
                                            return;
                                        } catch (Exception var18) {
                                            var114 = "set ASR_FOURMIC_OUT_SET_4MICUTTERANCETIMESTATUS Error.";
                                            break label1072;
                                        }
                                    case 10106:
                                        try {
                                            this.b.s((Boolean) var2);
                                            return;
                                        } catch (Exception var17) {
                                            var114 = "set ASR_FOURMIC_OUT_SET_4MICDELAYTIMESTATUS Error.";
                                            break label1072;
                                        }
                                    case 10107:
                                        try {
                                            this.b.c.i((Boolean) var2);
                                            return;
                                        } catch (Exception var16) {
                                            var114 = "set ASR_FOURMIC_IS_RK_PLATFORM Error.";
                                            break label1072;
                                        }
                                    case 10108:
                                        try {
                                            this.b.c.j((Boolean) var2);
                                            return;
                                        } catch (Exception var15) {
                                            var114 = "set ASR_FOURMIC_IS_RK_SINGALCHANEL Error.";
                                            break label1072;
                                        }
                                    case 10192:
                                        try {
                                            this.b.C((Boolean) var2);
                                            return;
                                        } catch (Exception var14) {
                                            var114 = "set ASR_OPT_FOURMIC_PCM_TEST Error.";
                                            break label1072;
                                        }
                                    case 10193:
                                        try {
                                            if (!this.l((Boolean) var2)) {
                                                com.unisound.common.y.a("set ASR_FOURMIC_USE_ASR_AS_VAD Error.");
                                            }
                                        } catch (Exception var8) {
                                            com.unisound.common.y.a("set ASR_FOURMIC_USE_ASR_AS_VAD Error.");
                                        }
                                    case 10007:
                                        try {
                                            this.b.H((Integer) var2);
                                            return;
                                        } catch (Exception var50) {
                                            var114 = "set ASR_OPT_WUW_NET0_THRESHOLD Error.";
                                            break label1072;
                                        }
                                    case 10194:
                                        try {
                                            this.s((Integer) var2);
                                            return;
                                        } catch (Exception var13) {
                                            var114 = "set ASR_FOURMIC_CHANGE_CHANNAL Error.";
                                            break label1072;
                                        }
                                    case 10198:
                                        try {
                                            this.mSpeechUnderstanderParams.f((Boolean) var2);
                                            return;
                                        } catch (Exception var12) {
                                            var114 = "set ASR_FOURMIC_ISDEBUG Error.";
                                            break label1072;
                                        }
                                    case 10199:
                                        try {
                                            this.mSpeechUnderstanderParams.e((Boolean) var2);
                                            return;
                                        } catch (Exception var11) {
                                            var114 = "set ASR_FOURMIC Error.";
                                            break label1072;
                                        }
                                    case 20170503:
                                        try {
                                            com.unisound.common.y.r = (Boolean) var2;
                                            return;
                                        } catch (Exception var10) {
                                            var114 = "set DEBUG_SAVE_OFFLINEASR_SESSION_PCM Error.";
                                            break label1072;
                                        }
                                    default:
                                        switch (var1) {
                                            case 1007:
                                                try {
                                                    var114 = (String) var2;
                                                    this.b.u(var114);
                                                    this.mSpeechUnderstanderParams.f(var114);
                                                    return;
                                                } catch (Exception var83) {
                                                    var114 = "set asr_online_oneshot_server_address Error.";
                                                    break label1072;
                                                }
                                            case 1008:
                                                try {
                                                    var114 = (String) var2;
                                                    this.l(var114);
                                                    this.mSpeechUnderstanderParams.c(var114);
                                                    return;
                                                } catch (Exception var82) {
                                                    var114 = "set asr_domain Error.";
                                                    break label1072;
                                                }
                                            case 1009:
                                                label723:
                                                {
                                                    try {
                                                        var114 = (String) var2;
                                                        if (this.b.t(var114)) {
                                                            this.mSpeechUnderstanderParams.a(var114);
                                                            return;
                                                        }
                                                    } catch (Exception var113) {
                                                        var10001 = false;
                                                        break label723;
                                                    }

                                                    try {
                                                        this.q(-63542);
                                                        return;
                                                    } catch (Exception var81) {
                                                        var10001 = false;
                                                    }
                                                }

                                                var114 = "set asr_server_address Error.";
                                                break label1072;
                                            default:
                                                switch (var1) {
                                                    case 1017:
                                                        try {
                                                            this.b.l((String) var2);
                                                            return;
                                                        } catch (Exception var88) {
                                                            var114 = "set ASR_OPT_SET_POST_PROCESS_PARAMS Error.";
                                                            break label1072;
                                                        }
                                                    case 1018:
                                                        try {
                                                            this.b.b((List) var2);
                                                            return;
                                                        } catch (Exception var87) {
                                                            var114 = "set ASR_OPT_SET_ONESHOT_WAKEUPWORD Error.";
                                                            break label1072;
                                                        }
                                                    case 1019:
                                                        try {
                                                            this.b.k((String) var2);
                                                            return;
                                                        } catch (Exception var86) {
                                                            var114 = "set ASR_OPT_ADDITIONAL_SERVICE Error.";
                                                            break label1072;
                                                        }
                                                    case 1020:
                                                        try {
                                                            this.e((Boolean) var2);
                                                            return;
                                                        } catch (Exception var85) {
                                                            var114 = "set nlu_enable Error.";
                                                            break label1072;
                                                        }
                                                    case 1021:
                                                        try {
                                                            this.e((String) var2);
                                                            return;
                                                        } catch (Exception var84) {
                                                            var114 = "set nlu_scenario Error.";
                                                            break label1072;
                                                        }
                                                    default:
                                                        switch (var1) {
                                                            case 1030:
                                                                try {
                                                                    this.f((String) var2);
                                                                    return;
                                                                } catch (Exception var92) {
                                                                    var114 = "set history Error.";
                                                                    break label1072;
                                                                }
                                                            case 1031:
                                                                try {
                                                                    this.g((String) var2);
                                                                    return;
                                                                } catch (Exception var91) {
                                                                    var114 = "set city Error.";
                                                                    break label1072;
                                                                }
                                                            case 1032:
                                                                try {
                                                                    this.h((String) var2);
                                                                    return;
                                                                } catch (Exception var90) {
                                                                    var114 = "set voiceID Error.";
                                                                    break label1072;
                                                                }
                                                            case 1033:
                                                                try {
                                                                    this.i((String) var2);
                                                                    return;
                                                                } catch (Exception var89) {
                                                                    var114 = "set gps Error.";
                                                                    break label1072;
                                                                }
                                                            default:
                                                                switch (var1) {
                                                                    case 1069:
                                                                        return;
                                                                    case 1070:
                                                                        try {
                                                                            this.b.g((Integer) var2);
                                                                            return;
                                                                        } catch (Exception var98) {
                                                                            var114 = "set Front_reset_cache_byte_time Error.";
                                                                            break label1072;
                                                                        }
                                                                    case 1071:
                                                                        try {
                                                                            com.unisound.common.y.t = (Boolean) var2;
                                                                            return;
                                                                        } catch (Exception var97) {
                                                                            var114 = "set DEBUG_SAVELOG Error.";
                                                                            break label1072;
                                                                        }
                                                                    case 1072:
                                                                        try {
                                                                            com.unisound.common.y.u = (Boolean) var2;
                                                                        } catch (Exception var9) {
                                                                            com.unisound.common.y.a("set DEBUG_POSTLOG Error.");
                                                                        }
                                                                    case 1073:
                                                                        try {
                                                                            this.aq = (Boolean) var2;
                                                                            return;
                                                                        } catch (Exception var96) {
                                                                            var114 = "set USE_HANDLERTHREAD Error.";
                                                                            break label1072;
                                                                        }
                                                                    case 1074:
                                                                        try {
                                                                            this.b.b((String) var2);
                                                                            return;
                                                                        } catch (Exception var95) {
                                                                            var114 = "set SAVE_AFTERVAD_RECORDING_DATA Error.";
                                                                            break label1072;
                                                                        }
                                                                    case 1075:
                                                                        try {
                                                                            this.b.i((Boolean) var2);
                                                                            return;
                                                                        } catch (Exception var94) {
                                                                            var114 = "set MARK_VAD Error.";
                                                                            break label1072;
                                                                        }
                                                                    case 1076:
                                                                        try {
                                                                            this.b.N((Boolean) var2);
                                                                            return;
                                                                        } catch (Exception var93) {
                                                                            var114 = "set TEMP_RESULT Error.";
                                                                            break label1072;
                                                                        }
                                                                    default:
                                                                        switch (var1) {
                                                                            case 1079:
                                                                                try {
                                                                                    this.b.e((Boolean) var2);
                                                                                    return;
                                                                                } catch (Exception var105) {
                                                                                    var114 = "set setRecognizeFrontVADEnable Error.";
                                                                                    break label1072;
                                                                                }
                                                                            case 1080:
                                                                                try {
                                                                                    this.b.x((Integer) var2);
                                                                                    return;
                                                                                } catch (Exception var104) {
                                                                                    var114 = "set ASR_OPT_RECORDING_PAC_SIZE Error";
                                                                                    break label1072;
                                                                                }
                                                                            case 1081:
                                                                                try {
                                                                                    this.b.h((Integer) var2);
                                                                                    return;
                                                                                } catch (Exception var103) {
                                                                                    var114 = "set OneShot VAD back sil time Error.";
                                                                                    break label1072;
                                                                                }
                                                                            case 1082:
                                                                                try {
                                                                                    this.b.O((Integer) var2);
                                                                                    return;
                                                                                } catch (Exception var102) {
                                                                                    var114 = "set RECOGNIZE_SCENE Error.";
                                                                                    break label1072;
                                                                                }
                                                                            case 1083:
                                                                                try {
                                                                                    this.b.D((Integer) var2);
                                                                                    com.unisound.common.y.c(new Object[]{"SpeechUnderstanderInterface", "set recognize modelId ", var2});
                                                                                    return;
                                                                                } catch (Exception var101) {
                                                                                    var114 = "set RECOGNIZE_MODEL_ID Error.";
                                                                                    break label1072;
                                                                                }
                                                                            case 1084:
                                                                                try {
                                                                                    this.b.E((Integer) var2);
                                                                                    com.unisound.common.y.c(new Object[]{"SpeechUnderstanderInterface", "set wakeup modelId ", var2});
                                                                                    return;
                                                                                } catch (Exception var100) {
                                                                                    var114 = "set WAKEUP_MODEL_ID Error.";
                                                                                    break label1072;
                                                                                }
                                                                            case 1085:
                                                                                try {
                                                                                    this.b.P((Boolean) var2);
                                                                                    return;
                                                                                } catch (Exception var99) {
                                                                                    var114 = "set ALREAD_AWPE Error.";
                                                                                    break label1072;
                                                                                }
                                                                            default:
                                                                                break label1071;
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }

                                com.unisound.common.y.a("set ASR_OPT_OPEN_FULL_DUPLEX Error.");
                                return;
                            }
                        } else {
                            try {
                                this.b.S((Integer) var2);
                                return;
                            } catch (Exception var106) {
                                var114 = "set ASR_FALSE_ALARM Error.";
                            }
                        }
                    } else {
                        try {
                            this.b.f((Integer) var2);
                            this.mSpeechUnderstanderParams.a((Integer) var2);
                            return;
                        } catch (Exception var108) {
                            var114 = "set asr_front_cache_time Error.";
                        }
                    }
                } else {
                    try {
                        this.j(String.valueOf(var2));
                        return;
                    } catch (Exception var110) {
                        var114 = "set nlu_ver Error.";
                    }
                }
            } else {
                try {
                    boolean var5 = (Boolean) var2;
                    this.d(var5);
                    this.mSpeechUnderstanderParams.a(var5);
                    return;
                } catch (Exception var112) {
                    var114 = "set asr_voice_field Error.";
                }
            }

            com.unisound.common.y.a(var114);
            return;
        }

        super.setOption(var1, var2);
    }

    protected int setWakeupWord(List var1) {
        int var2 = this.L();
        int var3 = 0;
        if (var2 != 0) {
            var3 = -64001;
        } else if (this.S() != 0) {
            var3 = -64002;
        } else {
            if (var1 != null && var1.size() > 0) {
                this.p.b(var1);
                this.p.a(true);
                if (this.p.c()) {
                    String var4 = this.p.a();
                    this.a("wakeup", "wakeup", this.f.b("wakeup"), this.p.b(), this.p.d(), var4);
                    this.p.a(false);
                    return var3;
                }
            }

            var3 = -1;
        }

        return var3;
    }

    protected int setWakeupWord(Map var1) {
        List var3 = this.b.a(var1);
        int var2;
        if (var3 != null) {
            com.unisound.common.y.c(new Object[]{"SpeechUnderstanderInterface: setWakeupWord"});
            var2 = this.setWakeupWord(var3);
        } else {
            com.unisound.common.y.c(new Object[]{"SpeechUnderstanderInterface: setWakeupWord error"});
            var2 = -1;
        }

        return var2;
    }

    protected void start() {
        this.start(this.f.e);
    }

    protected void start(String var1) {
        com.unisound.common.y.c(new Object[]{"USC", "start recognition tag is ", var1});
        this.C();
        int var2;
        if (var1 == null) {
            var2 = -63532;
        } else {
            if (this.F == 2 || this.N) {
                this.as = false;
                this.b.O(false);
                this.b.F(com.unisound.c.a.a(this.x, this.ak));
                if (this.mSpeechUnderstanderParams.o()) {
                    if (this.b.c.s()) {
                        this.b.c.q();
                    }

                    this.h(true);
                    this.i(this.mSpeechUnderstanderParams.p());
                    this.k(this.mSpeechUnderstanderParams.q());
                }

                if (this.y() > 1) {
                    this.e(this.b.Y());
                }

                if (this.b.Y() == 0) {
                    if (!this.getFixEngineVersion().contains("V3.")) {
                        this.g(this.b.aC());
                    } else {
                        this.h(this.b.aD());
                        this.i(this.b.aE());
                        this.j(this.b.aF());
                        this.k(this.b.aG());
                    }
                }

                this.b(this.b.aL());
                this.c(this.b.aM());
                Handler var3 = this.am;
                if (var3 != null) {
                    var3.removeCallbacksAndMessages((Object) null);
                }

                com.unisound.sdk.ao var9 = this.o;
                if (var9 != null) {
                    var9.removeCallbacksAndMessages((Object) null);
                }

                if (this.e != null) {
                    this.e.removeCallbacksAndMessages((Object) null);
                }

                if (!this.L) {
                    StringBuilder var6 = new StringBuilder();
                    var6.append("init error ");
                    var6.append(ErrorCode.toJsonMessage(-64001));
                    com.unisound.common.y.a(var6.toString());
                    this.z.onError(1300, ErrorCode.toJsonMessage(-64001));
                } else {
                    this.u.clear();
                    this.v.clear();
                    this.w.clear();
                    com.unisound.common.v.a();
                    this.au = false;
                    this.av = false;
                    com.unisound.common.y.a();
                    if (this.b.X() != this.b.v()) {
                        this.setOption(1011, this.b.X());
                    }

                    if (var1.contains("oneshot:")) {
                        this.b.j(true);
                        var1 = var1.split(":")[1];
                    } else {
                        this.b.j(false);
                    }

                    String var10 = var1;
                    if (var1.equals("wakeup:netasr")) {
                        this.mSpeechUnderstanderParams.h(true);
                        var10 = var1.split(":")[0];
                    }

                    int var4;
                    label87:
                    {
                        short var8;
                        label86:
                        {
                            w var7;
                            label85:
                            {
                                this.I = var10;
                                var4 = this.F;
                                if (this.b.A()) {
                                    this.b.O(true);
                                    if (this.mSpeechUnderstanderParams.i() != 2) {
                                        this.F = 0;
                                    }

                                    this.b.M(this.mSpeechUnderstanderParams.m());
                                    if (this.b.Y() == 0) {
                                        this.b.F(this.b.aA());
                                        var8 = 100;
                                        break label86;
                                    }
                                } else {
                                    if (!var10.equals(this.J)) {
                                        this.F = this.mSpeechUnderstanderParams.i();
                                        this.b.d(this.b.k());
                                        this.b.M(this.mSpeechUnderstanderParams.n());
                                        this.b.h(false);
                                        var7 = this.b;
                                        var2 = this.b.ay();
                                        break label85;
                                    }

                                    boolean var5;
                                    if (!this.mSpeechUnderstanderParams.r()) {
                                        this.F = 2;
                                        var7 = this.b;
                                        var5 = this.mSpeechUnderstanderParams.m();
                                    } else {
                                        this.F = 0;
                                        var7 = this.b;
                                        var5 = this.mSpeechUnderstanderParams.n();
                                    }

                                    var7.M(var5);
                                    if (this.b.Y() == 0) {
                                        this.b.F(this.b.aA());
                                        var8 = 300;
                                        break label86;
                                    }
                                }

                                this.b.h(true);
                                var7 = this.b;
                                var2 = this.b.aA();
                            }

                            var7.F(var2);
                            break label87;
                        }

                        this.f(var8);
                        this.b.h(true);
                    }

                    this.b.w(this.mSpeechUnderstanderParams.n());
                    this.H.a();
                    this.E = "";
                    this.C = false;
                    this.D = false;
                    this.B = null;
                    var2 = this.F;
                    if (var2 != 0) {
                        if (var2 != 1) {
                            this.C = true;
                            this.e.c(true);
                            this.e.d(this.M);
                        } else {
                            this.D = true;
                            this.e.c(false);
                            y var11 = new y(this.b, this.o);
                            this.B = var11;
                            this.o.a(var11, false, this.ar, (com.unisound.sdk.aa) null);
                        }
                    } else {
                        this.B = new y(this.b, this.o);
                        this.e.c(true);
                        this.e.d(this.M);
                        this.o.a(this.B, false, this.ar, (com.unisound.sdk.aa) null);
                    }

                    if (this.b.A()) {
                        this.F = var4;
                    }

                    super.start(var10);
                }

                return;
            }

            var2 = -63533;
        }

        this.q(var2);
    }

    protected void stop() {
        super.stop();
        this.B = null;
        this.o.d();
    }

    protected int u() {
        return super.u();
    }

    protected int unloadGrammar(String var1) {
        int var2;
        if (this.L() != 0) {
            var2 = -64001;
        } else {
            var2 = this.f.h(var1);
        }

        return var2;
    }

    protected void uploadUserData(Map var1) {
        this.o.a(var1);
    }

    protected String v() {
        return super.v();
    }

    protected String w() {
        return super.w();
    }

    protected int x() {
        return super.x();
    }

    protected int y() {
        return super.y();
    }

    protected int z() {
        int var1 = this.G;
        int var2 = -1;
        if (var1 != -1 && var1 != 2 || this.F != 2) {
            if (this.F != 2) {
                if (this.p(this.x)) {
                    this.N = true;
                } else {
                    this.q(-63533);
                }
            }

            if (this.b != null) {
                this.b.G("normal");
            }
        }

        if (!this.L) {
            var2 = this.G;
            if ((var2 == -1 || var2 == 1) && this.F == 1) {
                this.L = true;
                Handler var3 = this.am;
                if (var3 != null) {
                    var3.sendEmptyMessage(1129);
                }

                var2 = 0;
            } else {
                this.L = true;
                this.r();
                var2 = this.g(this.b.aJ());
                this.M = true;
                com.unisound.common.y.c(new Object[]{"SpeechUnderstanderInterface : loadResult = ", var2});
            }
        }

        return var2;
    }
}

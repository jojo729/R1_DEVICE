package com.unisound.b;

import java.util.*;
import android.content.*;
import android.text.*;
import org.json.*;

public class b
{
    private static final String C = "systemResponese";
    private static String z = "";
    private Context A = null;
    public Ra1 B;
    public com.unisound.b.c D = com.unisound.b.c.FINISH;
    private String a = "http://dc.hivoice.cn/rest/v2/device/activate";
    private String b = "http://dc.hivoice.cn/rest/v2/token/refresh";
    private String c;
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private String h = "";
    private String i = "";
    private String j = "";
    private String k = "";
    private String l = "";
    private String m = "";
    private String n = "";
    private String o = "";
    private String p = "";
    private String q = "";
    private String r = "Android";
    private String s = "";
    private String t = "";
    private String u = "";
    private String v = "";
    private String w = "";
    private String x = "";
    private String y = "";

    public b(Context context) {
        this.A = context;
        this.B = new Ra1(context.getMainLooper());
    }


    private Map<String, String> A() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String d;
        if (this.d != null) {
            d = this.d;
        }
        else {
            d = "";
        }
        hashMap.put("udid", d);
        String e;
        if (this.e != null) {
            e = this.e;
        }
        else {
            e = "";
        }
        hashMap.put("deviceSn", e);
        String f;
        if (this.f != null) {
            f = this.f;
        }
        else {
            f = "";
        }
        hashMap.put("appKey", f);
        hashMap.put("timestamp", this.E());
        String h;
        if (this.h != null) {
            h = this.h;
        }
        else {
            h = "";
        }
        hashMap.put("appVersion", h);
        String i;
        if (this.i != null) {
            i = this.i;
        }
        else {
            i = "";
        }
        hashMap.put("pkgName", i);
        String j;
        if (this.j != null) {
            j = this.j;
        }
        else {
            j = "";
        }
        hashMap.put("imei", j);
        String k;
        if (this.k != null) {
            k = this.k;
        }
        else {
            k = "";
        }
        hashMap.put("macAddress", k);
        String l;
        if (this.l != null) {
            l = this.l;
        }
        else {
            l = "";
        }
        hashMap.put("wifiSsid", l);
        String m;
        if (this.m != null) {
            m = this.m;
        }
        else {
            m = "";
        }
        hashMap.put("telecomOperator", m);
        String n;
        if (this.n != null) {
            n = this.n;
        }
        else {
            n = "";
        }
        hashMap.put("bssId", n);
        String o;
        if (this.o != null) {
            o = this.o;
        }
        else {
            o = "";
        }
        hashMap.put("productName", o);
        String p;
        if (this.p != null) {
            p = this.p;
        }
        else {
            p = "";
        }
        hashMap.put("productModel", p);
        String q;
        if (this.q != null) {
            q = this.q;
        }
        else {
            q = "";
        }
        hashMap.put("productMfr", q);
        String r;
        if (this.r != null) {
            r = this.r;
        }
        else {
            r = "";
        }
        hashMap.put("productOs", r);
        String s;
        if (this.s != null) {
            s = this.s;
        }
        else {
            s = "";
        }
        hashMap.put("productOsVersion", s);
        String t;
        if (this.t != null) {
            t = this.t;
        }
        else {
            t = "";
        }
        hashMap.put("hardwareSn", t);
        String u;
        if (this.u != null) {
            u = this.u;
        }
        else {
            u = "";
        }
        hashMap.put("memo", u);
        hashMap.put("signature", this.C());
        return hashMap;
    }

    private Map<String, String> B() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String d;
        if (this.d != null) {
            d = this.d;
        }
        else {
            d = "";
        }
        hashMap.put("udid", d);
        String f;
        if (this.f != null) {
            f = this.f;
        }
        else {
            f = "";
        }
        hashMap.put("appKey", f);
        String x;
        if (this.x != null) {
            x = this.x;
        }
        else {
            x = "";
        }
        hashMap.put("token", x);
        hashMap.put("timestamp", this.E());
        hashMap.put("signature", this.D());
        return hashMap;
    }

    // 计算签名
    private String C() {
        final ArrayList<String> list = new ArrayList<String>();
        String d;
        if (this.d != null) {
            d = this.d;
        }
        else {
            d = "";
        }
        list.add(d);
        String e;
        if (this.e != null) {
            e = this.e;
        }
        else {
            e = "";
        }
        list.add(e);
        String f;
        if (this.f != null) {
            f = this.f;
        }
        else {
            f = "";
        }
        list.add(f);
        String g;
        if (this.g != null) {
            g = this.g;
        }
        else {
            g = "";
        }
        list.add(g);
        String h;
        if (this.h != null) {
            h = this.h;
        }
        else {
            h = "";
        }
        list.add(h);
        String i;
        if (this.i != null) {
            i = this.i;
        }
        else {
            i = "";
        }
        list.add(i);
        String j;
        if (this.j != null) {
            j = this.j;
        }
        else {
            j = "";
        }
        list.add(j);
        String k;
        if (this.k != null) {
            k = this.k;
        }
        else {
            k = "";
        }
        list.add(k);
        String l;
        if (this.l != null) {
            l = this.l;
        }
        else {
            l = "";
        }
        list.add(l);
        String m;
        if (this.m != null) {
            m = this.m;
        }
        else {
            m = "";
        }
        list.add(m);
        String n;
        if (this.n != null) {
            n = this.n;
        }
        else {
            n = "";
        }
        list.add(n);
        String o;
        if (this.o != null) {
            o = this.o;
        }
        else {
            o = "";
        }
        list.add(o);
        String p;
        if (this.p != null) {
            p = this.p;
        }
        else {
            p = "";
        }
        list.add(p);
        String q;
        if (this.q != null) {
            q = this.q;
        }
        else {
            q = "";
        }
        list.add(q);
        String r;
        if (this.r != null) {
            r = this.r;
        }
        else {
            r = "";
        }
        list.add(r);
        String s;
        if (this.s != null) {
            s = this.s;
        }
        else {
            s = "";
        }
        list.add(s);
        String t;
        if (this.t != null) {
            t = this.t;
        }
        else {
            t = "";
        }
        list.add(t);
        String u;
        if (this.u != null) {
            u = this.u;
        }
        else {
            u = "";
        }
        list.add(u);
        String y;
        if (this.y != null) {
            y = this.y;
        }
        else {
            y = "";
        }
        list.add(y);
        return com.unisound.b.k.a((List)list);
    }

    private String D() {
        final ArrayList<String> list = new ArrayList<String>();
        String d;
        if (this.d != null) {
            d = this.d;
        }
        else {
            d = "";
        }
        list.add(d);
        String f;
        if (this.f != null) {
            f = this.f;
        }
        else {
            f = "";
        }
        list.add(f);
        String g;
        if (this.g != null) {
            g = this.g;
        }
        else {
            g = "";
        }
        list.add(g);
        String x;
        if (this.x != null) {
            x = this.x;
        }
        else {
            x = "";
        }
        list.add(x);
        String y;
        if (this.y != null) {
            y = this.y;
        }
        else {
            y = "";
        }
        list.add(y);
        return com.unisound.b.k.a((List)list);
    }

    private String E() {
        final long f = this.F();
        String s;
        if (f != 0L) {
            this.g = String.valueOf(com.unisound.b.k.a(f + System.currentTimeMillis()));
            s = this.g;
        }
        else {
            this.g = String.valueOf(com.unisound.b.k.a(System.currentTimeMillis()));
            s = this.g;
        }
        return s;
    }

    private long F() {
        return this.A.getSharedPreferences("systemResponese", 0).getLong("timeDelay", 0L);
    }

    static /* synthetic */ c a(final b b, final c d) {
        return b.D = d;
    }

    private void a(final long n) {
        final SharedPreferences.Editor edit = this.A.getSharedPreferences("systemResponese", 0).edit();
        edit.putLong("timeDelay", n);
        edit.commit();
    }

    static /* synthetic */ void a(final b b) {
        b.y();
    }

    static /* synthetic */ void a(final b b, final long n) {
        b.a(n);
    }

    protected static void a(String z) {
        if (!TextUtils.isEmpty((CharSequence)z)) {
            com.unisound.b.b.z = z;
        }
        else {
            com.unisound.b.i.a("ActivatroInterface setJsonString error!");
        }
    }

    static /* synthetic */ Context b(final b b) {
        return b.A;
    }

    static /* synthetic */ String c(final b b) {
        return b.f;
    }

    static /* synthetic */ String d(final b b) {
        return b.a;
    }

    static /* synthetic */ Map e(final b b) {
        return b.A();
    }

    static /* synthetic */ String f(final b b) {
        return b.b;
    }

    static /* synthetic */ Map g(final b b) {
        return b.B();
    }

    static /* synthetic */ Ra1 h(final b b) {
        return b.B;
    }

    private void r(final String s) {
        final JSONObject a = com.unisound.b.h.a(s);
        final String b = com.unisound.b.h.b(a, "deviceSn");
        if (b.equals("")) {
            com.unisound.b.i.c("ActivatorInterface _deviceSn= null");
        }
        else {
            this.l(b);
            com.unisound.b.i.c("ActivatorInterface _deviceSn= " + b);
        }
        final String b2 = com.unisound.b.h.b(a, "appKey");
        if (b2.equals("")) {
            com.unisound.b.i.a("ActivatorInterface _appkey= null");
        }
        else {
            this.c(b2);
            com.unisound.b.i.c("ActivatorInterface _appkey= " + b2);
        }
        final String b3 = com.unisound.b.h.b(a, "appSecret");
        if (b3.equals("")) {
            com.unisound.b.i.a("ActivatorInterface _secret= null");
        }
        else {
            this.d(b3);
            com.unisound.b.i.c("ActivatorInterface _secret= " + b3);
        }
        final String b4 = com.unisound.b.h.b(a, "appVersion");
        if (b4.equals("")) {
            com.unisound.b.i.c("ActivatorInterface _appVersion= null");
        }
        else {
            this.f(b4);
            com.unisound.b.i.c("ActivatorInterface _appVersion= " + b4);
        }
        final String b5 = com.unisound.b.h.b(a, "packageName");
        if (b5.equals("")) {
            com.unisound.b.i.c("ActivatorInterface _packageName= null");
        }
        else {
            this.g(b5);
            com.unisound.b.i.c("ActivatorInterface _packageName= " + b5);
        }
        final String b6 = com.unisound.b.h.b(a, "imei");
        if (b6.equals("")) {
            com.unisound.b.i.c("ActivatorInterface _imei= null");
        }
        else {
            this.e(b6);
            com.unisound.b.i.c("ActivatorInterface _imei= " + b6);
        }
        final String b7 = com.unisound.b.h.b(a, "macAddress");
        if (b7.equals("")) {
            com.unisound.b.i.c("ActivatorInterface _macAddress= null");
        }
        else {
            this.h(b7);
            com.unisound.b.i.c("ActivatorInterface _macAddress= " + b7);
        }
        final String b8 = com.unisound.b.h.b(a, "wifiSsid");
        if (b8.equals("")) {
            com.unisound.b.i.c("ActivatorInterface _wifiSsid= null");
        }
        else {
            this.i(b8);
            com.unisound.b.i.c("ActivatorInterface _wifiSsid= " + b8);
        }
        final String b9 = com.unisound.b.h.b(a, "telecomOperator");
        if (b9.equals("")) {
            com.unisound.b.i.c("ActivatorInterface _telecomOperator= null");
        }
        else {
            this.j(b9);
            com.unisound.b.i.c("ActivatorInterface _telecomOperator= " + b9);
        }
        final String b10 = com.unisound.b.h.b(a, "memo");
        if (b10.equals("")) {
            com.unisound.b.i.c("ActivatorInterface _memo= null");
        }
        else {
            this.k(b10);
            com.unisound.b.i.c("ActivatorInterface _memo= " + b10);
        }
        final String b11 = com.unisound.b.h.b(a, "token");
        if (b11.equals("")) {
            com.unisound.b.i.c("ActivatorInterface _token= null");
        }
        else {
            this.q(b11);
            com.unisound.b.i.c("ActivatorInterface __token= " + b11);
        }
        final boolean a2 = com.unisound.b.h.a(a, "debug", false);
        if (a2) {
            this.a(a2);
            com.unisound.b.i.c("ActivatorInterface _debug= " + a2);
        }
    }

    static /* synthetic */ String x() {
        return z;
    }

    private void y() {
        if (this.h == "") {
            this.g();
        }
        if (this.j == "") {
            this.e();
        }
        if (this.i == "") {
            this.i();
        }
        if (this.k == "") {
            this.k();
        }
        if (this.l == "") {
            this.m();
        }
        if (this.m == "") {
            this.o();
        }
        this.z();
    }

    private void z() {
        this.n = "";
        this.o = "";
        this.p = "";
        this.q = "";
        this.s = "";
        this.t = "";
        try {
            this.n = com.unisound.a.a.e(this.A);
            this.o = com.unisound.a.a.d();
            this.p = com.unisound.a.a.e();
            this.q = com.unisound.a.a.f();
            this.s = com.unisound.a.a.g();
            this.t = com.unisound.a.a.h();
        }
        catch (Exception ex) {
            com.unisound.b.i.d("activate cant createDeviceInfo");
        }
    }

    protected Context a() {
        return this.A;
    }

    public synchronized void  a(final int n) {
        if (this.D == com.unisound.b.c.RUNNING) {
            this.B.sendEmptyMessage(108);
        }
        else {
            this.D = com.unisound.b.c.RUNNING;
            switch (n) {
                case 0:
                case 1: {
                    this.r(com.unisound.b.b.z);
                    new d(this, n).start();
                    break;
                }
                default: {
                    this.B.sendEmptyMessage(110);
                    this.D = com.unisound.b.c.FINISH;
                    break;
                }
            }
        }
    }

    protected void a(final Context a) {
        this.A = a;
    }

    public void a(final com.unisound.b.a.c c) {
        this.B.a(c);
    }

    protected void a(final boolean a) {
        com.unisound.a.a.b(com.unisound.b.i.a = a);
    }

    protected String b() {
        return this.f;
    }

    protected void b(final String c) {
        this.c = c;
        this.a = "http://" + c + "/rest/v1/device/activate";
        this.b = "http://" + c + "/rest/v1/token/refresh";
    }

    protected String c() {
        return this.y;
    }

    protected void c(final String f) {
        this.f = f;
    }

    protected String d() {
        return this.j;
    }

    protected void d(final String y) {
        this.y = y;
    }

    protected void e() {
        try {
            this.j = com.unisound.a.a.b(this.A);
        }
        catch (Exception ex) {
            this.B.sendEmptyMessage(120);
            com.unisound.b.i.d("activate HttpResponse result is null");
        }
    }

    protected void e(final String j) {
        this.j = j;
    }

    protected String f() {
        return this.h;
    }

    protected void f(final String h) {
        this.h = h;
    }

    protected void g() {
        this.h = com.unisound.a.a.f(this.A);
    }

    protected void g(final String i) {
        this.i = i;
    }

    protected String h() {
        return this.i;
    }

    protected void h(final String k) {
        this.k = k;
    }

    protected void i() {
        this.i = com.unisound.a.a.g(this.A);
    }

    protected void i(final String l) {
        this.l = l;
    }

    protected String j() {
        return this.k;
    }

    protected void j(final String m) {
        this.m = m;
    }

    protected void k() {
        try {
            this.k = com.unisound.a.a.h(this.A);
        }
        catch (Exception ex) {
            com.unisound.b.i.d("activate setMacAddress error");
        }
    }

    protected void k(final String u) {
        this.u = u;
    }

    protected String l() {
        return this.l;
    }

    public void l(final String e) {
        com.unisound.a.a.b(this.e = e);
    }

    protected void m() {
        try {
            this.l = com.unisound.a.a.i(this.A);
        }
        catch (Exception ex) {
            com.unisound.b.i.d("activate setWifiSsid error");
        }
    }

    public void m(final String g) {
        this.g = g;
    }

    protected String n() {
        return this.m;
    }

    protected void n(final String d) {
        this.d = d;
    }

    protected void o() {
        try {
            this.m = com.unisound.a.a.j(this.A);
        }
        catch (Exception ex) {
            com.unisound.b.i.d("activate setTelecomOperator error");
        }
    }

    protected void o(final String v) {
        this.v = v;
    }

    protected String p() {
        return this.u;
    }

    protected void p(final String w) {
        this.w = w;
    }

    protected String q() {
        return com.unisound.b.e.a();
    }

    public void q(final String x) {
        this.x = x;
    }

    public String r() {
        return this.e;
    }

    public String s() {
        return this.g;
    }

    protected String t() {
        return this.d;
    }

    public String u() {
        return this.v;
    }

    public String v() {
        return this.w;
    }

    public String w() {
        return this.x;
    }

    public String getA() {
        return a;
    }
}

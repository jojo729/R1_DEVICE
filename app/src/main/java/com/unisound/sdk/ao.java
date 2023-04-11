//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.sdk;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import cn.yunzhisheng.asr.VAD;
import com.unisound.c.a;
import com.unisound.common.ab;
import com.unisound.common.av;
import com.unisound.common.y;
import java.util.List;
import java.util.Map;

public class ao extends ab implements ak, au, cq {
    public static int a;
    public static int b;
    private static final int r = 40;
    protected av c = null;
    private com.unisound.common.au d = new com.unisound.common.au();
    private ay e = null;
    private ay f = null;
    private an g = null;
    private z h = null;
    private ad i = null;
    private as j = null;
    private String k = "";
    private boolean l = true;
    private Context m;
    private aa n = null;
    private Looper o;
    private ag p = new ap(this);
    private af q;

    static {
        System.loadLibrary("uscasr");
        a = 60000;
        b = 10000;
    }

    public ao(as var1) {
        this.j = var1;
    }

    public ao(as var1, Looper var2) {
        super(var2);
        this.j = var1;
        this.o = var2;
        this.q = new af(this.p, var2);
    }

    private void d(String var1) {
        y.c("Before startRecognition :cancelRecognition()");
        this.d(false);
        this.j.aW = this.d.a();

        try {
            this.j.aZ = com.unisound.c.a.b();
        } catch (Exception var3) {
            y.c("Recognizer:: getNetType error");
        }

        this.e = this.f;
        this.c = null;
        this.l = false;
        this.k = "";
        this.g = new an(this.j, this.m, var1);
        this.g.a(this);
        this.g.setPriority(10);
        this.g.setName("usc_net_thread");
        if (!this.j.A()) {
            this.g.start();
        }

        y.c("Recognizer:: recognitionThread start");
    }

    private void d(boolean var1) {
        if (this.g != null && !this.g.d()) {
            this.g.c();
        }

    }

    private void f(int var1) {
        if (var1 == 0) {
            this.c = new av(this.j.aY, this.d.a());
        }

        this.l = true;
        this.k = "";
        if (this.g != null) {
            this.k = this.g.e();
        }

        if (!this.q.b()) {
            this.q.e();
            if (this.i != null) {
                this.i.b(var1);
            }
        }

    }

    private void r() {
        y.c(new Object[]{"Recognizer stopRecording"});
        this.l = true;
        if (this.h != null) {
            this.h.d();
        }

    }

    private void s() {
        this.l = true;
        if (this.g != null && !this.g.a()) {
            this.g.b();
            this.q.c();
        }

    }

    private void t() {
        if (this.h != null) {
            this.h.g();
        }

    }

    public void A() {
        this.sendMessage(40);
    }

    public String a() {
        return com.unisound.common.an.a();
    }

    public void a(int var1) {
        this.r();
        this.t();
        this.f(var1);
    }

    public void a(int var1, int var2, Object var3) {
        if (this.i != null) {
            this.i.a(var1, var2, var3);
        }

    }

    public void a(Context var1, ad var2) {
        this.m = var1;
        this.a(var2);
        com.unisound.c.a.a(var1);
    }

    public void a(SparseArray<List<String>> var1) {
        cp var2 = new cp();
        var2.a(this);
        var2.a(this.j.aZ(), var1);
    }

    public void a(VAD var1) {
        if (this.i != null) {
            this.i.b();
        }

    }

    public void a(ad var1) {
        this.i = var1;
    }

    public void a(al var1) {
        if (this.i != null) {
            this.i.a(var1);
        }

    }

    public void a(ay var1) {
        this.f = var1;
        this.e = var1;
    }

    public void a(z var1, boolean var2, String var3, aa var4) {
        this.q.d();
        this.d(var3);
        if (var2) {
            this.n = var4;
            this.h = var1;
            this.h.setPriority(10);
            this.h.start();
        }

    }

    public void a(String var1) {
        cp var2 = new cp();
        var2.a(this);
        var2.a(this.j.aZ(), var1);
    }

    public void a(String var1, int var2) {
        this.j.a(new String(var1), var2);
    }

    public void a(String var1, boolean var2, int var3, long var4, long var6, int var8, int var9) {
    }

    public void a(List<byte[]> var1, String var2) {
        this.d(var2);
        this.g.a(var1);
        this.g.b();
    }

    public void a(Map<Integer, List<String>> var1) {
        cp var2 = new cp();
        var2.a(this);
        var2.a(this.j.aZ(), var1);
    }

    public void a(boolean var1) {
        this.j.K(var1);
    }

    public void a(boolean var1, byte[] var2, int var3, int var4) {
        if (this.n != null) {
            this.n.a(var2);
        }

    }

    public boolean a(Message var1) {
        return true;
    }

    public int b() {
        return this.q.a();
    }

    public void b(int var1) {
        if (this.i != null) {
            this.i.a(var1);
        }

    }

    public void b(String var1) {
        this.c(var1);
    }

    public void b(boolean var1) {
        if (var1) {
            if (this.i != null) {
                this.i.d();
            }
        } else {
            this.f(-61001);
            y.c(new Object[]{"startRecognition Error:cancelRecognition()"});
            this.d(true);
        }

    }

    public void b(boolean var1, byte[] var2, int var3, int var4) {
        if (!this.j.A() || this.j.B()) {
            if (this.g != null && var1) {
                this.g.a(var2);
            }

            ad var5 = this.i;
            if (var5 != null) {
                var5.a(var1, var2, var3, var4);
            }
        }

    }

    public void c() {
        if (this.g != null) {
            this.g.start();
        }

    }

    public void c(int var1) {
        int var2;
        if (var1 > a) {
            var2 = a;
        } else {
            var2 = var1;
            if (var1 < b) {
                var2 = b;
            }
        }

        this.q.a(var2);
    }

    protected void c(String var1) {
        ay var2 = this.e;
        if (var2 != null) {
            var2.a(var1);
        }

    }

    public void c(boolean var1) {
        this.e = null;
        this.l = true;
        this.q.e();
        this.t();
        this.d(var1);
        y.c(new Object[]{"Recognizer: cancelRecognition()"});
        this.removeSendMessage();
    }

    public void d() {
        this.r();
        this.s();
    }

    public void d(int var1) {
        this.j.bd = var1;
    }

    protected void e() {
        if (this.i != null) {
            this.i.g();
        }

    }

    public void e(int var1) {
        if (this.i != null) {
            this.i.c(var1);
        }

    }

    public String f() {
        return this.k;
    }

    public boolean g() {
        return this.l;
    }

    public com.unisound.common.au h() {
        return this.d;
    }

    public void i() {
        this.f(0);
    }

    public void j() {
        this.h = null;
        this.s();
        if (this.i != null) {
            this.i.f();
        }

    }

    public void k() {
        this.d(true);
        this.f(-61002);
    }

    public void k(int var1) {
        if (this.i != null) {
            this.i.h();
        }

    }

    public void l() {
    }

    public void m() {
        this.c(true);
        y.c(new Object[]{"max_speech_timeout cancel()"});
        this.f(-30002);
    }

    public void n() {
        this.e();
    }

    public void o() {
    }

    public av p() {
        return this.c;
    }

    public String q() {
        String var1;
        if (this.g != null) {
            var1 = this.g.h();
        } else {
            var1 = "";
        }

        return var1;
    }
}

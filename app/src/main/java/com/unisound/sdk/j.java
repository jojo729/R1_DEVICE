//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.sdk;

import android.content.Context;
import cn.yunzhisheng.asr.VAD;
import cn.yunzhisheng.asrfix.JniAsrFix;
import com.unisound.c.a;
import com.unisound.common.ba;
import com.unisound.common.bd;
import com.unisound.common.y;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class j extends k {
    VAD a;
    aa b;
    boolean c = false;
    boolean d = false;
    public List<byte[]> e;
    boolean f = true;
    int g = 0;
    private boolean t = true;
    private int u = 200;
    private int v = 3000;
    private String w = "";

    public j(JniAsrFix var1, String var2, w var3, Context var4) {
        super(var1, var2, var3, var4);
    }

    public j(JniAsrFix var1, String var2, w var3, aa var4, Context var5) {
        super(var1, var2, var3, var5);
        this.a = var4.d;
        this.b = var4;
        this.e = new ArrayList();
    }

    private List<byte[]> a(BlockingQueue<byte[]> var1) {
        ArrayList var2 = new ArrayList();
        long var3 = this.l.ak();

        while(!var1.isEmpty()) {
            byte[] var5 = (byte[])var1.poll();
            if (var5 != null) {
                this.a(var2, var5, (int)var3);
            }
        }

        return var2;
    }

    private void a(String var1, boolean var2) {
        if (var1 != null && var1.length() >= 0) {
            if (com.unisound.sdk.l.d(var1) >= this.l.E()) {
                if (this.l.z()) {
                    com.unisound.common.j.a(false, this.l.c());
                }

                String var3 = com.unisound.sdk.l.e(var1);
                this.l.d(var3);
                int var4 = this.m.l();
                int var5 = this.m.q();
                int var6 = var5 - var4;
                int var7 = this.l.A(this.l.ag()) - var5;
                y.c(new Object[]{"utteranceEndTime = ", var5});
                y.c(new Object[]{"utteranceStartTime = ", var4});
                y.c(new Object[]{"utteranceTime = ", var5 - var4});
                y.c(new Object[]{"delayTime = ", var7});
                this.l.j(var4);
                this.l.k(var6);
                long var8 = this.l.U();
                long var10 = this.l.R();
                long var12 = this.l.h(var10 - var8);
                long var14 = (long)var4;
                y.c(new Object[]{"wakeupLength = " + this.l.h(var8)});
                y.c(new Object[]{"outCacheLength = " + this.l.h(var10)});
                this.l.l(true);
                ba.a();
                bd var16 = new bd();
                var16.a(this.l.aZ());
                var16.b(com.unisound.c.a.a(this.l.aZ()));
                var16.c(this.l.bu());
                var16.a(com.unisound.sdk.l.d(var1));
                var16.e(var3);
                var16.f(System.currentTimeMillis() + "");
                this.e();
                LinkedBlockingQueue var17 = new LinkedBlockingQueue();
                y.c(new Object[]{"dropWakeup end wakeupCacheList.size() =>", this.e.size()});
                var17.addAll(this.e);
                this.q();
                if (this.l.c != null && this.l.c.a()) {
                    if (!this.l.ab()) {
                        if (this.l.c.s()) {
                            this.l.c.g(var7);
                            this.l.c.d(var6);
                        } else {
                            this.l.c.f(var7);
                            this.l.c.c(var6);
                        }
                    }

                    if (!this.l.aa()) {
                        this.l.p(1);
                    }

                    if (this.l.A()) {
                        this.l.n(false);
                        this.b.g();
                        if (!this.l.T() && !this.i.isEmpty()) {
                            this.i.clear();
                        }

                        this.l.a(this.l.u(), this.l.W());
                    }
                }

                if (this.l.A()) {
                    this.f = false;
                }

                this.a(var1, var2, var6, var12 + var14, (long)var7, var4, var5);
            } else {
                this.q();
            }
        }

    }

    private void a(List<byte[]> var1, byte[] var2, int var3) {
        byte var4 = 0;
        synchronized(this){}

        Throwable var10000;
        label194: {
            int var5;
            boolean var10001;
            try {
                var1.add(var2);
                var5 = var1.size() - 1;
            } catch (Throwable var18) {
                var10000 = var18;
                var10001 = false;
                break label194;
            }

            int var6 = 0;

            while(true) {
                if (var5 < 0) {
                    var3 = 0;
                    var5 = var4;
                    break;
                }

                try {
                    var6 += ((byte[])var1.get(var5)).length;
                } catch (Throwable var17) {
                    var10000 = var17;
                    var10001 = false;
                    break label194;
                }

                if (var6 >= var3) {
                    var3 = var5;
                    var5 = var4;
                    break;
                }

                --var5;
            }

            while(true) {
                if (var5 >= var3) {
                    return;
                }

                try {
                    var1.remove(0);
                } catch (Throwable var16) {
                    var10000 = var16;
                    var10001 = false;
                    break;
                }

                ++var5;
            }
        }

    }

    private void b(boolean var1) {
        this.o = true;
        String var2 = this.l.L();
        int var3 = this.l.A(this.l.ag()) - this.m.q();
        this.g = 0;
        this.f = true;
        if (this.l.j(var2) && var1) {
            y.c(new Object[]{"continue oneshot"});
            this.p();
            this.l.k(true);
            this.l.z(this.u + this.l.O() + var3);
            if (this.t) {
                this.t = false;
                if (!this.r.isEmpty() && this.b != null) {
                    this.b.h();
                    this.b.a(this.a(this.r));
                }
            }

            this.l.e(var2);
            this.l.d(this.l.k());
            this.l.h(false);
            if (this.l.c != null && this.l.c.a()) {
                if (this.l.c.s()) {
                    this.l.c.g(true);
                    this.l.c.l(this.l.A(this.l.c.m()) - (var3 + this.l.O()));
                } else {
                    this.l.c.f(true);
                    this.l.c.k(this.l.A(this.l.c.m()) - (var3 + this.l.O()));
                }

                this.l.n(true);
            }

            this.l.F(this.l.ay());
            this.k();
        } else {
            y.c(new Object[]{"continue wakeup"});
            this.l.k(false);
            this.l.e("");
            this.l.l(false);
            this.o();
            if (this.l.c != null && this.l.c.a() && !this.l.aa()) {
                this.l.p(0);
            }
        }

    }

    private int d(byte[] var1) {
        byte var2 = -1;
        if (var1.length != 1 || var1[0] != 100 && var1[0] != 99) {
            y.f(new Object[]{"Before recognize"});
            long var3 = System.currentTimeMillis();
            if (y.l) {
                y.d(new Object[]{"FixRecognitionThread::BF_recognize ; current Time =", var3});
            }

            this.a(1, 3, var1);
            if (y.l) {
                y.d(new Object[]{"FixRecognitionThread::read size =", var1.length, " first byte ", var1[0]});
            }

            this.l.c.e(var1.length);
            if (y.r && y.s != null && !y.s.equals("")) {
                com.unisound.common.j.a(var1, "/sdcard/asrtest/" + y.s + ".pcm");
            }

            int var5 = this.m.b(var1, var1.length);
            this.l.w(var1.length);
            int var6;
            if (this.l.A()) {
                if (!this.l.B() && (this.l.c == null || !this.l.c.a())) {
                    this.l.g((long)var1.length);
                    try {
                        this.c(var1);
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }

                if (!this.l.B() && this.l.D()) {
                    this.g += var1.length;
                    var6 = this.m.u();
                    y.c(new Object[]{"Wakeupsuccess check oneshotdata status = " + var6});
                    if (var6 == 4) {
                        this.b(true);
                    } else if ((long)this.g >= this.l.i((long)this.l.aK())) {
                        this.b(1161);
                        this.b(false);
                    }
                }
            }

            y.f(new Object[]{"queueHeadBuffer = ", var1.length});
            y.f(new Object[]{"After recognize "});
            long var7 = System.currentTimeMillis();
            if (y.l) {
                y.d(new Object[]{"FixRecognitionThread::AF_recognize ; current Time =", var7});
            }

            if (y.l) {
                y.d(new Object[]{"FixRecognitionThread::recognize_process_time =", var7 - var3});
            }

            if (this.w.equals("wakeup")) {
                this.a(var1);
            }

            if (var5 == 2) {
                if (this.l.z()) {
                    com.unisound.common.j.a(false, 16000, this.l.c());
                }

                String var9 = this.m.f();
                var6 = this.m.l();
                int var10 = this.m.q();
                if (this.l.A()) {
                    if (this.l.y()) {
                        if (!this.l.D()) {
                            this.a(var9, false);
                        }
                    } else {
                        this.a(var9, false, var6, var10);
                    }
                } else if (this.l.y()) {
                    this.a(var9, false);
                } else {
                    this.a(var9, false, var6, var10);
                }

                if (this.l.A() && this.l.B()) {
                    this.c = true;
                }

                y.c(new Object[]{"FixRecognitionThread:recognize=", var5});
                y.c(new Object[]{"FixRecognitionThread:partial = ", var9});
                this.a(3, 2, var9);
            } else if (var5 == 3) {
                this.j();
                y.c(new Object[]{"FixRecognitionThread:onRecognitionVADTimeout"});
            } else if (var5 == -6) {
                this.i();
                y.c(new Object[]{"FixRecognitionThread:max timeout"});
            } else if (var5 != 1 && var5 < 0) {
                this.d();
                this.a(JniAsrFix.a(var5));
                this.d = true;
                return var2;
            }

            if (!this.o) {
            }

            var2 = 0;
        } else {
            this.o = true;
        }

        return var2;
    }

    private void q() {
        this.e.clear();
    }

    public void a(long var1) {
        long var3 = 0L;
        synchronized(this){}

        Throwable var10000;
        label273: {
            int var5;
            boolean var10001;
            try {
                y.c(new Object[]{"dropWakeupPcmFromByteLength =>", var1});
                y.c(new Object[]{"dropWakeup wakeupCacheList.size() =>", this.e.size()});
                var5 = this.e.size() - 1;
            } catch (Throwable var30) {
                var10000 = var30;
                var10001 = false;
                break label273;
            }

            long var6 = 0L;

            long var8;
            while(true) {
                var8 = var3;
                if (var5 < 0) {
                    break;
                }

                try {
                    var6 += (long)((byte[])this.e.get(var5)).length;
                } catch (Throwable var29) {
                    var10000 = var29;
                    var10001 = false;
                    break label273;
                }

                if (var6 >= var1) {
                    var8 = (long)var5;
                    break;
                }

                --var5;
            }

            try {
                StringBuilder var10 = new StringBuilder();
                y.c(new Object[]{var10.append("dropWakeup enabled = ").append(var8).toString()});
            } catch (Throwable var28) {
                var10000 = var28;
                var10001 = false;
                break label273;
            }

            var5 = 0;

            while(true) {
                if ((long)var5 >= var8) {
                    return;
                }

                try {
                    this.e.remove(0);
                } catch (Throwable var27) {
                    var10000 = var27;
                    var10001 = false;
                    break;
                }

                ++var5;
            }
        }

    }

    protected void a(byte[] var1) {
        byte var2 = 0;
        synchronized(this){}

        Throwable var10000;
        label194: {
            int var3;
            int var4;
            int var5;
            boolean var10001;
            try {
                this.e.add(var1);
                var3 = this.l.be() / 1000;
                var4 = this.v;
                var5 = this.e.size() - 1;
            } catch (Throwable var18) {
                var10000 = var18;
                var10001 = false;
                break label194;
            }

            int var6 = 0;

            while(true) {
                if (var5 < 0) {
                    var5 = 0;
                    var6 = var2;
                    break;
                }

                try {
                    var6 += ((byte[])this.e.get(var5)).length;
                } catch (Throwable var17) {
                    var10000 = var17;
                    var10001 = false;
                    break label194;
                }

                if (var6 >= var3 * var4 * 2) {
                    var6 = var2;
                    break;
                }

                --var5;
            }

            while(true) {
                if (var6 >= var5) {
                    return;
                }

                try {
                    this.e.remove(0);
                } catch (Throwable var16) {
                    var10000 = var16;
                    var10001 = false;
                    break;
                }

                ++var6;
            }
        }

    }

    protected boolean a() {
        boolean var1;
        if (!this.i.isEmpty() || !this.b.e.isEmpty() && !this.b.b()) {
            var1 = true;
        } else {
            var1 = false;
        }

        return var1;
    }

    public void b() {
        final int n = this.n();
        if (n != 0) {
            this.a(n);
            this.d = true;
            return;
        }
        synchronized (this.m) {
            if (this.l.c != null && this.l.c.a()) {
                this.l.c.g();
            }
            this.l.V();
            this.l.ah();
            this.q();
            // monitorexit(this.m)
            while (true) {
                Label_0567: {
                    if (this.l.A() && !this.l.B()) {
                        this.l.l(false);
                        this.w = "wakeup";
                        final int a = this.m.a("wakeup", this.l.aB());
                        if (a < 0) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("FixRecognitionThread:jac::start error=");
                            sb.append(a);
                            sb.append(",model=");
                            sb.append(this.p);
                            sb.append(",modelId=");
                            sb.append(this.l.aB());
                            y.a(sb.toString());
                            this.a(JniAsrFix.a(a));
                            this.d = true;
                        }
                        else {
                            y.c(new Object[] { "FixRecognitionThread:jac::start model=", "wakeup", ",modelId=", this.l.aB() });
                            if (this.l.c == null || !this.l.c.a()) {
                                break Label_0567;
                            }
                            final int n2 = this.b.e.size() * this.l.ai();
                            final int n3 = this.i.size() * this.l.ai();
                            this.l.c.n();
                            if (this.l.c.s()) {
                                this.l.c.g(false);
                                this.l.c.j(this.l.A(n2 + n3));
                                break Label_0567;
                            }
                            this.l.c.f(false);
                            this.l.c.i(this.l.A(n2 + n3));
                            break Label_0567;
                        }
                    }
                    else {
                        this.w = this.p;
                        final int a2 = this.m.a(this.p, this.l.aB());
                        if (a2 >= 0) {
                            y.c(new Object[] { "FixRecognitionThread:jac::start model=", this.p, ",modelId=", this.l.aB() });
                            break Label_0567;
                        }
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("FixRecognitionThread:jac::start error=");
                        sb2.append(a2);
                        sb2.append(",model=");
                        sb2.append(this.p);
                        sb2.append(",modelId=");
                        sb2.append(this.l.aB());
                        y.a(sb2.toString());
                        this.a(JniAsrFix.a(a2));
                        this.d = true;
                    }
                    return;
                }
                this.a(0, 0, (Object)null);
                while (!this.l()) {
                    try {
                        if (this.l.c != null && this.l.c.a()) {
                            final byte[] array = this.i.poll(com.unisound.sdk.j.h, TimeUnit.MILLISECONDS);
                            if (array != null) {
                                if (this.l.c != null && this.l.c.a() && this.l.A()) {
                                    this.l.c.p(array.length);
                                }
                                if (this.d(array) == -1) {
                                    break;
                                }
                                if (y.q) {
                                    com.unisound.common.j.a(array, "/sdcard/asrtest/oneshotofflineFourMic.pcm");
                                }
                            }
                        }
                        else {
                            final byte[] array2 = this.i.poll(com.unisound.sdk.j.h, TimeUnit.MILLISECONDS);
                            if (array2 != null) {
                                if (this.d(array2) == -1) {
                                    break;
                                }
                                if (y.q) {
                                    com.unisound.common.j.a(array2, "/sdcard/asrtest/offline.pcm");
                                }
                            }
                        }
                        if (!this.o && (!this.n || this.a())) {
                            continue;
                        }
                        y.b(new Object[] { "FixRecognitionThread:recog break" });
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
                this.m.e();
                y.b(new Object[] { "FixRecognitionThread:recog stopped" });
                String f = this.m.f();
                y.b(new Object[] { "FixRecognitionThread:partial: ", f });
                if (this.l.ag() < this.l.g() * 32) {
                    y.b(new Object[] { "FixRecognitionThread:Give Up Result:" });
                    f = "";
                }
                int l = 0;
                int q = 0;
                Label_0953: {
                    String s;
                    if (f != null && !f.equals("")) {
                        s = f;
                        if (this.l.aO()) {
                            s = f;
                            if (!this.w.equals("wakeup")) {
                                l = this.m.l();
                                q = this.m.q();
                                break Label_0953;
                            }
                        }
                    }
                    else {
                        s = "\n-20.0";
                    }
                    l = 0;
                    q = 0;
                    f = s;
                }
                this.a(4, 2, (Object)f);
                this.a(2, 0, (Object)null);
                if (this.l.y()) {
                    this.a(f, true);
                }
                else if (!this.w.equals("wakeup")) {
                    this.a(f, false, l, q);
                }
                if (this.l.Y() != 0 || !this.l.A() || !this.l.B()) {
                    break;
                }
                if (this.m.t()) {
                    this.m.b(2);
                    continue;
                }
                this.m.b(1);
                continue;
            }
        }
    }

    public void c() {
        super.c();
    }

    public void d() {
        super.d();
    }

    public void e() {
        if (this.e.size() > 0) {
            y.c(new Object[]{"handleWakeupWordBuffer UtteranceTime = " + this.l.O()});
            this.a(this.l.i((long)(this.l.O() + 200)));
        }

    }

    public void run() {
        this.t = true;
        this.l.ah();
        this.l.k(false);
        this.c = false;
        y.c(new Object[]{"modelTag =>", this.p});
        if (this.l.A()) {
            this.m.i(this.l.aK());
        }

        if (this.l.c != null && this.l.c.a() && (this.p.equals("wakeup") || this.l.A()) && !this.l.aa()) {
            this.l.p(0);
        }

        y.g(new Object[]{"FixRecognitionThread start"});
        if (y.l) {
            y.d(new Object[]{"FixRecognitionThread ::run ThreadName = ", Thread.currentThread().getName(), ", ThreadId = ", Thread.currentThread().getId()});
        }

        if (!this.l() && this.m != null) {
            this.d = false;
            boolean var1 = true;

            label98:
            while(true) {
                while(true) {
                    if (this.l() || this.n && !this.a() || this.d) {
                        break label98;
                    }

                    if (y.r) {
                        y.s = String.valueOf(System.currentTimeMillis());
                    }

                    if (!var1) {
                        this.o = false;
                    }

                    if (this.l.A() && this.l.B()) {
                        this.c = true;
                    }

                    boolean var2;
                    if (this.l.c != null && this.l.c.a()) {
                        if (this.l.T()) {
                            this.b();
                            var2 = var1;
                            if (var1) {
                                var2 = false;
                            }

                            if (this.l.A() && !this.l.B()) {
                            }

                            var1 = var2;
                            if (this.c) {
                                break label98;
                            }
                        } else {
                            try {
                                Thread.sleep(1L);
                            } catch (InterruptedException var5) {
                                var5.printStackTrace();
                            }
                        }
                    } else {
                        if (this.l.A() && !this.f) {
                            try {
                                Thread.sleep(1L);
                            } catch (InterruptedException var4) {
                                var4.printStackTrace();
                            }
                        }

                        this.b();
                        var2 = var1;
                        if (var1) {
                            var2 = false;
                        }

                        if (this.l.A() && !this.l.B()) {
                        }

                        var1 = var2;
                        if (this.c) {
                            break label98;
                        }
                    }
                }
            }

            this.m();
            this.i.clear();
            y.g(new Object[]{"FixRecognitionThread stop"});
        }

    }
}

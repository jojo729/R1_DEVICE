//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.sdk;

import android.content.Context;
import cn.yunzhisheng.asrfix.JniAsrFix;
import com.unisound.common.j;
import com.unisound.common.y;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class k extends Thread {
    protected static long h = 30L;
    protected BlockingQueue<byte[]> i = new LinkedBlockingQueue();
    protected aj j = null;
    protected ak k = null;
    protected w l = null;
    protected JniAsrFix m = null;
    protected boolean n = false;
    protected boolean o = false;
    protected String p = "";
    public List<byte[]> q;
    public BlockingQueue<byte[]> r;
    public Context s;

    public k(JniAsrFix var1, String var2, w var3, Context var4) {
        this.s = var4;
        this.m = var1;
        this.p = var2;
        this.l = var3;
        this.q = new LinkedList();
        this.r = new LinkedBlockingQueue();
    }

    protected void a(int var1) {
        y.c(new Object[]{"RecognitionThreadInterface:doRecognitionError=", var1});
        ak var2 = this.k;
        if (var2 != null) {
            var2.a(var1);
        }

    }

    protected void a(int var1, int var2, Object var3) {
        ak var4 = this.k;
        if (var4 != null) {
            var4.a(var1, var2, var3);
        }

    }

    public void a(aj var1) {
        this.j = var1;
    }

    public void a(ak var1) {
        this.k = var1;
    }

    protected void a(String var1, boolean var2, int var3, int var4) {
        y.c(new Object[]{"RecognitionThreadInterface:doRecognitionResult partial=", var1 + ", utteranceStartTime = " + var3 + ", utteranceEndTime = " + var4});
        ak var5 = this.k;
        if (var5 != null) {
            al var6 = new al();
            var6.a(var1);
            var6.a(var2);
            var6.a(var3);
            var6.b(var4);
            var5.a(var6);
        }

    }

    protected void a(String var1, boolean var2, int var3, long var4, long var6, int var8, int var9) {
        y.c(new Object[]{"RecognitionThreadInterface:doRecognitionResult partial=", var1});
        ak var10 = this.k;
        if (var10 != null) {
            var10.a(var1, true, var3, var4, var6, var8, var9);
        }

    }

    public void a(boolean var1) {
        if (var1) {
            this.c();
        }

        if (this.isAlive()) {
            try {
                this.join(3900L);
                y.c(new Object[]{this.getName(), "waitEnd()"});
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }
        }

    }

    protected boolean a() {
        boolean var1;
        if (this.i.isEmpty()) {
            var1 = false;
        } else {
            var1 = true;
        }

        return var1;
    }

    protected void b(int var1) {
        y.c(new Object[]{"RecognitionThreadInterface: onRecognitionEvent event = " + var1});
        ak var2 = this.k;
        if (var2 != null) {
            var2.k(var1);
        }

    }

    public void b(long var1) throws Throwable {
        synchronized(this){}

        Throwable var10000;
        label213: {
            boolean var10001;
            long var3;
            try {
                y.c(new Object[]{"dropTime =>", var1});
                var3 = this.l.i(var1);
                y.c(new Object[]{"dropCacheByteLength =>", var3});
            } catch (Throwable var18) {
                var10000 = var18;
                var10001 = false;
                break label213;
            }

            int var5 = 0;
            var1 = 0L;

            while(true) {
                label201: {
                    try {
                        if (var5 < this.q.size()) {
                            var1 += (long)((byte[])this.q.get(var5)).length;
                            break label201;
                        }
                    } catch (Throwable var17) {
                        var10000 = var17;
                        var10001 = false;
                        break label213;
                    }

                    var1 = 0L;
                    break;
                }

                if (var1 >= var3) {
                    var1 = (long)var5;
                    break;
                }

                ++var5;
            }

            var5 = 0;

            while(true) {
                if ((long)var5 >= var1) {
                    return;
                }

                try {
                    this.q.remove(0);
                } catch (Throwable var16) {
                    var10000 = var16;
                    var10001 = false;
                    break;
                }

                ++var5;
            }
        }

        Throwable var6 = var10000;
        throw var6;
    }

    public void b(byte[] var1) {
        if (y.l) {
            y.d(new Object[]{"FixRecognitionThread::addData size =", var1.length, " first byte ", var1[0]});
        }

        this.i.add(var1);
    }

    public void c() {
        if (!this.l()) {
            y.c(new Object[]{"RecognitionThreadInterface::cancel"});
            this.k = null;
            this.n = true;
        }

    }

    public void c(long var1) throws Throwable {
        synchronized(this){}

        Throwable var10000;
        label213: {
            boolean var10001;
            try {
                y.c(new Object[]{"dropPcmFromByteLength =>", var1});
            } catch (Throwable var18) {
                var10000 = var18;
                var10001 = false;
                break label213;
            }

            int var3 = 0;
            long var4 = 0L;

            while(true) {
                label201: {
                    try {
                        if (var3 < this.q.size()) {
                            var4 += (long)((byte[])this.q.get(var3)).length;
                            break label201;
                        }
                    } catch (Throwable var17) {
                        var10000 = var17;
                        var10001 = false;
                        break label213;
                    }

                    var1 = 0L;
                    break;
                }

                if (var4 >= var1) {
                    var1 = (long)var3;
                    break;
                }

                ++var3;
            }

            var3 = 0;

            while(true) {
                if ((long)var3 >= var1) {
                    return;
                }

                try {
                    this.q.remove(0);
                } catch (Throwable var16) {
                    var10000 = var16;
                    var10001 = false;
                    break;
                }

                ++var3;
            }
        }

        Throwable var6 = var10000;
        throw var6;
    }

    protected void c(byte[] var1) throws Throwable {
        byte var2 = 0;
        synchronized(this){}

        Throwable var10000;
        label198: {
            boolean var10001;
            int var3;
            try {
                this.q.add(var1);
                var3 = this.q.size() - 1;
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
                        var4 += ((byte[])this.q.get(var3)).length;
                        if (var4 >= this.l.J()) {
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
                    this.q.remove(0);
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

    public void d() {
        if (!this.n) {
            y.c(new Object[]{"RecognitionThreadInterface::stopRecognition"});
            this.n = true;
        }

    }

    public void f() {
        this.j = null;
        this.k = null;
    }

    public boolean g() {
        return this.n;
    }

    protected byte[] h() throws InterruptedException {
        return (byte[])this.i.poll(h, TimeUnit.MILLISECONDS);
    }

    protected void i() {
        y.c(new Object[]{"RecognitionThreadInterface:doRecognitionMaxSpeechTimeout"});
        ak var1 = this.k;
        if (var1 != null) {
            var1.m();
        }

    }

    protected void j() {
        y.c(new Object[]{"RecognitionThreadInterface:onRecognitionVADTimeout"});
        ak var1 = this.k;
        if (var1 != null) {
            var1.l();
        }

    }

    protected void k() {
        y.c(new Object[]{"RecognitionThreadInterface:doOneshotStart"});
        ak var1 = this.k;
        if (var1 != null && var1 instanceof m) {
            ((m)var1).c();
        }

    }

    protected boolean l() {
        boolean var1 = true;
        if ((this.m == null || !this.m.c()) && this.k != null) {
            var1 = false;
        }

        return var1;
    }

    protected void m() {
        ak var1 = this.k;
        if (var1 != null) {
            var1.i();
        }

    }

    protected int n() {
        int var1;
        if (this.j != null) {
            var1 = this.j.a();
        } else {
            var1 = 0;
        }

        return var1;
    }

    public void o() {
        if (this.l.A() && !this.l.B()) {
            this.q.clear();
        }

    }

    public void p() {
        if (this.l.A() && this.q.size() > 0) {
            if ((long)this.q.size() <= this.l.i((long)this.l.O())) {
                byte[] var1 = com.unisound.common.j.a(this.s);
                this.r.add(var1);
            }

            this.r.addAll(this.q);
            this.r.addAll(this.i);
            this.q.clear();
        }

    }

    public void run() {
    }

    public void start() {
        super.start();
    }
}

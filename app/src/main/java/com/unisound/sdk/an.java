//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.sdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import cn.yunzhisheng.asr.JniUscClient;
import com.phicomm.speaker.device.utils.PhicommUtils;
import com.unisound.client.ErrorCode;
import com.unisound.common.a;
import com.unisound.common.ae;
import com.unisound.common.j;
import com.unisound.common.t;
import com.unisound.common.y;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.unisound.vui.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class an extends Thread {
    private static final int a = 30;
    private ak b = null;
    private BlockingQueue<byte[]> c = new LinkedBlockingQueue();
    private ai d = new ai();
    private as e;
    private String f = "";
    private volatile boolean g = false;
    private String h = "";
    private String i = "";
    private String j = "";
    private Context k;
    private String l = "";
    private boolean m = true;

    public an(as var1, Context var2, String var3) {
        this.k = var2;
        this.e = var1;
        ai.a = true;
    }

    private void a(int var1) {
        y.c(new Object[]{"NetRecognition --> RecognitionEvent = " + var1});
        ak var2 = this.b;
        if (var2 != null) {
            var2.k(var1);
        }

    }

    private void a(int var1, JniUscClient var2) {
        ak var3 = this.b;
        this.b();
        y.a(var2);
        if (var3 != null) {
            var3.a(var1);
        }

    }

    private void a(JniUscClient var1) {
        if (this.e.ap()) {
            var1.a(208, 3);
            var1.a(209, 3);
        }

        if (y.l) {
            var1.a(208, 3);
            var1.a(209, 3);
        }

        y.a("init", (String)null, (Map)null, (String)null, (String)null, (String)null);
        a var2 = this.e.bb();
        long var3 = var1.a(var2.b(), var2.c());
        y.c(new Object[]{"NetRecognition -- > server = ", var2.b(), " ip = ", var2.a(), " port = ", var2.c()});
        HashMap var5 = new HashMap();
        var5.put("server", var2.a());
        var5.put("port", String.valueOf(var2.c()));
        y.a("create", (String)null, var5, (String)null, (String)null, (String)null);
        if (var3 == 0L) {
            y.a("create", "error", var5, (String)null, (String)null, "handle=0");
            y.c(new Object[]{this.toString(), "juc.create() returns ", var3});
        }

        var1.a(0, this.e.bb);
        var1.a(1, this.e.bc);
        var1.a(6, this.e.ba);
        var1.a(4, this.e.bd);
        HashMap var6 = new HashMap();
        var6.put("enable_vad", String.valueOf(this.e.bb));
        var6.put("vad_timeout", String.valueOf(this.e.bc));
        var6.put("pcm_compress", String.valueOf(this.e.ba));
        var6.put("result_timeout", String.valueOf(this.e.bd));
        y.a("create", (String)null, var6, (String)null, (String)null, (String)null);
        this.b(var1);
        this.d(var1);
        this.c(var1);
        if (!this.e.an()) {
            this.a(var1, "Start");
            StringBuilder var7 = new StringBuilder();
            if (!this.e.aX().y()) {
                if (this.e.aW().d() && !this.e.aX().w()) {
                    var7.append(this.i).append("filterName=vpr;");
                } else if (!this.e.aW().d() && this.e.aX().w()) {
                    var7.append(this.j).append("filterName=search;");
                } else if (this.e.aW().d() && this.e.aX().w()) {
                    var7.append(this.i).append(this.j).append("filterName=vpr,search;");
                }
            } else {
                if (this.e.aW().d() && !this.e.aX().w()) {
                    var7.append(this.i);
                } else if (!this.e.aW().d() && this.e.aX().w()) {
                    var7.append(this.j);
                } else if (this.e.aW().d() && this.e.aX().w()) {
                    var7.append(this.i).append(this.j);
                }

                var7.append("filterName=nlu2;");
            }

            if (this.e.aS() != null && !"".equals(this.e.aS())) {
                var7.append("additionalService=" + this.e.aS() + ";");
            } else if (this.e.aR()) {
                var7.append("additionalService=wx_adapt;");
            }

            if (this.e.bt() > 0) {
                var7.append("req_nlu_length=").append(this.e.bt() + ";");
            }

            if (this.e.aX().z() != -1) {
                var7.append("appendLength=").append(this.e.aX().z() + ";");
            }

            if (this.e.bv()) {
                var7.append("maxWell=").append(this.e.bv() + ";");
            }

            if (this.e.bw() != null && !this.e.bw().equals("")) {
                var7.append("retType=").append(this.e.bw() + ";");
            }

            var7.append("fullDuplex=").append(this.e.bz() + ";");
            var7.append("audioUrl=").append(this.e.by() + ";");
            var7.append("filterUrl=").append(this.e.bq() + ";");
            if (this.e.aT() == null || this.e.aT().equals("")) {
                var1.a(201, var7.toString());
                y.c(new Object[]{"juc init traficParams = ", var7.toString()});
            }
        }

        if (this.e.aT() != null && !this.e.aT().equals("")) {
            var1.a(201, this.e.aT());
            y.c(new Object[]{"juc init external setting traficParams = ", this.e.aT()});
        }

        if (this.e.aP() != this.e.be()) {
            var1.a(320, this.e.aP());
            var1.a(321, this.e.be());
        }

        y.x = 0;
        y.c(new Object[]{"juc init success"});
    }

    private void a(JniUscClient var1, com.unisound.common.ak var2) {
        if (var2 != null && var2.a()) {
            int var3 = var1.a(31, var2.c());
            y.a("updateAsrScene " + var2.c() + " res : " + var3);
            var2.a(false);
        }

    }

    private void a(JniUscClient var1, String var2) {
        cl var3 = this.e.aX();
        var3.b(System.currentTimeMillis());
        if (var3 != null && var3.w()) {
            this.j = var3.A();
            y.c(new Object[]{"NetRecognition --> ", var2, " NluParams : ", var3.A()});
            HashMap var4 = new HashMap();
            var4.put("nlu_sendParams", var3.A());
            y.a("initNLU", (String)null, var4, (String)null, (String)null, var2);
        }

    }

    private void a(JniUscClient var1, String var2, String var3, boolean var4) {
        if (!var3.equals("null")) {
            al var5 = new al();
            var5.a(am.a);
            var5.b(var2);
            var2 = var1.c(61);
            var5.a(PhicommUtils.hookRawAsr(var2));

            if (var3.equals("variable")) {
                var5.b(true);
            } else {
                if (var4) {
                    var3 = var1.c(62);
                    if (!TextUtils.isEmpty(var3) && Integer.valueOf(var3) > 0) {
                        var5.a(false);
                    } else {
                        var5.a(true);
                    }
                }

                var5.b(false);
            }

            this.a(var5);
            if (!TextUtils.isEmpty(var2) && this.b(var2).a() != 0) {
                this.m = false;
            }
            // no correct error
            if (!this.m) {
                this.a(ErrorCode.ASR_TOKEN_ERROR, var1);
            }
        }

    }

    private void a(JniUscClient var1, String var2, boolean var3) {
        while(true) {
            String var4 = var1.c(60);
            if (var4.equals("no")) {
                return;
            }

            this.a(var1, var2, var4, var3);
        }
    }

    private void a(al var1) {
        y.c(new Object[]{"NetRecognition --> onRecognitionResult=>" + var1.toString()});
        ak var2 = this.b;
        if (var2 != null) {
            var2.a(var1);
        }

    }

    private String[] a(String var1) {
        byte var2 = 0;
        int var3 = 0;
        String[] var4;
        if (var1.contains("}{\"asr_recongize\"")) {
            for(var4 = var1.split("\\}\\{\"asr_recongize\""); var3 < var4.length; ++var3) {
                if (var3 == 0) {
                    var4[var3] = var4[var3] + "}";
                } else if (var3 == var4.length - 1) {
                    var4[var3] = "{\"asr_recongize\"" + var4[var3];
                } else {
                    var4[var3] = "{\"asr_recongize\"" + var4[var3] + "}";
                }
            }
        } else if (var1.contains("}{\"gender\"")) {
            var4 = var1.split("\\}\\{\"gender\"");

            for(var3 = var2; var3 < var4.length; ++var3) {
                if (var3 == 0) {
                    var4[var3] = var4[var3] + "}";
                } else if (var3 == var4.length - 1) {
                    var4[var3] = "{\"gender\"" + var4[var3];
                } else {
                    var4[var3] = "{\"gender\"" + var4[var3] + "}";
                }
            }
        } else {
            var4 = new String[]{var1};
        }

        return var4;
    }

    private cu b(String var1) {
        cu var2 = new cu();
        y.d(new Object[]{"partial=", var1});

        JSONException var10000;
        label30: {
            boolean var10001;
            JSONObject var3;
            int var4;
            StringBuilder var8;
            try {
                var3 = new JSONObject(var1);
                if (!var1.contains("returnCode")) {
                    return var2;
                }

                var4 = var3.getInt("returnCode");
                var8 = new StringBuilder();
                y.d(new Object[]{"USCDEBUG", var8.append("returnCode=").append(var4).toString()});
                var2.a(var4);
            } catch (JSONException var7) {
                var10000 = var7;
                var10001 = false;
                break label30;
            }

            if (var4 == 0) {
                var8 = new StringBuilder();
                y.d(new Object[]{var8.append("errorInfo=").append("").toString()});
                var2.a("");
                return var2;
            } else {
                try {
                    String var10 = var3.getString("errorInfo");
                    var8 = new StringBuilder();
                    y.d(new Object[]{"USCDEBUG", var8.append("errorInfo=").append(var10).toString()});
                    var2.a(var10);
                    return var2;
                } catch (JSONException var6) {
                    var10000 = var6;
                    var10001 = false;
                }
            }
        }

        JSONException var9 = var10000;
        var9.printStackTrace();
        return var2;
    }

    private void b(JniUscClient var1) {
        ct var2 = this.e.aW();
        if (var2.d()) {
            var1.a(1015, 8);
            var1.a(1020, 1);
            var1.a(1019, this.e.bi());
            StringBuilder var3 = new StringBuilder();
            var3.append("type=");
            if (var2.e() == 1) {
                var3.append("register");
            } else if (var2.e() == 2) {
                var3.append("matchSingle");
            }

            var3.append(";");
            var3.append("userName=").append(var2.b()).append(";");
            var3.append("appkey=").append(this.e.aZ()).append(";");
            var3.append("returnType=").append("json").append(";");
            var3.append("scene=").append(var2.a()).append(";");
            y.c(new Object[]{"vpr params:  ", var3.toString()});
            this.i = var3.toString();
            HashMap var4 = new HashMap();
            var4.put("vpr_init", String.valueOf(8));
            var4.put("vpr_md5_check", String.valueOf(1));
            var4.put("vpr_secret", this.e.bi());
            var4.put("vpr_sendParams", var3.toString());
            y.a("initVPR", (String)null, var4, (String)null, (String)null, (String)null);
        }

    }

    private void c(JniUscClient var1) {
        StringBuilder var2 = new StringBuilder();
        var2.append("PN=" + com.unisound.c.a.s).append(":");
        var2.append("OS=0").append(":");
        var2.append("CR=" + com.unisound.c.a.r).append(":");
        var2.append("NT=" + this.e.aZ).append(":");
        var2.append("MD=" + com.unisound.c.a.t).append(":");
        var2.append("SV=" + com.unisound.common.an.a()).append(":");
        var2.append("RPT=" + this.e.bm()).append(":");
        var2.append("SID=" + this.e.bj()).append(":");
        var2.append("NPT=" + this.e.bo()).append(":");
        var2.append("IP=" + ae.a(this.k)).append(":");
        var2.append("EC=" + this.e.bn());
        var2.append("\t" + y.x + ":" + JniUscClient.l + ":" + JniUscClient.m);
        var1.a(15, var2.toString());
        this.e.Q(0);
        this.e.k(0L);
        this.e.x("");
        HashMap var3 = new HashMap();
        var3.put("nlu_sendParams", var2.toString());
        y.c(new Object[]{"collected_info = ", var2.toString()});
        y.a("collectedInfo", (String)null, var3, (String)null, (String)null, (String)null);
    }

    private void d(JniUscClient var1) {
        i var2 = this.e.ba();
        if (var2.a()) {
            HashMap var3 = new HashMap();
            var1.a(34, var2.toString());
            var3.put("engine_parameter", var2.toString());
            y.c(new Object[]{"NetRecognition --> AsrParams : ", var2.toString().replaceAll(":", "=").replaceAll("\\n", ";")});
            this.a(var1, this.e.aY());
            if (this.e.aG) {
                if (this.e.aJ) {
                    var1.a(20, "open");
                } else {
                    var1.a(20, "close");
                }
            }

            if (this.e.aW != 0) {
                var1.a(32, this.e.aW);
            }

            var1.a(this.e.bf());
            if (!TextUtils.isEmpty(this.e.aq()) && "pcm".equals(this.e.aq())) {
                y.c(new Object[]{"NetRecognition --> setAudioFormat : ", var1.a("pcm")});
            }
        }

        var1.a(9, this.e.aZ());
        var1.a(8, com.unisound.c.a.q);
        String var5 = com.unisound.c.a.a(this.e.aZ());
        String var4;
        if (this.e.bu() != null) {
            var4 = this.e.bu();
        } else {
            var4 = var5;
        }

        var1.a(14, var4);
        var1.a(22, var5);
        y.c(new Object[]{"NetRecognition --> appkey = ", this.e.aZ(), ", imei = ", com.unisound.c.a.q, ", userId = ", var5, ", udid = ", var5});
        if (this.e.bh()) {
            var1.a(17, "req_audio_url");
        }

        if (this.e.br() != null) {
            var1.a(151, this.e.br());
        }

    }

    private void e(JniUscClient var1) {
        ak var2 = this.b;
        this.b();
        y.a(var1);
        if (var2 != null) {
            var2.i();
        }

    }

    private void f(JniUscClient var1) {
        ak var2 = this.b;
        this.b();
        y.a(var1);
        if (var2 != null) {
            var2.m();
        }

    }

    private int g(JniUscClient var1) {
        y.c(new Object[]{"NetRecognition --> continue recognize"});
        var1.b();
        if (this.e.ar()) {
            String var2 = this.e.as();
            if (!TextUtils.isEmpty(var2)) {
                com.unisound.common.j.a(var2 + File.separator + this.e.bj() + ".wav", 1, this.e.be());
            }
        }

        this.a(var1, this.f, true);
        byte var3;
        if (!this.m) {
            var3 = -1;
        } else {
            this.f = "";
            y.c(new Object[]{"NetRecognition --> start called "});
            this.a(var1);
            y.c(new Object[]{"RecognitionThread : Service Mode =  ", this.e.aQ()});
            var1.a(1015, this.e.aQ());
            var1.a(207, this.e.bs());
            int var4 = var1.a();
            this.f = var1.c(21);
            this.e.x(this.f);
            this.a(1140);
            y.c(new Object[]{"NetRecognition --> sessionId = ", this.f});
            this.l = "";
            if (this.f != null && this.f.length() > 10) {
                this.l = this.f.substring(0, 10);
            }

            if (var4 != 0) {
                y.a("start", "error", (Map)null, (String)null, String.valueOf(var4), (String)null);
                y.c(new Object[]{"NetRecognition --> continue Recognition start error occured! , startCode = ", var4, ", sessionId = ", this.f});
                this.a(var4, var1);
                var1.e();
                this.e.bc();
                var3 = -1;
            } else {
                var3 = 0;
            }
        }

        return var3;
    }

    public void a(ak var1) {
        this.b = var1;
    }

    public void a(Collection<? extends byte[]> var1) {
        this.c.addAll(var1);
    }

    public void a(List<byte[]> var1) {
        Iterator var3 = var1.iterator();

        while(var3.hasNext()) {
            byte[] var2 = (byte[])var3.next();
            this.c.add(var2);
        }

    }

    public void a(byte[] var1) {
        this.d.a(var1, 0, var1.length);
        if (this.d.b()) {
            this.c.add(var1);
        }

    }

    public boolean a() {
        return this.g;
    }

    public void b() {
        this.g = true;
    }

    public void c() {
        this.b = null;
        this.g = true;
    }

    public boolean d() {
        boolean var1;
        if (this.b == null) {
            var1 = true;
        } else {
            var1 = false;
        }

        return var1;
    }

    public String e() {
        return this.f;
    }

    public void f() {
        ak var1 = this.b;
        if (var1 != null) {
            var1.A();
        }

    }

    public void g() {
        this.c();
        if (this.isAlive()) {
            try {
                this.join(3900L);
                y.c(new Object[]{"RecognitionThread::waitEnd()"});
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        }

    }

    public String h() {
        return this.h;
    }

    public void run() {
        y.c(new Object[]{"NetRecogniton -> run start"});
        if (y.l) {
            y.d(new Object[]{"RecognitionThread -> ThreadName = ", Thread.currentThread().getName(), ", ThreadId = ", Thread.currentThread().getId()});
        }

        y.g(new Object[]{"RecognitionThread start"});
        JniUscClient var1 = new JniUscClient();
        this.a(var1);
        y.c(new Object[]{"RecognitionThread : Service Mode =  ", this.e.aQ()});
        var1.a(1015, this.e.aQ());
        var1.a(207, this.e.bs());
        this.f = "";
        y.c(new Object[]{"NetRecognition --> start called "});
        int var2 = var1.a();
        this.f = var1.c(21);
        this.e.x(this.f);
        this.a(1140);
        y.c(new Object[]{"NetRecognition --> sessionId = ", this.f});
        this.l = "";
        if (this.f != null && this.f.length() > 10) {
            this.l = this.f.substring(0, 10);
        }

        if (var2 != 0) {
            y.a("start", "error", (Map)null, (String)null, String.valueOf(var2), (String)null);
            y.c(new Object[]{"NetRecognition --> start error occured! , startCode = ", var2, ", sessionId = ", this.f});
            this.a(var2, var1);
            var1.e();
            this.e.bc();
        } else {
            y.a("start", "success", (Map)null, (String)null, (String)null, (String)null);
            if (this.d()) {
                var1.d();
                y.a("cancel", (String)null, (Map)null, (String)null, (String)null, "cancel(start)");
                y.c(new Object[]{"NetRecognition --> cancel(start)"});
                var1.e();
            } else {
                long var3 = 0L;

                long var7;
                String var9;
                label247: {
                    while(true) {
                        boolean var10001;
                        byte[] var5;
                        try {
                            var5 = (byte[])this.c.poll(30L, TimeUnit.MILLISECONDS);
                        } catch (Exception var21) {
                            var10001 = false;
                            break;
                        }

                        if (var5 != null) {
                            label232: {
                                label229: {
                                    try {
                                        if (var5.length != 1) {
                                            break label229;
                                        }
                                    } catch (Exception var27) {
                                        var10001 = false;
                                        break;
                                    }

                                    if (var5[0] == 100 || var5[0] == 99) {
                                        boolean var6;
                                        try {
                                            var6 = this.e.an();
                                        } catch (Exception var14) {
                                            var10001 = false;
                                            break;
                                        }

                                        if (!var6) {
                                            var7 = var3;
                                            break label247;
                                        }
                                        break label232;
                                    }
                                }

                                label201: {
                                    try {
                                        if (!this.d()) {
                                            var2 = var1.a(var5, var5.length);
                                            break label201;
                                        }
                                    } catch (Exception var26) {
                                        var10001 = false;
                                        break;
                                    }

                                    var2 = 0;
                                }

                                try {
                                    if (this.e.ar()) {
                                        var9 = this.e.as();
                                        if (!TextUtils.isEmpty(var9)) {
                                            StringBuilder var10 = new StringBuilder();
                                            com.unisound.common.j.a(var5, var10.append(var9).append(File.separator).append(this.e.bj()).append(".wav").toString());
                                        }
                                    }
                                } catch (Exception var20) {
                                    var10001 = false;
                                    break;
                                }

                                try {
                                    if (y.q) {
                                        com.unisound.common.j.a(var5, "/sdcard/asrtest/oneshotonline.pcm");
                                    }
                                } catch (Exception var25) {
                                    var10001 = false;
                                    break;
                                }

                                try {
                                    var7 = (long)var5.length + var3;
                                } catch (Exception var19) {
                                    var10001 = false;
                                    break;
                                }

                                if (var2 >= 0 && var2 != 5) {
                                    try {
                                        this.a(var1, this.f, false);
                                    } catch (Exception var18) {
                                        var10001 = false;
                                        break;
                                    }

                                    var3 = var7;
                                } else if (var2 == 5) {
                                    try {
                                        y.c(new Object[]{"NetRecognition --> server vad stop"});
                                        this.f();
                                    } catch (Exception var17) {
                                        var10001 = false;
                                        break;
                                    }

                                    var3 = var7;
                                } else if (var2 == -30002) {
                                    try {
                                        y.a("recognition", (String)null, (Map)null, (String)null, (String)null, "max speech timeout");
                                        y.c(new Object[]{"NetRecognition --> max speech timeout"});
                                        this.f(var1);
                                    } catch (Exception var16) {
                                        var10001 = false;
                                        break;
                                    }

                                    var3 = var7;
                                } else if (var2 == -30001) {
                                    try {
                                        y.a("recognition", (String)null, (Map)null, (String)null, (String)null, "vad timeout");
                                        y.c(new Object[]{"NetRecognition --> vad timeout"});
                                    } catch (Exception var15) {
                                        var10001 = false;
                                        break;
                                    }

                                    var3 = var7;
                                } else {
                                    label181: {
                                        if (var2 == -91155) {
                                            try {
                                                if (this.e.an()) {
                                                    break label181;
                                                }
                                            } catch (Exception var24) {
                                                var10001 = false;
                                                break;
                                            }
                                        }

                                        try {
                                            y.a("recognition", "error", (Map)null, (String)null, String.valueOf(var2), (String)null);
                                            y.c(new Object[]{"NetRecognition --> error:", var2});
                                            this.a(var2, var1);
                                            var1.e();
                                            this.e.bc();
                                            return;
                                        } catch (Exception var11) {
                                            var10001 = false;
                                            break;
                                        }
                                    }

                                    var3 = var7;

                                    try {
                                        if (this.g(var1) != 0) {
                                            break label247;
                                        }
                                    } catch (Exception var13) {
                                        var10001 = false;
                                        break;
                                    }
                                }
                            }
                        }

                        label168: {
                            try {
                                if (!this.e.al() || !this.e.an()) {
                                    break label168;
                                }

                                var2 = this.g(var1);
                                this.e.v(false);
                            } catch (Exception var23) {
                                var10001 = false;
                                break;
                            }

                            var7 = var3;
                            if (var2 != 0) {
                                break label247;
                            }
                        }

                        label160: {
                            try {
                                if (!this.g || !this.c.isEmpty()) {
                                    break label160;
                                }

                                y.c(new Object[]{"NetRecognition --> break"});
                            } catch (Exception var22) {
                                var10001 = false;
                                break;
                            }

                            var7 = var3;
                            break label247;
                        }

                        try {
                            if (this.d()) {
                                var1.d();
                                y.a("recognition", (String)null, (Map)null, (String)null, (String)null, "cancel(recognizer)");
                                y.c(new Object[]{"NetRecognition --> cancel(recognizer)"});
                                var1.e();
                                return;
                            }
                        } catch (Exception var12) {
                            var10001 = false;
                            break;
                        }
                    }

                    y.a("NetRecognition --> exception");
                    y.a("recognition", "error", (Map)null, (String)null, String.valueOf(-62001), "recognition exception");
                    this.a(-62001, var1);
                    JniUscClient.l = -62001;
                    JniUscClient.m = 0;
                    var1.e();
                    return;
                }

                this.e.j(System.currentTimeMillis());
                this.h = "";
                if (!this.e.an()) {
                    this.a(var1, "Stop");
                }

                y.c(new Object[]{"NetRecognition --> stop called , bufferLength = ", var7, ", sessionId = ", this.l});
                var2 = var1.b();
                if (this.e.ar()) {
                    var9 = this.e.as();
                    if (!TextUtils.isEmpty(var9)) {
                        com.unisound.common.j.a(var9 + File.separator + this.e.bj() + ".wav", 1, this.e.be());
                    }
                }

                if (var2 < 0) {
                    y.c(new Object[]{"NetRecognition --> stop error occured! , stopCode = ", var2, ", sessionId = ", this.f});
                    y.a("stop", "error", (Map)null, (String)null, String.valueOf(var2), (String)null);
                    this.a(var2, var1);
                    var1.e();
                } else {
                    y.a("stop", "success", (Map)null, (String)null, (String)null, (String)null);
                    if (this.e.aW != 0) {
                        this.e.aY = t.a(var1.c(25));
                        y.c(new Object[]{"NetRecognition --> asrRspSpeakerInfo=", this.e.aY});
                    }

                    this.a(var1, this.f, true);
                    this.e(var1);
                    y.c(new Object[]{"NetRecognition --> released"});
                    var1.e();
                    this.c.clear();
                    y.g(new Object[]{"RecognitionThread stop"});
                    y.c(new Object[]{"NetRecognition --> run stop"});
                }
            }
        }

    }
}

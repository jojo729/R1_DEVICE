package cn.yunzhisheng.asrfix;

public class JniAsrFix {
    private static cn.yunzhisheng.asrfix.JniAsrFix D;
    private static java.util.List E;
    final private static int F = 0;
    final private static int G = -1;
    final private static int H = -2;
    final private static int I = -3;
    final private static int J = 100;
    final private static int K = 16;
    final private static int L = 500;
    final private static int M = 502;
    private static java.util.ArrayList R;
    final public static int a = 0;
    final public static int b = 1;
    final public static int c = 2;
    final public static int d = 3;
    final public static int e = -1;
    final public static int f = -2;
    final public static int g = -3;
    final public static int h = -4;
    final public static int i = -5;
    final public static int j = -6;
    final public static int k = -7;
    final public static int l = -8;
    final public static int m = -9;
    final public static int n = -11;
    final public static int o = -12;
    final public static int p = 0;
    final public static int q = 1;
    final public static int r = 2;
    final public static int s = 3;
    final public static int t = 4;
    final public static int u = 5;
    final public static int v = 6;
    final public static int w = 7;
    final public static int x = 8;
    final public static int y = 9;
    final public static int z = 22;
    boolean A;
    protected java.util.concurrent.BlockingQueue B;
    private Object C;
    private boolean N;
    private int O;
    private com.unisound.sdk.u P;
    private int Q;
    private boolean S;
    private boolean T;
    private boolean U;

    static {
        D = null;
        System.loadLibrary("asrfix");
    }

    private JniAsrFix() {
        this.C = new Object();
        this.N = false;
        this.O = 1501;
        this.P = null;
        this.Q = 1;
        this.A = false;
        this.S = true;
        this.T = false;
        this.B = (java.util.concurrent.BlockingQueue)(Object)new java.util.concurrent.LinkedBlockingQueue();
        this.U = false;
    }

    public static int a(int i0) {
        if (i0 > -12 && i0 < 0) {
            i0 = i0 - 63600;
        }
        return i0;
    }

    static int a(cn.yunzhisheng.asrfix.JniAsrFix a0, int i0) {
        a0.O = i0;
        return i0;
    }

    static int a(cn.yunzhisheng.asrfix.JniAsrFix a0, String s0, String s1) {
        return a0.reset(s0, s1);
    }

    public static cn.yunzhisheng.asrfix.JniAsrFix a() {
        if (D == null) {
            D = new cn.yunzhisheng.asrfix.JniAsrFix();
            E = (java.util.List)(Object)new java.util.ArrayList();
            R = new java.util.ArrayList();
        }
        return D;
    }

    static boolean a(cn.yunzhisheng.asrfix.JniAsrFix a0) {
        return a0.S;
    }

    static boolean a(cn.yunzhisheng.asrfix.JniAsrFix a0, boolean b0) {
        a0.T = b0;
        return b0;
    }

    public static boolean a(String s0) {
        return cn.yunzhisheng.asrfix.JniAsrFix.crcCheck(s0) == 0;
    }

    static boolean b(cn.yunzhisheng.asrfix.JniAsrFix a0) {
        return a0.N;
    }

    static boolean b(cn.yunzhisheng.asrfix.JniAsrFix a0, boolean b0) {
        a0.S = b0;
        return b0;
    }

    static int c(cn.yunzhisheng.asrfix.JniAsrFix a0) {
        return a0.O;
    }

    native private int cancel();


    native private int check_wav_end();


    native public static int compileDecodeNet(String arg, String arg0);


    native private static int crcCheck(String arg);


    static com.unisound.sdk.u d(cn.yunzhisheng.asrfix.JniAsrFix a0) {
        return a0.P;
    }

    native private int getOptionInt(int arg);


    native private String getOptionString(int arg, String arg0);


    native private String getResult();


    native public static String getVersion();


    native private int init(String arg, String arg0);


    native private int isEngineIdle();


    native private int isactive(byte[] arg, int arg0);


    native private int recognize(byte[] arg, int arg0);


    native private void release();


    native private int reset(String arg, String arg0);


    native private String search(String arg, String arg0);


    native private int setActiveNet(int arg);


    native private int setOptionInt(int arg, int arg0);


    native private int setOptionString(int arg, String arg0);


    native private int start(String arg, int arg0);


    native private int stop();


    native private void trackInfo(int arg);


    static cn.yunzhisheng.asrfix.JniAsrFix w() {
        return D;
    }

    private void x() {
        if (!this.U) {
            this.a((byte[])null, 0);
            this.g();
        }
    }

    public int a(int i0, int i1) {
        int i2 = 0;
        if (this.U) {
            i2 = this.setOptionInt(i0, i1);
            if (i2 < 0) {
                i2 = cn.yunzhisheng.asrfix.JniAsrFix.a(i2);
            }
        } else {
            i2 = -63502;
        }
        return i2;
    }

    public int a(int i0, String s0) {
        int i1 = 0;
        if (this.U) {
            i1 = this.setOptionString(i0, s0);
            if (i1 < 0) {
                i1 = cn.yunzhisheng.asrfix.JniAsrFix.a(i1);
            }
        } else {
            i1 = -63502;
        }
        return i1;
    }

    public int a(long j0, int i0) {
        int i1 = 0;
        if (this.U) {
            i1 = this.grammarCompilerSetOptionInt(j0, 1000, i0);
            if (i1 < 0) {
                i1 = cn.yunzhisheng.asrfix.JniAsrFix.a(i1);
            }
        } else {
            i1 = -63502;
        }
        return i1;
    }

    public int a(long j0, String s0, String s1) {
        int i0 = 0;
        if (E.contains((Object)s0)) {
            com.unisound.common.y.a(new StringBuilder().append("loadGompiledJsgf failed , the jsgf.dat of this grammarTag is already exists! The grammarTag is ").append(s0).toString());
            i0 = -1;
        } else {
            i0 = this.loadCompiledJsgf(j0, s1);
            if (i0 == 0) {
                E.add((Object)s0);
            }
        }
        return i0;
    }

    public int a(long j0, String s0, String s1, String s2) {
        int i0 = 0;
        cn.yunzhisheng.asrfix.JniAsrFix a0 = D;
        /*monenter(a0)*/;
        label3: try {
            this.S = false;
            if (j0 != 0L) {
                int i1 = 0;
                boolean b0 = E.contains((Object)s0);
                label1: {
                    label2: {
                        if (!b0) {
                            break label2;
                        }
                        Object[] a1 = new Object[1];
                        a1[0] = "compileDynamicUserData : grammarDat is loaded so compile directly!";
                        com.unisound.common.y.c(a1);
                        i1 = 0;
                        break label1;
                    }
                    Object[] a2 = new Object[4];
                    a2[0] = "compileDynamicUserData  loadedGrammar = + ";
                    a2[1] = s0;
                    a2[2] = " grammarPath= ";
                    a2[3] = s1;
                    com.unisound.common.y.c(a2);
                    i0 = this.loadCompiledJsgf(j0, s1);
                    label0: {
                        if (i0 != 0) {
                            break label0;
                        }
                        E.add((Object)s0);
                        Object[] a3 = new Object[1];
                        a3[0] = "compileDynamicUserData loadCompiledJsgf success!";
                        com.unisound.common.y.c(a3);
                        i1 = 0;
                        break label1;
                    }
                    com.unisound.common.y.a(new StringBuilder().append("compileDynamicUserData loadCompileJsgf error!  loadCompiledJsgfResult = ").append(i0).toString());
                    /*monexit(a0)*/;
                    break label3;
                }
                while(this.isEngineIdle() != 1 && i1 < 2000) {
                    InterruptedException a4 = null;
                    try {
                        Thread.sleep(50L);
                        i1 = i1 + 50;
                        continue;
                    } catch(InterruptedException a5) {
                        a4 = a5;
                    }
                    a4.printStackTrace();
                }
                i0 = this.compileDynamicUserData(j0, s2, s0);
                if (i0 != 0) {
                    Object[] a6 = new Object[2];
                    a6[0] = "compileDynamicUserData : compile failed!";
                    a6[1] = Integer.valueOf(i1);
                    com.unisound.common.y.c(a6);
                } else {
                    Object[] a7 = new Object[2];
                    a7[0] = "compileDynamicUserData : compile success! ";
                    a7[1] = Integer.valueOf(i1);
                    com.unisound.common.y.c(a7);
                }
                this.S = true;
                /*monexit(a0)*/;
            } else {
                Object[] a8 = new Object[1];
                a8[0] = "compile  compileDynamicUserData fail handle=0";
                com.unisound.common.y.c(a8);
                /*monexit(a0)*/;
                i0 = -63503;
            }
        } catch(Throwable a9) {
            Throwable a10 = a9;
            while(true) {
                try {
                    /*monexit(a0)*/;
                } catch(IllegalMonitorStateException | NullPointerException a11) {
                    Throwable a12 = a11;
                    a10 = a12;
                    continue;
                }
               break;
            }
        }
        return i0;
    }

    public int a(String s0, int i0) {
        int i1 = 0;
        this.x();
        if (this.U) {
            Object[] a0 = new Object[2];
            a0[0] = "JniAsrFix : start_ -> recognizerStatus = ";
            a0[1] = Integer.valueOf(this.O);
            com.unisound.common.y.c(a0);
            if (this.O != 1501) {
                i1 = -63999;
            } else {
                i1 = this.start(s0, i0);
                if (i1 == 0) {
                    this.N = true;
                    this.O = 1502;
                    this.P.a(this.O);
                }
                if (i1 < 0) {
                    i1 = cn.yunzhisheng.asrfix.JniAsrFix.a(i1);
                }
            }
        } else {
            i1 = -63502;
        }
        return i1;
    }

    public int a(String s0, String s1, com.unisound.sdk.w a0, String s2) {
        this.S = false;
        if (this.U) {
            Object[] a1 = new Object[4];
            a1[0] = "Recognizer.loadModel queue add ";
            a1[1] = s0;
            a1[2] = " ";
            a1[3] = s1;
            com.unisound.common.y.c(a1);
            this.B.add((Object)s0);
            this.B.add((Object)s1);
            this.B.add((Object)s2);
            if (!this.T) {
                new cn.yunzhisheng.asrfix.a(this, a0).start();
            }
        } else {
            com.unisound.common.y.a("Recognizer.loadModel not init Error");
        }
        return 0;
    }

    public int a(String s0, String s1, String s2, com.unisound.sdk.w a0) {
        this.i();
        this.A = false;
        if (D != null) {
            D.a(Boolean.valueOf(a0.aw()));
        }
        int i0 = this.init(s0, s1);
        if (i0 != 0) {
            this.P.b(1300, -63501);
        } else {
            if (a0.aN() != -1 && D != null) {
                D.j(a0.aN());
            }
            this.O = 1501;
            this.U = true;
            if ("init_asr" == s2) {
                this.Q = this.getOptionInt(100);
                int i1 = 0;
                while(i1 < this.Q) {
                    int i2 = this.setOptionInt(16, i1);
                    Object[] a1 = new Object[5];
                    a1[0] = "JniAsrFix ";
                    a1[1] = "modelNum = ";
                    a1[2] = Integer.valueOf(this.Q);
                    a1[3] = ", modelId = ";
                    a1[4] = Integer.valueOf(i2);
                    com.unisound.common.y.c(a1);
                    R.add((Object)Integer.valueOf(i2));
                    i1 = i1 + 1;
                }
                a0.a((java.util.List)(Object)R);
                if (this.Q < 2) {
                    int i3 = this.setOptionInt(16, 0);
                    Object[] a2 = new Object[4];
                    a2[0] = "JniAsrFix :modelNum = ";
                    a2[1] = Integer.valueOf(this.Q);
                    a2[2] = ", defaulltModelId = ";
                    a2[3] = Integer.valueOf(i3);
                    com.unisound.common.y.c(a2);
                    a0.E(i3);
                    a0.D(i3);
                }
                this.P.a(1129, (int)System.currentTimeMillis());
            }
        }
        return cn.yunzhisheng.asrfix.JniAsrFix.a(i0);
    }

    public int a(String s0, String s1, String s2, String s3, String s4, String s5) {
        int i0 = 0;
        Object[] a0 = new Object[1];
        a0[0] = "compile  initUserDataCompiler";
        com.unisound.common.y.c(a0);
        long j0 = this.initUserDataCompiler(s3);
        if (j0 != 0L) {
            Object[] a1 = new Object[13];
            a1[0] = "compile  compileUserData ===handle,inPartialFile, jsgf, szContent, netDat,outPartialFile";
            a1[1] = " = ";
            a1[2] = Long.valueOf(j0);
            a1[3] = " , ";
            a1[4] = s0;
            a1[5] = " , ";
            a1[6] = s1;
            a1[7] = " , ";
            a1[8] = s2;
            a1[9] = " , ";
            a1[10] = s4;
            a1[11] = " , ";
            a1[12] = s5;
            com.unisound.common.y.c(a1);
            i0 = this.partialCompileUserData(j0, s0, s1, s2, s4, s5);
            Object[] a2 = new Object[1];
            a2[0] = "compile  destroyUserDataCompiler";
            com.unisound.common.y.c(a2);
            this.destroyUserDataCompiler(j0);
            if (i0 != 0) {
                if (i0 != -10) {
                    com.unisound.common.y.a(new StringBuilder().append("compile  compileUserData fail code = ").append(i0).toString());
                    i0 = cn.yunzhisheng.asrfix.JniAsrFix.a(i0);
                } else {
                    com.unisound.common.y.a("compile compileUserData partialfile error, autofix ok");
                    i0 = 0;
                }
            } else {
                Object[] a3 = new Object[1];
                a3[0] = "compile  compileUserData ok";
                com.unisound.common.y.c(a3);
            }
        } else {
            i0 = -63503;
        }
        return i0;
    }

    public int a(boolean b0) {
        return b0 ? this.setOptionInt(125, 0) : this.setOptionInt(125, 1);
    }

    public int a(byte[] a0, int i0) {
        int i1 = 0;
        if (this.U) {
            i1 = this.isactive(a0, i0);
            if (i1 < 0) {
                i1 = cn.yunzhisheng.asrfix.JniAsrFix.a(i1);
            }
        } else {
            i1 = -63502;
        }
        return i1;
    }

    public String a(long j0) {
        return (this.U) ? (j0 != 0L) ? this.grammarCompilerGetOptionString(j0, 1002) : "handle is 0" : "";
    }

    public String a(String s0, String s1) {
        return (this.U) ? this.search(s0, s1) : null;
    }

    public void a(com.unisound.sdk.u a0) {
        this.P = a0;
    }

    public void a(Boolean a0) {
        if (a0.booleanValue()) {
            this.setOptionInt(12, 1);
        } else {
            this.setOptionInt(12, 0);
        }
    }

    public int b(int i0) {
        return (this.t()) ? this.setOptionInt(108, i0) : this.setOptionInt(17, i0);
    }

    public int b(long j0, int i0) {
        int i1 = 0;
        if (this.U) {
            i1 = this.grammarCompilerSetOptionInt(j0, 1001, i0);
            if (i1 < 0) {
                i1 = cn.yunzhisheng.asrfix.JniAsrFix.a(i1);
            }
        } else {
            i1 = -63502;
        }
        return i1;
    }

    public int b(boolean b0) {
        return b0 ? this.setOptionInt(126, 0) : this.setOptionInt(126, 1);
    }

    public int b(byte[] a0, int i0) {
        return (this.U) ? this.recognize(a0, i0) : -63502;
    }

    public void b() {
        int i0 = this.cancel();
        if (i0 != 0) {
            Object[] a0 = new Object[2];
            a0[0] = "JniAsrFix : cancel failed , result code = ";
            a0[1] = Integer.valueOf(i0);
            com.unisound.common.y.c(a0);
        }
    }

    public int c(int i0) {
        return this.setOptionInt(18, i0 / 10);
    }

    public boolean c() {
        return this.A;
    }

    native public int compileDynamicUserData(long arg, String arg0, String arg1);


    native public int compileUserData(long arg, String arg0, String arg1, String arg2);


    public int d(int i0) {
        return this.setOptionInt(120, i0);
    }

    public void d() {
        this.A = true;
    }

    native public void destroyUserDataCompiler(long arg);


    public int e() {
        int i0 = 0;
        if (this.U) {
            i0 = this.stop();
            if (i0 == 0) {
                this.O = 1501;
                this.P.a(this.O);
                this.N = false;
            }
            if (i0 < 0) {
                i0 = cn.yunzhisheng.asrfix.JniAsrFix.a(i0);
            }
        } else {
            i0 = -63502;
        }
        return i0;
    }

    public int e(int i0) {
        return this.setOptionInt(200, i0);
    }

    public int f(int i0) {
        return this.setOptionInt(201, i0);
    }

    public String f() {
        return (this.U) ? this.getResult() : "";
    }

    public int g() {
        int i0 = 0;
        if (this.U) {
            i0 = this.cancel();
            if (i0 == 0) {
                this.O = 1501;
                this.P.a(this.O);
                this.N = false;
            }
            if (i0 < 0) {
                i0 = cn.yunzhisheng.asrfix.JniAsrFix.a(i0);
            }
        } else {
            i0 = -63502;
        }
        return i0;
    }

    public int g(int i0) {
        return this.setOptionInt(202, i0);
    }

    native public String getTagsInfo(long arg);


    native public String grammarCompilerGetOptionString(long arg, int arg0);


    native public int grammarCompilerSetOptionInt(long arg, int arg0, int arg1);


    public int h(int i0) {
        return this.setActiveNet(i0);
    }

    public boolean h() {
        return this.U;
    }

    public int i(int i0) {
        return this.setOptionInt(22, i0);
    }

    public void i() {
        if (this.U) {
            Object[] a0 = new Object[1];
            a0[0] = "do Release";
            com.unisound.common.y.c(a0);
            this.release();
            E.clear();
            this.U = false;
            this.N = false;
            this.S = true;
        }
    }

    native public long initUserDataCompiler(String arg);


    public void j(int i0) {
        this.setOptionInt(500, i0);
        this.setOptionInt(502, 1);
        Object[] a0 = new Object[2];
        a0[0] = "JniAsrFix : setThreadNum num = ";
        a0[1] = Integer.valueOf(i0);
        com.unisound.common.y.c(a0);
    }

    public boolean j() {
        return this.N;
    }

    public int k() {
        return this.Q;
    }

    public int l() {
        return this.getOptionInt(101);
    }

    native public int loadCompiledJsgf(long arg, String arg0);


    native public int loadGrammarStr(String arg);


    public java.util.List m() {
        return (java.util.List)(Object)R;
    }

    public int n() {
        int i0 = this.getOptionInt(103);
        Object[] a0 = new Object[2];
        a0[0] = "getAuthorizedStaus = ";
        a0[1] = Integer.valueOf(i0);
        com.unisound.common.y.c(a0);
        return i0;
    }

    public String o() {
        String s0 = this.getOptionString(104, "");
        Object[] a0 = new Object[2];
        a0[0] = "getExpiryTime = ";
        a0[1] = s0;
        com.unisound.common.y.c(a0);
        return s0;
    }

    public String p() {
        String s0 = this.getOptionString(105, "");
        Object[] a0 = new Object[2];
        a0[0] = "getLimitPac = ";
        a0[1] = s0;
        com.unisound.common.y.c(a0);
        return s0;
    }

    native public int partialCompileUserData(long arg, String arg0, String arg1, String arg2, String arg3, String arg4);


    public int q() {
        return this.getOptionInt(102);
    }

    public int r() {
        return this.getOptionInt(107);
    }

    public int s() {
        return this.getOptionInt(106);
    }

    public boolean t() {
        return cn.yunzhisheng.asrfix.JniAsrFix.getVersion().contains((CharSequence)(Object)"V3.");
    }

    public int u() {
        return this.check_wav_end();
    }

    native public int unloadGrammar(String arg);


    public void v() {
        E.clear();
    }
}

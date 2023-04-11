//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.sdk;

import android.text.TextUtils;
import cn.yunzhisheng.nlu.OfflineNlu;
import com.unisound.common.ai;
import com.unisound.common.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class l {
    public List<String> a = new ArrayList();
    public boolean b = true;
    public boolean c = false;
    public String d;
    public float e;
    public boolean f = false;
    public String g = "";
    public OfflineNlu h;
    private String i;

    public l() {
    }

    public static String a(float var0, String var1) {
        String[] var2 = var1.split("\n");
        int var3 = var2.length - 1;
        String var4 = var1;
        if (var3 > 0) {
            if (var0 > b(var2[var3])) {
                var4 = "";
            } else {
                var4 = var1.replace(var2[var3], "").trim();
            }
        }

        return var4;
    }

    private String a(String var1, String var2) {
        y.c(new Object[]{"RecognizeResult", "rmIgnoreTag : result before ignoreTag :=", var2});
        StringBuffer var3 = new StringBuffer();
        var3.append("<").append(var1).append(">");
        StringBuffer var4 = new StringBuffer();
        var4.append("</").append(var1).append(">");
        Pattern var5 = Pattern.compile(var3.toString());
        var1 = Pattern.compile(var4.toString()).matcher(var5.matcher(var2).replaceAll("")).replaceAll("");
        y.c(new Object[]{"RecognizeResult", "rmIgnoreTag : result end ignoreTag :=", var1});
        return var1;
    }

    public static float b(String var0) {
        float var1;
        try {
            var1 = Float.parseFloat(var0);
        } catch (Exception var2) {
            var2.printStackTrace();
            var1 = -100.0F;
        }

        return var1;
    }

    public static float d(String var0) {
        String[] var3 = var0.split("\n");
        int var1 = var3.length - 1;
        float var2;
        if (var1 > 0) {
            var2 = b(var3[var1]);
        } else {
            var2 = -25.0F;
        }

        return var2;
    }

    public static String e(String var0) {
        String var1 = var0;
        if (var0.indexOf("<s>") >= 0) {
            var1 = var0.substring(var0.indexOf("<s>"));
        }

        var0 = var1.replaceAll(" ", "");
        Pattern var2 = Pattern.compile("<s>|</s>");
        Pattern var3 = Pattern.compile("<[\\w]*>");
        return Pattern.compile("<[\\w]*>").matcher(var3.matcher(var2.matcher(var0).replaceAll("")).replaceAll("").replaceAll("/", "")).replaceAll("").split("\n")[0].trim();
    }

    public String a() {
        Iterator var1 = this.a.iterator();
        String var2;
        if (var1.hasNext()) {
            var2 = (String)var1.next();
        } else {
            var2 = "";
        }

        return var2;
    }

    public boolean a(String var1) {
        boolean var2 = false;
        this.d = "";
        boolean var3;
        if (var1 == null) {
            var3 = var2;
        } else {
            var3 = var2;
            if (var1.length() != 0) {
                Pattern var4 = Pattern.compile("<s>|</s>");
                Pattern var5 = Pattern.compile("<[\\w]*>");
                String[] var7 = Pattern.compile("<[\\w]*>").matcher(var5.matcher(var4.matcher(var1).replaceAll("")).replaceAll("\n").replaceAll("/", "").replaceAll("\n\n", "\n")).replaceAll("").split("\n");
                this.e = -25.0F;
                int var6 = var7.length - 1;
                var3 = var2;
                if (var6 > 0) {
                    this.e = b(var7[var6]);
                    if (var6 == 1) {
                        this.d = var7[0];
                    } else {
                        this.d = var7[0] + var7[var6 - 1];
                    }

                    var3 = true;
                }
            }
        }

        return var3;
    }

    public boolean a(String var1, boolean var2, String var3) {
        int var4 = 0;
        byte var5 = 0;
        byte var6 = 0;
        boolean var7 = false;
        this.a.clear();
        boolean var8;
        if (var1 == null) {
            var8 = var7;
        } else {
            var8 = var7;
            if (var1.length() != 0) {
                if (var1.split("\n").length < 2) {
                    y.a("FixrecognizeResult -> setResultList: RecognitionResult error for lessing than two lines!");
                    var8 = var7;
                } else {
                    String var9 = var1;
                    if (var1.indexOf("<s>") >= 0) {
                        var9 = var1.substring(var1.indexOf("<s>"));
                    }

                    String[] var12;
                    int var16;
                    if (this.f && !var2) {
                        if (!this.g.equals("") && this.h != null) {
                            var12 = var9.split("\n");
                            this.e = -25.0F;
                            var16 = var12.length - 1;
                            var8 = var7;
                            if (var16 > 0) {
                                for(this.e = b(var12[var16]); var4 < var16; ++var4) {
                                    this.a.add(this.h.a("[" + var12[var4] + "]", ""));
                                }

                                var8 = true;
                            }
                        } else {
                            y.e(new Object[]{"RecognizeResult", "setResultList : nluConfigFile didn't exists or OfflineNlu is null"});
                            var8 = var7;
                        }
                    } else if (this.c) {
                        var1 = var9;
                        if (!TextUtils.isEmpty(var3)) {
                            var1 = this.a(var3, var9);
                        }

                        var12 = var1.split("\n");
                        this.e = -25.0F;
                        var16 = var12.length - 1;
                        y.c(new Object[]{"RecognizeResult", "setResultList : arrayOfstring.length =", var12.length});
                        var8 = var7;
                        if (var16 > 0) {
                            this.e = b(var12[var16]);

                            for(var4 = 0; var4 < var16; ++var4) {
                                var9 = var12[var4];

                                try {
                                    float var10 = this.e;
                                    StringBuilder var14 = new StringBuilder();
                                    var3 = ai.b(var9, var10, var14.append(this.i).append("result.xml").toString()).toString().replace(" ", "").replace("_", "");
                                    this.a.add(var3);
                                } catch (Exception var11) {
                                    var11.printStackTrace();
                                }
                            }

                            var8 = true;
                        }
                    } else if (!this.b) {
                        var12 = var9.split("\n");
                        this.e = -25.0F;
                        int var17 = var12.length - 1;
                        var8 = var7;
                        if (var17 > 0) {
                            this.e = b(var12[var17]);

                            for(var4 = var5; var4 < var17; ++var4) {
                                this.a.add(var12[var4]);
                            }

                            var8 = true;
                        }
                    } else {
                        Pattern var13 = Pattern.compile("<s>|</s>");
                        Pattern var15 = Pattern.compile("<[\\w]*>");
                        var12 = Pattern.compile("<[\\w]*>").matcher(var15.matcher(var13.matcher(var9).replaceAll("")).replaceAll("").replaceAll("/", "")).replaceAll("").split("\n");
                        this.e = -25.0F;
                        var16 = var12.length - 1;
                        var8 = var7;
                        if (var16 > 0) {
                            this.e = b(var12[var16]);

                            for(var4 = var6; var4 < var16; ++var4) {
                                this.a.add(var12[var4]);
                            }

                            var8 = true;
                        }
                    }
                }
            }
        }

        return var8;
    }

    public void c(String var1) {
        this.i = var1;
    }

    public String toString() {
        String var1;
        if (this.d != null && this.d.length() > 0) {
            var1 = this.d + "\n" + this.e;
        } else {
            var1 = "";
        }

        return var1;
    }
}

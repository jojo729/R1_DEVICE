package com.unisound.common;

import com.unisound.sdk.al;
import com.unisound.sdk.am;
import com.unisound.sdk.b;
import com.unisound.sdk.cn;
import com.unisound.sdk.w;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class v {
    public static final String a = "partial";
    public static final String b = "full";
    public static final String c = "change";
    private static final String d = "asr_recongize";
    private static final String e = "retTag";
    private static final String f = "nlu";
    private static ArrayList g = new ArrayList();

    public static cn a(String var0, boolean var1, w var2) {
        cn var15;
        label98: {
            JSONException var10000;
            label101: {
                boolean var10001;
                if (!var0.contains("asr_recongize")) {
                    break label98;
                }

                String var3 = var0;

                if (var0.contains("-changeable-")) {
                    var3 = var0.replace("-changeable-", "");
                }

                JSONObject var4;
                boolean var5;
                try {
                    var4 = new JSONObject(var3);
                    var5 = var4.has("retTag");
                } catch (JSONException var12) {
                    var10000 = var12;
                    var10001 = false;
                    break label101;
                }

                if (var5) {
                    label83: {
                        label106: {
                            try {
                                if (!var4.getString("retTag").equals("nlu")) {
                                    break label98;
                                }

                                var4.remove("retTag");
                                if (!var4.has("asr_recongize")) {
                                    break label98;
                                }

                                var4.remove("asr_recongize");
                                if (var4.has("nluProcessTime")) {
                                    var2.y(var4.getString("nluProcessTime"));
                                    break label106;
                                }
                            } catch (JSONException var11) {
                                var10000 = var11;
                                var10001 = false;
                                break label83;
                            }

                            var2.y("0");
                        }

                        var15 = new cn(var4.toString().replace("\\/", "/"));
                        return var15;
                    }
                } else {
                    label105: {
                        if (var1) {
                            break label98;
                        }

                        label69: {
                            try {
                                if (!var4.has("asr_recongize")) {
                                    break label98;
                                }

                                var4.remove("asr_recongize");
                                if (var4.has("nluProcessTime")) {
                                    var2.y(var4.getString("nluProcessTime"));
                                    break label69;
                                }
                            } catch (JSONException var9) {
                                var10000 = var9;
                                var10001 = false;
                                break label105;
                            }

                            var2.y("0");
                        }

                        var15 = new cn(var4.toString().replace("\\/", "/"));
                        return var15;
                    }
                }
            }

            JSONException var16 = var10000;
            var16.printStackTrace();
        }

        var15 = new cn((String)null);
        return var15;
    }

    public static String a(int var0, String var1, String var2, String var3, Object var4, Object var5, Object var6, Object var7, Object var8, Object var9, Object var10) {
        if (var2 != null) {
            JSONObject var11;
            label115: {
                JSONException var10000;
                label119: {
                    boolean var10001;
                    label113: {
                        var11 = new JSONObject();
                        String var12;
                        if (var0 == 1) {
                            var12 = "net";
                        } else if (var0 == 0) {
                            var12 = "mix";
                        } else if (var0 == 2) {
                            var12 = "local";
                        } else {
                            if (var0 != 1000) {
                                break label113;
                            }

                            var12 = "wakeup";
                        }

                        try {
                            var11.put("engine_mode", var12);
                        } catch (JSONException var23) {
                            var10000 = var23;
                            var10001 = false;
                            break label119;
                        }
                    }

                    if (var1 != null) {
                        try {
                            var11.put("result_type", var1);
                        } catch (JSONException var22) {
                            var10000 = var22;
                            var10001 = false;
                            break label119;
                        }
                    }

                    if (var4 != null) {
                        try {
                            var11.put("last_result", var4);
                        } catch (JSONException var21) {
                            var10000 = var21;
                            var10001 = false;
                            break label119;
                        }
                    }

                    if (var2 != null) {
                        try {
                            var11.put("recognition_result", var2);
                        } catch (JSONException var20) {
                            var10000 = var20;
                            var10001 = false;
                            break label119;
                        }
                    }

                    if (var3 != null) {
                        try {
                            var11.put("sessionID", var3);
                        } catch (JSONException var19) {
                            var10000 = var19;
                            var10001 = false;
                            break label119;
                        }
                    }

                    if (var5 != null) {
                        try {
                            var11.put("score", var5);
                        } catch (JSONException var18) {
                            var10000 = var18;
                            var10001 = false;
                            break label119;
                        }
                    }

                    if (var6 != null) {
                        try {
                            var11.put("utteranceTime", var6);
                        } catch (JSONException var17) {
                            var10000 = var17;
                            var10001 = false;
                            break label119;
                        }
                    }

                    if (var7 != null) {
                        try {
                            var11.put("outRecordingTime", var7);
                        } catch (JSONException var16) {
                            var10000 = var16;
                            var10001 = false;
                            break label119;
                        }
                    }

                    if (var8 != null) {
                        try {
                            var11.put("delayTime", var8);
                        } catch (JSONException var15) {
                            var10000 = var15;
                            var10001 = false;
                            break label119;
                        }
                    }

                    if (var9 != null) {
                        try {
                            var11.put("utteranceStartTime", var9);
                        } catch (JSONException var14) {
                            var10000 = var14;
                            var10001 = false;
                            break label119;
                        }
                    }

                    if (var10 == null) {
                        break label115;
                    }

                    try {
                        var11.put("utteranceEndTime", var10);
                        break label115;
                    } catch (JSONException var13) {
                        var10000 = var13;
                        var10001 = false;
                    }
                }

                JSONException var24 = var10000;
                var24.printStackTrace();
            }

            var1 = var11.toString().replace("\\/", "/");
        } else {
            var1 = "";
        }

        return var1;
    }

    public static String a(b var0) {
        am var1 = var0.a().e();
        String var2;
        if (var1 == am.b) {
            var2 = b(var0);
        } else if (var1 == am.a) {
            var2 = c(var0);
        } else {
            var2 = "";
        }

        return var2;
    }

    private String a(String var1) {
        String var2 = "";
        String var3 = var2;

        JSONException var10000;
        label75: {
            boolean var10001;
            if (!var1.contains("asr_recongize")) {
                return var3;
            }

            var3 = var2;

            if (!var1.contains("retTag")) {
                return var3;
            }

            var3 = var1;

            if (var1.contains("-changeable-")) {
                var3 = var1.replace("-changeable-", "");
            }

            JSONObject var11;
            try {
                var11 = new JSONObject(var3);
            } catch (JSONException var7) {
                var10000 = var7;
                var10001 = false;
                break label75;
            }

            var3 = var2;

            if (!var11.has("retTag")) {
                return var3;
            }

            var3 = var2;

            try {
                if (!var11.getString("retTag").equals("nlu")) {
                    return var3;
                }
            } catch (JSONException var5) {
                var10000 = var5;
                var10001 = false;
                break label75;
            }

            var3 = var2;

            try {
                if (var11.has("asr_recongize")) {
                    var3 = var11.getString("asr_recongize");
                }

                return var3;
            } catch (JSONException var4) {
                var10000 = var4;
                var10001 = false;
            }
        }

        JSONException var12 = var10000;
        var12.printStackTrace();
        var3 = var2;
        return var3;
    }

    private static String a(String var0, String var1) {
        String var2 = var0;

        try {
            if (!var0.contains(var1)) {
                return var2;
            }

            JSONObject var4 = new JSONObject(var0);
            if (var4.has(var1)) {
                var2 = var4.getString(var1);
                return var2;
            }
        } catch (JSONException var3) {
            var3.printStackTrace();
        }

        var2 = "";
        return var2;
    }

    public static String a(ArrayList var0, ArrayList var1, ArrayList var2) {
        JSONObject var3 = new JSONObject();
        byte var4 = 0;
        int var6;
        JSONObject var7;
        if (var0 != null) {
            new JSONObject();
            JSONArray var5 = new JSONArray();
            var6 = 0;

            while(true) {
                try {
                    if (var6 >= var0.size()) {
                        var3.put("local_asr", var5);
                        break;
                    }

                    var7 = new JSONObject((String)var0.get(var6));
                    var5.put(var7);
                } catch (JSONException var10) {
                    var10.printStackTrace();
                    break;
                }

                ++var6;
            }
        }

        if (var1 != null) {
            new JSONObject();
            JSONArray var11 = new JSONArray();
            var6 = 0;

            while(true) {
                try {
                    if (var6 >= var1.size()) {
                        var3.put("net_asr", var11);
                        break;
                    }

                    var7 = new JSONObject((String)var1.get(var6));
                    var11.put(var7);
                } catch (JSONException var9) {
                    var9.printStackTrace();
                    break;
                }

                ++var6;
            }
        }

        if (var2 != null && var2.size() > 0) {
            new JSONObject();
            JSONArray var13 = new JSONArray();
            var6 = var4;

            while(true) {
                try {
                    if (var6 >= var2.size()) {
                        var3.put("net_nlu", var13);
                        break;
                    }

                    if (var2.get(var6) != null) {
                        JSONObject var12 = new JSONObject((String)var2.get(var6));
                        var13.put(var12);
                    }
                } catch (JSONException var8) {
                    var8.printStackTrace();
                    break;
                }

                ++var6;
            }
        }

        return var3.toString().replace("\\/", "/");
    }

    public static Map a(String var0, Map var1) {
        HashMap var2 = new HashMap();

        JSONException var10000;
        label25: {
            boolean var10001;
            JSONObject var3;
            Iterator var4;
            try {
                var3 = new JSONObject(var0);
                var4 = var1.keySet().iterator();
            } catch (JSONException var6) {
                var10000 = var6;
                var10001 = false;
                break label25;
            }

            while(true) {
                if (!var4.hasNext()) {
                    return var2;
                }

                var0 = (String)var4.next();
                var2.put((Integer)var1.get(var0), var3.opt(var0));
            }
        }

        JSONException var7 = var10000;
        var7.printStackTrace();
        return var2;
    }

    public static void a() {
        y.c(new Object[]{"clearTotalASRResult"});
        ArrayList var0 = g;
        if (var0 != null) {
            var0.clear();
        }

    }

    public static String b() {
        String var2;
        if (g != null) {
            StringBuffer var0 = new StringBuffer();

            for(int var1 = 0; var1 < g.size(); ++var1) {
                var0.append((String)g.get(var1));
            }

            var2 = new String(var0);
        } else {
            var2 = "";
        }

        return var2;
    }

    private static String b(b var0) {
        JSONException var10000;
        JSONObject var18;
        label55: {
            boolean var3;
            String var4;
            boolean var5;
            float var6;
            int var7;
            int var8;
            int var9;
            long var10;
            long var12;
            String var17;
            boolean var10001;
            label51: {
                al var1 = var0.a();
                int var2 = var0.b();
                var3 = var0.c();
                var4 = var1.a();
                var5 = var1.d();
                var6 = var1.h();
                var7 = var1.i();
                var8 = var1.f();
                var9 = var1.g();
                var10 = var1.k();
                var12 = var1.j();
                var18 = new JSONObject();
                if (var2 == 1) {
                    var17 = "net";
                } else if (var2 == 0) {
                    var17 = "mix";
                } else if (var2 == 2) {
                    var17 = "local";
                } else {
                    if (var2 != 1000) {
                        break label51;
                    }

                    var17 = "wakeup";
                }

                try {
                    var18.put("engine_mode", var17);
                } catch (JSONException var16) {
                    var10000 = var16;
                    var10001 = false;
                    break label55;
                }
            }

            if (var3) {
                var17 = "full";
            } else if (var5) {
                var17 = "change";
            } else {
                var17 = "partial";
            }

            try {
                var18.put("result_type", var17);
            } catch (JSONException var15) {
                var10000 = var15;
                var10001 = false;
                break label55;
            }

            try {
                var18.put("recognition_result", var4);
                var18.put("score", (double)var6);
                var18.put("utteranceTime", var7);
                var18.put("outRecordingTime", var12);
                var18.put("delayTime", var10);
                var18.put("utteranceStartTime", var8);
                var18.put("utteranceEndTime", var9);
                return var18.toString().replace("\\/", "/");
            } catch (JSONException var14) {
                var10000 = var14;
                var10001 = false;
            }
        }

        JSONException var19 = var10000;
        var19.printStackTrace();
        return var18.toString().replace("\\/", "/");
    }

    private static void b(String var0) {
        StringBuilder var1 = new StringBuilder();
        var1.append("addToTotalASRResult=");
        var1.append(var0);
        y.c(new Object[]{var1.toString()});
        ArrayList var2 = g;
        if (var2 != null) {
            var2.add(var0);
        }

    }

    private static String c(b var0) {
        al var1 = var0.a();
        int var2 = var0.b();
        boolean var3 = var0.c();
        boolean var4 = var0.d();
        String var5 = var1.b();
        String var6 = var1.a();
        if (var4) {
            y.b(new Object[]{"originFormat onResult -> result = ", var6});
        } else {
            var6 = a(var6, "asr_recongize");
            if (!var1.d()) {
                b(var6);
            }

            y.b(new Object[]{"format onResult -> result = ", var6});
        }

        JSONObject var12;
        JSONException var10000;
        label65: {
            boolean var7;
            String var11;
            boolean var10001;
            label59: {
                var7 = var1.d();
                var4 = var1.c();
                var12 = new JSONObject();
                if (var2 == 1) {
                    var11 = "net";
                } else if (var2 == 0) {
                    var11 = "mix";
                } else if (var2 == 2) {
                    var11 = "local";
                } else {
                    if (var2 != 1000) {
                        break label59;
                    }

                    var11 = "wakeup";
                }

                try {
                    var12.put("engine_mode", var11);
                } catch (JSONException var10) {
                    var10000 = var10;
                    var10001 = false;
                    break label65;
                }
            }

            if (var3) {
                var11 = "full";
            } else if (var7) {
                var11 = "change";
            } else {
                var11 = "partial";
            }

            try {
                var12.put("result_type", var11);
            } catch (JSONException var9) {
                var10000 = var9;
                var10001 = false;
                break label65;
            }

            try {
                var12.put("last_result", var4);
                var12.put("recognition_result", var6);
                var12.put("sessionID", var5);
                return var12.toString().replace("\\/", "/");
            } catch (JSONException var8) {
                var10000 = var8;
                var10001 = false;
            }
        }

        JSONException var13 = var10000;
        var13.printStackTrace();
        return var12.toString().replace("\\/", "/");
    }
}

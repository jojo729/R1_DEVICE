package com.unisound.common;

import java.text.*;
import cn.yunzhisheng.asr.*;
import android.content.*;
import com.phicomm.speaker.device.ExampleApp;
import org.json.*;
import com.unisound.c.*;
import android.util.*;
import java.util.*;
import java.io.*;
import android.text.*;

public class y
{
    public static final String A = "initVPR";
    public static final String B = "initNLU";
    public static final String C = "collectedInfo";
    public static final String D = "start";
    public static final String E = "recognition";
    public static final String F = "stop";
    public static final String G = "cancel";
    public static final String H = "getResult";
    public static final String I = "error";
    public static final String J = "success";
    public static final String K = "partial";
    public static final String L = "last";
    private static ar M;
    private static final String N = "USCLOG";
    private static String O;
    private static String P;
    private static StringBuffer Q;
    private static boolean R = false;
    private static int S = 0;
    private static Object T;
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 3;
    public static boolean k = false;
    public static boolean l = false;
    public static boolean m = false;
    public static boolean n = false;
    public static boolean o = false;
    public static boolean p = false;
    public static boolean q = false;
    public static boolean r = false;
    public static String s;
    public static boolean t = false;
    public static boolean u = false;
    public static final String v = "USC";
    public static final String w = "USC_THREAD";
    public static int x = 0;
    public static final String y = "init";
    public static final String z = "create";

    static {
        com.unisound.common.y.M = null;
        com.unisound.common.y.k = false;
        com.unisound.common.y.l = false;
        com.unisound.common.y.m = false;
        com.unisound.common.y.n = false;
        com.unisound.common.y.o = false;
        com.unisound.common.y.p = false;
        com.unisound.common.y.q = false;
        com.unisound.common.y.r = false;
        com.unisound.common.y.s = "";
        com.unisound.common.y.t = true;
        com.unisound.common.y.u = true;
        com.unisound.common.y.x = 0;
        com.unisound.common.y.O = "";
        com.unisound.common.y.P = "";
        com.unisound.common.y.Q = new StringBuffer();
        com.unisound.common.y.R = true;
        com.unisound.common.y.S = 0;
        com.unisound.common.y.T = new Object();
    }

    public y() {
        super();
    }

    static /* synthetic */ int a(final int s) {
        return com.unisound.common.y.S = s;
    }

    private static String a(final long n) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(n));
    }

    public static String a(final JniUscClient jniUscClient) {
        return com.unisound.common.y.O = jniUscClient.c(54);
    }

    static /* synthetic */ String a(final File file) {
        return b(file);
    }

    private static String a(final String s, final String s2, final String s3, final int n, final String s4, final Context context, final String s5, final String s6, final long n2) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("appKey", (Object)s);
            jsonObject.put("imei", (Object)s2);
            jsonObject.put("sessionID", (Object)s3);
            jsonObject.put("timeStamp", (Object)b(n2));
            jsonObject.put("packageName", ExampleApp.PROCESS_NAME);
            jsonObject.put("status", n);
            jsonObject.put("errString", (Object)s4);
            jsonObject.put("javaSDKLog", (Object)s5);
            jsonObject.put("cSDKLog", (Object)s6);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static void a() {
        final StringBuffer q = com.unisound.common.y.Q;
        q.delete(0, q.length());
        com.unisound.common.y.O = "";
        com.unisound.common.y.P = "";
    }

    public static void a(final Context context) {
        if (com.unisound.common.y.u) {
            final StringBuilder sb = new StringBuilder();
            sb.append(context.getCacheDir().getAbsolutePath());
            sb.append("/errorlog/");
            new Thread((Runnable)new z(sb.toString())).start();
        }
    }

    public static void a(final Context context, final boolean b, final String s, String string, final int n, final String s2) {
        if (com.unisound.common.y.t) {
            final StringBuilder sb = new StringBuilder();
            sb.append(context.getCacheDir().getAbsolutePath());
            sb.append("/javalog");
            final String string2 = sb.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(context.getCacheDir().getAbsolutePath());
            sb2.append("/clog");
            final String string3 = sb2.toString();
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(context.getCacheDir().getAbsolutePath());
            sb3.append("/errorlog");
            final String string4 = sb3.toString();
            final StringBuilder sb4 = new StringBuilder();
            sb4.append(context.getCacheDir().getAbsolutePath());
            sb4.append("/errorlog/");
            sb4.append(System.currentTimeMillis());
            final String string5 = sb4.toString();
            final StringBuilder sb5 = new StringBuilder();
            sb5.append(context.getCacheDir().getAbsolutePath());
            sb5.append("/javalogbk");
            final String string6 = sb5.toString();
            final StringBuilder sb6 = new StringBuilder();
            sb6.append(context.getCacheDir().getAbsolutePath());
            sb6.append("/clogbk");
            final String string7 = sb6.toString();
            final File file = new File(string2);
            final File file2 = new File(string3);
            if (file.exists() && file.length() > 1000000L) {
                final File file3 = new File(string6);
                if (file3.exists()) {
                    file3.delete();
                }
                file.renameTo(file3);
            }
            if (file2.exists() && file2.length() > 1000000L) {
                final File file4 = new File(string7);
                if (file4.exists()) {
                    file4.delete();
                }
                file.renameTo(file4);
            }
            a(string2, new String(com.unisound.common.y.Q));
            c(string3, com.unisound.common.y.O);
            if (b) {
                final File file5 = new File(string5);
                if (!file5.getParentFile().exists()) {
                    file5.getParentFile().mkdirs();
                }
                final long currentTimeMillis = System.currentTimeMillis();
                if (string.equals("")) {
                    final StringBuilder sb7 = new StringBuilder();
                    sb7.append(com.unisound.c.a.q);
                    sb7.append(currentTimeMillis);
                    string = sb7.toString();
                }
                c(string4);
                c(string5, a(s, com.unisound.c.a.a(s), string, n, s2, context, com.unisound.common.y.P, com.unisound.common.y.O, currentTimeMillis));
            }
        }
    }

    public static void a(final ar m) {
        com.unisound.common.y.M = m;
    }

    public static void a(final String s) {
        Log.e("USC", s);
        b(s);
    }

    public static void a(final String s, final String s2) {
        final FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(s, true);
            new BufferedWriter(fileWriter);
            fileWriter.write(s2);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public static void a(final String s, final String s2, final Throwable t) {
        if (com.unisound.common.y.k) {
            Log.e(s, s2, t);
        }
    }

    public static void a(String s, String s2, final Map<String, String> map, final String s3, final String s4, final String s5) {
        if (com.unisound.common.y.k) {
            final StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append("Time=");
            sb.append(a(System.currentTimeMillis()));
            String s7;
            final String s6 = s7 = sb.toString();
            if (s != null) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(s6);
                sb2.append("&Action=");
                sb2.append(s);
                s7 = sb2.toString();
            }
            s = s7;
            if (s2 != null) {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(s7);
                sb3.append("&Status=");
                sb3.append(s2);
                s = sb3.toString();
            }
            s2 = s;
            if (map != null) {
                final Iterator<String> iterator = map.keySet().iterator();
                s2 = "{";
                while (iterator.hasNext()) {
                    final String s8 = iterator.next();
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append(s2);
                    sb4.append(s8);
                    sb4.append(":");
                    sb4.append(map.get(s8));
                    sb4.append("#");
                    s2 = sb4.toString();
                }
                final StringBuilder sb5 = new StringBuilder();
                sb5.append(s2);
                sb5.append("}");
                final String string = sb5.toString();
                final StringBuilder sb6 = new StringBuilder();
                sb6.append(s);
                sb6.append("&Params=");
                sb6.append(string);
                s2 = sb6.toString();
            }
            s = s2;
            if (s3 != null) {
                final StringBuilder sb7 = new StringBuilder();
                sb7.append(s2);
                sb7.append("&Result=");
                sb7.append(s3);
                s = sb7.toString();
            }
            s2 = s;
            if (s4 != null) {
                final StringBuilder sb8 = new StringBuilder();
                sb8.append(s);
                sb8.append("&Errno=");
                sb8.append(s4);
                s2 = sb8.toString();
            }
            s = s2;
            if (s5 != null) {
                final StringBuilder sb9 = new StringBuilder();
                sb9.append(s2);
                sb9.append("&Message=");
                sb9.append(s5);
                s = sb9.toString();
            }
            final StringBuilder sb10 = new StringBuilder();
            sb10.append(s);
            sb10.append("%");
            s = (com.unisound.common.y.P = sb10.toString());
            com.unisound.common.y.Q.append(s);
        }
    }

    public static void a(final String s, final Throwable t) {
        a("USC", s, t);
    }

    public static void a(final String s, final String... array) {
        if (com.unisound.common.y.k) {
            final String h = h((Object[])array);
            Log.e(s, h);
            b(h);
        }
    }

    public static void a(final Object... array) {
        if (com.unisound.common.y.k) {
            final String h = h(array);
            Log.v("USC", h);
            b(h);
        }
    }

    static /* synthetic */ boolean a(final boolean r) {
        return com.unisound.common.y.R = r;
    }

    static /* synthetic */ Object b() {
        return com.unisound.common.y.T;
    }

    private static String b(final long n) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(n));
    }

    private static String b(final File file) {
        final StringBuffer sb = new StringBuffer();
        String s;
        if (!file.exists()) {
            s = null;
        }
        else {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                }
                bufferedReader.close();
            }
            catch (IOException ex) {
                ex.getStackTrace();
            }
            s = new String(sb);
        }
        return s;
    }

    private static void b(final String s) {
        final ar m = com.unisound.common.y.M;
        if (m != null) {
            m.a(5, 2, (Object)s);
        }
    }

    public static void b(final String s, final String s2) {
        final FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(s, true);
            new BufferedWriter(fileWriter);
            fileWriter.write(s2);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public static void b(final Object... array) {
        if (com.unisound.common.y.k) {
            final String h = h(array);
            Log.i("USC", h);
            b(h);
        }
    }

    private static void c(final String s) {
        final File[] listFiles = new File(s).listFiles();
        if (listFiles.length >= 10) {
            if (listFiles.length >= 10) {
                for (int i = 0; i < listFiles.length; ++i) {
                    int n;
                    for (int j = 0; j < listFiles.length - 1; j = n) {
                        final long lastModified = listFiles[j].lastModified();
                        n = j + 1;
                        if (lastModified > listFiles[n].lastModified()) {
                            final File file = listFiles[j];
                            listFiles[j] = listFiles[n];
                            listFiles[n] = file;
                        }
                    }
                }
            }
            if (listFiles[0].exists()) {
                listFiles[0].delete();
            }
            c(s);
        }
    }

    public static void c(final String s, final String s2) {
        final FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(s, true);
            new BufferedWriter(fileWriter);
            fileWriter.write(s2);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public static void c(final Object... array) {
        if (com.unisound.common.y.k) {
            final String h = h(array);
            Log.d("USC", h);
            b(h);
        }
    }

    static /* synthetic */ boolean c() {
        return com.unisound.common.y.R;
    }

    static /* synthetic */ int d() {
        final int s = com.unisound.common.y.S;
        com.unisound.common.y.S = s + 1;
        return s;
    }

    public static void d(final Object... array) {
        if (com.unisound.common.y.l) {
            Log.d("USC_THREAD", h(array));
        }
    }

    static /* synthetic */ int e() {
        return com.unisound.common.y.S;
    }

    public static void e(final Object... array) {
        if (com.unisound.common.y.k) {
            final String h = h(array);
            Log.w("USC", h);
            b(h);
        }
    }

    public static void f(final Object... array) {
        if (com.unisound.common.y.m) {
            final String h = h(array);
            final StringBuilder sb = new StringBuilder();
            sb.append(h);
            sb.append(": ");
            sb.append(a(System.currentTimeMillis()));
            Log.d("USC_TIME_TRACE", sb.toString());
            b(h);
        }
    }

    public static void g(final Object... array) {
        if (com.unisound.common.y.n) {
            final String h = h(array);
            Log.e("USC", h);
            b(h);
        }
    }

    private static String h(final Object... array) {
        final String s = "getLog error";
        String string;
        try {
            final StringBuilder sb = new StringBuilder();
            for (final Object o : array) {
                if (!TextUtils.isEmpty((CharSequence)String.valueOf(o))) {
                    sb.append(o);
                }
            }
            string = sb.toString();
        }
        catch (Exception ex) {
            Log.e("USC", "getLog error");
            string = s;
        }
        return string;
    }
}

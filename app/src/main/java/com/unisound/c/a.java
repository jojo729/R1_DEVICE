//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.util.UUID;

import com.phicomm.speaker.device.BuildConfig;
import com.phicomm.speaker.device.ExampleApp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static String A = "4bd9354d1cf247c93db388257567d0e2";
    private static Context B;
    private static String C = "";
    private static boolean D = false;
    private static boolean E = true;
    private static final String F = "DeviceInfoUtil";
    private static String G = "00";
    public static final String a = "PN";
    public static final String b = "OS";
    public static final String c = "CR";
    public static final String d = "NT";
    public static final String e = "MD";
    public static final String f = "SV";
    public static final String g = "SID";
    public static final String h = "RPT";
    public static final String i = "EC";
    public static final String j = "NPT";
    public static final String k = "IP";
    public static final int l = 0;
    public static final int m = 1;
    public static final int n = 2;
    public static final int o = 3;
    public static final int p = 4;
    public static String q = "";
    public static String r = "";
    public static String s = "";
    public static String t = "";
    public static TelephonyManager u;
    public static NetworkInfo v;
    public static ConnectivityManager w;
    private static final String x = "000000000000000";
    private static boolean y = false;
    private static String z = "";

    public a() {
    }

    public static String a() {
        return Build.MODEL;
    }

    public static String a(Context var0, String var1) {
        return a(var0, var1, C);
    }

    private static String a(Context var0, String var1, String var2) {
        String var3 = d(var1);
        f("DeviceInfoUtil getUDID deviceSn = " + var2);
        String var4;
        if (var3 != null && !var3.equals("")) {
            f("DeviceInfoUtil getUDID from sdcard= " + var3);
            if (!TextUtils.isEmpty(var2) && !b(var1, var3, var2)) {
                var4 = b(var0, var1, var2);
            } else {
                c(var0, var1, var3);
                var4 = var3;
            }
        } else {
            var3 = b(var0, var1);
            if (var3 != null && !var3.equals("")) {
                f("DeviceInfoUtil getUDID from sharedPreferences= " + var3);
                if (!TextUtils.isEmpty(var2) && !b(var1, var3, var2)) {
                    var4 = b(var0, var1, var2);
                } else {
                    a(A, var1, var3);
                    var4 = var3;
                }
            } else {
                var4 = b(var0, var1, var2);
                f("DeviceInfoUtil first getUDID= " + var4);
            }
        }

        return var4;
    }

    public static String a(String var0) {
        if (TextUtils.isEmpty(z) || D) {
            z = a(B, var0);
            if (D) {
                D = false;
            }
        }

        return z;
    }

    public static String a(String var0, Context var1) {
        String var3 = a(var0, a(var0), var1);
        var0 = "";

        label13: {
            var3 = b(var3, "v3.1");

            var0 = var3;
        }

        h("DeviceUtil tokenJson is " + var0);
        return var0;
    }

    private static String a(String var0, String var1) {
        String var2 = "";
        h("build udid by deviceSn, appkey = " + var0 + ", OS = " + G + ", deviceSn = " + var1);
        int var3 = var0.hashCode();
        h("build udid by deviceSn, appkeyHashCode = " + var3);
        StringBuilder var4 = new StringBuilder();
        var4.append(var3).append(G).append(var1);
        var1 = var4.toString();
        h("build udid by deviceSn, deviceContent = " + var1);
        var0 = var2;
        if (!TextUtils.isEmpty(var1)) {
            var0 = Base64.encodeToString(var1.getBytes(), 8);
        }

        var0 = var0.replaceAll("=", "").replaceAll("\\s", "");
        h("build udid by deviceSn, udid = " + var0);
        return var0;
    }

    // get token
    public static String a(String var0, String var1, Context var2) {

        String var4 = var2.getSharedPreferences(A, 0).getString("deviceInfo", "");
        h("DeviceUtil getTokenInfo deviceContent = " + var4);
        String token =null;
        if (TextUtils.isEmpty(var4)) {
            token = "";
        }else {
            try {
                JSONObject var9 = new JSONObject(var4);
                JSONArray var10 = var9.getJSONArray("deviceInfo");
                for(int i=0;i<var10.length();i++){
                    JSONObject jsonObject = var10.getJSONObject(i);
                    if (var0.equals(jsonObject.get("appkey")) && var1.equals(jsonObject.get("udid"))) {
                        token = jsonObject.getString("token");
                        break;
                    }
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }

        h("getTokenInfo token = " + token);
        return token;
    }

    private static String a(byte[] var0) {
        StringBuilder var1 = new StringBuilder();

        for(int var2 = 0; var2 < var0.length; ++var2) {
            String var3 = Integer.toHexString(var0[var2] & 255);
            if (var3.length() == 1) {
                var1.append("0");
            }

            var1.append(var3.toUpperCase());
        }

        return var1.toString();
    }

    public static void a(Context var0) {
        B = var0;
        if (!y) {
            w = (ConnectivityManager)var0.getSystemService(Context.CONNECTIVITY_SERVICE);
            u = (TelephonyManager)var0.getSystemService(Context.TELEPHONY_SERVICE);
            if (w != null) {
                v = w.getNetworkInfo(0);
            }

            s = ExampleApp.PROCESS_NAME;
            r = c(var0);
            q = b(var0);
            t = Build.MODEL;
            y = true;
        }

    }

    private static void a(String s, String s2, String string) {
        final String s3 = null;
        final String s4 = null;
        try {
            final String j = j();
            final File file = new File(j);
            if (!file.exists()) {
                file.mkdirs();
            }

            final File file2 = new File(j+File.separator+s);
            String i;
            if (file2.exists()) {
                i = i();
                file2.delete();
            }
            else {
                i = "";
            }
            file2.createNewFile();
            JSONObject jsonObject3;
            if (i.contains("deviceInfo")) {
                final JSONObject jsonObject = new JSONObject(i);
                final JSONArray jsonArray = jsonObject.getJSONArray("deviceInfo");
                final int n = 0;
                int n2 = 0;
                int n3;
                while (true) {
                    n3 = n;
                    if (n2 >= jsonArray.length()) {
                        break;
                    }
                    final JSONObject jsonObject2 = jsonArray.getJSONObject(n2);
                    if (s2.equals(jsonObject2.get("appkey"))) {
                        jsonObject2.put("udid",string);
                        n3 = 1;
                        break;
                    }
                    ++n2;
                }
                jsonObject3 = jsonObject;
                if (n3 == 0) {
                    final JSONObject jsonObject4 = new JSONObject();
                    jsonObject4.put("appkey",s2);
                    jsonObject4.put("udid", string);
                    jsonArray.put(jsonObject4);
                    jsonObject.put("deviceInfo", jsonArray);
                    jsonObject3 = jsonObject;
                }
            }
            else {
                jsonObject3 = new JSONObject();
                final JSONArray jsonArray2 = new JSONArray();
                final JSONObject jsonObject5 = new JSONObject();
                jsonObject5.put("appkey", s2);
                jsonObject5.put("udid",string);
                jsonArray2.put((Object)jsonObject5);
                jsonObject3.put("deviceInfo",jsonArray2);
            }
            string = jsonObject3.toString();
            f("DeviceInfoUtil setUDIDToSdcard udidContent = "+string);
            RandomAccessFile randomAccessFile =  new RandomAccessFile(j+File.separator+s, "rw");
            try {
                randomAccessFile.write(string.getBytes());
                randomAccessFile.close();
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }

        }
        catch (Exception ex4) {
           ex4.printStackTrace();
        }
    }

    public static void a(boolean var0) {
        D = var0;
    }

    public static int b() {
        int var0 = 1;
        NetworkInfo var1 = w.getNetworkInfo(1);
        if (var1 == null || !var1.isAvailable()) {
            var0 = c();
        }

        return var0;
    }

    public static int b(String var0) {
        byte var1 = -1;
        byte var2 = var1;
        if (var0 != null) {
            if (var0.length() > 24) {
                C = "";
                var2 = var1;
            } else {
                C = var0;
                f("DeviceInfoUtil setDeviceSn = " + C);
                var2 = 0;
            }
        }

        return var2;
    }

    public static String b(Context var0) {
        String var1 = ((TelephonyManager)var0.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        if (var1 == null || "".equals(var1) || var1.equals("000000000000000")) {
            String var2 = Secure.getString(var0.getContentResolver(), "android_id");
            if (var2 != null && !"".equals(var2)) {
                var1 = var2;
                if (!var2.equals("000000000000000")) {
                    return var1;
                }
            }

            WifiInfo var3 = ((WifiManager)var0.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
            if (var3 != null) {
                var1 = var3.getMacAddress();
            } else {
                var1 = "000000000000000";
            }
        }

        return var1;
    }

    private static String b(Context var0, String var1) {
        int var2 = 0;
        String var3 = var0.getSharedPreferences(A, 0).getString("deviceInfo", "");
        h("DeviceInfoUtil getUDIDFromSb deviceContent = " + var3);
        String var4 = "";
        String var7 = var4;

        label34: {
            boolean var10001;
            JSONObject var8;
            JSONArray var9;
            try {
                if (TextUtils.isEmpty(var3)) {
                    return var7;
                }

                var8 = new JSONObject(var3);
                var9 = var8.getJSONArray("deviceInfo");
            } catch (Exception var6) {
                var10001 = false;
                break label34;
            }

            while(true) {
                var7 = var4;

                try {
                    if (var2 >= var9.length()) {
                        return var7;
                    }

                    var8 = var9.getJSONObject(var2);
                    if (var1.equals(var8.get("appkey"))) {
                        var7 = var8.getString("udid");
                        return var7;
                    }
                } catch (Exception var5) {
                    var10001 = false;
                    break;
                }

                ++var2;
            }
        }

        var7 = var4;
        return var7;
    }

    private static String b(Context var0, String var1, String var2) {
        if (!TextUtils.isEmpty(var2)) {
            var2 = a(var1, var2);
        } else {
            var2 = l();
        }

        c(var0, var1, var2);
        a(A, var1, var2);
        f("DeviceInfoUtil createNewUDID UDID= " + var2);
        return var2;
    }

    private static String b(String var0, String var1) {
        JSONObject var2 = new JSONObject();
        try {
            var2.put("deviceToken", var0);
            var2.put("activateVersion", var1);
        }catch (Exception e){
            e.printStackTrace();
        }

        return var2.toString();
    }

    public static void b(boolean var0) {
        E = var0;
    }

    private static boolean b(String var0, String var1, String var2) {
        boolean var3 = var1.equals(a(var0, var2));
        f("DeviceInfoUtil isUdidMatchDeviceSn = " + var3);
        return var3;
    }

    public static int c() {
        byte var0;
        if (v != null && v.isAvailable()) {
            switch(u.getNetworkType()) {
                case 1:
                case 2:
                case 4:
                    var0 = 3;
                    break;
                case 3:
                case 5:
                case 6:
                case 8:
                    var0 = 2;
                    break;
                case 7:
                default:
                    var0 = 4;
            }
        } else {
            var0 = 0;
        }

        return var0;
    }

    public static String c(Context var0) {
        String var1 = ((TelephonyManager)var0.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperator();
        if (var1 == null || "".equals(var1)) {
            var1 = "0";
        }

        return var1;
    }

    private static void c(Context var0, String var1, String var2) {
        SharedPreferences var4 = var0.getSharedPreferences(A, 0);
        String var5 = var4.getString("deviceInfo", "");
        String var16 = null;
        try {
            if (TextUtils.isEmpty(var5)) {
                JSONObject var21 = new JSONObject();
                JSONObject var15 = new JSONObject();
                var15.put("appkey", var1);
                var15.put("udid", var2);
                var15.put("token", "");
                JSONArray var17 = new JSONArray();
                var17.put(var15);
                var21.put("deviceInfo", var17);
                var16 = var21.toString();
            }else{
                JSONObject var15 = new JSONObject(var5);
                JSONArray var20 = var15.getJSONArray("deviceInfo");
                for(int i=0;i<var20.length();i++){
                    JSONObject var8 = var20.getJSONObject(i);
                    if (var1.equals(var8.getString("appkey")) && var2.equals(var8.getString("udid"))) {
                        continue;
                    }else{
                        JSONObject var9 = new JSONObject();
                        var9.put("appkey", var1);
                        var9.put("udid", var2);
                        var9.put("token", "");
                        var20.put(var9);
                    }

                }
                var16 = var15.toString();
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        try {
            h("DeviceInfoUtil setUDIDToSb deviceContent = "+var16);
            Editor var19 = var4.edit();
            var19.putString("deviceInfo", var16);
            var19.apply();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    public static void c(String var0) {
        G = var0;
        f("DeviceInfoUtil setOS = " + G);
    }

    public static String d() {
        return Build.PRODUCT;
    }

    private static String d(String var0) {
        String var1 = "";

        String var2;
        label34: {
            label38: {
                boolean var10001;
                JSONArray var8;
                try {
                    var2 = i();
                    JSONObject var3 = new JSONObject(var2);
                    var8 = var3.getJSONArray("deviceInfo");
                } catch (Exception var6) {
                    var10001 = false;
                    break label38;
                }

                int var4 = 0;

                while(true) {
                    var2 = var1;

                    try {
                        if (var4 >= var8.length()) {
                            break label34;
                        }

                        JSONObject var7 = var8.getJSONObject(var4);
                        if (var0.equals(var7.get("appkey"))) {
                            var2 = var7.getString("udid");
                            break label34;
                        }
                    } catch (Exception var5) {
                        var10001 = false;
                        break;
                    }

                    ++var4;
                }
            }

            var2 = var1;
        }

        f("DeviceInfoUtil getUDIDFromSdcard : appkey = " + var0 + ", udid = " + var2);
        return var2;
    }

    public static boolean d(Context var0) {
        NetworkInfo var2 = ((ConnectivityManager)var0.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        boolean var1;
        if (var2 != null && var2.isAvailable()) {
            var1 = true;
        } else {
            var1 = false;
        }

        return var1;
    }

    public static String e() {
        return Build.MODEL;
    }

    public static String e(final Context context) {
        final String s = "";
        String bssid = null;
        Label_0065: {
            try {
                final WifiInfo connectionInfo = ((WifiManager)context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
                if (connectionInfo != null) {
                    bssid = connectionInfo.getBSSID();
                    break Label_0065;
                }
            }
            catch (SecurityException ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append(ex.getMessage());
                sb.append(" add permission android.permission.ACCESS_WIFI_STATE");
                g(sb.toString());
            }
            bssid = "";
        }
        if (bssid == null) {
            bssid = s;
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("DeviceUtil getBSSID= ");
        sb2.append(bssid);
        f(sb2.toString());
        return bssid;
    }

    private static String e(String var0) {
        Object var1 = null;

        try {
            var0 = a(MessageDigest.getInstance("SHA-1").digest(var0.getBytes("UTF-8")));
        } catch (Exception var2) {
            var0 = (String)var1;
        }

        return var0;
    }

    public static String f() {
        return Build.MANUFACTURER;
    }

    public static String f(Context var0) {
        String var3;
        try {
            var3 = ExampleApp.VERSION_NAME;
        } catch (Exception var2) {
            var2.printStackTrace();
            var3 = "";
        }

        String var1 = var3;
        if (var3 == null) {
            var1 = "";
        }

        f("DeviceUtil getAppVersion= " + var1);
        return var1;
    }

    private static void f(String var0) {
        if (BuildConfig.DEBUG) {
            Log.i("DeviceInfoUtil", var0);
        }

    }

    public static String g() {
        return VERSION.RELEASE;
    }

    public static String g(Context var0) {
        String var3;
        try {
            var3 = ExampleApp.PROCESS_NAME;
        } catch (Exception var2) {
            var2.printStackTrace();
            var3 = "";
        }

        String var1 = var3;
        if (var3 == null) {
            var1 = "";
        }

        f("DeviceUtil getAppPackageName= " + var1);
        return var1;
    }

    private static void g(String var0) {
        if (BuildConfig.DEBUG) {
            Log.i("DeviceInfoUtil", var0);
        }

    }

    public static String h() {
        String var0 = "";
        String var1 = j() + File.separator + m();
        String var2 = var0;
        if ((new File(var1)).exists()) {
            try {
                RandomAccessFile var4 = new RandomAccessFile(var1, "rw");
                var2 = var4.readLine();
            } catch (Exception var3) {
                g(var3.getMessage() + "readSN error");
                var2 = var0;
            }
        }

        return var2;
    }

    public static String h(Context var0) {
        String var1;
        if (var0 == null) {
            var1 = "000000000000000";
        } else {
            WifiInfo var2 = ((WifiManager)var0.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
            if (var2 != null) {
                var1 = var2.getMacAddress();
            } else {
                f("DeviceUtil getMac= 000000000000000");
                var1 = "000000000000000";
            }
        }

        return var1;
    }

    private static void h(String var0) {
        if (BuildConfig.DEBUG) {
            Log.i("DeviceInfoUtil", var0);
        }

    }

    private static String i() {
        StringBuilder var0 = new StringBuilder();

        String var12;
        String var13;
        label91: {
            BufferedReader var1;
            boolean var10001;
            FileReader var2;
            label80: {
                StringBuilder var3;
                try {
                    var13 = j();
                    var3 = new StringBuilder();
                    var2 = new FileReader(var3.append(var13).append(File.separator).append(A).toString());
                } catch (Exception var11) {
                    var1 = null;
                    var2 = null;
                    var12 = "";
                    break label80;
                }

                try {
                    var1 = new BufferedReader(var2);
                } catch (Exception var7) {
                    var1 = null;
                    var12 = "";
                    break label80;
                }

                label68: {
                    while(true) {
                        String var15;
                        try {
                            var15 = var1.readLine();
                        } catch (Exception var10) {
                            var10001 = false;
                            break;
                        }

                        if (var15 != null) {
                            try {
                                var0.append(var15);
                            } catch (Exception var8) {
                                var10001 = false;
                                break;
                            }
                        } else {
                            try {
                                var12 = var0.toString();
                                break label68;
                            } catch (Exception var9) {
                                var10001 = false;
                                break;
                            }
                        }
                    }

                    var12 = "";
                    break label80;
                }

                try {
                    var3 = new StringBuilder();
                    f(var3.append("DeviceInfoUtil udidContent = ").append(var12).toString());
                    break label91;
                } catch (Exception var6) {
                }
            }

            label92: {
                IOException var10000;
                label81: {
                    if (var1 != null) {
                        try {
                            var1.close();
                        } catch (IOException var5) {
                            var10000 = var5;
                            var10001 = false;
                            break label81;
                        }
                    }

                    var13 = var12;
                    if (var2 == null) {
                        return var13;
                    }

                    try {
                        var2.close();
                        break label92;
                    } catch (IOException var4) {
                        var10000 = var4;
                        var10001 = false;
                    }
                }

                IOException var14 = var10000;
                var14.printStackTrace();
                var13 = var12;
                return var13;
            }

            var13 = var12;
            return var13;
        }

        var13 = var12;
        return var13;
    }

    public static String i(Context var0) {
        String var6;
        label40: {
            label39: {
                SecurityException var10000;
                label43: {
                    boolean var10001;
                    WifiInfo var5;
                    try {
                        var5 = ((WifiManager)var0.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
                    } catch (SecurityException var4) {
                        var10000 = var4;
                        var10001 = false;
                        break label43;
                    }

                    if (var5 == null) {
                        break label39;
                    }

                    try {
                        var6 = var5.getSSID();
                    } catch (SecurityException var3) {
                        var10000 = var3;
                        var10001 = false;
                        break label43;
                    }

                    if (var6 == null) {
                        break label39;
                    }

                    try {
                        if (!var6.equals("")) {
                            var6 = var6.substring(1, var6.length() - 1);
                            break label40;
                        }
                        break label39;
                    } catch (SecurityException var2) {
                        var10000 = var2;
                        var10001 = false;
                    }
                }

                SecurityException var7 = var10000;
                g(var7.getMessage() + " add permission android.permission.ACCESS_WIFI_STATE");
            }

            var6 = "";
        }

        String var1 = var6;
        if (var6 == null) {
            var1 = "";
        }

        f("DeviceUtil getWifiSSID= " + var1);
        return var1;
    }

    private static String j() {
        String var0;
        if (Environment.getExternalStorageState().equals("mounted")) {
            var0 = Environment.getExternalStorageDirectory().getPath() + File.separator + "unisound/sdk";
        } else {
            var0 = "/mnt/sdcard/unisound/sdk";
        }

        return var0;
    }

    public static String j(Context var0) {
        String var1 = ((TelephonyManager)var0.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
        if (var1 != null) {
            if (!var1.startsWith("46000") && !var1.startsWith("46002")) {
                if (var1.startsWith("46001")) {
                    var1 = "中国联通";
                } else if (var1.startsWith("46003")) {
                    var1 = "中国电信";
                } else {
                    var1 = "未找到对应运营商";
                }
            } else {
                var1 = "中国移动";
            }
        } else {
            var1 = "没有手机卡";
        }

        f("DeviceUtil operator= " + var1);
        return var1;
    }

    private static String k() {
        String s2;
        final String s = s2 = "00000000000000000000000000000000";
        String trim;
        try {
            final Process exec = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            trim = s;
            if (exec != null) {
                s2 = s;
                final InputStream inputStream = exec.getInputStream();
                trim = s;
                if (inputStream != null) {
                    s2 = s;
                    s2 = s;
                    s2 = s;
                    final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    s2 = s;
                    final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    Label_0143:
                    while (true) {
                        s2 = s;
                        final String line = bufferedReader.readLine();
                        while (true) {
                            trim = s;
                            if (line == null) {
                                break Label_0143;
                            }
                            s2 = s;
                            if (line.indexOf("Serial") <= -1) {
                                continue Label_0143;
                            }
                            s2 = s;
                            if (line.length() <= 1) {
                                continue;
                            }
                            s2 = s;
                            if (line.contains(":")) {
                                break;
                            }
                        }
                        s2 = s;
                        trim = line.substring(line.indexOf(":") + 1, line.length()).trim();
                        break;
                    }
                    s2 = trim;
                    bufferedReader.close();
                    s2 = trim;
                    inputStream.close();
                }
            }
        }
        catch (IOException ex) {
            trim = s2;
        }
        return trim;
    }

    private static String k(Context var0) {
        if (A.equals("")) {
            String var2 = b(var0);
            q = var2;
            String var1 = k();
            A = e(var2 + var1);
        }

        return A;
    }

    private static String l() {
        String var0 = UUID.randomUUID().toString();
        h("DeviceInfoUtil buildUdidFromUUId udid = " + var0);
        return var0;
    }

    private static String m() {
        return "SN";
    }
}

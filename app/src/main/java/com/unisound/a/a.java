package com.unisound.a;

import android.telephony.*;
import android.net.*;
import android.text.*;
import android.util.Base64;
import com.phicomm.speaker.device.BuildConfig;
import com.phicomm.speaker.device.ExampleApp;
import org.json.*;
import android.provider.*;
import android.net.wifi.*;
import android.content.*;

import java.lang.Process;
import java.security.*;
import android.util.*;
import android.os.*;
import java.io.*;
import java.util.*;

import static android.provider.Settings.Secure.getString;

public class a
{
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
        super();
    }

    public static String a() {
        return Build.MODEL;
    }

    public static String a(final Context context, final String s) {
        return a(context, s, com.unisound.a.a.C);
    }

    private static String a(final Context context, final String s, final String s2) {
        final String d = d(s);
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfoUtil getUDID deviceSn = ");
        sb.append(s2);
        f(sb.toString());
        if (d != null && !d.equals("")) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("DeviceInfoUtil getUDID from sdcard= ");
            sb2.append(d);
            f(sb2.toString());
            if (TextUtils.isEmpty((CharSequence)s2) || b(s, d, s2)) {
                c(context, s, d);
                return d;
            }
        }
        else {
            final String b = b(context, s);
            if (b == null || b.equals("")) {
                final String s3 = b(context, s, s2);
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("DeviceInfoUtil first getUDID= ");
                sb3.append(s3);
                f(sb3.toString());
                return s3;
            }
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("DeviceInfoUtil getUDID from sharedPreferences= ");
            sb4.append(b);
            f(sb4.toString());
            if (TextUtils.isEmpty((CharSequence)s2) || b(s, b, s2)) {
                a(com.unisound.a.a.A, s, b);
                return b;
            }
        }
        return b(context, s, s2);
    }

    public static String a(final String s) {
        if (TextUtils.isEmpty((CharSequence)com.unisound.a.a.z) || com.unisound.a.a.D) {
            com.unisound.a.a.z = a(com.unisound.a.a.B, s);
            if (com.unisound.a.a.D) {
                com.unisound.a.a.D = false;
            }
        }
        return com.unisound.a.a.z;
    }

    public static String a(String s, final Context context) {
        s = a(s, a(s), context);
        s = b(s, "v3.1");
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceUtil tokenJson is ");
        sb.append(s);
        h(sb.toString());
        return s;
    }

    private static String a(String encodeToString, String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("build udid by deviceSn, appkey = ");
        sb.append(encodeToString);
        sb.append(", OS = ");
        sb.append(com.unisound.a.a.G);
        sb.append(", deviceSn = ");
        sb.append(s);
        h(sb.toString());
        final int hashCode = encodeToString.hashCode();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("build udid by deviceSn, appkeyHashCode = ");
        sb2.append(hashCode);
        h(sb2.toString());
        final StringBuilder sb3 = new StringBuilder();
        sb3.append(hashCode);
        sb3.append(com.unisound.a.a.G);
        sb3.append(s);
        s = sb3.toString();
        final StringBuilder sb4 = new StringBuilder();
        sb4.append("build udid by deviceSn, deviceContent = ");
        sb4.append(s);
        h(sb4.toString());
        if (!TextUtils.isEmpty((CharSequence)s)) {
            encodeToString = Base64.encodeToString(s.getBytes(), 8);
        }
        else {
            encodeToString = "";
        }
        s = encodeToString.replaceAll("=", "").replaceAll("\\s", "");
        final StringBuilder sb5 = new StringBuilder();
        sb5.append("build udid by deviceSn, udid = ");
        sb5.append(s);
        h(sb5.toString());
        return s;
    }

    public static String a(final String s, final String s2, final Context context) {
        final String a = com.unisound.a.a.A;
        int n = 0;
        final SharedPreferences sharedPreferences = context.getSharedPreferences(a, 0);
        final String s3 = "";
        final String string = sharedPreferences.getString("deviceInfo", "");
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceUtil getTokenInfo deviceContent = ");
        sb.append(string);
        h(sb.toString());
        String string2 = s3;
        Label_0154: {
            try {
                if (!TextUtils.isEmpty((CharSequence)string)) {
                    final JSONArray jsonArray = new JSONObject(string).getJSONArray("deviceInfo");
                    JSONObject jsonObject;
                    while (true) {
                        string2 = s3;
                        if (n >= jsonArray.length()) {
                            break Label_0154;
                        }
                        jsonObject = jsonArray.getJSONObject(n);
                        if (s.equals(jsonObject.get("appkey")) && s2.equals(jsonObject.get("udid"))) {
                            break;
                        }
                        ++n;
                    }
                    string2 = jsonObject.getString("token");
                }
            }
            catch (Exception ex) {
                string2 = s3;
            }
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("getTokenInfo token = ");
        sb2.append(string2);
        h(sb2.toString());
        return string2;
    }

    private static String a(final byte[] array) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            final String hexString = Integer.toHexString(array[i] & 0xFF);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString.toUpperCase());
        }
        return sb.toString();
    }

    public static void a(final Context b) {
        com.unisound.a.a.B = b;
        if (!com.unisound.a.a.y) {
            com.unisound.a.a.w = (ConnectivityManager)b.getSystemService("connectivity");
            com.unisound.a.a.u = (TelephonyManager)b.getSystemService("phone");
            final ConnectivityManager w = com.unisound.a.a.w;
            if (w != null) {
                com.unisound.a.a.v = w.getNetworkInfo(0);
            }
            com.unisound.a.a.s = ExampleApp.PROCESS_NAME;
            com.unisound.a.a.r = c(b);
            com.unisound.a.a.q = b(b);
            com.unisound.a.a.t = Build.MODEL;
            com.unisound.a.a.y = true;
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
            final StringBuilder sb = new StringBuilder();
            sb.append(j);
            sb.append(File.separator);
            sb.append(s);
            final File file2 = new File(sb.toString());
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
                        jsonObject2.put("udid", (Object)string);
                        n3 = 1;
                        break;
                    }
                    ++n2;
                }
                jsonObject3 = jsonObject;
                if (n3 == 0) {
                    final JSONObject jsonObject4 = new JSONObject();
                    jsonObject4.put("appkey", (Object)s2);
                    jsonObject4.put("udid", (Object)string);
                    jsonArray.put((Object)jsonObject4);
                    jsonObject.put("deviceInfo", (Object)jsonArray);
                    jsonObject3 = jsonObject;
                }
            }
            else {
                jsonObject3 = new JSONObject();
                final JSONArray jsonArray2 = new JSONArray();
                final JSONObject jsonObject5 = new JSONObject();
                jsonObject5.put("appkey", (Object)s2);
                jsonObject5.put("udid", (Object)string);
                jsonArray2.put((Object)jsonObject5);
                jsonObject3.put("deviceInfo", (Object)jsonArray2);
            }
            string = jsonObject3.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("DeviceInfoUtil setUDIDToSdcard udidContent = ");
            sb2.append(string);
            f(sb2.toString());
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(j);
            sb3.append(File.separator);
            sb3.append(s);

            RandomAccessFile randomAccessFile = new RandomAccessFile(sb3.toString(), "rw");
            try {
                randomAccessFile.write(string.getBytes());
                randomAccessFile.close();
            }
            catch (Exception ex3) {}
            finally {
                s = null;
            }
        }
        catch (Exception ex4) {
            s = s3;
        }
        finally {
            s = null;
            s2 = s4;
        }
    }

    public static void a(final boolean d) {
        com.unisound.a.a.D = d;
    }

    public static int b() {
        final ConnectivityManager w = com.unisound.a.a.w;
        int c = 1;
        final NetworkInfo networkInfo = w.getNetworkInfo(1);
        if (networkInfo == null || !networkInfo.isAvailable()) {
            c = c();
        }
        return c;
    }

    public static int b(final String c) {
        if (c != null) {
            if (c.length() <= 24) {
                com.unisound.a.a.C = c;
                final StringBuilder sb = new StringBuilder();
                sb.append("DeviceInfoUtil setDeviceSn = ");
                sb.append(com.unisound.a.a.C);
                f(sb.toString());
                return 0;
            }
            com.unisound.a.a.C = "";
        }
        return -1;
    }

    public static String b(final Context context) {
        String s = ((TelephonyManager)context.getSystemService("phone")).getDeviceId();
        if (s == null || "".equals(s) || s.equals("000000000000000")) {
            final String string =getString(context.getContentResolver(), "android_id");
            if (string != null && !"".equals(string)) {
                s = string;
                if (!string.equals("000000000000000")) {
                    return s;
                }
            }
            final WifiInfo connectionInfo = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                s = connectionInfo.getMacAddress();
            }
            else {
                s = "000000000000000";
            }
        }
        return s;
    }

    private static String b(final Context context, final String s) {
        final String a = com.unisound.a.a.A;
        int n = 0;
        final SharedPreferences sharedPreferences = context.getSharedPreferences(a, 0);
        final String s2 = "";
        final String string = sharedPreferences.getString("deviceInfo", "");
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfoUtil getUDIDFromSb deviceContent = ");
        sb.append(string);
        h(sb.toString());
        String string2 = s2;
        try {
            if (!TextUtils.isEmpty((CharSequence)string)) {
                final JSONArray jsonArray = new JSONObject(string).getJSONArray("deviceInfo");
                JSONObject jsonObject;
                while (true) {
                    string2 = s2;
                    if (n >= jsonArray.length()) {
                        return string2;
                    }
                    jsonObject = jsonArray.getJSONObject(n);
                    if (s.equals(jsonObject.get("appkey"))) {
                        break;
                    }
                    ++n;
                }
                string2 = jsonObject.getString("udid");
            }
        }
        catch (Exception ex) {
            string2 = s2;
        }
        return string2;
    }

    private static String b(final Context context, final String s, String s2) {
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            s2 = a(s, s2);
        }
        else {
            s2 = l();
        }
        c(context, s, s2);
        a(com.unisound.a.a.A, s, s2);
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfoUtil createNewUDID UDID= ");
        sb.append(s2);
        f(sb.toString());
        return s2;
    }

    private static String b(final String s, final String s2) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put ("deviceToken", s);
            jsonObject.put("activateVersion", s2);

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static void b(final boolean e) {
        com.unisound.a.a.E = e;
    }

    private static boolean b(final String s, final String s2, final String s3) {
        final boolean equals = s2.equals(a(s, s3));
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfoUtil isUdidMatchDeviceSn = ");
        sb.append(equals);
        f(sb.toString());
        return equals;
    }

    public static int c() {
        final NetworkInfo v = com.unisound.a.a.v;
        int n = 0;
        if (v != null && v.isAvailable()) {
            switch (com.unisound.a.a.u.getNetworkType()) {
                default: {
                    n = 4;
                    break;
                }
                case 3:
                case 5:
                case 6:
                case 8: {
                    n = 2;
                    break;
                }
                case 1:
                case 2:
                case 4: {
                    n = 3;
                    break;
                }
            }
        }
        else {
            n = 0;
        }
        return n;
    }

    public static String c(final Context context) {
        String networkOperator = ((TelephonyManager)context.getSystemService("phone")).getNetworkOperator();
        if (networkOperator == null || "".equals(networkOperator)) {
            networkOperator = "0";
        }
        return networkOperator;
    }

    private static void c(final Context context, final String s, final String s2) {
        final String a = com.unisound.a.a.A;
        final int n = 0;
        final SharedPreferences sharedPreferences = context.getSharedPreferences(a, 0);
        final String string = sharedPreferences.getString("deviceInfo", "");
        try {
            String s3;
            if (TextUtils.isEmpty((CharSequence)string)) {
                final JSONObject jsonObject = new JSONObject();
                final JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("appkey", (Object)s);
                jsonObject2.put("udid", (Object)s2);
                jsonObject2.put("token", (Object)"");
                final JSONArray jsonArray = new JSONArray();
                jsonArray.put((Object)jsonObject2);
                jsonObject.put("deviceInfo", (Object)jsonArray);
                s3 = jsonObject.toString();
            }
            else {
                final JSONObject jsonObject3 = new JSONObject(string);
                final JSONArray jsonArray2 = jsonObject3.getJSONArray("deviceInfo");
                int n2 = 0;
                int n3;
                while (true) {
                    n3 = n;
                    if (n2 >= jsonArray2.length()) {
                        break;
                    }
                    final JSONObject jsonObject4 = jsonArray2.getJSONObject(n2);
                    if (s.equals(jsonObject4.get("appkey"))) {
                        if (!s2.equals(jsonObject4.get("udid"))) {
                            jsonObject4.put("udid", (Object)s2);
                            jsonObject4.put("token", (Object)"");
                        }
                        n3 = 1;
                        break;
                    }
                    ++n2;
                }
                if (n3 == 0) {
                    final JSONObject jsonObject5 = new JSONObject();
                    jsonObject5.put("appkey", (Object)s);
                    jsonObject5.put("udid", (Object)s2);
                    jsonObject5.put("token", (Object)"");
                    jsonArray2.put((Object)jsonObject5);
                    jsonObject3.put("deviceInfo", (Object)jsonArray2);
                }
                s3 = jsonObject3.toString();
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("DeviceInfoUtil setUDIDToSb deviceContent = ");
            sb.append(s3);
            h(sb.toString());
            final SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("deviceInfo", s3);
            edit.commit();
        }
        catch (Exception ex) {}
    }

    public static void c(final String g) {
        com.unisound.a.a.G = g;
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfoUtil setOS = ");
        sb.append(com.unisound.a.a.G);
        f(sb.toString());
    }

    public static String d() {
        return Build.PRODUCT;
    }

    private static String d(final String s) {
        String string = null;
        Label_0071: {
            Label_0068: {
                try {
                    final JSONArray jsonArray = new JSONObject(i()).getJSONArray("deviceInfo");
                    JSONObject jsonObject = null;
                    Block_3: {
                        for (int i = 0; i < jsonArray.length(); ++i) {
                            jsonObject = jsonArray.getJSONObject(i);
                            if (s.equals(jsonObject.get("appkey"))) {
                                break Block_3;
                            }
                        }
                        break Label_0068;
                    }
                    string = jsonObject.getString("udid");
                    break Label_0071;
                }
                catch (Exception ex) {}
            }
            string = "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfoUtil getUDIDFromSdcard : appkey = ");
        sb.append(s);
        sb.append(", udid = ");
        sb.append(string);
        f(sb.toString());
        return string;
    }

    public static boolean d(final Context context) {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public static String e() {
        return Build.MODEL;
    }

    public static String e(final Context context) {
        final String s = "";
        String bssid = null;
        Label_0065: {
            try {
                final WifiInfo connectionInfo = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo();
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

    private static String e(String a) {
        try {
            a = a(MessageDigest.getInstance("SHA-1").digest(a.getBytes("UTF-8")));
        }
        catch (Exception ex) {
            a = null;
        }
        return a;
    }

    public static String f() {
        return Build.MANUFACTURER;
    }

    public static String f(final Context context) {
        final String s = "";
        String versionName;
        try {
            versionName = "1.2.1";
        }
        catch (Exception ex) {
            ex.printStackTrace();
            versionName = "";
        }
        if (versionName == null) {
            versionName = s;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceUtil getAppVersion= ");
        sb.append(versionName);
        f(sb.toString());
        return versionName;
    }

    private static void f(final String s) {
        if (BuildConfig.DEBUG) {
            Log.i("DeviceInfoUtil", s);
        }
    }

    public static String g() {
        return Build.VERSION.RELEASE;
    }

    public static String g(final Context context) {
        final String s = "";
        String packageName;
        try {
            packageName = ExampleApp.PROCESS_NAME;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            packageName = "";
        }
        if (packageName == null) {
            packageName = s;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceUtil getAppPackageName= ");
        sb.append(packageName);
        f(sb.toString());
        return packageName;
    }

    private static void g(final String s) {
        if (BuildConfig.DEBUG) {
            Log.i("DeviceInfoUtil", s);
        }
    }

    public static String h() {
        final StringBuilder sb = new StringBuilder();
        sb.append(j());
        sb.append(File.separator);
        sb.append(m());
        final String string = sb.toString();
        if (new File(string).exists()) {
            try {
                return new RandomAccessFile(string, "rw").readLine();
            }
            catch (Exception ex) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(ex.getMessage());
                sb2.append("readSN error");
                g(sb2.toString());
            }
        }
        return "";
    }

    public static String h(final Context context) {
        final String s = "000000000000000";
        String macAddress;
        if (context == null) {
            macAddress = s;
        }
        else {
            final WifiInfo connectionInfo = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                macAddress = connectionInfo.getMacAddress();
            }
            else {
                f("DeviceUtil getMac= 000000000000000");
                macAddress = s;
            }
        }
        return macAddress;
    }

    private static void h(final String s) {
        if (BuildConfig.DEBUG) {
            Log.i("DeviceInfoUtil", s);
        }
    }

    private static String i() {
        final String s = "";
        final StringBuilder sb = new StringBuilder();
        Object line = null;
        InputStreamReader inputStreamReader;
        String string;
        try {
            final String j = j();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(j);
            sb2.append(File.separator);
            sb2.append(com.unisound.a.a.A);
            inputStreamReader = new FileReader(sb2.toString());
            try {
                final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    string = s;
                    try {
                        line = bufferedReader.readLine();
                        if (line != null) {
                            string = s;
                            sb.append((String)line);
                            continue;
                        }
                        string = s;
                        final String s2 = string = sb.toString();
                        line = new StringBuilder();
                        string = s2;
                        new StringBuilder();
                        string = s2;
                        ((StringBuilder)line).append("DeviceInfoUtil udidContent = ");
                        string = s2;
                        ((StringBuilder)line).append(s2);
                        string = s2;
                        f(((StringBuilder)line).toString());
                        return s2;
                    }
                    catch (Exception e) {
                        line = bufferedReader;
                    }
                }
            }
            catch (Exception ex2) {
                string = s;
            }
        }
        catch (Exception ex3) {
            inputStreamReader = null;
            string = s;
        }

        String s2 = string;
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            s2 = string;
        }
        return s2;
    }

    public static String i(final Context context) {
        final String s = "";
        String substring = null;
        Label_0090: {
            try {
                final WifiInfo connectionInfo = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo();
                if (connectionInfo != null) {
                    final String ssid = connectionInfo.getSSID();
                    if (ssid != null && !ssid.equals("")) {
                        substring = ssid.substring(1, ssid.length() - 1);
                        break Label_0090;
                    }
                }
            }
            catch (SecurityException ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append(ex.getMessage());
                sb.append(" add permission android.permission.ACCESS_WIFI_STATE");
                g(sb.toString());
            }
            substring = "";
        }
        if (substring == null) {
            substring = s;
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("DeviceUtil getWifiSSID= ");
        sb2.append(substring);
        f(sb2.toString());
        return substring;
    }

    private static String j() {
        String string;
        if (Environment.getExternalStorageState().equals("mounted")) {
            final StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStorageDirectory().getPath());
            sb.append(File.separator);
            sb.append("unisound/sdk");
            string = sb.toString();
        }
        else {
            string = "/mnt/sdcard/unisound/sdk";
        }
        return string;
    }

    public static String j(final Context context) {
        final String subscriberId = ((TelephonyManager)context.getSystemService("phone")).getSubscriberId();
        String s;
        if (subscriberId != null) {
            if (!subscriberId.startsWith("46000") && !subscriberId.startsWith("46002")) {
                if (subscriberId.startsWith("46001")) {
                    s = "中国联通";
                }
                else if (subscriberId.startsWith("46003")) {
                    s = "中国电信";
                }
                else {
                    s = "中国移动";
                }
            }
            else {
                s = "未找到对应运营商";
            }
        }
        else {
            s = "没有手机卡";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceUtil operator= ");
        sb.append(s);
        f(sb.toString());
        return s;
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

    private static String k(final Context context) {
        if (com.unisound.a.a.A.equals("")) {
            final String s = com.unisound.a.a.q = b(context);
            final String k = k();
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append(k);
            com.unisound.a.a.A = e(sb.toString());
        }
        return com.unisound.a.a.A;
    }

    private static String l() {
        final String string = UUID.randomUUID().toString();
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfoUtil buildUdidFromUUId udid = ");
        sb.append(string);
        h(sb.toString());
        return string;
    }

    private static String m() {
        return "SN";
    }
}

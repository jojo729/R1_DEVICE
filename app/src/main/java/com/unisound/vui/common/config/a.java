package com.unisound.vui.common.config;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.unisound.common.y;
import com.unisound.vui.common.file.FileHelper;
import com.unisound.vui.util.LogMgr;
import org.apache.commons.io.IOUtils;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f366a = false;
    private static HashMap<String, String> b = new HashMap<>();
    private static String c = "";
    private static String d = "";
    private static String e = "";
    private static ArrayList<AbstractC0008a> f = new ArrayList<>();

    /* renamed from: com.unisound.vui.common.config.a$a  reason: collision with other inner class name */
    public interface AbstractC0008a {
    }

    public static float a(String str, float f2) {
        return Float.valueOf(a(str, String.valueOf(f2))).floatValue();
    }

    public static int a(String str, int i) {
        return Integer.valueOf(a(str, String.valueOf(i))).intValue();
    }

    public static String a(String str) {
        String trim = str == null ? "" : str.trim();
        return trim.contains("%sdcard%") ? trim.replaceAll("%sdcard%", c) : trim.contains("%files%") ? trim.replaceAll("%files%", d) : trim.contains("%cache%") ? trim.replaceAll("%cache%", e) : trim;
    }

    public static String a(String str, String str2) {
        return (b == null || !b.containsKey(str)) ? str2 : b.get(str);
    }

    private static void a() {
        LogMgr.d("ANTPrivatePreference", "printConfig");
        if (b != null) {
            for (String str : b.keySet()) {
                LogMgr.d("ANTPrivatePreference", "[" + str + "=" + b.get(str) + "]");
            }
        }
    }

    public static void a(Context context) {
        LogMgr.d("ANTPrivatePreference", y.y);
        if (context != null && !f366a) {
            d = context.getFilesDir().getAbsolutePath();
            e = context.getCacheDir().getAbsolutePath();
            if (android.os.Process.myUid() == 1000) {
                LogMgr.e("ANTPrivatePreference", "Process is SYSTEM Process, Can't use getExternalStorageDirectory, will use %files% folder");
                c = d;
            } else {
                c = Environment.getExternalStorageDirectory().getPath();
            }
            File file = new File("system", "unisound/config");
            File file2 = new File(new File("sdcard", "unisound/config").getAbsolutePath(), "config.mg");
            File file3 = new File(file.getAbsolutePath(), "config.mg");
            if (file2.exists()) {
                try {
                    LogMgr.d("ANTPrivatePreference", "read sdcard file");
                    a(new FileInputStream(file2), context);
                } catch (FileNotFoundException e2) {
                }
            } else {
                if (!file3.exists()) {
                    FileHelper.copyFileFromAssets(context, "config", "config.mg", file);
                }
                if (file3.exists()) {
                    try {
                        LogMgr.d("ANTPrivatePreference", "read system file");
                        a(new FileInputStream(file3), context);
                    } catch (FileNotFoundException e3) {
                    }
                } else {
                    try {
                        LogMgr.d("ANTPrivatePreference", "read assest file");
                        a(context.getAssets().open("config/config.mg"), context);
                    } catch (IOException e4) {
                    }
                }
                a();
            }
        }
    }

    public static void a(AbstractC0008a aVar) {
        f.add(aVar);
    }

    private static void a(InputStream inputStream, Context context) {
        if (inputStream != null) {
            try {
                if (inputStream.available() > 0) {
                    byte[] bArr = new byte[inputStream.available()];
                    inputStream.read(bArr);
                    inputStream.close();
                    String str = new String(bArr, StandardCharsets.UTF_8);
                    if (!TextUtils.isEmpty(str)) {
                        str.replaceAll(IOUtils.LINE_SEPARATOR_WINDOWS, "\n");
                        String[] split = str.split("\n");
                        if (split.length > 0) {
                            for (String str2 : split) {
                                if (!TextUtils.isEmpty(str)) {
                                    String trim = str2.trim();
                                    if (trim.indexOf(MqttTopic.MULTI_LEVEL_WILDCARD) != 0) {
                                        String[] split2 = trim.split("=");
                                        if (split2.length > 1) {
                                            String str3 = split2.length >= 1 ? split2[0] : "";
                                            b.put(str3 == null ? "" : str3.trim(), a(split2.length >= 2 ? split2[1] : ""));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e2) {
            }
        }
    }

    public static boolean a(String str, boolean z) {
        return Boolean.valueOf(a(str, String.valueOf(z))).booleanValue();
    }
}

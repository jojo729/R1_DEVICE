//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.vui.util;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Keep;
import com.baidu.mobstat.Build;
import com.phicomm.speaker.device.BuildConfig;
import com.phicomm.speaker.device.utils.Setting;

@Keep
public class LogMgr {
    private static final String DEFAULT_TAG = "UniCar";

    private static final int ERROR = 5;
    private static final int WARN = 4;
    private static final int INFO = 3;
    private static final int DEBUG = 2;
    public static final int VERBOSE = 1;

    private static final int SETTING_LEVEL = Setting.LogMgrLevel;

    private LogMgr() {
    }

    public static void d(String var0) {
        if (getLevel() <= DEBUG) {
            StackTraceElement var1 = Thread.currentThread().getStackTrace()[3];
            Log.d(getDefaultTag(var1), getLogInfo(var1) + var0);
        }

    }

    public static void d(String var0, String var1) {
        if (getLevel() <= DEBUG) {
            StackTraceElement var2 = Thread.currentThread().getStackTrace()[3];
            String var3 = var0;
            if (TextUtils.isEmpty(var0)) {
                var3 = getDefaultTag(var2);
            }

            Log.d(var3, getLogInfo(var2) + var1);
        }

    }

    public static void d(String var0, String var1, Object... var2) {
        if (getLevel() <= DEBUG) {
            StackTraceElement var3 = Thread.currentThread().getStackTrace()[3];
            String var4 = var0;
            if (TextUtils.isEmpty(var0)) {
                var4 = getDefaultTag(var3);
            }

            Log.d(var4, getLogInfo(var3) + String.format(var1, var2));
        }

    }

    public static void e(String var0) {
        if (getLevel() <= ERROR) {
            StackTraceElement var1 = Thread.currentThread().getStackTrace()[3];
            Log.e(getDefaultTag(var1), getLogInfo(var1) + var0);
        }

    }

    public static void e(String var0, String var1) {
        if (getLevel() <= ERROR) {
            StackTraceElement var2 = Thread.currentThread().getStackTrace()[3];
            String var3 = var0;
            if (TextUtils.isEmpty(var0)) {
                var3 = getDefaultTag(var2);
            }

            Log.e(var3, getLogInfo(var2) + var1);
        }

    }

    public static void e(String var0, String var1, Throwable var2) {
        if (getLevel() <= ERROR) {
            StackTraceElement var3 = Thread.currentThread().getStackTrace()[3];
            String var4 = var0;
            if (TextUtils.isEmpty(var0)) {
                var4 = getDefaultTag(var3);
            }

            Log.e(var4, getLogInfo(var3) + var1, var2);
        }

    }

    public static void e(String var0, String var1, Object... var2) {
        if (getLevel() <= ERROR) {
            StackTraceElement var3 = Thread.currentThread().getStackTrace()[3];
            String var4 = var0;
            if (TextUtils.isEmpty(var0)) {
                var4 = getDefaultTag(var3);
            }

            Log.e(var4, getLogInfo(var3) + String.format(var1, var2));
        }

    }

    public static String getDefaultTag(StackTraceElement var0) {
        Object var1 = null;

        String var3;
        try {
            var3 = var0.getFileName().split("\\.")[0];
        } catch (Exception var2) {
            var3 = (String)var1;
        }

        return "UniCar-" + var3;
    }

    private static int getLevel() {
        return SETTING_LEVEL;
    }

    public static String getLogInfo(StackTraceElement var0) {
        StringBuilder var1 = new StringBuilder();
        Thread.currentThread().getName();
        Thread.currentThread().getId();
        var0.getFileName();
        var0.getClassName();
        var0.getMethodName();
        int var2 = var0.getLineNumber();
        var1.append("[ ");
        var1.append("lineNumber =" + var2);
        var1.append(" ] ");
        return var1.toString();
    }

    public static void i(String var0) {
        if (getLevel() <= INFO) {
            StackTraceElement var1 = Thread.currentThread().getStackTrace()[3];
            Log.i(getDefaultTag(var1), getLogInfo(var1) + var0);
        }

    }

    public static void i(String var0, String var1) {
        if (getLevel() <= INFO) {
            StackTraceElement var2 = Thread.currentThread().getStackTrace()[3];
            String var3 = var0;
            if (TextUtils.isEmpty(var0)) {
                var3 = getDefaultTag(var2);
            }

            Log.i(var3, getLogInfo(var2) + var1);
        }

    }

    public static void v(String var0) {
        if (getLevel() <= VERBOSE) {
            StackTraceElement var1 = Thread.currentThread().getStackTrace()[3];
            Log.v(getDefaultTag(var1), getLogInfo(var1) + var0);
        }

    }

    public static void v(String var0, String var1) {
        if (getLevel() <= VERBOSE) {
            StackTraceElement var2 = Thread.currentThread().getStackTrace()[3];
            String var3 = var0;
            if (TextUtils.isEmpty(var0)) {
                var3 = getDefaultTag(var2);
            }

            Log.v(var3, getLogInfo(var2) + var1);
        }

    }

    public static void w(String var0) {
        if (getLevel() <= WARN) {
            StackTraceElement var1 = Thread.currentThread().getStackTrace()[3];
            Log.w(getDefaultTag(var1), getLogInfo(var1) + var0);
        }

    }

    public static void w(String var0, String var1) {
        if (getLevel() <= WARN) {
            StackTraceElement var2 = Thread.currentThread().getStackTrace()[3];
            String var3 = var0;
            if (TextUtils.isEmpty(var0)) {
                var3 = getDefaultTag(var2);
            }

            Log.w(var3, getLogInfo(var2) + var1);
        }

    }
}

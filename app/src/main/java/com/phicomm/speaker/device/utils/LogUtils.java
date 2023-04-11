package com.phicomm.speaker.device.utils;

import android.util.Log;
import com.phicomm.speaker.device.BuildConfig;
import java.util.Locale;

public class LogUtils {
    private static final String TAG = "default_tag";
    private static final int ERROR = 5;
    private static final int WARN = 4;
    private static final int INFO = 3;
    private static final int DEBUG = 2;
    public static final int VERBOSE = 1;

    private static final int SETTING_LEVEL = Setting.LogMgrLevel;

    public static void d(String msg) {
        if (SETTING_LEVEL <= DEBUG) {
            Log.d(TAG, String.format(Locale.CHINA, "[%s]", msg));
        }
    }

    public static void d(String tag, String msg) {
        if (SETTING_LEVEL <=DEBUG) {
            Log.d(tag, String.format(Locale.CHINA, "[%s]", msg));
        }
    }

    public static void d(String msg, Throwable tr) {
        if (SETTING_LEVEL <=DEBUG) {
            Log.d(TAG, String.format(Locale.CHINA, "[%s]", msg), tr);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (SETTING_LEVEL <=DEBUG) {
            Log.d(tag, String.format(Locale.CHINA, "[%s]", msg), tr);
        }
    }

    public static void iAnyhow(String msg) {
        Log.i(TAG, String.format(Locale.CHINA, "[%s]", msg));
    }

    public static void i(String msg) {
        if (SETTING_LEVEL <=INFO) {
            Log.i(TAG, String.format(Locale.CHINA, "[%s]", msg));
        }
    }

    public static void i(String tag, String msg) {
        if (SETTING_LEVEL <=INFO) {
            Log.i(tag, String.format(Locale.CHINA, "[%s]", msg));
        }
    }

    public static void w(String msg) {
        if (SETTING_LEVEL <=WARN) {
            Log.w(TAG, String.format(Locale.CHINA, "[%s]", msg));
        }
    }

    public static void w(String tag, String msg) {
        if (SETTING_LEVEL <=WARN) {
            Log.w(tag, String.format(Locale.CHINA, "[%s]", msg));
        }
    }

    public static void w(String msg, Throwable tr) {
        if (SETTING_LEVEL <=WARN) {
            Log.w(TAG, String.format(Locale.CHINA, "[%s]", msg), tr);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (SETTING_LEVEL <=WARN) {
            Log.w(tag, String.format(Locale.CHINA, "[%s]", msg), tr);
        }
    }

    public static void e(Throwable tr) {
        if (SETTING_LEVEL <=ERROR) {
            Log.e(TAG, "[error]", tr);
        }
    }

    public static void e(String msg, Throwable tr) {
        if (SETTING_LEVEL <=ERROR) {
            Log.e(TAG, String.format(Locale.CHINA, "[%s]", msg), tr);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (SETTING_LEVEL <=ERROR) {
            Log.e(tag, String.format(Locale.CHINA, "[%s]", msg), tr);
        }
    }
}

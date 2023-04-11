package com.unisound.b;

import android.util.Log;
import com.phicomm.speaker.device.BuildConfig;

public class i {

    /* renamed from: a  reason: collision with root package name */
    public static boolean a = BuildConfig.DEBUG;
    private static final String b = "USCActivate";

    public static void a(String str) {
        if (a) {
            Log.e(b, str);
        }
    }

    public static void a(String str, String str2) {
        if (a) {
            Log.e(str, str2);
        }
    }

    public static void b(String str) {
        if (a) {
            Log.w(b, str);
        }
    }

    public static void b(String str, String str2) {
        if (a) {
            Log.w(str, str2);
        }
    }

    public static void c(String str) {
        if (a) {
            Log.i(b, str);
        }
    }

    public static void c(String str, String str2) {
        if (a) {
            Log.i(str, str2);
        }
    }

    public static void d(String str) {
        if (a) {
            Log.d(b, str);
        }
    }

    public static void d(String str, String str2) {
        if (a) {
            Log.d(str, str2);
        }
    }
}

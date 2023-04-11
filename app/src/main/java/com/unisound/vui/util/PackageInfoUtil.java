package com.unisound.vui.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.phicomm.speaker.device.BuildConfig;
import com.phicomm.speaker.device.ExampleApp;

public class PackageInfoUtil {
    private PackageInfoUtil() {
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getVersionCode(Context context) {
        return ExampleApp.VERSION_CODE;
    }

    public static String getVersionName(Context context) {
        return ExampleApp.VERSION_NAME;
    }

    public static boolean isZhSystem(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }
}

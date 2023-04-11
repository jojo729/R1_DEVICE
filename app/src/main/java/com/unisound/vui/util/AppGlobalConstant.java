package com.unisound.vui.util;

import android.content.Context;

public class AppGlobalConstant {
    private static Context mContext;
    private static String udid = "";

    public static Context getContext() {
        return mContext;
    }

    public static String getUdid() {
        return udid;
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    public static void setUdid(String udid2) {
        udid = udid2;
    }
}

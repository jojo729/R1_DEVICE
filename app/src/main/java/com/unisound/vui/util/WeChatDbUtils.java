package com.unisound.vui.util;

import android.content.Context;
import com.unisound.vui.data.entity.a.d;
import com.unisound.vui.data.f.a;
import java.util.ArrayList;
import java.util.List;

public class WeChatDbUtils {
    private static Context mContext;
    private static a weChatDbCallBack;

    private WeChatDbUtils() {
    }

    private static void callbackMessage(d weChatMessage) {
        if (weChatDbCallBack != null) {
            weChatDbCallBack.a(weChatMessage);
        }
    }

    public static void clearAll() {
        com.unisound.vui.data.a.a.a(mContext).c();
    }

    public static List<d> getAllWeChatMessageByUser(String userName) {
        List<d> a2 = com.unisound.vui.data.a.a.a(mContext).a(userName);
        return a2 == null ? new ArrayList() : a2;
    }

    public static void init(Context mContext2) {
        mContext = mContext2;
    }

    public static void saveWechatMessage(d weChatMessage) {
        com.unisound.vui.data.a.a.a(mContext).a(weChatMessage);
        callbackMessage(weChatMessage);
    }

    public static void setWeChatDbCallBack(a weChatDbCallBack2) {
        weChatDbCallBack = weChatDbCallBack2;
    }

    public static void upWechatMessage(d weChatMessage) {
        com.unisound.vui.data.a.a.a(mContext).b(weChatMessage);
        callbackMessage(weChatMessage);
    }
}

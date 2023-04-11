package com.unisound.vui.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.unisound.vui.common.network.b;

public class AppStateReceiver extends BroadcastReceiver {
    public static final String INSTALL_APP = "android.intent.action.PACKAGE_ADDED";
    public static final String UNINSTALL_APP = "android.intent.action.PACKAGE_REMOVED";

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(INSTALL_APP)) {
            b.a(context);
        } else if (intent.getAction().equals(UNINSTALL_APP)) {
            b.a(context);
        }
    }
}

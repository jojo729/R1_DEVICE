package com.unisound.vui.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Parcelable;

public class NetworkConnectChangedReceiver extends BroadcastReceiver {
    public static final String NET_ALIVE_CONNECTED = "com.device.wifi.alive.connected";
    public static final String NET_CONNECTED = "com.device.wifi.connected";
    public static final String NET_CONNECTING = "com.device.wifi.connecting";
    public static final String NET_DISCONNECTED = "com.device.wifi.disconnected";
    private static final String TAG = "NetworkConnectChangedReceiver";

    private void netChangeDispatcher(Context context, Intent intent) {
        Parcelable parcelableExtra = intent.getParcelableExtra("networkInfo");
        if (parcelableExtra != null) {
            NetworkInfo.State state = ((NetworkInfo) parcelableExtra).getState();
            if (state == NetworkInfo.State.CONNECTED) {
                context.sendBroadcast(new Intent(NET_CONNECTED));
                LogMgr.d(TAG, "net state change contented");
            } else if (state == NetworkInfo.State.DISCONNECTED) {
                context.sendBroadcast(new Intent(NET_DISCONNECTED));
                LogMgr.d(TAG, "net state change disconnected");
            } else if (state == NetworkInfo.State.CONNECTING) {
                context.sendBroadcast(new Intent(NET_CONNECTING));
                LogMgr.d(TAG, "net state change connecting");
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        Parcelable parcelableExtra;
        String action = intent.getAction();
        LogMgr.d(TAG, "-->>intent action:" + action);
        if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
            switch (intent.getIntExtra("wifi_state", 0)) {
                case 0:
                    LogMgr.d(TAG, "wifi state change disabling");
                    return;
                case 1:
                    LogMgr.d(TAG, "wifi state change disabled");
                    return;
                default:
                    return;
            }
        } else if ("android.net.wifi.STATE_CHANGE".equals(action)) {
            LogMgr.d(TAG, "--->>net state change");
            Parcelable parcelableExtra2 = intent.getParcelableExtra("networkInfo");
            if (parcelableExtra2 != null && ((NetworkInfo) parcelableExtra2).getState() == NetworkInfo.State.CONNECTING) {
                context.sendBroadcast(new Intent(NET_CONNECTING));
                LogMgr.d(TAG, "net state change connecting");
            }
        } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action) && (parcelableExtra = intent.getParcelableExtra("networkInfo")) != null) {
            NetworkInfo.State state = ((NetworkInfo) parcelableExtra).getState();
            if (state == NetworkInfo.State.CONNECTED) {
                context.sendBroadcast(new Intent(NET_CONNECTED));
                LogMgr.d(TAG, "net state change contented");
            } else if (state == NetworkInfo.State.DISCONNECTED) {
                context.sendBroadcast(new Intent(NET_DISCONNECTED));
                LogMgr.d(TAG, "net state change disconnected");
            }
        }
    }
}

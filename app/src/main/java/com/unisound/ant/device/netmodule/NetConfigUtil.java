package com.unisound.ant.device.netmodule;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import com.unisound.ant.device.listener.WifiChangeListener;
import com.unisound.vui.util.LogMgr;
import java.util.List;

public class NetConfigUtil {
    private static final String TAG = "NetConfigUtil";

    public static void removeAllNetConfig(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        List<WifiConfiguration> existingConfigs = wifiManager.getConfiguredNetworks();
        if (existingConfigs != null) {
            for (WifiConfiguration existingConfig : existingConfigs) {
                int networkId = existingConfig.networkId;
                LogMgr.d(TAG, "--->>removeAllNetConfig networkId:" + networkId);
                forgetWifi(wifiManager, networkId);
            }
        }
    }

    public static void closeWifi(Context context) {
        ((WifiManager) context.getSystemService("wifi")).setWifiEnabled(false);
    }

    public static void closeWifiAndRemoveWifiCOnfig(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
        List<WifiConfiguration> existingConfigs = wifiManager.getConfiguredNetworks();
        if (existingConfigs != null) {
            for (WifiConfiguration existingConfig : existingConfigs) {
                forgetWifi(wifiManager, existingConfig.networkId);
            }
        }
    }

    public static void changeConnectWifi(String ssid, String password, WifiChangeListener listener) {
        new Thread() {
            /* class com.unisound.ant.device.netmodule.NetConfigUtil.AnonymousClass1 */

            public void run() {
            }
        }.start();
    }

    private static void forgetWifi(WifiManager wifiManager, int networkId) {
        try {
            Class<?>[] classl = wifiManager.getClass().getDeclaredClasses();
            Class<?> ActionListener = null;
            int i = 0;
            while (true) {
                if (classl == null || i >= classl.length) {
                    break;
                } else if (classl[i].getName().endsWith("ActionListener")) {
                    ActionListener = classl[i];
                    break;
                } else {
                    i++;
                }
            }
            wifiManager.getClass().getMethod("forget", Integer.TYPE, ActionListener).invoke(wifiManager, Integer.valueOf(networkId), null);
            LogMgr.d(TAG, "forgetWifi networkId:" + networkId);
        } catch (Exception e) {
            e.printStackTrace();
            LogMgr.e(TAG, "forgetWifi e:" + e);
        }
    }
}

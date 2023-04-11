package com.unisound.vui.handler.session.setting;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;

public class DefaultSettingOperator {
    WifiManager wifiManager;

    public DefaultSettingOperator(Context context) {
        this.wifiManager = (WifiManager) context.getSystemService("wifi");
    }

    public void setWifiEnable(boolean flag) {
        this.wifiManager.setWifiEnabled(flag);
    }

    public void setBluetoothEnabled(boolean enabled) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null && adapter.isEnabled() != enabled) {
            if (enabled) {
                adapter.enable();
            } else {
                adapter.disable();
            }
        }
    }
}

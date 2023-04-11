package com.unisound.ant.device.netmodule;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import com.unisound.vui.util.LogMgr;
import java.util.List;

public class WifiConnect {
    public static final int SWITCH_WIFI_CREATE_CONFIG_FAIL = 1;
    public static final int SWITCH_WIFI_OPEN_FAIL = 0;
    public static final int SWITCH_WIFI_SAVE_CONFIG_SUCCESS = 3;
    public static final int SWITCH_WIFI_SSID_NOT_EXIST = 2;
    private static final String TAG = "WifiConnect";
    private WifiManager wifiManager;

    public enum WifiCipherType {
        WIFICIPHER_WEP,
        WIFICIPHER_WPA,
        WIFICIPHER_NOPASS,
        WIFICIPHER_INVALID
    }

    public WifiConnect(Context context) {
        this.wifiManager = (WifiManager) context.getSystemService("wifi");
    }

    private boolean openWifi() {
        if (!this.wifiManager.isWifiEnabled()) {
            return this.wifiManager.setWifiEnabled(true);
        }
        return true;
    }

    public int connect(String ssid, String password, WifiCipherType type) {
        if (!openWifi()) {
            LogMgr.d(TAG, "open wifi failed");
            return 0;
        }
        LogMgr.d(TAG, "connect SSID=" + ssid + "  Password=" + password + " Type=" + type);
        int count = 0;
        while (this.wifiManager.getWifiState() != 3) {
            try {
                Thread.sleep(50);
                count++;
                LogMgr.d(TAG, "WifiState() =" + this.wifiManager.getWifiState() + "count = " + count);
                if (count > 30) {
                    return 0;
                }
            } catch (InterruptedException e) {
                return 0;
            }
        }
        WifiConfiguration wifiConfig = createWifiInfo(ssid, password, type);
        if (wifiConfig == null) {
            LogMgr.d(TAG, "wifiConfig is null");
            return 1;
        }
        WifiConfiguration tempConfig = isExsits(ssid);
        if (tempConfig != null) {
            LogMgr.d(TAG, "remove the save net");
            this.wifiManager.removeNetwork(tempConfig.networkId);
        }
        int netID = this.wifiManager.addNetwork(wifiConfig);
        LogMgr.d(TAG, "addNetwork netID == " + netID);
        if (getScanResult(ssid) == null || this.wifiManager.getConfiguredNetworks() == null) {
            return 2;
        }
        for (WifiConfiguration c0 : this.wifiManager.getConfiguredNetworks()) {
            if (c0.networkId == netID) {
                this.wifiManager.enableNetwork(c0.networkId, true);
            } else {
                this.wifiManager.enableNetwork(c0.networkId, false);
            }
        }
        LogMgr.d(TAG, "enableNetwork netID :" + netID);
        this.wifiManager.saveConfiguration();
        return 3;
    }

    private WifiConfiguration isExsits(String ssid) {
        List<WifiConfiguration> existingConfigs = this.wifiManager.getConfiguredNetworks();
        if (existingConfigs == null) {
            return null;
        }
        for (WifiConfiguration existingConfig : existingConfigs) {
            if (existingConfig.SSID.equals("\"" + ssid + "\"")) {
                return existingConfig;
            }
        }
        return null;
    }

    private WifiConfiguration createWifiInfo(String ssid, String password, WifiCipherType type) {
        WifiConfiguration wc = new WifiConfiguration();
        wc.allowedAuthAlgorithms.clear();
        wc.allowedGroupCiphers.clear();
        wc.allowedKeyManagement.clear();
        wc.allowedPairwiseCiphers.clear();
        wc.allowedProtocols.clear();
        wc.SSID = "\"" + ssid + "\"";
        wc.priority = 40;
        if (type == WifiCipherType.WIFICIPHER_NOPASS) {
            wc.wepKeys[0] = "";
            wc.allowedKeyManagement.set(0);
            wc.wepTxKeyIndex = 0;
            return wc;
        } else if (type == WifiCipherType.WIFICIPHER_WEP) {
            wc.wepKeys[0] = "\"" + password + "\"";
            wc.hiddenSSID = true;
            wc.allowedAuthAlgorithms.set(1);
            wc.allowedGroupCiphers.set(3);
            wc.allowedGroupCiphers.set(2);
            wc.allowedGroupCiphers.set(0);
            wc.allowedGroupCiphers.set(1);
            wc.allowedKeyManagement.set(0);
            wc.wepTxKeyIndex = 0;
            return wc;
        } else if (type != WifiCipherType.WIFICIPHER_WPA) {
            return null;
        } else {
            wc.preSharedKey = "\"" + password + "\"";
            wc.hiddenSSID = true;
            wc.allowedAuthAlgorithms.set(0);
            wc.allowedGroupCiphers.set(2);
            wc.allowedGroupCiphers.set(3);
            wc.allowedKeyManagement.set(1);
            wc.allowedPairwiseCiphers.set(1);
            wc.allowedPairwiseCiphers.set(2);
            wc.allowedProtocols.set(0);
            wc.allowedProtocols.set(1);
            return wc;
        }
    }

    public String getScanResult(String ssid) {
        String ss = null;
        int count = 1;
        this.wifiManager.startScan();
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            List<ScanResult> listResult = this.wifiManager.getScanResults();
            if (listResult == null || listResult.size() <= 0) {
                LogMgr.d(TAG, "listResult size is zero");
                if (count == 5) {
                    return null;
                }
                count++;
            } else {
                if (listResult != null) {
                    for (int i = 0; i < listResult.size(); i++) {
                        ScanResult mScanResult = listResult.get(i);
                        if (ssid.equals(mScanResult.SSID)) {
                            ss = mScanResult.capabilities;
                        }
                    }
                }
                return ss;
            }
        }
    }
}

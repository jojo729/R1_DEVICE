package com.unisound.ant.device.netmodule;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.unisound.ant.device.listener.WifiChangeListener;
import com.unisound.vui.util.LogMgr;

public class SwitchWifiRunnable implements Runnable, NetChangeReceiver.NetAliveConnectListener, Handler.Callback {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int NET_CONNECT_TIMEOUT = 1;
    private static final String TAG = "SwitchWifiRunnable";
    private Context context;
    private WifiChangeListener listener;
    private String password;
    private NetChangeReceiver receiver = new NetChangeReceiver();
    private String ssid;
    private Handler timeoutHandler;
    private WifiConnect wifiConnect;

    public SwitchWifiRunnable(Context context2, WifiChangeListener wifiChangeListener) {
        this.context = context2;
        this.listener = wifiChangeListener;
        this.wifiConnect = new WifiConnect(context2);
        this.receiver.setAliveConnectListener(this);
        this.receiver.registerNetStateReceiver(context2);
        this.timeoutHandler = new Handler(this);
    }

    public void setWifiInfo(String ssid2, String password2) {
        this.ssid = ssid2;
        this.password = password2;
    }

    public void run() {
        if (TextUtils.isEmpty(this.ssid)) {
            LogMgr.d(TAG, "ssid is not set and plese check setWifiInfo");
            return;
        }
        int code = this.wifiConnect.connect(this.ssid, this.password, WifiConnect.WifiCipherType.WIFICIPHER_WPA);
        if (this.listener == null || code == 3) {
            this.timeoutHandler.removeCallbacksAndMessages(null);
            this.timeoutHandler.sendEmptyMessageDelayed(1, 10000);
            return;
        }
        this.listener.onChangeWifiFail(code);
        dealWithConnectWifiFail();
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                dealWithConnectWifiFail();
                return false;
            default:
                return false;
        }
    }

    private void dealWithConnectWifiFail() {
        if (this.receiver != null) {
            this.receiver.unregisterNetStateReceiver(this.context);
        }
        this.timeoutHandler.removeCallbacksAndMessages(null);
    }

    private void dealWitchConnectWifiSuccess() {
        if (this.listener != null) {
            this.listener.onChangeWifiSuccess();
        }
        if (this.receiver != null) {
            this.receiver.unregisterNetStateReceiver(this.context);
        }
        this.timeoutHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.unisound.ant.device.netmodule.NetChangeReceiver.NetAliveConnectListener
    public void onNetAliveConnected() {
        LogMgr.d(TAG, "onNetAliveConnected");
        dealWitchConnectWifiSuccess();
    }
}

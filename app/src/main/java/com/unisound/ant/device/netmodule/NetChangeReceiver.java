package com.unisound.ant.device.netmodule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.unisound.vui.common.network.NetUtil;
import com.unisound.vui.transport.config.RuntimeConfig;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.NetworkConnectChangedReceiver;

public class NetChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "NetChangeReceiver";
    private NetAliveConnectListener aliveConnectListener;
    private boolean isOneTime = false;
    private NetStateListener stateListener;

    public interface NetAliveConnectListener {
        void onNetAliveConnected();
    }

    public interface NetStateListener {
        void onNetConnected();

        void onNetConnecting();

        void onNetDisconnected();
    }

    public void setStateListener(NetStateListener listener) {
        this.stateListener = listener;
    }

    public void setAliveConnectListener(NetAliveConnectListener listener) {
        this.aliveConnectListener = listener;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            LogMgr.d(TAG, "NetChangeReceiver OnReceiver action:" + action + "  isOneTime=" + this.isOneTime);
            if (NetworkConnectChangedReceiver.NET_CONNECTED.equals(action)) {
                RuntimeConfig.setConnectedWifi(true);
                if (this.stateListener != null && this.isOneTime) {
                    this.isOneTime = false;
                    this.stateListener.onNetConnected();
                }
            } else if (NetworkConnectChangedReceiver.NET_DISCONNECTED.equals(action)) {
                if (this.stateListener != null) {
                    this.stateListener.onNetDisconnected();
                }
            } else if (NetworkConnectChangedReceiver.NET_CONNECTING.equals(action)) {
                this.isOneTime = true;
                if (this.stateListener != null) {
                    this.stateListener.onNetConnecting();
                }
            } else if (NetworkConnectChangedReceiver.NET_ALIVE_CONNECTED.equals(action) && this.aliveConnectListener != null) {
                this.aliveConnectListener.onNetAliveConnected();
            }
        }
    }

    public void registerNetStateReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(NetworkConnectChangedReceiver.NET_CONNECTED);
        filter.addAction(NetworkConnectChangedReceiver.NET_DISCONNECTED);
        filter.addAction(NetworkConnectChangedReceiver.NET_CONNECTING);
        filter.addAction(NetworkConnectChangedReceiver.NET_ALIVE_CONNECTED);
        try {
            context.registerReceiver(this, filter);
        } catch (Exception e) {
            LogMgr.e(TAG, "-->>this receiver is alreeady register ");
        }
    }

    public void registerNetReceiverAndCheck(Context context) {
        registerNetStateReceiver(context);
        if (NetUtil.isNetworkConnected(context)) {
            RuntimeConfig.setConnectedWifi(true);
            if (this.stateListener != null) {
                this.stateListener.onNetConnected();
            }
        } else if (this.stateListener != null) {
            this.stateListener.onNetDisconnected();
        }
    }

    public void unregisterNetStateReceiver(Context context) {
        try {
            context.unregisterReceiver(this);
        } catch (Exception e) {
            LogMgr.e(TAG, "-->>this receiver is not register or all ready unregister");
        }
    }
}

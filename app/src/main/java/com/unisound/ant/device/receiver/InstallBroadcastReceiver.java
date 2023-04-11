package com.unisound.ant.device.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.unisound.vui.util.LogMgr;

public class InstallBroadcastReceiver extends BroadcastReceiver {
    private static final String InstallFailAction = "com.unisound.pandora.installfailed";
    private static final String TAG = "InstallBroadcastReceiver";
    private InstallStateListener stateListener;

    public interface InstallStateListener {
        void onInstallFailed();
    }

    public void setStateListener(InstallStateListener listener) {
        this.stateListener = listener;
    }

    public void onReceive(Context context, Intent intent) {
        if (InstallFailAction.equals(intent.getAction())) {
            this.stateListener.onInstallFailed();
        }
    }

    public void registerInstallStateReceiver(Context context) {
        try {
            context.registerReceiver(this, new IntentFilter(InstallFailAction));
        } catch (Exception e) {
            LogMgr.e(TAG, "-->>this receiver is alreeady register ");
        }
    }

    public void unregisterInstallStateReceiver(Context context) {
        try {
            context.unregisterReceiver(this);
        } catch (Exception e) {
            LogMgr.e(TAG, "-->>this receiver is not register or all ready unregister");
        }
    }
}

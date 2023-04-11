package com.unisound.ant.device.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.unisound.vui.common.location.client.LocationClient;
import com.unisound.vui.common.location.client.LocationClientImpl;
import com.unisound.vui.common.location.listener.LocationListener;
import com.unisound.vui.common.location.strategy.baidu.BaiduLocationStrategy;
import com.unisound.vui.common.network.NetUtil;

public class AutoLocationReceiver extends BroadcastReceiver {
    private static final String NET_CHANGE_FILTER = "android.net.conn.CONNECTIVITY_CHANGE";
    private LocationClient locationClient;
    private Context mContext;

    public AutoLocationReceiver(Context context, LocationListener locationListener) {
        this.mContext = context;
        initLocationManager(context, locationListener);
        registerBroadcast(context);
    }

    private void initLocationManager(Context context, LocationListener locationListener) {
        this.locationClient = new LocationClientImpl(new BaiduLocationStrategy(context));
        this.locationClient.registerLocationLinstener(locationListener);
    }

    private void registerBroadcast(Context context) {
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this, iFilter);
    }

    public void startLocation() {
        this.locationClient.startLocation();
    }

    private void stopLocation() {
        this.locationClient.stopLocation();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            triggeredLocation(context);
        }
    }

    private void triggeredLocation(Context context) {
        if (NetUtil.isNetworkConnected(context)) {
            startLocation();
        } else {
            stopLocation();
        }
    }

    public void onDestory() {
        this.locationClient.onDestory();
        this.mContext.unregisterReceiver(this);
    }
}

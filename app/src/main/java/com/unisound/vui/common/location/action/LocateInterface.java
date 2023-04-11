package com.unisound.vui.common.location.action;

import com.unisound.vui.common.location.listener.LocationListener;

public interface LocateInterface {
    void onDestory();

    void registerLocationLinstener(LocationListener locationListener);

    void startLocation();

    void stopLocation();

    void unRegisterAllLocationListener();

    void unRegisterLocationListener(LocationListener locationListener);
}

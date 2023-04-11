package com.unisound.vui.common.location.client;

import com.unisound.vui.common.location.listener.LocationListener;
import com.unisound.vui.common.location.strategy.LocationStrategy;

public class LocationClientImpl implements LocationClient {
    private LocationStrategy mLocationStrategy;

    public LocationClientImpl(LocationStrategy locationStrategy) {
        if (locationStrategy == null) {
            throw new NullPointerException("locationStrategy is null !!");
        }
        this.mLocationStrategy = locationStrategy;
    }

    @Override // com.unisound.vui.common.location.action.LocateInterface
    public void onDestory() {
        this.mLocationStrategy.onDestory();
    }

    @Override // com.unisound.vui.common.location.action.LocateInterface
    public void registerLocationLinstener(LocationListener locationListener) {
        if (locationListener == null) {
            throw new NullPointerException("locationStrategy is null !!");
        }
        this.mLocationStrategy.registerLocationLinstener(locationListener);
    }

    @Override // com.unisound.vui.common.location.action.LocateInterface
    public void startLocation() {
        this.mLocationStrategy.startLocation();
    }

    @Override // com.unisound.vui.common.location.action.LocateInterface
    public void stopLocation() {
        this.mLocationStrategy.stopLocation();
    }

    @Override // com.unisound.vui.common.location.action.LocateInterface
    public void unRegisterAllLocationListener() {
        this.mLocationStrategy.unRegisterAllLocationListener();
    }

    @Override // com.unisound.vui.common.location.action.LocateInterface
    public void unRegisterLocationListener(LocationListener locationListener) {
        if (locationListener == null) {
            throw new NullPointerException("locationStrategy is null !!");
        }
        this.mLocationStrategy.unRegisterLocationListener(locationListener);
    }
}

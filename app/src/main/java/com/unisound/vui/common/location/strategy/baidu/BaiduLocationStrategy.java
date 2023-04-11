package com.unisound.vui.common.location.strategy.baidu;

import android.content.Context;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.unisound.vui.common.location.strategy.LocationStrategy;

public class BaiduLocationStrategy extends LocationStrategy {
    private LocationClient baiduLocationClient;
    private BDLocationListener bdAbstractLocationListener = new BDLocationListener() {
        /* class com.unisound.vui.common.location.strategy.baidu.BaiduLocationStrategy.AnonymousClass1 */

        @Override // com.baidu.location.b
        public void onReceiveLocation(BDLocation bdLocation) {
            if (BaiduLocationStrategy.this.isLocateSuccess(bdLocation)) {
                BaiduLocationStrategy.this.onLocationSuccess(BaiduLocationInfoParser.parser(bdLocation));
            } else {
                BaiduLocationStrategy.this.onLocationFail(BaiduLocationStrategy.this.getLocationFailInfo(bdLocation));
            }
        }
    };

    public BaiduLocationStrategy(Context context) {
        this.baiduLocationClient = new LocationClient(context, getDefaultOptions());
        this.baiduLocationClient.registerLocationListener (this.bdAbstractLocationListener);
    }



    private LocationClientOption getDefaultOptions() {
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.isOnceLocation = false;
        locationClientOption.setOpenGps(false);
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        locationClientOption.setIsNeedLocationDescribe(true);
        locationClientOption.setIsNeedAddress(true);
        locationClientOption.setIsNeedLocationPoiList(true);
        locationClientOption.setIsNeedAltitude(true);
//        hVar.c = false;
//        hVar.g=2;
//        hVar.a(true);
//        hVar.b(false);
        locationClientOption.setScanSpan(1000);
        return locationClientOption;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getLocationFailInfo(BDLocation bdLocation) {
        return bdLocation == null ? "bdLocation is null !!" : bdLocation.getLocationDescribe();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isLocateSuccess(BDLocation bdLocation) {
        return bdLocation != null && bdLocation.getLocType()  == 161;
    }

    @Override // com.unisound.vui.common.location.strategy.LocationStrategy, com.unisound.vui.common.location.action.LocateInterface
    public void startLocation() {
        if (!this.isStartLocation) {
            this.baiduLocationClient.start();
        }
        super.startLocation();
    }

    @Override // com.unisound.vui.common.location.strategy.LocationStrategy, com.unisound.vui.common.location.action.LocateInterface
    public void stopLocation() {
        if (this.isStartLocation) {
            this.baiduLocationClient.stop();
        }
        super.stopLocation();
    }
}

package com.unisound.vui.common.location.strategy.baidu;

import com.baidu.location.BDLocation;
import com.unisound.vui.common.location.bean.LocationInfo;

class BaiduLocationInfoParser {
    BaiduLocationInfoParser() {
    }

    static LocationInfo parser(BDLocation bdLocation) {
        LocationInfo locationInfo = new LocationInfo();
        transportToUniLocationInfo(locationInfo, bdLocation);
        return locationInfo;
    }

    private static void transportToUniLocationInfo(LocationInfo uniLocatonInfo, BDLocation bdLocation) {
        uniLocatonInfo.setType(bdLocation.getLocType());
        uniLocatonInfo.setName(bdLocation.getBuildingName());
        uniLocatonInfo.setCountry(bdLocation.getCountry());
        uniLocatonInfo.setProvince(bdLocation.getProvince());
        uniLocatonInfo.setCity(bdLocation.getCity());
        uniLocatonInfo.setCityCode(bdLocation.getCityCode());
        uniLocatonInfo.setDistrict(bdLocation.getDistrict());
        uniLocatonInfo.setAddress(bdLocation.getAddrStr());
        uniLocatonInfo.setLatitude(bdLocation.getLatitude());
        uniLocatonInfo.setLongitude(bdLocation.getLongitude());
    }
}

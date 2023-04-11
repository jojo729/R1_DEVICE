package com.unisound.vui.common.location.listener;

import com.unisound.vui.common.location.bean.LocationInfo;

public interface LocationListener {
    void onLocationFail(String str);

    void onLocationSuccess(LocationInfo locationInfo);
}

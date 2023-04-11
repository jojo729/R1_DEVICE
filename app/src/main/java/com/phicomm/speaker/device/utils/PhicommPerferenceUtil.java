package com.phicomm.speaker.device.utils;

import android.content.Context;
import com.unisound.vui.util.SharedPreferencesHelper;

public class PhicommPerferenceUtil {
    private static final String DEVICE_AMBIENT_LIGHT_STATE = "ambientLightStatus";

    public static boolean getAmbientLightState(Context context) {
        return SharedPreferencesHelper.getInstance(context, "unicar_user_settings").getBooleanValue(DEVICE_AMBIENT_LIGHT_STATE, false);
    }

    public static void setAmbientLightState(Context context, boolean isAmbientLightOn) {
        SharedPreferencesHelper.getInstance(context, "unicar_user_settings").saveBooleanValue(DEVICE_AMBIENT_LIGHT_STATE, isAmbientLightOn);
    }
}

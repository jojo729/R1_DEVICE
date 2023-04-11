package com.unisound.vui.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class NaviConfig {
    public static final String AMAPCARAUTOPACKAGENAME = "com.autonavi.amapauto";
    public static final String BAIDUNAVIPACKAGENAME = "com.baidu.navi";
    public static final String FMTYPE = "FmType";
    public static final String ISUSERDEALMUSICOPRS = "isUserDealMusciOprs";
    public static final String MAPOPRSARRAYID = "MapOprsArrayId";
    public static final String MAPPACKAGENAME = "MapAppPackageName";
    public static final String MAPTYPE = "MapType";
    public static final String MUSICTYPE = "MusicType";
    public static final String NAVIBUILD = "NaviBuild";
    public static Map<Object, Object> configs = new HashMap();
    public static Context context;
    public static Map<String, String> mapAndPackageName = new HashMap();
    public static Map<String, String> mapOprsToMethod = new HashMap();
    public static Map<String, String> mapOprsToUrl = new HashMap();
    public static List<String> naviSuportWakeUpWords = new ArrayList();

    static {
        mapAndPackageName.put(UserPerferenceUtil.SP_NAVI_AMAP, AMAPCARAUTOPACKAGENAME);
        mapAndPackageName.put(UserPerferenceUtil.SP_NAVI_BAMAP, BAIDUNAVIPACKAGENAME);
    }

    private NaviConfig() {
    }

    public static void clearAfterExitNavi() {
        naviSuportWakeUpWords.clear();
        mapOprsToUrl.clear();
        mapOprsToMethod.clear();
    }

    public static String getAmapCarpatuoPacname(String key) {
        return mapAndPackageName.get(key);
    }

    public static Context getContext() {
        return context;
    }

    public static Object getNaviConfig(String key) {
        return configs.get(key);
    }

    public static void initMapOprsAndOprWpWords() {
        String[] stringArray;
        int intValue = ((Integer) getNaviConfig(MAPOPRSARRAYID)).intValue();
        if (!(context == null || (stringArray = context.getResources().getStringArray(intValue)) == null || stringArray.length <= 0)) {
            for (String str : stringArray) {
                if (str.contains(MqttTopic.MULTI_LEVEL_WILDCARD)) {
                    String[] split = str.split(MqttTopic.MULTI_LEVEL_WILDCARD);
                    if (split[0] != null && split.length > 0) {
                        if (split[0].contains(",")) {
                            String[] split2 = split[0].split(",");
                            for (int i = 0; i < split2.length; i++) {
                                if (split2[i] != null) {
                                    naviSuportWakeUpWords.add(split2[i].replaceAll("\\s*", ""));
                                    if (split[1] != null) {
                                        mapOprsToMethod.put(split2[i], split[1].replaceAll("\\s*", ""));
                                    }
                                    if (split.length > 2) {
                                        mapOprsToUrl.put(split2[i], split[2].replaceAll("\\s*", ""));
                                    }
                                }
                            }
                        } else {
                            naviSuportWakeUpWords.add(split[0].replaceAll("\\s*", ""));
                            if (split.length > 1) {
                                mapOprsToMethod.put(split[0], split[1].replaceAll("\\s*", ""));
                            }
                            if (split.length > 2) {
                                mapOprsToUrl.put(split[0], split[2].replaceAll("\\s*", ""));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void setContext(Context context2) {
        context = context2;
    }

    public static void setDefaultMap(String defaultMapType, int resId) {
        setNaviConfig(MAPTYPE, defaultMapType);
        setNaviConfig(MAPPACKAGENAME, AMAPCARAUTOPACKAGENAME);
        setNaviConfig(MAPOPRSARRAYID, Integer.valueOf(resId));
    }

    public static void setNaviConfig(String key, Object value) {
        configs.put(key, value);
    }

    public static void setUserMap(Object naviBuild, String mapAppName, int resId) {
        setNaviConfig(NAVIBUILD, naviBuild);
        setNaviConfig(MAPPACKAGENAME, mapAppName);
        LogMgr.d("MUSICANDFM", "----mapAppName---" + mapAppName);
        setNaviConfig(MAPOPRSARRAYID, Integer.valueOf(resId));
    }
}

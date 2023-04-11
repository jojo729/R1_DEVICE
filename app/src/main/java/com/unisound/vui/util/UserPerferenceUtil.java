package com.unisound.vui.util;

import android.content.Context;
import android.text.TextUtils;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.udid.DeviceIdProcessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class UserPerferenceUtil {
    private static final String APP_VERSION_NAME = "app_version_name";
    public static final String CAR_THEFT_ENANLE = "car_theft_enable";
    public static final String CAR_THEFT_UPLOAD = "car_theft_upload";
    private static final String CUR_VERSION = "cur_version";
    private static final String DEVICE_BIND_CLIENTID = "clientID";
    private static final String DEVICE_BIND_STATE = "isBinded";
    private static final String DEVICE_DORMANT_LIGHT_STATE = "dormantLightStatus";
    private static final String DEVICE_NICK_NAME = "nickName";
    private static final String DEVICE_ONLINE_STATE = "isOnline";
    private static final String DEVICE_TEMP_EFFECTIVE_WAKEUP = "effectiveWakeupword";
    private static final String ERROR_TTS_PLAY = "error_tts_play";
    public static final String FILE_TAG = "\\[fileName\\](.*)\\[fileName\\]";
    private static final String FIRST_USED_DEVICE = "firstUsedDevice";
    private static final String IS_CONENCT_OUT_NET = "isOUtNet";
    private static final String IS_VOICE_CONNECTING = "is_voice_connecting";
    public static final String KEY_ACTIVATE_DATE = "key_activate_date";
    public static final String PCM_TAG = "[PCM]";
    public static final String SP_AEC_ENABLE = "sp_aec_enable";
    private static final String SP_ANTI_THEFT_SWITCH = "sp_anti_theft_switch";
    private static final String SP_ANT_LAUNCH_FIRST = "sp_ant_launch_first";
    public static final String SP_APP_FIRST_LAUNCH = "sp_app_first_launch";
    private static final String SP_APP_FORWARD = "app_forward";
    public static final int SP_CHILD_TTS = 0;
    private static final String SP_CONNECTED_WIFI = "connected_wifi";
    private static final String SP_CURRENT_FRAGMENT = "sp_current_fragement";
    private static final String SP_CURRENT_VOLUME = "current_wolume";
    private static final String SP_DEVICE_FIRST_WAKEUP = "firstWakeupPerDay";
    private static String SP_DEVICE_ID = DeviceIdProcessor.SP_DEVICE_ID;
    private static String SP_DEVICE_RID = "deviceRId";
    private static final String SP_DOWNLOAD_FILE_PATH = "sp_download_file_path";
    private static final String SP_ENABLE_ONESHOT = "enable_oneshot";
    private static final String SP_ENABLE_ONESHOT_V1 = "enable_oneshot_v1";
    private static final String SP_ENABLE_RECOGNIZE_PERSONAL = "enable_recognize_personal";
    private static final String SP_ENTER_ASR_TTS_END = "sp_enter_asr_tts_end";
    private static final String SP_MUSIC_POPUP_PARAM = "music_popup_param";
    private static final String SP_NAME = "unicar_user_settings";
    public static final String SP_NAVI_AMAP = "amap_map";
    public static final String SP_NAVI_BAMAP = "baidu_map";
    private static final String SP_NAVI_SELECTED_STATE = "sp_navi_selected_state";
    private static final String SP_NEED_INSTALL_UPDATE = "sp_need_install_update";
    private static final String SP_PASSPORT_MESSAGE = "sp_passport_message";
    private static final String SP_SDK_TOKEN = "sdk_token";
    private static final String SP_SDK_TOKEN_UPDATE_TIME = "sdk_token_update_time";
    private static final String SP_SDK_TOKEN_VALID_TIME = "sdk_token_valid_time";
    public static final int SP_SEX_TTS = 2;
    public static final int SP_STANDAR_TTS = 1;
    private static final String SP_SUBSCRIBE_EFFECTIVE = "sp_subscribe_effective";
    private static final String SP_SUBSCRIBE_WELCOME_IMGURL = "sp_subscribe_welcome_imgurl";
    private static final String SP_SUBSCRIBE_WELCOME_MUSICURL = "sp_subscribe_welcome_musicurl";
    public static final int SP_SWEET_TTS = 3;
    private static final String SP_TRAFFIC_INFO_SWITCH = "sp_traffic_info_switch";
    public static final String SP_TRAFFIC_INFO_SWITCH_A_GU = "sp_traffic_info_switch_a_gu";
    private static final String SP_TTS_SELECTED_STATE = "sp_tts_selected_state";
    private static final String SP_USER_LOCATION_INFO = "lcoation_info";
    private static final String SP_USER_TTS_MODEL_TYPE = "ttsModelType";
    private static final String SP_USE_DEFAULT_HOME_POI_ADDRESS = "default_home_poi_address";
    private static final String SP_USE_DEFAULT_OFFICE_POI_ADDRESS = "default_office_poi_address";
    private static final String SP_USE_DEFAULT_TTS_MODEL = "default_tts_model";
    private static final String SP_WAKEUP_WORD = "main_wakeup_word";
    public static String START_WAKEUP_AFTER_SET_WAKEUP_WORD = "start_wakeup_after_set_wakeup_word";
    private static final String TAG = "UserPerferenceUtil";
    public static final int TTS_CHILD = 1;
    public static final String TTS_CHILD_SUFFIX = "_child.pcm";
    private static final String TTS_PLAYING = "tts_playing";
    public static final int TTS_STANDARD = 2;
    public static final String TTS_STANDARD_SUFFIX = "_standard.pcm";
    private static int TTS_SUFFIX = 0;
    public static final int TTS_SWEET = 0;
    public static final String TTS_SWEET_SUFFIX = "_sweet.pcm";
    public static final int TTS_TAIWAN = 3;
    public static final String TTS_TAIWAN_SUFFIX = "_taiwan.pcm";
    public static final String TXT_TAG = "\\[txt\\](.*)\\[txt\\]";
    public static final String VALUE_ACTIVATE_DATE = "";
    private static String WAKEUP_RECORD_ID = "wakeupSuccessId";
    private static CurrentActiveMusicHandler currentActiveMusicHandler = CurrentActiveMusicHandler.CarMusic;
    public static boolean isNeedAddMusicWakeUp = false;

    private UserPerferenceUtil() {
    }

    public static String getActivateDate(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(KEY_ACTIVATE_DATE, "");
    }

    public static List<String> getActuallyMainWakeupWord(Context context) {
        List<String> mainWakeupWord = getMainWakeupWord(context);
        mainWakeupWord.removeAll(getCmopetitionWord(context));
        return mainWakeupWord;
    }

    public static boolean getAecEnable(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_AEC_ENABLE, false);
    }

    public static boolean getAntiTheftSwitchState(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_ANTI_THEFT_SWITCH, true);
    }

    public static boolean getAppFirstLaunch(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_APP_FIRST_LAUNCH, true);
    }

    public static boolean getAppForward(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_APP_FORWARD, false);
    }

    public static String getBindClientID(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(DEVICE_BIND_CLIENTID, null);
    }

    public static List<String> getCmopetitionWord(Context context) {
        ArrayList arrayList = new ArrayList();
        String[] stringArray = context.getResources().getStringArray(R.array.competition_words);
        for (int i = 0; i < stringArray.length; i++) {
            if (!TextUtils.isEmpty(stringArray[i])) {
                arrayList.add(stringArray[i]);
            }
        }
        return arrayList;
    }

    public static boolean getConnectedWifi(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_CONNECTED_WIFI, true);
    }

    public static String getCurVersion(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(CUR_VERSION, null);
    }

    public static CurrentActiveMusicHandler getCurrentActiveMusicHandler() {
        return currentActiveMusicHandler;
    }

    public static int getCurrentFragment(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getIntValue(SP_CURRENT_FRAGMENT, 0);
    }

    public static int getCurrentVolume(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getIntValue(SP_CURRENT_VOLUME, 6);
    }

    public static String getData(Context context, String key) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(key, null);
    }

    public static boolean getData(Context context, String key, boolean defaultValue) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(key, defaultValue);
    }

    public static List<String> getDefaultWakeupWord(Context context) {
        ArrayList arrayList = new ArrayList();
        String[] stringArray = context.getResources().getStringArray(R.array.default_wakeup_words);
        for (int i = 0; i < stringArray.length; i++) {
            if (!TextUtils.isEmpty(stringArray[i])) {
                arrayList.add(stringArray[i]);
            }
        }
        return arrayList;
    }

    public static boolean getDeviceBindState(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(DEVICE_BIND_STATE, false);
//        return true;
    }

    public static String getDeviceId(Context context) {
        return SharedPreferencesHelper.getInstance(context).getStringValue(SP_DEVICE_ID, null);
    }

    public static String getDeviceRId(Context context) {
        return SharedPreferencesHelper.getInstance(context).getStringValue(SP_DEVICE_RID, null);
    }

    public static String getDeviceNick(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(DEVICE_NICK_NAME, "");
    }

    public static boolean getDeviceOnlineState(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(DEVICE_ONLINE_STATE, false);
    }

    public static boolean getDormantLightState(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(DEVICE_DORMANT_LIGHT_STATE, false);
    }

    public static List<String> getEffectWakeupword(Context context) {
        Set<String> stringSetValue = SharedPreferencesHelper.getInstance(context).getStringSetValue(DEVICE_TEMP_EFFECTIVE_WAKEUP);
        ArrayList arrayList = new ArrayList();
        for (String str : stringSetValue) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public static boolean getEnableRecognizePersonal(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_ENABLE_RECOGNIZE_PERSONAL, false);
    }

    public static String getFileNamePersonal(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(SP_DOWNLOAD_FILE_PATH, "");
    }

    public static List<String> getGlobalCancelWakeupWord(Context context) {
        return Arrays.asList(context.getResources().getStringArray(R.array.tts_global_cancel_wake_up));
    }

    public static boolean getIsErrorTTSPlay(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(ERROR_TTS_PLAY, false);
    }

    public static boolean getIsVoiceConnect(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(IS_VOICE_CONNECTING, false);
    }

    public static String getLocationInfo(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(SP_USER_LOCATION_INFO, null);
    }

    public static List<String> getMainWakeupWord(Context context) {
        HashSet hashSet = new HashSet();
        Set<String> stringSetValue = SharedPreferencesHelper.getInstance(context, SP_NAME).getStringSetValue(SP_WAKEUP_WORD);
        if (stringSetValue != null && stringSetValue.size() > 0) {
            hashSet.addAll(stringSetValue);
        }
        hashSet.addAll(getDefaultWakeupWord(context));
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(hashSet);
        return arrayList;
    }

    public static ArrayList<String> getMusicOprlWakeupWord(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] stringArray = context.getResources().getStringArray(R.array.music_running_wakeup_words);
        if (stringArray != null && stringArray.length > 0) {
            for (String str : stringArray) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static String getMusicPopPara(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(SP_MUSIC_POPUP_PARAM, null);
    }

    public static String getNaviSelectedState(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(SP_NAVI_SELECTED_STATE, SP_NAVI_AMAP);
    }

    private static List<String> getNavigationWakeupWord() {
        ArrayList arrayList = new ArrayList();
        if (NaviConfig.naviSuportWakeUpWords.size() <= 0) {
            NaviConfig.initMapOprsAndOprWpWords();
        }
        for (String str : NaviConfig.naviSuportWakeUpWords) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public static boolean getNeedInstallUpdate(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_NEED_INSTALL_UPDATE, false);
    }

    public static List<String> getOneShotWakeupWord(Context context) {
        HashSet hashSet = new HashSet();
        if (getOneshotEnableV1(context)) {
            hashSet.addAll(getMainWakeupWord(context));
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(hashSet);
        return arrayList;
    }

    public static boolean getOneshotEnable(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_ENABLE_ONESHOT, false);
    }

    public static boolean getOneshotEnableV1(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_ENABLE_ONESHOT_V1, false);
    }

    public static String getPassportMessage(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(SP_PASSPORT_MESSAGE, null);
    }

    public static String getPcmFileSuffix() {
        switch (getTTSSelectedState()) {
            case 0:
                return TTS_CHILD_SUFFIX;
            case 1:
                return TTS_STANDARD_SUFFIX;
            case 2:
                return TTS_TAIWAN_SUFFIX;
            case 3:
                return TTS_SWEET_SUFFIX;
            default:
                return "";
        }
    }

    public static String getPoiDefaultHomeInfo(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(SP_USE_DEFAULT_HOME_POI_ADDRESS, "");
    }

    public static String getPoiDefaultOfficeInfo(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(SP_USE_DEFAULT_OFFICE_POI_ADDRESS, "");
    }

    public static ArrayList<String> getSceneModeControlWakeupWord(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] stringArray = context.getResources().getStringArray(R.array.scenemode_wakeup_word);
        if (stringArray != null && stringArray.length > 0) {
            for (String str : stringArray) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static String getSdkToken(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(SP_SDK_TOKEN, "");
    }

    public static long getSdkTokenUpdateTime(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getLongValue(SP_SDK_TOKEN_UPDATE_TIME);
    }

    public static int getSdkTokenValidTime(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getIntValue(SP_SDK_TOKEN_VALID_TIME, 0);
    }

    public static String getSubscribeWelcomeImgUrl(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(SP_SUBSCRIBE_WELCOME_IMGURL, null);
    }

    public static String getSubscribeWelcomeMusicUrl(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(SP_SUBSCRIBE_WELCOME_MUSICURL, null);
    }

    public static boolean getSubscribedEffective(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_SUBSCRIBE_EFFECTIVE, false);
    }

    public static int getTTSSelectedState() {
        return TTS_SUFFIX;
    }

    public static boolean getTrafficInfoAGuSwitchState(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_TRAFFIC_INFO_SWITCH_A_GU, true);
    }

    public static boolean getTrafficInfoSwitchState(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_TRAFFIC_INFO_SWITCH, true);
    }

    public static boolean getUseDefaultTTSModel(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(SP_USE_DEFAULT_TTS_MODEL, true);
    }

    public static String getUserTTSModelType(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getStringValue(SP_USER_TTS_MODEL_TYPE, "SWEET");
    }

    public static String getValidSdk(Context context) {
        if (TextUtils.isEmpty(getSdkToken(context))) {
            return null;
        }
        String sdkToken = getSdkToken(context);
        long sdkTokenUpdateTime = getSdkTokenUpdateTime(context);
        long sdkTokenValidTime = ((long) ((getSdkTokenValidTime(context) * 1000) / 2)) + sdkTokenUpdateTime;
        long currentTimeMillis = System.currentTimeMillis();
        LogMgr.d("SdkVeri", "now:" + currentTimeMillis + "validTime:" + sdkTokenValidTime + "upDateTime:" + sdkTokenUpdateTime);
        if (sdkTokenValidTime > currentTimeMillis) {
            return sdkToken;
        }
        return null;
    }

    public static List<String> getWakeupWord(Context context) {
        return getWakeupWord(context, true);
    }

    public static List<String> getWakeupWord(Context context, boolean isSupportInterval) {
        return getWakeupWord(context, true, isSupportInterval);
    }

    private static List<String> getWakeupWord(Context context, boolean isSupportGloable, boolean isSupportInterval) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getMainWakeupWord(context));
        if (isSupportGloable) {
        }
        LogMgr.d(TAG, "-->>getWakeupWord wakeupWord:" + arrayList.toArray().toString());
        return arrayList;
    }

    public static boolean isFirstWakeup(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue("firstWakeupPerDay", true);
    }

    public static boolean isOutNet(Context context) {
        return SharedPreferencesHelper.getInstance(context, SP_NAME).getBooleanValue(IS_CONENCT_OUT_NET, true);
    }

    public static void putData(Context context, String key, Boolean value) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(key, value.booleanValue());
    }

    public static void putData(Context context, String key, String value) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(key, value);
    }

    public static void setActivateDate(Context context, String activateDate) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(KEY_ACTIVATE_DATE, activateDate);
    }

    public static void setAecEnable(Context context, boolean enable) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_AEC_ENABLE, enable);
    }

    public static void setAntiTheftSwitchState(Context context, boolean onOrOff) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_ANTI_THEFT_SWITCH, onOrOff);
    }

    public static void setAppFirstLaunch(Context context, boolean enable) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_APP_FIRST_LAUNCH, enable);
    }

    public static void setAppForward(Context context, boolean forward) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_APP_FORWARD, forward);
    }

    public static void setBindClientID(Context context, String clientID) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(DEVICE_BIND_CLIENTID, clientID);
    }

    public static void setConnectedWifi(Context context, boolean configured) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_CONNECTED_WIFI, configured);
    }

    public static void setCurVersion(Context context, String curVersion) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(CUR_VERSION, curVersion);
    }

    public static void setCurrentActiveMusicHandler(CurrentActiveMusicHandler currentActiveMusicHandler2) {
        currentActiveMusicHandler = currentActiveMusicHandler2;
    }

    public static void setCurrentFragment(Context context, int index) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveIntValue(SP_CURRENT_FRAGMENT, index);
    }

    public static void setCurrentVolume(Context context, int mIndex) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveIntValue(SP_CURRENT_VOLUME, mIndex);
    }

    public static void setDeviceBindState(Context context, boolean isBindState) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(DEVICE_BIND_STATE, isBindState);
    }

    public static void setDeviceId(Context context, String deviceId) {
        SharedPreferencesHelper.getInstance(context).saveStringValue(SP_DEVICE_ID, deviceId);
    }

    public static void setDeviceRId(Context context, String deviceId) {
        SharedPreferencesHelper.getInstance(context).saveStringValue(SP_DEVICE_RID, deviceId);
    }

    public static void setDeviceNick(Context context, String nickName) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(DEVICE_NICK_NAME, nickName);
    }

    public static void setDeviceOnlineState(Context context, boolean isOnlineState) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(DEVICE_ONLINE_STATE, isOnlineState);
    }

    public static void setDormantLightState(Context context, boolean isLighting) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(DEVICE_DORMANT_LIGHT_STATE, isLighting);
    }

    public static void setEffectiveWakeupword(List<String> wakeupwords, Context context) {
        HashSet hashSet = new HashSet();
        for (String str : wakeupwords) {
            hashSet.add(str);
        }
        SharedPreferencesHelper.getInstance(context).saveStringSetValue(DEVICE_TEMP_EFFECTIVE_WAKEUP, hashSet);
    }

    public static void setEnableRecognizePersonal(Context context, boolean use) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_ENABLE_RECOGNIZE_PERSONAL, use);
    }

    public static void setFileNamePersonal(Context context, String fileName) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(SP_DOWNLOAD_FILE_PATH, fileName);
    }

    public static void setFirstWakeup(Context context, boolean isFirst) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue("firstWakeupPerDay", isFirst);
    }

    public static void setIsErrorTTSPlay(Context context, boolean onOrOff) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(ERROR_TTS_PLAY, onOrOff);
    }

    public static void setIsVoiceConnect(Context context, boolean isVoiceConnect) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(IS_VOICE_CONNECTING, isVoiceConnect);
    }

    public static void setLocationInfo(Context context, String info) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(SP_USER_LOCATION_INFO, info);
    }

    public static void setMusicPopPara(Context context, String para) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(SP_MUSIC_POPUP_PARAM, para);
    }

    public static void setNaviSelectedState(Context context, String mapName) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(SP_NAVI_SELECTED_STATE, mapName);
    }

    public static void setNeedInstallUpdate(Context context, boolean isNeed) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_NEED_INSTALL_UPDATE, isNeed);
    }

    public static void setOneshotEnable(Context context, boolean enable) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_ENABLE_ONESHOT, enable);
    }

    public static void setOneshotEnableV1(Context context, boolean enable) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_ENABLE_ONESHOT_V1, enable);
    }

    public static void setOutNet(Context context, boolean isOutNet) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(IS_CONENCT_OUT_NET, isOutNet);
    }

    public static void setPassportMessage(Context context, String jsonStr) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(SP_PASSPORT_MESSAGE, jsonStr);
    }

    public static void setPoiDefaultHomeInfo(Context context, String info) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(SP_USE_DEFAULT_HOME_POI_ADDRESS, info);
    }

    public static void setPoiDefaultOfficeInfo(Context context, String info) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(SP_USE_DEFAULT_OFFICE_POI_ADDRESS, info);
    }

    public static void setSdkToken(Context context, String token) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(SP_SDK_TOKEN, token);
    }

    public static void setSpSdkTokenUpdateTime(Context context, long time) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveLongValue(SP_SDK_TOKEN_UPDATE_TIME, time);
    }

    public static void setSpSdkTokenValidTime(Context context, int valid) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveIntValue(SP_SDK_TOKEN_VALID_TIME, valid);
    }

    public static void setStartWakeupAfterSetWakeupWord(Context context, boolean startWakeup) {
        SharedPreferencesHelper.getInstance(context).saveBooleanValue(START_WAKEUP_AFTER_SET_WAKEUP_WORD, startWakeup);
    }

    public static void setSubscribeWelcomeImgUrl(Context context, String jsonStr) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(SP_SUBSCRIBE_WELCOME_IMGURL, jsonStr);
    }

    public static void setSubscribeWelcomeMusicUrl(Context context, String jsonStr) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(SP_SUBSCRIBE_WELCOME_MUSICURL, jsonStr);
    }

    public static void setSubscribedEffective(Context context, boolean effective) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_SUBSCRIBE_EFFECTIVE, effective);
    }

    public static void setTTSSelectedState(int ttsType) {
        switch (ttsType) {
            case 0:
            case 1:
            case 2:
            case 3:
                TTS_SUFFIX = ttsType;
                return;
            default:
                return;
        }
    }

    public static void setTrafficInfoAGuSwitchState(Context context, boolean onOrOff) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_TRAFFIC_INFO_SWITCH_A_GU, onOrOff);
    }

    public static void setTrafficInfoSwitchState(Context context, boolean onOrOff) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_TRAFFIC_INFO_SWITCH, onOrOff);
    }

    public static void setUseDefaultTTSModel(Context context, boolean use) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveBooleanValue(SP_USE_DEFAULT_TTS_MODEL, use);
    }

    public static void setUserTTSModelType(Context context, String type) {
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringValue(SP_USER_TTS_MODEL_TYPE, type);
    }

    public static void setWakeupWord(Context context, List<String> wakeupWord) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(wakeupWord);
        SharedPreferencesHelper.getInstance(context, SP_NAME).saveStringSetValue(SP_WAKEUP_WORD, hashSet);
    }

    public static boolean startWakeupAfterSetWakeupWord(Context context) {
        return SharedPreferencesHelper.getInstance(context).getBooleanValue(START_WAKEUP_AFTER_SET_WAKEUP_WORD, true);
    }
}

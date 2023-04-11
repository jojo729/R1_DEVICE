package com.unisound.vui.util;

public final class ExoConstants {
    // pro
    //    public static final String APP_KEY = "pohygvuxtkeojpmdrelijzjj5axlzwaldweolfaf";
//    public static final String APP_SECRET = "453d70af4992b8cd05eab1d873241d92";
//
//    public static final String MEDICAL_APP_KEY = "45gn7md5n44aak7a57rdjud3b5l4xdgv75saomys";
//    public static final String MEDICAL_APP_SECRET = "ba24a917a38e11e49c6fb82a72e0d896";


    public static final String APP_KEY = "rb35fqpoz33hyu56zh4kklgqqwdicqcmbxzwe4qc";
    public static final String APP_SECRET = "99900e2146fca6dff6d61d7a6c4664a3";
    public static final String DO_ACTIVE_INTERRUPT = "doActiveInterrupt";
    public static final String DO_ASR_INTERRUPT = "doAsrInterrupt";
    public static final String DO_ENGINE_INIT_DONE = "doEngineInitDone";
    public static final String DO_ENTER_ASR_BY_MIC = "doEnterAsrByMic";
    public static final String DO_FINISH_ALL_INTERRUPT = "doFinishAllInterrupt";
    public static final String DO_ONE_SHOT_INTERRUPT = "doOneShotInterrupt";
    public static final String DO_PASSIVE_INTERRUPT = "doPassiveInterrupt";
    public static final String DO_PTT_ACTION_BY_MIC = "doPttActionByMic";
    public static final String DO_RESUME = "doResume";
    public static final String FIRE_ACTIVE_INTERRUPT = "fireActiveInterrupt";
    public static final String FIRE_ASR_INTERRUPT = "fireAsrInterrupt";
    public static final String FIRE_ENGINE_INIT_DONE = "firEngineInitDone";
    public static final String FIRE_ONE_SHOT_INTERRUPT = "fireOneShotInterrupt";
    public static final String FIRE_PASSIVE_INTERRUPT = "firePassiveInterrupt";
    public static final String FIRE_RESUME = "fireResume";
    public static final String MUSIC_START = "musicStart";
    public static final String SWITCH_TTS_PLAYER = "switchTtsPlayer";
    public static boolean createHtml5 = false;

    public static final class a {
    }

    public static final class b {
    }

    private ExoConstants() {
    }

    public static String getServiceParam() {
        String validSdk = UserPerferenceUtil.getValidSdk(ContextUtils.getContext());
        String str = createHtml5 ? "{\"param\":{\"createH5URL\":\"true\",\"deviceToken\":\"" + validSdk + "\",\"activateVer\":\"v3.1\"}}" : "{\"param\":{\"deviceToken\":\"" + validSdk + "\",\"activateVer\":\"v3.1\"}}";
        LogMgr.d("SdkVeri", str);
        return str;
    }
}

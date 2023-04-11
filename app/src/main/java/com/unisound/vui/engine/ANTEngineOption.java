package com.unisound.vui.engine;

import com.unisound.vui.transport.MessageCodec;
import com.unisound.vui.util.a;
import com.unisound.vui.util.b;
import com.unisound.vui.util.c;

import java.util.List;

public final class ANTEngineOption<T> extends a<ANTEngineOption<T>> {


    private static final c<ANTEngineOption<Object>> POOL = new c<ANTEngineOption<Object>>() {
        /* class com.unisound.vui.engine.ANTEngineOption.AnonymousClass1 */


        public ANTEngineOption<Object> b(final int n, final String s) {
            return (ANTEngineOption<Object>)new ANTEngineOption(n, s, null);
        }

//        public  b b(final int n, final String s) {
//            return (b)this.a(n, s);
//        }


    };

    public static final ANTEngineOption<String> ASR_DOMAIN = valueOf("ASR_DOMAIN");
    public static final ANTEngineOption<String> ASR_ENGINE_INFO = valueOf("ASR_ENGINE_INFO");
    public static final ANTEngineOption<Boolean> ASR_FOURMIC = valueOf("ASR_FOURMIC");
    public static final ANTEngineOption<Integer> ASR_FOURMIC_BOARD_VERSION = valueOf("ASR_FOURMIC_BOARD_VERSION");
    public static final ANTEngineOption<Integer> ASR_FOURMIC_CHANGE_CHANNEL = valueOf("ASR_FOURMIC_CHANGE_CHANNEL");
    public static final ANTEngineOption<Boolean> ASR_FOURMIC_CLOSE_4MICALGORITHM = valueOf("ASR_FOURMIC_CLOSE_4MICALGORITHM");
    public static final ANTEngineOption<Integer> ASR_FOURMIC_DOA_RESULT = valueOf("ASR_FOURMIC_DOA_RESULT");
    public static final ANTEngineOption<Boolean> ASR_FOURMIC_ISDEBUG = valueOf("ASR_FOURMIC_ISDEBUG");
    public static final ANTEngineOption<String> ASR_LANGUAGE = valueOf("ASR_LANGUAGE");
    public static final ANTEngineOption<Integer> ASR_NET_TIMEOUT = valueOf("ASR_NET_TIMEOUT");
    public static final ANTEngineOption<Boolean> ASR_OPT_ALREAD_AWPE = valueOf("ASR_OPT_ALREAD_AWPE");
    public static final ANTEngineOption<Boolean> ASR_OPT_DEBUG_POSTLOG = valueOf("ASR_OPT_DEBUG_POSTLOG");
    public static final ANTEngineOption<Boolean> ASR_OPT_DEBUG_SAVELOG = valueOf("ASR_OPT_DEBUG_SAVELOG");
    public static final ANTEngineOption<Integer> ASR_OPT_FRONT_CACHE_TIME = valueOf("ASR_OPT_FRONT_CACHE_TIME");
    public static final ANTEngineOption<String> ASR_OPT_FRONT_RESET_CACHE_BYTE_TIME = valueOf("ASR_OPT_FRONT_RESET_CACHE_BYTE_TIME");
    @Deprecated
    public static final ANTEngineOption<Boolean> ASR_OPT_FRONT_VAD_ENABLED = valueOf("ASR_OPT_FRONT_VAD_ENABLED");
    public static final ANTEngineOption<List<Integer>> ASR_OPT_MODEL_LIST = valueOf("ASR_OPT_MODEL_LIST");
    public static final ANTEngineOption<Integer> ASR_OPT_ONESHOT_VADBACKSIL_TIME = valueOf("ASR_OPT_ONESHOT_VADBACKSIL_TIME");
    public static final ANTEngineOption<Boolean> ASR_OPT_PRINT_ENGINE_LOG = valueOf("ASR_OPT_PRINT_ENGINE_LOG");
    public static final ANTEngineOption<Boolean> ASR_OPT_PRINT_LOG = valueOf("ASR_OPT_PRINT_LOG");
    public static final ANTEngineOption<Boolean> ASR_OPT_PRINT_TIME_LOG = valueOf("ASR_OPT_PRINT_TIME_LOG");
    public static final ANTEngineOption<Boolean> ASR_OPT_RECOGNITION_FRONT_VAD = valueOf("ASR_OPT_RECOGNITION_FRONT_VAD");
    public static final ANTEngineOption<Integer> ASR_OPT_RECOGNIZE_MODEL_ID = valueOf("ASR_OPT_RECOGNIZE_MODEL_ID");
    public static final ANTEngineOption<Integer> ASR_OPT_RECOGNIZE_SCENE = valueOf("ASR_OPT_RECOGNIZE_SCENE");
    public static final ANTEngineOption<Boolean> ASR_OPT_RECORDING_ENABLED = valueOf("ASR_OPT_RECORDING_ENABLED");
    public static final ANTEngineOption<Boolean> ASR_OPT_RESULT_FILTER = valueOf("ASR_OPT_RESULT_FILTER");
    public static final ANTEngineOption<String> ASR_OPT_SAVE_AFTERVAD_RECORDING_DATA = valueOf("ASR_OPT_SAVE_AFTERVAD_RECORDING_DATA");
    public static final ANTEngineOption<String> ASR_OPT_SAVE_RECORDING_DATA = valueOf("ASR_OPT_SAVE_RECORDING_DATA");
    public static final ANTEngineOption<String> ASR_OPT_SUB_SERVICE_PARAM = valueOf("ASR_OPT_SUB_SERVICE_PARAM");
    public static final ANTEngineOption<Boolean> ASR_OPT_TEMP_RESULT_ENABLE = valueOf("ASR_OPT_TEMP_RESULT_ENABLE");
    public static final ANTEngineOption<String> ASR_OPT_TIMEOUT_STATUS = valueOf("ASR_OPT_TIMEOUT_STATUS");
    public static final ANTEngineOption<Boolean> ASR_OPT_VAD_ENABLED = valueOf("ASR_OPT_VAD_ENABLED");
    public static final ANTEngineOption<Integer> ASR_OPT_WAKEUP_MODEL_ID = valueOf("ASR_OPT_WAKEUP_MODEL_ID");
    public static final ANTEngineOption<Boolean> ASR_OPT_WX_SERVICE = valueOf("ASR_OPT_WX_SERVICE");
    public static final ANTEngineOption<Integer> ASR_SAMPLING_RATE = valueOf("ASR_SAMPLING_RATE");
    public static final ANTEngineOption<Integer> ASR_SERVICE_MODE = valueOf("ASR_SERVICE_MODE");
    public static final ANTEngineOption<String> ASR_SESSION_ID = valueOf("ASR_SESSION_ID");
    public static final ANTEngineOption<String> ASR_SUBDOMAIN = valueOf("ASR_SUBDOMAIN");
    public static final ANTEngineOption<Integer> ASR_VAD_TIMEOUT_BACKSIL = valueOf("ASR_VAD_TIMEOUT_BACKSIL");
    public static final ANTEngineOption<Integer> ASR_VAD_TIMEOUT_FRONTSIL = valueOf("ASR_VAD_TIMEOUT_FRONTSIL");
    public static final ANTEngineOption<Boolean> ASR_VOICE_FIELD = valueOf("ASR_VOICE_FIELD");
    public static final ANTEngineOption<String> GENERAL_CITY = valueOf("GENERAL_CITY");
    public static final ANTEngineOption<String> GENERAL_GPS = valueOf("GENERAL_GPS");
    public static final ANTEngineOption<String> GENERAL_HISTORY = valueOf("GENERAL_HISTORY");
    public static final ANTEngineOption<String> GENERAL_UDID = valueOf("GENERAL_UDID");
    public static final ANTEngineOption<Integer> GENERAL_UPDATE_VOLUME = valueOf("GENERAL_UPDATE_VOLUME");
    public static final ANTEngineOption<String> GENERAL_VOICEID = valueOf("GENERAL_VOICEID");
    public static final ANTEngineOption<String> MAIN_TAG = valueOf("MAIN_TAG");
    public static final ANTEngineOption<MessageCodec> MESSAGE_CODEC = valueOf("MESSAGE_CODEC");
    public static final ANTEngineOption<String> NLU_APPVER = valueOf("NLU_APPVER");
    public static final ANTEngineOption<Boolean> NLU_ENABLE = valueOf("NLU_ENABLE");
    public static final ANTEngineOption<String> NLU_SCENARIO = valueOf("NLU_SCENARIO");
    public static final ANTEngineOption<String> NLU_VER = valueOf("NLU_VER");
    public static final ANTEngineOption<Boolean> OPT_SET_FIX_RESULT_NLU = valueOf("OPT_SET_FIX_RESULT_NLU");
    public static final ANTEngineOption<String> OPT_SET_FIX_RESULT_NLU_CONFIGPATH = valueOf("OPT_SET_FIX_RESULT_NLU_CONFIGPATH");


    public static final ANTEngineOption<String> TTS_KEY_BACKEND_MODEL_PATH = valueOf("TTS_KEY_BACKEND_MODEL_PATH");
    public static final ANTEngineOption<Integer> TTS_KEY_BACK_SILENCE = valueOf("TTS_KEY_BACK_SILENCE");
    public static final ANTEngineOption<String> TTS_KEY_FRONTEND_MODEL_PATH = valueOf("TTS_KEY_FRONTEND_MODEL_PATH");
    public static final ANTEngineOption<Integer> TTS_KEY_FRONT_SILENCE = valueOf("TTS_KEY_FRONT_SILENCE");
    public static final ANTEngineOption<Boolean> TTS_KEY_IS_DEBUG = valueOf("TTS_KEY_IS_DEBUG");
    public static final ANTEngineOption<Boolean> TTS_KEY_IS_READ_ENLISH_IN_PINYIN = valueOf("TTS_KEY_IS_READ_ENLISH_IN_PINYIN");
    public static final ANTEngineOption<Boolean> TTS_KEY_IS_URGENT_AUDIO = valueOf("TTS_KEY_IS_URGENT_AUDIO");
    public static final ANTEngineOption<Integer> TTS_KEY_PLAY_START_BUFFER_TIME = valueOf("TTS_KEY_PLAY_START_BUFFER_TIME");
    public static final ANTEngineOption<Integer> TTS_KEY_SAMPLE_RATE = valueOf("TTS_KEY_SAMPLE_RATE");
    public static final ANTEngineOption<Integer> TTS_KEY_STREAM_TYPE = valueOf("TTS_KEY_STREAM_TYPE");
    public static final ANTEngineOption<String> TTS_KEY_SWITCH_BACKEND_MODEL_PATH = valueOf("TTS_KEY_SWITCH_BACKEND_MODEL_PATH");
    public static final ANTEngineOption<String> TTS_KEY_USER_DICT_FILE_PATH = valueOf("TTS_KEY_USER_DICT_FILE_PATH");
    public static final ANTEngineOption<String> TTS_KEY_VOICE_NAME = valueOf("TTS_KEY_VOICE_NAME");
    public static final ANTEngineOption<Integer> TTS_KEY_VOICE_PITCH = valueOf("TTS_KEY_VOICE_PITCH");
    public static final ANTEngineOption<Integer> TTS_KEY_VOICE_SPEED = valueOf("TTS_KEY_VOICE_SPEED");
    public static final ANTEngineOption<Integer> TTS_KEY_VOICE_VOLUME = valueOf("TTS_KEY_VOICE_VOLUME");
    public static final ANTEngineOption<Integer> TTS_SERVICE_MODE = valueOf("TTS_SERVICE_MODE");
    public static final ANTEngineOption<String> WAKEUP_ONESHOT_TAG = valueOf("WAKEUP_ONESHOT_TAG");
    public static final ANTEngineOption<Float> WAKEUP_OPT_THRESHOLD_VALUE = valueOf("WAKEUP_OPT_THRESHOLD_VALUE");
    public static final ANTEngineOption<String> WAKEUP_TAG = valueOf("WAKEUP_TAG");


    private ANTEngineOption(int i, String str) {
        super(i, str);
    }

    ANTEngineOption(final int n, final String s, final ANTEngineOption antEngineOption) {
        this(n, s);
    }






    public static boolean exists(String str) {
        return POOL.b(str);
    }

    public static <T> ANTEngineOption<T> newInstance(String str) {
        return (ANTEngineOption) POOL.c(str);
    }

    public static <T> ANTEngineOption<T> valueOf(Class<?> cls, String str) {
        return (ANTEngineOption) POOL.a(cls, str);
    }

    public static <T> ANTEngineOption<T> valueOf(String str) {
        return (ANTEngineOption) POOL.a(str);
    }

    public void validate(T t) {
    }
}

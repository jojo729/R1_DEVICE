package com.unisound.vui.engine;

import com.unisound.vui.bootstrap.ANTELocalConfiguration;
import com.unisound.vui.bootstrap.DefaultUserANTEOptionProvider;
import com.unisound.vui.transport.DefaultMessageCodec;
import com.unisound.vui.transport.MessageCodec;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.internal.ObjectUtil;

import java.util.List;
import java.util.Map;

public class b<T> implements ANTEngineConfig {
    private static final MessageCodec c = new DefaultMessageCodec();

    /* renamed from: a  reason: collision with root package name */
    protected final ANTEngine antEngine;
    protected final ANTEngine.Unsafe unsafe;
    private volatile MessageCodec d = c;
    private String e;
    private String f;
    private String g;
    private Map<ANTEngineOption<?>, Object> h;
    private ANTELocalConfiguration i;
    private Map<String, List<String>> j;
    private volatile boolean k = false;

    public b(ANTEngine aNTEngine) {
        super();
        ObjectUtil.checkNotNull(aNTEngine, "antEngine");
        this.antEngine = aNTEngine;
        this.unsafe = aNTEngine.unsafe();
    }

    private static <T> T a(T t, T t2, String str) {
        if (t2 == null) {
            throw new NullPointerException(str);
        } else if (t == null) {
            return t2;
        } else {
            throw new IllegalStateException(str + "set already");
        }
    }

    /* renamed from: a */
    public b setMainTag(String str) {
        this.e = str;
        this.unsafe.setMainTag(str);
        return this;
    }

    /* access modifiers changed from: protected */
    public <T> void a(ANTEngineOption<T> aNTEngineOption, T t) {
        if (aNTEngineOption != null) {
            aNTEngineOption.validate(t);
            return;
        }
        throw new NullPointerException("option");
    }

    /* renamed from: b */
    public b setWakeupTag(String str) {
        this.f = (String) a(this.f, str, "wakeupTag");
        return this;
    }

    /* renamed from: c */
    public b setWakeupOneshotTag(String str) {
        this.g = (String) a(this.g, str, "wakeupOneshotTag");
        return this;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public ANTELocalConfiguration getLocalConfiguration() {
        return this.i;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public String getMainTag() {
        return this.e;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public Map<String, List<String>> getMainVocab() {
        return this.j;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public MessageCodec getMessageCodec() {
        return this.d;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public <T> T getOption(ANTEngineOption<T> var1) {
        ObjectUtil.checkNotNull(var1, "option");
        Object var2;
        if (var1 == ANTEngineOption.MAIN_TAG) {
            var2 = this.getMainTag();
        } else if (var1 == ANTEngineOption.WAKEUP_TAG) {
            var2 = this.getWakeupTag();
        } else if (var1 == ANTEngineOption.WAKEUP_ONESHOT_TAG) {
            var2 = this.getWakeupOneshotTag();
        } else if (var1 == ANTEngineOption.MESSAGE_CODEC) {
            var2 = this.getMessageCodec();
        } else if (var1 == ANTEngineOption.GENERAL_UPDATE_VOLUME) {
            var2 = this.unsafe.getOption(1045);
        } else if (var1 == ANTEngineOption.ASR_OPT_TIMEOUT_STATUS) {
            var2 = this.unsafe.getOption(1078);
        } else if (var1 == ANTEngineOption.ASR_SAMPLING_RATE) {
            var2 = this.unsafe.getOption(1044);
        } else if (var1 == ANTEngineOption.ASR_DOMAIN) {
            var2 = this.unsafe.getOption(1008);
        } else if (var1 == ANTEngineOption.ASR_VOICE_FIELD) {
            var2 = this.unsafe.getOption(1003);
        } else if (var1 == ANTEngineOption.ASR_LANGUAGE) {
            var2 = this.unsafe.getOption(1004);
        } else if (var1 == ANTEngineOption.ASR_VAD_TIMEOUT_FRONTSIL) {
            var2 = this.unsafe.getOption(1010);
        } else if (var1 == ANTEngineOption.ASR_VAD_TIMEOUT_BACKSIL) {
            var2 = this.unsafe.getOption(1011);
        } else if (var1 == ANTEngineOption.ASR_NET_TIMEOUT) {
            var2 = this.unsafe.getOption(1014);
        } else if (var1 == ANTEngineOption.WAKEUP_OPT_THRESHOLD_VALUE) {
            var2 = this.unsafe.getOption(3150);
        } else if (var1 == ANTEngineOption.NLU_ENABLE) {
            var2 = this.unsafe.getOption(1020);
        } else if (var1 == ANTEngineOption.NLU_SCENARIO) {
            var2 = this.unsafe.getOption(1021);
        } else if (var1 == ANTEngineOption.GENERAL_HISTORY) {
            var2 = this.unsafe.getOption(1030);
        } else if (var1 == ANTEngineOption.GENERAL_CITY) {
            var2 = this.unsafe.getOption(1031);
        } else if (var1 == ANTEngineOption.GENERAL_VOICEID) {
            var2 = this.unsafe.getOption(1032);
        } else if (var1 == ANTEngineOption.ASR_OPT_RECOGNIZE_SCENE) {
            var2 = this.unsafe.getOption(1082);
        } else if (var1 == ANTEngineOption.ASR_SUBDOMAIN) {
            var2 = this.unsafe.getOption(1087);
        } else if (var1 == ANTEngineOption.ASR_VAD_TIMEOUT_FRONTSIL) {
            var2 = this.unsafe.getOption(1010);
        } else if (var1 == ANTEngineOption.ASR_VAD_TIMEOUT_BACKSIL) {
            var2 = this.unsafe.getOption(1011);
        } else if (var1 == ANTEngineOption.ASR_FOURMIC_DOA_RESULT) {
            var2 = this.unsafe.getOption(10196);
        } else if (var1 == ANTEngineOption.ASR_FOURMIC_BOARD_VERSION) {
            var2 = this.unsafe.getOption(10197);
        } else if (var1 == ANTEngineOption.ASR_FOURMIC_CLOSE_4MICALGORITHM) {
            var2 = this.unsafe.getOption(10100);
        } else if (var1 == ANTEngineOption.GENERAL_UDID) {
            var2 = this.unsafe.getOption(1036);
        } else {
            var2 = this.h.get(var1);
        }

        return (T)var2;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public Map<ANTEngineOption<?>, Object> getOptions() {
        return this.h;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public String getWakeupOneshotTag() {
        return this.g;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public String getWakeupTag() {
        return this.f;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public boolean isPrintEngineLog() {
        return this.k;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public ANTEngineConfig setLocalConfiguration(ANTELocalConfiguration aNTELocalConfiguration) {
        this.i = aNTELocalConfiguration;
        return this;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public ANTEngineConfig setMainVocab(Map<String, List<String>> map) {
        this.j = (Map) a(this.j, map, "mainVocab");
        return this;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public ANTEngineConfig setMessageCodec(MessageCodec messageCodec) {
        if (messageCodec != null) {
            this.d = messageCodec;
            return this;
        }
        throw new NullPointerException("messageCodec");
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public <T> boolean setOption(ANTEngineOption<T> var1, T var2) {
        a(var1, var2);
        LogMgr.d("NativeANTEngineConfig", "==>>setOption option:" + var1 + ",value:" + ((Object) var2));
        boolean var3;
        if (var1 == ANTEngineOption.MAIN_TAG) {
            setMainTag((String) var2);
        } else if (var1 == ANTEngineOption.WAKEUP_TAG) {
            setWakeupTag((String)var2);
        } else if (var1 == ANTEngineOption.WAKEUP_ONESHOT_TAG) {
            setWakeupOneshotTag((String)var2);
        } else if (var1 == ANTEngineOption.MESSAGE_CODEC) {
            this.setMessageCodec((MessageCodec)var2);
        } else if (var1 == ANTEngineOption.ASR_SAMPLING_RATE) {
            this.unsafe.setASROption(1044, var2);
        } else if (var1 == ANTEngineOption.ASR_DOMAIN) {
            this.unsafe.setASROption(1008, var2);
        } else if (var1 == ANTEngineOption.ASR_VOICE_FIELD) {
            this.unsafe.setASROption(1003, var2);
        } else if (var1 == ANTEngineOption.ASR_LANGUAGE) {
            this.unsafe.setASROption(1004, var2);
        } else if (var1 == ANTEngineOption.ASR_NET_TIMEOUT) {
            this.unsafe.setASROption(1014, var2);
        } else if (var1 == ANTEngineOption.GENERAL_UPDATE_VOLUME) {
            this.unsafe.setASROption(1045, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_FRONT_CACHE_TIME) {
            this.unsafe.setASROption(1060, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_VAD_ENABLED) {
            this.unsafe.setASROption(1061, var2);
        } else if (var1 == ANTEngineOption.WAKEUP_OPT_THRESHOLD_VALUE) {
            this.unsafe.setASROption(3150, var2);
        } else if (var1 == ANTEngineOption.NLU_ENABLE) {
            this.unsafe.setASROption(1020, var2);
        } else if (var1 == ANTEngineOption.NLU_SCENARIO) {
            this.unsafe.setASROption(1021, var2);
        } else if (var1 == ANTEngineOption.GENERAL_GPS) {
            this.unsafe.setASROption(1033, var2);
        } else if (var1 == ANTEngineOption.GENERAL_HISTORY) {
            this.unsafe.setASROption(1030, var2);
        } else if (var1 == ANTEngineOption.GENERAL_CITY) {
            this.unsafe.setASROption(1031, var2);
        } else if (var1 == ANTEngineOption.GENERAL_VOICEID) {
            this.unsafe.setASROption(1032, var2);
        } else if (var1 == ANTEngineOption.NLU_VER) {
            this.unsafe.setASROption(1024, var2);
        } else if (var1 == ANTEngineOption.NLU_APPVER) {
            this.unsafe.setASROption(1025, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_FRONT_RESET_CACHE_BYTE_TIME) {
            this.unsafe.setASROption(1070, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_DEBUG_SAVELOG) {
            this.unsafe.setASROption(1071, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_DEBUG_POSTLOG) {
            this.unsafe.setASROption(1072, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_SAVE_AFTERVAD_RECORDING_DATA) {
            this.unsafe.setASROption(1074, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_ONESHOT_VADBACKSIL_TIME) {
            this.unsafe.setASROption(1081, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_RECOGNIZE_SCENE) {
            this.unsafe.setASROption(1082, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_RECOGNIZE_MODEL_ID) {
            if (this.antEngine.isModeInitialized()) {
                LogMgr.d("NativeANTEngineConfig", "ASR_OPT_RECOGNIZE_MODEL_ID:" + var2);
                this.unsafe.setASROption(1083, var2);
            }
        } else if (var1 == ANTEngineOption.ASR_OPT_WAKEUP_MODEL_ID) {
            if (this.antEngine.isModeInitialized()) {
                LogMgr.d("NativeANTEngineConfig", "ASR_OPT_WAKEUP_MODEL_ID:" + var2);
                this.unsafe.setASROption(1084, var2);
            }
        } else if (var1 == ANTEngineOption.ASR_OPT_ALREAD_AWPE) {
            this.unsafe.setASROption(1085, var2);
        } else if (var1 == ANTEngineOption.ASR_SUBDOMAIN) {
            this.unsafe.setASROption(1087, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_RESULT_FILTER) {
            this.unsafe.setASROption(1051, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_RECORDING_ENABLED) {
            this.unsafe.setASROption(1053, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_PRINT_LOG) {
            this.unsafe.setASROption(1054, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_PRINT_TIME_LOG) {
            this.unsafe.setASROption(1063, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_SAVE_RECORDING_DATA) {
            this.unsafe.setASROption(1058, var2);
        } else if (var1 == ANTEngineOption.ASR_VAD_TIMEOUT_FRONTSIL) {
            this.unsafe.setASROption(1010, var2);
        } else if (var1 == ANTEngineOption.ASR_VAD_TIMEOUT_BACKSIL) {
            this.unsafe.setASROption(1011, var2);
        } else if (var1 == ANTEngineOption.ASR_FOURMIC) {
            this.unsafe.setASROption(10199, var2);
        } else if (var1 == ANTEngineOption.ASR_FOURMIC_ISDEBUG) {
            this.unsafe.setASROption(10198, var2);
        } else if (var1 == ANTEngineOption.ASR_FOURMIC_CHANGE_CHANNEL) {
            this.unsafe.setASROption(10194, var2);
        } else if (var1 == ANTEngineOption.ASR_FOURMIC_CLOSE_4MICALGORITHM) {
            this.unsafe.setASROption(10100, var2);
        } else if (var1 == ANTEngineOption.OPT_SET_FIX_RESULT_NLU) {
            this.unsafe.setASROption(5, var2);
        } else if (var1 == ANTEngineOption.OPT_SET_FIX_RESULT_NLU_CONFIGPATH) {
            this.unsafe.setASROption(6, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_PRINT_ENGINE_LOG) {
            this.unsafe.setASROption(1062, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_VOICE_SPEED) {
            this.unsafe.setTTSOption(2001, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_VOICE_PITCH) {
            this.unsafe.setTTSOption(2002, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_VOICE_VOLUME) {
            this.unsafe.setTTSOption(2003, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_VOICE_NAME) {
            this.unsafe.setTTSOption(2005, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_PLAY_START_BUFFER_TIME) {
            this.unsafe.setTTSOption(2012, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_SAMPLE_RATE) {
            this.unsafe.setTTSOption(2004, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_STREAM_TYPE) {
            this.unsafe.setTTSOption(2013, var2);
        } else if (var1 == ANTEngineOption.TTS_SERVICE_MODE) {
            this.unsafe.setTTSOption(2020, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_IS_DEBUG) {
            this.unsafe.setTTSOption(2014, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_IS_READ_ENLISH_IN_PINYIN) {
            this.unsafe.setTTSOption(2021, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_FRONT_SILENCE) {
            this.unsafe.setTTSOption(2022, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_BACK_SILENCE) {
            this.unsafe.setTTSOption(2023, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_IS_URGENT_AUDIO) {
            this.unsafe.setTTSOption(2024, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_FRONTEND_MODEL_PATH) {
            this.unsafe.setTTSOption(2030, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_BACKEND_MODEL_PATH) {
            this.unsafe.setTTSOption(2031, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_SWITCH_BACKEND_MODEL_PATH) {
            this.unsafe.setTTSOption(2032, var2);
        } else if (var1 == ANTEngineOption.TTS_KEY_USER_DICT_FILE_PATH) {
            this.unsafe.setTTSOption(2035, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_WX_SERVICE) {
            LogMgr.d("NativeANTEngineConfig", "ASR_OPT_WX_SERVICE:" + var2);
            this.unsafe.setASROption(1095, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_SUB_SERVICE_PARAM) {
            LogMgr.d("NativeANTEngineConfig", "ASR_OPT_SUB_SERVICE_PARAM:" + var2);
            this.unsafe.setASROption(1097, var2);
        } else if (var1 == ANTEngineOption.ASR_OPT_RECOGNITION_FRONT_VAD) {
            this.unsafe.setASROption(1079, var2);
        } else {
            if (var1 != ANTEngineOption.ASR_OPT_TEMP_RESULT_ENABLE) {
                var3 = false;
                return var3;
            }

            this.unsafe.setASROption(1076, var2);
        }

        var3 = true;
        return var3;
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public boolean setOptions(Map<ANTEngineOption<?>, ?> map) {
        if (map != null) {
            if (this.h == null) {
                this.h = new DefaultUserANTEOptionProvider().options();
            }
            boolean z = true;
            for (Map.Entry<ANTEngineOption<?>, ?> entry : map.entrySet()) {
                this.h.put(entry.getKey(), entry.getValue());

                if (!setOption((ANTEngineOption<T>)entry.getKey(), (T)entry.getValue())) {
                    z = false;
                }
            }
            return z;
        }
        throw new NullPointerException("options");
    }

    @Override // com.unisound.vui.engine.ANTEngineConfig
    public ANTEngineConfig setPrintEngineLog(boolean z) {
        this.k = z;
        this.unsafe.setASROption(1062, Boolean.valueOf(z));
        return this;
    }
}

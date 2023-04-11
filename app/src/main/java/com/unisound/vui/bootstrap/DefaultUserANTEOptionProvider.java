package com.unisound.vui.bootstrap;

import com.phicomm.speaker.device.BuildConfig;
import com.phicomm.speaker.device.utils.Setting;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.engine.ANTEngineOption;
import com.unisound.vui.util.LogMgr;

import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultUserANTEOptionProvider implements a {
    @Override // com.unisound.vui.bootstrap.a
    public Map<ANTEngineOption<?>, Object> options() {
        LogMgr.d("DefaultUserANTEOptionProvider", "DefaultUserANTEOptionProvider");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(ANTEngineOption.ASR_OPT_RESULT_FILTER, true);
        linkedHashMap.put(ANTEngineOption.ASR_OPT_PRINT_TIME_LOG, Setting.ASR_OPT_PRINT_TIME_LOG);
        linkedHashMap.put(ANTEngineOption.ASR_OPT_PRINT_LOG, Setting.ASR_OPT_PRINT_LOG);
        linkedHashMap.put(ANTEngineOption.ASR_OPT_PRINT_ENGINE_LOG, Setting.ASR_OPT_PRINT_ENGINE_LOG);
        linkedHashMap.put(ANTEngineOption.ASR_VOICE_FIELD, true);
        linkedHashMap.put(ANTEngineOption.ASR_FOURMIC, true);
        linkedHashMap.put(ANTEngineOption.ASR_FOURMIC_CLOSE_4MICALGORITHM, false);
        linkedHashMap.put(ANTEngineOption.OPT_SET_FIX_RESULT_NLU, true);
        linkedHashMap.put(ANTEngineOption.ASR_NET_TIMEOUT, 5);
        linkedHashMap.put(ANTEngineOption.WAKEUP_OPT_THRESHOLD_VALUE, Float.valueOf(ANTConfigPreference.wakeupBenchmarkForMusicPlayingThree));
        linkedHashMap.put(ANTEngineOption.ASR_DOMAIN, "song");
        linkedHashMap.put(ANTEngineOption.NLU_SCENARIO, "musicDefault");
        linkedHashMap.put(ANTEngineOption.ASR_OPT_ONESHOT_VADBACKSIL_TIME, Integer.valueOf(ANTConfigPreference.oneshotVadBackSil));
        linkedHashMap.put(ANTEngineOption.ASR_VAD_TIMEOUT_FRONTSIL, Integer.valueOf(ANTConfigPreference.asrVadTimeoutFrontSil));
        linkedHashMap.put(ANTEngineOption.ASR_VAD_TIMEOUT_BACKSIL, Integer.valueOf(ANTConfigPreference.asrVadTimeoutBackSil));
        linkedHashMap.put(ANTEngineOption.ASR_OPT_RECOGNIZE_MODEL_ID, 54);
        linkedHashMap.put(ANTEngineOption.ASR_OPT_WAKEUP_MODEL_ID, 54);
        linkedHashMap.put(ANTEngineOption.TTS_SERVICE_MODE, 3);
        linkedHashMap.put(ANTEngineOption.TTS_KEY_FRONT_SILENCE, 0);
        linkedHashMap.put(ANTEngineOption.TTS_KEY_BACK_SILENCE, 200);
        linkedHashMap.put(ANTEngineOption.TTS_KEY_VOICE_VOLUME, 80);
        linkedHashMap.put(ANTEngineOption.TTS_KEY_VOICE_SPEED, 70);
        linkedHashMap.put(ANTEngineOption.OPT_SET_FIX_RESULT_NLU_CONFIGPATH, "unidrive_nlu_offline.conf");
        linkedHashMap.put(ANTEngineOption.NLU_VER, "3.0");
        linkedHashMap.put(ANTEngineOption.ASR_OPT_TEMP_RESULT_ENABLE, false);
        linkedHashMap.put(ANTEngineOption.ASR_OPT_FRONT_RESET_CACHE_BYTE_TIME, Integer.valueOf(ANTConfigPreference.beepSize));
        linkedHashMap.put(ANTEngineOption.ASR_OPT_WX_SERVICE, true);
        return linkedHashMap;
    }
}

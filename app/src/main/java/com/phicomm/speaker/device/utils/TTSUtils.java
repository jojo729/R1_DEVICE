package com.phicomm.speaker.device.utils;

import android.content.Context;
import com.unisound.vui.bootstrap.DefaultLocalConfigurationProvider;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.engine.ANTEngineOption;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.UserPerferenceUtil;

public class TTSUtils {
    public static void switchSpeaker(Context context, ANTEngine engine, String speaker) {
        String ttsModelFile;
        DefaultLocalConfigurationProvider provider = new DefaultLocalConfigurationProvider(context);
        char c = 65535;
        switch (speaker.hashCode()) {
            case -1852813825:
                if (speaker.equals("CHILDREN")) {
                    c = 3;
                    break;
                }
                break;
            case 75902:
                if (speaker.equals("LZL")) {
                    c = 5;
                    break;
                }
                break;
            case 2358797:
                if (speaker.equals("MALE")) {
                    c = 2;
                    break;
                }
                break;
            case 79312592:
                if (speaker.equals("SWEET")) {
                    c = 4;
                    break;
                }
                break;
            case 2070122316:
                if (speaker.equals("FEMALE")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 1:
                ttsModelFile = provider.getTTSBackendStandarPath();
                break;
            case 2:
                ttsModelFile = provider.getTTSBackendMalePath();
                break;
            case 3:
                ttsModelFile = provider.getTTSBackendChildPath();
                break;
            case 4:
                ttsModelFile = provider.getTTSBackendSweetPath();
                break;
            case 5:
                ttsModelFile = provider.getTTSBackendLZLPath();
                break;
            default:
                ttsModelFile = provider.getTTSBackendSweetPath();
                break;
        }
        UserPerferenceUtil.setUserTTSModelType(context, speaker);
        engine.config().setOption(ANTEngineOption.TTS_KEY_SWITCH_BACKEND_MODEL_PATH, ttsModelFile);
        engine.pipeline().fireUserEventTriggered(ExoConstants.SWITCH_TTS_PLAYER);
    }
}

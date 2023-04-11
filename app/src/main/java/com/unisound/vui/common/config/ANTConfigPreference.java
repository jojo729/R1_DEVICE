package com.unisound.vui.common.config;

import android.content.Context;
import android.text.TextUtils;
import com.phicomm.speaker.device.BuildConfig;
import com.unisound.client.ErrorCode;
import com.unisound.vui.util.LogMgr;

public class ANTConfigPreference {
    public static float FUNCTION_WAKEUP_BENCHMARK = -7.0f;
    public static boolean aecDebug = false;
    public static boolean aecEnable = false;
    public static int aecMicChannel = 0;
    public static int asrMaxDuration = 7000;
    public static int asrVadTimeoutBackSil = ErrorCode.ERR_ILLEGAL_CHAR;
    public static int asrVadTimeoutFrontSil = 5000;
    public static int asrWechatMaxDuration = 30000;
    public static int beepSize = 200;
    public static boolean debug = false;
    public static boolean isAsrRecognitionTest = false;
    public static boolean isWakeupAecTest = false;
    public static boolean isWakeupTest = false;
    public static int musicAec = 512;
    public static int oneshotAsrVadTimeoutBackSil = 500;
    public static boolean oneshotEnable = false;
    public static int oneshotVadBackSil = 1000;
    public static int oneshotWakeupVadTimeoutBackSil = 500;
    public static boolean onlineVad = false;
    public static float recognizerScore = -12.8f;
    private static String sDevAppServer;
    private static String sDevMsgServer;
    private static String sDevTRServer;
    public static int sMaxCountAlarm;
    public static int sMaxCountCountDown;
    public static int sMaxCountReminder;
    public static String sVersionType;
    public static boolean saveRecognizerRecording = false;
    public static boolean saveTTSRecording = false;
    public static boolean saveWakeUpRecording = false;
    public static boolean sdkDebug = false;
    public static int ttsAec = 128;
    public static float wakeupBenchmark = -14.3f;
    public static float effectWakeupBenchmark = wakeupBenchmark;
    public static float wakeupBenchmarkForContinues = -14.3f;
    public static float wakeupBenchmarkForMusicPlaying = -15.6f;
    public static float wakeupBenchmarkForMusicPlayingThree = -18.6f;
    public static float wakeupBenchmarkForMusicPlayingTwo = -17.6f;
    public static int wakeupDurationForContinues = 5000;
    public static int wakeupVadTimeoutBackSil = 200;

    private ANTConfigPreference() {
    }

    public static String getAppServerUrl() {
        return isDev() ? TextUtils.isEmpty(sDevAppServer) ? "http://106.38.55.5:19999/" : sDevAppServer : "http://aios-home.hivoice.cn:19999/";
    }

    public static String getMsgCenterUrl() {
        return isDev() ? TextUtils.isEmpty(sDevMsgServer) ? "http://106.38.55.5:18080" : sDevMsgServer : isPreRelease() ? "http://msg.pda.hivoice.cn:8080" : "http://msg-pandora.hivoice.cn";
    }

    public static String getTRUrl() {
        return isDev() ? TextUtils.isEmpty(sDevTRServer) ? "211.102.192.39:8089" : sDevTRServer : "asrv3.hivoice.cn";
    }

    public static void init(Context context) {
        a.a(context);
        initPreference();
        a.a(new a.AbstractC0008a() {
            /* class com.unisound.vui.common.config.ANTConfigPreference.AnonymousClass1 */
        });
    }

    /* access modifiers changed from: private */
    public static void initPreference() {
        LogMgr.d("ANTConfigPreference", "initPreference");
        recognizerScore = a.a("recognizer_benchmark", recognizerScore);
        wakeupBenchmark = a.a("wakeup_benchmark", wakeupBenchmark);
        effectWakeupBenchmark = wakeupBenchmark;
        beepSize = a.a("beep_size", beepSize);
        asrVadTimeoutFrontSil = a.a("asr_vad_timeout_front_sil", asrVadTimeoutFrontSil);
        asrVadTimeoutBackSil = a.a("asr_vad_timeout_back_sil", asrVadTimeoutBackSil);
        oneshotAsrVadTimeoutBackSil = a.a("oneshot_asr_vad_timeout_back_sil", oneshotAsrVadTimeoutBackSil);
        wakeupVadTimeoutBackSil = a.a("wakeup_vad_timeout_back_sil", wakeupVadTimeoutBackSil);
        oneshotWakeupVadTimeoutBackSil = a.a("oneshot_wakeup_vad_timeout_back_sil", oneshotWakeupVadTimeoutBackSil);
        oneshotVadBackSil = a.a("oneshot_vad_back_sil", oneshotVadBackSil);
        asrMaxDuration = a.a("asr_max_duration", asrMaxDuration);
        asrWechatMaxDuration = a.a("asr_wechat_max_duration", asrWechatMaxDuration);
        saveRecognizerRecording = a.a("save_recognizer_recording", saveRecognizerRecording);
        saveWakeUpRecording = a.a("save_wakeup_recording", saveWakeUpRecording);
        aecEnable = a.a("aec_enable", aecEnable);
        aecDebug = a.a("aec_debug", aecDebug);
        aecMicChannel = a.a("aec_mic_channel", aecMicChannel);
        oneshotEnable = a.a("oneshot_enable", oneshotEnable);
        debug = a.a(BuildConfig.BUILD_TYPE, debug);
        ttsAec = a.a("tts_aec", ttsAec);
        musicAec = a.a("music_aec", musicAec);
        wakeupDurationForContinues = a.a("wakeup_duration_for_continues", wakeupDurationForContinues);
        wakeupBenchmarkForContinues = a.a("wakeup_benchmark_for_continues", wakeupBenchmarkForContinues);
        wakeupBenchmarkForMusicPlaying = a.a("wakeup_benchmark_for_music_playing", wakeupBenchmarkForMusicPlaying);
        wakeupBenchmarkForMusicPlayingTwo = a.a("wakeup_benchmark_for_music_playing_two", wakeupBenchmarkForMusicPlayingTwo);
        wakeupBenchmarkForMusicPlayingThree = a.a("wakeup_benchmark_for_music_playing_three", wakeupBenchmarkForMusicPlayingThree);
        isWakeupTest = a.a("wakeup_test", isWakeupTest);
        isWakeupAecTest = a.a("wakeup_aec_test", isWakeupAecTest);
        sdkDebug = a.a("sdk_debug", sdkDebug);
        saveTTSRecording = a.a("save_tts_recording", saveTTSRecording);
        onlineVad = a.a("asr_vad_online", onlineVad);
        isAsrRecognitionTest = a.a("asr_recognition_test", isAsrRecognitionTest);
        sVersionType = a.a("version_type", "");
        sMaxCountAlarm = a.a("max_count_alarm", 30);
        sMaxCountReminder = a.a("max_count_reminder", 30);
        sMaxCountCountDown = a.a("max_count_count_down", 30);
        sDevAppServer = a.a("devAppServer", "");
        sDevMsgServer = a.a("devMsgServer", "");
        sDevTRServer = a.a("devTRServer", "");
    }

    public static boolean isDev() {
        return "develop".equals(sVersionType);
    }

    public static boolean isPreRelease() {
        return "preRelease".equals(sVersionType);
    }

    public static boolean isRelease() {
        return "release".equals(sVersionType) || TextUtils.isEmpty(sVersionType);
    }
}

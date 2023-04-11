package com.unisound.vui.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.unisound.client.IAudioSource;
import com.unisound.client.SpeechConstants;
import com.unisound.vui.Ra2;
import com.unisound.vui.bootstrap.ANTELocalConfiguration;
import com.unisound.vui.bootstrap.DefaultLocalConfigurationProvider;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.common.file.FileHelper;
import com.unisound.vui.common.network.NetUtil;
import com.unisound.vui.d;
import com.unisound.vui.data.tts.TTSContent;
import com.unisound.vui.e;
import com.unisound.vui.transport.out.VocabContent;
import com.unisound.vui.util.*;
import com.unisound.vui.util.entity.ExoAsrTag;
import com.unisound.vui.util.entity.VocabSlotTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NativeANTEngine extends AbstractANTEngine {

    /* renamed from: a  reason: collision with root package name */
    private static final String a = ("oneshot:" + ExoAsrTag.getMainTag(ExoAsrTag.TAG_UNIDRIVE_MAIN));
    private static String b;
    private static String c;
    private List<String> d;
    private String e;
    private List<String> f = new ArrayList();
    private List<String> g = new ArrayList();
    private final ANTEngineConfig h;
    private final Unsafe unsafe = newUnsafe();

    private Handler handler = new Handler(Looper.getMainLooper()) {
        /* class com.unisound.vui.engine.NativeANTEngine.AnonymousClass1 */

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    ArrayList arrayList = new ArrayList();
                    UserPerferenceUtil.setWakeupWord(context, d);
                    arrayList.addAll(UserPerferenceUtil.getMainWakeupWord(context));
                    setWakeupWord0(arrayList, false);
                    insertVocab(f(), ExoAsrTag.getMainTag(ExoAsrTag.TAG_UNIDRIVE_MAIN));
                    return;
                case 1002:
                    pipeline().fireUserEventTriggered(Integer.valueOf((int) EventType.WAKEUP_EVENT_UPDATEWAKEUPWORD_FAIL));
                    return;
                case 1003:
                    setWakeupWord0(d, false);
                    return;
                default:
                    return;
            }
        }
    };
    private final Ra2 j;
    private final e k;
    private final com.unisound.vui.c l;
    private String m;
    private ANTELocalConfiguration n;
    private Gson o = new Gson();
    private NativeSpeechUnderstanderListener p;
    private TTSContent q;
    private Handler r = new Handler() {
        /* class com.unisound.vui.engine.NativeANTEngine.AnonymousClass2 */

        public void handleMessage(Message message) {
            LogMgr.d("NativeANTEngine", "try to initSpeechUnderstanderWithDeviceId again");
            c();
        }
    };

    public  final class NativeUnsafe extends AbstractUnsafe {
        NativeUnsafe() {
            super();
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public Object getOption(int i) {
            return j.a(i);
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public String getVersion() {
            return j.c();
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public int loadCompiledJsgf(String str, String str2) {
            return j.a(str, str2);
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public int loadGrammar(String str, String str2) {
            return j.b(str, str2);
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void release(int i, String str) {
            j.a(i, str);
            l.a(i, str);
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void setASROption(int i, Object obj) {
            j.a(i, obj);
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void setMainTag(String str) {
            if (n == null) {
                n = config().getLocalConfiguration();
            }
            if (n.contentTag(str)) {
                m = str;
                if (((Boolean) config().getOption(ANTEngineOption.OPT_SET_FIX_RESULT_NLU)).booleanValue()) {
                    j.a(6, (Object) n.getLocalRecognition(m).getLocalNluPath());
                }
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void setNLUOption(int i, Object obj) {
            k.a(i, obj);
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void setOption(String str, Object obj) {
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void setTTSOption(int i, Object obj) {
            l.a(i, obj);
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public int unloadGrammar(String str) {
            return j.c(str);
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void uploadUserData(Map<Integer, List<String>> map) {
            j.a(map);
        }
    }

    /* access modifiers changed from: package-private */
    public final class NativeSpeechUnderstanderListener implements com.unisound.vui.b {
        private final ANTPipeline aNTPipeline;

        NativeSpeechUnderstanderListener(ANTPipeline aNTPipeline) {
            this.aNTPipeline = aNTPipeline;
        }

        @Override // com.unisound.client.SpeechUnderstanderListener, com.unisound.vui.b
        public void onError(int i, String str) {
            LogMgr.d("NativeANTEngine", "type:" + i + ";onError:" + str);
            if (!ANTConfigPreference.isAsrRecognitionTest) {
                this.aNTPipeline.fireASRError(i, str);
            }
        }

        @Override // com.unisound.client.SpeechUnderstanderListener, com.unisound.vui.b
        public void onEvent(int i, int i2) {
            if (i == 1129) {
                LogMgr.d("NativeANTEngine", "--->>engine init done vesion " + j.c());
                j.a(5001, Float.valueOf(5000000.0f));
                j.a(5002, Float.valueOf(5000000.0f));
                j.a(5003, Float.valueOf(-0.8f));
                j.a(5004, (Object) 3);
                j.a(5005, (Object) 100);
                j.a(5008, (Object) 30);
                j.a(5012, Float.valueOf(0.8f));
                j.a(5013, (Object) 1);
                j.a(5017, (Object) 3);
                j.a(1019, (Object) "athenaAppService");
            }
            if (i == 3105) {
                LogMgr.d("NativeANTEngine", "wakeUpEventSetWakeUpWordDone");
                f.clear();
                f.addAll(g);
                setNeedRecoveryWakeUp(false);
                setInSetWakeUpWord(false);
                boolean startWakeupAfterSetWakeupWord = UserPerferenceUtil.startWakeupAfterSetWakeupWord(context);
                LogMgr.d("NativeANTEngine", "set wakeup word done, need start wakeup = " + startWakeupAfterSetWakeupWord + ", current thread is " + Thread.currentThread().getName());
                if (!isASR() && !  isRecognition() && startWakeupAfterSetWakeupWord) {
                    if (  getAecEnable() || !  isTTSPlaying()) {
                        try {
                            if (  isTTSPlaying()) {
                                doEnterWakeup(false);
                            } else {
                                doEnterWakeup(  isPlayBeep());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    return;
                }
            } else if (i == 1160) {
                LogMgr.d("NativeANTEngine", "upload online wake up word success");
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 1001;
                handler.sendMessage(obtainMessage);
            } else if (i != 1117) {
                if (i == 3103) {
                    LogMgr.d("NativeANTEngine", "WAKEUP_EVENT_RECOGNITION_SUCCESS ");
                    return;
                } else if (i == 1129) {
                    LogMgr.d("AutoStart", "engine init done");
                } else if (i == 1101) {
                    LogMgr.d("NativeANTEngine", "onAsrEngine recording start:" + SystemUitls.getCurrentTime());
                } else if (i == 1102) {
                    LogMgr.d("NativeANTEngine", "onAsrEngine recording stop:" + SystemUitls.getCurrentTime());
                    j.a(1058, (Object) "");
                } else if (i == 1107) {
                    LogMgr.d("NativeANTEngine", "onAsrEngine recording end:" + SystemUitls.getCurrentTime());
                    setEngineState(0);
                } else if (i == 1103) {
                    LogMgr.d("NativeANTEngine", "onAsrEngine vad timeout:" + SystemUitls.getCurrentTime());
                    doStopASR();
                }
            }
            if (ANTConfigPreference.isAsrRecognitionTest) {
                LogMgr.d("NativeANTEngine", "asr is recognise test:" + ANTConfigPreference.isAsrRecognitionTest);
                if (i == 1102) {
                    try {
                        doEnterASR();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            this.aNTPipeline.fireASREvent(i);
        }

        @Override // com.unisound.client.SpeechUnderstanderListener, com.unisound.vui.b
        public void onResult(int i, String str) {
            LogMgr.d("NativeANTEngine", "type:" + i + ";onResult:" + str);
            if (!ANTConfigPreference.isAsrRecognitionTest) {
                this.aNTPipeline.fireASRResult(i, str);
            } else if (i == 3201) {
                try {
                    doEnterASR();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    final class c implements d {
        private final ANTPipeline b;

        private c(ANTPipeline aNTPipeline) {
            this.b = aNTPipeline;
        }

        @Override // com.unisound.client.SpeechSynthesizerListener, com.unisound.vui.d
        public void onError(int i, String str) {
            this.b.fireTTSError(i, str);
        }

        @Override // com.unisound.client.SpeechSynthesizerListener, com.unisound.vui.d
        public void onEvent(int i) {
            LogMgr.d("NativeANTEngine", "onEvent : " + i);
            if (i == 2107) {
                LogMgr.d("NativeANTEngine", "tts play end ,isInSetWakeUpWord:" + isInSetWakeUpWord());
               setTtsPlaying(false);
            }
            if (i == 2101) {
                LogMgr.d("AutoStart", "tts int done");
               b();
                l.a(1, (Object) 500);
            }
            this.b.fireTTSEvent(i);
        }
    }

    public NativeANTEngine(com.unisound.vui.engine.a.AbstractC0007a aVar, IAudioSource audioSource) {
        LogMgr.d("NativeANTEngine", "NativeANTEngine");
        a();
        this.h = new com.unisound.vui.engine.b(this);
        com.unisound.vui.engine.a aNTBuilder = aVar.getANTBuilder();
        this.context = aVar.context();
        UserPerferenceUtil.setStartWakeupAfterSetWakeupWord(this.context, true);
        this.j = aNTBuilder.createASRManager();
        this.k = aNTBuilder.createNluManager();
        ANTPipeline pipeline = pipeline();
        NativeSpeechUnderstanderListener bVar = new NativeSpeechUnderstanderListener(pipeline);
        this.p = bVar;
        this.j.a(bVar);
        com.unisound.vui.c createTTSManager = aNTBuilder.createTTSManager();
        this.l = createTTSManager;
        createTTSManager.a(new c(pipeline));
        TTSContent tTSContent = new TTSContent(this.context);
        this.q = tTSContent;
        tTSContent.init();
    }

    private void a() {
        LogMgr.d("NativeANTEngine", "initReqAddress is versionType:" + ANTConfigPreference.sVersionType);
        c = ANTConfigPreference.getTRUrl();
        if (ANTConfigPreference.isDev()) {
            b = "http://106.38.55.5:19999/tr/dataProcess";
        }
        LogMgr.d("NativeANTEngine", "current asr_server " + c);
    }

    private void a(int i2) {
        if (i2 == 0 && !ANTConfigPreference.saveWakeUpRecording) {
            return;
        }
        if (i2 != 1 || ANTConfigPreference.saveRecognizerRecording) {
            String aSRSavedRecordingPath = FileHelper.getASRSavedRecordingPath(i2);
            LogMgr.d("NativeANTEngine", "-setSaveRecordingOption-" + i2);
            if (aSRSavedRecordingPath != null) {
                this.j.a(1058, (Object) aSRSavedRecordingPath);
            }
        }
    }

    private boolean a(String str) {
        return (UserPerferenceUtil.getUserTTSModelType(this.context).equals("SWEET") || this.l.a(2005).equals("sweet")) && this.q.isLocalAnswer(str);
    }

    private String b(String str) {
        return "<WAV>/system/unisound/audio/tts_answer/" + str + "</WAV>";
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b() {
        LogMgr.d("AutoStart", "understander init");
        this.j.a(SpeechConstants.ASR_INIT_MODE, (Object) 0);
        this.j.a(1001, (Object) 0);
        this.j.a(SpeechConstants.ASR_FOURMIC_IS_RK_PLATFORM, (Object) true);
        this.j.a(SpeechConstants.ASR_FOURMIC_IS_RK_SINGALCHANEL, (Object) false);
        c();
        if (ANTConfigPreference.sdkDebug) {
            this.j.a(20120629, (Object) true);
        }
        d();
        this.k.a("");
        initOneshotState();
        initAecEnableState();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c() {
        String deviceId = UserPerferenceUtil.getDeviceRId(this.context);
        if(deviceId==null){
            deviceId = "FC7C02BD2981541";
            UserPerferenceUtil.setDeviceRId(this.context,deviceId);
        }

        // 可用 FC7C02BD2981  old : FC7C02BD2EE5
        deviceId = "FC7C02BD2981";
        this.e = deviceId;
        if (!TextUtils.isEmpty(deviceId)) {
            String str = "{\"activate\":\"true\",\"deviceSn\":\"" + this.e + "\"}";
            this.j.a(str);
            LogMgr.d("NativeANTEngine", "speechUnderstander init with jsonStr : " + str);
            return;
        }
        LogMgr.d("NativeANTEngine", "jsonStr is null, post delay 1000 ms");
        this.r.sendEmptyMessageDelayed(0, 1000);
    }

    private void d() {
        Ra2 ra2Var = this.j;
        if (ra2Var != null) {
            ra2Var.a(1095, (Object) true);
            if (ANTConfigPreference.isDev()) {
                this.j.a(SpeechConstants.ASR_OPT_FILTER_URL, (Object) b);
            }
            LogMgr.d("NativeANTEngine", "set token:" + UserPerferenceUtil.getValidSdk(this.context));
            if (!TextUtils.isEmpty(UserPerferenceUtil.getValidSdk(this.context))) {
                this.j.a(1097, (Object) ExoConstants.getServiceParam());
            }
        }
    }

    private void e() {
        LogMgr.d("AutoStart", "tts init");
        this.l.a(2022, (Object) 150);
        this.l.a(2003, (Object) 100);
        if (ANTConfigPreference.sdkDebug) {
            this.l.a(20120629, (Object) true);
        }
        if (ANTConfigPreference.saveTTSRecording) {
            this.l.a(2014, (Object) true);
            this.l.a(2015, (Object) FileHelper.getASRSavedRecordingPath(2));
        }
        this.l.a("");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Map<String, List<String>> f() {
        HashMap hashMap = new HashMap();
        List<String> mainWakeupWord = UserPerferenceUtil.getMainWakeupWord(this.context);
        if (!isOneshot()) {
            LogMgr.d("NativeANTEngine", "no oneshot");
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(mainWakeupWord);
            if (arrayList.size() > 0) {
                hashMap.put(VocabSlotTag.Domain_wakeup_words_slot.toString(), arrayList);
            }
        } else {
            ArrayList arrayList2 = new ArrayList();
            if (UserPerferenceUtil.getOneshotEnableV1(this.context)) {
                for (String str : mainWakeupWord) {
                    arrayList2.add(str);
                    arrayList2.add(str + " <unk>");
                }
            }
            hashMap.put(VocabSlotTag.asr_wakeup_words_slot.toString(), arrayList2);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add("cccc");
            hashMap.put(VocabSlotTag.Domain_wakeup_words_slot.toString(), arrayList3);
        }
        LogMgr.d("NativeANTEngine", "insertWakeupWordVocab wakeupWordsMap size : %d", Integer.valueOf(hashMap.size()));
        return hashMap;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public ANTEngineConfig config() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doCancelASR() {
        LogMgr.d("NativeANTEngine", "doCancelASR state:" + this.engineState);
        if (isASR() || isRecognition()) {
            this.j.b();
            setEngineState(0);
            this.j.a(1058, (Object) "");
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doCancelEngine() {
        if (!isIdle() || isInSetWakeUpWord()) {
            LogMgr.d("NativeANTEngine", "doCancelEngine start");
            this.j.b();
            LogMgr.d("NativeANTEngine", "doCancelEngine end");
            this.j.a(1058, (Object) "");
            setEngineState(0);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doCancelTTS() {
        LogMgr.d("NativeANTEngine", "doCancelTTS");
        setTtsPlaying(false);
        this.l.b();
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doCloseReleaseStatus() {
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doEnterASR() {
        String str;
        if (ANTConfigPreference.sdkDebug && ANTConfigPreference.isAsrRecognitionTest) {
            this.j.a(10198, (Object) true);
        }
        doCancelEngine();
        LogMgr.d("NativeANTEngine", "doEnterASR isWakeUp:" + isWakeup());
        a(1);
        this.j.a(1070, Integer.valueOf(ANTConfigPreference.beepSize));
        LogMgr.d("NativeANTEngine", "doEnterASR tag - %s", this.m);
        this.j.a(1011, Integer.valueOf(ANTConfigPreference.asrVadTimeoutBackSil));
        this.j.a(5008, (Object) 50);
        if (ANTConfigPreference.isDev()) {
            this.j.a(SpeechConstants.ASR_OPT_FILTER_URL, (Object) b);
        }
        this.j.a(1009, (Object) c);
        this.j.a(10100, (Object) false);
        this.j.a(SpeechConstants.ASR_OPT_RECORDING_PAC_SIZE, Integer.valueOf((int) SpeechConstants.ASR_BEST_RESULT_RETURN));
        if (ANTConfigPreference.onlineVad) {
            this.j.a(SpeechConstants.ASR_OPT_RECOGNIZE_VAD_ENABLE, (Object) true);
        }
        List<String> mainWakeupWord = UserPerferenceUtil.getMainWakeupWord(this.context);
        mainWakeupWord.removeAll(UserPerferenceUtil.getDefaultWakeupWord(this.context));
        if (mainWakeupWord.size() == 0 || TextUtils.isEmpty(mainWakeupWord.get(0))) {
            mainWakeupWord = UserPerferenceUtil.getDefaultWakeupWord(this.context);
        }
        String str2 = mainWakeupWord.get(0);
        this.j.a(1008, (Object) "song");
        Ra2 ra2Var = this.j;
        StringBuilder sb = new StringBuilder();
        sb.append("returnType=json;city=");
        sb.append((String) this.h.getOption(ANTEngineOption.GENERAL_CITY));
        sb.append(";gps=");
        sb.append((String) this.h.getOption(ANTEngineOption.GENERAL_GPS));
        sb.append(";time=");
        sb.append(SystemUitls.getTime());
        sb.append(";screen=;dpi=;history=;udid=");
        sb.append((String) this.h.getOption(ANTEngineOption.GENERAL_UDID));
        sb.append(";ver=3.0;appver=;oneshotKeyProperty=wakeup;filterName=nlu2;additionalService=athenaAppService;scenario=musicDefault");
        if (ANTConfigPreference.isDev()) {
            str = ";filterUrl=" + b + ";";
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(";wakeupword=");
        sb.append(str2);
        sb.append(";appendLength=2;audioUrl=true;mac=");
        sb.append(this.e);
        sb.append(";");
        ra2Var.a(1017, (Object) sb.toString());
        if (isWakeupRecord()) {
            setWakeupRecord(false);
        }
        this.j.b(this.m);
        setEngineState(2);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doEnterWakeup(boolean z) {
        Ra2 ra2Var;
        String str;
        synchronized (this) {
            if (isInSetWakeUpWord()) {
                LogMgr.e("NativeANTEngine", "doEnterWakeup: now is setting wakeup words,cannot start wakeup");
            } else {
                if (ANTConfigPreference.sdkDebug && (ANTConfigPreference.isWakeupTest || ANTConfigPreference.isWakeupAecTest)) {
                    this.j.a(10198, (Object) true);
                }
                if (!isWakeup()) {
                    doCancelEngine();
                }
                LogMgr.d("NativeANTEngine", "doEnterWakeup isIdle:" + isIdle() + ", current thread = " + Thread.currentThread().getName());
                this.lastPlayBeep = z;
                if (isIdle()) {
                    this.j.a(10100, (Object) false);
                    a(0);
                    if (isOneshot()) {
                        this.j.a(1070, (Object) 0);
                        this.j.a(1011, Integer.valueOf(ANTConfigPreference.oneshotWakeupVadTimeoutBackSil));
                        setWakeupRecord(true);
                        this.j.a(SpeechConstants.ASR_OPT_RECORDING_PAC_SIZE, (Object) 3840);
                        ra2Var = this.j;
                        str = a;
                    } else {
                        this.j.a(10199, (Object) true);
                        this.j.a(1070, (Object) 0);
                        this.j.a(1011, Integer.valueOf(ANTConfigPreference.wakeupVadTimeoutBackSil));
                        this.j.a(5008, (Object) 30);
                        setWakeupRecord(true);
                        LogMgr.d("NativeANTEngine", "ASR_OPT_RECORDING_PAC_SIZE : 640");
                        this.j.a(SpeechConstants.ASR_OPT_RECORDING_PAC_SIZE, (Object) 640);
                        ra2Var = this.j;
                        str = "wakeup";
                    }
                    ra2Var.b(str);
                    setEngineState(1);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doInitEngine() {
        c();
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doInitializeMode() {
        ANTEngineConfig config;
        ANTEngineOption<String> aNTEngineOption;
        String tTSBackendSweetPath;
        DefaultLocalConfigurationProvider defaultLocalConfigurationProvider = new DefaultLocalConfigurationProvider(this.context);
        config().setOption(ANTEngineOption.TTS_KEY_FRONTEND_MODEL_PATH, defaultLocalConfigurationProvider.getTTSFrontendPath());
        String userTTSModelType = UserPerferenceUtil.getUserTTSModelType(this.context);
        LogMgr.d("NativeANTEngine", "get history speaker:" + userTTSModelType);
        if ("FEMALE".equals(userTTSModelType)) {
            config = config();
            aNTEngineOption = ANTEngineOption.TTS_KEY_BACKEND_MODEL_PATH;
            tTSBackendSweetPath = defaultLocalConfigurationProvider.getTTSBackendStandarPath();
        } else if ("MALE".equals(userTTSModelType)) {
            config = config();
            aNTEngineOption = ANTEngineOption.TTS_KEY_BACKEND_MODEL_PATH;
            tTSBackendSweetPath = defaultLocalConfigurationProvider.getTTSBackendMalePath();
        } else if ("CHILDREN".equals(userTTSModelType)) {
            config = config();
            aNTEngineOption = ANTEngineOption.TTS_KEY_BACKEND_MODEL_PATH;
            tTSBackendSweetPath = defaultLocalConfigurationProvider.getTTSBackendChildPath();
        } else {
            "SWEET".equals(userTTSModelType);
            config = config();
            aNTEngineOption = ANTEngineOption.TTS_KEY_BACKEND_MODEL_PATH;
            tTSBackendSweetPath = defaultLocalConfigurationProvider.getTTSBackendSweetPath();
        }
        config.setOption(aNTEngineOption, tTSBackendSweetPath);
        e();
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doInitializeSdk() {
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doPPTAction0() {
        LogMgr.d("NativeANTEngine", "doPPTAction0");
        doCancelASR();
        doEnterASR();
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doPlayBuffer(byte[] bArr) {
        setTtsPlaying(true);
        this.l.a(bArr);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doPlayTTS(String str) {
        com.unisound.vui.c cVar;
        if (isTTSPlaying()) {
            doCancelTTS();
        }
        LogMgr.d("NativeANTEngine", "doPlayTTS:" + str);
        if (!getAecEnable()) {
            doStopWakeup();
        } else if (!isInSetWakeUpWord()) {
            doEnterWakeup(false);
        }
        if (!TextUtils.isEmpty(str)) {
            setTtsPlaying(true);
            if (a(str)) {
                cVar = this.l;
                str = b(this.q.getLocalAnswerPath(str));
            } else {
                cVar = this.l;
            }
            cVar.b(str);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doReleaseAudioRecord() {
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doStopASR() {
        LogMgr.d("NativeANTEngine", "doStopASR isAsr:" + isASR());
        if (isASR()) {
            if (this.l.a()) {
                this.l.c();
            }
            this.j.a();
            setEngineState(3);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doStopWakeup() {
        LogMgr.d("NativeANTEngine", "doStopWakeup isWakeUp:" + isWakeup());
        if (isWakeup()) {
            this.j.b();
            setEngineState(0);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void doWrite(Object obj) {
        if (obj instanceof VocabContent) {
            VocabContent vocabContent = (VocabContent) obj;
            insertVocab0(vocabContent.getVocabContent(), vocabContent.getGrammarTag());
        }
    }

    @Override // com.unisound.vui.engine.AbstractANTEngine, com.unisound.vui.engine.ANTEngine
    public void enableOneshot(boolean z) {
        super.enableOneshot(z);
        this.j.a(1018, UserPerferenceUtil.getOneShotWakeupWord(this.context));
        insertVocab(f(), ExoAsrTag.getMainTag(ExoAsrTag.TAG_UNIDRIVE_MAIN));
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void insertVocab0(Map<String, List<String>> map, String str) {
        LogMgr.d("NativeANTEngine", "insertVocab0");
        this.j.a(map, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public AbstractUnsafe newUnsafe() {
        return new NativeUnsafe();
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void setWakeupWord0(List<String> list, boolean z) {
        if (list == null) {
            LogMgr.e("NativeANTEngine", "setWakeupWord error, wakeup word is null");
        } else if (isInSetWakeUpWord()) {
//            LogMgr.d("NativeANTEngine", "now is setting wakeup word, delay 100ms");
            this.d = list;
           handler.sendEmptyMessageDelayed(1003, 100);
        } else {
            setInSetWakeUpWord(true);
            if (isASR() || isRecognition()) {
                try {
                    doCancelEngine();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (StringUtils.listStringEquals(this.f, list)) {
                LogMgr.d("NativeANTEngine", "old wake up equals new wake up");
                this.p.onEvent(3105, 0);
                return;
            }
            try {
                doCancelEngine();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            LogMgr.d("NativeANTEngine", "setWakeupWord0,words=" + this.o.toJson(list) + "playBeep:" + z);
            setNeedRecoveryWakeUp(true);
            setPlayBeep(z);
            this.g.clear();
            this.g.addAll(list);
            this.f.clear();
            LogMgr.d("NativeANTEngine", "setWakeupWord0");
            HashMap hashMap = new HashMap();
            hashMap.put("wakeup", list);
            this.j.b(hashMap);
            UserPerferenceUtil.setEffectiveWakeupword(list, this.context);
            this.j.a(1018, UserPerferenceUtil.getOneShotWakeupWord(this.context));
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void updateWakeupWord0(final List<String> list) {
        this.d = list;
        LogMgr.d("NativeANTEngine", "updateWakeupWord0");
        if (!NetUtil.getInstante(this.context).getConnectState()) {
            pipeline().fireUserEventTriggered(Integer.valueOf((int) EventType.WAKEUP_EVENT_UPDATEWAKEUPWORD_FAIL));
        } else {
            new Thread(new Runnable() {
                /* class com.unisound.vui.engine.NativeANTEngine.AnonymousClass3 */

                public void run() {
                    j.a(list);
                }
            }).start();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.AbstractANTEngine
    public void updateWakeupWord1(final List<String> list) {
        this.d = list;
        LogMgr.d("NativeANTEngine", "updateWakeupWord1");
        if (!NetUtil.getInstante(this.context).getConnectState()) {
            pipeline().fireUserEventTriggered(Integer.valueOf((int) EventType.WAKEUP_EVENT_UPDATEWAKEUPWORD_FAIL));
        } else {
            new Thread(new Runnable() {
                /* class com.unisound.vui.engine.NativeANTEngine.AnonymousClass4 */

                public void run() {
                    j.a(list);
                }
            }).start();
        }
    }
}

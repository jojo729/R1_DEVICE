package com.unisound.vui.engine;

import android.content.Context;
import android.text.TextUtils;
import com.unisound.vui.common.file.FileHelper;
import com.unisound.vui.transport.out.VocabContent;
import com.unisound.vui.util.*;
import nluparser.scheme.SCode;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract class AbstractANTEngine extends d implements ANTEngine {
    private static final AttributeKey<Context> ANDROID_CONTEXT = AttributeKey.newInstance("ANDROID_CONTEXT");
    private static final AttributeKey<Boolean> MODE_INITIALIZED = AttributeKey.newInstance("MODE_INITIALIZED");
    private static final AttributeKey<Boolean> NEED_RECOVERY_WAKE_UP = AttributeKey.newInstance("NEED_RECOVERY_WAKE_UP");
    protected static final String TAG = "NativeANTEngine";
    private static final String TTS_PCM_END_TAG = "</PCM>";
    private static final String TTS_PCM_START_TAG = "<PCM>";
    private static final AttributeKey<List<String>> WAKEUP_WORD = AttributeKey.newInstance(SCode.WAKEUP_WORD);
    private volatile boolean aecEnable;
    protected Context context;
    protected volatile int engineState;
    private volatile boolean inSetWakeUpWord = false;
    private volatile boolean isOneshot;
    protected volatile boolean isTtsPlaying = false;
    private volatile boolean isWakeupRecord;
    protected volatile boolean lastPlayBeep = false;
    private final Unsafe unsafe;
    private final DefaultANTPipeline pipeline;
    private volatile boolean playBeep = false;

    /* access modifiers changed from: protected */
    public abstract class AbstractUnsafe implements Unsafe {
        protected AbstractUnsafe() {
            super();
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void cancelASR() {
            try {
                doCancelASR();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void cancelEngine() {
            try {
               doCancelEngine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void cancelTTS() {
            try {
               doCancelTTS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void enterASR() {
            try {
                doEnterASR();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void enterWakeup(boolean z) {
            try {
               doEnterWakeup(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void initEngine() {
            try {
               doInitEngine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void initializeMode() {
            try {
                doInitializeMode();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void initializeSdk() {
            try {
               doInitializeSdk();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void insertVocab(Map<String, List<String>> map, String str) {
            try {
               insertVocab0(map, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void markModeInitialized() {
            if (!attr(AbstractANTEngine.MODE_INITIALIZED).compareAndSet(false, true)) {
                throw new Error("markModeInitialized");
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void playBuffer(byte[] bArr) {
            try {
                doPlayBuffer(bArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void playTTS(String str) {
            try {
                if (TextUtils.isEmpty(str) || !str.startsWith(UserPerferenceUtil.PCM_TAG)) {
                } else {
                    String matchFind = StringUtils.matchFind(str, UserPerferenceUtil.FILE_TAG);
                    if (!TextUtils.isEmpty(matchFind)) {
                        String str2 = matchFind + UserPerferenceUtil.getPcmFileSuffix();
                        String path = FileHelper.getTTSPCMFile(str2).getPath();
                        File file = new File(path);
                        if (!file.isFile() || !file.exists()) {
                            LogMgr.d(TAG, "pcm file not exist:" + path);
                        } else {
                            str = TTS_PCM_START_TAG + FileHelper.getTTSPCMFile(str2).getPath() + AbstractANTEngine.TTS_PCM_END_TAG;
                        }
                    }
                    str = StringUtils.matchFind(str, UserPerferenceUtil.TXT_TAG);
                }
                doPlayTTS(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void setAndroidContext(Context context) {
            attr(ANDROID_CONTEXT).setIfAbsent(context);
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void setWakeupWord(List<String> list, boolean z) {
            try {
                attr(WAKEUP_WORD).set(list);
                setWakeupWord0(list, z);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void stopASR() {
            try {
               doStopASR();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void stopWakeup() {
            try {
                doStopWakeup();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void updateCustomWakeupWord(List<String> list) {
            try {
                updateWakeupWord1(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void updateWakeupWord(List<String> list) {
            try {
                attr(AbstractANTEngine.WAKEUP_WORD).set(list);
                updateWakeupWord0(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.unisound.vui.engine.ANTEngine.Unsafe
        public void write(Object obj) {
            try {
                doWrite(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected AbstractANTEngine() {
        super();
        this.unsafe = newUnsafe();
        this.pipeline = new DefaultANTPipeline(this);
        attr(MODE_INITIALIZED).set(false);
        attr(NEED_RECOVERY_WAKE_UP).set(false);
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public Context androidContext() {
        return (Context) attr(ANDROID_CONTEXT).get();
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void cancelASR() {
        this.pipeline.cancelASR();
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void cancelTTS() {
        this.pipeline.cancelTTS();
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void closeReleaseStatus() {
        try {
            doCloseReleaseStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void doCancelASR() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doCancelEngine() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doCancelTTS() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doCloseReleaseStatus() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doEnterASR() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doEnterWakeup(boolean z) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doInitEngine() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doInitializeMode() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doInitializeSdk() throws Exception;

    @Override // com.unisound.vui.engine.ANTEngine
    public void doPPTAction() {
        this.pipeline.doPPTAction();
    }

    /* access modifiers changed from: protected */
    public abstract void doPPTAction0() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doPlayBuffer(byte[] bArr) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doPlayTTS(String str) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doReleaseAudioRecord() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doStopASR() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doStopWakeup() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doWrite(Object obj) throws Exception;

    @Override // com.unisound.vui.engine.ANTEngine
    public void enableOneshot(boolean z) {
        UserPerferenceUtil.setOneshotEnable(androidContext(), z);
        this.isOneshot = z;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void enterASR() {
        this.pipeline.enterASR();
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void enterWakeup(boolean z) {
        this.pipeline.enterWakeup(z);
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public boolean getAecEnable() {
        return this.aecEnable;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public int getEngineState() {
        return this.engineState;
    }

    /* access modifiers changed from: package-private */
    public void initAecEnableState() {
        this.aecEnable = UserPerferenceUtil.getAecEnable(androidContext());
    }

    /* access modifiers changed from: package-private */
    public void initOneshotState() {
        this.isOneshot = UserPerferenceUtil.getOneshotEnable(androidContext());
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void initializeMode() {
        this.pipeline.initializeMode();
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void initializeSdk() {
        this.pipeline.initializeSdk();
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void insertVocab(Map<String, List<String>> map, String str) {
        this.pipeline.write(new VocabContent(map, str));
    }

    /* access modifiers changed from: protected */
    public abstract void insertVocab0(Map<String, List<String>> map, String str) throws Exception;

    @Override // com.unisound.vui.engine.ANTEngine
    public boolean isASR() {
        return this.engineState == 2;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public boolean isIdle() {
        return this.engineState == 0;
    }

    public boolean isInSetWakeUpWord() {
        return this.inSetWakeUpWord;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public boolean isModeInitialized() {
        return attr(MODE_INITIALIZED).get().booleanValue();
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public boolean isNeedRecoveryWakeUp() {
        return attr(NEED_RECOVERY_WAKE_UP).get().booleanValue();
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public boolean isOneshot() {
        return this.isOneshot;
    }

    public boolean isPlayBeep() {
        return this.playBeep;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public boolean isRecognition() {
        return this.engineState == 3;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public boolean isShowVolume() {
        return isASR() || (isWakeup() && this.lastPlayBeep);
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public boolean isTTSPlaying() {
        return this.isTtsPlaying;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public boolean isWakeup() {
        return this.engineState == 1;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public boolean isWakeupRecord() {
        return this.isWakeupRecord;
    }

    /* access modifiers changed from: protected */
    protected abstract AbstractUnsafe newUnsafe();

    @Override // com.unisound.vui.engine.ANTEngine
    public ANTPipeline pipeline() {
        return this.pipeline;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void playTTS(String str) {
        this.pipeline.playTTS(str);
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void releaseAudioRecord() {
        try {
            doReleaseAudioRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public final void setEngineState(int i) {
        LogMgr.d(TAG, "setState:" + i);
        if (this.engineState != i) {
            this.engineState = i;
        }
    }

    public void setInSetWakeUpWord(boolean z) {
        this.inSetWakeUpWord = z;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void setNeedRecoveryWakeUp(boolean z) {
        attr(NEED_RECOVERY_WAKE_UP).set(Boolean.valueOf(z));
    }

    public void setPlayBeep(boolean z) {
        LogMgr.d(TAG, "setPlayBeep:" + z);
        this.playBeep = z;
    }

    /* access modifiers changed from: protected */
    public void setTtsPlaying(boolean z) {
        this.isTtsPlaying = z;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void setWakeupRecord(boolean z) {
        this.isWakeupRecord = z;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void setWakeupWord(List<String> list, boolean z) {
        this.pipeline.setWakeupWord(list, z);
    }

    /* access modifiers changed from: protected */
    public abstract void setWakeupWord0(List<String> list, boolean z) throws Exception;

    @Override // com.unisound.vui.engine.ANTEngine
    public void stopASR() {
        this.pipeline.stopASR();
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void stopWakeup() {
        this.pipeline.stopWakeup();
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public Unsafe unsafe() {
        return this.unsafe;
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void updateWakeupWord(List<String> list) {
        this.pipeline.updateWakeupWord(list);
    }

    /* access modifiers changed from: protected */
    public abstract void updateWakeupWord0(List<String> list) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void updateWakeupWord1(List<String> list) throws Exception;

    @Override // com.unisound.vui.engine.ANTEngine
    public List<String> wakeupWord() {
        return (List) attr(WAKEUP_WORD).get();
    }

    @Override // com.unisound.vui.engine.ANTEngine
    public void write(Object obj) {
        this.pipeline.write(obj);
    }
}

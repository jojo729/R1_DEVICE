package com.unisound.vui.bootstrap;

import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.engine.ANTEngineInitializer;
import com.unisound.vui.engine.ANTEngineOption;
import com.unisound.vui.engine.ANTPipeline;
import com.unisound.vui.util.AttributeKey;
import com.unisound.vui.util.LogMgr;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.Map;

public class NativeBootstrap extends AbstractBootstrap<NativeBootstrap> {
    private static final String TAG = "NativeBootstrap";

    /* access modifiers changed from: private */
    public final class a extends ANTEngineInitializer {
        private a() {
        }

        /* access modifiers changed from: protected */
        @Override // com.unisound.vui.engine.ANTEngineInitializer
        public void onEngineInitDone(final ANTEngine engine) {
            engine.unsafe().markModeInitialized();
            int intValue = ((Integer) engine.config().getOption(ANTEngineOption.ASR_OPT_WAKEUP_MODEL_ID)).intValue();
            int intValue2 = ((Integer) engine.config().getOption(ANTEngineOption.ASR_OPT_RECOGNIZE_MODEL_ID)).intValue();
            engine.config().setOption(ANTEngineOption.ASR_OPT_WAKEUP_MODEL_ID, Integer.valueOf(intValue));
            engine.config().setOption(ANTEngineOption.ASR_OPT_RECOGNIZE_MODEL_ID, Integer.valueOf(intValue2));
            engine.pipeline().addLast(NativeBootstrap.this.initializationHandler());
            engine.unsafe().setAndroidContext(NativeBootstrap.this.androidContext());
            engine.setWakeupWord(NativeBootstrap.this.wakeupWord(), false);
            Observable.just(new DefaultLocalConfigurationProvider(NativeBootstrap.this.androidContext())).map(new Func1<DefaultLocalConfigurationProvider, ANTELocalConfiguration>() {
                /* class com.unisound.vui.bootstrap.NativeBootstrap.a.AnonymousClass2 */

                /* renamed from: a */
                public ANTELocalConfiguration call(DefaultLocalConfigurationProvider defaultLocalConfigurationProvider) {
                    defaultLocalConfigurationProvider.copyJsgfFromAssets();
                    return defaultLocalConfigurationProvider.getConfig();
                }
            }).subscribeOn(Schedulers.io()).subscribe(new Action1<ANTELocalConfiguration>() {
                /* class com.unisound.vui.bootstrap.NativeBootstrap.a.AnonymousClass1 */

                /* renamed from: a */
                public void call(ANTELocalConfiguration aNTELocalConfiguration) {
                    engine.config().setLocalConfiguration(aNTELocalConfiguration);
                    String mainTag = NativeBootstrap.this.mainTag();
                    ANTELocalConfiguration.LocalRecognition localRecognition = aNTELocalConfiguration.getLocalRecognition(mainTag);
                    localRecognition.getGrammerPath();
                    int loadCompiledJsgf = engine.unsafe().loadCompiledJsgf(mainTag, localRecognition.getCompileJsgfPath());
                    engine.unsafe().insertVocab(NativeBootstrap.this.mainVocab(), mainTag);
                    if (loadCompiledJsgf != 0) {
                        LogMgr.e(NativeBootstrap.TAG, "loadGrammar errro %s", mainTag);
                    }
                    engine.config().setMainTag(mainTag);
                    engine.config().setMainVocab(NativeBootstrap.this.mainVocab());
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.bootstrap.AbstractBootstrap
    public void init0(ANTEngine engine) {
        Map<ANTEngineOption<?>, ?> options = options();
        synchronized (NativeBootstrap.class) {
            engine.config().setOptions(options);
        }
        Map<AttributeKey<?>, Object> attrs = attrs();
        synchronized (attrs) {
            for (Map.Entry<AttributeKey<?>, Object> entry : attrs.entrySet()) {
                engine.attr(entry.getKey()).set(entry.getValue());
            }
        }
        ANTPipeline pipeline = engine.pipeline();
        if (handler() != null) {
            pipeline.addLast(handler());
        }
        pipeline.addLast(new a());
    }

    /* access modifiers changed from: package-private */
    @Override // com.unisound.vui.bootstrap.AbstractBootstrap
    public boolean isUseProxyMode() {
        return false;
    }
}

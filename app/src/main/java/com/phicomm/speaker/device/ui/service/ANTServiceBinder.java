package com.phicomm.speaker.device.ui.service;

import android.app.Application;
import android.os.Binder;
import com.phicomm.speaker.device.ui.ExampleANTEngineInitializer;
import com.unisound.vui.auth.BasicCredentials;
import com.unisound.vui.auth.UNIOSCredentials;
import com.unisound.vui.bootstrap.ANTEFactory;
import com.unisound.vui.bootstrap.DefaultANTEMainVocabProvider;
import com.unisound.vui.bootstrap.DefaultUserANTEOptionProvider;
import com.unisound.vui.bootstrap.NativeBootstrap;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.engine.ANTAudioSourceImpl;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.engine.DefaultANTBuilder;
import com.unisound.vui.engine.NativeANTEngine;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.UserPerferenceUtil;
import com.unisound.vui.util.entity.ExoAsrTag;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ANTServiceBinder extends Binder {
    private static final String TAG = ANTServiceBinder.class.getSimpleName();
    private ANTEngine antEngine;
    private ANTEFactory<ANTEngine> antEngineANTEFactory;
    private int bindCount;
    private boolean engineInited = false;

    public boolean isEngineInited() {
        return this.engineInited;
    }

    public ANTServiceBinder(Application application, ANTAudioSourceImpl antAudioSource) {
        this.antEngineANTEFactory = new NativeANTEFactory(new BasicCredentials(ExoConstants.APP_KEY, ExoConstants.APP_SECRET), application, antAudioSource);
        this.antEngine = this.antEngineANTEFactory.newANTEngine();
        initMode(application);
    }

    public ANTEngine getAntEngine() {
        return this.antEngine;
    }

    public int getBindCount() {
        return this.bindCount;
    }

    public void increaseBindCount() {
        this.bindCount++;
    }

    private void initMode(final Application application) {
        LogMgr.d(TAG, "initMode");
        Observable.create(new Observable.OnSubscribe<NativeBootstrap>() {
            /* class com.phicomm.speaker.device.ui.service.ANTServiceBinder.AnonymousClass2 */

            public void call(Subscriber<? super NativeBootstrap> subscriber) {
                NativeBootstrap bootstrap = new NativeBootstrap();
                bootstrap.options(new DefaultUserANTEOptionProvider()).antEngineFactory(ANTServiceBinder.this.antEngineANTEFactory).androidContext(application).wakeupWord(UserPerferenceUtil.getWakeupWord(application, ANTConfigPreference.isWakeupTest)).mainTag(ExoAsrTag.getMainTag(ExoAsrTag.TAG_UNIDRIVE_MAIN)).mainVocab(new DefaultANTEMainVocabProvider().getMainVocab(application)).initializationHandler(new ExampleANTEngineInitializer());
                subscriber.onNext(bootstrap);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<NativeBootstrap>() {
            /* class com.phicomm.speaker.device.ui.service.ANTServiceBinder.AnonymousClass1 */

            public void call(NativeBootstrap nativeBootstrap) {
                LogMgr.e("MainActivity", "ANTServiceBinder");
                nativeBootstrap.init();
                engineInited = true;
            }
        });
    }

    private static final class NativeANTEFactory implements ANTEFactory<ANTEngine> {
        private final ANTEngine antEngine;

        public NativeANTEFactory(UNIOSCredentials credentials, Application app, ANTAudioSourceImpl audioSource) {
            LogMgr.d(ANTServiceBinder.TAG, "NativeANTEFactory");
            if (audioSource == null) {
                UserPerferenceUtil.setAecEnable(app, ANTConfigPreference.aecEnable);
                UserPerferenceUtil.setOneshotEnable(app, ANTConfigPreference.oneshotEnable);
            }
            this.antEngine = new NativeANTEngine(new DefaultANTBuilder.Provider(app, credentials), audioSource);
        }

        @Override // com.unisound.vui.bootstrap.ANTEFactory
        public ANTEngine newANTEngine() {
            return this.antEngine;
        }
    }
}

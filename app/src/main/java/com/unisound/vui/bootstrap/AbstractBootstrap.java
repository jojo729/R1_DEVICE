package com.unisound.vui.bootstrap;

import android.content.Context;
import android.text.TextUtils;
import com.unisound.common.y;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.engine.ANTEngineOption;
import com.unisound.vui.engine.ANTHandler;
import com.unisound.vui.engine.ANTPipeline;
import com.unisound.vui.util.AttributeKey;
import com.unisound.vui.util.LogMgr;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBootstrap<B extends AbstractBootstrap<B>> {
    private static final String TAG = AbstractBootstrap.class.getSimpleName();
    private volatile ANTEFactory<? extends ANTEngine> antEngineFactory;
    private final Map<AttributeKey<?>, Object> attrs = new LinkedHashMap();
    private Context context;
    private volatile ANTHandler handler;
    private volatile ANTHandler initializationHandler;
    private ANTELocalConfiguration localConfiguration;
    private volatile String mainTag;
    private Map<String, List<String>> mainVocab;
    private final Map<ANTEngineOption<?>, Object> options = new LinkedHashMap();
    private ANTPipeline pipeline;
    private volatile List<String> wakeupWord;

    /* access modifiers changed from: package-private */
    public final Context androidContext() {
        return this.context;
    }

    public AbstractBootstrap androidContext(Context context2) {
        if (context2 == null) {
            throw new NullPointerException("context");
        }
        this.context = context2;
        return this;
    }

    public AbstractBootstrap antEngineFactory(ANTEFactory<ANTEngine> antEngineFactory2) {
        if (antEngineFactory2 == null) {
            throw new NullPointerException("antEngineFactory");
        } else if (this.antEngineFactory != null) {
            throw new IllegalStateException("antEngineFactory set already");
        } else {
            this.antEngineFactory = antEngineFactory2;
            return this;
        }
    }

    public <T> AbstractBootstrap attr(AttributeKey<T> key, T value) {
        if (key == null) {
            throw new NullPointerException("key");
        }
        if (value == null) {
            synchronized (this.attrs) {
                this.attrs.remove(key);
            }
        } else {
            synchronized (this.attrs) {
                this.attrs.put(key, value);
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public final Map<AttributeKey<?>, Object> attrs() {
        return this.attrs;
    }

    public AbstractBootstrap handler(ANTHandler handler2) {
        if (handler2 == null) {
            throw new NullPointerException("handler");
        }
        this.handler = handler2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final ANTHandler handler() {
        return this.handler;
    }

    public void init() {
        LogMgr.d(TAG, y.y);
        validate();
        ANTEngine newANTEngine = this.antEngineFactory.newANTEngine();
        try {
            this.pipeline = newANTEngine.pipeline();
            init0(newANTEngine);
            if (isUseProxyMode()) {
                newANTEngine.initializeSdk();
            } else {
                newANTEngine.initializeMode();
            }
        } catch (Exception e) {
            LogMgr.e(TAG, "init exception:" + e.toString());
            newANTEngine.unsafe().release(1401, null);
        }
    }

    /* access modifiers changed from: package-private */
    public abstract void init0(ANTEngine aNTEngine) throws Exception;

    public AbstractBootstrap initializationHandler(ANTHandler initializationHandler2) {
        if (initializationHandler2 == null) {
            throw new NullPointerException("initializationHandler");
        }
        this.initializationHandler = initializationHandler2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final ANTHandler initializationHandler() {
        return this.initializationHandler;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean isUseProxyMode();

    /* access modifiers changed from: package-private */
    public final ANTELocalConfiguration localConfiguration() {
        return this.localConfiguration;
    }

    public AbstractBootstrap localConfiguration(ANTELocalConfiguration localConfiguration2) {
        if (localConfiguration2 == null) {
            throw new NullPointerException("ant engine localConfiguration is null");
        }
        this.localConfiguration = localConfiguration2;
        return this;
    }

    public AbstractBootstrap mainTag(String mainTag2) {
        if (TextUtils.isEmpty(mainTag2)) {
            throw new NullPointerException("mainTag");
        }
        this.mainTag = mainTag2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final String mainTag() {
        return this.mainTag;
    }

    public AbstractBootstrap mainVocab(Map<String, List<String>> vocab) {
        if (vocab == null) {
            throw new NullPointerException("mainVocab");
        }
        this.mainVocab = vocab;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final Map<String, List<String>> mainVocab() {
        return this.mainVocab;
    }

    public <T> AbstractBootstrap option(ANTEngineOption<T> option, T value) {
        if (option == null) {
            throw new NullPointerException("option");
        }
        if (value == null) {
            synchronized (this.options) {
                this.options.remove(option);
            }
        } else {
            synchronized (this.options) {
                this.options.put(option, value);
            }
        }
        return this;
    }

    public AbstractBootstrap options(a provider) {
        if (provider == null) {
            throw new NullPointerException("ant engine options provider");
        }
        synchronized (this.options) {
            Map<? extends ANTEngineOption<?>, ? extends Object> options2 = provider.options();
            if (options2 == null) {
                throw new NullPointerException("The ANTEOptionsProvider.options() method can't return null value");
            }
            this.options.putAll(options2);
        }
        return this;
    }

    public AbstractBootstrap options(Map<ANTEngineOption<?>, Object> optionObjectMap) {
        synchronized (this.options) {
            if (optionObjectMap == null) {
                throw new NullPointerException("The optionObjectMap value can't be null");
            }
            this.options.putAll(optionObjectMap);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public final Map<ANTEngineOption<?>, Object> options() {
        return this.options;
    }

    public ANTPipeline pipeline() {
        return this.pipeline;
    }

    public AbstractBootstrap validate() {
        if (this.antEngineFactory == null) {
            throw new IllegalStateException("antEngineFactory not set");
        } else if (this.context == null) {
            throw new IllegalStateException("android context not set");
        } else if (this.wakeupWord == null) {
            throw new IllegalStateException("wakeup word not set");
        } else if (this.initializationHandler != null) {
            return this;
        } else {
            throw new IllegalStateException("initializationHandler not set");
        }
    }

    public AbstractBootstrap wakeupWord(List<String> wakeupWord2) {
        if (wakeupWord2 == null) {
            throw new NullPointerException("wakeupWord");
        }
        this.wakeupWord = wakeupWord2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final List<String> wakeupWord() {
        return this.wakeupWord;
    }
}

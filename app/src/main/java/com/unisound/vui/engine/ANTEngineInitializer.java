package com.unisound.vui.engine;

import com.unisound.vui.handler.ANTEventDispatcher;
import com.unisound.vui.util.LogMgr;

public abstract class ANTEngineInitializer extends ANTEventDispatcher {
    private static final String TAG = "ANTEngineInitializer";

    @Override // com.unisound.vui.engine.ANTHandlerAdapter, com.unisound.vui.engine.ANTHandler
    public void exceptionCaught(ANTHandlerContext aNTHandlerContext, Throwable th) throws Exception {
        LogMgr.e(TAG, th + ":Failed to initialize the ant engine");
        ANTPipeline pipeline = aNTHandlerContext.pipeline();
        if (pipeline.context(this) != null) {
            pipeline.remove(this);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventEngineInitDone(ANTHandlerContext aNTHandlerContext) {
        onEngineInitDone(aNTHandlerContext.engine());
        aNTHandlerContext.pipeline().remove(this);
        return false;
    }

    /* access modifiers changed from: protected */
    protected abstract void onEngineInitDone(ANTEngine aNTEngine);
}

package com.unisound.vui.handler;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.NaviConfig;
import com.unisound.vui.util.UserPerferenceUtil;
import com.unisound.vui.util.internal.e;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import logreport.FullLog;
import nluparser.scheme.LocalASR;
import nluparser.scheme.NLU;

public abstract class SimpleUserEventInboundHandler<I> extends SimpleSessionManagementHandler {
    private static final String TAG = "SimpleUserEvent";
    private static final int WAKEUP_TIMEOUT_MESSAGE = 0;
    private static Set<SimpleUserEventInboundHandler> activeHandlers = new HashSet();
    protected ANTHandlerContext ctx;
    protected int errorTime;
    protected boolean eventReceived;
    private Handler handler;
    private final e matcher;
    protected boolean playCancelTTS;
    private int priority;
    protected String sessionName;
    protected boolean shouldResume;

    protected SimpleUserEventInboundHandler() {
        this.playCancelTTS = false;
        this.shouldResume = false;
        this.matcher = e.a(this, SimpleUserEventInboundHandler.class, "I");
        initPriority();
        initHandler();
    }

    protected SimpleUserEventInboundHandler(Class<? extends I> parserResultType) {
        this.playCancelTTS = false;
        this.shouldResume = false;
        this.matcher = e.a((Class<?>) parserResultType);
        initPriority();
        initHandler();
    }

    public static boolean hasActiveHandlers() {
        if (activeHandlers == null || activeHandlers.size() <= 0) {
            return false;
        }
        Iterator<SimpleUserEventInboundHandler> it = activeHandlers.iterator();
        while (it.hasNext()) {
            LogMgr.d(TAG, "active handler:" + it.next().getClass().getSimpleName());
        }
        return true;
    }

    public boolean acceptInboundEvent(Object evt) throws Exception {
        return this.matcher.a(evt);
    }

    /* access modifiers changed from: protected */
    public boolean acceptInboundEvent0(I i) throws Exception {
        return false;
    }

    public void cancelAsrEventTriggered(LocalASR localASR, ANTHandlerContext ctx2) {
    }

    /* access modifiers changed from: protected */
    public void eventReceived(I i, ANTHandlerContext ctx2) throws Exception {
        setDomainWakeUpWordList(ctx2);
    }

    /* access modifiers changed from: protected */
    public int getErrorTime() {
        return this.errorTime;
    }

    /* access modifiers changed from: protected */
    public String getInterruptType(I i) {
        return ExoConstants.DO_ACTIVE_INTERRUPT;
    }

    public int getPriority() {
        return this.priority;
    }

    /* access modifiers changed from: protected */
    public void increaseErrorTime() {
        this.errorTime++;
    }

    /* access modifiers changed from: protected */
    public void initHandler() {
        this.handler = new Handler() {
            /* class com.unisound.vui.handler.SimpleUserEventInboundHandler.AnonymousClass1 */

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        SimpleUserEventInboundHandler.this.onWakeupTimeOut();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public abstract void initPriority();

    public boolean localAsrEventTriggered(LocalASR localASR, ANTHandlerContext ctx2) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void mark(boolean eventReceived2) {
        LogMgr.d(TAG, "mark true,hanler:" + getClass().getSimpleName());
        if (!this.eventReceived) {
            this.eventReceived = eventReceived2;
            activeHandlers.add(this);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventCancel(ANTHandlerContext ctx2) {
        return super.onASREventCancel(ctx2);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx2) {
        LogMgr.d(TAG, "onASREventEngineInitDone:" + getClass().getSimpleName());
        this.ctx = ctx2;
        NaviConfig.setContext(ctx2.androidContext());
        return super.onASREventEngineInitDone(ctx2);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventRecordingStart(ANTHandlerContext ctx2) {
        return super.onASREventRecordingStart(ctx2);
    }

    /* access modifiers changed from: protected */
    public void onDestroy(ANTHandlerContext ctx2) {
    }

    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onTTSError(int type, String error, ANTHandlerContext ctx2) throws Exception {
        if (this.eventReceived) {
            reset();
            ctx2.enterWakeup(false);
        }
        ctx2.fireTTSError(type, error);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx2) {
        LogMgr.d(TAG, "onTTSEventPlayingEnd:" + getClass().getSimpleName());
        return super.onTTSEventPlayingEnd(ctx2);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onWakeupEventSetWakeupwordDone(ANTHandlerContext ctx2) {
        LogMgr.d(TAG, "onWakeupEventSetWakeupwordDone:" + getClass().getSimpleName());
        return super.onWakeupEventSetWakeupwordDone(ctx2);
    }

    /* access modifiers changed from: protected */
    public void onWakeupTimeOut() {
        LogMgr.i(TAG, "-onWakeupTimeOut-2");
    }

    /* access modifiers changed from: protected */
    public void removeTimeoutMessage() {
        if (this.handler.hasMessages(0)) {
            this.handler.removeMessages(0);
        }
    }

    /* access modifiers changed from: protected */
    public void reset() {
        LogMgr.d(TAG, "reset,hanler:" + getClass().getSimpleName());
        if (this.eventReceived) {
            this.eventReceived = false;
            activeHandlers.remove(this);
        }
        resetErrorTime();
    }

    /* access modifiers changed from: protected */
    public void resetErrorTime() {
        LogMgr.d(TAG, "-resetErrorTime-");
        this.errorTime = 0;
    }

    public void sendFullLogToDeviceCenter(NLU evt, String ttsData) {
        this.ctx.pipeline().fireUserEventTriggered(new FullLog(evt, ttsData));
    }

    public void sendTimeoutMessage(int time) {
        this.handler.removeMessages(0);
        this.handler.sendEmptyMessageDelayed(0, (long) time);
    }

    /* access modifiers changed from: protected */
    public void setDomainWakeUpWordList(ANTHandlerContext ctx2) {
        ctx2.setWakeupWord(UserPerferenceUtil.getWakeupWord(ctx2.androidContext()), false);
    }

    /* access modifiers changed from: protected */
    public void setPriority(int priority2) {
        this.priority = priority2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void userEventTriggered(Object evt, ANTHandlerContext ctx2) throws Exception {
        if (!acceptInboundEvent(evt) || !acceptInboundEvent0((I) evt)) {
            if (evt instanceof ExoConstants.a) {
                onDestroy(ctx2);
            } else if (!(evt instanceof LocalASR)) {
                manageState(evt, ctx2);
            } else if (UserPerferenceUtil.getGlobalCancelWakeupWord(ctx2.androidContext()).contains(((LocalASR) evt).getRecognitionResult())) {
                cancelAsrEventTriggered((LocalASR) evt, ctx2);
            } else if (localAsrEventTriggered((LocalASR) evt, ctx2)) {
                return;
            }
            ctx2.fireUserEventTriggered(evt);
            return;
        }
        if (!this.eventReceived) {
            ctx2.cancelTTS();
            ctx2.cancelEngine();
            String interruptType = getInterruptType((I) evt);
            if (!TextUtils.isEmpty(interruptType)) {
                ctx2.pipeline().fireUserEventTriggered(interruptType);
            }
            mark(true);
        }
        eventReceived((I) evt, ctx2);
    }
}

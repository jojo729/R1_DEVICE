package com.unisound.vui.handler.launch;

import android.content.Context;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.common.file.AudioFileHelper;
import com.unisound.vui.engine.ANTEngineOption;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SessionRegister;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.LogMgr;
import nluparser.scheme.NLU;
import nluparser.scheme.SName;

public class DefaultANTLaunchHandler extends SimpleUserEventInboundHandler<NLU> {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f427a = new Boolean(false);
    private static boolean b = false;
    private static Boolean d = new Boolean(false);
    private static Boolean e = new Boolean(false);
    private static int f = ANTConfigPreference.asrMaxDuration;
    private final Object c = new Object();
    private boolean g = false;
    private ANTHandlerContext h;
    private Context context;
    private int j = -1;
    private long k = 0;
    private int l = 0;
    private AudioFileHelper m;

    public DefaultANTLaunchHandler(Context context) {
        this.sessionName = SessionRegister.SESSION_ANT_LAUNCH;
        this.context = context;
        this.m = new AudioFileHelper(context);
        a();
    }

    private void a() {
        this.m.loadWakeupAudio();
    }

    private void a(int i2) {
        this.j = i2;
    }

    private void b() {
        LogMgr.d("DefaultANTLaunchHandler", "playWakeupTips");
        this.h.engine().unsafe().setTTSOption(2004, "22050");
        byte[] randomWakeUpTips = this.m.getRandomWakeUpTips();
        if (randomWakeUpTips == null) {
            this.h.playTTS("<WAV>" + this.m.getRandomWakeUpFilePath() + "</WAV>");
        } else {
            this.h.playBuffer(randomWakeUpTips);
        }
    }

    private void c() {
        this.j = -1;
    }

    /* renamed from: a */
    @Override
    public boolean acceptInboundEvent0(NLU nlu) throws Exception {
        return SName.ANT_LAUNCH.equals(nlu.getService());
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx, String interruptType) {
        LogMgr.d("DefaultANTLaunchHandler", "doInterrupt eventReceived:" + this.eventReceived);
        if (this.eventReceived) {
            b = false;
            ctx.cancelTTS();
            this.l = 0;
            c();
            reset();
        }
    }

    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.engine.ANTOutboundHandler
    public void doPPTAction(ANTHandlerContext ctx) throws Exception {
        LogMgr.d("DefaultANTLaunchHandler", " doPPTAction ttsInited=" + e + "engine inited" + d);
    }

    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.engine.ANTOutboundHandler
    public void enterASR(ANTHandlerContext ctx) throws Exception {
        super.enterASR(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(201);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventCancel(ANTHandlerContext ctx) {
        LogMgr.d("DefaultANTLaunchHandler", "onASREventCancel startRecognize:0");
        this.k = 0;
        return super.onASREventCancel(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        LogMgr.d("DefaultANTLaunchHandler", "onASREventEngineInitDone");
        this.h = ctx;
        d = true;
        if (2 == ((Integer) ctx.engine().config().getOption(ANTEngineOption.TTS_SERVICE_MODE)).intValue()) {
            e = true;
        } else {
            e = false;
        }
        LogMgr.d("DefaultANTLaunchHandler", " onASREventEngineInitDone ttsEngine ttsInited=" + e);
        synchronized (this.c) {
            if (e.booleanValue() && !f427a.booleanValue()) {
                LogMgr.d("DefaultANTLaunchHandler", "onASREventEngineInitDone enter wakeup");
                f427a = true;
                ctx.enterWakeup(false);
                fireEngineInitDone(ctx);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventLoadGrammarDone(ANTHandlerContext ctx) {
        LogMgr.d("DefaultANTLaunchHandler", "onASREventLoadGrammarDone");
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventOneshotVadTimeout(ANTHandlerContext ctx) {
        LogMgr.d("DefaultANTLaunchHandler", "onASREventOneshotVadTimeout");
        ctx.cancelEngine();
        ctx.enterASR();
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventRecognitionEnd(ANTHandlerContext ctx) {
        LogMgr.d("DefaultANTLaunchHandler", "onASREventRecognitionEnd:0");
        this.k = 0;
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventRecordingStart(ANTHandlerContext ctx) {
        if (!ctx.engine().isOneshot() && ctx.engine().isASR()) {
            this.k = System.currentTimeMillis();
            LogMgr.d("DefaultANTLaunchHandler", "onASREventRecordingStart startRecognize:" + this.k);
        }
        return super.onASREventRecordingStart(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventRecordingStop(ANTHandlerContext ctx) {
        LogMgr.d("DefaultANTLaunchHandler", "onASREventRecordingStop startRecognize:0");
        this.k = 0;
        return super.onASREventRecordingStop(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventVadTimeout(ANTHandlerContext ctx) {
        LogMgr.d("DefaultANTLaunchHandler", "onASREventVadTimeout");
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventVolumeChange(ANTHandlerContext ctx, int volume) {
        if (!ctx.engine().isASR() || this.k <= 0 || System.currentTimeMillis() - this.k <= ((long) f)) {
            return false;
        }
        LogMgr.d("DefaultANTLaunchHandler", "onASREventVolumeChange stopAsr");
        ctx.stopASR();
        return false;
    }

    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onTTSEvent(int type, ANTHandlerContext ctx) throws Exception {
        LogMgr.d("DefaultANTLaunchHandler", "onTTSEvent:" + type + ";enterAsrAfterTtsPlayEnd:" + b);
        if (b && type == 2102) {
            this.l++;
            if (this.l > 1) {
                b = false;
                this.l = 0;
            }
        }
        if (!b || type != 2107) {
            super.onTTSEvent(type, ctx);
            return;
        }
        LogMgr.d("DefaultANTLaunchHandler", "onTTSEvent doPPTAction");
        b = false;
        this.l = 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventInit(ANTHandlerContext ctx) {
        e = true;
        LogMgr.d("DefaultANTLaunchHandler", " onTTSEventInit engineInited=" + d);
        synchronized (this.c) {
            if (d.booleanValue() && !f427a.booleanValue()) {
                LogMgr.d("DefaultANTLaunchHandler", "onTTSEventInit enter wakeup");
                f427a = true;
                ctx.enterWakeup(false);
                fireEngineInitDone(ctx);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        LogMgr.d("DefaultANTLaunchHandler", "onTTSEventPlayingEnd state : " + this.j);
        ctx.engine().unsafe().setTTSOption(2004, "16000");
        switch (this.j) {
            case 1:
                if (this.g) {
                    LogMgr.d("AutoStart", "enterASR");
                    this.g = false;
                    ctx.enterASR();
                } else {
                    LogMgr.d("AutoStart", "enterWakeup");
                    reset();
                }
                c();
                return true;
            case 2:
                ctx.enterASR();
                c();
                return true;
            case 3:
                ctx.engine().setWakeupRecord(true);
                c();
                return false;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onWakeupResult(ANTHandlerContext ctx, String result) {
        this.k = System.currentTimeMillis();
        ctx.fireUserEventTriggered(ExoConstants.DO_ASR_INTERRUPT);
        mark(true);
        a(2);
        b();
        ctx.cancelEngine();
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void reset() {
        this.h.engine().config().setOption(ANTEngineOption.NLU_SCENARIO, "musicDefault");
        super.reset();
    }

    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void userEventTriggered(Object evt, ANTHandlerContext ctx) throws Exception {
        LogMgr.d("DefaultANTLaunchHandler", "AntLaunchHandler : userEventTriggered---" + evt);
        if (evt != null && (evt instanceof String)) {
            if (ExoConstants.SWITCH_TTS_PLAYER.equals(evt)) {
                a();
                return;
            }
            if (ExoConstants.DO_ENTER_ASR_BY_MIC.equals(evt)) {
                if (!this.eventReceived) {
                    ctx.pipeline().fireUserEventTriggered(ExoConstants.DO_ASR_INTERRUPT);
                    mark(true);
                }
                c();
                ctx.cancelTTS();
                ctx.enterASR();
            }
            if (ExoConstants.DO_PTT_ACTION_BY_MIC.equals(evt)) {
                doPPTAction(ctx);
            }
        }
        super.userEventTriggered(evt, ctx);
    }
}

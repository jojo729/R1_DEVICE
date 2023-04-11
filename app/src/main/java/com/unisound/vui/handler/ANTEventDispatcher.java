package com.unisound.vui.handler;

import com.unisound.vui.engine.ANTEngineOption;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.engine.ANTInboundHandlerAdapter;
import com.unisound.vui.engine.EventType;
import nluparser.scheme.LocalASR;

public abstract class ANTEventDispatcher extends ANTInboundHandlerAdapter {
    @Override // com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onASRError(int type, String error, ANTHandlerContext ctx) throws Exception {
        if (!onASRError(ctx, error)) {
            ctx.fireASRError(type, error);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onASRError(ANTHandlerContext ctx, String error) {
        return false;
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onASREvent(int type, ANTHandlerContext ctx) throws Exception {
        boolean onWakeupEventUpdateWakeupWordFail;
        switch (type) {
            case 1101:
                onWakeupEventUpdateWakeupWordFail = onASREventRecordingStart(ctx);
                break;
            case 1102:
                onWakeupEventUpdateWakeupWordFail = onASREventRecordingStop(ctx);
                break;
            case 1103:
                onWakeupEventUpdateWakeupWordFail = onASREventVadTimeout(ctx);
                break;
            case 1104:
                onWakeupEventUpdateWakeupWordFail = onASREventSpeechDetected(ctx);
                break;
            case 1105:
                onWakeupEventUpdateWakeupWordFail = onASREventSpeechEnd(ctx);
                break;
            case 1107:
                onWakeupEventUpdateWakeupWordFail = onASREventRecognitionEnd(ctx);
                break;
            case 1108:
                onWakeupEventUpdateWakeupWordFail = onASREventUserdataUploaded(ctx);
                break;
            case 1109:
                onWakeupEventUpdateWakeupWordFail = onASREventGrammarCompiled(ctx);
                break;
            case 1110:
                onWakeupEventUpdateWakeupWordFail = onASREventGrammarLoaded(ctx);
                break;
            case 1111:
                onWakeupEventUpdateWakeupWordFail = onASREventGrammarInserted(ctx);
                break;
            case 1112:
                onWakeupEventUpdateWakeupWordFail = onASREventVocabInserted(ctx);
                break;
            case 1113:
                onWakeupEventUpdateWakeupWordFail = onASREventFxAbnormalTooLoud(ctx);
                break;
            case 1114:
                onWakeupEventUpdateWakeupWordFail = onASREventFxAbnormalTooQuiet(ctx);
                break;
            case 1115:
                onWakeupEventUpdateWakeupWordFail = onASREventFxAbnormalSnrBad(ctx);
                break;
            case 1116:
                onWakeupEventUpdateWakeupWordFail = onASREventFxAbnormalNoLeadingsilence(ctx);
                break;
            case 1117:
                onWakeupEventUpdateWakeupWordFail = onASREventCancel(ctx);
                break;
            case 1118:
                onWakeupEventUpdateWakeupWordFail = onASREventLocalEnd(ctx);
                break;
            case 1119:
                onWakeupEventUpdateWakeupWordFail = onASREventNetEnd(ctx);
                break;
            case 1120:
                onWakeupEventUpdateWakeupWordFail = onASREventEnd(ctx);
                break;
            case 1121:
                onWakeupEventUpdateWakeupWordFail = onASRNluEventEnd(ctx);
                break;
            case 1122:
                onWakeupEventUpdateWakeupWordFail = onASREventVolumeChange(ctx, ((Integer) ctx.engine().config().getOption(ANTEngineOption.GENERAL_UPDATE_VOLUME)).intValue());
                break;
            case 1123:
                onWakeupEventUpdateWakeupWordFail = onASREventCompileDone(ctx);
                break;
            case 1129:
                onWakeupEventUpdateWakeupWordFail = onASREventEngineInitDone(ctx);
                break;
            case 1130:
                onWakeupEventUpdateWakeupWordFail = onASREventLoadGrammarDone(ctx);
                break;
            case 1131:
                onWakeupEventUpdateWakeupWordFail = onASREventRecordingPrepared(ctx);
                break;
            case 1150:
                onWakeupEventUpdateWakeupWordFail = onASREventModelLoadSuccess(ctx);
                break;
            case 1151:
                onWakeupEventUpdateWakeupWordFail = onASREventModelLoadFail(ctx);
                break;
            case EventType.ASR_EVENT_ONESHOT_VAD_TIMEOUT:
                onWakeupEventUpdateWakeupWordFail = onASREventOneshotVadTimeout(ctx);
                break;
            case 3105:
                onWakeupEventUpdateWakeupWordFail = onWakeupEventSetWakeupwordDone(ctx);
                break;
            case EventType.WAKEUP_EVENT_UPDATEWAKEUPWORD_FAIL:
                onWakeupEventUpdateWakeupWordFail = onWakeupEventUpdateWakeupWordFail(ctx);
                break;
            default:
                onWakeupEventUpdateWakeupWordFail = false;
                break;
        }
        if (!onWakeupEventUpdateWakeupWordFail) {
            ctx.fireASREvent(type);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onASREventCancel(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventCompileDone(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventEnd(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventFxAbnormalNoLeadingsilence(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventFxAbnormalSnrBad(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventFxAbnormalTooLoud(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventFxAbnormalTooQuiet(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventGrammarCompiled(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventGrammarInserted(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventGrammarLoaded(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventLoadGrammarDone(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventLocalEnd(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventModelLoadFail(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventModelLoadSuccess(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventNetEnd(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventOneshotVadTimeout(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventRecognitionEnd(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventRecordingPrepared(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventRecordingStart(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventRecordingStop(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventSpeechDetected(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventSpeechEnd(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventUserdataUploaded(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventVadTimeout(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventVocabInserted(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASREventVolumeChange(ANTHandlerContext ctx, int volume) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASRNluEventEnd(ANTHandlerContext ctx) {
        return false;
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onASRResult(int type, String result, ANTHandlerContext ctx) throws Exception {
        boolean onWakeupResult;
        switch (type) {
            case 1201:
                onWakeupResult = onASRResultNet(ctx, result);
                break;
            case 1202:
                onWakeupResult = onASRResultLocal(ctx, result);
                break;
            case 1210:
                onWakeupResult = onASRResultRecognition(ctx, result);
                break;
            case 3201:
                onWakeupResult = onWakeupResult(ctx, result);
                break;
            default:
                onWakeupResult = false;
                break;
        }
        if (!onWakeupResult) {
            ctx.fireASRResult(type, result);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onASRResultLocal(ANTHandlerContext ctx, String result) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASRResultNet(ANTHandlerContext ctx, String result) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onASRResultRecognition(ANTHandlerContext ctx, String result) {
        return false;
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onNLUError(int type, String error, ANTHandlerContext ctx) throws Exception {
        ctx.fireNLUError(type, error);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onNLUEvent(int type, ANTHandlerContext ctx) throws Exception {
        ctx.fireNLUEvent(type);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onNLUResult(int type, String result, ANTHandlerContext ctx) throws Exception {
        ctx.fireNLUResult(type, result);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onTTSError(int type, String error, ANTHandlerContext ctx) throws Exception {
        ctx.fireTTSError(type, error);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onTTSEvent(int type, ANTHandlerContext ctx) throws Exception {
        boolean onTTSEventSwitchBackendModelSuccess;
        switch (type) {
            case 2101:
                onTTSEventSwitchBackendModelSuccess = onTTSEventInit(ctx);
                break;
            case 2102:
                onTTSEventSwitchBackendModelSuccess = onTTSEventSynthesizerStart(ctx);
                break;
            case 2103:
                onTTSEventSwitchBackendModelSuccess = onTTSEventSynthesizerEnd(ctx);
                break;
            case 2104:
                onTTSEventSwitchBackendModelSuccess = onTTSEventBufferBegin(ctx);
                break;
            case 2105:
                onTTSEventSwitchBackendModelSuccess = onTTSEventBufferReady(ctx);
                break;
            case 2106:
                onTTSEventSwitchBackendModelSuccess = onTTSEventPlayingStart(ctx);
                break;
            case 2107:
                onTTSEventSwitchBackendModelSuccess = onTTSEventPlayingEnd(ctx);
                break;
            case 2108:
                onTTSEventSwitchBackendModelSuccess = onTTSEventPause(ctx);
                break;
            case 2109:
                onTTSEventSwitchBackendModelSuccess = onTTSEventResume(ctx);
                break;
            case 2110:
            case 2113:
            default:
                onTTSEventSwitchBackendModelSuccess = false;
                break;
            case 2111:
                onTTSEventSwitchBackendModelSuccess = onTTSEventStop(ctx);
                break;
            case 2112:
                onTTSEventSwitchBackendModelSuccess = onTTSEventRelease(ctx);
                break;
            case 2114:
                onTTSEventSwitchBackendModelSuccess = onTTSEventSwitchBackendModelSuccess(ctx);
                break;
        }
        if (!onTTSEventSwitchBackendModelSuccess) {
            ctx.fireTTSEvent(type);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventBufferBegin(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventBufferReady(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventInit(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventPause(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventPlayingStart(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventRelease(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventResume(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventStop(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventSwitchBackendModelSuccess(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventSynthesizerEnd(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onTTSEventSynthesizerStart(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onWakeupEventSetWakeupwordDone(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onWakeupEventUpdateWakeupWordFail(ANTHandlerContext ctx) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onWakeupResult(ANTHandlerContext ctx, String result) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onWakeupResult(ANTHandlerContext ctx, LocalASR localASR) {
        return false;
    }
}

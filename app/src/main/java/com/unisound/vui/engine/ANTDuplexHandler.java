package com.unisound.vui.engine;

import com.unisound.vui.handler.ANTEventDispatcher;
import nluparser.scheme.LocalASR;

import java.util.List;

public class ANTDuplexHandler extends ANTEventDispatcher implements ANTOutboundHandler {
    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void cancelASR(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.cancelASR();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void cancelEngine(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.cancelEngine();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void cancelTTS(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.cancelTTS();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void doPPTAction(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.doPPTAction();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void enterASR(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.enterASR();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void enterWakeup(ANTHandlerContext aNTHandlerContext, boolean z) throws Exception {
        aNTHandlerContext.enterWakeup(z);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void initializeMode(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.initializeMode();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void initializeSdk(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.initializeSdk();
    }

    @Override // com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onASRError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        super.onASRError(i, str, aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASRError(ANTHandlerContext aNTHandlerContext, String str) {
        return super.onASRError(aNTHandlerContext, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventCancel(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventCancel(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventCompileDone(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventCompileDone(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventEnd(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventEnd(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventEngineInitDone(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventEngineInitDone(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventFxAbnormalNoLeadingsilence(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventFxAbnormalNoLeadingsilence(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventFxAbnormalSnrBad(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventFxAbnormalSnrBad(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventFxAbnormalTooLoud(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventFxAbnormalTooLoud(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventFxAbnormalTooQuiet(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventFxAbnormalTooQuiet(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventGrammarCompiled(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventGrammarCompiled(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventGrammarInserted(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventGrammarInserted(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventGrammarLoaded(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventGrammarLoaded(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventLoadGrammarDone(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventLoadGrammarDone(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventLocalEnd(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventLocalEnd(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventModelLoadFail(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventModelLoadFail(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventModelLoadSuccess(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventModelLoadSuccess(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventNetEnd(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventNetEnd(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventOneshotVadTimeout(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventOneshotVadTimeout(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventRecognitionEnd(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventRecognitionEnd(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventRecordingPrepared(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventRecordingPrepared(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventRecordingStart(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventRecordingStart(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventRecordingStop(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventRecordingStop(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventSpeechDetected(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventSpeechDetected(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventSpeechEnd(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventSpeechEnd(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventUserdataUploaded(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventUserdataUploaded(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventVadTimeout(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventVadTimeout(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventVocabInserted(ANTHandlerContext aNTHandlerContext) {
        return super.onASREventVocabInserted(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASREventVolumeChange(ANTHandlerContext aNTHandlerContext, int i) {
        return super.onASREventVolumeChange(aNTHandlerContext, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASRNluEventEnd(ANTHandlerContext aNTHandlerContext) {
        return super.onASRNluEventEnd(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASRResultLocal(ANTHandlerContext aNTHandlerContext, String str) {
        return super.onASRResultLocal(aNTHandlerContext, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASRResultNet(ANTHandlerContext aNTHandlerContext, String str) {
        return super.onASRResultNet(aNTHandlerContext, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onASRResultRecognition(ANTHandlerContext aNTHandlerContext, String str) {
        return super.onASRResultRecognition(aNTHandlerContext, str);
    }

    @Override // com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onNLUError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        super.onNLUError(i, str, aNTHandlerContext);
    }

    @Override // com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onNLUEvent(int i, ANTHandlerContext aNTHandlerContext) throws Exception {
        super.onNLUEvent(i, aNTHandlerContext);
    }

    @Override // com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onNLUResult(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        super.onNLUResult(i, str, aNTHandlerContext);
    }

    @Override // com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onTTSError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        super.onTTSError(i, str, aNTHandlerContext);
    }

    @Override // com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void onTTSEvent(int i, ANTHandlerContext aNTHandlerContext) throws Exception {
        super.onTTSEvent(i, aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventBufferBegin(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventBufferBegin(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventBufferReady(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventBufferReady(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventInit(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventInit(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventPause(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventPause(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventPlayingEnd(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventPlayingEnd(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventPlayingStart(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventPlayingStart(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventRelease(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventRelease(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventResume(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventResume(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventStop(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventStop(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventSwitchBackendModelSuccess(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventSwitchBackendModelSuccess(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventSynthesizerEnd(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventSynthesizerEnd(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onTTSEventSynthesizerStart(ANTHandlerContext aNTHandlerContext) {
        return super.onTTSEventSynthesizerStart(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onWakeupEventSetWakeupwordDone(ANTHandlerContext aNTHandlerContext) {
        return super.onWakeupEventSetWakeupwordDone(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onWakeupEventUpdateWakeupWordFail(ANTHandlerContext aNTHandlerContext) {
        return super.onWakeupEventUpdateWakeupWordFail(aNTHandlerContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onWakeupResult(ANTHandlerContext aNTHandlerContext, String str) {
        return super.onWakeupResult(aNTHandlerContext, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher
    public boolean onWakeupResult(ANTHandlerContext aNTHandlerContext, LocalASR localASR) {
        return super.onWakeupResult(aNTHandlerContext, localASR);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void playBuffer(ANTHandlerContext aNTHandlerContext, byte[] bArr) throws Exception {
        aNTHandlerContext.playBuffer(bArr);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void playTTS(ANTHandlerContext aNTHandlerContext, String str) throws Exception {
        aNTHandlerContext.playTTS(str);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void setWakeupWord(ANTHandlerContext aNTHandlerContext, List<String> list, boolean z) throws Exception {
        aNTHandlerContext.setWakeupWord(list, z);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void stopASR(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.stopASR();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void stopWakeup(ANTHandlerContext aNTHandlerContext) throws Exception {
        aNTHandlerContext.stopWakeup();
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void updateWakeupWord(ANTHandlerContext aNTHandlerContext, List<String> list) throws Exception {
        aNTHandlerContext.updateWakeupWord(list);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter
    public void userEventTriggered(Object obj, ANTHandlerContext aNTHandlerContext) throws Exception {
        super.userEventTriggered(obj, aNTHandlerContext);
    }

    @Override // com.unisound.vui.engine.ANTOutboundHandler
    public void write(ANTHandlerContext aNTHandlerContext, Object obj) throws Exception {
        aNTHandlerContext.write(obj);
    }
}

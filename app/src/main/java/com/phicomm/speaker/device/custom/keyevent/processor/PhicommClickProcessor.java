package com.phicomm.speaker.device.custom.keyevent.processor;

import android.content.Context;
import com.phicomm.speaker.device.custom.ipc.PhicommLightController;
import com.phicomm.speaker.device.custom.ipc.PhicommXController;
import com.phicomm.speaker.device.custom.speech.SpeechManager;
import com.phicomm.speaker.device.custom.status.PhicommDeviceStatusProcessor;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.handler.session.memo.DefaultMemoRingingHandler;

public abstract class PhicommClickProcessor implements PhicommStatusListener {
    private PhicommDeviceStatusProcessor deviceStatusProcessor = PhicommDeviceStatusProcessor.getInstance();
    protected ANTEngine mANTEngine;
    protected Context mContext;
    protected PhicommLightController mPhicommLightController;
    protected PhicommXController mPhicommXController;
    protected SpeechManager mSpeechManager;

    public PhicommClickProcessor(ANTEngine antEngine, Context context) {
        this.mANTEngine = antEngine;
        this.mContext = context;
        this.mPhicommXController = new PhicommXController(context);
        this.mSpeechManager = new SpeechManager(antEngine);
        this.mPhicommLightController = new PhicommLightController(context);
    }

    public final void onTriggered() {
        dispatchDeviceStatus(this.deviceStatusProcessor.getDeviceStatus());
    }

    /* access modifiers changed from: protected */
    public void turnOffWakeupLightsIfNeeds() {
        if (this.mANTEngine.isASR() || this.mANTEngine.isRecognition()) {
            this.mPhicommLightController.turnOffALLWakeupLight();
        }
    }

    private void dispatchDeviceStatus(int deviceStatus) {
        if (isRingingStatus()) {
            onRingingStatus();
            return;
        }
        switch (deviceStatus) {
            case 0:
                dispatchReadyStatus();
                return;
            case 1:
                dispatchMusicStatus();
                return;
            case 2:
                onNetStatus();
                return;
            case 3:
                onBlueToothStatus();
                return;
            case 4:
            default:
                return;
            case 5:
                onDormantStatus();
                return;
        }
    }

    private boolean isRingingStatus() {
        return this.mANTEngine.hasAttr(DefaultMemoRingingHandler.ALARM_PLAYING) && ((Boolean) this.mANTEngine.attr(DefaultMemoRingingHandler.ALARM_PLAYING).get()).booleanValue();
    }

    private void dispatchReadyStatus() {
        if (isRecordingStatus(this.mANTEngine)) {
            onRecordingStatus();
        } else if (isSpeechHandling(this.mANTEngine)) {
            onSpeechHandingStatus();
        } else {
            onReadyStatus();
        }
    }

    private void dispatchMusicStatus() {
        onMusicStatus();
    }

    private boolean isRecordingStatus(ANTEngine mANTEngine2) {
        return mANTEngine2.isASR();
    }

    private boolean isSpeechHandling(ANTEngine mANTEngine2) {
        return mANTEngine2.isRecognition() || mANTEngine2.isTTSPlaying();
    }
}

package com.phicomm.speaker.device.custom.keyevent.processor;

import android.content.Context;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.outputevents.BluetoothOutputEvent;
import com.phicomm.speaker.device.custom.outputevents.DormantMessageEvent;
import com.phicomm.speaker.device.utils.LogUtils;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.util.UserPerferenceUtil;

public class TripleClickProcessor extends PhicommClickProcessor {
    private static final String TAG = "TripleClickProcessor";

    public TripleClickProcessor(ANTEngine engine, Context context) {
        super(engine, context);
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onMusicStatus() {
        LogUtils.d(TAG, "onMusicStatus");
        turnOffWakeupLightsIfNeeds();
        this.mSpeechManager.exitMusicDomain();
        this.mSpeechManager.playTTS(R.string.tts_open_bluetooth);
        openBluetooth();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onNetStatus() {
        LogUtils.d(TAG, "onNetStatus");
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onBlueToothStatus() {
        LogUtils.d(TAG, "onBlueToothStatus");
        turnOffWakeupLightsIfNeeds();
        this.mPhicommXController.resetBlueToothStatus();
        this.mSpeechManager.playTTS(R.string.tts_open_bluetooth);
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onSpeechHandingStatus() {
        LogUtils.d(TAG, "onSpeechHandingStatus");
        this.mPhicommLightController.turnOffALLWakeupLight();
        this.mPhicommLightController.turnOffLoadingLight();
        this.mSpeechManager.playTTS(R.string.tts_open_bluetooth);
        this.mSpeechManager.cancelRecognize();
        openBluetooth();
        this.mSpeechManager.startWakeup();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onRecordingStatus() {
        LogUtils.d(TAG, "onRecordingStatus");
        onSpeechHandingStatus();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onReadyStatus() {
        LogUtils.d(TAG, "onReadyStatus");
        this.mSpeechManager.playTTS(R.string.tts_open_bluetooth);
        openBluetooth();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onDormantStatus() {
        LogUtils.d(TAG, "onDormantStatus");
        this.mSpeechManager.playTTS(R.string.tts_open_bluetooth);
        openBluetooth();
        this.mPhicommLightController.turnOffDormantLight();
        UserPerferenceUtil.setStartWakeupAfterSetWakeupWord(this.mContext, true);
        this.mSpeechManager.startWakeup();
        this.mANTEngine.pipeline().write(new DormantMessageEvent(false));
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onRingingStatus() {
        LogUtils.d(TAG, "onRingingStatus");
        this.mSpeechManager.exitMusicDomain();
        this.mSpeechManager.stopRinging();
        this.mSpeechManager.playTTS(R.string.tts_open_bluetooth);
        openBluetooth();
    }

    private void openBluetooth() {
        this.mANTEngine.pipeline().write(new BluetoothOutputEvent(true));
        LogUtils.d(TAG, "openBluetooth");
        this.mPhicommXController.openBlueToothStatus();
    }
}

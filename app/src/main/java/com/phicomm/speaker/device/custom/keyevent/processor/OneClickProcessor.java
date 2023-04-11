package com.phicomm.speaker.device.custom.keyevent.processor;

import android.content.Context;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.ipc.PhicommXController;
import com.phicomm.speaker.device.custom.outputevents.BluetoothOutputEvent;
import com.phicomm.speaker.device.custom.outputevents.DormantMessageEvent;
import com.phicomm.speaker.device.custom.outputevents.DormantOutputEvent;
import com.phicomm.speaker.device.custom.outputevents.NetworkConfigOutputEvent;
import com.phicomm.speaker.device.utils.LogUtils;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.handler.session.music.outputevents.MusicOutputEvents;

public class OneClickProcessor extends PhicommClickProcessor {
    private static final String TAG = "OneClickProcessor";

    OneClickProcessor(ANTEngine engine, Context context) {
        super(engine, context);
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onMusicStatus() {
        LogUtils.d(TAG, "onMusicStatus");
        this.mANTEngine.pipeline().write(new MusicOutputEvents(false));
        this.mPhicommLightController.turnOffALLWakeupLight();
        this.mPhicommLightController.turnOffLoadingLight();
        this.mSpeechManager.exitMusicDomain();
        this.mSpeechManager.startWakeup();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onNetStatus() {
        LogUtils.d(TAG, "onNetStatus");
        this.mANTEngine.pipeline().write(new NetworkConfigOutputEvent(false));
        this.mPhicommXController.closeConnectNetStatus();
        this.mSpeechManager.startWakeup();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onBlueToothStatus() {
        this.mANTEngine.pipeline().write(new BluetoothOutputEvent(false));
        LogUtils.d(TAG, "onBlueToothStatus");
        turnOffWakeupLightsIfNeeds();
        this.mPhicommXController.closeBlueToothStatus();
        this.mSpeechManager.startWakeup();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onSpeechHandingStatus() {
        LogUtils.d(TAG, "onSpeechHandingStatus");
        this.mPhicommLightController.turnOffALLWakeupLight();
        this.mPhicommLightController.turnOffLoadingLight();
        this.mSpeechManager.cancelAll();
        this.mSpeechManager.startWakeup();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onRecordingStatus() {
        LogUtils.d(TAG, "onRecordingStatus");
        this.mSpeechManager.cancelAll();
        this.mPhicommLightController.turnOffALLWakeupLight();
        this.mSpeechManager.doWakeupSuccess();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onReadyStatus() {
        LogUtils.d(TAG, "onReadyStatus");
        this.mSpeechManager.doWakeupSuccess();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onDormantStatus() {
        LogUtils.d(TAG, "onDormantStatus");
        this.mANTEngine.pipeline().write(new DormantOutputEvent(false));
        this.mPhicommLightController.turnOffDormantLight();
        this.mPhicommXController.syncDeviceStatus(PhicommXController.DeviceStatus.Speech);
        this.mSpeechManager.playTTS(R.string.tts_stop_dormant);
        this.mSpeechManager.startWakeup();
        this.mANTEngine.pipeline().write(new DormantMessageEvent(false));
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onRingingStatus() {
        LogUtils.d(TAG, "onRingingStatus");
        this.mSpeechManager.stopRinging();
    }
}

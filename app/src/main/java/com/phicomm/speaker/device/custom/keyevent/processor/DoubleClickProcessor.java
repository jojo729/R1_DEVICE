package com.phicomm.speaker.device.custom.keyevent.processor;

import android.content.Context;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.ipc.PhicommXController;
import com.phicomm.speaker.device.custom.outputevents.DormantMessageEvent;
import com.phicomm.speaker.device.custom.outputevents.DormantOutputEvent;
import com.phicomm.speaker.device.utils.LogUtils;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.util.UserPerferenceUtil;

public class DoubleClickProcessor extends PhicommClickProcessor {
    private static final String TAG = "DoubleClickProcessor";

    public DoubleClickProcessor(ANTEngine engine, Context context) {
        super(engine, context);
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onMusicStatus() {
        this.mANTEngine.pipeline().write(new DormantOutputEvent(true));
        LogUtils.d(TAG, "onMusicStatus");
        turnOffWakeupLightsIfNeeds();
        this.mSpeechManager.exitMusicDomain();
        this.mSpeechManager.playTTS(R.string.tts_start_dormant);
        stopWakeup();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onNetStatus() {
        LogUtils.d(TAG, "onNetStatus");
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onBlueToothStatus() {
        this.mANTEngine.pipeline().write(new DormantOutputEvent(true));
        LogUtils.d(TAG, "onBlueToothStatus");
        turnOffWakeupLightsIfNeeds();
        this.mPhicommXController.closeBlueToothStatus();
        this.mSpeechManager.playTTS(R.string.tts_start_dormant);
        stopWakeup();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onSpeechHandingStatus() {
        this.mANTEngine.pipeline().write(new DormantOutputEvent(true));
        LogUtils.d(TAG, "onSpeechHandingStatus");
        this.mSpeechManager.stopSession();
        this.mPhicommLightController.turnOffALLWakeupLight();
        this.mPhicommLightController.turnOffLoadingLight();
        this.mSpeechManager.playTTS(R.string.tts_start_dormant);
        this.mSpeechManager.cancelRecognize();
        stopWakeup();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onRecordingStatus() {
        LogUtils.d(TAG, "onRecordingStatus");
        onSpeechHandingStatus();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onReadyStatus() {
        this.mANTEngine.pipeline().write(new DormantOutputEvent(true));
        LogUtils.d(TAG, "onReadyStatus");
        this.mSpeechManager.playTTS(R.string.tts_start_dormant);
        stopWakeup();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onDormantStatus() {
        LogUtils.d(TAG, "onDormantStatus");
    }

    private void stopWakeup() {
        LogUtils.d(TAG, "stop wakeup");
        UserPerferenceUtil.setStartWakeupAfterSetWakeupWord(this.mContext, false);
        this.mPhicommLightController.turnOnDormantLight();
        this.mPhicommXController.syncDeviceStatus(PhicommXController.DeviceStatus.Dormant);
        this.mSpeechManager.stopWakeup();
        this.mANTEngine.pipeline().write(new DormantMessageEvent(true));
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onRingingStatus() {
        this.mANTEngine.pipeline().write(new DormantOutputEvent(true));
        LogUtils.d(TAG, "onRingingStatus");
        this.mSpeechManager.exitMusicDomain();
        this.mSpeechManager.stopRinging();
        this.mSpeechManager.playTTS(R.string.tts_start_dormant);
        stopWakeup();
    }
}

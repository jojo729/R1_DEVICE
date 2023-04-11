package com.phicomm.speaker.device.custom.keyevent.processor;

import android.content.Context;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.outputevents.DormantMessageEvent;
import com.phicomm.speaker.device.custom.outputevents.NetworkConfigOutputEvent;
import com.phicomm.speaker.device.utils.LogUtils;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.util.UserPerferenceUtil;

public class LongClickProcessor extends PhicommClickProcessor {
    private static final String TAG = "LongClickProcessor";

    public LongClickProcessor(ANTEngine engine, Context context) {
        super(engine, context);
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onMusicStatus() {
        LogUtils.d(TAG, "onMusicStatus");
        turnOffWakeupLightsIfNeeds();
        this.mSpeechManager.exitMusicDomain();
        this.mSpeechManager.playTTS(R.string.tts_start_match_net);
        startConnectNet();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onNetStatus() {
        LogUtils.d(TAG, "onNetStatus");
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onBlueToothStatus() {
        LogUtils.d(TAG, "onBlueToothStatus");
        turnOffWakeupLightsIfNeeds();
        this.mSpeechManager.playTTS(R.string.tts_start_match_net);
        this.mPhicommXController.closeBlueToothStatus();
        startConnectNet();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onSpeechHandingStatus() {
        LogUtils.d(TAG, "onSpeechHandingStatus");
        this.mPhicommLightController.turnOffALLWakeupLight();
        this.mPhicommLightController.turnOffLoadingLight();
        this.mSpeechManager.playTTS(R.string.tts_start_match_net);
        this.mSpeechManager.cancelRecognize();
        startConnectNet();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onRecordingStatus() {
        LogUtils.d(TAG, "onRecordingStatus");
        onSpeechHandingStatus();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onReadyStatus() {
        LogUtils.d(TAG, "onReadyStatus");
        this.mSpeechManager.playTTS(R.string.tts_start_match_net);
        startConnectNet();
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onDormantStatus() {
        LogUtils.d(TAG, "onDormantStatus");
        this.mSpeechManager.playTTS(R.string.tts_start_match_net);
        startConnectNet();
        this.mANTEngine.pipeline().write(new DormantMessageEvent(false));
    }

    @Override // com.phicomm.speaker.device.custom.keyevent.processor.PhicommStatusListener
    public void onRingingStatus() {
        LogUtils.d(TAG, "onRingingStatus");
        this.mSpeechManager.exitMusicDomain();
        this.mSpeechManager.stopRinging();
        this.mSpeechManager.playTTS(R.string.tts_start_match_net);
        startConnectNet();
    }

    private void startConnectNet() {
        LogUtils.d(TAG, "startConnectNet");
        this.mANTEngine.pipeline().write(new NetworkConfigOutputEvent(true));
        UserPerferenceUtil.setStartWakeupAfterSetWakeupWord(this.mContext, false);
        this.mPhicommXController.openConnectNetStatus();
        this.mSpeechManager.stopWakeup();
    }
}

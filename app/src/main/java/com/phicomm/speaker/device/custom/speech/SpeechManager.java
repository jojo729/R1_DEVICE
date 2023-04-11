package com.phicomm.speaker.device.custom.speech;

import com.phicomm.speaker.device.custom.tts.PhicommEvent;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.util.ExoConstants;

public class SpeechManager {
    private ANTEngine mANTEngine;

    public SpeechManager(ANTEngine engine) {
        this.mANTEngine = engine;
    }

    public void startWakeup() {
        this.mANTEngine.enterWakeup(false);
    }

    public void stopWakeup() {
        this.mANTEngine.stopWakeup();
    }

    public void startRecognize() {
        this.mANTEngine.enterASR();
    }

    public void cancelRecognize() {
        this.mANTEngine.cancelASR();
    }

    public void playTTS(int ttsID) {
        if (this.mANTEngine.androidContext() != null) {
            this.mANTEngine.write(new PhicommEvent("playTTS", this.mANTEngine.androidContext().getString(ttsID)));
        }
    }

    public void cancelTTS() {
        this.mANTEngine.cancelTTS();
    }

    public void cancelAll() {
        this.mANTEngine.cancelASR();
        this.mANTEngine.cancelTTS();
    }

    public void doWakeupSuccess() {
        this.mANTEngine.pipeline().fireASRResult(3201, "{\"local_asr\":[{\"engine_mode\":\"wakeup\",\"result_type\":\"full\",\"recognition_result\":\"  小讯小讯   \",\"score\":6.08,\"utteranceTime\":1230,\"outRecordingTime\":212210,\"delayTime\":280}]}");
    }

    public void exitMusicDomain() {
        this.mANTEngine.pipeline().fireUserEventTriggered(ExoConstants.DO_FINISH_ALL_INTERRUPT);
    }

    public void stopSession() {
        this.mANTEngine.pipeline().fireUserEventTriggered(ExoConstants.DO_ACTIVE_INTERRUPT);
    }

    public void stopRinging() {
        doWakeupSuccess();
    }
}

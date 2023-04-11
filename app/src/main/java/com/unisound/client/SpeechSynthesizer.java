package com.unisound.client;

import android.content.Context;
import com.unisound.sdk.az;

public class SpeechSynthesizer extends az {
    public SpeechSynthesizer(Context context, String str, String str2) {
        super(context, str, str2);
    }

    @Override // com.unisound.sdk.az
    public int cancel() {
        return super.cancel();
    }

    @Override // com.unisound.sdk.az
    public Object getOption(int i) {
        return super.getOption(i);
    }

    @Override // com.unisound.sdk.az
    public int getStatus() {
        return super.getStatus();
    }

    @Override // com.unisound.sdk.az
    public String getVersion() {
        return super.getVersion();
    }

    @Override // com.unisound.sdk.az
    public int init(String str) {
        return super.init(str);
    }

    @Override // com.unisound.sdk.az
    public void pause() {
        super.pause();
    }

    @Override // com.unisound.sdk.az
    public void playBuffer(byte[] bArr) {
        super.playBuffer(bArr);
    }

    @Override // com.unisound.sdk.az
    public void playSynWav() {
        super.playSynWav();
    }

    @Override // com.unisound.sdk.az
    public int playText(String str) {
        return super.playText(str);
    }

    @Override // com.unisound.sdk.az
    public int release(int i, String str) {
        return super.release(i, str);
    }

    @Override // com.unisound.sdk.az
    public void resume() {
        super.resume();
    }

    @Override // com.unisound.sdk.az
    public int setAudioSource(IAudioSource iAudioSource) {
        return super.setAudioSource(iAudioSource);
    }

    @Override // com.unisound.sdk.az
    public void setOption(int i, Object obj) {
        super.setOption(i, obj);
    }

    @Override // com.unisound.sdk.az
    public void setTTSListener(SpeechSynthesizerListener speechSynthesizerListener) {
        super.setTTSListener(speechSynthesizerListener);
    }

    @Override // com.unisound.sdk.az
    public void stop() {
        super.stop();
    }

    @Override // com.unisound.sdk.az
    public void synthesizeText(String str) {
        super.synthesizeText(str);
    }
}

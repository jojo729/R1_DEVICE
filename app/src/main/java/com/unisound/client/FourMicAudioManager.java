package com.unisound.client;

import com.unisound.sdk.x;

public class FourMicAudioManager extends x {
    public FourMicAudioManager(FourMicAudioManagerListener fourMicAudioManagerListener) {
        super(fourMicAudioManagerListener);
    }

    @Override // com.unisound.sdk.x
    public void setOption(int i, Object obj) {
        super.setOption(i, obj);
    }

    @Override // com.unisound.sdk.x
    public void startRecord() {
        super.startRecord();
    }

    @Override // com.unisound.sdk.x
    public void stopRecord() {
        super.stopRecord();
    }
}

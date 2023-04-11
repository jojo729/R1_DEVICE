package com.phicomm.speaker.device.custom.tts;

public class PhicommEvent {
    private String mTtsContent;
    private String mType;

    public PhicommEvent(String type, String ttsContent) {
        this.mType = type;
        this.mTtsContent = ttsContent;
    }

    public String getType() {
        return this.mType;
    }

    public String getTtsContent() {
        return this.mTtsContent;
    }
}

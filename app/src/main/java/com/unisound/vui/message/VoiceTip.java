package com.unisound.vui.message;

public class VoiceTip {
    private String content;
    private boolean isShowLoading;
    private String sessionName;
    private String tip;
    private VoiceType voiceType;

    public String getContent() {
        return this.content;
    }

    public String getSessionName() {
        return this.sessionName;
    }

    public String getTip() {
        return this.tip;
    }

    public VoiceType getVoiceType() {
        return this.voiceType;
    }

    public boolean isShowLoading() {
        return this.isShowLoading;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setSessionName(String str) {
        this.sessionName = str;
    }

    public void setShowLoading(boolean z) {
        this.isShowLoading = z;
    }

    public void setTip(String str) {
        this.tip = str;
    }

    public void setVoiceType(VoiceType voiceType2) {
        this.voiceType = voiceType2;
    }
}

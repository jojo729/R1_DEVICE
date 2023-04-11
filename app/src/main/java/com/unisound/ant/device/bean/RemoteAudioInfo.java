package com.unisound.ant.device.bean;

import java.util.List;
import nluparser.scheme.AudioResult;

public class RemoteAudioInfo {
    private List<AudioResult.Music> audioinfo;
    private String voiceTip;

    public String getVoiceTip() {
        return this.voiceTip;
    }

    public void setVoiceTip(String voiceTip2) {
        this.voiceTip = voiceTip2;
    }

    public List<AudioResult.Music> getAudioinfo() {
        return this.audioinfo;
    }

    public void setAudioinfo(List<AudioResult.Music> audioinfo2) {
        this.audioinfo = audioinfo2;
    }
}

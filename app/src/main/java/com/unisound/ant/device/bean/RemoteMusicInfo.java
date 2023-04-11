package com.unisound.ant.device.bean;

import java.util.List;
import nluparser.scheme.MusicResult;

public class RemoteMusicInfo {
    private List<MusicResult.Music> musicinfo;
    private String voiceTip;

    public List<MusicResult.Music> getMusicinfo() {
        return this.musicinfo;
    }

    public void setMusicinfo(List<MusicResult.Music> musicinfo2) {
        this.musicinfo = musicinfo2;
    }

    public String getVoiceTip() {
        return this.voiceTip;
    }

    public void setVoiceTip(String voiceTip2) {
        this.voiceTip = voiceTip2;
    }
}

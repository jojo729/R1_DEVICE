package com.unisound.ant.device.bean;

import java.util.List;

public class PhicommPlayerInfo {
    private int playIndex;
    private List<MusicItem> playList;
    private int playMode;
    private int playState;

    public int getPlayMode() {
        return this.playMode;
    }

    public void setPlayMode(int playMode2) {
        this.playMode = playMode2;
    }

    public int getPlayState() {
        return this.playState;
    }

    public void setPlayState(int playState2) {
        this.playState = playState2;
    }

    public int getPlayIndex() {
        return this.playIndex;
    }

    public void setPlayIndex(int playIndex2) {
        this.playIndex = playIndex2;
    }

    public List<MusicItem> getPlayList() {
        return this.playList;
    }

    public void setPlayList(List<MusicItem> playList2) {
        this.playList = playList2;
    }
}

package com.unisound.ant.device.bean;

import java.util.List;
import nluparser.scheme.MusicResult;

public class DeviceMusicData {
    private int index;
    private List<MusicResult.Music> musicList;

    public DeviceMusicData(int index2, List<MusicResult.Music> musicList2) {
        this.index = index2;
        this.musicList = musicList2;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index2) {
        this.index = index2;
    }

    public List<MusicResult.Music> getMusicList() {
        return this.musicList;
    }

    public void setMusicList(List<MusicResult.Music> musicList2) {
        this.musicList = musicList2;
    }
}

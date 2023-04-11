package com.unisound.vui.handler.session.music.syncloud;

import com.unisound.ant.device.bean.MusicData;

public class MusicStatusUtils {
    public static MusicData packageDataAllInfo(String playStatus, String playMode) {
        MusicData data = new MusicData();
        data.setControlCmd("play");
        data.setPlayState(playStatus);
        data.setPlayMode(playMode);
        return data;
    }

    public static MusicData packageDataPlayStatusPause() {
        MusicData data = new MusicData();
        data.setPlayState("pause");
        data.setControlCmd("pause");
        return data;
    }

    public static MusicData packageDataPlayStatusStop() {
        MusicData data = new MusicData();
        data.setPlayState("stop");
        data.setControlCmd(MusicData.CMD_EXIT);
        return data;
    }

    public static MusicData packageDataPlayMode(String playMode) {
        MusicData data = new MusicData();
        data.setControlCmd("changeMode");
        data.setPlayMode(playMode);
        return data;
    }

    public static MusicData packageDataCollectionStatus(boolean isCollected) {
        MusicData data = new MusicData();
        data.setControlCmd(isCollected ? "collect" : "cancelCollect");
        return data;
    }

    public static MusicData packageDataVolume(int volume) {
        MusicData data = new MusicData();
        data.setControlCmd("changeVolume");
        data.setVolume(volume);
        return data;
    }
}

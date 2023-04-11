package com.phicomm.speaker.device.custom.music;

import android.content.Context;
import android.os.MessageDispatchManager;
import android.os.ParcelableUtil;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.phicomm.speaker.device.utils.PhicommMessageManager;
import com.unisound.vui.handler.session.music.impl.IMusicController;
import com.unisound.vui.handler.session.music.kuwo.MusicPlayMode;
import java.util.List;
import nluparser.scheme.MusicResult;

public class PhicommMusicController implements IMusicController {
    private static final String TAG = PhicommMusicController.class.getSimpleName();
    private MessageDispatchManager mMessageManager;

    public PhicommMusicController(Context context) {
        this.mMessageManager = PhicommMessageManager.messageCenter(context);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void playRandomMusic() {
        Log.d(TAG, "sendMessage : MSG_TYPE_PLAYER playRandomMusic 3");
        this.mMessageManager.sendMessage(4, 3, -1, null);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void playSpecifiedMusic(String name, String singer, String album) {
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void playSpecifiedMusic(List<MusicResult.Music> musicList) {
        JsonArray musicJsonArr = new JsonArray();
        for (MusicResult.Music music : musicList) {
            musicJsonArr.add(getMusicJson(music));
        }
        Log.d(TAG, "sendMessage MSG_TYPE_PLAYER playSpecifiedMusic 1 : " + musicJsonArr.toString());
        this.mMessageManager.sendMessage(4, 1, -1, ParcelableUtil.obtain(musicJsonArr.toString()));
    }

    private JsonObject getMusicJson(MusicResult.Music music) {
        JsonObject musicJson = new JsonObject();
        musicJson.addProperty("title", music.getTitle());
        musicJson.addProperty("url", music.getUrl());
        return musicJson;
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void playNext() {
        Log.d(TAG, "sendMessage MSG_TYPE_PLAYER playNext 6");
        this.mMessageManager.sendMessage(4, 6, -1, null);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void playPrev() {
        Log.d(TAG, "sendMessage MSG_TYPE_PLAYER playPrev 5");
        this.mMessageManager.sendMessage(4, 5, -1, null);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void play() {
        Log.d(TAG, "sendMessage MSG_TYPE_PLAYER play 3");
        this.mMessageManager.sendMessage(4, 3, -1, null);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void pause() {
        Log.d(TAG, "sendMessage MSG_TYPE_PLAYER pause 2");
        this.mMessageManager.sendMessage(4, 2, -1, null);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void exit() {
        Log.d(TAG, "sendMessage MSG_TYPE_PLAYER exit 4");
        this.mMessageManager.sendMessage(4, 4, -1, null);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void setPlayMode(MusicPlayMode playMode) {
        switch (playMode) {
            case MODE_ALL_RANDOM:
                setRandomMode();
                return;
            case MODE_ALL_ORDER:
                setOrderMode();
                return;
            case MODE_SINGLE_CIRCLE:
                setSingleMode();
                return;
            case MODE_ALL_CIRCLE:
                setCircleMode();
                return;
            default:
                return;
        }
    }

    private void setRandomMode() {
        Log.d(TAG, "sendMessage MSG_TYPE_PLAYER setRandomMode 7-1");
        this.mMessageManager.sendMessage(4, 7, -1, ParcelableUtil.obtain(1));
    }

    private void setOrderMode() {
        Log.d(TAG, "sendMessage MSG_TYPE_PLAYER setOrderMode 7-2");
        this.mMessageManager.sendMessage(4, 7, -1, ParcelableUtil.obtain(2));
    }

    private void setSingleMode() {
        Log.d(TAG, "sendMessage MSG_TYPE_PLAYER setSingleMode 7-3");
        this.mMessageManager.sendMessage(4, 7, -1, ParcelableUtil.obtain(3));
    }

    private void setCircleMode() {
        Log.d(TAG, "sendMessage MSG_TYPE_PLAYER setCircleMode 7-4");
        this.mMessageManager.sendMessage(4, 7, -1, ParcelableUtil.obtain(4));
    }
}

package com.unisound.vui.handler.session.music.kuwo;

import android.content.Context;
import cn.kuwo.autosdk.api.KWAPI;
import cn.kuwo.autosdk.api.PlayMode;
import cn.kuwo.autosdk.api.PlayState;
import com.kaolafm.sdk.client.KLClientAPI;
import com.unisound.vui.handler.session.music.impl.IMusicController;
import java.util.List;
import nluparser.scheme.MusicResult;

public class KwMusicController implements IMusicController {
    private Context context;
    private KWAPI kwApi;

    public KwMusicController(Context context2) {
        this.context = context2;
        this.kwApi = KWAPI.createKWAPI(context2, KLClientAPI.KEY_AUTO);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void playRandomMusic() {
        this.kwApi.startAPP(this.context, true);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void playSpecifiedMusic(String song, String singer, String album) {
        this.kwApi.playClientMusics(this.context, song, singer, album);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void playSpecifiedMusic(List<MusicResult.Music> list) {
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void playNext() {
        this.kwApi.setPlayState(this.context, PlayState.STATE_NEXT);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void playPrev() {
        this.kwApi.setPlayState(this.context, PlayState.STATE_PRE);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void setPlayMode(MusicPlayMode playMode) {
        switch (playMode) {
            case MODE_ALL_CIRCLE:
                this.kwApi.setPlayMode(this.context, PlayMode.MODE_ALL_CIRCLE);
                return;
            case MODE_SINGLE_CIRCLE:
                this.kwApi.setPlayMode(this.context, PlayMode.MODE_SINGLE_CIRCLE);
                return;
            case MODE_SINGLE_PLAY:
                this.kwApi.setPlayMode(this.context, PlayMode.MODE_SINGLE_PLAY);
                return;
            case MODE_ALL_ORDER:
                this.kwApi.setPlayMode(this.context, PlayMode.MODE_ALL_ORDER);
                return;
            case MODE_ALL_RANDOM:
                this.kwApi.setPlayMode(this.context, PlayMode.MODE_ALL_RANDOM);
                return;
            default:
                return;
        }
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void play() {
        playRandomMusic();
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void pause() {
        this.kwApi.setPlayState(this.context, PlayState.STATE_PAUSE);
    }

    @Override // com.unisound.vui.handler.session.music.impl.IMusicController
    public void exit() {
        this.kwApi.exitAPP(this.context);
    }
}

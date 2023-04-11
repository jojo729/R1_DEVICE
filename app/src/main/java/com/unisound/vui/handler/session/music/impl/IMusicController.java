package com.unisound.vui.handler.session.music.impl;

import com.unisound.vui.handler.session.music.kuwo.MusicPlayMode;
import java.util.List;
import nluparser.scheme.MusicResult;

public interface IMusicController {
    void exit();

    void pause();

    void play();

    void playNext();

    void playPrev();

    void playRandomMusic();

    void playSpecifiedMusic(String str, String str2, String str3);

    void playSpecifiedMusic(List<MusicResult.Music> list);

    void setPlayMode(MusicPlayMode musicPlayMode);
}

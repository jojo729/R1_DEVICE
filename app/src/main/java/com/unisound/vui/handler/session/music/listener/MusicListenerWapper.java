package com.unisound.vui.handler.session.music.listener;

import com.unisound.ant.device.sessionlayer.SessionExecuteHandler;
import com.unisound.vui.handler.session.music.playitem.PlayItem;
import java.util.List;

public abstract class MusicListenerWapper extends SessionExecuteHandler implements MusicListener, MusicStatusListener {
    @Override // com.unisound.vui.handler.session.music.listener.MusicListener
    public void fireMusicListChanged(List<PlayItem> list) {
        onMusicListChanged(list);
    }

    @Override // com.unisound.vui.handler.session.music.listener.MusicListener
    public void fireMusicStart(PlayItem info, int index) {
        onMusicStart(info, index);
    }

    @Override // com.unisound.vui.handler.session.music.listener.MusicListener
    public void fireMusicEnd(PlayItem info) {
        onMusicEnd(info);
    }

    @Override // com.unisound.vui.handler.session.music.listener.MusicListener
    public void fireMusicPrepare(PlayItem info, int index) {
        onMusicPrepare(info, index);
    }

    @Override // com.unisound.vui.handler.session.music.listener.MusicListener
    public void fireError(String errorMessage) {
        onError(errorMessage);
    }

    @Override // com.unisound.vui.handler.session.music.listener.MusicStatusListener
    public void fireStatusChanged(int state) {
        onStatusChanged(state);
    }

    @Override // com.unisound.vui.handler.session.music.listener.MusicStatusListener
    public void firePlayModeChanged(String mode) {
        onPlayModeChanged(mode);
    }

    @Override // com.unisound.vui.handler.session.music.listener.MusicListener
    public void fireMusicChange() {
    }

    @Override // com.unisound.vui.handler.session.music.listener.MusicStatusListener
    public void fireItemOperateCommand(int operate) {
        onItemOperateCommand(operate);
    }

    public void onMusicListChanged(List<PlayItem> list) {
    }

    public void onMusicStart(PlayItem info, int index) {
    }

    public void onMusicEnd(PlayItem info) {
    }

    public void onMusicPrepare(PlayItem info, int index) {
    }

    public void onError(String errorMessage) {
    }

    public void onStatusChanged(int state) {
    }

    public void onPlayModeChanged(String mode) {
    }

    public void onItemOperateCommand(int operate) {
    }
}

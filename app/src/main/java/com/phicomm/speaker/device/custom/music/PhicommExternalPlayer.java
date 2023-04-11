package com.phicomm.speaker.device.custom.music;

import android.content.Context;
import android.os.MessageDispatchManager;
import com.phicomm.speaker.device.utils.PhicommMessageManager;
import com.phicomm.speaker.device.utils.PhicommPlayerUtils;
import com.unisound.vui.handler.session.music.CommonPlayer;
import com.unisound.vui.handler.session.music.PlayController;
import com.unisound.vui.handler.session.music.kuwo.MusicPlayMode;
import com.unisound.vui.handler.session.music.player.MusicPlayer;
import com.unisound.vui.handler.session.music.playitem.PlayItem;
import java.util.List;

public class PhicommExternalPlayer implements CommonPlayer {
    private final MessageDispatchManager mMessageManager;

    public PhicommExternalPlayer(Context context) {
        this.mMessageManager = PhicommMessageManager.messageCenter(context);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public boolean isPrepared() {
        return false;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play() {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play(int currentPage, int totalPage) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play(String asrResult) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play(List<PlayItem> list, int index) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play(List<PlayItem> list, int index, int currentPage, int totalPage) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void pause() {
        PhicommPlayerUtils.pause(this.mMessageManager);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void resume() {
        PhicommPlayerUtils.resume(this.mMessageManager);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void stop() {
        PhicommPlayerUtils.stop(this.mMessageManager);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void playPrev() {
        PhicommPlayerUtils.playPrev(this.mMessageManager);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void playNext() {
        PhicommPlayerUtils.playNext(this.mMessageManager);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void release() {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void setCurrentPlayMode(MusicPlayMode currentPlayMode) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void collect() {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void seekTo(long position) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public boolean getPlayWhenReady() {
        return false;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public long getDuration() {
        return 0;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public long getCurrentPosition() {
        return 0;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public int getBufferPercent() {
        return 0;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public boolean isPlaying() {
        return false;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public int getPlayStatus() {
        return 0;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public List<PlayItem> getItemList() {
        return null;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public PlayItem getCurrentItem() {
        return null;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void registerCallback(MusicPlayer.Callback callback) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void setRenderer(MusicPlayer.Renderer renderer) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play(int index) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void addCollectMusic(String id) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void deleteCollectMusic(String id) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void batchDeleteCollectMusic(String ids) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void setPlayList(List<PlayItem> list) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void appendPlayList(int page, int totalPage, List<PlayItem> list) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public PlayController.ItemPlayMode getCurrentPlayMode() {
        return null;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void setDevicePlayingType(String devicePlayingType) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public String getDevicePlayingType() {
        return null;
    }
}

package com.unisound.vui.handler.session.music;

import android.text.TextUtils;
import com.unisound.ant.device.bean.MusicData;
import com.unisound.ant.device.listener.MusicStatusListener;
import com.unisound.vui.handler.session.music.PlayController;
import com.unisound.vui.handler.session.music.listener.MusicListenerWapper;
import com.unisound.vui.handler.session.music.playitem.PlayItem;
import com.unisound.vui.util.LogMgr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ANTPlayController extends AbstractPlayController {
    public static final int OPERATE_CANCLE_COLLECTED = 21;
    public static final int OPERATE_COLLECTED = 20;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_ENDED = 4;
    public static final int STATE_EXIT = 0;
    public static final int STATE_PAUSE = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_PREPARED = 5;
    public static final int STATE_STOP = 1;
    private static final String TAG = "ANTPlayController";
    public static final String TYPE_AUDIO = "audio";
    public static final String TYPE_MUSIC = "music";
    public static final String TYPE_NEWS = "news";
    public static final String TYPE_NONE = "none";
    private static Map<Integer, String> status = new HashMap();
    private MusicService musicService;

    static {
        status.put(1, "stop");
        status.put(2, "pause");
        status.put(3, MusicData.PLAY_STATUS_PLAYING);
        status.put(4, "end");
        status.put(5, MusicData.PLAY_STATUS_PREPARED);
        status.put(6, MusicData.PLAY_STATUS_BUFFERING);
    }

    public String getPlayStatus(int state) {
        return status.get(Integer.valueOf(state));
    }

    public ANTPlayController(MusicService musicService2) {
        super(musicService2);
        this.musicService = musicService2;
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public boolean isPrepared() {
        return this.musicService.isPrepared();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void play(boolean playWhenReady) {
        this.playOnFocusGain = true;
        this.musicService.play();
    }

    public void play(boolean playWhenReady, int currentPage, int totalPage) {
        this.playOnFocusGain = true;
        this.musicService.play(currentPage, totalPage);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void play(boolean playWhenReady, String asrResult) {
        this.playOnFocusGain = true;
        this.musicService.play(asrResult);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void pause() {
        this.musicService.pause();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void resume() {
        this.musicService.resume();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void stop() {
        this.musicService.stop();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void playPrev() {
        this.musicService.playPrev();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void playNext() {
        this.musicService.playNext();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void release() {
        this.musicService.getMusicPlayer().release();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void seekTo(long pos) {
        this.musicService.getMusicPlayer().seekTo(pos);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void configMediaPlayerState() {
        LogMgr.d(TAG, "configMediaPlayerState. audioFocus=%b", Integer.valueOf(this.audioFocus));
        if (this.audioFocus == 0 || this.audioFocus == 1) {
            if (isPlaying()) {
                LogMgr.d(TAG, "audio focus loss, music is Playing, pause it");
                pause();
            }
        } else if (this.playOnFocusGain && 2 == getPlaybackStatus()) {
            LogMgr.d(TAG, "audio focus gain, music is pausing, continue to play");
            seekTo(getCurrentPostion());
            play(this.musicService.playWhenReady());
            this.playOnFocusGain = false;
        }
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void fastForward(int offset) {
        this.musicService.setOffsetTime(offset);
        this.musicService.fastForward();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void rewind(int offset) {
        this.musicService.setOffsetTime(offset);
        this.musicService.rewind();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void skipToNext(boolean playWhenReady) {
        this.musicService.skipToNext(playWhenReady);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void skipToPrevious(boolean playWhenReady) {
        this.musicService.skipToPrevious(playWhenReady);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void skipToQueueItem(int index, boolean playWhenReady) {
        this.musicService.skipToQueueItem(index, playWhenReady);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void setPlayMode(PlayController.ItemPlayMode playMode) {
        switch (playMode) {
            case LIST_LOOP:
                this.musicService.setListLoop(true);
                return;
            case SINGLE_LOOP:
                this.musicService.setRepeating(true);
                return;
            case LIST_SHUFFLED:
                this.musicService.setShuffled(true);
                return;
            default:
                return;
        }
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public PlayController.ItemPlayMode getPlayMode() {
        return this.musicService.getPlayListMode();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void cancelCollect() {
        LogMgr.d(TAG, "-->>cancelCollect");
        this.musicService.collectItem(false);
        this.musicService.onOperateCommandChange(21);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public boolean isCollected() {
        return this.musicService.isCollected();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void collect() {
        LogMgr.d(TAG, "-->>collect");
        this.musicService.collectItem(true);
        this.musicService.onOperateCommandChange(20);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public boolean isPlaying() {
        return this.musicService.getMusicPlayer().isPlaying();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public long getCurrentPostion() {
        return this.musicService.getMusicPlayer().getCurrentPosition();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void setPlaylist(List<PlayItem> itemList) {
        addMusicListId(itemList);
        this.musicService.setPlaylist(itemList);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void appendPlaylist(int page, int totalPage, List<PlayItem> musicList) {
        addMusicListId(musicList);
        this.musicService.appendPlaylist(page, totalPage, musicList);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public List<PlayItem> getPlayItemList() {
        return this.musicService.getItemList();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public PlayItem getCurrPlayItem() {
        return this.musicService.getCurrentItem();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void registerMusicListener(MusicListenerWapper lis) {
        this.musicService.registerMusicListener(lis);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void unRegisterMusicListener(MusicListenerWapper lis) {
        this.musicService.unRegisterListener(lis);
    }

    public void setStateListener(MusicStatusListener listener) {
        if (this.musicService != null) {
            this.musicService.setStateListener(listener);
        }
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public int getPlaybackStatus() {
        return this.musicService.getPlaybackStatus();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public int getBufferPercent() {
        return this.musicService.getBufferPercent();
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public void setBeginIndex(int index) {
        this.musicService.setBeginIndex(index);
    }

    @Override // com.unisound.vui.handler.session.music.PlayController
    public long getDuration() {
        return this.musicService.getDuration();
    }

    public String getDevicePlayingType() {
        return this.musicService.getDevicePlayingType();
    }

    public void setDevicePlayingType(String devicePlayingType) {
        this.musicService.setDevicePlayingType(devicePlayingType);
    }

    public void switchTo(String itemId) {
        this.musicService.switchTo(itemId);
    }

    public String getPlayStatus() {
        return getPlayStatus(this.musicService.getPlayStatus());
    }

    public void addCollectMusic(String id) {
        this.musicService.addCollectMusic(id);
    }

    public void deleteCollectMusic(String id) {
        this.musicService.deleteCollectMusic(id);
    }

    public void batchDeleteCollectMusic(String ids) {
        this.musicService.batchDeleteCollectMusic(ids);
    }

    public void play(List<PlayItem> itemList, int index) {
        addMusicListId(itemList);
        this.musicService.play(itemList, index);
    }

    public void play(List<PlayItem> itemList, int index, int currentPage, int totalPage) {
        addMusicListId(itemList);
        this.musicService.play(itemList, index, currentPage, totalPage);
    }

    private void addMusicListId(List<PlayItem> musicList) {
        String listId = null;
        List<PlayItem> emptyIdlist = new ArrayList<>();
        for (PlayItem item : musicList) {
            String id = item.getListId();
            if (!TextUtils.isEmpty(id)) {
                listId = id;
            } else {
                emptyIdlist.add(item);
            }
        }
        if (TextUtils.isEmpty(listId)) {
            listId = UUID.randomUUID().toString();
        }
        for (PlayItem music : emptyIdlist) {
            music.setListId(listId);
        }
    }
}

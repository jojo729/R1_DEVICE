package com.unisound.vui.handler.session.music;

import com.unisound.vui.handler.session.music.listener.MusicListenerWapper;
import com.unisound.vui.handler.session.music.playitem.PlayItem;
import java.util.List;

public interface PlayController {

    public enum ItemPlayMode {
        LIST_LOOP,
        LIST_SHUFFLED,
        SINGLE_LOOP,
        LIST_ORDER
    }

    void appendPlaylist(int i, int i2, List<PlayItem> list);

    void cancelCollect();

    void collect();

    void configMediaPlayerState();

    void fastForward(int i);

    int getBufferPercent();

    PlayItem getCurrPlayItem();

    long getCurrentPostion();

    long getDuration();

    List<PlayItem> getPlayItemList();

    ItemPlayMode getPlayMode();

    int getPlaybackStatus();

    boolean isCollected();

    boolean isPlaying();

    boolean isPrepared();

    void pause();

    void play(boolean z);

    void play(boolean z, String str);

    void playNext();

    void playPrev();

    void registerMusicListener(MusicListenerWapper musicListenerWapper);

    void release();

    void resume();

    void rewind(int i);

    void seekTo(long j);

    void setBeginIndex(int i);

    void setPlayMode(ItemPlayMode itemPlayMode);

    void setPlaylist(List<PlayItem> list);

    void skipToNext(boolean z);

    void skipToPrevious(boolean z);

    void skipToQueueItem(int i, boolean z);

    void stop();

    void unRegisterMusicListener(MusicListenerWapper musicListenerWapper);
}

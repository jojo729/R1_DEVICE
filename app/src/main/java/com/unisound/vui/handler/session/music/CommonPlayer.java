package com.unisound.vui.handler.session.music;

import com.unisound.vui.handler.session.music.PlayController;
import com.unisound.vui.handler.session.music.kuwo.MusicPlayMode;
import com.unisound.vui.handler.session.music.player.MusicPlayer;
import com.unisound.vui.handler.session.music.playitem.PlayItem;
import java.util.List;

public interface CommonPlayer {

    public interface Callback {
        void onOperateCommandChange(int i);

        void onPlayStateChanged(int i);

        void onPlayerError(String str);
    }

    public interface PlayerFactory<T extends CommonPlayer> {
        T newInstance();
    }

    public interface Renderer {

        public enum RendererType {
            TYPE_MUSIC,
            TYPE_AUDIO,
            TYPE_RADIO
        }

        MusicPlayer.Renderer.RendererType getRendererType();
    }

    void addCollectMusic(String str);

    void appendPlayList(int i, int i2, List<PlayItem> list);

    void batchDeleteCollectMusic(String str);

    void collect();

    void deleteCollectMusic(String str);

    int getBufferPercent();

    PlayItem getCurrentItem();

    PlayController.ItemPlayMode getCurrentPlayMode();

    long getCurrentPosition();

    String getDevicePlayingType();

    long getDuration();

    List<PlayItem> getItemList();

    int getPlayStatus();

    boolean getPlayWhenReady();

    boolean isPlaying();

    boolean isPrepared();

    void pause();

    void play();

    void play(int i);

    void play(int i, int i2);

    void play(String str);

    void play(List<PlayItem> list, int i);

    void play(List<PlayItem> list, int i, int i2, int i3);

    void playNext();

    void playPrev();

    void registerCallback(MusicPlayer.Callback callback);

    void release();

    void resume();

    void seekTo(long j);

    void setCurrentPlayMode(MusicPlayMode musicPlayMode);

    void setDevicePlayingType(String str);

    void setPlayList(List<PlayItem> list);

    void setRenderer(MusicPlayer.Renderer renderer);

    void stop();
}

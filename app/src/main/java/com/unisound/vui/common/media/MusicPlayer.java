package com.unisound.vui.common.media;

public interface MusicPlayer {

    public interface Callback {
        void onOperateCommandChange(int i);

        void onPlayStateChanged(int i);

        void onPlayerError(String str);
    }

    public interface PlayerFactory<T extends MusicPlayer> {
        T newInstance();
    }

    public interface Renderer {

        public enum RendererType {
            TYPE_MUSIC,
            TYPE_AUDIO,
            TYPE_RADIO
        }

        RendererType getRendererType();
    }

    int getBufferPercent();

    long getCurrentPosition();

    long getDuration();

    boolean getPlayWhenReady();

    boolean isPlaying();

    boolean isPrepared();

    void pause();

    void play(String str, boolean z);

    void registerCallback(Callback callback);

    void release();

    void seekTo(long j);

    void setRenderer(Renderer renderer);

    void stop();
}

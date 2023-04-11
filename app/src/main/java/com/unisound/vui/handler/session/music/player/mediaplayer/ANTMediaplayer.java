package com.unisound.vui.handler.session.music.player.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import com.phicomm.speaker.device.R;
import com.unisound.vui.handler.session.music.player.MusicPlayer;
import com.unisound.vui.util.LogMgr;
import java.io.IOException;

public class ANTMediaplayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnBufferingUpdateListener, MusicPlayer {
    private static final int MEDIA_ERROR_UNKNOWN = Integer.MIN_VALUE;
    private static final String TAG = "ANTMediaplayer";
    private int bufferPercent;
    private MusicPlayer.Callback callback;
    private final Context context;
    private volatile int currentPosition;
    private boolean isPrepared;
    private MediaPlayer mediaPlayer;
    private boolean playWhenReady;
    private int state;

    private ANTMediaplayer(Context context2) {
        this.context = context2;
        createMediaPlayerIfNeeded();
    }

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        this.bufferPercent = percent;
    }

    public void onSeekComplete(MediaPlayer mp) {
        LogMgr.e(TAG, "onSeekComplete from MediaPlayer:" + mp.getCurrentPosition());
        this.currentPosition = mp.getCurrentPosition();
        if (this.state == 6) {
            this.mediaPlayer.start();
            this.state = 3;
            onStateChanged();
        }
    }

    public void onCompletion(MediaPlayer player) {
        LogMgr.d(TAG, "onCompletion from MediaPlayer");
        this.state = 4;
        onStateChanged();
    }

    public void onPrepared(MediaPlayer player) {
        LogMgr.e(TAG, "onPrepared from MediaPlayer");
        this.isPrepared = true;
        this.state = 5;
        onStateChanged();
        if (this.playWhenReady) {
            this.mediaPlayer.start();
            this.state = 3;
            onStateChanged();
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        LogMgr.e(TAG, "Media player error: what=" + what + ", extra=" + extra);
        if (this.callback != null) {
            String errorMessage = "";
            if (what == 1) {
                switch (extra) {
                    case -1010:
                    case -1007:
                        errorMessage = this.context.getString(R.string.tts_music_format_no_supported);
                        break;
                    case -1004:
                        errorMessage = this.context.getString(R.string.tts_music_cache_fail);
                        break;
                    case -110:
                        errorMessage = this.context.getString(R.string.tts_music_cache_fail);
                        break;
                    default:
                        errorMessage = this.context.getString(R.string.tts_music_cache_fail);
                        break;
                }
            } else if (what == -38) {
                errorMessage = this.context.getString(R.string.tts_music_cache_fail);
            }
            this.callback.onPlayerError(errorMessage);
        }
        relaxResources(true);
        return true;
    }

    private void createMediaPlayerIfNeeded() {
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(this.mediaPlayer == null);
        LogMgr.d(TAG, "createMediaPlayerIfNeeded. needed? %b", objArr);
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
            this.mediaPlayer.setWakeMode(this.context.getApplicationContext(), 1);
            this.mediaPlayer.setOnPreparedListener(this);
            this.mediaPlayer.setOnCompletionListener(this);
            this.mediaPlayer.setOnErrorListener(this);
            this.mediaPlayer.setOnSeekCompleteListener(this);
            this.mediaPlayer.setOnBufferingUpdateListener(this);
            return;
        }
        this.mediaPlayer.reset();
    }

    private void relaxResources(boolean releaseMediaPlayer) {
        LogMgr.e(TAG, "relaxResources. releaseMediaPlayer=%b", Boolean.valueOf(releaseMediaPlayer));
        if (releaseMediaPlayer && this.mediaPlayer != null) {
            this.mediaPlayer.reset();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public boolean isPrepared() {
        return this.isPrepared;
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public boolean isPlaying() {
        return this.mediaPlayer != null && this.mediaPlayer.isPlaying();
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public void play(String url, boolean playWhenReady2) {
        if (this.state == 2 || this.state == 5) {
            this.state = 3;
            this.mediaPlayer.start();
            onStateChanged();
            return;
        }
        this.playWhenReady = playWhenReady2;
        this.isPrepared = false;
        this.bufferPercent = 0;
        relaxResources(false);
        try {
            createMediaPlayerIfNeeded();
            this.state = 6;
            onStateChanged();
            this.mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setDataSource(url);
            this.mediaPlayer.prepareAsync();
        } catch (IOException ex) {
            LogMgr.e(TAG, ex + "Exception playing song");
        }
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public void pause() {
        if (this.state == 3 && this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
            this.currentPosition = this.mediaPlayer.getCurrentPosition();
        }
        this.state = 2;
        onStateChanged();
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public void stop() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
            this.currentPosition = this.mediaPlayer.getCurrentPosition();
        }
        this.state = 1;
        onStateChanged();
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public void release() {
        relaxResources(true);
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public void seekTo(long position) {
        LogMgr.e(TAG, "seekTo called with ", Long.valueOf(position));
        if (this.mediaPlayer == null) {
            this.currentPosition = (int) position;
            return;
        }
        if (this.mediaPlayer.isPlaying()) {
            this.state = 6;
            onStateChanged();
        }
        this.mediaPlayer.seekTo((int) position);
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public long getDuration() {
        return (long) this.mediaPlayer.getDuration();
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public long getCurrentPosition() {
        return this.mediaPlayer != null ? (long) this.mediaPlayer.getCurrentPosition() : (long) this.currentPosition;
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public int getBufferPercent() {
        return this.bufferPercent;
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public void registerCallback(MusicPlayer.Callback callback2) {
        this.callback = callback2;
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer
    public void setRenderer(MusicPlayer.Renderer renderer) {
    }

    private void onStateChanged() {
        if (this.callback != null) {
            this.callback.onPlayStateChanged(this.state);
        }
    }

    public static final class MediaplayerFactory implements MusicPlayer.PlayerFactory<ANTMediaplayer> {
        private Context context;

        public MediaplayerFactory(Context context2) {
            this.context = context2;
        }

        @Override // com.unisound.vui.handler.session.music.player.MusicPlayer.PlayerFactory
        public ANTMediaplayer newInstance() {
            return new ANTMediaplayer(this.context);
        }
    }
}

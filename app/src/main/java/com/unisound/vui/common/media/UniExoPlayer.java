package com.unisound.vui.common.media;

import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.exoplayer2.*;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.unisound.vui.util.LogMgr;
import okhttp3.OkHttpClient;

public class UniExoPlayer implements ExoPlayer.EventListener, MusicPlayer {
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    public static final int MEDIA_STREAM_TYPE = 3;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_ENDED = 4;
    public static final int STATE_PAUSE = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_PREPARED = 5;
    public static final int STATE_STOP = 1;
    private static final String TAG = "UniExoPlayer";
    private Callback callback;
    private Context context;
    private int currentNotifyVolume;
    private EventLogger eventLogger;
    private boolean isAutoSetAlarm;
    private boolean isPrepared;
    private final Handler mainHandler;
    private DataSource.Factory mediaDataSourceFactory;
    private int playState;
    private boolean playWhenReady;
    private SimpleExoPlayer player;
    private int rendererBuildingState;
    private DefaultTrackSelector trackSelector;
    private String userAgent;

    public static final class ExoplayerFactory implements PlayerFactory<UniExoPlayer> {
        private Context context;

        public ExoplayerFactory(Context context2) {
            this.context = context2;
        }

        @Override // com.unisound.vui.common.media.MusicPlayer.PlayerFactory
        public UniExoPlayer newInstance() {
            return new UniExoPlayer(this.context);
        }
    }

    private UniExoPlayer(Context context2) {
        this.currentNotifyVolume = 1;
        this.isAutoSetAlarm = false;
        this.context = context2;
        this.mediaDataSourceFactory = buildDataSourceFactory(true);
        DefaultRenderersFactory defaultRenderersFactory = new DefaultRenderersFactory(context2);
        this.trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(BANDWIDTH_METER));
        this.eventLogger = new EventLogger(this.trackSelector);
        this.player = ExoPlayerFactory.newSimpleInstance(defaultRenderersFactory, this.trackSelector);
        this.player.addListener(this);
        this.player.setAudioStreamType(3);
        this.mainHandler = new Handler();
    }

    private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
        return buildDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }

    private MediaSource buildMediaSource(Uri uri, String overrideExtension) {
        int inferContentType = TextUtils.isEmpty(overrideExtension) ? Util.inferContentType(uri) : Util.inferContentType("." + overrideExtension);
        switch (inferContentType) {
            case 0:
                return new DashMediaSource(uri, buildDataSourceFactory(false), new DefaultDashChunkSource.Factory(this.mediaDataSourceFactory), this.mainHandler, this.eventLogger);
            case 1:
                return new SsMediaSource(uri, buildDataSourceFactory(false), new DefaultSsChunkSource.Factory(this.mediaDataSourceFactory), this.mainHandler, this.eventLogger);
            case 2:
                return new HlsMediaSource(uri, this.mediaDataSourceFactory, this.mainHandler, this.eventLogger);
            case 3:
                return new ExtractorMediaSource(uri, this.mediaDataSourceFactory, new DefaultExtractorsFactory(), this.mainHandler, this.eventLogger);
            default:
                throw new IllegalStateException("Unsupported type: " + inferContentType);
        }
    }

    private void onStateChanged() {
        if (this.callback != null) {
            this.callback.onPlayStateChanged(this.playState);
        }
    }

    private void prepare(String url, boolean playWhenReady2) {
        LogMgr.d(TAG, "prepare url : " + url + ";playWhenReady : " + playWhenReady2);
        this.player.setPlayWhenReady(playWhenReady2);
        MediaSource buildMediaSource = buildMediaSource(Uri.parse(url), null);
        this.player.seekTo(0);
        this.player.prepare(buildMediaSource);
        this.isPrepared = false;
        this.playWhenReady = playWhenReady2;
    }

    private void resetNotifyVolume() {
        if (this.isAutoSetAlarm) {
            ((AudioManager) this.context.getSystemService(Context.AUDIO_SERVICE)).setStreamVolume(3, this.currentNotifyVolume, 0);
            this.isAutoSetAlarm = false;
        }
    }

    private void setNotificationMaxVolume() {
        AudioManager audioManager = (AudioManager) this.context.getSystemService(Context.AUDIO_SERVICE);
        this.currentNotifyVolume = audioManager.getStreamVolume(3);
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        if (this.currentNotifyVolume < streamMaxVolume / 2) {
            this.isAutoSetAlarm = true;
            audioManager.setStreamVolume(3, (streamMaxVolume / 2) + 1, 0);
        }
    }

    public DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(this.context, bandwidthMeter, buildHttpDataSourceFactory(bandwidthMeter));
    }

    public HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        this.userAgent = Util.getUserAgent(this.context, TAG);
        return new OkHttpDataSourceFactory(new OkHttpClient(), this.userAgent, bandwidthMeter);
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public int getBufferPercent() {
        return this.player.getBufferedPercentage();
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public long getCurrentPosition() {
        return this.player.getCurrentPosition();
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public long getDuration() {
        return this.player.getDuration();
    }

    public Handler getMainHandler() {
        return this.mainHandler;
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public boolean isPlaying() {
        return this.playState == 3;
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public boolean isPrepared() {
        return this.isPrepared;
    }

    @Override // com.google.android.exoplayer2.ExoPlayer.EventListener
    public void onLoadingChanged(boolean isLoading) {
    }

    @Override // com.google.android.exoplayer2.ExoPlayer.EventListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    @Override // com.google.android.exoplayer2.ExoPlayer.EventListener
    public void onPlayerError(ExoPlaybackException error) {
        if (this.callback != null) {
            this.callback.onPlayerError(error.getMessage());
        }
    }

    @Override // com.google.android.exoplayer2.ExoPlayer.EventListener
    public void onPlayerStateChanged(boolean playWhenReady2, int state) {
        switch (state) {
            case 2:
                this.playState = 6;
                break;
            case 3:
                if (this.playState == 6) {
                    this.isPrepared = true;
                    this.playState = 5;
                    if (playWhenReady2) {
                        onStateChanged();
                        this.playState = 3;
                        break;
                    }
                } else {
                    return;
                }
                break;
            case 4:
                this.playState = 4;
                break;
        }
        onStateChanged();
    }

    @Override // com.google.android.exoplayer2.ExoPlayer.EventListener
    public void onPositionDiscontinuity(int reason) {
    }

    @Override // com.google.android.exoplayer2.ExoPlayer.EventListener
    public void onTimelineChanged(Timeline timeline, Object manifest,int reason) {
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onSeekProcessed() {

    }

    @Override // com.google.android.exoplayer2.ExoPlayer.EventListener
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public void pause() {
        LogMgr.d(TAG, "pause");
        this.player.setPlayWhenReady(false);
        this.playState = 2;
        onStateChanged();
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public void play(String url, boolean playWhenReady2) {
        LogMgr.d(TAG, "play url : " + url + ";playState : " + this.playState + ";playWhenReady : " + playWhenReady2);
        if (this.playState == 2 || this.playState == 5) {
            this.player.setPlayWhenReady(true);
            this.playState = 3;
            onStateChanged();
            return;
        }
        prepare(url, playWhenReady2);
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public void registerCallback(Callback callback2) {
        this.callback = callback2;
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public void release() {
        LogMgr.d(TAG, "release");
        this.player.release();
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public void seekTo(long position) {
        this.player.seekTo(position);
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public void setRenderer(Renderer renderer) {
    }

    @Override // com.unisound.vui.common.media.MusicPlayer
    public void stop() {
        LogMgr.d(TAG, "stop");
        this.player.stop();
        this.playState = 1;
        onStateChanged();
    }
}

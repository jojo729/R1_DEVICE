package com.phicomm.speaker.device.utils;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import com.unisound.vui.util.LogMgr;
import java.io.File;

public class MediaPlayerUtils {
    private static final String TAG = "MediaPlayerUtils";
    private static MediaPlayerUtils sMediaPlayerUtils;
    private Handler mHandler = new Handler(Looper.myLooper());
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private int mMediaPlayerState = 0;

    private MediaPlayerUtils() {
    }

    public static MediaPlayerUtils getInstance() {
        if (sMediaPlayerUtils == null) {
            synchronized (TAG) {
                if (sMediaPlayerUtils == null) {
                    sMediaPlayerUtils = new MediaPlayerUtils();
                }
            }
        }
        return sMediaPlayerUtils;
    }

    public void play(String path) {
        play(path, -1);
    }

    public void play(String path, final int duration) {
        this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            /* class com.phicomm.speaker.device.utils.MediaPlayerUtils.AnonymousClass1 */

            public void onCompletion(MediaPlayer mp) {
                LogMgr.d(MediaPlayerUtils.TAG, "onCompletion");
            }
        });
        this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            /* class com.phicomm.speaker.device.utils.MediaPlayerUtils.AnonymousClass2 */

            public boolean onError(MediaPlayer mp, int what, int extra) {
                LogMgr.d(MediaPlayerUtils.TAG, "onError");
                return false;
            }
        });
        File file = new File(path);
        if (file.exists() && file.length() > 0 && this.mMediaPlayer != null) {
            if (this.mMediaPlayerState == 2) {
                LogMgr.d(TAG, "mMediaPlayerState : 2");
                this.mMediaPlayer.start();
                return;
            }
            this.mMediaPlayer.stop();
            try {
                this.mMediaPlayer.reset();
                this.mMediaPlayer.setDataSource(path);
                if (duration > 0) {
                    this.mMediaPlayer.setLooping(true);
                }
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.prepareAsync();
                this.mMediaPlayer.setVolume(1.0f, 1.0f);
                this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    /* class com.phicomm.speaker.device.utils.MediaPlayerUtils.AnonymousClass3 */

                    public void onPrepared(MediaPlayer mp) {
                        LogMgr.d(MediaPlayerUtils.TAG, "onPrepared");
                        MediaPlayerUtils.this.mMediaPlayer.start();
                        MediaPlayerUtils.this.mHandler.postDelayed(new Runnable() {
                            /* class com.phicomm.speaker.device.utils.MediaPlayerUtils.AnonymousClass3.AnonymousClass1 */

                            public void run() {
                                MediaPlayerUtils.this.stop();
                            }
                        }, (long) duration);
                    }
                });
            } catch (Exception e) {
                LogMgr.e(TAG, "paly error = " + e.toString());
            }
        }
    }

    public boolean isPlaying() {
        if (this.mMediaPlayer != null) {
            return this.mMediaPlayer.isPlaying();
        }
        return false;
    }

    public void pause() {
        LogMgr.d(TAG, "puase mMediaPlayerState : 2");
        if (this.mMediaPlayer != null && this.mMediaPlayerState == 1) {
            this.mMediaPlayer.pause();
        }
    }

    public void resume() {
        if (this.mMediaPlayer != null && this.mMediaPlayerState == 2) {
            this.mMediaPlayer.start();
        }
    }

    public void start() {
        LogMgr.d(TAG, "mMediaPlayer start  mMediaPlayerState: " + this.mMediaPlayerState);
        if (this.mMediaPlayer != null && this.mMediaPlayerState == 5) {
            this.mMediaPlayer.start();
        }
    }

    public void stop() {
        LogMgr.d(TAG, "stop mMediaPlayerState : " + this.mMediaPlayerState);
        if (this.mMediaPlayer != null && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.stop();
        }
    }

    public void release() {
        LogMgr.d(TAG, "release mMediaPlayerState : " + this.mMediaPlayerState);
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }
}

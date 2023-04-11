package com.unisound.vui.handler.session.memo.ring;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import com.unisound.vui.handler.session.memo.utils.MemoConstants;
import com.unisound.vui.util.LogMgr;
import java.io.IOException;

public class RingingPlayer {
    private static final String TAG = (MemoConstants.MEMO_TAG + RingingPlayer.class.getSimpleName());
    private Context mContext;
    private MediaPlayer mPlayer = new MediaPlayer();
    private MediaPlayer.OnCompletionListener onCompletionListener;

    public RingingPlayer(Context context) {
        this.mContext = context;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener) {
        this.onCompletionListener = listener;
    }

    public void play(Uri toneUri, boolean isLooping) {
        try {
            if (this.mPlayer != null && !this.mPlayer.isPlaying()) {
                this.mPlayer.reset();
                this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    /* class com.unisound.vui.handler.session.memo.ring.RingingPlayer.AnonymousClass1 */

                    public void onPrepared(MediaPlayer mp) {
                        LogMgr.d(RingingPlayer.TAG, "play start");
                        mp.start();
                    }
                });
                this.mPlayer.setOnCompletionListener(this.onCompletionListener);
                LogMgr.d(TAG, " memoRinging play uri:" + toneUri);
                this.mPlayer.setDataSource(this.mContext, toneUri);
                this.mPlayer.setAudioStreamType(4);
                this.mPlayer.setLooping(isLooping);
                this.mPlayer.prepareAsync();
            }
        } catch (Exception e) {
            LogMgr.e(TAG, TAG, e);
        }
    }

    public void play(int rawId, boolean looping) {
        play(rawId, looping, null);
    }

    public void play(int rawId, boolean looping, MediaPlayer.OnCompletionListener completeCallback) {
        this.onCompletionListener = completeCallback;
        AssetFileDescriptor file = this.mContext.getResources().openRawResourceFd(rawId);
        if (this.mPlayer == null) {
            throw new RuntimeException("MediaPlayer has been released.");
        }
        if (this.onCompletionListener != null) {
            this.mPlayer.setOnCompletionListener(this.onCompletionListener);
        }
        this.mPlayer.reset();
        try {
            this.mPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
            this.mPlayer.setAudioStreamType(4);
            this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /* class com.unisound.vui.handler.session.memo.ring.RingingPlayer.AnonymousClass2 */

                public void onPrepared(MediaPlayer mp) {
                    LogMgr.d(RingingPlayer.TAG, "play start");
                    mp.start();
                }
            });
            this.mPlayer.setLooping(looping);
            this.mPlayer.setVolume(0.5f, 0.5f);
            this.mPlayer.prepareAsync();
            try {
                file.close();
            } catch (IOException e) {
                LogMgr.e(TAG, e.toString());
            }
        } catch (Exception e2) {
            LogMgr.e(TAG, e2.toString());
            try {
                file.close();
            } catch (IOException e3) {
                LogMgr.e(TAG, e3.toString());
            }
        } catch (Throwable th) {
            try {
                file.close();
            } catch (IOException e4) {
                LogMgr.e(TAG, e4.toString());
            }
            throw th;
        }
    }

    public void stop() {
        if (this.mPlayer != null) {
            if (this.mPlayer.isPlaying()) {
                this.mPlayer.stop();
            }
            this.mPlayer.reset();
        }
    }
}

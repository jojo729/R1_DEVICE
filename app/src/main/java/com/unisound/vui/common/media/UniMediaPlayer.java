package com.unisound.vui.common.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import com.unisound.vui.util.LogMgr;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UniMediaPlayer {
    private static final String TAG = "UniMediaPlayer";
    public static UniMediaPlayer uniMediaPlayer;
    private Context appContent;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass15 */

        public void onAudioFocusChange(int focusChange) {
            LogMgr.d(UniMediaPlayer.TAG, "onAudioFocusChange focusChange = " + focusChange);
            if (focusChange == -2) {
                UniMediaPlayer.this.stop();
            } else if (focusChange != 1 && focusChange == -1) {
                UniMediaPlayer.this.mAudioManager.abandonAudioFocus(UniMediaPlayer.this.mAudioFocusChangeListener);
                UniMediaPlayer.this.stop();
            }
        }
    };
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private int mMediaPlayerState = 0;
    private List<IMediaPlayerStateListener> mediaPlayerStateListenerList = new CopyOnWriteArrayList();
    private String playTag;

    private UniMediaPlayer(Context context) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.appContent = context;
        LogMgr.d(TAG, "new MediaPlayer()");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void callBackPlayerState(int state) {
        LogMgr.d(TAG, "callBackPlayerState State : " + state);
        this.mMediaPlayerState = state;
        if (this.mediaPlayerStateListenerList != null) {
            for (IMediaPlayerStateListener iMediaPlayerStateListener : this.mediaPlayerStateListenerList) {
                iMediaPlayerStateListener.onPlayerState(state, this.playTag);
            }
            if (state == -1001 || state == 3 || state == 4) {
                this.playTag = null;
            }
        }
    }

    public static UniMediaPlayer getInstance() {
        if (uniMediaPlayer != null) {
            return uniMediaPlayer;
        }
        throw new RuntimeException("UniMediaPlayer need init first");
    }

    public static void init(Context context) {
        uniMediaPlayer = new UniMediaPlayer(context);
    }

    private boolean requestFocus() {
        return this.mAudioManager.requestAudioFocus(this.mAudioFocusChangeListener, 3, 1) == 1;
    }

    public void addIMediaPlayerStateListener(IMediaPlayerStateListener listener) {
        if (listener != null && !this.mediaPlayerStateListenerList.contains(listener)) {
            this.mediaPlayerStateListenerList.add(listener);
        }
    }

    public String getPlayTag() {
        return this.playTag;
    }

    public boolean isPlayFile(String file) {
        return this.playTag != null && this.playTag.equals(file) && this.mMediaPlayerState == 1;
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
            callBackPlayerState(2);
        }
    }

    public void pausePushMusic() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.pause();
        }
    }

    public void play(String path, IMediaPlayerStateListener listener) {
        callBackPlayerState(3);
        this.playTag = new String(path);
        addIMediaPlayerStateListener(listener);
        this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass1 */

            public void onCompletion(MediaPlayer mp) {
                LogMgr.d(UniMediaPlayer.TAG, "onCompletion");
                UniMediaPlayer.this.callBackPlayerState(4);
                UniMediaPlayer.this.mAudioManager.abandonAudioFocus(UniMediaPlayer.this.mAudioFocusChangeListener);
            }
        });
        this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass2 */

            public boolean onError(MediaPlayer mp, int what, int extra) {
                LogMgr.d(UniMediaPlayer.TAG, "onError");
                UniMediaPlayer.this.callBackPlayerState(PlayerState.MPS_ERROR);
                return false;
            }
        });
        File file = new File(path);
        if (requestFocus() && file.exists() && file.length() > 0 && this.mMediaPlayer != null) {
            if (this.mMediaPlayerState == 2) {
                LogMgr.d(TAG, "mMediaPlayerState : 2");
                this.mMediaPlayer.start();
                callBackPlayerState(1);
                return;
            }
            this.mMediaPlayer.stop();
            try {
                this.mMediaPlayer.reset();
                this.mMediaPlayer.setDataSource(path);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.prepareAsync();
                this.mMediaPlayer.setVolume(1.0f, 1.0f);
                this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass3 */

                    public void onPrepared(MediaPlayer mp) {
                        LogMgr.d(UniMediaPlayer.TAG, "onPrepared");
                        UniMediaPlayer.this.mMediaPlayer.start();
                        UniMediaPlayer.this.callBackPlayerState(1);
                    }
                });
            } catch (Exception e) {
                LogMgr.e(TAG, "paly error = " + e.toString());
                callBackPlayerState(4);
            }
        }
    }

    public void playBeepSound(int rawId) {
        playBeepSound("", rawId, null, false);
    }

    public void playBeepSound(String tag, int rawId, final IMediaPlayerStateListener stateListener, boolean isLooping) {
        this.playTag = new String(tag);
        LogMgr.d(TAG, "---->>playBeepSound isLooping = " + isLooping);
        stop();
        if (stateListener != null) {
            addIMediaPlayerStateListener(stateListener);
        } else {
            LogMgr.d(TAG, "---->>playBeepSound stateListener is null");
        }
        AssetFileDescriptor openRawResourceFd = this.appContent.getResources().openRawResourceFd(rawId);
        if (this.mMediaPlayer == null) {
            throw new RuntimeException("MediaPlayer has been released.");
        }
        this.mMediaPlayer.setOnCompletionListener(null);
        if (this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.stop();
        }
        this.mMediaPlayer.reset();
        this.mMediaPlayer.setLooping(isLooping);
        try {
            this.mMediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            this.mMediaPlayer.setAudioStreamType(3);
            this.mMediaPlayer.prepareAsync();
            this.mMediaPlayer.setVolume(0.3f, 0.3f);
            this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass13 */

                public void onPrepared(MediaPlayer mp) {
                    LogMgr.d(UniMediaPlayer.TAG, "---->>playBeepSound onPrepared mMediaPlayer.isPlaying()=" + UniMediaPlayer.this.mMediaPlayer.isPlaying());
                    if (stateListener != null) {
                        UniMediaPlayer.this.callBackPlayerState(1);
                    }
                    if (!UniMediaPlayer.this.mMediaPlayer.isPlaying()) {
                        UniMediaPlayer.this.mMediaPlayer.start();
                    }
                }
            });
            this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass14 */

                public void onCompletion(MediaPlayer mp) {
                    LogMgr.d(UniMediaPlayer.TAG, "---->>playBeepSound onCompletion");
                    if (stateListener != null) {
                        UniMediaPlayer.this.callBackPlayerState(4);
                    }
                }
            });
            try {
                openRawResourceFd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            LogMgr.e(TAG, "--->>playBeepSound has a exception");
            try {
                openRawResourceFd.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } catch (Throwable th) {
            try {
                openRawResourceFd.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
        LogMgr.d(TAG, "---->>playBeepSound end");
    }

    public void playBeepSoundLooping(String tag, int rawId, IMediaPlayerStateListener stateListener) {
        playBeepSound(tag, rawId, stateListener, true);
    }

    public void playUrl(String videoUrl, String tag) {
        callBackPlayerState(3);
        this.playTag = new String(tag);
        this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass4 */

            public void onCompletion(MediaPlayer mp) {
                LogMgr.d(UniMediaPlayer.TAG, "onCompletion");
                UniMediaPlayer.this.callBackPlayerState(4);
                UniMediaPlayer.this.mAudioManager.abandonAudioFocus(UniMediaPlayer.this.mAudioFocusChangeListener);
            }
        });
        this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass5 */

            public boolean onError(MediaPlayer mp, int what, int extra) {
                LogMgr.d(UniMediaPlayer.TAG, "onError");
                UniMediaPlayer.this.callBackPlayerState(PlayerState.MPS_ERROR);
                return false;
            }
        });
        if (requestFocus() && this.mMediaPlayer != null) {
            LogMgr.d(TAG, "111 playUrl mMediaPlayerState : " + this.mMediaPlayerState);
            if (this.mMediaPlayerState == 2) {
                this.mMediaPlayer.start();
                callBackPlayerState(1);
                return;
            }
            this.mMediaPlayer.stop();
            try {
                this.mMediaPlayer.reset();
                this.mMediaPlayer.setDataSource(videoUrl);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.prepareAsync();
                this.mMediaPlayer.setVolume(1.0f, 1.0f);
                this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass6 */

                    public void onPrepared(MediaPlayer mp) {
                        LogMgr.d(UniMediaPlayer.TAG, "onPrepared");
                        UniMediaPlayer.this.mMediaPlayer.start();
                        UniMediaPlayer.this.callBackPlayerState(1);
                    }
                });
            } catch (Exception e) {
                LogMgr.e(TAG, "paly error = " + e.toString());
                callBackPlayerState(4);
            }
        }
    }

    public void playUrl(String videoUrl, String tag, IMediaPlayerStateListener listener) {
        this.playTag = new String(tag);
        addIMediaPlayerStateListener(listener);
        this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass7 */

            public void onCompletion(MediaPlayer mp) {
                LogMgr.d(UniMediaPlayer.TAG, "onCompletion");
                UniMediaPlayer.this.callBackPlayerState(4);
                UniMediaPlayer.this.mAudioManager.abandonAudioFocus(UniMediaPlayer.this.mAudioFocusChangeListener);
            }
        });
        this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass8 */

            public boolean onError(MediaPlayer mp, int what, int extra) {
                LogMgr.d(UniMediaPlayer.TAG, "onError");
                UniMediaPlayer.this.callBackPlayerState(PlayerState.MPS_ERROR);
                return false;
            }
        });
        if (requestFocus() && this.mMediaPlayer != null) {
            LogMgr.d(TAG, "222 playUrl mMediaPlayerState : " + this.mMediaPlayerState);
            if (this.mMediaPlayerState == 2) {
                this.mMediaPlayer.start();
                callBackPlayerState(1);
                return;
            }
            this.mMediaPlayer.stop();
            try {
                this.mMediaPlayer.reset();
                this.mMediaPlayer.setDataSource(videoUrl);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.prepareAsync();
                this.mMediaPlayer.setVolume(1.0f, 1.0f);
                this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass9 */

                    public void onPrepared(MediaPlayer mp) {
                        LogMgr.d(UniMediaPlayer.TAG, "onPrepared");
                        UniMediaPlayer.this.mMediaPlayer.start();
                        UniMediaPlayer.this.callBackPlayerState(1);
                    }
                });
            } catch (Exception e) {
                LogMgr.e(TAG, "paly error = " + e.toString());
                callBackPlayerState(4);
            }
        }
    }

    public void playUrl(String videoUrl, String tag, IMediaPlayerStateListener listener, final boolean isChatSession) {
        LogMgr.d(TAG, "playUrl videoUrl : " + videoUrl + " tag=" + tag + "  isChatSession=" + isChatSession);
        callBackPlayerState(3);
        this.playTag = "";
        this.playTag = new String(tag);
        addIMediaPlayerStateListener(listener);
        this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass10 */

            public void onCompletion(MediaPlayer mp) {
                LogMgr.d(UniMediaPlayer.TAG, "onCompletion");
                UniMediaPlayer.this.callBackPlayerState(4);
                UniMediaPlayer.this.mAudioManager.abandonAudioFocus(UniMediaPlayer.this.mAudioFocusChangeListener);
            }
        });
        this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass11 */

            public boolean onError(MediaPlayer mp, int what, int extra) {
                LogMgr.d(UniMediaPlayer.TAG, "onError what:" + what + ",extra:" + extra);
                UniMediaPlayer.this.callBackPlayerState(PlayerState.MPS_ERROR);
                return false;
            }
        });
        LogMgr.d(TAG, "mMediaPlayerState : " + this.mMediaPlayerState);
        if (requestFocus() && this.mMediaPlayer != null) {
            if (this.mMediaPlayerState == 2) {
                this.mMediaPlayer.start();
                callBackPlayerState(1);
                return;
            }
            this.mMediaPlayer.stop();
            try {
                this.mMediaPlayer.reset();
                this.mMediaPlayer.setDataSource(videoUrl);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.prepareAsync();
                this.mMediaPlayer.setVolume(1.0f, 1.0f);
                this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    /* class com.unisound.vui.common.media.UniMediaPlayer.AnonymousClass12 */

                    public void onPrepared(MediaPlayer mp) {
                        LogMgr.d(UniMediaPlayer.TAG, "onPrepared");
                        if (isChatSession) {
                            UniMediaPlayer.this.callBackPlayerState(5);
                            return;
                        }
                        UniMediaPlayer.this.mMediaPlayer.start();
                        UniMediaPlayer.this.callBackPlayerState(1);
                    }
                });
            } catch (Exception e) {
                LogMgr.e(TAG, "paly error = " + e.toString());
                callBackPlayerState(4);
            }
        }
    }

    public void release() {
        LogMgr.d(TAG, "release mMediaPlayerState : " + this.mMediaPlayerState);
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            callBackPlayerState(0);
            this.mAudioManager.abandonAudioFocus(this.mAudioFocusChangeListener);
            this.mMediaPlayer = null;
        }
    }

    public void removeIMediaPlayerStateListener(IMediaPlayerStateListener listener) {
        if (listener != null && this.mediaPlayerStateListenerList.contains(listener)) {
            this.mediaPlayerStateListenerList.remove(listener);
        }
    }

    public void resume() {
        if (this.mMediaPlayer != null && this.mMediaPlayerState == 2) {
            this.mMediaPlayer.start();
            callBackPlayerState(1);
        }
    }

    public void resumePushMusic() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.start();
        }
    }

    public void start() {
        LogMgr.d(TAG, "mMediaPlayer start  mMediaPlayerState: " + this.mMediaPlayerState);
        if (this.mMediaPlayer != null && this.mMediaPlayerState == 5) {
            this.mMediaPlayer.start();
            callBackPlayerState(1);
        }
    }

    public void stop() {
        LogMgr.d(TAG, "stop mMediaPlayerState : " + this.mMediaPlayerState);
        if (this.mMediaPlayer != null && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.stop();
            callBackPlayerState(3);
        }
    }
}

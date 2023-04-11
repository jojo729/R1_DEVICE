package com.unisound.vui.handler.session.music;

import android.content.Context;
import android.media.AudioManager;
import com.unisound.vui.util.LogMgr;

public abstract class AbstractPlayController implements PlayController, AudioManager.OnAudioFocusChangeListener {
    static final int AUDIO_FOCUSED = 2;
    static final int AUDIO_NO_FOCUS_CAN_DUCK = 1;
    static final int AUDIO_NO_FOCUS_NO_DUCK = 0;
    private static final String TAG = AbstractPlayController.class.getSimpleName();
    int audioFocus = 0;
    private AudioManager audioManager;
    boolean playOnFocusGain;

    public AbstractPlayController(Context context) {
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void onAudioFocusChange(int focusChange) {
        boolean canDuck;
        int i;
        LogMgr.d(TAG, "onAudioFocusChange. focusChange=" + focusChange);
        if (focusChange == 1) {
            this.audioFocus = 2;
        } else if (focusChange == -1 || focusChange == -2 || focusChange == -3) {
            if (focusChange == -3) {
                canDuck = true;
            } else {
                canDuck = false;
            }
            if (canDuck) {
                i = 1;
            } else {
                i = 0;
            }
            this.audioFocus = i;
            if (isPlaying()) {
                this.playOnFocusGain = true;
                LogMgr.d(TAG, "onAudioFocusChange playOnFocusGain is true");
            } else {
                this.playOnFocusGain = false;
                LogMgr.d(TAG, "onAudioFocusChange playOnFocusGain is false");
            }
        } else {
            LogMgr.d(TAG, "onAudioFocusChange: Ignoring unsupported focusChange: ", Integer.valueOf(focusChange));
        }
        if (isPrepared()) {
            configMediaPlayerState();
        }
    }

    public void giveUpAudioFocus() {
        LogMgr.d(TAG, "giveUpAudioFocus");
        if (this.audioFocus == 2 && this.audioManager.abandonAudioFocus(this) == 1) {
            this.audioFocus = 0;
        }
    }

    public void tryToGetAudioFocus() {
        LogMgr.d(TAG, "tryToGetAudioFocus");
        if (this.audioFocus != 2 && this.audioManager.requestAudioFocus(this, 4, 1) == 1) {
            this.audioFocus = 2;
        }
    }
}

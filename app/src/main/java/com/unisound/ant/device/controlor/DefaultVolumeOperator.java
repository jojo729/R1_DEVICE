package com.unisound.ant.device.controlor;

import android.content.Context;
import android.media.AudioManager;
import com.unisound.ant.device.listener.VolumeListener;
import com.unisound.vui.util.LogMgr;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultVolumeOperator implements VolumeOperator {
    private static final String TAG = "DefaultVolumeOperator";
    private static final int VOLUME_INTERVAL = 1;
    private static final int VOLUME_MIN = 1;
    private static DefaultVolumeOperator sInstance;
    private AudioManager mAudioManager;
    private Integer mMaxMusicVolume;
    private int mVolumeBeforeMuteOn = 7;
    private CopyOnWriteArrayList<VolumeListener> mVolumeListeners;

    private DefaultVolumeOperator(Context context) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mVolumeListeners = new CopyOnWriteArrayList<>();
    }

    public static void init(Context context) {
        sInstance = new DefaultVolumeOperator(context);
    }

    public static DefaultVolumeOperator getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DefaultVolumeOperator.class) {
                if (sInstance == null) {
                    sInstance = new DefaultVolumeOperator(context);
                }
            }
        }
        return sInstance;
    }

    public void registerListener(VolumeListener listener) {
        if (listener != null && !this.mVolumeListeners.contains(listener)) {
            this.mVolumeListeners.add(listener);
        }
    }

    public void unRegisterListener(VolumeListener listener) {
        if (this.mVolumeListeners.contains(listener)) {
            this.mVolumeListeners.remove(listener);
        }
    }

    public boolean isMinMusicVolume() {
        return getMusicCurrentVolume() == getMusicMinVolume();
    }

    public boolean isMaxMusicVolume() {
        return getMusicCurrentVolume() == getMusicMaxVolume();
    }

    @Override // com.unisound.ant.device.controlor.VolumeOperator
    public void setVolumeRaise() {
        setVolume(getMusicCurrentVolume() + 1);
    }

    @Override // com.unisound.ant.device.controlor.VolumeOperator
    public void setVolumeLower() {
        setVolume(getMusicCurrentVolume() - 1);
    }

    @Override // com.unisound.ant.device.controlor.VolumeOperator
    public void setVolumeMax() {
        LogMgr.d(TAG, "setVolumeMax");
        setVolume(getMusicMaxVolume());
    }

    @Override // com.unisound.ant.device.controlor.VolumeOperator
    public void setVolumeMin() {
        LogMgr.d(TAG, "setVolumeMin");
        setVolume(1);
    }

    @Override // com.unisound.ant.device.controlor.VolumeOperator
    public void setMuteOn() {
        closeMute();
        LogMgr.d(TAG, "setMuteOn");
        this.mVolumeBeforeMuteOn = getMusicCurrentVolume();
        setVolumeMin();
    }

    @Override // com.unisound.ant.device.controlor.VolumeOperator
    public void setMuteOff() {
        LogMgr.d(TAG, "setMuteOff");
        setVolume(this.mVolumeBeforeMuteOn);
    }

    @Override // com.unisound.ant.device.controlor.VolumeOperator
    public void setVoiceVolume(int volume) {
        LogMgr.d(TAG, "setVoiceVolume volume=" + volume);
        setVolume(volume);
    }

    @Override // com.unisound.ant.device.controlor.VolumeOperator
    public void setVoiceVolume(float percent) {
        LogMgr.d(TAG, "setVoiceVolume percent=" + percent);
        setVolume(Math.round(((float) getMusicMaxVolume()) * percent));
    }

    @Override // com.unisound.ant.device.controlor.VolumeOperator
    public int getCurrentVolume() {
        return getMusicCurrentVolume();
    }

    @Override // com.unisound.ant.device.controlor.VolumeOperator
    public int getMaxVolume() {
        return getMusicMaxVolume();
    }

    private void setVolume(int volume) {
        closeMute();
        int musicMinVolume = getMusicMinVolume();
        int musicMaxVolume = getMusicMaxVolume();
        if (volume < musicMinVolume) {
            volume = musicMinVolume;
        }
        if (volume > musicMaxVolume) {
            volume = musicMaxVolume;
        }
        this.mAudioManager.setStreamVolume(3, volume, 0);
        fireVolumeChange();
    }

    private int getMusicCurrentVolume() {
        return this.mAudioManager.getStreamVolume(3);
    }

    private int getMusicMinVolume() {
        return 1;
    }

    private int getMusicMaxVolume() {
        if (this.mMaxMusicVolume == null) {
            this.mMaxMusicVolume = Integer.valueOf(this.mAudioManager.getStreamMaxVolume(3));
        }
        LogMgr.d(TAG, "mMaxMusicVolume :" + this.mMaxMusicVolume);
        return this.mMaxMusicVolume.intValue();
    }

    private void closeMute() {
        if (getMusicCurrentVolume() == 0) {
            this.mAudioManager.setStreamMute(3, false);
        }
    }

    private void fireVolumeChange() {
        Iterator<VolumeListener> volumeListenerIterator = this.mVolumeListeners.iterator();
        int currentVolume = getCurrentVolume();
        int maxVolume = getMusicMaxVolume();
        while (volumeListenerIterator.hasNext()) {
            volumeListenerIterator.next().onVolumeChanged(Integer.valueOf(currentVolume), Integer.valueOf(maxVolume), Float.valueOf(((float) currentVolume) / ((float) maxVolume)));
        }
    }
}

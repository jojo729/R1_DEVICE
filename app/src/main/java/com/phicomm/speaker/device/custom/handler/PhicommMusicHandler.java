package com.phicomm.speaker.device.custom.handler;

import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.status.PhicommDeviceStatusProcessor;
import com.phicomm.speaker.device.utils.LogUtils;
import com.unisound.ant.device.controlor.DefaultVolumeOperator;
import com.unisound.ant.device.listener.MusicStatusListener;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.session.music.CommonPlayer;
import com.unisound.vui.handler.session.music.DefaultMusicHandler;
import com.unisound.vui.util.LogMgr;

public class PhicommMusicHandler extends DefaultMusicHandler implements MusicStatusListener {
    private static final String TAG = PhicommMusicHandler.class.getSimpleName();
    private Context mContext;
    private Handler mHandler = new Handler();
    private RecoveryWakeupWordRunnable mRecoveryWakeupWordRunnable;

    public PhicommMusicHandler(CommonPlayer commonPlayer) {
        super(commonPlayer);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.session.music.DefaultMusicHandler, com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        this.mContext = ctx.androidContext();
        return super.onASREventEngineInitDone(ctx);
    }

    @Override // com.unisound.vui.handler.session.music.DefaultMusicHandler
    public void onServiceConnected(ComponentName name, IBinder service) {
        super.onServiceConnected(name, service);
        this.playController.setStateListener(this);
    }

    @Override // com.unisound.ant.device.listener.MusicStatusListener
    public void onMusicStatusChanged(int status) {
        float currentBenchMark = getEffectBenchmark(status);
        LogMgr.d(TAG, "onMusicStatusChanged status:" + status + ",currentBenchMark" + currentBenchMark);
        ANTConfigPreference.effectWakeupBenchmark = currentBenchMark;
        if (status == 2) {
            LogUtils.d(TAG, "start task 'recovery wakeup work'");
            if (this.mRecoveryWakeupWordRunnable == null) {
                this.mRecoveryWakeupWordRunnable = new RecoveryWakeupWordRunnable();
            }
            this.mHandler.postDelayed(this.mRecoveryWakeupWordRunnable, 1800000);
        } else if (status == 3 && this.mRecoveryWakeupWordRunnable != null) {
            LogUtils.d(TAG, "stop task 'recovery wakeup work'");
            this.mHandler.removeCallbacks(this.mRecoveryWakeupWordRunnable);
            this.mRecoveryWakeupWordRunnable = null;
        }
    }

    private float getEffectBenchmark(int status) {
        if (status == 3) {
            return getCurrentMusicWakeupBenchmark();
        }
        return ANTConfigPreference.wakeupBenchmark;
    }

    private float getCurrentMusicWakeupBenchmark() {
        int currentVolume = DefaultVolumeOperator.getInstance(this.mContext).getCurrentVolume();
        LogMgr.d(TAG, "onMusicStatusChanged music playing  volume:" + currentVolume);
        if (currentVolume < 8) {
            return ANTConfigPreference.wakeupBenchmark;
        }
        if (currentVolume < 10) {
            return ANTConfigPreference.wakeupBenchmarkForMusicPlaying;
        }
        if (currentVolume < 12) {
            return ANTConfigPreference.wakeupBenchmarkForMusicPlayingTwo;
        }
        return ANTConfigPreference.wakeupBenchmarkForMusicPlayingThree;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.session.music.DefaultMusicHandler, com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx, String type) {
        if (PhicommDeviceStatusProcessor.getInstance().getDeviceStatus() != 3) {
            super.doInterrupt(ctx, type);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.session.music.DefaultMusicHandler, com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doResume(ANTHandlerContext ctx) {
        if (PhicommDeviceStatusProcessor.getInstance().getDeviceStatus() != 3) {
            super.doResume(ctx);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.session.music.DefaultMusicHandler
    public void firNluMusicWakeupOperate(String wakeupWord) {
        if (this.mContext.getString(R.string.music_volume_increase).equals(wakeupWord)) {
            DefaultVolumeOperator.getInstance(this.mContext).setVolumeRaise();
        } else if (this.mContext.getString(R.string.music_volume_decrease).equals(wakeupWord)) {
            DefaultVolumeOperator.getInstance(this.mContext).setVolumeLower();
        } else {
            super.firNluMusicWakeupOperate(wakeupWord);
        }
    }

    private class RecoveryWakeupWordRunnable implements Runnable {
        private RecoveryWakeupWordRunnable() {
        }

        public void run() {
            PhicommMusicHandler.this.recoveryDefaultWakeupWord(PhicommMusicHandler.this.ctx);
        }
    }
}

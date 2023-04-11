package com.unisound.vui.handler.session.memo.ring;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import com.unisound.vui.handler.session.memo.utils.MemoConstants;
import com.unisound.vui.handler.session.memo.utils.RingingUtils;
import com.unisound.vui.util.LogMgr;

public class MemoRingingService extends Service {
    public final String TAG = (MemoConstants.MEMO_TAG + getClass().getSimpleName());
    private final IBinder mBinder = new LocalBinder();
    private RingingPlayer mRingPlayer;

    public void onCreate() {
        super.onCreate();
        LogMgr.d(this.TAG, "MemoRingingService created!");
        this.mRingPlayer = new RingingPlayer(this);
    }

    public IBinder onBind(Intent intent) {
        LogMgr.d(this.TAG, "MemoRingingService onBind");
        return this.mBinder;
    }

    public boolean onUnbind(Intent intent) {
        LogMgr.d(this.TAG, "MemoRingingService onUnbind");
        return super.onUnbind(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        LogMgr.d(this.TAG, "onCreate destroyed!");
    }

    public void stopPlayRing() {
        if (this.mRingPlayer != null) {
            this.mRingPlayer.stop();
        }
    }

    public void startPlayingRing(boolean isLooping, String ringName) {
        LogMgr.d(this.TAG, "startPlayingRing, ringName:" + ringName);
        this.mRingPlayer.play(Uri.fromFile(RingingUtils.getRingingFile(ringName, RingingUtils.RINGING_DEFAULT)), isLooping);
    }

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public MemoRingingService getService() {
            return MemoRingingService.this;
        }
    }
}

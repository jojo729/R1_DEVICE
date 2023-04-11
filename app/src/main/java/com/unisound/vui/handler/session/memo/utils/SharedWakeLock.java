package com.unisound.vui.handler.session.memo.utils;

import android.content.Context;
import android.os.PowerManager;
import com.unisound.vui.util.LogMgr;

public class SharedWakeLock {
    private static final String TAG = "memolog-SharedWakeLock";
    private static SharedWakeLock sWakeLock;
    private PowerManager.WakeLock mFullWakeLock;
    private PowerManager.WakeLock mPartialWakeLock;

    private SharedWakeLock(Context context) {
        PowerManager pm = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.mFullWakeLock = pm.newWakeLock(805306394, TAG);
        this.mPartialWakeLock = pm.newWakeLock(1, TAG);
    }

    public static SharedWakeLock get(Context context) {
        if (sWakeLock == null) {
            synchronized (SharedWakeLock.class) {
                if (sWakeLock == null) {
                    sWakeLock = new SharedWakeLock(context);
                }
            }
        }
        return sWakeLock;
    }

    public void acquireFullWakeLock() {
        if (!this.mFullWakeLock.isHeld()) {
            this.mFullWakeLock.acquire();
            LogMgr.d(TAG, "Acquired Full WAKE_LOCK!");
        }
    }

    public void releaseFullWakeLock() {
        if (this.mFullWakeLock.isHeld()) {
            this.mFullWakeLock.release();
            LogMgr.d(TAG, "Released Full WAKE_LOCK!");
        }
    }

    public void acquirePartialWakeLock() {
        if (!this.mPartialWakeLock.isHeld()) {
            this.mPartialWakeLock.acquire();
            LogMgr.d(TAG, "Acquired Partial WAKE_LOCK!");
        }
    }

    public void releasePartialWakeLock() {
        if (this.mPartialWakeLock.isHeld()) {
            this.mPartialWakeLock.release();
            LogMgr.d(TAG, "Released Partial WAKE_LOCK!");
        }
    }
}

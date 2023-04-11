package com.phicomm.speaker.device.custom.status;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.ParcelableUtil;
import com.phicomm.speaker.device.custom.status.SceneStateMachine;
import com.phicomm.speaker.device.ipc.IpcManager;
import com.phicomm.speaker.device.ipc.IpcReceiver;
import com.phicomm.speaker.device.utils.LogUtils;
import com.unisound.vui.util.SharedPreferencesHelper;
import java.util.ArrayList;
import java.util.List;

public class PhicommDeviceStatusProcessor implements IpcReceiver, SceneStateMachine.StatusChangedListener {
    public static final String SP_DEVICE_STATUS = "deviceStatus";
    public static final int STATUS_BLUETOOTH = 3;
    public static final int STATUS_DORMANT = 5;
    public static final int STATUS_MUSIC = 1;
    public static final int STATUS_READY = 0;
    public static final int STATUS_SETUP_NET = 2;
    public static final String TAG = "PhicommDeviceStatusProcessor";
    @SuppressLint({"StaticFieldLeak"})
    private static Context sContext;
    private Integer mDeviceStatus;
    private IpcManager mIpcManager;
    private List<OnDeviceStatusChangedListener> mListeners;
    private SceneStateMachine mSceneStateMachine;

    public interface OnDeviceStatusChangedListener {
        void onDeviceStatusChanged(int i, int i2);
    }

    private PhicommDeviceStatusProcessor() {
        this.mDeviceStatus = null;
        this.mIpcManager = IpcManager.defaultInstance(sContext);
        this.mIpcManager.registerReceiver(this, 32768);
    }

    public void startMonitorStatus() {
        this.mSceneStateMachine = new SceneStateMachine(sContext);
        this.mSceneStateMachine.registerStatusChangedListener(this);
        this.mSceneStateMachine.start();
    }

    public static PhicommDeviceStatusProcessor getInstance() {
        return Holder.sProcessor;
    }

    public static void init(Context context) {
        if (context instanceof Application) {
            sContext = context;
        } else {
            sContext = context.getApplicationContext();
        }
    }

    public void release() {
        this.mIpcManager.unRegisterReceiver(this);
        if (this.mListeners != null) {
            this.mListeners.clear();
            this.mListeners = null;
        }
    }

    public void addDeviceStatusChangedListener(OnDeviceStatusChangedListener listener) {
        synchronized (PhicommDeviceStatusProcessor.class) {
            if (this.mListeners == null) {
                this.mListeners = new ArrayList();
            }
        }
        if (!this.mListeners.contains(listener)) {
            this.mListeners.add(listener);
        }
    }

    public void removeDeviceStatusChangedListener(OnDeviceStatusChangedListener listener) {
        this.mListeners.remove(listener);
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
    }

    public int getDeviceStatus() {
        if (this.mDeviceStatus == null) {
            this.mDeviceStatus = Integer.valueOf(SharedPreferencesHelper.getInstance(sContext).getIntValue(SP_DEVICE_STATUS, 0));
        }
        return this.mDeviceStatus.intValue();
    }

    @Override // com.phicomm.speaker.device.ipc.IpcReceiver
    public void onReceive(int domain, int subDomain, int sessionId, Object data) {
        if (subDomain == 51) {
            if (((Boolean) ((ParcelableUtil) data).getValue()).booleanValue()) {
                sendStatusMessage(103);
            } else {
                sendStatusMessage(104);
            }
        } else if (subDomain == 52) {
            if (((Boolean) ((ParcelableUtil) data).getValue()).booleanValue()) {
                sendStatusMessage(107);
            } else {
                sendStatusMessage(108);
            }
        } else if (subDomain != 53) {
        } else {
            if (((Boolean) ((ParcelableUtil) data).getValue()).booleanValue()) {
                sendStatusMessage(105);
            } else {
                sendStatusMessage(106);
            }
        }
    }

    private void setDeviceStatus(int deviceStatus) {
        this.mDeviceStatus = Integer.valueOf(deviceStatus);
        SharedPreferencesHelper.getInstance(sContext).saveIntValue(SP_DEVICE_STATUS, deviceStatus);
    }

    @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.StatusChangedListener
    public void onStateChanged(int newState) {
        int prevStatus = getDeviceStatus();
        LogUtils.d(TAG, "onReceived device status = " + newState + ", prevStatus = " + prevStatus);
        setDeviceStatus(newState);
        this.mIpcManager.sendMessage(16384, 4, -1, ParcelableUtil.obtain(true));
        if (this.mListeners != null) {
            for (OnDeviceStatusChangedListener listener : this.mListeners) {
                listener.onDeviceStatusChanged(prevStatus, newState);
            }
        }
    }

    private static class Holder {
        @SuppressLint({"StaticFieldLeak"})
        private static PhicommDeviceStatusProcessor sProcessor = new PhicommDeviceStatusProcessor();

        private Holder() {
        }
    }

    public void sendStatusMessage(int what) {
        this.mSceneStateMachine.sendMessage(what);
    }
}

package com.phicomm.speaker.device.custom.udid;

import android.content.Context;
import android.os.ParcelableUtil;
import android.util.Log;
import com.phicomm.speaker.device.ipc.IpcManager;
import com.phicomm.speaker.device.ipc.IpcReceiver;
import com.phicomm.speaker.device.utils.LogUtils;
import com.phicomm.speaker.device.utils.PhicommMessageManager;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.engine.ANTEngineOption;

public class UDIDProcessor implements IpcReceiver {
    public static final String TAG = "UDIDProcessor";
    private ANTEngine mEngine;
    private IpcManager mMsgCenter;

    public UDIDProcessor(Context context, ANTEngine engine) {
        this.mMsgCenter = IpcManager.defaultInstance(context);
        this.mEngine = engine;
    }

    public void register() {
        Log.d(TAG, "regitster");
        this.mMsgCenter.registerReceiver(this, 16384);
    }

    @Override // com.phicomm.speaker.device.ipc.IpcReceiver
    public void onReceive(int domain, int subDomain, int sessionId, Object data) {
        if (subDomain == PhicommMessageManager.DOMAIN_UDID) {
            String udid = (String) this.mEngine.config().getOption(ANTEngineOption.GENERAL_UDID);
            LogUtils.d(TAG, "-------udid = " + udid);
            this.mMsgCenter.sendMessage(32768, 2, -1, ParcelableUtil.obtain(udid));
        }
    }

    public void unregister() {
        Log.d(TAG, "regitster");
        this.mMsgCenter.unRegisterReceiver(this);
    }
}

package com.phicomm.speaker.device.custom.mqtt;

import android.content.Context;
import com.phicomm.speaker.device.custom.ipc.PhicommXController;
import com.unisound.ant.device.mqtt.OnMQTTStatusChangeListener;
import com.unisound.vui.util.LogMgr;

public class PhicommMQTTStatausChange implements OnMQTTStatusChangeListener {
    private static final String TAG = "PhicommMQTTStatausChange";
    private PhicommXController phicommXController;

    public PhicommMQTTStatausChange(Context context) {
        this.phicommXController = new PhicommXController(context);
    }

    @Override // com.unisound.ant.device.mqtt.OnMQTTStatusChangeListener
    public void onSuccess() {
        LogMgr.d(TAG, "onSuccess: ");
        this.phicommXController.syncBindSuccessEvent();
    }

    @Override // com.unisound.ant.device.mqtt.OnMQTTStatusChangeListener
    public void onFail(String errorCode) {
        int errorNumberCode;
        LogMgr.d(TAG, "onFail: " + errorCode);
        if (errorCode == null || errorCode.length() < 4) {
            errorNumberCode = Integer.valueOf("9999").intValue();
        } else {
            errorNumberCode = Integer.valueOf(errorCode.substring(3)).intValue();
        }
        this.phicommXController.syncBindFailEvent(errorNumberCode);
    }
}

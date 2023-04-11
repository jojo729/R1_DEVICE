package com.phicomm.speaker.device.custom.lights;

import android.content.Context;
import com.phicomm.speaker.device.custom.ipc.PhicommLightController;
import com.phicomm.speaker.device.utils.LogUtils;
import com.unisound.vui.handler.session.light.SimpleLightListener;

public class PhicommLightListener extends SimpleLightListener {
    private static final String TAG = PhicommLightListener.class.getSimpleName();
    private PhicommLightController phicommLightController;

    public PhicommLightListener(Context context) {
        this.phicommLightController = new PhicommLightController(context);
    }

    @Override // com.unisound.vui.handler.session.light.SimpleLightListener, com.unisound.vui.handler.session.light.LightListener
    public void onWakeupSuccess(int angle) {
        LogUtils.d(TAG, "----------onWakeupSuccess---------- 处理前角度 = " + angle);
        int currentLightIndex = PhicommLightIndexProcessor.getIndex(angle);
        LogUtils.d(TAG, "----------onWakeupSuccess---------- 处理后角度 = " + angle + " light index : " + currentLightIndex);
        this.phicommLightController.turnOnWakeupIndexLight(currentLightIndex);
    }

    @Override // com.unisound.vui.handler.session.light.SimpleLightListener, com.unisound.vui.handler.session.light.LightListener
    public void onRecognizeStart() {
        LogUtils.d(TAG, "----------onRecognizeStart----------");
        this.phicommLightController.turnOffALLWakeupLight();
        this.phicommLightController.turnOnLoadingLight();
    }

    @Override // com.unisound.vui.handler.session.light.SimpleLightListener, com.unisound.vui.handler.session.light.LightListener
    public void onInterrupt() {
        LogUtils.d(TAG, "----------onInterrupt----------");
        this.phicommLightController.turnOffALLWakeupLight();
        this.phicommLightController.turnOffLoadingLight();
    }

    @Override // com.unisound.vui.handler.session.light.SimpleLightListener, com.unisound.vui.handler.session.light.LightListener
    public void onTTSEnd() {
        this.phicommLightController.turnOffLoadingLight();
    }
}

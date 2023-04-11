package com.phicomm.speaker.device.custom.ringing;

import android.content.Context;
import com.phicomm.speaker.device.custom.ipc.PhicommLightController;
import com.phicomm.speaker.device.custom.ipc.PhicommXController;
import com.phicomm.speaker.device.custom.message.MessageSenderDelegate;
import com.phicomm.speaker.device.custom.status.PhicommDeviceStatusProcessor;
import com.phicomm.speaker.device.utils.LogUtils;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.handler.session.memo.DefaultMemoRingingHandler;
import com.unisound.vui.util.ExoConstants;

public class RingingEventProcessor implements DefaultMemoRingingHandler.OnRingingListener {
    private static final String TAG = RingingEventProcessor.class.getSimpleName();
    private Context mContext;
    private ANTEngine mEngine;
    private MessageSenderDelegate mMessageCenter = MessageSenderDelegate.getInstance();
    protected PhicommLightController mPhicommLightController;
    private PhicommXController mPhicommXController;
    private PhicommDeviceStatusProcessor mStatusProcessor = PhicommDeviceStatusProcessor.getInstance();

    public RingingEventProcessor(ANTEngine engine, Context context) {
        this.mContext = context;
        this.mEngine = engine;
        this.mPhicommXController = new PhicommXController(context);
        this.mPhicommLightController = new PhicommLightController(context);
    }

    @Override // com.unisound.vui.handler.session.memo.DefaultMemoRingingHandler.OnRingingListener
    public void onRingingStart() {
        processRingingEvent(true);
    }

    @Override // com.unisound.vui.handler.session.memo.DefaultMemoRingingHandler.OnRingingListener
    public void onRingingStop() {
        processRingingEvent(false);
    }

    private void processRingingEvent(boolean ringing) {
        int status = this.mStatusProcessor.getDeviceStatus();
        LogUtils.d(TAG, (ringing ? "准备响铃" : "响铃结束") + ", 当前状态" + status);
        switch (status) {
            case 0:
                if (ringing) {
                    ringStartOnStatusReady();
                    return;
                } else {
                    ringStoppedOnStatusReady();
                    return;
                }
            case 1:
                if (ringing) {
                    ringStartOnStatusMusic();
                    return;
                } else {
                    ringStoppedOnStatusMusic();
                    return;
                }
            case 2:
                if (ringing) {
                    ringStartOnStatusSetupNet();
                    return;
                } else {
                    ringStoppedOnStatusSetupNet();
                    return;
                }
            case 3:
                if (ringing) {
                    ringStartOnStatusBluetooth();
                    return;
                } else {
                    ringStoppedOnStatusBluetooth();
                    return;
                }
            case 4:
            default:
                return;
            case 5:
                if (ringing) {
                    ringStartOnStatusSleeping();
                    return;
                } else {
                    ringStoppedOnStatusSleeping();
                    return;
                }
        }
    }

    private void ringStartOnStatusReady() {
        turnOffWakeupLightsIfNeeds();
        this.mEngine.pipeline().fireUserEventTriggered(ExoConstants.DO_ACTIVE_INTERRUPT);
        this.mEngine.cancelTTS();
        this.mEngine.cancelASR();
        this.mEngine.enterWakeup(false);
    }

    private void ringStoppedOnStatusReady() {
    }

    private void ringStartOnStatusMusic() {
        turnOffWakeupLightsIfNeeds();
    }

    private void ringStoppedOnStatusMusic() {
    }

    private void ringStartOnStatusSetupNet() {
        this.mMessageCenter.send(262144, 2, -1, null);
        this.mEngine.enterWakeup(false);
    }

    private void ringStoppedOnStatusSetupNet() {
    }

    private void ringStartOnStatusBluetooth() {
        turnOffWakeupLightsIfNeeds();
        this.mMessageCenter.send(4, 2, -1, null);
    }

    private void turnOffWakeupLightsIfNeeds() {
        this.mPhicommLightController.turnOffALLWakeupLight();
    }

    private void ringStoppedOnStatusBluetooth() {
        this.mMessageCenter.send(4, 3, -1, null);
    }

    private void ringStartOnStatusSleeping() {
        this.mPhicommLightController.turnOffDormantLight();
        this.mPhicommXController.syncDeviceStatus(PhicommXController.DeviceStatus.Speech);
        this.mEngine.enterWakeup(false);
    }

    private void ringStoppedOnStatusSleeping() {
    }
}

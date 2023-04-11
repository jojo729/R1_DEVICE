package com.phicomm.speaker.device.custom.setting;

import android.content.Context;
import android.os.Handler;
import android.os.MessageDispatchManager;
import android.os.ParcelableUtil;
import android.util.Log;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.ipc.PhicommXController;
import com.phicomm.speaker.device.custom.status.PhicommDeviceStatusProcessor;
import com.phicomm.speaker.device.data.PhicommIotResponse;
import com.phicomm.speaker.device.utils.PhicommMessageManager;
import com.phicomm.speaker.device.utils.PhicommPerferenceUtil;
import com.unisound.ant.device.bean.DstServiceName;
import com.unisound.ant.device.bean.SelfDefinationRequestInfo;
import com.unisound.ant.device.bean.SelfDefinationResponseInfo;
import com.unisound.ant.device.sessionlayer.SessionRegister;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.handler.session.setting.DefaultSettingHandler;
import com.unisound.vui.handler.session.setting.SettingHandler;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;
import java.util.HashMap;
import java.util.Map;
import logreport.FullLog;
import nluparser.scheme.NLU;
import nluparser.scheme.Operator;

public class PhicommSettingHandler implements SettingHandler {
    public static final String TAG = "PhicommSettingHandler";
    private ANTEngine mANTEngine;
    private Context mContext;
    private final Handler mHandler = new Handler();
    private MessageDispatchManager mMsgCenter;
    private final PhicommXController mPhicommXController;

    public PhicommSettingHandler(Context context, ANTEngine engine) {
        this.mMsgCenter = PhicommMessageManager.messageCenter(context);
        this.mPhicommXController = new PhicommXController(context);
        this.mContext = context;
        this.mANTEngine = engine;
    }

    @Override // com.unisound.vui.handler.session.setting.SettingHandler
    public void handleOrder(String type) {
        char c = 65535;
        switch (type.hashCode()) {
            case -2060339247:
                if (type.equals(Operator.ACT_PHICOMM_BACKUP)) {
                    c = 3;
                    break;
                }
                break;
            case -1709623475:
                if (type.equals(Operator.ACT_PHICOMM_OTA)) {
                    c = 2;
                    break;
                }
                break;
            case 1252793481:
                if (type.equals(Operator.ACT_PHICOMM_VERSION)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 1:
                sendPhicommVersion();
                return;
            case 2:
                sendPhicommOTA();
                return;
            case 3:
                sendphicommBackup();
                return;
            default:
                LogMgr.d(TAG, "handleOrder: type '" + type + "' is ignored");
                return;
        }
    }

    @Override // com.unisound.vui.handler.session.setting.SettingHandler
    public void handleOrder(String type, Object data) {
        char c = 65535;
        switch (type.hashCode()) {
            case -1956904988:
                if (type.equals(DefaultSettingHandler.ACT_PHICOMM_OPEN_AMBIENTLIGHT)) {
                    c = 2;
                    break;
                }
                break;
            case -1535806438:
                if (type.equals(DefaultSettingHandler.ACT_PHICOMM_OPEN_SOUNDEFFECT)) {
                    c = 4;
                    break;
                }
                break;
            case -796427244:
                if (type.equals(DefaultSettingHandler.ACT_PHICOMM_CLOSE_AMBIENTLIGHT)) {
                    c = 3;
                    break;
                }
                break;
            case -205674991:
                if (type.equals(Operator.ACT_PHICOMM_OPEN_SLEEP_MODE)) {
                    c = '\b';
                    break;
                }
                break;
            case 718385642:
                if (type.equals(DefaultSettingHandler.ACT_PHICOMM_CLOSE_SOUNDEFFECT)) {
                    c = 5;
                    break;
                }
                break;
            case 1229674424:
                if (type.equals(Operator.ACT_PHICOMM_CLOSE_BLUETOOTH)) {
                    c = 7;
                    break;
                }
                break;
            case 1280959976:
                if (type.equals(Operator.ACT_PHICOMM_OPEN_BLUETOOTH)) {
                    c = 6;
                    break;
                }
                break;
            case 1893652548:
                if (type.equals(Operator.ACT_PHICOMM_SMART_HOME)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 1:
                sendPhicommSmartHomeCommand(data);
                return;
            case 2:
                openAmbientLight((NLU) data);
                return;
            case 3:
                closeAmbientLight((NLU) data);
                return;
            case 4:
                openSoundEffect((NLU) data);
                return;
            case 5:
                closeSoundEffect((NLU) data);
                return;
            case 6:
                openBluetoothMode((NLU) data);
                return;
            case 7:
                closeBluetoothMode((NLU) data);
                return;
            case '\b':
                openSleepMode((NLU) data);
                return;
            default:
                LogMgr.d(TAG, "handleOrder: type '" + type + "' is ignored");
                return;
        }
    }

    public void sendPhicommVersion() {
        LogMgr.d(TAG, "sendPhicommVersion");
        this.mMsgCenter.sendMessage(16384, 5, -1, null);
    }

    public void sendPhicommOTA() {
        LogMgr.d(TAG, "sendPhicommOTA");
        this.mMsgCenter.sendMessage(16384, 6, -1, null);
    }

    public void sendphicommBackup() {
        LogMgr.d(TAG, "sendphicommBackup");
        this.mMsgCenter.sendMessage(16384, 7, -1, null);
    }

    private void openBluetoothMode(NLU nlu) {
        String tts = this.mContext.getString(R.string.tts_open_bluetooth);
        if (PhicommDeviceStatusProcessor.getInstance().getDeviceStatus() != 3) {
            this.mPhicommXController.triggeredTropleClickEvent();
        } else {
            this.mANTEngine.playTTS(this.mContext.getString(R.string.tts_open_bluetooth));
        }
        sendFullLogToDeviceCenter(nlu, tts);
    }

    private void closeBluetoothMode(NLU nlu) {
        String tts = this.mContext.getString(R.string.tts_close_bluetooth);
        if (PhicommDeviceStatusProcessor.getInstance().getDeviceStatus() == 3) {
            this.mPhicommXController.closeBlueToothStatus();
        } else {
            tts = this.mContext.getString(R.string.tts_cant_help_you);
            this.mANTEngine.playTTS(tts);
        }
        sendFullLogToDeviceCenter(nlu, tts);
    }

    private void openAmbientLight(NLU nlu) {
        if (!PhicommPerferenceUtil.getAmbientLightState(this.mContext)) {
            this.mPhicommXController.openAmbientLight();
            PhicommPerferenceUtil.setAmbientLightState(this.mContext, true);
            onAmbientLightStatusChanged(true);
        }
        String text = this.mContext.getString(R.string.tts_open_ambientlight);
        this.mANTEngine.playTTS(text);
        sendFullLogToDeviceCenter(nlu, text);
    }

    private void closeAmbientLight(NLU nlu) {
        if (PhicommPerferenceUtil.getAmbientLightState(this.mContext)) {
            this.mPhicommXController.closeAmbientLight();
            PhicommPerferenceUtil.setAmbientLightState(this.mContext, false);
            onAmbientLightStatusChanged(false);
        }
        String text = this.mContext.getString(R.string.tts_close_ambientlight);
        this.mANTEngine.playTTS(text);
        sendFullLogToDeviceCenter(nlu, text);
    }

    private void openSoundEffect(NLU nlu) {
        this.mPhicommXController.openSoundEffect();
        String text = this.mContext.getString(R.string.tts_open_soundeffect);
        this.mANTEngine.playTTS(text);
        sendFullLogToDeviceCenter(nlu, text);
    }

    private void closeSoundEffect(NLU nlu) {
        this.mPhicommXController.closeSoundEffect();
        String text = this.mContext.getString(R.string.tts_close_soundeffect);
        this.mANTEngine.playTTS(text);
        sendFullLogToDeviceCenter(nlu, text);
    }

    private void onAmbientLightStatusChanged(boolean isAmbientLightOn) {
        Map<String, String> content = new HashMap<>();
        content.put(SelfDefinationRequestInfo.CURRENT_AMBIENT_LIGHT_STATUS, isAmbientLightOn ? "1" : "0");
        SessionRegister.getUpDownMessageManager().onReportStatus(DstServiceName.DST_SERVICE_SELF_DEFINATION, null, DstServiceName.DST_SERVICE_SELF_DEFINATION, DstServiceName.DST_SERVICE_SELF_DEFINATION, new SelfDefinationResponseInfo(SelfDefinationRequestInfo.MODIFY_AMBIENT_LIGHT_STATUS, 0, content));
    }

    private void openSleepMode(NLU nlu) {
        this.mPhicommXController.triggeredDoubleClickEvent();
        sendFullLogToDeviceCenter(nlu, this.mContext.getString(R.string.tts_start_dormant));
    }

    private void sendPhicommSmartHomeCommand(Object nluData) {
        String dataJson = JsonTool.toJson(nluData);
        LogMgr.d(TAG, "sendPhicommSmartHomeCommand: " + dataJson);
        NLU nlu = (NLU) nluData;
        PhicommIotReceiver iotReceiver = new PhicommIotReceiver(nlu);
        IoTRunnable ioTRunnable = new IoTRunnable(iotReceiver, nlu);
        iotReceiver.setIoTRunnable(ioTRunnable);
        this.mMsgCenter.registerMessageReceiver(iotReceiver, 16777216);
        this.mMsgCenter.sendMessage(8388608, 1, -1, ParcelableUtil.obtain(dataJson));
        this.mHandler.postDelayed(ioTRunnable, 15000);
    }

    /* access modifiers changed from: private */
    public class PhicommIotReceiver implements MessageDispatchManager.MessageReceiver {
        private IoTRunnable mIoTRunnable;
        private NLU mNLU;

        PhicommIotReceiver(NLU nlu) {
            this.mNLU = nlu;
        }

        public void setIoTRunnable(IoTRunnable ioTRunnable) {
            this.mIoTRunnable = ioTRunnable;
        }

        @Override // android.os.MessageDispatchManager.MessageReceiver
        public void notifyMsg(int what, int domain, int subdomain, Object data) {
            Log.d(PhicommSettingHandler.TAG, "PhicommIotReceiver recevied " + data);
            PhicommSettingHandler.this.mHandler.removeCallbacks(this.mIoTRunnable);
            PhicommSettingHandler.this.unregistPhicommMessageReceiver(this);
            try {
                String text = ((PhicommIotResponse) JsonTool.fromJson((String) ((ParcelableUtil) data).getValue(), PhicommIotResponse.class)).getText();
                PhicommSettingHandler.this.mANTEngine.playTTS(text);
                PhicommSettingHandler.this.sendFullLogToDeviceCenter(this.mNLU, text);
            } catch (Exception e) {
                LogMgr.e(PhicommSettingHandler.TAG, "received iot message from phicomm, but an error has occurred when processing : ", e);
            }
        }
    }

    /* access modifiers changed from: private */
    public class IoTRunnable implements Runnable {
        private PhicommIotReceiver mIotReceiver;
        private NLU mNLU;

        public IoTRunnable(PhicommIotReceiver iotReceiver, NLU nlu) {
            this.mIotReceiver = iotReceiver;
            this.mNLU = nlu;
        }

        public void run() {
            Log.d(PhicommSettingHandler.TAG, "recevied phicomm message timeout");
            PhicommSettingHandler.this.unregistPhicommMessageReceiver(this.mIotReceiver);
            String ttsData = PhicommSettingHandler.this.mContext.getString(R.string.tts_smart_home_timeout);
            PhicommSettingHandler.this.mANTEngine.playTTS(ttsData);
            PhicommSettingHandler.this.sendFullLogToDeviceCenter(this.mNLU, ttsData);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void unregistPhicommMessageReceiver(PhicommIotReceiver receiver) {
        if (this.mMsgCenter != null) {
            this.mMsgCenter.unregisterMessageReceiver(receiver);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendFullLogToDeviceCenter(NLU nlu, String ttsData) {
        this.mANTEngine.pipeline().fireUserEventTriggered(new FullLog(nlu, ttsData));
    }
}

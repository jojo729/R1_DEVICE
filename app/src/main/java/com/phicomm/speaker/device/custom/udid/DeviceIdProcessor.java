package com.phicomm.speaker.device.custom.udid;

import android.content.Context;
import android.os.ParcelableUtil;
import com.phicomm.speaker.device.custom.ipc.PhicommIpcRegister;
import com.phicomm.speaker.device.custom.message.MessageSenderDelegate;
import com.phicomm.speaker.device.ipc.IpcReceiver;
import com.phicomm.speaker.device.utils.PhicommMessageManager;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.SharedPreferencesHelper;
import com.unisound.vui.util.UserPerferenceUtil;

public class DeviceIdProcessor implements IpcReceiver {
    public static final String SP_DEVICE_ID = "deviceId";
    public static final String TAG = "DeviceIdProcessor";
    private Context mContext;
    private PhicommIpcRegister mIpcRegister;
    private MessageSenderDelegate mMessageSender = MessageSenderDelegate.getInstance();

    public DeviceIdProcessor(Context context) {
        this.mContext = context;
        this.mIpcRegister = new PhicommIpcRegister(context);
    }

    public void fetchDeviceId() {
        register();
        sendFetchDeviceIdOrder();
    }

    public String getDeviceId() {
        return SharedPreferencesHelper.getInstance(this.mContext).getStringValue(SP_DEVICE_ID, null);
    }

    private void register() {
        this.mIpcRegister.registerReceiver(this, 32768);
    }

    private void sendFetchDeviceIdOrder() {
        this.mMessageSender.send(true, 32768, PhicommMessageManager.DOMAIN_DEVICE_ID, 16384, 1, -1, null);
    }

    @Override // com.phicomm.speaker.device.ipc.IpcReceiver
    public void onReceive(int domain, int subDomain, int sessionId, Object data) {
        if (subDomain == PhicommMessageManager.DOMAIN_DEVICE_ID) {
            saveDeviceId(parseDeviceId(data));
            unRegister();
        }
    }

    private String parseDeviceId(Object obj) {
        return (String) ((ParcelableUtil) obj).getValue();
    }

    private void saveDeviceId(String deviceId) {
        UserPerferenceUtil.setDeviceId(this.mContext, deviceId);
        LogMgr.d(TAG, "saveDeviceId : " + deviceId);
    }

    private void unRegister() {
        this.mIpcRegister.unRegisterReceiver(this);
    }
}

package com.unisound.ant.device.sessionlayer;

import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.unisound.ant.device.bean.SessionData;
import com.unisound.ant.device.bean.UnisoundDeviceCommand;
import com.unisound.ant.device.profile.DstServiceProfile;
import com.unisound.ant.device.service.CloudResponse;
import com.unisound.ant.device.service.ServiceProtocolUtil;
import com.unisound.vui.util.LogMgr;

public class NluSessionLayer {
    private static final String TAG = "NluSessionLayer";

    public static UnisoundDeviceCommand parseNluSessionContent(String message) {
        LogMgr.d(TAG, "--->>dispatcherMessage message:" + message);
        if (TextUtils.isEmpty(message)) {
            return null;
        }
        CloudResponse<SessionData<JsonObject>> cloudResponse = ServiceProtocolUtil.getResponseHeader(message, new TypeToken<CloudResponse<SessionData<JsonObject>>>() {
            /* class com.unisound.ant.device.sessionlayer.NluSessionLayer.AnonymousClass1 */
        }.getType());
        String messageType = cloudResponse.getMessageType();
        SessionData sessionData = cloudResponse.getMessageBody();
        if (sessionData == null) {
            LogMgr.e(TAG, "--parse sessionData is null and this session is not effective");
            return null;
        } else if (!CloudResponse.MESSAGE_TYPE_INTENT_ACTION.equals(messageType)) {
            return null;
        } else {
            sessionData.getDstService().getUniCommand().getOperation();
            return dispatcherServiceMessage(sessionData.getDialog().getDstService(), sessionData.getDstService());
        }
    }

    public static UnisoundDeviceCommand dispatcherServiceMessage(String dstServiceName, DstServiceProfile dstServiceProfile) {
        LogMgr.d(TAG, "--->>dispatcherServiceMessage dstServiceName:" + dstServiceName);
        SessionExecuteHandler handler = SessionRegister.getSessionExecuter(dstServiceName);
        UnisoundDeviceCommand command = dstServiceProfile.getUniCommand();
        if (handler != null && command != null) {
            return command;
        }
        LogMgr.d(TAG, "--->>dispatcherServiceMessage handler may be null OR not register");
        return null;
    }
}

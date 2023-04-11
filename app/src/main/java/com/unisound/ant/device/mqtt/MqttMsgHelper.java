package com.unisound.ant.device.mqtt;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.unisound.ant.device.mqtt.bean.MessageParam;
import com.unisound.ant.device.mqtt.bean.MqttMessage;
import com.unisound.ant.device.mqtt.bean.RegisterParam;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.TimeUtils;
import java.util.ArrayList;
import java.util.List;

public class MqttMsgHelper {
    private static final String TAG = "MqttMsgHelper";
    private static String WEB_URL = ANTConfigPreference.getMsgCenterUrl();
    private static final int appOsType = 1;
    private static String signature = null;
    public static final int subsystemId = 4;
    private static long timestamp = MsgUtil.getCurrentUnixTimestamp();

    static {
        LogMgr.d(TAG, "initMqtt is versionType:" + ANTConfigPreference.sVersionType);
        LogMgr.d(TAG, "init mqtt address:" + WEB_URL);
    }

    public static MqttMessage registerMqtt(RegisterParam param) {
        String url = WEB_URL + "/rest/v1/client/register";
        LogMgr.d(TAG, "registerMqtt registerAdress:" + url);
        MqttMessage registerResult = null;
        try {
            String paramString = formatParam(param);
            LogMgr.d(TAG, "registerMqtt paramString:" + paramString);
            String result = HttpRequestUtil.sendPost(url, paramString);
            if (result != null) {
                LogMgr.d(TAG, "result : " + result);
                registerResult = (MqttMessage) new Gson().fromJson(result, MqttMessage.class);
            }
            return registerResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String formatParam(RegisterParam param) {
        timestamp = TimeUtils.getCurrentTime() / 1000;
        StringBuffer urlParams = new StringBuffer();
        urlParams.append("appKey=").append(param.getAppKey()).append("&udid=").append(param.getUdid()).append("&subsystemId=").append(4).append("&token=").append(param.getToken()).append("&tcDeviceId=").append(param.getTcDeviceId()).append("&timestamp=").append(timestamp).append("&dataVersion=").append(param.getDataVersion()).append("&signature=").append(getSignature(param)).append("&appOsType=").append(1).append("&extras=").append(param.getExtras());
        return urlParams.toString();
    }

    public static String getSignature(RegisterParam param) {
        List<String> params = new ArrayList<>();
        params.add(param.getAppKey());
        params.add(param.getAppSecret());
        params.add(param.getUdid());
        params.add("4");
        params.add(param.getToken());
        params.add(param.getTcDeviceId());
        params.add(param.getDataVersion());
        if (timestamp > 0) {
            params.add(timestamp + "");
        }
        params.add("1");
        params.add(param.getExtras());
        LogMgr.d("MqttRegister", "params:" + params);
        signature = MsgUtil.buildSignature(params);
        return signature;
    }

    public static MqttMessage getUnreadMessages(MessageParam param) throws Exception {
        MqttMessage message = new MqttMessage();
        String url = WEB_URL + "/rest/v1/client/getUnreadMessages";
        LogMgr.d(TAG, "getUnreadMessages url:" + url + "\n param:" + new Gson().toJson(param));
        String result = HttpRequestUtil.sendPost(url, param.formateParam());
        if (!TextUtils.isEmpty(result)) {
            return (MqttMessage) new Gson().fromJson(result, MqttMessage.class);
        }
        return message;
    }

    public static MqttMessage getHistoryMessages(MessageParam param) throws Exception {
        MqttMessage message = new MqttMessage();
        String url = WEB_URL + "/rest/v1/client/getHistoryMessages";
        LogMgr.d(TAG, "getHistoryMessages url:" + url + "\n param:" + new Gson().toJson(param));
        String result = HttpRequestUtil.sendPost(url, param.formateParam());
        if (!TextUtils.isEmpty(result)) {
            return (MqttMessage) new Gson().fromJson(result, MqttMessage.class);
        }
        return message;
    }

    public static String checkClientAuth(String clientid, String userName, String password) throws Exception {
        try {
            return HttpRequestUtil.sendPost(WEB_URL + "/rest/v1/client/auth", "clientid=" + clientid + "&username=" + userName + "&password=" + password);
        } catch (Throwable e) {
            e.getStackTrace();
            return null;
        }
    }
}

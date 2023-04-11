package com.unisound.ant.device.netmodule;

import androidx.annotation.WorkerThread;
import com.unisound.ant.device.bean.RequestInfo;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.util.HttpUtils;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;
import java.io.IOException;
import okhttp3.Response;
import org.json.JSONObject;

public class DeviceInfoUtils {
    private static final String TAG = DeviceInfoUtils.class.getSimpleName();

    @WorkerThread
    public static boolean isDeviceBounded(String udid) throws Exception {
//        String str;
//        Response response = HttpUtils.getInstance().postSync(ANTConfigPreference.getAppServerUrl() + "getUserInfo", JsonTool.toJson(new RequestInfo("UserManager", "getUserInfo", new RequestInfo.PageInfo("1", "10", udid), new RequestInfo.ClientInfo(9), "2.0.0")));
//        if (HttpUtils.isResponseCorrect(response)) {
            try {
//                JSONObject jsonObject = JsonTool.parseToJSONObject(response.body().string());
                JSONObject jsonObject = JsonTool.parseToJSONObject("{\"status\":\"0\"}");
                int status = JsonTool.getJsonIntValue(jsonObject, "status");
                if (500 == status) {
                    LogMgr.d(TAG, "fetch device bound status, device is not bound");
                    return false;
                } else if (status == 0) {
                    LogMgr.d(TAG, "fetch device bound status, device is bound");
                    return true;
                } else {
                    String detailInfo = JsonTool.getJsonValue(jsonObject, "detailInfo");
                    LogMgr.d(TAG, "fetch device bound status failure, errorCode = " + status + ", errorDetail = " + detailInfo);
                    throw new Exception("errorCode = " + status + ", errorDetail = " + detailInfo);
                }
            } catch (IOException e) {
                LogMgr.e(TAG, "query bound bound error: " + e.getMessage());
                throw e;
            }
//        } else {
//            StringBuilder append = new StringBuilder().append("query bound status error, ");
//            if (response == null) {
//                str = "response is null";
//            } else {
//                str = " response code is " + response.code();
//            }
//            throw new Exception(append.append(str).toString());
//        }
    }
}

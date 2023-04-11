package com.unisound.ant.device.netmodule;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.unisound.ant.device.bean.SessionData;
import com.unisound.ant.device.service.BaseRequest;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.HttpUtils;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.StringUtils;
import com.unisound.vui.util.ThreadUtils;
import java.io.IOException;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpReportUtils {
    private static final String TAG = HttpReportUtils.class.getSimpleName();

    public static void httpReportASRLog(final String udid, final BaseRequest<SessionData> data) {
        ThreadUtils.executeInSingle(new Runnable() {
            /* class com.unisound.ant.device.netmodule.HttpReportUtils.AnonymousClass1 */

            public void run() {
                String str;
                JsonObject jsonHead = new JsonObject();
                jsonHead.addProperty("udid", udid);
                jsonHead.addProperty("APP_KEY", ExoConstants.APP_KEY);
                jsonHead.addProperty("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
                JsonObject jsonData = new JsonObject();
                jsonData.add("head", jsonHead);
                jsonData.add("body", new Gson().toJsonTree(data));
                JsonObject json = new JsonObject();
                json.add("data", jsonData);
                json.addProperty("signature", StringUtils.MD5(jsonData.toString()));
                String url = ANTConfigPreference.getAppServerUrl() + "rest/v1/api/terminal_syslog";
                String dataString = json.toString();
                LogMgr.d(HttpReportUtils.TAG, "httpReportASRLog, url = " + url + ", params = " + dataString);
                Response response = HttpUtils.getInstance().postSync(url, dataString);
                if (HttpUtils.isResponseCorrect(response)) {
                    try {
                        JSONObject jsonObject = JsonTool.parseToJSONObject(response.body().string());
                        String code = jsonObject.getString("errorCode");
                        if ("1200000".equals(code)) {
                            LogMgr.d(HttpReportUtils.TAG, "httpReportASRLog success!");
                        } else {
                            LogMgr.d(HttpReportUtils.TAG, "httpReportASRLog failure, error code = " + code + ", message = " + jsonObject.getString("errorMsg"));
                        }
                    } catch (IOException e) {
                        LogMgr.e(HttpReportUtils.TAG, "httpReportASRLog error, " + e.getMessage());
                    } catch (JSONException e2) {
                        LogMgr.e(HttpReportUtils.TAG, "httpReportASRLog error, " + e2.getMessage());
                    }
                } else {
                    String str2 = HttpReportUtils.TAG;
                    StringBuilder append = new StringBuilder().append("httpReportASRLog failure, ");
                    if (response == null) {
                        str = "response is null!";
                    } else {
                        str = " response code = " + response.code();
                    }
                    LogMgr.d(str2, append.append(str).toString());
                }
            }
        });
    }

    public static void httpReportMusicInfo(final String udid, final BaseRequest<SessionData> data) {
        ThreadUtils.executeInSingle(new Runnable() {
            /* class com.unisound.ant.device.netmodule.HttpReportUtils.AnonymousClass2 */

            public void run() {
                String str;
                JsonObject jsonHead = new JsonObject();
                jsonHead.addProperty("udid", udid);
                jsonHead.addProperty("APP_KEY", ExoConstants.APP_KEY);
                jsonHead.addProperty("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
                JsonObject jsonData = new JsonObject();
                jsonData.add("head", jsonHead);
                jsonData.add("body", new Gson().toJsonTree(data));
                JsonObject json = new JsonObject();
                json.add("data", jsonData);
                json.addProperty("signature", StringUtils.MD5(jsonData.toString()));
                String url = ANTConfigPreference.getAppServerUrl() + "rest/v1/api/terminal_reported";
                String dataString = json.toString();
                LogMgr.d(HttpReportUtils.TAG, "httpReportMusicInfo, url = " + url + ", params = " + dataString);
                Response response = HttpUtils.getInstance().postSync(url, dataString);
                if (HttpUtils.isResponseCorrect(response)) {
                    try {
                        JSONObject jsonObject = JsonTool.parseToJSONObject(response.body().string());
                        String code = jsonObject.getString("errorCode");
                        if ("1200000".equals(code)) {
                            LogMgr.d(HttpReportUtils.TAG, "httpReportMusicInfo success!");
                        } else {
                            LogMgr.d(HttpReportUtils.TAG, "httpReportMusicInfo failure, error code = " + code + ", message = " + jsonObject.getString("errorMsg"));
                        }
                    } catch (IOException e) {
                        LogMgr.e(HttpReportUtils.TAG, "httpReportMusicInfo error, " + e.getMessage());
                    } catch (JSONException e2) {
                        LogMgr.e(HttpReportUtils.TAG, "httpReportMusicInfo error, " + e2.getMessage());
                    }
                } else {
                    String str2 = HttpReportUtils.TAG;
                    StringBuilder append = new StringBuilder().append("httpReportMusicInfo failure, ");
                    if (response == null) {
                        str = "response is null!";
                    } else {
                        str = " response code = " + response.code();
                    }
                    LogMgr.d(str2, append.append(str).toString());
                }
            }
        });
    }
}

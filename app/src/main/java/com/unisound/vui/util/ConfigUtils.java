package com.unisound.vui.util;

import android.os.Environment;
import com.google.gson.Gson;
import com.unisound.vui.common.file.FileHelper;
import com.unisound.vui.common.network.d;
import com.unisound.vui.common.network.e;
import com.unisound.vui.util.upload.ReqDataUtils;
import java.io.File;
import java.util.HashMap;

public class ConfigUtils {
    private static final String CONFIG_PATH = (Environment.getExternalStorageDirectory() + File.separator + "config.txt");
    private static final String TAG = "ConfigUtils";
    private static String configFileUrl = "";
    private static String getFilePathUrl = "http://10.30.2.231:8080/app_wx_adapt_service/m/getCustomerConfig";

    public static class ConfigPostData {
        private String appKey;
        private String udid;

        public String getAppKey() {
            return this.appKey;
        }

        public String getUdid() {
            return this.udid;
        }

        public void setAppKey(String appKey2) {
            this.appKey = appKey2;
        }

        public void setUdid(String udid2) {
            this.udid = udid2;
        }
    }

    public static class ConfigResponseData {
        private String configFileUrl;
        private int processStatus;
        private String requestId;

        public String getConfigFileUrl() {
            return this.configFileUrl;
        }

        public int getProcessStatus() {
            return this.processStatus;
        }

        public String getRequestId() {
            return this.requestId;
        }

        public void setConfigFileUrl(String configFileUrl2) {
            this.configFileUrl = configFileUrl2;
        }

        public void setProcessStatus(int processStatus2) {
            this.processStatus = processStatus2;
        }

        public void setRequestId(String requestId2) {
            this.requestId = requestId2;
        }
    }

    private ConfigUtils() {
    }

    public static void downLoadConfig(String appKey, String udid) {
        ConfigPostData configPostData = new ConfigPostData();
        configPostData.setAppKey(appKey);
        configPostData.setUdid(udid);
        HashMap hashMap = new HashMap();
        hashMap.put("encodeType", "add_base64");
        LogMgr.d(TAG, "downLoadConfig para:" + new Gson().toJson(configPostData));
        String encoder = ReqDataUtils.encoder(new Gson().toJson(configPostData));
        LogMgr.d(TAG, "downLoadConfig postData:" + encoder);
        e.a().a(TAG, 1, getFilePathUrl, encoder.getBytes(), hashMap, new d<String>() {
            /* class com.unisound.vui.util.ConfigUtils.AnonymousClass1 */

            @Override // com.unisound.vui.common.network.d
            public void onError(String errorMessage) {
                LogMgr.d(ConfigUtils.TAG, "downLoadConfig error:" + errorMessage.toString());
            }

            public void onResponse(String res) {
                LogMgr.d(ConfigUtils.TAG, "downLoadConfig res:" + res);
                String decoder = ReqDataUtils.decoder(res);
                LogMgr.d(ConfigUtils.TAG, "downLoadConfig response:" + decoder);
                try {
                    ConfigResponseData configResponseData = (ConfigResponseData) new Gson().fromJson(decoder.toString(), ConfigResponseData.class);
                    if (configResponseData.getProcessStatus() == 0) {
                        String unused = ConfigUtils.configFileUrl = configResponseData.getConfigFileUrl();
                        LogMgr.d(ConfigUtils.TAG, "downLoadConfig configFileUrl:" + ConfigUtils.configFileUrl);
                        e.a().a(ConfigUtils.TAG, ConfigUtils.configFileUrl, new d<byte[]>() {
                            /* class com.unisound.vui.util.ConfigUtils.AnonymousClass1.AnonymousClass1 */

                            @Override // com.unisound.vui.common.network.d
                            public void onError(String errorMessage) {
                                LogMgr.d(ConfigUtils.TAG, "write onError:" + errorMessage);
                            }

                            public void onResponse(byte[] response) {
                                File file = new File(ConfigUtils.CONFIG_PATH);
                                if (file.exists()) {
                                    file.delete();
                                }
                                FileHelper.writeDataToFile(ConfigUtils.CONFIG_PATH, response);
                                LogMgr.d(ConfigUtils.TAG, "write success");
                            }
                        });
                    }
                } catch (Exception e) {
                    LogMgr.d(ConfigUtils.TAG, "downLoadConfig exception:" + e.toString());
                }
            }
        });
    }
}

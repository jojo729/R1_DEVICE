package com.unisound.vui.util.downloader;

import android.content.Context;
import com.unisound.vui.data.entity.out.a;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.UserPerferenceUtil;
import com.unisound.vui.util.auth.UserAuthUtil;
import com.unisound.vui.util.upload.SimpleRequestListener;
import com.unisound.vui.util.upload.SimpleRequester;
import org.json.JSONException;
import org.json.JSONObject;

public class IndividualSettingDownloader implements Downloader {
    private static final String DOWNLOAD_TAG = "IndividualSetting";
    private static final String INDIVIDUAL_SETTING_DOWNLOAD_ADDRESS = "http://10.200.19.108:8080/app_wx_adapt_service/m/uploadClientInfo/getPersonalOptions";
    private static final String TAG = "IndividualSettingDownloader";
    private JSONObject body;
    private DownloaderListener downloaderListener;
    private Context mContext;
    private SimpleRequestListener simpleRequestListener = new SimpleRequestListener() {
        /* class com.unisound.vui.util.downloader.IndividualSettingDownloader.AnonymousClass1 */

        @Override // com.unisound.vui.util.upload.SimpleRequestListener
        public void onError(String errorMessage) {
            IndividualSettingDownloader.this.downloaderListener.onError(errorMessage);
        }

        @Override // com.unisound.vui.util.upload.SimpleRequestListener
        public void onResponse(String response) {
            if (IndividualSettingDownloader.this.parseNeedUpdate(response)) {
                IndividualSettingDownloader.this.downloaderListener.onUpdate(IndividualSettingDownloader.this.parserOptions(response));
                return;
            }
            IndividualSettingDownloader.this.downloaderListener.onSameVersion();
        }
    };
    private SimpleRequester simpleRequester;

    public IndividualSettingDownloader(Context mContext2) {
        this.mContext = mContext2;
        this.simpleRequester = new SimpleRequester();
        this.body = new JSONObject();
    }

    private boolean checkCurVersionExist() {
        return UserPerferenceUtil.getCurVersion(this.mContext) != null;
    }

    private void constructBody() {
        a userAuth = getUserAuth();
        if (userAuth != null) {
            setParamsForBody(userAuth);
        } else {
            LogMgr.e(TAG, "fetch user auth fail !!");
        }
    }

    private String getCurVersion() {
        return UserPerferenceUtil.getCurVersion(this.mContext);
    }

    private a getUserAuth() {
        return UserAuthUtil.getInstance().getUniUserAuth();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean parseNeedUpdate(String response) {
        try {
            return ((Boolean) new JSONObject(response).get("needModify")).booleanValue();
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private JSONObject parserOptions(String response) {
        try {
            return (JSONObject) new JSONObject(response).get("options");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sendRequest() {
        this.simpleRequester.request("IndividualSetting", INDIVIDUAL_SETTING_DOWNLOAD_ADDRESS, this.body, this.simpleRequestListener);
    }

    private void setParamsForBody(a userAuth) {
        try {
            this.body.put("passportId", userAuth.b());
            this.body.put("passportToken", userAuth.c());
            this.body.put("udid", userAuth.a());
            this.body.put("curVersion", getCurVersion());
        } catch (JSONException e) {
            LogMgr.e("setParamsForBody error : " + e.toString());
        }
    }

    @Override // com.unisound.vui.util.downloader.Downloader
    public void download(DownloaderListener downloaderListener2) {
        this.downloaderListener = downloaderListener2;
        if (checkCurVersionExist()) {
            constructBody();
            sendRequest();
            return;
        }
        LogMgr.e(TAG, "check curVersion not exist !! download cancel");
    }
}

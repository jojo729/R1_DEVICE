package com.unisound.vui.util.upload.user;

import android.content.Context;
import com.unisound.vui.data.entity.out.a;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.UserPerferenceUtil;
import com.unisound.vui.util.auth.UserAuthUtil;
import com.unisound.vui.util.upload.SimpleRequestListener;
import com.unisound.vui.util.upload.SimpleRequester;
import org.json.JSONException;
import org.json.JSONObject;

public class IndividualSettingUploader implements Uploader {
    private static final String INDEVIDUAL_SETTING_REQUEST_ADDRESS = "http://10.200.19.108:8080/app_wx_adapt_service/m/uploadClientInfo/setPersonalOptions";
    public static final String TAG = "IndividualSettingUploader";
    public static final String UPLOAD_TAG = "IndividualSetting";
    private JSONObject body;
    private Context mContext;
    private SimpleRequestListener simpleRequestListener = new SimpleRequestListener() {
        /* class com.unisound.vui.util.upload.user.IndividualSettingUploader.AnonymousClass1 */

        @Override // com.unisound.vui.util.upload.SimpleRequestListener
        public void onError(String errorMessage) {
            IndividualSettingUploader.this.uploaderListener.onError(errorMessage);
        }

        @Override // com.unisound.vui.util.upload.SimpleRequestListener
        public void onResponse(String response) {
            IndividualSettingUploader.this.saveCurVersion(response);
            if ("0".equals(IndividualSettingUploader.this.parserResponse(response, "processStatus"))) {
                IndividualSettingUploader.this.uploaderListener.onSuccess();
            }
        }
    };
    private SimpleRequester simpleRequester;
    private UploaderListener uploaderListener;

    public IndividualSettingUploader(Context mContext2) {
        this.mContext = mContext2;
        this.simpleRequester = new SimpleRequester();
        this.body = new JSONObject();
    }

    private void constructBody(JSONObject options) {
        a userAuth = getUserAuth();
        if (userAuth != null) {
            setParamsForBody(userAuth, options);
        } else {
            LogMgr.e(TAG, "fetch user auth fail !!");
        }
    }

    private a getUserAuth() {
        return UserAuthUtil.getInstance().getUniUserAuth();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String parserResponse(String response, String name) {
        try {
            return (String) new JSONObject(response).get(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void saveCurVersion(String response) {
        UserPerferenceUtil.setCurVersion(this.mContext, parserResponse(response, "version"));
    }

    private void sendRequest() {
        this.simpleRequester.request(UPLOAD_TAG, INDEVIDUAL_SETTING_REQUEST_ADDRESS, this.body, this.simpleRequestListener);
    }

    private void setParamsForBody(a userAuth, JSONObject options) {
        try {
            this.body.put("passportId", userAuth.b());
            this.body.put("passportToken", userAuth.c());
            this.body.put("udid", userAuth.a());
            this.body.put("options", options);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.unisound.vui.util.upload.user.Uploader
    public void upload(JSONObject options, UploaderListener uploaderListener2) {
        this.uploaderListener = uploaderListener2;
        constructBody(options);
        sendRequest();
    }
}

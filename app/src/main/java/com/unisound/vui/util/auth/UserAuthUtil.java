package com.unisound.vui.util.auth;

import android.content.Context;
import com.unisound.passport.PassportListener;
import com.unisound.vui.data.entity.out.a;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;
import org.json.JSONException;
import org.json.JSONObject;

public class UserAuthUtil {
    public static final String TAG = "UserAuthUtil";
    private static a uniUserAuth;
    private static UserAuthUtil userAuthUtil;
    private PassportListener listener = new PassportListener() {
        /* class com.unisound.vui.util.auth.UserAuthUtil.AnonymousClass1 */

        @Override // com.unisound.passport.PassportListener
        public void onError(int i, String s) {
            LogMgr.d(UserAuthUtil.TAG, "onError : " + s);
        }

        @Override // com.unisound.passport.PassportListener
        public void onEvent(int i, long l) {
            LogMgr.d(UserAuthUtil.TAG, "onEvent : " + l);
        }

        @Override // com.unisound.passport.PassportListener
        public void onResult(int i, String result) {
            LogMgr.d(UserAuthUtil.TAG, "onResult : " + result);
            UserAuthUtil.this.handleResult(result);
        }
    };

    private UserAuthUtil() {
        uniUserAuth = new a();
    }

    public static UserAuthUtil getInstance() {
        if (userAuthUtil == null) {
            synchronized (UserAuthUtil.class) {
                if (userAuthUtil == null) {
                    userAuthUtil = new UserAuthUtil();
                }
            }
        }
        return userAuthUtil;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleResult(String result) {
        try {
            JSONObject jSONObject = new JSONObject(result);
            setIdAndToken(JsonTool.getJsonValue(jSONObject, "passportId"), JsonTool.getJsonValue(jSONObject, "passportToken"));
            setUniUserAuth(uniUserAuth);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void request(Context mContext, String appKey, String udid) {
        PassPortUtil passPortUtil = new PassPortUtil(mContext);
        passPortUtil.initPassPort(appKey, udid);
        passPortUtil.setPassPortListener(this.listener);
        passPortUtil.openConnection();
    }

    private void setIdAndToken(String passportId, String passportToken) {
        uniUserAuth.c(passportId);
        uniUserAuth.d(passportToken);
    }

    private void setKeyAndUdid(String appKey, String udid) {
        uniUserAuth.a(appKey);
        uniUserAuth.b(udid);
    }

    private void setUniUserAuth(a uniUserAuth2) {
        LogMgr.d(TAG, "setUniUserAuth : " + uniUserAuth2.toString());
        uniUserAuth = uniUserAuth2;
    }

    public a getUniUserAuth() {
        return uniUserAuth;
    }

    public void requestAuth(Context mContext, String appKey, String udid) {
        setKeyAndUdid(appKey, udid);
        request(mContext, appKey, udid);
    }
}

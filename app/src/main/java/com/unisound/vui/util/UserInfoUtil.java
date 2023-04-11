package com.unisound.vui.util;

import android.content.Context;
import com.unisound.vui.data.a.a;
import com.unisound.vui.data.entity.a.c;
import com.unisound.vui.data.entity.out.UniContact;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.util.Const;

public class UserInfoUtil {
    private static final String TAG = "UserInfoUtil";
    private static Context mContext;
    private static UserInfoUtil mInstance;

    private UserInfoUtil() {
    }

    private void addCarReportPreference(JSONObject userInfo) {
        putValueInfoJson(userInfo, "report", Boolean.valueOf(UserPerferenceUtil.getTrafficInfoSwitchState(mContext)));
    }

    private void addCarTheftPreference(JSONObject userInfo) {
        putValueInfoJson(userInfo, "theft", Boolean.valueOf(UserPerferenceUtil.getAntiTheftSwitchState(mContext)));
    }

    private void addCompanyPreference(JSONObject userInfo) {
        try {
            String poiDefaultOfficeInfo = UserPerferenceUtil.getPoiDefaultOfficeInfo(mContext);
            if (poiDefaultOfficeInfo != null && !"".equals(poiDefaultOfficeInfo)) {
                userInfo.put("company", new JSONObject(poiDefaultOfficeInfo));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addContactPreference(JSONObject userInfo) {
        putValueInfoJson(userInfo, "contacts", constructContactJson(a.a(mContext).a()));
    }

    private void addHomePreference(JSONObject userInfo) {
        try {
            String poiDefaultHomeInfo = UserPerferenceUtil.getPoiDefaultHomeInfo(mContext);
            if (poiDefaultHomeInfo != null && !"".equals(poiDefaultHomeInfo)) {
                userInfo.put("home", new JSONObject(poiDefaultHomeInfo));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addMapPreference(JSONObject userInfo) {
        putValueInfoJson(userInfo, "map", UserPerferenceUtil.getNaviSelectedState(mContext));
    }

    private void addPoiPreference(JSONObject userInfo) {
        putValueInfoJson(userInfo, "pois", constructPoiJson(a.a(mContext).b()));
    }

    private void addSettingPreference(JSONObject userInfo) {
        JSONObject jSONObject = new JSONObject();
        constructSettingPreference(jSONObject);
        try {
            userInfo.put("setting", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addSexPreference(JSONObject userInfo) {
        putValueInfoJson(userInfo, "sex", Boolean.valueOf(UserPerferenceUtil.getEnableRecognizePersonal(mContext)));
    }

    private void addTTSPreference(JSONObject userInfo) {
        putValueInfoJson(userInfo, "tts", Integer.valueOf(UserPerferenceUtil.getTTSSelectedState()));
    }

    private JSONArray constructContactJson(List<UniContact> uniContacts) {
        JSONArray jSONArray = new JSONArray();
        for (UniContact uniContact : uniContacts) {
            String contactName = uniContact.getContactName();
            ArrayList<String> contactPhoneNO = uniContact.getContactPhoneNO();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Const.TableSchema.COLUMN_NAME, contactName);
                jSONObject.put("numbers", contactPhoneNO);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }

    private JSONArray constructPoiJson(List<c> poiList) {
        JSONArray jSONArray = new JSONArray();
        for (c cVar : poiList) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("address", cVar.b());
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }

    private void constructSettingPreference(JSONObject settingJson) {
        addMapPreference(settingJson);
        addTTSPreference(settingJson);
        addCarTheftPreference(settingJson);
        addCarReportPreference(settingJson);
        addSexPreference(settingJson);
        addHomePreference(settingJson);
        addCompanyPreference(settingJson);
    }

    public static UserInfoUtil getInstance() {
        if (mInstance != null) {
            return mInstance;
        }
        throw new RuntimeException("WeChatUserInfo must init first");
    }

    public static void init(Context mContext2) {
        mContext = mContext2;
        initInstance();
    }

    private static void initInstance() {
        if (mInstance == null) {
            synchronized (UserInfoUtil.class) {
                if (mInstance == null) {
                    mInstance = new UserInfoUtil();
                }
            }
        }
    }

    private void putValueInfoJson(JSONObject json, String name, Object value) {
        try {
            json.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getUserInfo() {
        JSONObject jSONObject = new JSONObject();
        addSettingPreference(jSONObject);
        return jSONObject;
    }
}

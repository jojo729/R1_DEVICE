package com.unisound.vui.util;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

public class UserInfoParser {
    private Context mContext;

    public UserInfoParser(Context mContext2) {
        this.mContext = mContext2;
    }

    private Object getValue(JSONObject options, String key) {
        try {
            return options.get(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void syncAddressPreference(JSONObject options) {
    }

    private void syncCarReportPreference(JSONObject options) {
        UserPerferenceUtil.setTrafficInfoSwitchState(this.mContext, ((Boolean) getValue(options, "report")).booleanValue());
    }

    private void syncCarTheftPreference(JSONObject options) {
        UserPerferenceUtil.setAntiTheftSwitchState(this.mContext, ((Boolean) getValue(options, "theft")).booleanValue());
    }

    private void syncMapPreference(JSONObject options) {
        UserPerferenceUtil.setNaviSelectedState(this.mContext, (String) getValue(options, "map"));
    }

    private void syncSexPreference(JSONObject options) {
        UserPerferenceUtil.setEnableRecognizePersonal(this.mContext, ((Boolean) getValue(options, "sex")).booleanValue());
    }

    private void syncTTSPreference(JSONObject options) {
        UserPerferenceUtil.setTTSSelectedState(((Integer) getValue(options, "tts")).intValue());
    }

    public void parse(JSONObject options) {
        syncMapPreference(options);
        syncTTSPreference(options);
        syncCarTheftPreference(options);
        syncCarReportPreference(options);
        syncSexPreference(options);
        syncAddressPreference(options);
    }
}

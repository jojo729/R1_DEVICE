package com.unisound.vui.util.auth;

import android.content.Context;
import com.unisound.passport.PassportListener;
import com.unisound.passport.PassportManager;

public class PassPortUtil {
    private static final String TAG = PassPortUtil.class.getSimpleName();
    private Context mContext;
    private PassportManager mPassportManager;

    public PassPortUtil(Context context) {
        this.mContext = context;
    }

    public void closeConnection() {
        if (this.mPassportManager != null) {
            this.mPassportManager.closeConnection();
        }
    }

    public void initPassPort(String appKey, String udid) {
        this.mPassportManager = new PassportManager(this.mContext, appKey, udid);
    }

    public void openConnection() {
        if (this.mPassportManager != null) {
            this.mPassportManager.openConnection();
        }
    }

    public void setPassPortListener(PassportListener listener) {
        if (this.mPassportManager != null) {
            this.mPassportManager.setPassportListener(listener);
        }
    }
}

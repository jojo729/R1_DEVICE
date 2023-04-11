package com.unisound.vui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Set;

public class SharedPreferencesHelper {
    private static final String SP_NAME = "defaultSp";
    private static final String TAG = SharedPreferencesHelper.class.getSimpleName();
    private static SharedPreferencesHelper instance = null;
    @SuppressLint({"StaticFieldLeak"})
    private static Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences sp;

    @SuppressLint({"CommitPrefEdits"})
    private SharedPreferencesHelper(Context context, String name) {
        mContext = context;
        this.sp = mContext.getSharedPreferences(name, 0);
        this.mEditor=this.sp.edit();
    }

    public static synchronized SharedPreferencesHelper getInstance(Context context) {
        SharedPreferencesHelper instance2;
        synchronized (SharedPreferencesHelper.class) {
            instance2 = getInstance(context, SP_NAME);
        }
        return instance2;
    }

    public static synchronized SharedPreferencesHelper getInstance(Context context, String name) {
        SharedPreferencesHelper sharedPreferencesHelper;
        synchronized (SharedPreferencesHelper.class) {
            if (instance == null && !SP_NAME.equals(name)) {
                instance = new SharedPreferencesHelper(context, name);
            }
            sharedPreferencesHelper = instance;
        }
        return sharedPreferencesHelper;
    }

    public static void init(Context context) {
        mContext = context.getApplicationContext();
        instance = new SharedPreferencesHelper(context, SP_NAME);
    }

    public boolean getBooleanValue(String key, boolean bool) {
        return this.sp.getBoolean(key, bool);
    }

    public int getIntValue(String key) {
        return this.sp.getInt(key, 0);
    }

    public int getIntValue(String key, int defaultValue) {
        return this.sp.getInt(key, defaultValue);
    }

    public long getLongValue(String key) {
        return this.sp.getLong(key, 0);
    }

    public Set<String> getStringSetValue(String key) {
        return this.sp.getStringSet(key, null);
    }

    public String getStringValue(String key, String defalut) {
        return this.sp.getString(key, defalut);
    }

    public void saveBooleanValue(String key, boolean booleanValue) {
        if (!TextUtils.isEmpty(key)) {
            this.mEditor.putBoolean(key, booleanValue);
        }
        this.mEditor.apply();
    }

    public void saveIntValue(String key, int intValue) {
        if (!TextUtils.isEmpty(key)) {
            this.mEditor.putInt(key, intValue);
        }
        this.mEditor.apply();
    }

    public void saveLongValue(String key, long longValue) {
        if (!TextUtils.isEmpty(key)) {
            this.mEditor.putLong(key, longValue);
        }
        this.mEditor.apply();
    }

    public void saveStringSetValue(String key, Set<String> setValue) {
        if (!TextUtils.isEmpty(key)) {
            this.mEditor.putStringSet(key, setValue);
        }
        this.mEditor.apply();
    }

    public void saveStringValue(String key, String value) {
        if (!TextUtils.isEmpty(key)) {
            this.mEditor.putString(key, value);
        }
        this.mEditor.apply();
    }
}

package com.unisound.vui.util;

import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.phicomm.speaker.device.BuildConfig;
import com.phicomm.speaker.device.ExampleApp;
import org.apache.commons.io.FileUtils;

public class DeviceTool {
    private static final String INVALID_IMEI = "000000000000000";
    public static final String TAG = "DeviceTool";

    private DeviceTool() {
    }

    public static String getAppPackageName(Context context) {
        try {
            return ExampleApp.PROCESS_NAME;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getAppVersionName(Context context) {
        try {
            return ExampleApp.VERSION_NAME;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static long getAvailableInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long availableBlocks = (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / FileUtils.ONE_MB;
        LogMgr.d(TAG, "Device Available InternalMemorySize = " + availableBlocks + " MB");
        return availableBlocks;
    }

    public static String getDeviceId(Context context) {
        String imei = getIMEI(context);
        return (imei == null || "".equals(imei)) ? INVALID_IMEI : imei;
    }

    public static int getDeviceSDKVersion() {
        int i = Build.VERSION.SDK_INT;
        LogMgr.d(TAG, "!--->sdkVersion = " + i);
        return i;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static String getIMEI(Context context) {
        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        if (deviceId != null && !"".equals(deviceId) && !deviceId.equals(INVALID_IMEI)) {
            return deviceId;
        }
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (string != null && !"".equals(string) && !string.equals(INVALID_IMEI)) {
            return string;
        }
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getMacAddress() : INVALID_IMEI;
    }

    public static String getMac(Context context) {
        WifiInfo connectionInfo;
        return (context == null || (connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo()) == null) ? INVALID_IMEI : connectionInfo.getMacAddress();
    }

    public static float getScreenDensity(Context context) {
        return getDisplayMetrics(context).density;
    }

    public static int getScreenHight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static boolean isAvailableInternalMemory(int sizeMb) {
        return getAvailableInternalMemorySize() > ((long) sizeMb);
    }

    public static boolean isAvailableSDcardSpace(int sizeMb) {
        if (isSdCardExist()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long availableBlocks = (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / FileUtils.ONE_MB;
            LogMgr.d(TAG, "SDCard availableSpare = " + availableBlocks + " MB");
            return availableBlocks > ((long) sizeMb);
        }
        LogMgr.e(TAG, "No sdcard!");
        return false;
    }

    public static boolean isScreenLandscape(Context context) {
        int i = context.getResources().getConfiguration().orientation;
        if (i == 2) {
            LogMgr.i(TAG, "!--->isScreenLandscape---landscape");
            return true;
        }
        if (i == 1) {
            LogMgr.i(TAG, "!--->isScreenLandscape---portrait");
        }
        return false;
    }

    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static void showEditTextKeyboard(EditText editText, boolean isShow) {
        LogMgr.d(TAG, "showKeyboard isShow = " + isShow);
        if (editText != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService("input_method");
            if (isShow) {
                inputMethodManager.toggleSoftInput(0, 2);
            } else {
                inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        }
    }
}

package com.unisound.vui.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import cn.yunzhisheng.common.PinyinConverter;
import com.phicomm.speaker.device.BuildConfig;
import com.phicomm.speaker.device.ExampleApp;
import com.unisound.vui.handler.session.memo.utils.MemoConstants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SystemUitls {
    public static String getAppVersion(Context context) {
        String str = "";
        str = ExampleApp.VERSION_NAME;
        LogMgr.i("===>>getVersionCode and code:" + str);
        return str;
    }

    private static String getAvailMemory(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        String str = memoryInfo.availMem + "";
        LogMgr.d("====>>AvailMemory:" + str);
        return str;
    }

    public static String getBrandModel() {
        return Build.BRAND + PinyinConverter.PINYIN_SEPARATOR + Build.MODEL;
    }

    public static String getCompactTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static String getCpuInfo() {
        String[] strArr = {"", ""};
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
            String[] split = bufferedReader.readLine().split("\\s+");
            for (int i = 2; i < split.length; i++) {
                strArr[0] = strArr[0] + split[i] + PinyinConverter.PINYIN_SEPARATOR;
            }
            strArr[1] = strArr[1] + bufferedReader.readLine().split("\\s+")[2];
            bufferedReader.close();
        } catch (IOException e) {
        }
        return strArr[0] + ":" + strArr[1];
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.sss").format(new Date());
    }

    public static String getIMEI(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
    }

    public static String getPhoneNumber(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
    }

    public static String getRamAvailableSize(Context context) {
        return getAvailMemory(context);
    }

    public static String getRamTotalSize(Context context) {
        return getTotalMemory(context);
    }

    public static String getSDAvailableSize(Context context) {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return Formatter.formatFileSize(context, ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()));
    }

    public static String getSDTotalSize(Context context) {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return Formatter.formatFileSize(context, ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
    }

    public static String getSimOperatorName(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    public static String getSn() {
        return Build.SERIAL;
    }

    public static int getSystemSDKLevel() {
        return Build.VERSION.SDK_INT;
    }

    public static String getSystemVersionCode() {
        return Build.VERSION.RELEASE;
    }

    public static String getTime() {
        return new SimpleDateFormat(MemoConstants.DATE_FORMATE_YMDHMS).format(new Date());
    }

    private static String getTotalMemory(Context context) {
        long j = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String readLine = bufferedReader.readLine();
            String[] split = readLine.split("\\s+");
            int length = split.length;
            for (int i = 0; i < length; i++) {
                LogMgr.i(readLine, split[i] + "\t");
            }
            j = (long) (Integer.valueOf(split[1]).intValue() * 1024);
            bufferedReader.close();
        } catch (IOException e) {
        }
        String str = j + "";
        Formatter.formatFileSize(context, j);
        LogMgr.d("=====>>totalSize:" + str);
        return str;
    }

    public static int getVersionCode(Context context) {
        int i =ExampleApp.VERSION_CODE;

        LogMgr.i("===>>getVersionCode and code:" + i);
        return i;
    }

    public static boolean hasGPSDevice(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager == null) {
            return false;
        }
        List<String> allProviders = locationManager.getAllProviders();
        if (allProviders == null) {
            return false;
        }
        return allProviders.contains("gps");
    }

    public static boolean isSupportCamera(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature("android.hardware.camera") || packageManager.hasSystemFeature("android.hardware.camera.front");
    }
}

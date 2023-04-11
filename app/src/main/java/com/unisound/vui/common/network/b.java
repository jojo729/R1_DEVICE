package com.unisound.vui.common.network;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.PackageInfoUtil;

import java.util.HashMap;
import java.util.List;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<String, String> f369a = new HashMap<>();
    private static Context b;
    private static boolean c = true;

    public static void a(Context context) {
        if (b == null) {
            b = context;
        }
        f369a.clear();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        LogMgr.d("LaunchUtils", "packages size:" + installedPackages.size());
        for (PackageInfo packageInfo : installedPackages) {
            LogMgr.d("LaunchUtils", packageInfo.packageName + "start");
            String str = packageInfo.packageName;
            String charSequence = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
            LogMgr.d("LaunchUtils", "mlabel:" + charSequence);
            if (!f369a.containsKey(charSequence)) {
                f369a.put(charSequence, str);
            }
        }
        c = PackageInfoUtil.isZhSystem(context);
    }
}

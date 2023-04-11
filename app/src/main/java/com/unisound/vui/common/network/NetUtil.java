package com.unisound.vui.common.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.unisound.b.f;
import com.unisound.vui.util.LogMgr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

public class NetUtil {
    private static final String TAG = "NetUtil";
    private static NetUtil mNetUtil;
    private ConnectivityManager mConnectivityManager = ((ConnectivityManager) this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE));
    private Context mContext;
    private NetworkInfo mNetworkInfo;

    private NetUtil(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static NetUtil getInstante(Context context) {
        if (mNetUtil == null) {
            mNetUtil = new NetUtil(context);
        }
        return mNetUtil;
    }

    public static String getLocalIpAddress() {
        SocketException socketException;
        String str;
        String str2 = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            loop0:
            while (true) {
                try {
                    if (!networkInterfaces.hasMoreElements()) {
                        str = str2;
                        break;
                    }
                    Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                    while (true) {
                        if (inetAddresses.hasMoreElements()) {
                            InetAddress nextElement = inetAddresses.nextElement();
                            if (!nextElement.isLoopbackAddress()) {
                                str = nextElement.getHostAddress().toString();
                                if (nextElement instanceof Inet4Address && !str.equals("::1")) {
                                    if (str.startsWith("192")) {
                                        break loop0;
                                    }
                                    str2 = str;
                                }
                            }
                            str = str2;
                            str2 = str;
                        }
                    }
                } catch (Exception e) {
                    str = str2;
                    e.printStackTrace();
                    LogMgr.d(TAG, "===>>get current ipAddress:" + str);
                    return str;
                }
            }
        } catch (SocketException e2) {
            str = null;
            socketException = e2;
            socketException.printStackTrace();
            LogMgr.e(TAG, "===>>get current ipAddress:" + socketException.toString());
            LogMgr.d(TAG, "===>>get current ipAddress:" + str);
            return str;
        }
        LogMgr.d(TAG, "===>>get current ipAddress:" + str);
        return str;
    }

    public static String getNetWorkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            String typeName = activeNetworkInfo.getTypeName();
            if (typeName.equalsIgnoreCase("WIFI") || typeName.equalsIgnoreCase("MOBILE")) {
                return typeName;
            }
        }
        return "UNKNOW";
    }

    public static String getOutNetIp() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://www.cmyip.com/").openConnection();
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, f.b));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine + "\n");
                    } else {
                        inputStream.close();
                        return sb.toString();
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return "";
    }

    public static boolean isMobileConnected(Context context) {
        NetworkInfo networkInfo;
        if (context == null || (networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(0)) == null) {
            return false;
        }
        return networkInfo.isAvailable();
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isConnected();
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo networkInfo;
        if (context == null || (networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(1)) == null) {
            return false;
        }
        return networkInfo.isAvailable();
    }

    public boolean getConnectState() {
        if (this.mConnectivityManager != null) {
            this.mNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
            if (this.mNetworkInfo != null) {
                return this.mNetworkInfo.isConnected();
            }
        }
        return false;
    }

    public void release() {
        this.mContext = null;
        this.mConnectivityManager = null;
        this.mNetworkInfo = null;
    }
}

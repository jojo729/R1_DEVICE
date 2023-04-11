package com.unisound.vui.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;

public class TimeUtils {
    private static final String TAG = TimeUtils.class.getSimpleName();

    public static String format(long time, String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(new Date(time));
    }

    public static String format(String str, String oldFormat, String newFormat) throws ParseException {
        return format(new SimpleDateFormat(oldFormat, Locale.CHINA).parse(str).getTime(), newFormat);
    }

    public static String format(Date date, String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(date);
    }

    public static long getCurrentTime() {
        long currentTimeMillis = System.currentTimeMillis();
        long unisoundWebTime = getUnisoundWebTime();
        LogMgr.d(TAG, "getUnisoundWebTime = " + unisoundWebTime);
        if (unisoundWebTime <= 0) {
            unisoundWebTime = getWebsiteDatetime("http://www.baidu.com");
            LogMgr.d(TAG, "getBaiduWebTime = " + unisoundWebTime);
        }
        return (unisoundWebTime <= 0 || Math.abs(currentTimeMillis - unisoundWebTime) <= 300000) ? currentTimeMillis : unisoundWebTime;
    }

    public static Date getDate(String date, String format){
        try {
            return new SimpleDateFormat(format, Locale.CHINA).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getNowDate(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        simpleDateFormat.format(new Date());
        return simpleDateFormat.format(new Date());
    }

    public static long getTime(String date, String format) {
        try {
            return new SimpleDateFormat(format, Locale.CHINA).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static long getUnisoundWebTime() {
        try {
            HttpUtils.getInstance().cancel("getUnisoundWebTime");
            Response sync = HttpUtils.getInstance().getSync("getUnisoundWebTime", "http://uc.hivoice.cn/timestamp.jsp");
            if (HttpUtils.isResponseCorrect(sync)) {
                return Long.parseLong(sync.body().string().replace(IOUtils.LINE_SEPARATOR_WINDOWS, "").replace("\n", "")) * 1000;
            }
        } catch (Exception e) {
            LogMgr.e(TAG, "getUnisoundWebTime error : ", e);
        }
        return 0;
    }

    private static long getWebsiteDatetime(String webUrl) {
        try {
            URLConnection openConnection = new URL(webUrl).openConnection();
            openConnection.connect();
            return openConnection.getDate();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return 0;
    }
}

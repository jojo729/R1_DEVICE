package com.unisound.vui.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class UpLoadWechat {
    private UpLoadWechat() {
    }

    public static byte[] httpGet(String url, byte[] sdata, Map<String, Object> requestParam, boolean printError) {
        OutputStream outputStream;
        try {
            if (isEmpty(url)) {
                return null;
            }
            if (requestParam != null && requestParam.size() > 0) {
                if (!url.endsWith("?")) {
                    url = url + "?";
                }
                for (String str : requestParam.keySet()) {
                    url = url + str + "=" + requestParam.get(str) + "&";
                }
                if (url.endsWith("&")) {
                    url = url.substring(0, url.length() - 1);
                }
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("GET");
            if (sdata != null) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(sdata);
                outputStream.flush();
            } else {
                outputStream = null;
            }
            byte[] stream2Bytes = stream2Bytes(httpURLConnection.getInputStream());
            if (outputStream != null) {
                outputStream.close();
            }
            if (stream2Bytes != null) {
                return stream2Bytes;
            }
            return null;
        } catch (Throwable th) {
            if (printError) {

            }
            return null;
        }
    }

    public static byte[] httpPost(String url, byte[] sdata, Map<String, Object> requestParam, boolean printError) {
        OutputStream outputStream;
        try {
            if (isEmpty(url)) {
                return null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("POST");
            if (requestParam != null && requestParam.size() > 0) {
                for (String str : requestParam.keySet()) {
                    httpURLConnection.addRequestProperty(str, requestParam.get(str) + "");
                }
            }
            if (sdata != null) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(sdata);
                outputStream.flush();
            } else {
                outputStream = null;
            }
            byte[] stream2Bytes = stream2Bytes(httpURLConnection.getInputStream());
            if (outputStream != null) {
                outputStream.close();
            }
            if (stream2Bytes != null) {
                return stream2Bytes;
            }
            return null;
        } catch (Throwable th) {
            if (printError) {
            }
            return null;
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static byte[] stream2Bytes(InputStream in) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[2048];
            while (true) {
                int read = in.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray().length > 0 ? byteArrayOutputStream.toByteArray() : null;
            byteArrayOutputStream.close();
            in.close();
            return byteArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.unisound.ant.device.mqtt;

import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;

public class MsgUtil {
    public static String buildSignature(List<String> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        Collections.sort(params);
        StringBuilder sb = new StringBuilder();
        for (String param : params) {
            if (param == null) {
                param = "";
            }
            sb.append(param);
        }
        return getSHA1Digest(sb.toString());
    }

    public static String getSHA1Digest(String data) {
        try {
            return byte2hex(MessageDigest.getInstance("SHA-1").digest(data.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 255);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static long getCurrentUnixTimestamp() {
        return System.currentTimeMillis() / 1000;
    }
}

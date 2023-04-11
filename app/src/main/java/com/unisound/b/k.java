package com.unisound.b;

import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;

public class k {
    public static long a(long j) {
        return j / 1000;
    }

    public static String a(String str) {
        try {
            return a(MessageDigest.getInstance("SHA-1").digest(str.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            if (str == null) {
                str = "";
            }
            sb.append(str);
        }
        return a(sb.toString());
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString.toUpperCase());
        }
        return sb.toString();
    }
}

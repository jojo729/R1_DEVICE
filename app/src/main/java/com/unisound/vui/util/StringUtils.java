package com.unisound.vui.util;

import android.text.TextUtils;
import java.lang.Character;
import java.security.MessageDigest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private StringUtils() {
    }

    public static String MD5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                bArr[i] = (byte) charArray[i];
            }
            byte[] digest = instance.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i2 = b & 255;
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getSubString(String str, String key) {
        int i = 0;
        int i2 = 0;
        while (true) {
            int indexOf = str.indexOf(key, i);
            if (indexOf == -1) {
                return i2;
            }
            i = indexOf + key.length();
            i2++;
        }
    }

    public static boolean hasChinese(String str) {
        char[] charArray;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || of == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || of == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    public static boolean isTrue(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return String.valueOf(true).equals(str);
    }

    public static boolean listStringEquals(List<String> oldList, List<String> newList) {
        if (oldList == null || newList == null) {
            return false;
        }
        if (oldList.size() != newList.size()) {
            return false;
        }
        for (String str : oldList) {
            if (!newList.contains(str)) {
                return false;
            }
        }
        return true;
    }

    public static String matchFind(String response, String result) {
        String str = "";
        Matcher matcher = Pattern.compile(result).matcher(response);
        while (matcher.find()) {
            str = matcher.group(1);
        }
        return str;
    }
}

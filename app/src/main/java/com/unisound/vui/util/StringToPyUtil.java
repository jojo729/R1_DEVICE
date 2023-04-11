package com.unisound.vui.util;


import androidx.core.view.MotionEventCompat;

public class StringToPyUtil {
    private static int BEGIN = 45217;
    private static int END = 63486;
    private static char[] chartable = {21834, 33453, 25830, 25645, 34558, 21457, 22134, 21704, 21704, 20987, 21888, 22403, 22920, 25343, 21734, 21866, 26399, 28982, 25746, 22604, 22604, 22604, 25366, 26132, 21387, 21277};
    private static char[] initialtable = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 't', 't', 'w', 'x', 'y', 'z'};
    private static int[] table = new int[27];

    static {
        for (int i = 0; i < 26; i++) {
            table[i] = gbValue(chartable[i]);
        }
        table[26] = END;
    }

    private static char Char2Initial(char ch) {
        int gbValue;
        if (ch >= 'a' && ch <= 'z') {
            return (char) ((ch - 'a') + 65);
        }
        if ((ch >= 'A' && ch <= 'Z') || (gbValue = gbValue(ch)) < BEGIN || gbValue > END) {
            return ch;
        }
        int i = 0;
        while (i < 26 && (gbValue < table[i] || gbValue >= table[i + 1])) {
            i++;
        }
        if (gbValue == END) {
            i = 25;
        }
        return initialtable[i];
    }

    public static String cn2py(String SourceStr) {
        String str = "";
        int i = 0;
        while (i < SourceStr.length()) {
            try {
                String str2 = str + Char2Initial(SourceStr.charAt(i));
                i++;
                str = str2;
            } catch (Exception e) {
                return "";
            }
        }
        return str;
    }

    private static int gbValue(char ch) {
        try {
            byte[] bytes = (new String() + ch).getBytes("GB2312");
            if (bytes.length < 2) {
                return 0;
            }
            return (bytes[1] & 255) + ((bytes[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
        } catch (Exception e) {
            return 0;
        }
    }
}

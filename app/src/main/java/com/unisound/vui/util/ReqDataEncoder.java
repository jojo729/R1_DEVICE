package com.unisound.vui.util;

import java.nio.charset.Charset;

public class ReqDataEncoder {
    private static final int TOTAL_LEN_FIELD_LEN = 4;

    private ReqDataEncoder() {
    }

    public static byte[] encode(String userId, byte[] targetData) {
        byte[] bArr = null;
        if (!(userId == null || userId.length() == 0 || targetData == null || targetData.length == 0)) {
            byte length = (byte) userId.length();
            int length2 = targetData.length;
            int i = length + 5 + length2;
            byte[] bytes = userId.getBytes(Charset.forName("UTF-8"));
            byte[] intToByteArray = intToByteArray(i);
            bArr = new byte[(i + 5)];
            System.arraycopy(intToByteArray, 0, bArr, 0, 4);
            bArr[4] = length;
            System.arraycopy(bytes, 0, bArr, 5, length);
            byte b = bytes[0];
            int i2 = length + 5;
            byte b2 = 0;
            for (int i3 = 0; i3 < length2; i3++) {
                bArr[i2 + i3] = (byte) ((b2 ^ targetData[i3]) ^ bytes[i3 % length]);
                b2 = bArr[i2 + i3];
            }
        }
        return bArr;
    }

    public static byte[] intToByteArray(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }
}

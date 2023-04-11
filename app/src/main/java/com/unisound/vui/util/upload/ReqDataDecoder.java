package com.unisound.vui.util.upload;

import android.annotation.SuppressLint;
import com.unisound.vui.util.LogMgr;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ReqDataDecoder {
    private static final String TAG = ReqDataDecoder.class.getSimpleName();
    private static final int TOTAL_LEN_FIELD_LEN = 4;

    private ReqDataDecoder() {
    }

    @SuppressLint({"NewApi"})
    public static DecodeResult decode(byte[] input) {
        byte b = 0;
        if (input == null || input.length == 0) {
            LogMgr.e(TAG, "bad input");
            return null;
        }
        byte[] bArr = new byte[4];
        System.arraycopy(input, 0, bArr, 0, 4);
        int i = ByteBuffer.wrap(bArr).getInt();
        byte b2 = input[4];
        int i2 = ((i - 4) - 1) - b2;
        int length = input.length;
        LogMgr.e(TAG, "[IN DECODE]totalLen: " + i + ", userIDLen: " + ((int) b2) + ", leftLen: " + i2 + ", inputLen: " + length);
        if (i >= length) {
            return null;
        }
        byte[] bArr2 = new byte[b2];
        int i3 = 5 + b2;
        System.arraycopy(input, 5, bArr2, 0, b2);
        byte b3 = bArr2[0];
        byte[] bArr3 = new byte[i2];
        int i4 = 0;
        while (i4 != i2) {
            byte b4 = bArr2[i4 % b2];
            byte b5 = input[i3 + i4];
            bArr3[i4] = (byte) ((b ^ b5) ^ b4);
            i4++;
            b = b5;
        }
        DecodeResult decodeResult = new DecodeResult();
        decodeResult.setUserID(new String(bArr2, Charset.forName("UTF-8")));
        decodeResult.setOther(bArr3);
        return decodeResult;
    }
}

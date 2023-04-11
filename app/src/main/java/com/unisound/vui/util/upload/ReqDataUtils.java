package com.unisound.vui.util.upload;

import android.util.Base64;
import com.unisound.vui.util.ReqDataEncoder;
import java.nio.charset.Charset;

public class ReqDataUtils {
    private static final String USERID = "userId";

    private ReqDataUtils() {
    }

    public static String decoder(String cipherText) {
        DecodeResult decode = ReqDataDecoder.decode(Base64.decode(cipherText.getBytes(), 0));
        return new String(decode != null ? decode.getOther() : new byte[0]);
    }

    public static String encoder(String originalText) {
        return new String(Base64.encode(ReqDataEncoder.encode(USERID, originalText.getBytes(Charset.forName("UTF-8"))), 0));
    }
}

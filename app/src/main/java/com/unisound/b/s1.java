package com.unisound.b;



import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import android.util.Base64;


public class s1 {

    public static final String KEY_ALGORITHM = "RSA";

    private static final int MAX_DECRYPT_BLOCK = 384;


    private static final int MAX_DECRYPT_BLOCK_2048 = 256;


    private static final int MAX_DECRYPT_BLOCK_1536 = 192;


    private static final int MAX_DECRYPT_BLOCK_1024 = 128;

    public static byte[] decryptByPublicKey(int keyLength,String data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64.decode(publicKey,Base64.DEFAULT);
        byte[] encryptedData = Base64.decode(data,Base64.DEFAULT);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        int mdb = 0;
        switch (keyLength) {
            case 3072:
                mdb = MAX_DECRYPT_BLOCK;
                break;
            case 2048:
                mdb = MAX_DECRYPT_BLOCK_2048;
                break;
            case 1536:
                mdb = MAX_DECRYPT_BLOCK_1536;
                break;
            case 1024:
                mdb = MAX_DECRYPT_BLOCK_1024;
                break;
            default:
                break;
        }
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > mdb) {
                cache = cipher.doFinal(encryptedData, offSet, mdb);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * mdb;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }


}

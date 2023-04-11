package com.phicomm.speaker.device.ui.service;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Handler;
import android.os.Message;
import androidx.core.view.MotionEventCompat;
import com.unisound.vui.util.LogMgr;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

public class YzsKeyThread extends Thread {
    private static final String TAG = "YzsKeyThread";
    private Handler handler;

    public YzsKeyThread(Handler mHandler) {
        this.handler = mHandler;
    }

    public void run() {
        String resultValue;
        int temp;
        LocalSocket s = new LocalSocket();
        LocalSocketAddress l = new LocalSocketAddress("yzskey", LocalSocketAddress.Namespace.RESERVED);
        try {
            LogMgr.i(TAG, "yzskey socket connect start");
            s.connect(l);
            InputStream is = s.getInputStream();
            byte[] socketReadBuffer = new byte[4];
            while (true) {
                LogMgr.i(TAG, "while yzskey socket connect");
                int socketCount = is.read(socketReadBuffer);
                if (socketCount > 0 && (temp = stringToInt((resultValue = String.valueOf(getChars(socketReadBuffer))))) != 0) {
                    int keyEvent = temp >> 12;
                    int keyCode = temp & 4095;
                    LogMgr.i(TAG, " socket resultValue = " + resultValue + " ; socketCount = " + socketCount + " keyEvent=" + keyEvent + "  keyCode=" + keyCode + " temp=" + temp);
                    if (this.handler != null) {
                        this.handler.sendMessage(Message.obtain(this.handler, keyEvent, Integer.valueOf(keyCode)));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int byte2int(byte[] res) {
        return (res[0] & 255) | ((res[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((res[2] << 24) >>> 8) | (res[3] << 24);
    }

    private char[] getChars(byte[] bytes) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        return cs.decode(bb).array();
    }

    private int stringToInt(String str) {
        if (str == null || !isNumeric(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isNumeric(String str) {
        if (!Pattern.compile("[0-9]*").matcher(str).matches()) {
            return false;
        }
        return true;
    }
}

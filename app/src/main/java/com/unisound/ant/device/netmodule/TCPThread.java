package com.unisound.ant.device.netmodule;

import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.LogMgr;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPThread extends Thread {
    private static final String TAG = "ApWifi-TCPThread";
    private static final int UDP_CLIENT_PORT = 9997;
    String deviceId;
    String ip;
    boolean isStop = false;
    String mDeviceName;
    Socket mSocket = null;
    PrintWriter mWriter;
    String udid;

    public TCPThread(String Ip, String DeviceId, String udid2, String deviceName) {
        this.ip = Ip;
        this.deviceId = DeviceId;
        this.udid = udid2;
        this.mDeviceName = deviceName;
        LogMgr.d(TAG, "Ip:" + Ip + " DeviceId:" + DeviceId + " udid=" + udid2 + " deviceName=" + deviceName);
    }

    public void stopRun() {
        LogMgr.d(TAG, "stopRun");
        this.isStop = true;
        clearSocket();
    }

    public void run() {
        LogMgr.d(TAG, "tcp long conenct started");
        if (this.ip != null) {
            boolean isSuc = false;
            int tryCount = 0;
            while (!isSuc && tryCount < 10 && !this.isStop) {
                try {
                    LogMgr.d(TAG, "will conenct client ip : %s", this.ip);
                    Thread.sleep(1000);
                    tryCount++;
                    this.mSocket = new Socket(this.ip, (int) UDP_CLIENT_PORT);
                    this.mWriter = new PrintWriter(this.mSocket.getOutputStream());
                    StringBuffer buffer = new StringBuffer();
                    String split = String.valueOf((char) 31);
                    buffer.append(this.mDeviceName).append(split).append(this.deviceId).append(split).append(this.udid).append(split).append(ExoConstants.APP_KEY);
                    this.mWriter.print(buffer);
                    this.mWriter.flush();
                    LogMgr.d(TAG, "response device name : %s,deviceId: %s,udid: %s", this.mDeviceName, this.deviceId, this.udid);
                    isSuc = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    isSuc = false;
                } finally {
                    clearSocket();
                }
            }
        }
    }

    private void clearSocket() {
        try {
            if (this.mSocket != null) {
                this.mSocket.close();
                this.mSocket = null;
            }
            if (this.mWriter != null) {
                this.mWriter.close();
                this.mWriter = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

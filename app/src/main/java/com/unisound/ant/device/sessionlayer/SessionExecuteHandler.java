package com.unisound.ant.device.sessionlayer;

import android.os.Handler;
import android.os.Message;
import com.unisound.ant.device.bean.UnisoundDeviceCommand;
import com.unisound.ant.device.service.ActionResponse;

public class SessionExecuteHandler extends Handler {
    protected static final int MESSAGE_TYPE_CLOUD_RESPONSE = 1;
    protected static final int MESSAGE_TYPE_DSTSERVICE = 0;
    protected static final int MESSAGE_TYPE_SYNC_SERVICE_STATUS = 2;

    public void dispDstService(UnisoundDeviceCommand unisoundDeviceCommand) {
        Message msg = Message.obtain();
        msg.what = 0;
        msg.obj = unisoundDeviceCommand;
        sendMessage(msg);
    }

    public void dispActionResponse(ActionResponse actionResponse) {
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = actionResponse;
        sendMessage(msg);
    }

    public void dispSyncServiceStatusReq() {
        Message msg = Message.obtain();
        msg.what = 2;
        sendMessage(msg);
    }
}

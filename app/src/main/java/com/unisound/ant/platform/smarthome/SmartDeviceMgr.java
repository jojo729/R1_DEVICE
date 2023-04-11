package com.unisound.ant.platform.smarthome;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.unisound.ant.device.bean.*;
import com.unisound.ant.device.service.ActionResponse;
import com.unisound.ant.device.sessionlayer.SessionExecuteHandler;
import com.unisound.ant.device.sessionlayer.SessionRegister;
import com.unisound.ant.platform.smarthome.bean.SmartAction;
import com.unisound.ant.platform.smarthome.bean.SmartDeviceStatus;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;

import java.util.List;
import java.util.UUID;

public class SmartDeviceMgr extends SessionExecuteHandler implements SmartExecutor.ExecutorCallBack {
    private static final int SMART_DEVICE_SYNC_STATUS = 1;
    private static final String TAG = "SmartDeviceMgr";
    private Context context;
    private SmartExecutor executor;
    public ReportHandler reportHandler = new ReportHandler();

    public SmartDeviceMgr(ANTHandlerContext ctx) {
        this.context = ctx.androidContext();
        this.executor = new SmartExecutor(ctx);
        this.executor.setCallBack(this);
        SessionRegister.associateSessionCenter(DstServiceName.DST_SERVICE_CENTER_CONTROL, this);
    }

    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                dispatchSmartHouseControlCommand((UnisoundDeviceCommand) msg.obj);
                return;
            default:
                return;
        }
    }

    private void dispatchSmartHouseControlCommand(UnisoundDeviceCommand command) {
        if (command == null) {
            LogMgr.d(TAG, "dispatchSmartHouseControlCommand command is null");
            return;
        }
        LogMgr.d(TAG, "--->>dispatchSmartHouseControlCommand operate:" + command.getOperation());
        List<SmartAction> actions = (List) JsonTool.fromJson(((JsonObject) command.getParameter()).getAsJsonArray("operations"), new TypeToken<List<SmartAction>>() {
            /* class com.unisound.ant.platform.smarthome.SmartDeviceMgr.AnonymousClass1 */
        }.getType());
        if (actions == null) {
            LogMgr.e(TAG, "dispatchSmartHouseControlCommand actions is null");
        }
        this.executor.executeCommands(actions);
    }

    private void reportActionResp(String operation) {
        if (this.reportHandler != null && this.reportHandler.getLooper().getThread().isAlive()) {
            responseCloudCommand(operation, getActionResponse(0));
        }
    }

    private void responseCloudCommand(String action, ActionResponse response) {
        SessionRegister.getUpDownMessageManager().reponseCloudCommandWithoutAck(action, response);
    }

    @Override // com.unisound.ant.platform.smarthome.SmartExecutor.ExecutorCallBack
    public void onControlResult(String state, SmartDeviceStatus statusDetail) {
        if (this.reportHandler != null && this.reportHandler.getLooper().getThread().isAlive()) {
            Message msg = Message.obtain();
            msg.what = 1;
            msg.obj = statusDetail;
            this.reportHandler.sendMessage(msg);
        }
    }

    public class ReportHandler extends Handler {
        public ReportHandler() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    SmartDeviceMgr.this.updateExecuteStatus(DstServiceState.SERVICE_STATE_SETTING_OVER, CommandOperate.COMMAND_OPERATE_SMART_STATUS_SYNC, (SmartDeviceStatus) msg.obj);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateExecuteStatus(String dstStatus, String operate, SmartDeviceStatus aSmartStatus) {
        if (SessionRegister.getUpDownMessageManager() == null) {
            LogMgr.d(TAG, "--->>messgaeMonitor is null");
        } else {
            SessionRegister.getUpDownMessageManager().onReportStatus(DstServiceName.DST_SERVICE_CENTER_CONTROL, dstStatus, operate, aSmartStatus);
        }
    }

    public ActionResponse getActionResponse(int statusCode) {
        ActionResponse response = new ActionResponse();
        response.setActionStatus(statusCode);
        response.setDetailInfo(ActionStatus.getStateDetail(statusCode));
        response.setActionResponseId(UUID.randomUUID().toString());
        response.setActionTimestamp(System.currentTimeMillis() + "");
        return response;
    }
}

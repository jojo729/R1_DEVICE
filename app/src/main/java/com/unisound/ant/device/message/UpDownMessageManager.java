package com.unisound.ant.device.message;

import android.content.Context;
import android.text.TextUtils;
import com.unisound.ant.device.bean.Accelerate;
import com.unisound.ant.device.bean.AlarmReminder;
import com.unisound.ant.device.bean.DstServiceName;
import com.unisound.ant.device.bean.DstServiceState;
import com.unisound.ant.device.bean.MusicData;
import com.unisound.ant.device.bean.NoteInfo;
import com.unisound.ant.device.bean.SceneModeInfo;
import com.unisound.ant.device.bean.UnisoundDeviceCommand;
import com.unisound.ant.device.profile.DstServiceProfile;
import com.unisound.ant.device.service.ActionResponse;
import com.unisound.ant.device.service.BaseRequest;
import com.unisound.ant.device.sessionlayer.DialogProfile;
import com.unisound.ant.device.sessionlayer.SessionExecuteHandler;
import com.unisound.ant.device.sessionlayer.SessionRegister;
import com.unisound.vui.util.LogMgr;

public class UpDownMessageManager {
    private static final String TAG = "UpDownMessageManager";
    private Context context;
    private MessageExcuteListener listener;

    public interface MessageExcuteListener {
        void onExcuteResult(String str, String str2, String str3, DstServiceProfile dstServiceProfile);

        void onReportResponse(String str, String str2, String str3, ActionResponse actionResponse);
    }

    public UpDownMessageManager(Context context2) {
        this.context = context2;
        SessionRegister.saveUpDownMessageManager(this);
    }

    public void setMessageExcuteListener(MessageExcuteListener listener2) {
        this.listener = listener2;
    }

    public void dispatcherServiceMessage(String dstServiceName, DstServiceProfile dstServiceProfile) {
        LogMgr.d(TAG, "--->>dispatcherServiceMessage dstServiceName:" + dstServiceName);
        SessionExecuteHandler handler = SessionRegister.getSessionExecuter(dstServiceName);
        UnisoundDeviceCommand command = dstServiceProfile.getUniCommand();
        if (handler == null || command == null) {
            LogMgr.d(TAG, "--->>dispatcherServiceMessage handler may be null OR not register");
        } else {
            handler.dispDstService(command);
        }
    }

    public void dispatcherCloudResponse(String serviceName, ActionResponse actionResponse) {
        LogMgr.d(TAG, "--->>dispatcherCloudResponse dstServiceName:" + serviceName);
        SessionExecuteHandler handler = SessionRegister.getSessionExecuter(serviceName);
        if (handler == null || actionResponse == null) {
            LogMgr.d(TAG, "--->>dispatcherServiceMessage handler may be null OR not register");
        } else {
            handler.dispActionResponse(actionResponse);
        }
    }

    public void dispatcherSynServiceStatusReq(String serviceName) {
        LogMgr.d(TAG, "--->>dispatcherSynServiceStatusReq serviceName:" + serviceName);
        SessionExecuteHandler handler = SessionRegister.getSessionExecuter(serviceName);
        if (handler == null) {
            LogMgr.d(TAG, "--->>dispatcherServiceMessage handler may be null OR not register");
        } else {
            handler.dispSyncServiceStatusReq();
        }
    }

    public void onReportDeviceStatusWithAck(String dstStatus, String actionResponseId, UnisoundDeviceCommand command) {
        LogMgr.d(TAG, "--->>onReportDeviceStatusWithAck");
        DstServiceProfile dstServiceProfile = new DstServiceProfile();
        dstServiceProfile.setDstState(dstStatus);
        dstServiceProfile.setDstServiceName(command.getCapability());
        dstServiceProfile.setUniCommand(command);
        if (this.listener == null) {
            throw new IllegalArgumentException("listener may be null and please check");
        }
        this.listener.onExcuteResult(DialogProfile.DIALOG_FINISH, BaseRequest.MESSAGE_TYPE_PD_REQUEST, actionResponseId, dstServiceProfile);
    }

    public void syncInfoForDeviceWithAck(String dstStatus, String actionResponseId, UnisoundDeviceCommand command) {
        LogMgr.d(TAG, "--->>onReportDeviceStatusWithAck");
        DstServiceProfile dstServiceProfile = new DstServiceProfile();
        dstServiceProfile.setDstState(dstStatus);
        dstServiceProfile.setDstServiceName(command.getCapability());
        dstServiceProfile.setUniCommand(command);
        if (this.listener == null) {
            throw new IllegalArgumentException("listener may be null and please check");
        }
        this.listener.onExcuteResult(DialogProfile.DIALOG_FINISH, "synInfo", actionResponseId, dstServiceProfile);
    }

    public void onReportDeviceStatusWithAckWithOutAck(String dstStatus, UnisoundDeviceCommand command) {
        LogMgr.d(TAG, "--->>onReportDeviceStatusWithAckWithOutAck");
        DstServiceProfile dstServiceProfile = new DstServiceProfile();
        dstServiceProfile.setDstState(dstStatus);
        dstServiceProfile.setDstServiceName(command.getCapability());
        dstServiceProfile.setUniCommand(command);
        if (this.listener == null) {
            throw new IllegalArgumentException("listener may be null and please check");
        }
        this.listener.onExcuteResult(DialogProfile.DIALOG_FINISH, BaseRequest.MESSAGE_TYPE_GD_REQUEST, null, dstServiceProfile);
    }

    public void onReportMusicStatus(String dstStatus, String commandValue, MusicData data) {
        LogMgr.d(TAG, "--->>onReportMusicStatus");
        onReportStatus(DstServiceName.DST_SERVICE_MUSIC, dstStatus, "synInfo", commandValue, data);
    }

    public void onReportAudioStatus(String dstStatus, String commandValue, MusicData data) {
        LogMgr.d(TAG, "--->>onReportAudioStatus");
        onReportStatus(DstServiceName.DST_SERVICE_AUDIO, dstStatus, "synInfo", commandValue, data);
    }

    public void onReportNewsStatus(String dstStatus, String commandValue, MusicData data) {
        LogMgr.d(TAG, "--->>onReportAudioStatus");
        onReportStatus(DstServiceName.DST_SERVICE_NEWS, dstStatus, "synInfo", commandValue, data);
    }

    public void onReportModeSettingStatus(String dstStatus, String commandValue, SceneModeInfo data) {
        LogMgr.d(TAG, "--->>onReportMusicStatus");
        DstServiceProfile dstServiceProfile = new DstServiceProfile();
        dstServiceProfile.setDstState(dstStatus);
        dstServiceProfile.setDstServiceName("deviceManagement");
        Accelerate accelerate = new Accelerate();
        accelerate.setCommand("synInfo");
        accelerate.setValuse(commandValue);
        dstServiceProfile.setAccelerate(accelerate);
        dstServiceProfile.setParameter(data);
        if (this.listener == null) {
            throw new IllegalArgumentException("listener may be null and please check");
        }
        this.listener.onExcuteResult(DialogProfile.DIALOG_FINISH, "synInfo", null, dstServiceProfile);
    }

    public void onReportAlarmReminderStatus(String dstStatus, String commandValue, AlarmReminder data) {
        LogMgr.d(TAG, "--->>onReportAlarmReminderStatus");
        DstServiceProfile dstServiceProfile = new DstServiceProfile();
        if (TextUtils.isEmpty(dstStatus)) {
            dstStatus = DstServiceState.SERVICE_STATE_SETTING_OVER;
        }
        dstServiceProfile.setDstState(dstStatus);
        dstServiceProfile.setDstServiceName(DstServiceName.DST_SERVICE_MEMORY_MANAGER);
        Accelerate accelerate = new Accelerate();
        accelerate.setCommand("synInfo");
        accelerate.setValuse(commandValue);
        dstServiceProfile.setAccelerate(accelerate);
        dstServiceProfile.setParameter(data);
        if (this.listener == null) {
            throw new IllegalArgumentException("listener may be null and please check");
        }
        this.listener.onExcuteResult(DialogProfile.DIALOG_FINISH, "synInfo", null, dstServiceProfile);
    }

    public void onReportNoteStatus(String dstStatus, String commandValue, NoteInfo data) {
        LogMgr.d(TAG, "--->>onReportNoteStatus");
        DstServiceProfile dstServiceProfile = new DstServiceProfile();
        if (TextUtils.isEmpty(dstStatus)) {
            dstStatus = DstServiceState.SERVICE_STATE_SETTING_OVER;
        }
        dstServiceProfile.setDstState(dstStatus);
        dstServiceProfile.setDstServiceName(DstServiceName.DST_SERVICE_NOTE_MANAGER);
        Accelerate accelerate = new Accelerate();
        accelerate.setCommand("synInfo");
        accelerate.setValuse(commandValue);
        dstServiceProfile.setAccelerate(accelerate);
        dstServiceProfile.setParameter(data);
        if (this.listener == null) {
            throw new IllegalArgumentException("listener may be null and please check");
        }
        this.listener.onExcuteResult(DialogProfile.DIALOG_FINISH, "synInfo", null, dstServiceProfile);
    }

    public void onReportStatus(String commandValue, Object data) {
        onReportStatus("synInfo", commandValue, data);
    }

    public void onReportStatus(String command, String commandValue, Object data) {
        onReportStatus("deviceManagement", DstServiceState.SERVICE_STATE_SETTING_OVER, command, commandValue, data);
    }

    public void onReportStatus(String dstService, String dstStatus, String commandValue, Object data) {
        if (TextUtils.isEmpty(dstService)) {
            dstService = "deviceManagement";
        }
        onReportStatus(dstService, dstStatus, "synInfo", commandValue, data);
    }

    public void onReportStatus(String dstService, String dstStatus, String command, String commandValue, Object data) {
        LogMgr.d(TAG, "--->>onReportMusicStatus");
        DstServiceProfile dstServiceProfile = new DstServiceProfile();
        dstServiceProfile.setDstState(dstStatus);
        dstServiceProfile.setDstServiceName(dstService);
        Accelerate accelerate = new Accelerate();
        accelerate.setCommand(command);
        accelerate.setValuse(commandValue);
        dstServiceProfile.setAccelerate(accelerate);
        dstServiceProfile.setParameter(data);
        if (this.listener == null) {
            throw new IllegalArgumentException("listener may be null and please check");
        }
        this.listener.onExcuteResult(DialogProfile.DIALOG_FINISH, "synInfo", null, dstServiceProfile);
    }

    public void reponseCloudCommandWithoutAck(String action, ActionResponse actionResponse) {
        LogMgr.d(TAG, "--->>reponseCloudCommandWithoutAck");
        if (this.listener == null) {
            throw new IllegalArgumentException("listener may be null and please check");
        }
        this.listener.onReportResponse(DialogProfile.DIALOG_FINISH, BaseRequest.MESSAGE_TYPE_INTENT_ACK, action, actionResponse);
    }
}

package com.unisound.ant.device.sessionlayer;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.unisound.ant.device.bean.DstServiceState;
import com.unisound.ant.device.bean.SessionData;
import com.unisound.ant.device.message.UpDownMessageManager;
import com.unisound.ant.device.profile.DstServiceProfile;
import com.unisound.ant.device.service.ActionResponse;
import com.unisound.ant.device.service.CloudResponse;
import com.unisound.ant.device.service.ServiceProtocolUtil;
import com.unisound.vui.util.LogMgr;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class BaseSessionLayer implements UpDownMessageManager.MessageExcuteListener {
    private static final String TAG = "BaseSessionLayer";
    private static Map<String, DialogProfile> actionDialogs = new HashMap();
    private DialogProfile dialogProfile;
    private UpDownMessageManager upDownMessageManager;
    private SessionUpdateCallBack updateCallBack;

    public BaseSessionLayer(Context context, SessionUpdateCallBack updateCallBack2) {
        this.upDownMessageManager = new UpDownMessageManager(context);
        this.upDownMessageManager.setMessageExcuteListener(this);
        this.updateCallBack = updateCallBack2;
    }

    public void saveDialogStatus(String action, DialogProfile dialogProfile2) {
        actionDialogs.put(action, dialogProfile2);
        this.dialogProfile = dialogProfile2;
    }

    public DialogProfile getDialogStatus(String action) {
        this.dialogProfile = actionDialogs.get(action);
        if (this.dialogProfile == null) {
            this.dialogProfile = new DialogProfile();
        }
        return this.dialogProfile;
    }

    public void clearDialogStatus() {
        this.dialogProfile = null;
    }

    public void filterSessionContent(String message) {
        LogMgr.d(TAG, "--->>dispatcherMessage message:" + message);
        if (!TextUtils.isEmpty(message)) {
            CloudResponse<SessionData<JsonObject>> cloudResponse = ServiceProtocolUtil.getResponseHeader(message, new TypeToken<CloudResponse<SessionData<JsonObject>>>() {
                /* class com.unisound.ant.device.sessionlayer.BaseSessionLayer.AnonymousClass1 */
            }.getType());
            String messageType = cloudResponse.getMessageType();
            SessionData sessionData = cloudResponse.getMessageBody();
            if (sessionData == null) {
                LogMgr.e(TAG, "--parse sessionData is null and this session is not effective");
            } else if (CloudResponse.MESSAGE_TYPE_INTENT_ACTION.equals(messageType) || "synInfo".equals(messageType)) {
                try {
                    String action = sessionData.getDstService().getUniCommand().getOperation();
                    DialogProfile dialog = sessionData.getDialog();
                    if (dialog == null) {
                        LogMgr.e(TAG, "dialog is null");
                    } else {
                        saveDialogStatus(action, dialog);
                        LogMgr.d(TAG, "sessionDialog save action:" + action + ";actionResponseId:" + dialog.getSendToCloudResponse().getActionResponseId());
                        if (this.updateCallBack != null) {
                            this.updateCallBack.onSessionToSceneControl(dialog.getSceneFlag());
                        }
                    }
                } catch (Exception e) {
                    LogMgr.e(TAG, "--->>get action dialog exception");
                }
                dispatchControlSessionMsg(sessionData.getDialog().getDstService(), sessionData.getDstService());
            } else if (CloudResponse.MESSAGE_TYPE_UPDATE_REPONSE.equals(messageType) || CloudResponse.MESSAGE_TYPE_UPLOAD_PD_REPONSE.equals(messageType)) {
                receiveCloudResponseSessionMsg(sessionData.getDialog().getDstService(), sessionData.getActionResponse());
            } else if (CloudResponse.MESSAGE_TYPE_START_SYN.equals(messageType)) {
                LogMgr.d(TAG, "cloud command start syn info");
                dispatchSyncServiceStatusReq(sessionData.getDialog().getDstService());
            }
        }
    }

    private void dispatchSyncServiceStatusReq(String serviceName) {
        if (this.upDownMessageManager == null || serviceName == null) {
            throw new IllegalStateException("upDownMessageManager or serviceName is null and please check");
        }
        this.upDownMessageManager.dispatcherSynServiceStatusReq(serviceName);
    }

    private void dispatchControlSessionMsg(String serviceName, DstServiceProfile dstServiceProfile) {
        if (this.upDownMessageManager == null || dstServiceProfile == null) {
            throw new IllegalStateException("upDownMessageManager or dstServiceProfile is null and please check");
        }
        this.upDownMessageManager.dispatcherServiceMessage(serviceName, dstServiceProfile);
    }

    private void receiveCloudResponseSessionMsg(String serviceName, ActionResponse actionResponse) {
        if (actionResponse != null && this.upDownMessageManager != null) {
            if (TextUtils.isEmpty(serviceName)) {
                serviceName = "deviceManagement";
            }
            this.upDownMessageManager.dispatcherCloudResponse(serviceName, actionResponse);
            LogMgr.d(TAG, "--->>receiveCloudResponseSessionMsg actionResponse:" + actionResponse.toString());
        }
    }

    @Override // com.unisound.ant.device.message.UpDownMessageManager.MessageExcuteListener
    public void onExcuteResult(String state, String messageType, String actionResponseId, DstServiceProfile dstServiceProfile) {
        DialogProfile dialog = new DialogProfile();
        if (dstServiceProfile.getUniCommand() != null) {
            dialog = getDialogStatus(dstServiceProfile.getUniCommand().getOperation());
        } else if (dstServiceProfile.getAccelerate() != null) {
            dialog = getDialogStatus(dstServiceProfile.getAccelerate().getValuse());
        }
        try {
            actionResponseId = dialog.getSendToCloudResponse().getActionResponseId();
            dialog.setSendToTerminalResponse(new DialogProfile.TerminalResponse(actionResponseId));
            dialog.setSendToCloudResponse(new DialogProfile.TerminalResponse(actionResponseId));
        } catch (Exception e) {
            LogMgr.e(TAG, "get actionResponseId has exception");
            dialog.setSendToTerminalResponse(new DialogProfile.TerminalResponse(actionResponseId));
            dialog.setSendToCloudResponse(new DialogProfile.TerminalResponse(actionResponseId));
        }
        dialog.setDstState(state);
        dialog.setDstService(dstServiceProfile.getDstServiceName());
        SessionData sessionData = new SessionData();
        sessionData.setDialog(dialog);
        sessionData.setDstService(dstServiceProfile);
        try {
            String action = dstServiceProfile.getUniCommand().getOperation();
            saveDialogStatus(action, sessionData.getDialog());
            LogMgr.d(TAG, "sessionDialog onExcuteResult save action:" + action + ";actionResponseId:" + actionResponseId);
        } catch (Exception e2) {
            LogMgr.e(TAG, "--->>get action dialog exception");
        }
        if (this.updateCallBack == null) {
            LogMgr.d(TAG, "--->>sessionUpdataCallBack may be null");
        } else {
            this.updateCallBack.onSessionDataUpdate(messageType, sessionData);
        }
    }

    @Override // com.unisound.ant.device.message.UpDownMessageManager.MessageExcuteListener
    public void onReportResponse(String dialogState, String messageType, String action, ActionResponse actionResponse) {
        DialogProfile dialog = getDialogStatus(action);
        dialog.setDstState(dialogState);
        String actionResponseId = UUID.randomUUID().toString();
        try {
            actionResponseId = dialog.getSendToCloudResponse().getActionResponseId();
        } catch (Exception e) {
            LogMgr.d(TAG, "get actionResponseId has exception");
        }
        LogMgr.d(TAG, "sessionDialog report action:" + action + ";actionResponseId:" + actionResponseId);
        actionResponse.setActionResponseId(actionResponseId);
        SessionData sessionData = new SessionData();
        sessionData.setDialog(dialog);
        DstServiceProfile dstServiceProfile = new DstServiceProfile();
        dstServiceProfile.setDstState(DstServiceState.SERVICE_STATE_SETTING_OVER);
        sessionData.setDstService(dstServiceProfile);
        sessionData.setActionResponse(actionResponse);
        if (this.updateCallBack == null) {
            LogMgr.d(TAG, "--->>sessionUpdataCallBack may be null");
            return;
        }
        this.updateCallBack.onSessionDataUpdate(messageType, sessionData);
        clearDialogStatus();
    }
}

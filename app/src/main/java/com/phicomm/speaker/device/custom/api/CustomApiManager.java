package com.phicomm.speaker.device.custom.api;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.google.gson.JsonObject;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.ipc.PhicommLightController;
import com.phicomm.speaker.device.custom.ipc.PhicommXController;
import com.phicomm.speaker.device.custom.music.PhicommPlayer;
import com.phicomm.speaker.device.custom.status.PhicommDeviceStatusProcessor;
import com.phicomm.speaker.device.utils.LogUtils;
import com.phicomm.speaker.device.utils.PhicommPerferenceUtil;
import com.phicomm.speaker.device.utils.TTSUtils;
import com.unisound.ant.device.bean.ActionStatus;
import com.unisound.ant.device.bean.DevicePlayingType;
import com.unisound.ant.device.bean.DstServiceName;
import com.unisound.ant.device.bean.SelfDefinationRequestInfo;
import com.unisound.ant.device.bean.SelfDefinationResponseInfo;
import com.unisound.ant.device.bean.UnisoundDeviceCommand;
import com.unisound.ant.device.service.ActionResponse;
import com.unisound.ant.device.sessionlayer.SessionExecuteHandler;
import com.unisound.ant.device.sessionlayer.SessionRegister;
import com.unisound.vui.common.media.UniMediaPlayer;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.handler.session.memo.utils.RingingUtils;
import com.unisound.vui.transport.out.ChangeWakeupWordEvent;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.UserPerferenceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CustomApiManager extends SessionExecuteHandler {
    public static final String AMBIENT_LIGHT_OFF = "0";
    public static final String AMBIENT_LIGHT_ON = "1";
    public static final String DORMANT_OFF = "0";
    public static final String DORMANT_ON = "1";
    private static final String TAG = CustomApiManager.class.getSimpleName();
    private static final String TYPE_LIGHT_OFF = "1";
    private static final String TYPE_LIGHT_ON = "0";
    public static final int TYPE_RINGING = 0;
    private final Context mContext;
    private ANTEngine mEngine;
    private SparseArray<List<CustomApiListener>> mListenerMap = new SparseArray<>();
    private final PhicommPlayer mPhicommPlayer;
    private PhicommLightController phicommLightController;
    private PhicommXController phicommXController;

    public CustomApiManager(Context context, ANTEngine engine, PhicommPlayer phicommPlayer) {
        this.mContext = context;
        this.mEngine = engine;
        this.mPhicommPlayer = phicommPlayer;
        SessionRegister.associateSessionCenter(DstServiceName.DST_SERVICE_SELF_DEFINATION, this);
        this.phicommLightController = new PhicommLightController(context);
        this.phicommXController = new PhicommXController(context);
    }

    public void addListener(int type, CustomApiListener listener) {
        List<CustomApiListener> list = this.mListenerMap.get(type);
        if (list == null) {
            list = new ArrayList<>();
            this.mListenerMap.put(type, list);
        }
        list.add(listener);
    }

    public void removeListener(int type, CustomApiListener listener) {
        List<CustomApiListener> list = this.mListenerMap.get(type);
        if (list != null) {
            list.remove(listener);
        }
    }

    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                handleSelfDefinedCommand((UnisoundDeviceCommand) msg.obj);
                return;
            case 1:
            case 2:
            default:
                return;
        }
    }

    private void handleSelfDefinedCommand(UnisoundDeviceCommand command) {
        SelfDefinationRequestInfo selfDefinationRequestInfo = (SelfDefinationRequestInfo) JsonTool.fromJson((JsonObject) command.getParameter(), SelfDefinationRequestInfo.class);
        String operationType = selfDefinationRequestInfo.getOperationType();
        if (TextUtils.isEmpty(operationType)) {
            LogMgr.w(TAG, "operationType is null, ignore");
            return;
        }
        char c = 65535;
        switch (operationType.hashCode()) {
            case -1800049821:
                if (operationType.equals(SelfDefinationRequestInfo.MODIFY_WAKE_UP_WORD)) {
                    c = 1;
                    break;
                }
                break;
            case -1215778683:
                if (operationType.equals(SelfDefinationRequestInfo.RESET_DEVICE)) {
                    c = '\n';
                    break;
                }
                break;
            case -1171359559:
                if (operationType.equals(SelfDefinationRequestInfo.AUDITION_TTS_SPEAKER)) {
                    c = '\t';
                    break;
                }
                break;
            case -1033755431:
                if (operationType.equals(SelfDefinationRequestInfo.MODIFY_DORMANT_STATUS)) {
                    c = 4;
                    break;
                }
                break;
            case -320427075:
                if (operationType.equals(SelfDefinationRequestInfo.GET_DORMANT_STATUS)) {
                    c = 5;
                    break;
                }
                break;
            case -263001055:
                if (operationType.equals(SelfDefinationRequestInfo.MODIFY_DORMANT_LIGHT_STATUS)) {
                    c = 3;
                    break;
                }
                break;
            case -22720282:
                if (operationType.equals(SelfDefinationRequestInfo.GET_AMBIENT_LIGHT_STATUS)) {
                    c = 7;
                    break;
                }
                break;
            case 137701706:
                if (operationType.equals(SelfDefinationRequestInfo.MODIFY_AMBIENT_LIGHT_STATUS)) {
                    c = 6;
                    break;
                }
                break;
            case 976071245:
                if (operationType.equals(SelfDefinationRequestInfo.AUDITION_RINGING)) {
                    c = '\b';
                    break;
                }
                break;
            case 1218847283:
                if (operationType.equals(SelfDefinationRequestInfo.GET_DEVICE_INFO)) {
                    c = '\f';
                    break;
                }
                break;
            case 1493475098:
                if (operationType.equals(SelfDefinationRequestInfo.MODIFY_TTS_PLAYER)) {
                    c = 2;
                    break;
                }
                break;
            case 1879432826:
                if (operationType.equals(SelfDefinationRequestInfo.CHECK_DEVICE_STATE)) {
                    c = 0;
                    break;
                }
                break;
            case 1980659193:
                if (operationType.equals(SelfDefinationRequestInfo.GET_LIGHTING_STATUS)) {
                    c = 11;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                handleCheckDeviceState();
                break;
            case 1:
                handleModifyWakeUpWord(selfDefinationRequestInfo.getContent());
                break;
            case 2:
                handleModifyTtsPlayer(selfDefinationRequestInfo.getContent());
                break;
            case 3:
                handleModifyDormantLightStatus(selfDefinationRequestInfo.getContent());
                break;
            case 4:
                handleModifyDormantStatus(selfDefinationRequestInfo.getContent());
                break;
            case 5:
                handleGetDormantStatus();
                break;
            case 6:
                handleModifyAmbientLightStatus(selfDefinationRequestInfo.getContent());
                break;
            case 7:
                handleGetAmbientLightStatus();
                break;
            case '\b':
                handleAuditionRinging(selfDefinationRequestInfo.getContent());
                break;
            case '\t':
                handleAuditionTtsSpeaker(selfDefinationRequestInfo.getContent());
                break;
            case '\n':
                handleResetDevice();
                break;
            case 11:
                handleGetLightingStatus();
                break;
            case '\f':
                handleGetDeviceInfo();
                break;
        }
        SessionRegister.getUpDownMessageManager().reponseCloudCommandWithoutAck(command.getOperation(), getActionResponse(0));
    }

    private void handleResetDevice() {
        LogMgr.d(TAG, "handleResetDevice: ");
        this.phicommXController.resetDevice();
        responseToClient(SelfDefinationRequestInfo.RESET_DEVICE, 0);
    }

    private void handleCheckDeviceState() {
        responseToClient(SelfDefinationRequestInfo.CHECK_DEVICE_STATE, 0, new DevicePlayingType(this.mPhicommPlayer.getDevicePlayingType()));
    }

    private void handleModifyWakeUpWord(Map<String, Object> content) {
        int status = -1;
        try {
            Object wakeUpWord = content.get("wakeUpWord");
            if (wakeUpWord != null) {
                status = processChangeWakeupWord((String) wakeUpWord);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "handleModifyWakeUpWord error : ", e);
        }
        responseToClient(SelfDefinationRequestInfo.MODIFY_WAKE_UP_WORD, status);
    }

    private void handleModifyTtsPlayer(Map<String, Object> content) {
        int status = -1;
        boolean ttsPlaying = this.mEngine.isTTSPlaying();
        LogMgr.d(TAG, "isTTSPlaying = " + ttsPlaying);
        if (!ttsPlaying) {
            try {
                Object ttsPlayer = content.get("ttsPlayer");
                if (ttsPlayer != null) {
                    status = processSwitchTTSSpeaker((String) ttsPlayer);
                }
            } catch (Exception e) {
                LogUtils.e(TAG, "handleModifyTtsPlayer error : ", e);
            }
        } else {
            status = ResponseStatus.STATUS_FAIL_SWITCH_TTS_SPEAKER_TTS_PLAYING;
        }
        responseToClient(SelfDefinationRequestInfo.MODIFY_TTS_PLAYER, status);
    }

    private void handleModifyDormantLightStatus(Map<String, Object> content) {
        int status = -1;
        try {
            Object isLighting = content.get("isLighting");
            if (isLighting != null) {
                status = processChangeLightingStatus((String) isLighting);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "handleModifyDormantLightStatus error : ", e);
        }
        responseToClient(SelfDefinationRequestInfo.MODIFY_DORMANT_LIGHT_STATUS, status);
    }

    private void handleModifyDormantStatus(Map<String, Object> content) {
        boolean isDormant = true;
        int status = -1;
        Map<String, String> resContent = new HashMap<>();
        try {
            Object switchSleepMode = content.get("isDormant");
            LogUtils.w(TAG, "modifyDormantStatus switchSleepMode = " + switchSleepMode);
            if (switchSleepMode != null) {
                if (Integer.parseInt(switchSleepMode.toString()) != 1) {
                    isDormant = false;
                }
                if (isDormant) {
                    status = openSleepMode();
                    if (status == 0) {
                        resContent.put(SelfDefinationRequestInfo.CURRENT_DORMANT_STATUS, "1");
                    } else {
                        resContent.put(SelfDefinationRequestInfo.CURRENT_DORMANT_STATUS, "0");
                    }
                } else {
                    status = closeSleepMode();
                    if (status == 0) {
                        resContent.put(SelfDefinationRequestInfo.CURRENT_DORMANT_STATUS, "0");
                    } else {
                        resContent.put(SelfDefinationRequestInfo.CURRENT_DORMANT_STATUS, "1");
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "modifyDormantStatus error : ", e);
        }
        responseToClient(SelfDefinationRequestInfo.MODIFY_DORMANT_STATUS, status, resContent);
    }

    private void handleGetDormantStatus() {
        Map<String, String> content = new HashMap<>();
        content.put(SelfDefinationRequestInfo.CURRENT_DORMANT_STATUS, getDormantStatus());
        responseToClient(SelfDefinationRequestInfo.GET_DORMANT_STATUS, 0, content);
    }

    private void handleModifyAmbientLightStatus(Map<String, Object> content) {
        boolean isAmbientLight = true;
        int status = -1;
        Map<String, String> resContent = new HashMap<>();
        try {
            Object switchAmbientLight = content.get("isAmbientLight");
            LogUtils.w(TAG, "modifyAmbientLightStatus switchAmbientLight = " + switchAmbientLight);
            if (switchAmbientLight != null) {
                if (Integer.parseInt(switchAmbientLight.toString()) != 1) {
                    isAmbientLight = false;
                }
                if (isAmbientLight) {
                    status = openAmbientLight();
                    if (status == 0) {
                        resContent.put(SelfDefinationRequestInfo.CURRENT_AMBIENT_LIGHT_STATUS, "1");
                    } else {
                        resContent.put(SelfDefinationRequestInfo.CURRENT_AMBIENT_LIGHT_STATUS, "0");
                    }
                } else {
                    status = closeAmbientLight();
                    if (status == 0) {
                        resContent.put(SelfDefinationRequestInfo.CURRENT_AMBIENT_LIGHT_STATUS, "0");
                    } else {
                        resContent.put(SelfDefinationRequestInfo.CURRENT_AMBIENT_LIGHT_STATUS, "1");
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "modifyAmbientLightStatus error : ", e);
        }
        responseToClient(SelfDefinationRequestInfo.MODIFY_AMBIENT_LIGHT_STATUS, status, resContent);
    }

    private void handleGetAmbientLightStatus() {
        Map<String, String> content = new HashMap<>();
        content.put(SelfDefinationRequestInfo.CURRENT_AMBIENT_LIGHT_STATUS, PhicommPerferenceUtil.getAmbientLightState(this.mContext) ? "1" : "0");
        responseToClient(SelfDefinationRequestInfo.GET_AMBIENT_LIGHT_STATUS, 0, content);
    }

    private void handleAuditionRinging(Map<String, Object> content) {
        int status = -1;
        try {
            Object alarmFile = content.get("alarmFile");
            if (alarmFile != null && RingingUtils.getRingingFile((String) alarmFile).exists()) {
                status = 0;
                List<CustomApiListener> list = this.mListenerMap.get(0);
                if (list != null) {
                    for (CustomApiListener listener : list) {
                        listener.onCustomEvent(1, alarmFile);
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "handleAuditionRinging error : ", e);
        }
        responseToClient(SelfDefinationRequestInfo.AUDITION_RINGING, status);
    }

    private void handleAuditionTtsSpeaker(Map<String, Object> content) {
        int status = -1;
        try {
            String speaker = content.get("ttsSpeaker").toString();
            LogUtils.d(TAG, "audition tts speaker, speaker is " + speaker);
            if (!TextUtils.isEmpty(speaker)) {
                status = listeningTtsSpeaker(speaker);
            } else {
                LogUtils.d(TAG, "audition tts speaker, but speaker is null");
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "handleAuditionTtsSpeaker error : ", e);
        }
        responseToClient(SelfDefinationRequestInfo.AUDITION_TTS_SPEAKER, status);
    }

    private int listeningTtsSpeaker(String speaker) {
        String speakerFile;
        char c = 65535;
        switch (speaker.hashCode()) {
            case -1852813825:
                if (speaker.equals("CHILDREN")) {
                    c = 3;
                    break;
                }
                break;
            case 75902:
                if (speaker.equals("LZL")) {
                    c = 5;
                    break;
                }
                break;
            case 2358797:
                if (speaker.equals("MALE")) {
                    c = 2;
                    break;
                }
                break;
            case 79312592:
                if (speaker.equals("SWEET")) {
                    c = 4;
                    break;
                }
                break;
            case 2070122316:
                if (speaker.equals("FEMALE")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 1:
                speakerFile = "system/unisound/audio/tts_audition/" + "tts_speaker_xiaowen.wav";
                break;
            case 2:
                speakerFile = "system/unisound/audio/tts_audition/" + "tts_speaker_xiaofeng.wav";
                break;
            case 3:
                speakerFile = "system/unisound/audio/tts_audition/" + "tts_speaker_tangtang.wav";
                break;
            case 4:
                speakerFile = "system/unisound/audio/tts_audition/" + "tts_speaker_xuanxuan.wav";
                break;
            case 5:
                speakerFile = "system/unisound/audio/tts_audition/" + "tts_speaker_lingling.wav";
                break;
            default:
                LogUtils.d(TAG, "audition tts speaker, but speaker is wrong, speaker = " + speaker);
                return ResponseStatus.STATUS_FAIL_AUDITION_TTS_SPEAKER;
        }
        UniMediaPlayer.getInstance().play(speakerFile, null);
        return 0;
    }

    private void handleGetLightingStatus() {
        Map<String, String> content = new HashMap<>();
        content.put("isLighting", parseDormantLightStatus(UserPerferenceUtil.getDormantLightState(this.mContext)));
        responseToClient(SelfDefinationRequestInfo.GET_LIGHTING_STATUS, 0, content);
    }

    private void handleGetDeviceInfo() {
        Map<String, Object> content = new HashMap<>();
        content.put("ttsPlayer", UserPerferenceUtil.getUserTTSModelType(this.mContext));
        content.put("wakeUpWord", UserPerferenceUtil.getActuallyMainWakeupWord(this.mContext));
        responseToClient(SelfDefinationRequestInfo.GET_DEVICE_INFO, 0, content);
    }

    private int processChangeWakeupWord(@NonNull String wakeupWord) {
        if (!isWakeupWordValid(wakeupWord)) {
            return ResponseStatus.STATUS_FAIL_SET_WAKEUP_WORD;
        }
        ArrayList<String> list = new ArrayList<>();
        list.add(wakeupWord);
        this.mEngine.cancelTTS();
        this.mEngine.cancelASR();
        this.mEngine.stopWakeup();
        this.mEngine.updateWakeupWord(list);
        this.mEngine.pipeline().fireUserEventTriggered(new ChangeWakeupWordEvent(wakeupWord));
        return 0;
    }

    private boolean isWakeupWordValid(@NonNull String wakeupWord) {
        if (!TextUtils.isEmpty(wakeupWord) && wakeupWord.length() >= 4 && wakeupWord.length() <= 6) {
            return true;
        }
        LogUtils.d(TAG, "change wakeup word error,new wakeup word is " + wakeupWord);
        return false;
    }

    private int processSwitchTTSSpeaker(String ttsSpeaker) {
        TTSUtils.switchSpeaker(this.mContext, this.mEngine, ttsSpeaker);
        return 0;
    }

    private String parseDormantLightStatus(boolean dormantLightState) {
        return dormantLightState ? "0" : "1";
    }

    private String getDormantStatus() {
        if (PhicommDeviceStatusProcessor.getInstance().getDeviceStatus() == 5) {
            return "1";
        }
        return "0";
    }

    private void responseToClient(String type, int status) {
        responseToClient(type, status, null);
    }

    private void responseToClient(String type, int status, Object content) {
        SessionRegister.getUpDownMessageManager().onReportStatus(DstServiceName.DST_SERVICE_SELF_DEFINATION, null, DstServiceName.DST_SERVICE_SELF_DEFINATION, DstServiceName.DST_SERVICE_SELF_DEFINATION, new SelfDefinationResponseInfo(type, status, content));
    }

    private ActionResponse getActionResponse(int statusCode) {
        ActionResponse response = new ActionResponse();
        response.setActionStatus(statusCode);
        response.setDetailInfo(ActionStatus.getStateDetail(statusCode));
        response.setActionResponseId(UUID.randomUUID().toString());
        response.setActionTimestamp(System.currentTimeMillis() + "");
        return response;
    }

    private int processChangeLightingStatus(String lighting) {
        LogMgr.d(TAG, "processChangeLightingStatus: " + lighting);
        if (PhicommDeviceStatusProcessor.getInstance().getDeviceStatus() != 5) {
            return ResponseStatus.STATUS_FAIL_CHANGE_LIGHTING_STATUS;
        }
        if (lighting.equals("0")) {
            this.phicommLightController.turnOnDormantLight();
            UserPerferenceUtil.setDormantLightState(this.mContext, true);
            return 0;
        }
        this.phicommLightController.turnOffDormantLight();
        UserPerferenceUtil.setDormantLightState(this.mContext, false);
        return 0;
    }

    private int openSleepMode() {
        if (PhicommDeviceStatusProcessor.getInstance().getDeviceStatus() == 5) {
            return 0;
        }
        this.phicommXController.triggeredDoubleClickEvent();
        return 0;
    }

    private int closeSleepMode() {
        if (PhicommDeviceStatusProcessor.getInstance().getDeviceStatus() != 5) {
            return 0;
        }
        this.phicommXController.triggeredOneClickEvent();
        return 0;
    }

    private int openAmbientLight() {
        if (!PhicommPerferenceUtil.getAmbientLightState(this.mContext)) {
            this.phicommXController.openAmbientLight();
            PhicommPerferenceUtil.setAmbientLightState(this.mContext, true);
        }
        this.mEngine.playTTS(this.mContext.getString(R.string.tts_open_ambientlight));
        return 0;
    }

    private int closeAmbientLight() {
        if (PhicommPerferenceUtil.getAmbientLightState(this.mContext)) {
            this.phicommXController.closeAmbientLight();
            PhicommPerferenceUtil.setAmbientLightState(this.mContext, false);
        }
        this.mEngine.playTTS(this.mContext.getString(R.string.tts_close_ambientlight));
        return 0;
    }
}

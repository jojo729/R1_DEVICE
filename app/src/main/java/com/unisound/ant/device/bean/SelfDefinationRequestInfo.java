package com.unisound.ant.device.bean;

import java.util.Map;

public class SelfDefinationRequestInfo {
    public static final String AUDITION_RINGING = "audition";
    public static final String AUDITION_TTS_SPEAKER = "auditionTtsSpeaker";
    public static final String CHECK_DEVICE_STATE = "checkDeviceStateManager";
    public static final String CURRENT_AMBIENT_LIGHT_STATUS = "currentAmbientLightStatus";
    public static final String CURRENT_DORMANT_STATUS = "currentDormantStatus";
    public static final String GET_AMBIENT_LIGHT_STATUS = "getAmbientLightStatus";
    public static final String GET_DEVICE_INFO = "getDeviceInfoManager";
    public static final String GET_DORMANT_STATUS = "getDormantStatus";
    public static final String GET_LIGHTING_STATUS = "getLightingStatusManager";
    public static final String MODIFY_AMBIENT_LIGHT_STATUS = "modifyAmbientLightStatus";
    public static final String MODIFY_DORMANT_LIGHT_STATUS = "modifyDormantLightStatus";
    public static final String MODIFY_DORMANT_STATUS = "modifyDormantStatus";
    public static final String MODIFY_TTS_PLAYER = "modifyTtsPlayer";
    public static final String MODIFY_WAKE_UP_WORD = "modifyWakeUpWord";
    public static final String RESET_DEVICE = "resetDevice";
    private Map<String, Object> content;
    private String operationType;

    public String getOperationType() {
        return this.operationType;
    }

    public void setOperationType(String operationType2) {
        this.operationType = operationType2;
    }

    public Map<String, Object> getContent() {
        return this.content;
    }

    public void setContent(Map<String, Object> content2) {
        this.content = content2;
    }
}

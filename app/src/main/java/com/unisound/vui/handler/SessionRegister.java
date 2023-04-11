package com.unisound.vui.handler;

import java.util.LinkedHashMap;

public class SessionRegister {
    public static final String DEVICE_CENTER = "device_center";
    public static final String NLU_FILTER = "nlu_filter";
    public static final String SEESION_SWITCH_TTS_MODEL = "seesion_switch_tts_model";
    public static final String SESSION_ALARM = "session_alarm";
    public static final String SESSION_ANT_LAUNCH = "session_launch";
    public static final String SESSION_APCONNECTION = "session_apconnection";
    public static final String SESSION_BROADCAST = "session_broadcast";
    public static final String SESSION_CHAT = "session_chat";
    public static final String SESSION_CONNECTION = "session_connection";
    public static final String SESSION_MANAGERAPP = "session_managerapp";
    public static final String SESSION_MUSIC = "session_music";
    public static final String SESSION_OTA = "session_ota";
    public static final String SESSION_PLAYTTS = "session_playtts";
    public static final String SESSION_PUSH = "session_push";
    public static final String SESSION_REMINDER = "session_reminder";
    public static final String SESSION_SCENEMODE = "session_scenemode";
    public static final String SESSION_SETTING = "session_setting";
    public static final String SESSION_STOCK = "session_stock";
    public static final String SESSION_TRAFFIC_CONTROL = "session_traffic_control";
    public static final String SESSION_UNSUPPORT = "session_unsupport";
    public static final String SESSION_WEATHER = "session_weather";
    private static volatile SessionRegister instance;
    private LinkedHashMap<String, SimpleUserEventInboundHandler> userSession = new LinkedHashMap<>();

    private SessionRegister() {
    }

    public static SessionRegister getInstance() {
        if (instance == null) {
            synchronized (SessionRegister.class) {
                if (instance == null) {
                    instance = new SessionRegister();
                }
            }
        }
        return instance;
    }

    public SimpleUserEventInboundHandler getUserHandler(String name) {
        if (this.userSession.containsKey(name)) {
            return this.userSession.get(name);
        }
        return null;
    }

    public void recycle() {
        instance = null;
    }

    public void registerUserSession(String name, SimpleUserEventInboundHandler simpleUserHandler) {
        if (!this.userSession.containsValue(simpleUserHandler)) {
            this.userSession.put(name, simpleUserHandler);
            return;
        }
        throw new IllegalArgumentException("userEventInboundHandler can't repeat to add");
    }
}

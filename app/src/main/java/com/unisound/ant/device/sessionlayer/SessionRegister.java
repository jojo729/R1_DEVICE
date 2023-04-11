package com.unisound.ant.device.sessionlayer;

import com.unisound.ant.device.message.UpDownMessageManager;
import java.util.HashMap;
import java.util.Map;

public final class SessionRegister {
    private static Map<String, SessionExecuteHandler> sessionExecuters = new HashMap();
    private static UpDownMessageManager upDownMessageManager;

    public static void saveUpDownMessageManager(UpDownMessageManager manager) {
        upDownMessageManager = manager;
    }

    public static UpDownMessageManager getUpDownMessageManager() {
        return upDownMessageManager;
    }

    public static void associateSessionCenter(String dstName, SessionExecuteHandler executerHandler) {
        if (!sessionExecuters.containsKey(dstName)) {
            sessionExecuters.put(dstName, executerHandler);
        }
    }

    public static SessionExecuteHandler getSessionExecuter(String dstName) {
        return sessionExecuters.get(dstName);
    }
}

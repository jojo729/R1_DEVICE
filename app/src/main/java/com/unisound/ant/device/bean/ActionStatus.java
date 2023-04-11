package com.unisound.ant.device.bean;

import java.util.HashMap;
import java.util.Map;

public final class ActionStatus {
    public static final int STATE_FAIL = -1;
    public static final String STATE_FAIL_DETAIL = "fail";
    public static final int STATE_OK = 0;
    public static final String STATE_OK_DETAIL = "ok";
    private static Map<Integer, String> detailMap = new HashMap();

    static {
        detailMap.put(-1, "fail");
        detailMap.put(0, "ok");
    }

    public static String getStateDetail(int state) {
        return detailMap.get(Integer.valueOf(state));
    }
}

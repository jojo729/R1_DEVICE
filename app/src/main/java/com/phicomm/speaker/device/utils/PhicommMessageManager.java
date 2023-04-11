package com.phicomm.speaker.device.utils;

import android.content.Context;
import android.os.MessageDispatchManager;

public class PhicommMessageManager {
    public static int DOMAIN_DEVICE_ID = 1;
    public static int DOMAIN_MATCH_PHIJOIN_CONFIG = 11;
    public static int DOMAIN_UDID = 2;
    private static final String PHICOMM_MESSAGE_CENTER_SYSTEM_NAME = "msgcenter";

    public static MessageDispatchManager messageCenter(Context context) {
        return (MessageDispatchManager) context.getApplicationContext().getSystemService(PHICOMM_MESSAGE_CENTER_SYSTEM_NAME);
    }
}

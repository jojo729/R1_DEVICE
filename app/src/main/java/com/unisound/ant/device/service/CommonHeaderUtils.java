package com.unisound.ant.device.service;

import com.unisound.vui.util.AppGlobalConstant;
import com.unisound.vui.util.SystemUitls;

public final class CommonHeaderUtils {
    private CommonHeaderUtils() {
    }

    public static MessageHeader buildUpdateReqHeader(String messageType) {
        return new MessageHeader(messageType, SystemUitls.getAppVersion(AppGlobalConstant.getContext()));
    }

    public static MessageHeader buildUpdateReqHeader() {
        return new MessageHeader(BaseRequest.MESSAGE_TYPE_GD_REQUEST, SystemUitls.getAppVersion(AppGlobalConstant.getContext()));
    }

    public static MessageHeader buildUploadReqHeader() {
        return new MessageHeader(BaseRequest.MESSAGE_TYPE_PD_REQUEST, SystemUitls.getAppVersion(AppGlobalConstant.getContext()));
    }
}

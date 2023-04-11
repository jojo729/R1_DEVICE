package com.unisound.ant.device.service;

import com.unisound.vui.util.JsonTool;
import java.lang.reflect.Type;

public final class ServiceProtocolUtil {
    private ServiceProtocolUtil() {
    }

    public static <E> BaseRequest<E> getReqContent(MessageHeader requestHeader, E data) {
        return new BaseRequest.Builder().setRequestHeader(requestHeader).setData(data).build();
    }

    public static <E> BaseRequest<E> getReqContent(E data) {
        return new BaseRequest.Builder().setRequestHeader(CommonHeaderUtils.buildUpdateReqHeader()).setData(data).build();
    }

    public static <E> BaseRequest<E> getReqContent(String messageType, E data) {
        return new BaseRequest.Builder().setRequestHeader(CommonHeaderUtils.buildUpdateReqHeader(messageType)).setData(data).build();
    }

    public static <E> CloudResponse<E> getResponseHeader(String response, Type type) {
        return (CloudResponse) JsonTool.fromJson(response, type);
    }
}

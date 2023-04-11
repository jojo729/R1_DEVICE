package com.unisound.vui.util;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.*;

public class HttpUtils {
    private static final byte DEFAULT_CONNECT_TIMEOUT = 30;
    private static final String DEFAULT_HTTP_TAG = "defaultHttpTag";
    private static final byte DEFAULT_READ_TIMEOUT = 30;
    private static final byte DEFAULT_WRITE_TIMEOUT = 30;
    private static final String TAG = HttpUtils.class.getSimpleName();
    public static final MediaType TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType TYPE_FORM = MediaType.parse("application/x-www-form-urlencoded");
    public static final MediaType TYPE_STREAM = MediaType.parse("application/octet-stream");
    private static HttpUtils sHttpUtils;
    private OkHttpClient mOkHttpClient;

    private HttpUtils() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS);
        this.mOkHttpClient = builder.build();
    }

    public static HttpUtils getInstance() {
        if (sHttpUtils == null) {
            synchronized (HttpUtils.class) {
                if (sHttpUtils == null) {
                    sHttpUtils = new HttpUtils();
                }
            }
        }
        return sHttpUtils;
    }

    public static boolean isResponseCorrect(Response response) {
        int code;
        return response != null && (code = response.code()) >= 200 && code < 400;
    }

    public void cancel(String tag) {
        if (tag == null) {
            tag = DEFAULT_HTTP_TAG;
        }
        synchronized (HttpUtils.class) {
            for (Call call : this.mOkHttpClient.dispatcher().queuedCalls()) {
                if (tag.equals(call.request().tag())) {
                    call.cancel();
                }
            }
            for (Call call2 : this.mOkHttpClient.dispatcher().runningCalls()) {
                if (tag.equals(call2.request().tag())) {
                    call2.cancel();
                }
            }
        }
    }

    public OkHttpClient getOkHttpClient() {
        return this.mOkHttpClient;
    }

    public Response getSync(String url) {
        return getSync(DEFAULT_HTTP_TAG, url);
    }

    public Response getSync(String tag, String url) {
        try {
            return this.mOkHttpClient.newCall(new Request.Builder().url(url).get().tag(tag).build()).execute();
        } catch (IOException e) {
            LogMgr.e(TAG, "http get error : " + e.getMessage());
            return null;
        }
    }

    public Response postSync(String url, String jsonParams) {
        return postSync(DEFAULT_HTTP_TAG, url, jsonParams);
    }

    public Response postSync(String tag, String url, String jsonParams) {
        return postSync(new Request.Builder().url(url).post(RequestBody.create(TYPE_JSON, jsonParams)).tag(tag).build());
    }

    public Response postSync(String url, Map<String,String> jsonParams) {
        FormBody.Builder formBody = new FormBody.Builder();
        for(String k:jsonParams.keySet()){
            formBody.add(k,jsonParams.get(k));
        }
        return postSync(new Request.Builder().url(url).post(formBody.build()).build());
    }

    public Response postSync(Request request) {
        try {
            return this.mOkHttpClient.newCall(request).execute();
        } catch (IOException e) {
            LogMgr.e(TAG, "http post error : " + e.getMessage());
            return null;
        }
    }
}

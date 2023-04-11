package com.unisound.ant.device.mqtt;

import okhttp3.*;

import java.util.Map;

public class HttpRequestUtil {
    public static String sendGet(String url)  throws Exception{
        return sendGet(url, 30000, null);
    }

    public static String sendGet(String url, int timeout)  throws Exception{
        return sendGet(url, timeout, null);
    }

    public static String sendGet(String url, Map<String, String> httpHeaders) throws Exception{
        return sendGet(url, 30000, httpHeaders);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0081 A[SYNTHETIC, Splitter:B:25:0x0081] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0086 A[Catch:{ Exception -> 0x00c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00cb A[SYNTHETIC, Splitter:B:51:0x00cb] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d0 A[Catch:{ Exception -> 0x00d5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String sendGet(java.lang.String url, int timeout, Map<String, String> httpHeaders) throws java.lang.Exception{
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/json; charset=utf-8");

        final Request request = new Request.Builder().url(url).headers(Headers.of(httpHeaders)).get().build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    public static String sendPost(String url, String param) throws Exception {
        return sendPost(url, param, 30000, null);
    }

    public static String sendPost(String url, String param, int timeout) throws Exception {
        return sendPost(url, param, timeout, null);
    }

    public static String sendPost(String url, String param, Map<String, String> httpHeaders) throws Exception {
        return sendPost(url, param, 30000, httpHeaders);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f A[SYNTHETIC, Splitter:B:27:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a4 A[Catch:{ IOException -> 0x0100 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a9 A[Catch:{ IOException -> 0x0100 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String sendPost(String url,String param, int timeout, Map<String, String> httpHeaders) throws java.lang.Exception {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder2 = new FormBody.Builder();
        for(String p:param.split("&")){
            String[] p2 = p.split("=");
            if(p2.length==2){
                builder2.addEncoded(p2[0],p2[1]);
            }else{
                builder2.addEncoded(p2[0],"");
            }
        }

        Request.Builder builder = new Request.Builder().url(url).post(builder2.build());
        if(httpHeaders!=null){
            builder.headers(Headers.of(httpHeaders));
        }
        Response response = okHttpClient.newCall(builder.build()).execute();
        return response.body().string();
    }
}

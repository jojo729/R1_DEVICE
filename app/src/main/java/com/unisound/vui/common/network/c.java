package com.unisound.vui.common.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpStack;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class c implements HttpStack {

    /* renamed from: a  reason: collision with root package name */
    private final OkHttpClient f370a;

    public c(OkHttpClient okHttpClient) {
        this.f370a = okHttpClient;
    }

    private static RequestBody a(Request request) throws AuthFailureError {
        byte[] body = request.getBody();
        if (body == null) {
            if (request.getMethod() != 1) {
                return null;
            }
            body = "".getBytes();
        }
        return RequestBody.create(MediaType.parse(request.getBodyContentType()), body);
    }

    private static HttpEntity a(Response response) {
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        ResponseBody body = response.body();
        basicHttpEntity.setContent(body.byteStream());
        basicHttpEntity.setContentLength(body.contentLength());
        basicHttpEntity.setContentEncoding(response.header("Content-Encoding"));
        if (body.contentType() != null) {
            basicHttpEntity.setContentType(body.contentType().type());
        }
        return basicHttpEntity;
    }

    private static ProtocolVersion a(Protocol protocol) {
        switch (protocol) {
            case HTTP_1_0:
                return new ProtocolVersion("HTTP", 1, 0);
            case HTTP_1_1:
                return new ProtocolVersion("HTTP", 1, 1);
            case SPDY_3:
                return new ProtocolVersion("SPDY", 3, 1);
            case HTTP_2:
                return new ProtocolVersion("HTTP", 2, 0);
            default:
                throw new IllegalAccessError("Unknown protocol");
        }
    }

    private static void a(okhttp3.Request.Builder builder, Request<?> request) throws AuthFailureError {
        switch (request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    builder.post(RequestBody.create(MediaType.parse(request.getPostBodyContentType()), postBody));
                    return;
                }
                return;
            case 0:
                builder.get();
                return;
            case 1:
                builder.post(a(request));
                return;
            case 2:
                builder.put(a(request));
                return;
            case 3:
                builder.delete();
                return;
            case 4:
                builder.head();
                return;
            case 5:
                builder.method("OPTIONS", null);
                return;
            case 6:
                builder.method("TRACE", null);
                return;
            case 7:
                builder.patch(a(request));
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    @Override // com.android.volley.toolbox.HttpStack
    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws AuthFailureError, IOException {
        int timeoutMs = request.getTimeoutMs();
        OkHttpClient.Builder newBuilder = this.f370a.newBuilder();
        newBuilder.connectTimeout((long) timeoutMs, TimeUnit.MILLISECONDS).readTimeout((long) timeoutMs, TimeUnit.MILLISECONDS).writeTimeout((long) timeoutMs, TimeUnit.MILLISECONDS);
        OkHttpClient build = newBuilder.build();
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        builder.url(request.getUrl());
        Map<String, String> headers = request.getHeaders();
        for (String str : headers.keySet()) {
            builder.addHeader(str, headers.get(str));
        }
        for (String str2 : additionalHeaders.keySet()) {
            builder.header(str2, additionalHeaders.get(str2));
            builder.header(str2, additionalHeaders.get(str2));
        }
        a(builder, request);
        Response execute = build.newCall(builder.build()).execute();
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(a(execute.protocol()), execute.code(), execute.message()));
        basicHttpResponse.setEntity(a(execute));
        Headers headers2 = execute.headers();
        int size = headers2.size();
        for (int i = 0; i < size; i++) {
            String name = headers2.name(i);
            String value = headers2.value(i);
            if (name != null) {
                basicHttpResponse.addHeader(new BasicHeader(name, value));
            }
        }
        return basicHttpResponse;
    }
}

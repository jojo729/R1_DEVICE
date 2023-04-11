package com.unisound.vui.common.network;

import android.content.Context;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.unisound.vui.util.ContextUtils;
import com.unisound.vui.util.LogMgr;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class e {

    /* renamed from: a  reason: collision with root package name */
    private static e f372a = new e(ContextUtils.getContext());
    private RequestQueue b;
    private OkHttpClient c;

    private e() {
    }

    private e(Context context) {
        if (this.b == null) {
            this.c = new OkHttpClient.Builder().followRedirects(true).addInterceptor(new Interceptor() {
                /* class com.unisound.vui.common.network.e.AnonymousClass1 */

                @Override // okhttp3.Interceptor
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    return chain.proceed(chain.request().newBuilder().addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0").addHeader("Accept", "*/*").addHeader("Connection", "keep-alive").build());
                }
            }).build();
            this.b = Volley.newRequestQueue(context, new c(this.c));
        }
    }

    public static e a() {
        return f372a;
    }

    public <T> void a(Request<T> request, Object obj) {
        request.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0f));
        if (obj != null) {
            request.setTag(obj);
        }
        this.b.add(request);
    }

    public void a(Object obj) {
        this.b.cancelAll(obj);
    }

    public void a(Object obj, int i, String str, final byte[] bArr, Map<String, String> map, final d<String> dVar) {
        a(new StringRequest(i, str, new Response.Listener<String>() {
            /* class com.unisound.vui.common.network.e.AnonymousClass2 */

            /* renamed from: a */
            public void onResponse(String str) {
                LogMgr.d("VolleyUtils", "queryString response:" + str);
                if (dVar != null) {
                    dVar.onResponse(str);
                }
            }
        }, new Response.ErrorListener() {
            /* class com.unisound.vui.common.network.e.AnonymousClass3 */

            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError error) {
                LogMgr.d("VolleyUtils", "queryString onErrorResponse:" + error.toString());
                if (dVar != null) {
                    dVar.onError(error + "");
                }
            }
        }) {
            /* class com.unisound.vui.common.network.e.AnonymousClass4 */

            @Override // com.android.volley.Request
            public byte[] getBody() {
                return bArr;
            }

            @Override // com.android.volley.Request
            public Map<String, String> getHeaders() {
                HashMap hashMap = new HashMap();
                hashMap.put("encodeType", "add_base64");
                return hashMap;
            }
        }, obj);
    }

    public void a(Object obj, String str, final d<byte[]> dVar) {
        a(new a(0, str, new Response.Listener<byte[]>() {
            /* class com.unisound.vui.common.network.e.AnonymousClass5 */

            /* renamed from: a */
            public void onResponse(byte[] bArr) {
                LogMgr.e("VolleyUtils", "downloadFile success");
                dVar.onResponse(bArr);
            }
        }, new Response.ErrorListener() {
            /* class com.unisound.vui.common.network.e.AnonymousClass6 */

            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError error) {
                LogMgr.e("VolleyUtils", "downloadFile onFail = " + error.getMessage());
                dVar.onError(error + "");
            }
        }, null), obj);
    }
}

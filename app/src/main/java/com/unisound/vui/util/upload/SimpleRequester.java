package com.unisound.vui.util.upload;

import android.text.TextUtils;
import com.unisound.vui.common.network.d;
import com.unisound.vui.common.network.e;
import com.unisound.vui.util.LogMgr;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SimpleRequester {
    public static final String CODE_SUCCESS = "0";
    public static final String TAG = "SimpleRequester";
    private Map<String, String> head;
    private SimpleRequestListener listener;
    private d responseListener = new d<String>() {
        /* class com.unisound.vui.util.upload.SimpleRequester.AnonymousClass1 */

        @Override // com.unisound.vui.common.network.d
        public void onError(String errorMessage) {
            if (!TextUtils.isEmpty(errorMessage)) {
                SimpleRequester.this.volleyUtils.a(SimpleRequester.this.tag);
                SimpleRequester.this.listener.onError(errorMessage);
            }
        }

        public void onResponse(String response) {
            if (!TextUtils.isEmpty(response)) {
                SimpleRequester.this.volleyUtils.a(SimpleRequester.this.tag);
                SimpleRequester.this.listener.onResponse(ReqDataUtils.decoder(response));
            }
        }
    };
    private String tag;
    private e volleyUtils = e.a();

    public SimpleRequester() {
        initHead();
    }

    private void initHead() {
        this.head = new HashMap();
        this.head.put("encodeType", "add_base64");
    }

    public void request(String tag2, String url, JSONObject body, SimpleRequestListener listener2) {
        this.listener = listener2;
        this.tag = tag2;
        String jSONObject = body.toString();
        LogMgr.d(TAG, "bodyStr : " + jSONObject.toString());
        this.volleyUtils.a(tag2, 1, url, ReqDataUtils.encoder(jSONObject).getBytes(), this.head, this.responseListener);
    }

    public void request(String tag2, String url, byte[] body, SimpleRequestListener listener2) {
        this.listener = listener2;
        this.tag = tag2;
        String str = new String(body);
        LogMgr.d(TAG, "bodyStr : " + str);
        this.volleyUtils.a(tag2, 1, url, ReqDataUtils.encoder(str).getBytes(), this.head, this.responseListener);
    }
}

package com.unisound.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.unisound.b.a.c;
import org.json.JSONObject;

public class Ra1 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    private c a;

    public Ra1(Looper looper) {
        super(looper);
    }

    private String a(int i) {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", i);
            jSONObject.put("registerCode", "");
            switch (i) {
                case 1011:
                    str = "没有网络连接错误";
                    break;
                case 1012:
                    str = "异常错误";
                    break;
                case 1013:
                    str = "返回结果为空错误";
                    break;
                case 1014:
                    str = "无效激活类型";
                    break;
                case 1015:
                    str = "激活状态错误，已经有激活操作正在执行";
                    break;
                default:
                    str = "未知错误";
                    break;
            }
            jSONObject.put("message", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public void a(c cVar) {
        this.a = cVar;
    }

    public void handleMessage(final Message message) {
        if (this.a == null) {
            i.b("ActivatorHandler listener == null");
        }
        else {
            switch (message.what) {
                case 102: {
                    i.c("ActivatorHandler GET_RESULT");
                    this.a.a((String)message.obj);
                    break;
                }
                case 109: {
                    i.a("ActivatorHandler NO_NETWORK_ERROR");
                    this.a.a(this.a(1011));
                    break;
                }
                case 107: {
                    i.a("ActivatorHandler EXCEPTION_ERROR");
                    this.a.a(this.a(1012));
                    break;
                }
                case 106: {
                    i.a("ActivatorHandler RESPONSE_IS_NULL_ERROR");
                    this.a.a(this.a(1013));
                    break;
                }
                case 110: {
                    i.a("ActivatorHandler INVALID_URL_TYPE_ERROR");
                    this.a.a(this.a(1014));
                    break;
                }
                case 108: {
                    i.a("ActivatorHandler ACTIVATOR_STATUS_ERROR_MESSAGE");
                    this.a.a(this.a(1015));
                    break;
                }
                case 120: {
                    i.c("ActivatorHandler NO_READ_PHONE_STATE_PERMISSION");
                    this.a.a((String)message.obj);
                    break;
                }
            }
        }
    }
}

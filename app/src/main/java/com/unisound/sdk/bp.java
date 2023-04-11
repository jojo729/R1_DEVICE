//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.unisound.client.ErrorCode;
import com.unisound.common.v;
import com.unisound.common.y;

class bp extends Handler {

    private bg a;

    public bp(bg var1) {
        this.a = var1;
    }

    public bp(bg var1, Looper var2) {
        super(var2);
        this.a = var1;
    }

    public void handleMessage(Message var1) {
        switch(var1.what) {
            case 1:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(3103, (int)System.currentTimeMillis());
                }
                break;
            case 5:
                y.c(new Object[]{"recognition End"});
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(1107, (int)System.currentTimeMillis());
                }

                this.a.u.clear();
                this.a.v.clear();
                this.a.w.clear();
                v.a();
                bg.c(this.a, false);
                break;
            case 6:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(1118, (int)System.currentTimeMillis());
                }
                break;
            case 7:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(1119, (int)System.currentTimeMillis());
                }
                break;
            case 11:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(1101, (int)System.currentTimeMillis());
                }
                break;
            case 12:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(1102, (int)System.currentTimeMillis());
                    bg.d(this.a, true);
                    if (bg.m(this.a)) {
                        bg.h(this.a).onEvent(1107, (int)System.currentTimeMillis());
                    }
                }
                break;
            case 13:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(1104, (int)System.currentTimeMillis());
                }
                break;
            case 14:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(1131, (int)System.currentTimeMillis());
                }
                break;
            case 15:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onEvent(1117, (int)System.currentTimeMillis());
                }
                break;
            case 16:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onEvent(1108, (int)System.currentTimeMillis());
                }
                break;
            case 17:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(1122, (int)System.currentTimeMillis());
                }
                break;
            case 18:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(1103, (int)System.currentTimeMillis());
                }
                break;
            case 20:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(1105, (int)System.currentTimeMillis());
                }
                break;
            case 21:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onEvent(1131, (int)System.currentTimeMillis());
                }
                break;
            case 22:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onEvent(1123, (int)System.currentTimeMillis());
                }
                break;
            case 23:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onEvent(1124, (int)System.currentTimeMillis());
                }
                break;
            case 24:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onEvent(1125, (int)System.currentTimeMillis());
                }
                break;
            case 30:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onEvent(1140, (int)System.currentTimeMillis());
                }
                break;
            case 40:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onEvent(1141, (int)System.currentTimeMillis());
                }
                break;
            case 50:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onEvent(1160, (int)System.currentTimeMillis());
                }
                break;
            case 53:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onError(3301, ErrorCode.toJsonMessage((Integer)var1.obj));
                }
                break;
            case 54:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onError(1300, ErrorCode.toJsonMessage((Integer)var1.obj));
                }
                break;
            case 1129:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onEvent(1129, (int)System.currentTimeMillis());
                }
                break;
            case 1201:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onResult(1201, (String)var1.obj);
                }
                break;
            case 1202:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onResult(1202, (String)var1.obj);
                }
                break;
            case 1210:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onResult(1210, (String)var1.obj);
                }
                break;
            case 3201:
                if (bg.h(this.a) != null && !bg.l(this.a)) {
                    bg.h(this.a).onResult(3201, (String)var1.obj);
                }
                break;
            default:
                if (bg.h(this.a) != null) {
                    bg.h(this.a).onEvent(var1.what, (int)System.currentTimeMillis());
                }
        }

    }
}

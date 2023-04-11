package com.unisound.sdk;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.unisound.b.a.c;
import com.unisound.client.ErrorCode;
import com.unisound.common.y;
import org.json.JSONObject;

public class bm implements c {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ bg a;

    bm(bg bgVar) {
        this.a = bgVar;
    }

    @Override // com.unisound.b.a.c
    public void a(final String s) {
        while (true) {
            y.b(new Object[] { "SpeechUnderstanderInterface init activate result= " + s });
            String string = null;
            Label_0212: {
                try {
                    final JSONObject jsonObject = new JSONObject(s);
                    string = jsonObject.getString("returnCode");
                    final SharedPreferences.Editor edit = bg.b(this.a).getSharedPreferences("ACTIVITY_FLAG", 0).edit();
                    if (string.equals("dc_0000")) {
                        edit.putString("activityFlag", "activity success");
                        final String string2 = new JSONObject(jsonObject.getString("result")).getString("token");
                        if (!TextUtils.isEmpty((CharSequence)string2)) {
                            bg.a(this.a, this.a.x, com.unisound.c.a.a(this.a.x), string2);
                        }
                        bg.n(this.a);
                        edit.commit();
                        bg.i(this.a).sendEmptyMessage(40);
                    }
                    else {
                        y.a("USC", new String[] { "activate errorCode = ", string });
                        if (!string.equals("dc_0002")) {
                            break Label_0212;
                        }
                        this.a.refreshActivate();
                    }
                    return;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
            }
            if (string.equals("dc_0006")) {
                if(bg.o(this.a)) {
                    bg.p(this.a).a(0);
                    bg.e(this.a, false);
                }
                return;
            }
            if (string.equals("dc_0003")) {
                bg.b(this.a, ErrorCode.ASR_SDK_ACTIVATE_ERROR);
                bg.b(this.a, ErrorCode.ASR_SDK_ACTIVATE_SIGN_ERROR);
            }
            else if (string.equals("dc_0004")) {
                bg.b(this.a,ErrorCode.ASR_SDK_ACTIVATE_ERROR);
                bg.b(this.a,ErrorCode.ASR_SDK_ACTIVATE_NO_PERMISSION);
            }
            else if (string.equals("dc_0005")) {
                bg.b(this.a, ErrorCode.ASR_SDK_ACTIVATE_ERROR);
                bg.b(this.a, ErrorCode.ASR_SDK_ACTIVATE_OVER_MAX_ACT_FREQUENCY);
                return;
            }
            bg.a(this.a, this.a.x, com.unisound.c.a.a(this.a.x), "");
        }
    }
}

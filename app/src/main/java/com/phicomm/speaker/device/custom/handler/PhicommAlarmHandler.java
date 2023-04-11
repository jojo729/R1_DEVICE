package com.phicomm.speaker.device.custom.handler;

import android.content.Context;
import android.util.Log;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.ipc.PhicommLightController;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.session.memo.DefaultAlarmHandler;
import nluparser.scheme.AlarmIntent;
import nluparser.scheme.Intent;

public class PhicommAlarmHandler extends DefaultAlarmHandler {
    private static final String TAG = "PhicommAlarmHandler";
    private PhicommLightController mLightController;

    public PhicommAlarmHandler(Context context) {
        super(context);
        this.mLightController = new PhicommLightController(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.session.memo.DefaultAlarmHandler, com.unisound.vui.handler.session.memo.AbstractMemoHandler
    public String dealWithSetMemo(Intent intent, Context mAndroidContext) {
        AlarmIntent alarmIntent = (AlarmIntent) intent;
        if (alarmIntent.getType() == 1) {
            Log.d(TAG, "dealWithSetMemo countDown:" + alarmIntent.getCountDown());
            if (alarmIntent.getCountDown() < 10000) {
                return mAndroidContext.getString(R.string.tts_countdown_set_too_short);
            }
            if (alarmIntent.getCountDown() > 86400000) {
                return mAndroidContext.getString(R.string.tts_countdown_set_too_long);
            }
        }
        return super.dealWithSetMemo(intent, mAndroidContext);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.session.memo.AbstractMemoHandler, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (!this.eventReceived) {
            return false;
        }
        if (!ctx.hasAttr(NEED_SUPPLEMENT) || !((Boolean) ctx.attr(NEED_SUPPLEMENT).getAndRemove()).booleanValue()) {
            return super.onTTSEventPlayingEnd(ctx);
        }
        reset();
        ctx.enterASR();
        this.mLightController.turnOnWakeupLastLight();
        return false;
    }
}

package com.unisound.ant.platform.smarthome;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.phicomm.speaker.device.R;
import com.unisound.ant.device.DeviceCenterHandler;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.SpeakerTTSUtil;
import nluparser.scheme.NLU;
import nluparser.scheme.SName;

public class DefaultSmartHomeHandler extends SimpleUserEventInboundHandler<NLU> {
    private static final int MESSAGE_TYPE_NLU_UNIIOT = 1;
    private static final String TAG = "DefaultSmartHomeHandler";
    private SmartDeviceMgr smartDeviceMgr;
    private Handler taskHandler = new Handler() {
        /* class com.unisound.ant.platform.smarthome.DefaultSmartHomeHandler.AnonymousClass1 */

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    DeviceCenterHandler.getDeviceCenterMgr().onReceivedMsg(2, (String) msg.obj);
                    return;
                default:
                    return;
            }
        }
    };

    /* access modifiers changed from: protected */
    @Override
    public boolean acceptInboundEvent0(NLU evt) throws Exception {
        if (SName.SMART_HOUSE.equals(evt.getService())) {
            return true;
        }
        return super.acceptInboundEvent0(evt);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        this.smartDeviceMgr = new SmartDeviceMgr(ctx);
        return super.onASREventEngineInitDone(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    /* access modifiers changed from: protected */
    @Override
    public void eventReceived(NLU evt, ANTHandlerContext ctx) throws Exception {
        super.eventReceived(evt, ctx);
        String answer = null;
        if (evt.getGeneral() != null) {
            answer = evt.getGeneral().getText();
        }
        if (evt.getIotUniJson() != null) {
            String iotJson = evt.getIotUniJson().toString();
            LogMgr.d(TAG, "get iotJson for CenterControl:" + iotJson);
            reportServiceInfoToSessionLayer(iotJson);
        }
        if (TextUtils.isEmpty(answer)) {
            answer = SpeakerTTSUtil.getTTSString(R.string.tts_unsupport, -1, ctx.androidContext());
        }
        ctx.playTTS(answer);
        sendFullLogToDeviceCenter(evt, answer);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (this.eventReceived) {
            exitSession(true);
        }
        return super.onTTSEventPlayingEnd(ctx);
    }

    private void exitSession(boolean fireResume) {
        LogMgr.d(TAG, "fireResume:" + fireResume);
        reset();
        this.ctx.enterWakeup(false);
        if (fireResume) {
            fireResume(this.ctx);
        }
    }

    private void reportServiceInfoToSessionLayer(String message) {
        LogMgr.d(TAG, "reportServiceInfoToSessionLayer message:" + message);
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = message;
        this.taskHandler.sendMessage(msg);
    }
}

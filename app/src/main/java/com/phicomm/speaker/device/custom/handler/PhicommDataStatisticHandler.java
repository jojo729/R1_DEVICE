package com.phicomm.speaker.device.custom.handler;

import android.content.Context;
import android.os.MessageDispatchManager;
import android.os.ParcelableUtil;
import com.orhanobut.logger.Logger;
import com.phicomm.speaker.device.R;
import com.phicomm.speaker.device.custom.message.MessageSenderDelegate;
import com.unisound.ant.device.bean.PhicommStatisticInfo;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.transport.out.ChangeWakeupWordEvent;
import com.unisound.vui.transport.out.SimulateWakeupEvent;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.ThreadUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import logreport.FullLog;
import nluparser.MixtureProcessor;
import nluparser.scheme.LocalASR;
import nluparser.scheme.Mixture;
import nluparser.scheme.NLU;
import nluparser.scheme.SName;

public class PhicommDataStatisticHandler extends SimpleUserEventInboundHandler<NLU> {
    private static final String BAIKE = "BAIKE";
    private static final String DATA_SOURCE_UNISOUND = "unisound";
    private static final String KEY_TYPE_DIALOGUE = "dialog";
    private static final String KEY_TYPE_WAKEUP = "wakeup";
    private static final String KEY_TYPE_WAKEUP_WORD = "wakeupWord";
    private static final String SELFDIALOG = "selfdialog";
    private static final String STYLE_SELFDIALOG = "prvtfaq";
    private static final String TAG = "PhicommDataStatisticHandler";
    private final MessageSenderDelegate mMessageSenderDelegate = MessageSenderDelegate.getInstance();
    private MixtureProcessor mMixtureProcessor;

    public PhicommDataStatisticHandler(Context context, MixtureProcessor mixtureProcessor) {
        this.mMixtureProcessor = mixtureProcessor;
        MessageSenderDelegate.init(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onWakeupResult(ANTHandlerContext ctx, final String result) {
        ThreadUtils.execute(new Runnable() {
            /* class com.phicomm.speaker.device.custom.handler.PhicommDataStatisticHandler.AnonymousClass1 */

            public void run() {
                Mixture mixture;
                if (!ANTConfigPreference.isDev() && (mixture = mMixtureProcessor.from(result)) != null && mixture.getLocalASRList() != null) {

                    LogMgr.e(TAG,"PPP not sendMainWakeupStatisticToPhicomm");
//                    sendMainWakeupStatisticToPhicomm(mixture);
                }
            }
        });
        return super.onWakeupResult(ctx, result);
    }

    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void userEventTriggered(Object evt, ANTHandlerContext ctx) throws Exception {
        if (ANTConfigPreference.isDev()) {
            super.userEventTriggered(evt, ctx);
            return;
        }
        if (evt instanceof FullLog) {
            sendDialogueStatisticToPhicomm((FullLog) evt);
        } else if (evt instanceof ChangeWakeupWordEvent) {
            sendChangeWakeupWordStatisticToPhicomm((ChangeWakeupWordEvent) evt);
        } else if (evt instanceof SimulateWakeupEvent) {
            sendSimulateWakeupStatisticToPhicomm((SimulateWakeupEvent) evt);
        }
        super.userEventTriggered(evt, ctx);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendMainWakeupStatisticToPhicomm(Mixture mixture) {
        LogMgr.d(TAG, "sendMainWakeupStatisticToPhicomm: ");
        String wakeupWord = ((LocalASR)mixture.getLocalASRList().get(0)).getRecognitionResult();
        HashMap<String, Object> data = new HashMap<>();
        data.put(KEY_TYPE_WAKEUP_WORD, wakeupWord);
        data.put("wakeupType", 1);
        sendStatisticInfoToPhicomm(getRandomMessageId(), "wakeup", DATA_SOURCE_UNISOUND, data);
    }

    private void sendDialogueStatisticToPhicomm(FullLog evt) {
        LogMgr.d(TAG, "sendDialogueStatisticToPhicomm: ");
        HashMap<String, Object> data = new HashMap<>();
        String ttsData = evt.getTtsData();
        data.put("tts", ttsData.replaceAll("<py>[\\d|\\w]+</py>", ""));
        if (Arrays.asList(this.ctx.androidContext().getResources().getStringArray(R.array.tts_unsupport_answer)).contains(ttsData)) {
            data.put("service", SName.CHAT_ILLEGAL);
        } else if (evt.getNlu().getGeneral() != null && BAIKE.equals(evt.getNlu().getGeneral().getStyle())) {
            data.put("service", BAIKE);
        } else if (evt.getNlu().getGeneral() == null || !STYLE_SELFDIALOG.equals(evt.getNlu().getGeneral().getStyle())) {
            data.put("service", evt.getNlu().getService());
        } else {
            data.put("service", SELFDIALOG);
        }
        data.put("text", evt.getNlu().getText());
        sendStatisticInfoToPhicomm(getRandomMessageId(), KEY_TYPE_DIALOGUE, DATA_SOURCE_UNISOUND, data);
    }

    private void sendChangeWakeupWordStatisticToPhicomm(ChangeWakeupWordEvent evt) {
        LogMgr.d(TAG, "sendChangeWakeupWordStatisticToPhicomm: ");
        HashMap<String, Object> data = new HashMap<>();
        data.put(KEY_TYPE_WAKEUP_WORD, evt.getData());
        sendStatisticInfoToPhicomm(getRandomMessageId(), KEY_TYPE_WAKEUP_WORD, DATA_SOURCE_UNISOUND, data);
    }

    private void sendSimulateWakeupStatisticToPhicomm(SimulateWakeupEvent evt) {
        LogMgr.d(TAG, "sendSimulateWakeupStatisticToPhicomm: ");
        HashMap<String, Object> data = new HashMap<>();
        data.put(KEY_TYPE_WAKEUP_WORD, evt.getData());
        data.put("wakeupType", 2);
        sendStatisticInfoToPhicomm(getRandomMessageId(), "wakeup", DATA_SOURCE_UNISOUND, data);
    }

    private String getRandomMessageId() {
        return UUID.randomUUID().toString();
    }

    private void sendStatisticInfoToPhicomm(String msgId, String key, String from, HashMap<String, Object> data) {
        PhicommStatisticInfo phicommStatisticInfo = new PhicommStatisticInfo(msgId, key, from, data);
        LogMgr.d(TAG, "sendStatisticInfoToPhicomm: " + JsonTool.toJson(phicommStatisticInfo));
        this.mMessageSenderDelegate.send(false, MessageDispatchManager.MSG_TYPE_USER_DATA_ACK, 3, MessageDispatchManager.MSG_TYPE_USER_DATA, 3, -1, ParcelableUtil.obtain(JsonTool.toJson(phicommStatisticInfo)));
    }
}

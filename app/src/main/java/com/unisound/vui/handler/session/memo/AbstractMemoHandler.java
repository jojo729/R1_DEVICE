package com.unisound.vui.handler.session.memo;

import android.content.Context;
import android.text.TextUtils;
import com.phicomm.speaker.device.R;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.handler.session.memo.entity.LocalMemo;
import com.unisound.vui.handler.session.memo.schulding.MemoDataManager;
import com.unisound.vui.handler.session.memo.utils.MemoConstants;
import com.unisound.vui.handler.session.memo.utils.MemoUtils;
import com.unisound.vui.priority.PriorityMap;
import com.unisound.vui.util.AttributeKey;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.LogMgr;
import nluparser.scheme.AlarmIntent;
import nluparser.scheme.General;
import nluparser.scheme.Intent;
import nluparser.scheme.NLU;
import nluparser.scheme.Result;
import nluparser.scheme.SCode;

public abstract class AbstractMemoHandler extends SimpleUserEventInboundHandler<NLU<Intent, Result.NullResult>> {
    protected static final AttributeKey<Boolean> NEED_SUPPLEMENT = AttributeKey.valueOf(DefaultAlarmHandler.class, "NEED_SUPPLEMENT");
    private static final String TAG = "AbstractMemoHandler";
    private Context mContext;
    protected MemoDataManager mMemoInfoMgr;

    /* access modifiers changed from: protected */
    public abstract String dealWithSetMemo(Intent intent, Context context);

    /* access modifiers changed from: protected */
    public abstract void recoveryHandlerPriority();

    public AbstractMemoHandler(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        this.mMemoInfoMgr = MemoDataManager.getInstance();
        return super.onASREventEngineInitDone(ctx);
    }

    /* access modifiers changed from: protected */
    @Override
    public void eventReceived(NLU<Intent, Result.NullResult> evt, ANTHandlerContext ctx) throws Exception {
        super.eventReceived(evt, ctx);
        String code = evt.getCode();
        LogMgr.d(TAG, "-->> eventReceived code:" + code);
        String playAnswer = "";
        char c = 65535;
        switch (code.hashCode()) {
            case -2007525643:
                if (code.equals(SCode.REMINDER_SET)) {
                    c = '\t';
                    break;
                }
                break;
            case -1573228012:
                if (code.equals(SCode.ALARM_REMOVE_ALL)) {
                    c = 0;
                    break;
                }
                break;
            case -1193906763:
                if (code.equals(SCode.ALARM_SET_TIMING)) {
                    c = '\n';
                    break;
                }
                break;
            case -441914032:
                if (code.equals(SCode.ALARM_STOP)) {
                    c = 7;
                    break;
                }
                break;
            case 46724168:
                if (code.equals(SCode.ALARM_CANCEL)) {
                    c = 2;
                    break;
                }
                break;
            case 479837778:
                if (code.equals(SCode.ALARM_REMOVE)) {
                    c = 4;
                    break;
                }
                break;
            case 761293543:
                if (code.equals(SCode.REMINDER_CANCEL)) {
                    c = 3;
                    break;
                }
                break;
            case 820478259:
                if (code.equals(SCode.REMINDER_REMOVE_ALL)) {
                    c = 1;
                    break;
                }
                break;
            case 1194407153:
                if (code.equals(SCode.REMINDER_REMOVE)) {
                    c = 5;
                    break;
                }
                break;
            case 1935487934:
                if (code.equals(SCode.ANSWER)) {
                    c = 6;
                    break;
                }
                break;
            case 2063954228:
                if (code.equals(SCode.ALARM_SET)) {
                    c = '\b';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
                playAnswer = this.mContext.getString(R.string.tts_memo_cancel_disallowed);
                break;
            case 2:
            case 3:
                playAnswer = this.mContext.getString(R.string.tts_memo_cancel_disallowed);
                break;
            case 4:
            case 5:
                playAnswer = this.mContext.getString(R.string.tts_memo_cancel_disallowed);
                break;
            case 6:
                playAnswer = dealWithAnswer(ctx, evt.getGeneral());
                break;
            case '\b':
            case '\t':
                if (evt.getSemantic() != null) {
                    playAnswer = dealWithSetMemo(evt.getSemantic().getIntent(), this.mContext);
                    break;
                }
                break;
            case '\n':
                if (evt.getSemantic() != null) {
                    AlarmIntent intent = (AlarmIntent) evt.getSemantic().getIntent();
                    intent.setType(1);
                    playAnswer = dealWithSetMemo(intent, this.mContext);
                    break;
                }
                break;
        }
        if (TextUtils.isEmpty(playAnswer)) {
            playAnswer = this.mContext.getResources().getString(R.string.tts_unsupport);
        }
        ctx.playTTS(playAnswer);
        sendFullLogToDeviceCenter(evt, playAnswer);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (this.eventReceived) {
            reset();
            if (!ctx.hasAttr(NEED_SUPPLEMENT) || !((Boolean) ctx.attr(NEED_SUPPLEMENT).getAndRemove()).booleanValue()) {
                recoveryHandlerPriority();
                ctx.enterWakeup(false);
                ctx.fireUserEventTriggered(ExoConstants.DO_RESUME);
            } else {
                ctx.enterASR();
            }
        }
        return super.onTTSEventPlayingEnd(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx, String interruptType) {
        if (this.eventReceived) {
            ctx.cancelTTS();
            reset();
            recoveryHandlerPriority();
        }
    }

    /* access modifiers changed from: protected */
    public String dealWithAnswer(ANTHandlerContext ctx, General answerGeneral) {
        if (answerGeneral == null) {
            return null;
        }
        ctx.attr(NEED_SUPPLEMENT).set(true);
        setPriority(PriorityMap.PRIORITY_MAX);
        return answerGeneral.getText();
    }

    /* access modifiers changed from: package-private */
    public boolean checkMemoCount(String memoType) {
        char c = 65535;
        switch (memoType.hashCode()) {
            case -518602638:
                if (memoType.equals("reminder")) {
                    c = 2;
                    break;
                }
                break;
            case 92895825:
                if (memoType.equals("alarm")) {
                    c = 0;
                    break;
                }
                break;
            case 1351273041:
                if (memoType.equals(MemoConstants.MEMO_TYPE_COUNT_DOWN)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                int alarmCount = this.mMemoInfoMgr.getAlarmsCount();
                LogMgr.d(TAG, "checkAlarmCount, alarmCount:" + alarmCount);
                if (alarmCount > ANTConfigPreference.sMaxCountAlarm) {
                    return false;
                }
                break;
            case 1:
                int countDownCount = this.mMemoInfoMgr.getCountDownCount();
                LogMgr.d(TAG, "checkAlarmCount, countDownCount:" + countDownCount);
                if (countDownCount > ANTConfigPreference.sMaxCountCountDown) {
                    return false;
                }
                break;
            case 2:
                int reminderCount = this.mMemoInfoMgr.getReminderCount();
                if (reminderCount > ANTConfigPreference.sMaxCountReminder) {
                    LogMgr.d(TAG, "checkReminderCount:" + reminderCount + ", max:" + ANTConfigPreference.sMaxCountAlarm);
                    return false;
                }
                break;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public String getMemoTimeNlu(Context mAndroidContext, LocalMemo memo) {
        return MemoUtils.getSetMemoNluTime(memo, mAndroidContext.getResources().getStringArray(R.array.memo_time_day_nlu), mAndroidContext.getResources().getStringArray(R.array.memo_time_duration_nlu));
    }
}

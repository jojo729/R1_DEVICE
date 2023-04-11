package com.unisound.vui.handler.session.memo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.phicomm.speaker.device.R;
import com.unisound.vui.handler.session.memo.entity.LocalMemo;
import com.unisound.vui.handler.session.memo.utils.MemoConstants;
import com.unisound.vui.handler.session.memo.utils.MemoUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import nluparser.scheme.AlarmIntent;
import nluparser.scheme.Intent;
import nluparser.scheme.NLU;
import nluparser.scheme.Result;
import nluparser.scheme.SName;

public class DefaultAlarmHandler extends AbstractMemoHandler {
    private static final String TAG = "memolog-AlarmHandler";

//    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
//    /* access modifiers changed from: protected */
//    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
//    public /* bridge */ /* synthetic */ boolean acceptInboundEvent0(NLU<Intent, Result.NullResult> nlu) throws Exception {
//        return acceptInboundEvent0(nlu);
//    }

    public DefaultAlarmHandler(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    /* access modifiers changed from: protected */
    @Override
    public boolean acceptInboundEvent0(NLU evt) throws Exception {
        return SName.ALARM.equals(evt.getService());
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.session.memo.AbstractMemoHandler
    @SuppressLint({"StringFormatInvalid", "StringFormatMatches"})
    public String dealWithSetMemo(Intent intent, Context context) {
        LocalMemo memo;
        AlarmIntent alarmIntent = (AlarmIntent) intent;
        if (alarmIntent.getType() == 0) {
            if (!checkMemoCount("alarm")) {
                return this.ctx.androidContext().getString(R.string.tts_alarm_count_max);
            }
        } else if (!checkMemoCount(MemoConstants.MEMO_TYPE_COUNT_DOWN)) {
            return this.ctx.androidContext().getString(R.string.tts_count_down_count_max);
        }
        if (!isValidIntent(alarmIntent)) {
            return "";
        }
        if (alarmIntent.getType() == 1) {
            memo = MemoUtils.generateCountdownMemo(alarmIntent);
        } else {
            memo = MemoUtils.generateAlarmMemo(alarmIntent);
            if (memo == null) {
                String dateStr = new SimpleDateFormat("yyyy年MM月dd日HH点mm分").format(new Date());
                return context.getString(R.string.tts_memo_set_result_outofdate_exception, dateStr);
            }
        }
        this.mMemoInfoMgr.addMemo(memo);
        if (!memo.isAlarm()) {
            return context.getString(R.string.tts_count_down_set_result_ok);
        }
        return context.getString(R.string.tts_alarm_set_result_ok, getMemoTimeNlu(context, memo));
    }

    private boolean isValidIntent(AlarmIntent intent) {
        boolean z = true;
        if (intent == null) {
            return false;
        }
        if (intent.getType() != 0) {
            if (intent.getCountDown() < 10000) {
                z = false;
            }
            return z;
        } else if ("MONTH".equals(intent.getRepeatDate()) || "YEAR".equals(intent.getRepeatDate())) {
            return false;
        } else {
            return !TextUtils.isEmpty(intent.getDate()) || !TextUtils.isEmpty(intent.getTime());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.session.memo.AbstractMemoHandler
    public void recoveryHandlerPriority() {
        setPriority(300);
    }
}

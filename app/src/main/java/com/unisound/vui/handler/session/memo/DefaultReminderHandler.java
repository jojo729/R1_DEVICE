package com.unisound.vui.handler.session.memo;

import android.content.Context;
import android.text.TextUtils;
import com.phicomm.speaker.device.R;
import com.unisound.vui.handler.session.memo.entity.LocalMemo;
import com.unisound.vui.handler.session.memo.utils.MemoUtils;
import com.unisound.vui.util.LogMgr;
import java.text.SimpleDateFormat;
import java.util.Date;
import nluparser.scheme.Intent;
import nluparser.scheme.NLU;
import nluparser.scheme.ReminderIntent;
import nluparser.scheme.Result;
import nluparser.scheme.SName;

public class DefaultReminderHandler extends AbstractMemoHandler {
    private static final String TAG = "memolog-ReminderHandler";

//    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
//    /* access modifiers changed from: protected */
//    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
//    public /* bridge */ /* synthetic */ boolean acceptInboundEvent0(NLU<Intent, Result.NullResult> nlu) throws Exception {
//        return acceptInboundEvent0((NLU) nlu);
//    }

    public DefaultReminderHandler(Context context) {
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
        return SName.REMINDER.equals(evt.getService());
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.session.memo.AbstractMemoHandler
    public String dealWithSetMemo(Intent intent, Context context) {
        if (!checkMemoCount("reminder")) {
            return context.getString(R.string.tts_reminder_count_max);
        }
        ReminderIntent reminderIntent = (ReminderIntent) intent;
        if (!isValidReminder(reminderIntent)) {
            LogMgr.d(TAG, "dealWithSetMemo, reminder intent is invalid");
            return "";
        } else if (TextUtils.isEmpty(reminderIntent.getContent())) {
            LogMgr.d(TAG, "dealWithSetMemo, reminder content is empty");
            return context.getString(R.string.tts_memo_set_result_withoutcontent_exception);
        } else {
            LocalMemo memo = MemoUtils.generateReminderMemo(reminderIntent);
            if (memo == null) {
                String dateStr = new SimpleDateFormat("yyyy年MM月dd日HH点mm分").format(new Date());
                return context.getString(R.string.tts_memo_set_result_outofdate_exception, dateStr);
            }
            this.mMemoInfoMgr.addMemo(memo);
            return context.getString(R.string.tts_reminder_set_result_ok, getMemoTimeNlu(context, memo), memo.getMemoContent());
        }
    }

    private boolean isValidReminder(ReminderIntent intent) {
        if (intent != null && !TextUtils.isEmpty(intent.getDateTime())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.session.memo.AbstractMemoHandler
    public void recoveryHandlerPriority() {
        setPriority(300);
    }
}

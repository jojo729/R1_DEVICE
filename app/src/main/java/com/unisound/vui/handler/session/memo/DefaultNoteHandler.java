package com.unisound.vui.handler.session.memo;

import android.text.TextUtils;
import com.phicomm.speaker.device.R;
import com.unisound.ant.device.bean.CommandOperate;
import com.unisound.ant.device.bean.NoteInfo;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.handler.session.memo.syncloud.NoteSateMgr;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.SystemUitls;
import nluparser.scheme.Intent;
import nluparser.scheme.NLU;
import nluparser.scheme.NoteIntent;
import nluparser.scheme.Result;
import nluparser.scheme.SCode;
import nluparser.scheme.SName;

public class DefaultNoteHandler extends SimpleUserEventInboundHandler<NLU<NoteIntent, Result.NullResult>> implements NoteSateMgr.NoteStateListener {
    private static final String TAG = "DefaultNoteHandler";
    private boolean isContinueRecon = false;
    private NoteSateMgr noteSateMgr = new NoteSateMgr();

//    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
//    /* access modifiers changed from: protected */
//    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
//    public /* bridge */ /* synthetic */ boolean acceptInboundEvent0(NLU<NoteIntent, Result.NullResult> nlu) throws Exception {
//        return acceptInboundEvent0((NLU) nlu);
//    }

    public DefaultNoteHandler() {
        this.noteSateMgr.setControlStateListener(this);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    /* access modifiers changed from: protected */
    @Override
    public boolean acceptInboundEvent0(NLU evt) throws Exception {
        return SName.NOTE.equals(evt.getService());
    }

    /* access modifiers changed from: protected */
    @Override
    public void eventReceived(NLU<NoteIntent, Result.NullResult> evt, ANTHandlerContext ctx) throws Exception {
        super.eventReceived( evt, ctx);
        Intent intent = evt.getSemantic().getIntent();
        LogMgr.d(TAG, "eventReceived noteIntent:" + intent);
        if (intent == null || !(intent instanceof NoteIntent)) {
            String answer = evt.getGeneral().getText();
            if (!TextUtils.isEmpty(answer)) {
                ctx.playTTS(answer);
                sendFullLogToDeviceCenter(evt, answer);
                return;
            }
            exit();
            return;
        }
        NoteIntent noteIntent = (NoteIntent) intent;
        String noteContent = noteIntent.getContent();
        if (SCode.ANSWER.equals(evt.getCode()) || TextUtils.isEmpty(noteContent)) {
            this.isContinueRecon = true;
            String answer2 = evt.getGeneral().getText();
            if (TextUtils.isEmpty(answer2)) {
                answer2 = ctx.androidContext().getString(R.string.tts_add_note_answer_tip);
            }
            ctx.playTTS(answer2);
            sendFullLogToDeviceCenter(evt, answer2);
            return;
        }
        String content = String.format(ctx.androidContext().getString(R.string.tts_add_note_tip), noteContent);
        ctx.playTTS(content);
        addNoteSync(noteIntent);
        sendFullLogToDeviceCenter(evt, content);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx, String interruptType) {
        if (this.eventReceived) {
            ctx.cancelTTS();
            reset();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        LogMgr.d(TAG, "onTTSEventPlayingEnd eventReceived:" + this.eventReceived + ",isContinueRecon:" + this.isContinueRecon);
        if (this.eventReceived) {
            if (this.isContinueRecon) {
                ctx.enterASR();
                return true;
            }
            exit();
        }
        return super.onTTSEventPlayingEnd(ctx);
    }

    private void exit() {
        reset();
        fireResume(this.ctx);
    }

    private void addNoteSync(NoteIntent noteIntent) {
        NoteInfo info = new NoteInfo();
        info.setMsg(noteIntent.getContent());
        info.setCreateTime(SystemUitls.getTime());
        this.noteSateMgr.reportNoteStatus(CommandOperate.COMMAND_OPERATE_NOTE_ADD, info);
    }

    @Override // com.unisound.vui.handler.session.memo.syncloud.NoteSateMgr.NoteStateListener
    public void remoteAddNote(NoteInfo localMemo) {
    }

    @Override // com.unisound.vui.handler.session.memo.syncloud.NoteSateMgr.NoteStateListener
    public void remoteDeleteNote(NoteInfo localMemo) {
    }

    @Override // com.unisound.vui.handler.session.memo.syncloud.NoteSateMgr.NoteStateListener
    public void remoteUpdateNote(NoteInfo localMemo) {
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void reset() {
        super.reset();
        this.isContinueRecon = false;
    }
}

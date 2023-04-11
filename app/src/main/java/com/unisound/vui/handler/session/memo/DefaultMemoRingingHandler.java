package com.unisound.vui.handler.session.memo;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.phicomm.speaker.device.R;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.handler.session.memo.entity.LocalMemo;
import com.unisound.vui.handler.session.memo.ring.MemoRingingService;
import com.unisound.vui.handler.session.memo.schulding.MemoDataManager;
import com.unisound.vui.handler.session.memo.schulding.MemoScheduler;
import com.unisound.vui.handler.session.memo.utils.MemoConstants;
import com.unisound.vui.util.AttributeKey;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.TimeUtils;
import com.unisound.vui.util.UserPerferenceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nluparser.scheme.Intent;
import nluparser.scheme.LocalASR;
import nluparser.scheme.NLU;
import nluparser.scheme.Result;
import nluparser.scheme.SName;

public class DefaultMemoRingingHandler extends SimpleUserEventInboundHandler<NLU<Intent, Result.NullResult>> implements Handler.Callback {
    public static final AttributeKey<Boolean> ALARM_PLAYING = AttributeKey.valueOf(DefaultAlarmHandler.class, "ALARM_PLAYING");
    public static final String ALARM_RINGING_AUDITION_SERVICE = "cn.yunzhisheng.ringing.alarm.audition";
    public static final String ALARM_RINGING_SERVICE = "cn.yunzhisheng.ringing.alarm";
    protected static final AttributeKey<Boolean> DEFAULT_WAKEUP_WORD = AttributeKey.valueOf(DefaultAlarmHandler.class, "DEFAULT_WAKEUP_WORD");
    private static final long MEMO_ALARM_RING_AUDITION_EXECUTE_TIME = 10000;
    private static final long MEMO_ALARM_RING_EXECUTE_TIME = 180000;
    private static final int MSG_MEMO_RING_STOP = 4096;
    protected static final AttributeKey<Boolean> STOP_ALARM_WAKEUP_WORD = AttributeKey.valueOf(DefaultAlarmHandler.class, "STOP_ALARM_WAKEUP_WORD");
    private static final String TAG = "memolog-RingHandler";
    private long mCurrentMemoTime;
    private List<LocalMemo> mCurrentMemos;
    private LocalMemo mFirstMemo;
    private Handler mHandler = new Handler(this);
    private boolean mIsAudition;
    private MemoDataManager mMemoInfoMgr;
    private MemoRingingService mMemoRingingService;
    private OnRingingListener mRingingListener;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        /* class com.unisound.vui.handler.session.memo.DefaultMemoRingingHandler.AnonymousClass1 */

        public void onServiceConnected(ComponentName className, IBinder service) {
            LogMgr.d(DefaultMemoRingingHandler.TAG, "onServiceConnected");
            DefaultMemoRingingHandler.this.mMemoRingingService = ((MemoRingingService.LocalBinder) service).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            LogMgr.d(DefaultMemoRingingHandler.TAG, "onServiceDisconnected");
            DefaultMemoRingingHandler.this.mMemoRingingService = null;
        }
    };
    private List<String> mWakeUpWords = new ArrayList();

    public interface OnRingingListener {
        void onRingingStart();

        void onRingingStop();
    }

    public void setOnRingingListener(OnRingingListener ringingListener) {
        this.mRingingListener = ringingListener;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    /* access modifiers changed from: protected */
    @Override
    public boolean acceptInboundEvent0(NLU<Intent, Result.NullResult> evt) throws Exception {
        if (SName.SETTING_MP.equals(evt.getService())) {
            LogMgr.d(TAG, "手机端点歌播放前需要停掉响铃");
            if (this.ctx.engine().hasAttr(ALARM_PLAYING) && ((Boolean) this.ctx.engine().attr(ALARM_PLAYING).get()).booleanValue()) {
                stopAlarmRinging();
            }
        }
        return ALARM_RINGING_SERVICE.equals(evt.getService()) || ALARM_RINGING_AUDITION_SERVICE.equals(evt.getService());
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        this.mMemoInfoMgr = MemoDataManager.getInstance();
        ctx.androidContext().registerReceiver(new MemoRingEventReceiver(), new IntentFilter(MemoConstants.ACTION_MEMO_RING));
        bindRingingService(ctx.androidContext());
        this.mWakeUpWords = UserPerferenceUtil.getWakeupWord(ctx.androidContext());
        return super.onASREventEngineInitDone(ctx);
    }

    private void bindRingingService(Context context) {
        context.bindService(new android.content.Intent(context, MemoRingingService.class), this.mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void userEventTriggered(Object evt, ANTHandlerContext ctx) throws Exception {
        if (evt instanceof LocalASR) {
            LogMgr.d(TAG, "userEventTriggered:" + evt.toString());
            if (this.mWakeUpWords.contains(((LocalASR) evt).getRecognitionResult().trim())) {
                stopAlarmRinging();
                return;
            }
        }
        super.userEventTriggered(evt, ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onWakeupEventSetWakeupwordDone(ANTHandlerContext ctx) {
        if (ctx.hasAttr(STOP_ALARM_WAKEUP_WORD) && ((Boolean) ctx.attr(STOP_ALARM_WAKEUP_WORD).getAndRemove()).booleanValue()) {
            LogMgr.d(TAG, "update wakeup  word for alarm success!");
            ctx.enterWakeup(false);
            return true;
        } else if (!ctx.hasAttr(DEFAULT_WAKEUP_WORD) || !((Boolean) ctx.attr(DEFAULT_WAKEUP_WORD).getAndRemove()).booleanValue()) {
            return super.onWakeupEventSetWakeupwordDone(ctx);
        } else {
            LogMgr.d(TAG, "recovery default wakeup word success!");
            ctx.enterWakeup(false);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override
    public void eventReceived(NLU<Intent, Result.NullResult> evt, ANTHandlerContext ctx) throws Exception {
        super.eventReceived(evt, ctx);
        if (ALARM_RINGING_AUDITION_SERVICE.equals(evt.getService())) {
            LogMgr.d(TAG, "eventReceived, audition");
            this.mIsAudition = true;
            doBeforeRingPlay();
            startAlarmRinging(evt.getText());
            return;
        }
        this.mIsAudition = false;
        if (getMemoInfoByTime(evt.getTime())) {
            doBeforeRingPlay();
            doPlayMemoTTS(ctx.androidContext());
            return;
        }
        reset();
    }

    private boolean getMemoInfoByTime(long time) {
        this.mCurrentMemoTime = time;
        this.mCurrentMemos = this.mMemoInfoMgr.getMemosByTime(time);
        if (this.mCurrentMemos == null || this.mCurrentMemos.isEmpty()) {
            return false;
        }
        this.mFirstMemo = this.mCurrentMemos.get(0);
        LogMgr.d(TAG, "getMemoInfoByTime, memo size:" + this.mCurrentMemos.size() + ", first memo:" + this.mFirstMemo);
        return true;
    }

    private void doBeforeRingPlay() {
        LogMgr.d(TAG, "doBeforeRingPlay");
        this.ctx.engine().attr(ALARM_PLAYING).set(true);
        if (this.mRingingListener != null) {
            this.mRingingListener.onRingingStart();
        }
        if (!this.mIsAudition) {
            setStopMemoWakeupWord(this.ctx, this.mFirstMemo.getMemoType());
        }
    }

    private void doPlayMemoTTS(Context context) {
        String memoTts;
        if (this.mFirstMemo.isReminder()) {
            StringBuilder content = new StringBuilder();
            for (LocalMemo memo : this.mCurrentMemos) {
                if (!memo.isReminder()) {
                    break;
                }
                content.append(memo.getMemoContent()).append(",");
            }
            if (content.length() > 0) {
                content.deleteCharAt(content.length() - 1);
            }
            memoTts = context.getString(R.string.tts_reminder_tts_content, Integer.valueOf(this.mFirstMemo.getMemoHour()), Integer.valueOf(this.mFirstMemo.getMemoMinute()), content);
        } else if (this.mFirstMemo.isAlarm()) {
            memoTts = context.getString(R.string.tts_alarm_start_ringing_prompt, Integer.valueOf(this.mFirstMemo.getMemoHour()), Integer.valueOf(this.mFirstMemo.getMemoMinute()));
        } else {
            memoTts = context.getString(R.string.tts_count_down_time_over);
        }
        LogMgr.d(TAG, "doPlayMemoTTS:" + memoTts);
        this.ctx.playTTS(memoTts);
    }

    private void startAlarmRinging(String ringName) {
        LogMgr.d(TAG, "startAlarmRinging");
        if (this.mMemoRingingService != null) {
            this.mMemoRingingService.startPlayingRing(true, ringName);
        }
        if (this.mIsAudition) {
            this.mHandler.sendEmptyMessageDelayed(4096, MEMO_ALARM_RING_AUDITION_EXECUTE_TIME);
        } else {
            this.mHandler.sendEmptyMessageDelayed(4096, MEMO_ALARM_RING_EXECUTE_TIME);
        }
    }

    private void stopAlarmRinging() {
        LogMgr.d(TAG, "stopAlarmRinging");
        if (this.mHandler.hasMessages(4096)) {
            this.mHandler.removeMessages(4096);
        }
        if (this.mMemoRingingService != null) {
            this.mMemoRingingService.stopPlayRing();
        }
        if (this.mRingingListener != null) {
            this.mRingingListener.onRingingStop();
        }
        if (!this.mIsAudition) {
            recoveryDefaultWakeupWord(this.ctx);
        }
        if (this.ctx.engine().hasAttr(ALARM_PLAYING) && ((Boolean) this.ctx.engine().attr(ALARM_PLAYING).getAndRemove()).booleanValue()) {
            LogMgr.d(TAG, "Memo Ringing completed and fire Resume");
            reset();
            this.ctx.fireUserEventTriggered(ExoConstants.DO_RESUME);
        }
        if (!this.mIsAudition) {
            this.mMemoInfoMgr.updateMemosByExpiredTime(this.ctx.androidContext(), this.mCurrentMemoTime);
            this.mCurrentMemoTime = 0;
            this.mCurrentMemos = null;
            this.mFirstMemo = null;
        }
    }

    public boolean handleMessage(Message msg) {
        if (msg.what != 4096) {
            return false;
        }
        stopAlarmRinging();
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onWakeupResult(ANTHandlerContext ctx, String result) {
        if (!ctx.engine().hasAttr(ALARM_PLAYING) || !((Boolean) ctx.engine().attr(ALARM_PLAYING).get()).booleanValue()) {
            return super.onWakeupResult(ctx, result);
        }
        Log.d(TAG, "onWakeupResult, " + result);
        ctx.cancelTTS();
        stopAlarmRinging();
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (this.eventReceived) {
            LogMgr.d(TAG, "onTTSEventPlayingEnd");
            if (!ctx.engine().hasAttr(ALARM_PLAYING) || !((Boolean) ctx.engine().attr(ALARM_PLAYING).get()).booleanValue()) {
                reset();
                ctx.enterWakeup(false);
                ctx.fireUserEventTriggered(ExoConstants.DO_RESUME);
            } else {
                startAlarmRinging(this.mFirstMemo.getRinging());
                ctx.enterWakeup(false);
            }
        }
        return super.onTTSEventPlayingEnd(ctx);
    }

    private void setStopMemoWakeupWord(ANTHandlerContext ctx, String memoType) {
        ctx.attr(STOP_ALARM_WAKEUP_WORD).set(true);
        this.mWakeUpWords.addAll(getStopAlarmWakeUpWords(ctx.androidContext(), memoType));
        Log.d(TAG, "setStopMemoWakeupWord:" + this.mWakeUpWords);
        ctx.stopWakeup();
        ctx.setWakeupWord(this.mWakeUpWords, false);
    }

    private void recoveryDefaultWakeupWord(ANTHandlerContext ctx) {
        Log.d(TAG, "recoveryDefaultWakeupWord");
        ctx.attr(DEFAULT_WAKEUP_WORD).set(true);
        this.mWakeUpWords = UserPerferenceUtil.getWakeupWord(ctx.androidContext());
        ctx.stopWakeup();
        ctx.setWakeupWord(this.mWakeUpWords, false);
    }

    private List<String> getStopAlarmWakeUpWords(Context mAndroidContext, String memoType) {
        String[] wordStrings;
        if ("alarm".equals(memoType)) {
            wordStrings = mAndroidContext.getResources().getStringArray(R.array.voice_stop_alarm_wakeup_word);
        } else if ("reminder".equals(memoType)) {
            wordStrings = mAndroidContext.getResources().getStringArray(R.array.voice_stop_reminder_wakeup_word);
        } else {
            wordStrings = mAndroidContext.getResources().getStringArray(R.array.voice_stop_alarm_wakeup_word);
        }
        List<String> wakeupWords = Arrays.asList(wordStrings);
        Log.d(TAG, "getStopAlarmWakeUpWords: " + wakeupWords);
        return wakeupWords;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onScheduleTimeReceived(long memoTime) {
        LogMgr.d(TAG, "onScheduleTimeReceived, memoTime:" + memoTime + ", currentTime:" + System.currentTimeMillis());
        if (this.ctx.engine().hasAttr(ALARM_PLAYING) && ((Boolean) this.ctx.engine().attr(ALARM_PLAYING).get()).booleanValue()) {
            LogMgr.d(TAG, "onScheduleTimeReceived, prev alarm is playing, stop first");
            this.ctx.cancelTTS();
            stopAlarmRinging();
        }
        NLU nlu = new NLU();
        nlu.setService(ALARM_RINGING_SERVICE);
        nlu.setTime(memoTime);
        this.ctx.pipeline().fireUserEventTriggered(nlu);
    }

    public void onRingAudition(String ringName) {
        LogMgr.d(TAG, "onRingAudition, ringName:" + ringName);
        if (this.ctx.engine().hasAttr(ALARM_PLAYING) && ((Boolean) this.ctx.engine().attr(ALARM_PLAYING).get()).booleanValue()) {
            LogMgr.d(TAG, "onScheduleTimeReceived, prev alarm is playing, stop first");
            this.ctx.cancelTTS();
            stopAlarmRinging();
        }
        NLU nlu = new NLU();
        nlu.setService(ALARM_RINGING_AUDITION_SERVICE);
        nlu.setText(ringName);
        this.ctx.pipeline().fireUserEventTriggered(nlu);
    }

    class MemoRingEventReceiver extends BroadcastReceiver {
        MemoRingEventReceiver() {
        }

        public void onReceive(Context context, android.content.Intent intent) {
            long time = intent.getLongExtra(MemoScheduler.EXTRA_TIME, 0);
            LogMgr.d(DefaultMemoRingingHandler.TAG, "MemoRingEventReceiver onReceive: time:" + time);
            LogMgr.d(DefaultMemoRingingHandler.TAG, "MemoRingEventReceiver onReceive: time:" + TimeUtils.format(time, MemoConstants.DATE_FORMATE_ONE));
            if (!UserPerferenceUtil.getDeviceBindState(context)) {
                LogMgr.d(DefaultMemoRingingHandler.TAG, "device is not bound, ignore all memo ring event");
            } else if (Math.abs(System.currentTimeMillis() - time) > ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS) {
                DefaultMemoRingingHandler.this.mMemoInfoMgr.updateMemosByExpiredTime(context, time);
            } else {
                DefaultMemoRingingHandler.this.onScheduleTimeReceived(time);
            }
        }
    }
}

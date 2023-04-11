package com.unisound.vui.handler.session.music;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.annotation.NonNull;
import com.phicomm.speaker.device.R;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.handler.session.memo.utils.MemoConstants;
import com.unisound.vui.handler.session.music.schedule.MusicScheduleReceiver;
import com.unisound.vui.util.AppGlobalConstant;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.LogMgr;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import nluparser.scheme.NLU;
import nluparser.scheme.Operator;
import nluparser.scheme.Result;
import nluparser.scheme.SettingExtIntent;
import nluparser.scheme.SettingIntent;

public abstract class AbstractMusicScheduleHandler extends SimpleUserEventInboundHandler<NLU<SettingExtIntent, Result.NullResult>> implements MusicScheduleReceiver.ScheduleTriggeredListener {
    protected static final String ACTION_MUSIC_SCHEDULE = "com.phicomm.speaker.music.schedule";
    protected static final String KEY_STOP_TIME_IN_MILLS = "stop_time_in_mills";
    protected static final int MAX_TIME_OFFSET = 86400000;
    protected static final int MIN_TIME_OFFSET = 10000;
    protected static final String SERVICE_NAME = "cn.yunzhisheng.music.schedule";
    protected static final String SP_NAME_MUSIC_SCHEDULE = "music_schedule";
    protected static final String TAG = "AbstractMusicScheduleHandler";
    protected Context mContext = AppGlobalConstant.getContext();
    protected SharedPreferences mSharedPrefs = this.mContext.getSharedPreferences(SP_NAME_MUSIC_SCHEDULE, 0);

    /* access modifiers changed from: protected */
    public abstract boolean isInMusicStatus();

    public AbstractMusicScheduleHandler() {
        this.mContext.registerReceiver(new MusicScheduleReceiver(this), new IntentFilter(ACTION_MUSIC_SCHEDULE));
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        long stopTime = this.mSharedPrefs.getLong(KEY_STOP_TIME_IN_MILLS, 0);
        if (stopTime > System.currentTimeMillis() + 1000) {
            createSchedule(stopTime);
        } else if (stopTime > 0) {
            clearStopTime();
        }
        return super.onASREventEngineInitDone(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    /* access modifiers changed from: protected */
    @Override
    public boolean acceptInboundEvent0(NLU<SettingExtIntent, Result.NullResult> evt) throws Exception {
        return SERVICE_NAME.equals(evt.getService());
    }

    /* access modifiers changed from: protected */
    @Override
    public void eventReceived(NLU<SettingExtIntent, Result.NullResult> evt, ANTHandlerContext ctx) throws Exception {
        super.eventReceived(evt, ctx);
        String prompt = this.mContext.getString(R.string.tts_unsupport);
        List<SettingIntent> settingIntents = evt.getSemantic().getIntent().getOperations();
        if (settingIntents.iterator().hasNext()) {
            SettingIntent settingIntent = settingIntents.iterator().next();
            LogMgr.d(TAG, "eventReceived, " + getStringOfSettingIntent(settingIntent));
            if (Operator.ACT_STOP.equals(settingIntent.getOperator())) {
                prompt = dealWithStopAction(settingIntent);
            }
        }
        ctx.playTTS(prompt);
    }

    @NonNull
    private String dealWithStopAction(SettingIntent settingIntent) throws ParseException {
        if (!isInMusicStatus()) {
            return this.mContext.getString(R.string.tts_music_schedule_not_support_in_current_scene);
        }
        long offsetTimeInt = parseOffsetTime(settingIntent.getOffsetTime());
        if (offsetTimeInt < 10000) {
            return this.mContext.getString(R.string.tts_music_schedule_time_too_short);
        }
        if (offsetTimeInt > 86400000) {
            return this.mContext.getString(R.string.tts_music_schedule_time_too_long);
        }
        Calendar calendar = parseTime(settingIntent.getTime());
        if (calendar.getTimeInMillis() > System.currentTimeMillis() + 1000) {
            if (offsetTimeInt < 30000) {
                calendar.add(13, 3);
            }
            saveStopTime(calendar.getTimeInMillis());
            createSchedule(calendar.getTimeInMillis());
            int day = calendar.get(5);
            int hour = calendar.get(11);
            int minute = calendar.get(12);
            return this.mContext.getString(R.string.tts_music_schedule_will_stop, Integer.valueOf(day), Integer.valueOf(hour), Integer.valueOf(minute));
        }
        int year = Calendar.getInstance().get(1);
        int month = Calendar.getInstance().get(2) + 1;
        int date = Calendar.getInstance().get(5);
        int hour2 = Calendar.getInstance().get(11);
        return this.mContext.getString(R.string.tts_music_schedule_time_out_of_date, Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(date), Integer.valueOf(hour2));
    }

    private void createSchedule(long stopTimeInMillis) {
        LogMgr.d(TAG, "createSchedule, " + stopTimeInMillis);
        AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService("alarm");
        Intent intent = new Intent();
        intent.setAction(ACTION_MUSIC_SCHEDULE);
        intent.putExtra("stop_time", stopTimeInMillis);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.mContext, 4096, intent, 134217728);
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, stopTimeInMillis, pendingIntent);
        } else {
            alarmManager.set(0, stopTimeInMillis, pendingIntent);
        }
    }

    private void saveStopTime(long stopTimeInMillis) {
        LogMgr.d(TAG, "saveStopTime: " + stopTimeInMillis);
        this.mSharedPrefs.edit().putLong(KEY_STOP_TIME_IN_MILLS, stopTimeInMillis).apply();
    }

    private void clearStopTime() {
        LogMgr.d(TAG, "clearStopTime");
        this.mSharedPrefs.edit().remove(KEY_STOP_TIME_IN_MILLS).apply();
    }

    private long parseOffsetTime(String offsetTime) throws ParseException {
        Date date = new SimpleDateFormat(MemoConstants.DATE_FORMATE_HMS, Locale.getDefault()).parse(offsetTime);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        return calendar.getTimeInMillis() + ((long) calendar.get(15));
    }

    private Calendar parseTime(String time) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        return calendar;
    }

    private String getStringOfSettingIntent(SettingIntent intent) {
        return "deviceType:" + intent.getDeviceType() + ", operator:" + intent.getOperator() + ", anchorTime:" + intent.getAnchorTime() + ", time:" + intent.getTime() + ", offset:" + intent.getOffsetTime();
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (this.eventReceived) {
            LogMgr.d(TAG, "onTTSEventPlayingEnd");
            reset();
            ctx.pipeline().fireUserEventTriggered(ExoConstants.DO_RESUME);
        }
        return super.onTTSEventPlayingEnd(ctx);
    }

    @Override // com.unisound.vui.handler.session.music.schedule.MusicScheduleReceiver.ScheduleTriggeredListener
    public void onScheduleTriggered() {
        if (isInMusicStatus()) {
            LogMgr.d(TAG, "onScheduleTriggered stop music");
            this.ctx.pipeline().fireUserEventTriggered(ExoConstants.DO_FINISH_ALL_INTERRUPT);
        }
        clearStopTime();
    }
}

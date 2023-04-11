package com.unisound.vui.handler.session.memo.schulding;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.unisound.vui.handler.session.memo.utils.MemoConstants;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.TimeUtils;
import java.util.Set;

public class MemoScheduler {
    public static final String EXTRA_TIME = "extra_time";
    private static final String TAG = "memolog-MemoScheduler";

    public void createSchedules(Context context, Set<Long> times) {
        LogMgr.d(TAG, "createSchedules, size:" + times.size());
        for (Long l : times) {
            createAlarmSchedule(context, l.longValue());
        }
    }

    private PendingIntent buildPendingIntent(Context context, long time) {
        Intent intent = new Intent(MemoConstants.ACTION_MEMO_RING);
        intent.putExtra(EXTRA_TIME, time);
        return PendingIntent.getBroadcast(context, (int) (time / 1000), intent, 134217728);
    }

    public void createAlarmSchedule(Context context, long time) {
        LogMgr.d(TAG, "createAlarmSchedule, time:" + TimeUtils.format(time, MemoConstants.DATE_FORMATE_ONE));
        PendingIntent pendingIntent = buildPendingIntent(context, time);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, time, pendingIntent);
        } else {
            alarmManager.set(0, time, pendingIntent);
        }
    }

    public void cancelAlarmSchedule(Context context, long time) {
        LogMgr.d(TAG, "cancelAlarmSchedule, time:" + TimeUtils.format(time, MemoConstants.DATE_FORMATE_ONE));
        ((AlarmManager) context.getSystemService("alarm")).cancel(buildPendingIntent(context, time));
    }
}

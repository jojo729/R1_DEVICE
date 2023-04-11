package com.unisound.vui.handler.session.memo.entity;

import android.content.ContentUris;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import cn.yunzhisheng.common.PinyinConverter;
import com.unisound.vui.handler.session.memo.utils.MemoConstants;
import com.unisound.vui.handler.session.memo.utils.RingingUtils;
import com.unisound.vui.util.TimeUtils;
import java.util.Calendar;
import java.util.UUID;
import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

public class LocalMemo extends DataSupport implements Comparable<LocalMemo> {
    private int countDown;
    private int executeCount;
    private long id;
    private boolean isEnabled;
    private boolean isLocalCreateUpDdate;
    private boolean isRepeat;
    private boolean isSnoozed;
    private String memoContent;
    private String memoCreateTime;
    private int memoDay;
    private int memoHour;
    private String memoId;
    private int memoMinute;
    private int memoMonth;
    private int memoSecond;
    private String memoTone;
    private String memoType;
    private int memoYear;
    private String repeatType;
    @Column(defaultValue = RingingUtils.RINGING_DEFAULT)
    private String ringing;
    private int snoozeHour;
    private int snoozeMinute;
    private int snoozeSeconds;
    private long timeGap;
    private boolean[] weeks;

    public LocalMemo() {
        this(UUID.randomUUID().toString());
    }

    private LocalMemo(String memoId2) {
        this.isLocalCreateUpDdate = true;
        this.memoId = memoId2;
        this.weeks = new boolean[]{false, false, false, false, false, false, false};
        this.memoTone = ContentUris.withAppendedId(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, 7).toString();
        this.isEnabled = true;
        this.memoCreateTime = TimeUtils.getNowDate(MemoConstants.DATE_FORMATE_ONE);
        this.ringing = RingingUtils.RINGING_DEFAULT;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id2) {
        this.id = id2;
    }

    public String getMemoId() {
        return this.memoId;
    }

    public void setMemoId(String memoId2) {
        this.memoId = memoId2;
    }

    public int getMemoYear() {
        return this.memoYear;
    }

    public void setMemoYear(int memoYear2) {
        this.memoYear = memoYear2;
    }

    public int getMemoMonth() {
        return this.memoMonth;
    }

    public void setMemoMonth(int memoMonth2) {
        this.memoMonth = memoMonth2;
    }

    public int getMemoDay() {
        return this.memoDay;
    }

    public void setMemoDay(int memoDay2) {
        this.memoDay = memoDay2;
    }

    public int getMemoHour() {
        return this.memoHour;
    }

    public void setMemoHour(int memoHour2) {
        this.memoHour = memoHour2;
    }

    public int getMemoMinute() {
        return this.memoMinute;
    }

    public void setMemoMinute(int memoMinute2) {
        this.memoMinute = memoMinute2;
    }

    public int getMemoSecond() {
        return this.memoSecond;
    }

    public void setMemoSecond(int memoSecond2) {
        this.memoSecond = memoSecond2;
    }

    public boolean[] getWeeks() {
        return this.weeks;
    }

    public void setWeeks(boolean[] weeks2) {
        this.weeks = weeks2;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public boolean isRepeat() {
        return !"OFF".equals(this.repeatType);
    }

    public void setRepeat(boolean repeat) {
        this.isRepeat = repeat;
    }

    public String getRepeatType() {
        return this.repeatType;
    }

    public void setRepeatType(String repeatType2) {
        this.repeatType = repeatType2;
    }

    public long getTimeGap() {
        return this.timeGap;
    }

    public void setTimeGap(long timeGap2) {
        this.timeGap = timeGap2;
    }

    public int getExecuteCount() {
        return this.executeCount;
    }

    public void setExecuteCount(int executeCount2) {
        this.executeCount = executeCount2;
    }

    public String getMemoType() {
        return this.memoType;
    }

    public void setMemoType(String memoType2) {
        this.memoType = memoType2;
    }

    public String getMemoContent() {
        return this.memoContent;
    }

    public void setMemoContent(String memoContent2) {
        this.memoContent = memoContent2;
    }

    public String getMemoTone() {
        return this.memoTone;
    }

    public void setMemoTone(String memoTone2) {
        this.memoTone = memoTone2;
    }

    public boolean isSnoozed() {
        return this.isSnoozed;
    }

    public void setSnoozed(boolean snoozed) {
        this.isSnoozed = snoozed;
    }

    public int getSnoozeHour() {
        return this.snoozeHour;
    }

    public void setSnoozeHour(int snoozeHour2) {
        this.snoozeHour = snoozeHour2;
    }

    public int getSnoozeMinute() {
        return this.snoozeMinute;
    }

    public void setSnoozeMinute(int snoozeMinute2) {
        this.snoozeMinute = snoozeMinute2;
    }

    public int getSnoozeSeconds() {
        return this.snoozeSeconds;
    }

    public void setSnoozeSeconds(int snoozeSeconds2) {
        this.snoozeSeconds = snoozeSeconds2;
    }

    public String getMemoCreateTime() {
        return this.memoCreateTime;
    }

    public void setMemoCreateTime(String memoCreateTime2) {
        this.memoCreateTime = memoCreateTime2;
    }

    public boolean isLocalCreateUpDdate() {
        return this.isLocalCreateUpDdate;
    }

    public void setLocalCreateUpDdate(boolean localCreateUpDdate) {
        this.isLocalCreateUpDdate = localCreateUpDdate;
    }

    public String getRinging() {
        return this.ringing;
    }

    public void setRinging(String ringing2) {
        this.ringing = ringing2;
    }

    public int getCountDown() {
        return this.countDown;
    }

    public void setCountDown(int countDown2) {
        this.countDown = countDown2;
    }

    public boolean isAlarm() {
        return "alarm".equals(this.memoType);
    }

    public boolean isReminder() {
        return "reminder".equals(this.memoType);
    }

    public boolean isCountdown() {
        return MemoConstants.MEMO_TYPE_COUNT_DOWN.equals(this.memoType);
    }

    public void setTime(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        setMemoYear(calendar.get(1));
        setMemoMonth(calendar.get(2) + 1);
        setMemoDay(calendar.get(5));
        setMemoHour(calendar.get(11));
        setMemoMinute(calendar.get(12));
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.id).append(PinyinConverter.PINYIN_EXCLUDE);
        builder.append(this.memoId.substring(this.memoId.length() - 12)).append(PinyinConverter.PINYIN_EXCLUDE);
        builder.append(this.memoType).append(PinyinConverter.PINYIN_EXCLUDE);
        builder.append(this.memoYear).append(this.memoMonth).append(this.memoDay).append(PinyinConverter.PINYIN_SEPARATOR);
        builder.append(this.memoHour).append(":").append(this.memoMinute).append(":").append(this.memoSecond).append(PinyinConverter.PINYIN_EXCLUDE);
        builder.append(this.isEnabled).append(PinyinConverter.PINYIN_EXCLUDE);
        builder.append("createTime:").append(this.memoCreateTime).append(PinyinConverter.PINYIN_EXCLUDE);
        builder.append(this.memoContent).append(PinyinConverter.PINYIN_EXCLUDE);
        builder.append(this.repeatType);
        return builder.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalMemo)) {
            return false;
        }
        return getMemoId().equals(((LocalMemo) o).getMemoId());
    }

    public int hashCode() {
        return getMemoId().hashCode();
    }

    public long getTimeInMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(this.memoYear, this.memoMonth - 1, this.memoDay, this.memoHour, this.memoMinute, this.memoSecond);
        return calendar.getTimeInMillis();
    }

    public String getTimeStr() {
        return TimeUtils.format(getTimeInMillis(), MemoConstants.DATE_FORMATE_ONE);
    }

    private byte getTypeWeight() {
        if ("reminder".equals(this.memoType)) {
            return 3;
        }
        if (MemoConstants.MEMO_TYPE_COUNT_DOWN.equals(this.memoType)) {
            return 2;
        }
        return 1;
    }

    public int compareTo(@NonNull LocalMemo another) {
        int i = -1;
        if (getTimeInMillis() != another.getTimeInMillis()) {
            return getTimeInMillis() > another.getTimeInMillis() ? 1 : -1;
        }
        if (getTypeWeight() != another.getTypeWeight()) {
            if (getTypeWeight() <= another.getTypeWeight()) {
                i = 1;
            }
            return i;
        }
        return TimeUtils.getDate(this.memoCreateTime, MemoConstants.DATE_FORMATE_ONE).getTime() <= TimeUtils.getDate(another.memoCreateTime, MemoConstants.DATE_FORMATE_ONE).getTime() ? -1 : 1;
    }
}

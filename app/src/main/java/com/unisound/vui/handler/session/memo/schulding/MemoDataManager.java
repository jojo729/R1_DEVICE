package com.unisound.vui.handler.session.memo.schulding;

import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import com.unisound.vui.handler.session.memo.entity.LocalMemo;
import com.unisound.vui.handler.session.memo.syncloud.MemoryStateMgr;
import com.unisound.vui.handler.session.memo.utils.MemoConstants;
import com.unisound.vui.handler.session.memo.utils.MemoUtils;
import com.unisound.vui.handler.session.memo.utils.RingingUtils;
import com.unisound.vui.util.AppGlobalConstant;
import com.unisound.vui.util.LogMgr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.litepal.crud.DataSupport;

public class MemoDataManager implements Handler.Callback, MemoryStateMgr.ControlStateListener {
    public static final String KEY_FAVORITE_RINGTONE = "favorite_ringtone";
    public static final String MEMO_OPTION_ADD = "add";
    public static final String MEMO_OPTION_DELETE = "delete";
    public static final String MEMO_OPTION_UPDATE = "update";
    private static final int MSG_ACTION_ADD = 4097;
    private static final int MSG_ACTION_DELETE = 4098;
    private static final int MSG_ACTION_QUERY_ALL = 4096;
    private static final int MSG_ACTION_UPDATE = 4099;
    public static final String SP_NAME_MEMO_RINGTONE_TYPE = "memo_ringtone_type";
    private static final String TAG = (MemoConstants.MEMO_TAG + MemoDataManager.class.getSimpleName());
    private static MemoDataManager sInstance;
    private Context mContext;
    private MemoScheduler mMemoScheduler;
    private MemoryStateMgr mMemoStateMgr;
    private Handler mTaskHandler;
    private List<LocalMemo> sAllMemos = new CopyOnWriteArrayList();
    private Map<Long, List<LocalMemo>> sEnabledMemoMap = new ConcurrentHashMap();

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 4096:
                LogMgr.d(TAG, "MSG_ACTION_QUERY_ALL");
                onQueryAllMemosCompleted(LocalMemo.findAll(LocalMemo.class, new long[0]));
                return true;
            case MSG_ACTION_ADD /*{ENCODED_INT: 4097}*/:
                LogMgr.d(TAG, "MSG_ACTION_ADD: " + msg.obj);
                ((LocalMemo) msg.obj).saveFast();
                return true;
            case 4098:
                LogMgr.d(TAG, "MSG_ACTION_DELETE: " + msg.obj);
                DataSupport.deleteAll(LocalMemo.class, "memoId = ?", ((LocalMemo) msg.obj).getMemoId());
                return true;
            case MSG_ACTION_UPDATE /*{ENCODED_INT: 4099}*/:
                LogMgr.d(TAG, "MSG_ACTION_UPDATE: " + msg.obj);
                ContentValues values = (ContentValues) msg.obj;
                String memoId = values.getAsString("memoid");
                values.remove("memoid");
                Log.d(TAG, "update rows:" + DataSupport.updateAll(LocalMemo.class, values, "memoId = ?", memoId));
                return true;
            default:
                return false;
        }
    }

    private void onQueryAllMemosCompleted(List<LocalMemo> allLocalMemos) {
        this.sAllMemos = filterMemos(allLocalMemos);
        generateTimeMemoMap();
        this.mMemoScheduler.createSchedules(this.mContext, this.sEnabledMemoMap.keySet());
    }

    private void sendDataActionMessage(int what, Object object) {
        this.mTaskHandler.obtainMessage(what, object).sendToTarget();
    }

    private void doDataActionOfQueryAll() {
        sendDataActionMessage(4096, null);
    }

    private void doDataActionOfDelete(LocalMemo memo) {
        sendDataActionMessage(4098, memo);
        this.mMemoStateMgr.reportMemoStatus("delete", memo);
    }

    private void doDataActionOfAdd(LocalMemo memo) {
        sendDataActionMessage(MSG_ACTION_ADD, memo);
        this.mMemoStateMgr.reportMemoStatus("add", memo);
    }

    private void doDataActionOfUpdateEnable(LocalMemo newMemo) {
        ContentValues values = new ContentValues();
        values.put("isenabled", Boolean.valueOf(newMemo.isEnabled()));
        values.put("memoid", newMemo.getMemoId());
        sendDataActionMessage(MSG_ACTION_UPDATE, values);
        this.mMemoStateMgr.reportMemoStatus("update", newMemo);
    }

    private void doDataActionOfUpdateTime(LocalMemo newMemo) {
        ContentValues values = new ContentValues();
        values.put("memoyear", Integer.valueOf(newMemo.getMemoYear()));
        values.put("memomonth", Integer.valueOf(newMemo.getMemoMonth()));
        values.put("memoday", Integer.valueOf(newMemo.getMemoDay()));
        values.put("memohour", Integer.valueOf(newMemo.getMemoHour()));
        values.put("memominute", Integer.valueOf(newMemo.getMemoMinute()));
        values.put("memosecond", Integer.valueOf(newMemo.getMemoSecond()));
        values.put("memoid", newMemo.getMemoId());
        sendDataActionMessage(MSG_ACTION_UPDATE, values);
        this.mMemoStateMgr.reportMemoStatus("update", newMemo);
    }

    private void doDataActionOfUpdateAll(LocalMemo newMemo) {
        ContentValues values = new ContentValues();
        values.put("memoyear", Integer.valueOf(newMemo.getMemoYear()));
        values.put("memomonth", Integer.valueOf(newMemo.getMemoMonth()));
        values.put("memoday", Integer.valueOf(newMemo.getMemoDay()));
        values.put("memohour", Integer.valueOf(newMemo.getMemoHour()));
        values.put("memominute", Integer.valueOf(newMemo.getMemoMinute()));
        values.put("memosecond", Integer.valueOf(newMemo.getMemoSecond()));
        values.put("islocalcreateupddate", Boolean.valueOf(newMemo.isLocalCreateUpDdate()));
        values.put("isenabled", Boolean.valueOf(newMemo.isEnabled()));
        values.put("repeattype", newMemo.getRepeatType());
        values.put("memocontent", newMemo.getMemoContent());
        values.put("memotype", newMemo.getMemoType());
        values.put("ringing", newMemo.getRinging());
        values.put("countdown", Integer.valueOf(newMemo.getCountDown()));
        values.put("memoid", newMemo.getMemoId());
        sendDataActionMessage(MSG_ACTION_UPDATE, values);
        this.mMemoStateMgr.reportMemoStatus("update", newMemo);
    }

    private MemoDataManager() {
        HandlerThread mHandlerThread = new HandlerThread("syncMemo");
        mHandlerThread.start();
        this.mTaskHandler = new Handler(mHandlerThread.getLooper(), this);
        this.mContext = AppGlobalConstant.getContext();
        this.mMemoStateMgr = new MemoryStateMgr();
        this.mMemoStateMgr.setControlStateListener(this);
        this.mMemoScheduler = new MemoScheduler();
        initMemoData();
    }

    public static MemoDataManager getInstance() {
        if (sInstance == null) {
            synchronized (MemoDataManager.class) {
                if (sInstance == null) {
                    sInstance = new MemoDataManager();
                }
            }
        }
        return sInstance;
    }

    public void initMemoData() {
        doDataActionOfQueryAll();
    }

    private List<LocalMemo> filterMemos(List<LocalMemo> memos) {
        LogMgr.d(TAG, "filterMemos, size:" + memos.size());
        List<LocalMemo> memosToDel = new ArrayList<>();
        for (LocalMemo memo : memos) {
            LogMgr.d(TAG, "filterMemos, " + memo);
            if (memo.isEnabled() && memo.getTimeInMillis() < System.currentTimeMillis() - 3000) {
                if (!memo.isAlarm()) {
                    memosToDel.add(memo);
                    doDataActionOfDelete(memo);
                } else if (!memo.isRepeat()) {
                    memo.setEnabled(false);
                    doDataActionOfUpdateEnable(memo);
                } else {
                    MemoUtils.calculateNextTime(memo);
                    doDataActionOfUpdateTime(memo);
                }
            }
        }
        memos.removeAll(memosToDel);
        Collections.sort(memos);
        return memos;
    }

    private void generateTimeMemoMap() {
        Collections.sort(this.sAllMemos);
        this.sEnabledMemoMap.clear();
        for (LocalMemo memo : this.sAllMemos) {
            if (memo.isEnabled()) {
                long time = memo.getTimeInMillis();
                if (!this.sEnabledMemoMap.containsKey(Long.valueOf(time))) {
                    List<LocalMemo> memoList = new ArrayList<>();
                    memoList.add(memo);
                    this.sEnabledMemoMap.put(Long.valueOf(time), memoList);
                } else {
                    this.sEnabledMemoMap.get(Long.valueOf(time)).add(memo);
                }
            }
        }
    }

    public List<LocalMemo> getMemosByTime(long time) {
        return this.sEnabledMemoMap.get(Long.valueOf(time));
    }

    public int getAlarmsCount() {
        return getMemos("alarm").size();
    }

    public int getReminderCount() {
        return getMemos("reminder").size();
    }

    public int getCountDownCount() {
        return getMemos(MemoConstants.MEMO_TYPE_COUNT_DOWN).size();
    }

    @NonNull
    private List<LocalMemo> getMemos(String memoType) {
        List<LocalMemo> memos = new CopyOnWriteArrayList<>();
        for (LocalMemo memo : this.sAllMemos) {
            if (memoType.equals(memo.getMemoType())) {
                memos.add(memo);
            }
        }
        return memos;
    }

    private LocalMemo getLocalMemo(String memoId) {
        for (LocalMemo memo : this.sAllMemos) {
            if (memoId.equals(memo.getMemoId())) {
                return memo;
            }
        }
        return null;
    }

    public void addMemo(LocalMemo memo) {
        LogMgr.d(TAG, "addMemo " + memo);
        if (!this.sAllMemos.contains(memo)) {
            this.sAllMemos.add(memo);
            if (memo.isEnabled()) {
                putMemoToMap(memo);
            }
        }
        doDataActionOfAdd(memo);
    }

    public void updateMemo(LocalMemo newMemo) {
        LogMgr.d(TAG, "updateMemo " + newMemo);
        int index = this.sAllMemos.indexOf(newMemo);
        if (index < 0) {
            LogMgr.d(TAG, "updateMemo newMemo not in List");
            return;
        }
        LocalMemo oldMemo = this.sAllMemos.get(index);
        newMemo.setId(oldMemo.getId());
        this.sAllMemos.set(index, newMemo);
        Collections.sort(this.sAllMemos);
        if (!oldMemo.isEnabled() || !newMemo.isEnabled()) {
            if (oldMemo.isEnabled() && !newMemo.isEnabled()) {
                removeMemoFromMap(oldMemo);
            } else if (!oldMemo.isEnabled() && newMemo.isEnabled()) {
                putMemoToMap(newMemo);
            }
        } else if (newMemo.getTimeInMillis() == oldMemo.getTimeInMillis()) {
            List<LocalMemo> memos = this.sEnabledMemoMap.get(Long.valueOf(oldMemo.getTimeInMillis()));
            if (memos != null) {
                memos.set(memos.indexOf(oldMemo), newMemo);
            }
        } else {
            removeMemoFromMap(oldMemo);
            putMemoToMap(newMemo);
        }
        doDataActionOfUpdateAll(newMemo);
    }

    private void removeMemoFromMap(LocalMemo memo) {
        LogMgr.d(TAG, "removeMemoFromMap: " + memo);
        List<LocalMemo> memoList = this.sEnabledMemoMap.get(Long.valueOf(memo.getTimeInMillis()));
        if (memoList != null) {
            memoList.remove(memo);
            if (memoList.size() == 0) {
                this.sEnabledMemoMap.remove(Long.valueOf(memo.getTimeInMillis()));
                this.mMemoScheduler.cancelAlarmSchedule(this.mContext, memo.getTimeInMillis());
            }
        }
    }

    private void putMemoToMap(LocalMemo memo) {
        LogMgr.d(TAG, "putMemoToMap: " + memo);
        List<LocalMemo> memoList = this.sEnabledMemoMap.get(Long.valueOf(memo.getTimeInMillis()));
        if (memoList != null) {
            memoList.add(memo);
            Collections.sort(memoList);
            return;
        }
        List<LocalMemo> memoList2 = new ArrayList<>();
        memoList2.add(memo);
        this.sEnabledMemoMap.put(Long.valueOf(memo.getTimeInMillis()), memoList2);
        this.mMemoScheduler.createAlarmSchedule(this.mContext, memo.getTimeInMillis());
    }

    public void deleteMemo(LocalMemo memo) {
        LogMgr.d(TAG, "deleteMemo " + memo);
        this.sAllMemos.remove(memo);
        if (memo.isEnabled()) {
            removeMemoFromMap(memo);
        }
        doDataActionOfDelete(memo);
    }

    public void updateMemosByExpiredTime(Context context, long time) {
        if (!this.sEnabledMemoMap.containsKey(Long.valueOf(time))) {
            LogMgr.d(TAG, "updateMemosByExpiredTime, time not in map!");
            return;
        }
        List<LocalMemo> expiredMemos = this.sEnabledMemoMap.remove(Long.valueOf(time));
        LogMgr.d(TAG, "updateMemosByExpiredTime, expiredMemos size:" + expiredMemos.size());
        for (LocalMemo memo : expiredMemos) {
            LogMgr.d(TAG, "updateMemosByExpiredTime, " + memo);
            if (memo.isCountdown() || memo.isReminder()) {
                this.sAllMemos.remove(memo);
                doDataActionOfDelete(memo);
            } else if (memo.isRepeat()) {
                MemoUtils.calculateNextTime(memo);
                doDataActionOfUpdateTime(memo);
                if (!this.sEnabledMemoMap.containsKey(Long.valueOf(memo.getTimeInMillis()))) {
                    this.mMemoScheduler.createAlarmSchedule(context, memo.getTimeInMillis());
                    List<LocalMemo> memoList = new ArrayList<>();
                    memoList.add(memo);
                    this.sEnabledMemoMap.put(Long.valueOf(memo.getTimeInMillis()), memoList);
                } else {
                    List<LocalMemo> memoList2 = this.sEnabledMemoMap.get(Long.valueOf(memo.getTimeInMillis()));
                    memoList2.add(memo);
                    Collections.sort(memoList2);
                }
            } else {
                memo.setEnabled(false);
                doDataActionOfUpdateEnable(memo);
            }
        }
    }

    @Override // com.unisound.vui.handler.session.memo.syncloud.MemoryStateMgr.ControlStateListener
    public void remoteAddMemo(LocalMemo memo) {
        LogMgr.d(TAG, "remoteAddMemo, " + memo);
        addMemo(memo);
    }

    @Override // com.unisound.vui.handler.session.memo.syncloud.MemoryStateMgr.ControlStateListener
    public void remoteDeleteMemo(LocalMemo memo) {
        LogMgr.d(TAG, "remoteDeleteMemo, " + memo);
        deleteMemo(memo);
    }

    @Override // com.unisound.vui.handler.session.memo.syncloud.MemoryStateMgr.ControlStateListener
    public void remoteUpdateMemo(LocalMemo memo) {
        LogMgr.d(TAG, "remoteUpdateMemo, " + memo);
        saveFavoriteRingtone(memo);
        updateMemo(memo);
    }

    private void saveFavoriteRingtone(LocalMemo newMemo) {
        LocalMemo oldMemo;
        int i = this.sAllMemos.indexOf(newMemo);
        if (i > 0 && (oldMemo = this.sAllMemos.get(i)) != null && oldMemo.getRinging() != null && !oldMemo.getRinging().equals(newMemo.getRinging())) {
            LogMgr.d(TAG, "saveFavoriteRingtone, " + newMemo.getRinging());
            this.mContext.getSharedPreferences(SP_NAME_MEMO_RINGTONE_TYPE, 0).edit().putString(KEY_FAVORITE_RINGTONE, newMemo.getRinging()).apply();
        }
    }

    public String getFavoriteRingtone() {
        String ringtone = this.mContext.getSharedPreferences(SP_NAME_MEMO_RINGTONE_TYPE, 0).getString(KEY_FAVORITE_RINGTONE, RingingUtils.RINGING_DEFAULT);
        LogMgr.d(TAG, "getFavoriteRingtone, " + ringtone);
        return ringtone;
    }
}

package com.phicomm.speaker.device.custom.music;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageDispatchManager;
import android.os.ParcelableUtil;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.phicomm.speaker.device.custom.ipc.PhicommIpcSender;
import com.phicomm.speaker.device.custom.status.PhicommDeviceStatusProcessor;
import com.phicomm.speaker.device.utils.LogUtils;
import com.phicomm.speaker.device.utils.PhicommMessageManager;
import com.phicomm.speaker.device.utils.PhicommPlayerUtils;
import com.unisound.ant.device.bean.MusicItem;
import com.unisound.ant.device.bean.PhicommModeInfo;
import com.unisound.ant.device.bean.PhicommMusicInfo;
import com.unisound.ant.device.bean.PhicommPlayerInfo;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.handler.session.music.ANTPlayController;
import com.unisound.vui.handler.session.music.CommonPlayer;
import com.unisound.vui.handler.session.music.PlayController;
import com.unisound.vui.handler.session.music.kuwo.MusicPlayMode;
import com.unisound.vui.handler.session.music.outputevents.RequestListOutputEvent;
import com.unisound.vui.handler.session.music.player.MusicPlayer;
import com.unisound.vui.handler.session.music.playitem.PlayItem;
import com.unisound.vui.handler.session.music.playitem.PlayItemAdapter;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import nluparser.scheme.AudioResult;
import nluparser.scheme.MusicResult;
import nluparser.scheme.NewsResult;

public class PhicommPlayer implements CommonPlayer {
    public static final int MESSAGE_CURRENT_ITEM = 8;
    public static final int MESSAGE_CURRENT_PLAY_MODE = 13;
    public static final int MESSAGE_PAUSE = 2;
    public static final int MESSAGE_PLAY = 1;
    public static final int MESSAGE_PLAY_ERROR = 14;
    public static final int MESSAGE_PLAY_LIST_WITH_INDEX = 15;
    public static final int MESSAGE_PLAY_MODE = 7;
    public static final int MESSAGE_PLAY_NEW = 9;
    public static final int MESSAGE_PLAY_NEXT = 6;
    public static final int MESSAGE_PLAY_PREV = 5;
    public static final int MESSAGE_PLAY_SPECIFIED = 11;
    public static final int MESSAGE_QUERY_ITEM_LIST = 19;
    public static final int MESSAGE_RESUME = 3;
    public static final int MESSAGE_STOP = 4;
    public static final int MESSAGE_SYNCHRONIZE_PLAYER = 16;
    public static final int MESSAGE_UDID = 17;
    private static final int PAGE_START = 1;
    private static final String TAG = "PhicommPlayer";
    private static final int TYPE_AUDIO = 1;
    private static final int TYPE_MUSIC = 0;
    private static final int TYPE_NEWS = 2;
    private static final int TYPE_UNKNOWN = -1;
    private MusicPlayer.Callback callback;
    private int errorTimes;
    public PlayItem mCurrentItem;
    private int mCurrentPage = 1;
    private PlayController.ItemPlayMode mCurrentPlayMode = PlayController.ItemPlayMode.LIST_LOOP;
    private String mDevicePlayingType = "none";
    private ANTEngine mEngine;
    private List<PlayItem> mItemList = new ArrayList();
    private Handler mMainHandler;
    private MessageDispatchManager mMessageManager;
    private final PhicommIpcSender mPhicommIpcSender;
    private int playState = 2;

    public PhicommPlayer(Context context, String udid, ANTEngine engine) {
        Log.d(TAG, "PhicommPlayer: " + udid);
        this.mEngine = engine;
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mMessageManager = PhicommMessageManager.messageCenter(context);
        this.mMessageManager.registerMessageReceiver(new PhicommReceiver(), 8);
        this.mPhicommIpcSender = new PhicommIpcSender(context);
        this.mPhicommIpcSender.send(true, 8, 17, 4, 17, -1, ParcelableUtil.obtain(udid));
        if (PhicommDeviceStatusProcessor.getInstance().getDeviceStatus() == 1) {
            LogMgr.d(TAG, "onSuccess: get device state success");
            this.mMessageManager.sendMessage(4, 16, -1, null);
        }
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public boolean isPrepared() {
        return false;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play() {
        play(1, 1);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play(int currentPage, int totalPage) {
        resetPage();
        this.mCurrentItem = this.mItemList.get(0);
        JsonObject musicJsonObj = new JsonObject();
        musicJsonObj.add("itemList", buildMusicJson(this.mItemList));
        musicJsonObj.addProperty("asrResult", (String) null);
        musicJsonObj.addProperty("pageIndex", Integer.valueOf(currentPage));
        musicJsonObj.addProperty("totalPage", Integer.valueOf(totalPage));
        LogMgr.d(TAG, "play: " + musicJsonObj);
        this.mMessageManager.sendMessage(4, 1, -1, ParcelableUtil.obtain(musicJsonObj.toString()));
        requestPhicommPlayMode();
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play(String asrResult) {
        resetPage();
        this.mCurrentItem = this.mItemList.get(0);
        JsonObject musicJsonObj = new JsonObject();
        musicJsonObj.add("itemList", buildMusicJson(this.mItemList));
        musicJsonObj.addProperty("asrResult", asrResult);
        LogMgr.d(TAG, "play: " + musicJsonObj);
        this.mMessageManager.sendMessage(4, 1, -1, ParcelableUtil.obtain(musicJsonObj.toString()));
        requestPhicommPlayMode();
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play(List<PlayItem> itemList, int index) {
        play(itemList, index, 1, 1);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play(List<PlayItem> itemList, int index, int currentPage, int totalPage) {
        Log.d(TAG, "play: index = " + index + ", currentPage = " + currentPage + ", totalPage = " + totalPage);
        if (itemList != null && index >= 0) {
            resetPage();
            this.mItemList.clear();
            this.mItemList.addAll(itemList);
            this.mCurrentItem = this.mItemList.get(index);
            List<MusicItem> musicList = new ArrayList<>();
            for (PlayItem playItem : this.mItemList) {
                musicList.add(new MusicItem(playItem.getTitle(), playItem.getUrl(), playItem.getId(), parseItemType(playItem.getType()), playItem.getAlbum(), playItem.getArtist()));
            }
            this.mMessageManager.sendMessage(4, 15, -1, ParcelableUtil.obtain(JsonTool.toJson(new PhicommMusicInfo(index, musicList, currentPage, totalPage))));
            requestPhicommPlayMode();
        }
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void pause() {
        this.playState = 2;
        PhicommPlayerUtils.pause(this.mMessageManager);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void resume() {
        this.playState = 3;
        PhicommPlayerUtils.resume(this.mMessageManager);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void stop() {
        this.playState = 1;
        PhicommPlayerUtils.stop(this.mMessageManager);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void playPrev() {
        PhicommPlayerUtils.playPrev(this.mMessageManager);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void playNext() {
        PhicommPlayerUtils.playNext(this.mMessageManager);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void release() {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void setCurrentPlayMode(MusicPlayMode currentPlayMode) {
        switch (currentPlayMode) {
            case MODE_ALL_RANDOM:
                setRandomMode();
                break;
            case MODE_SINGLE_CIRCLE:
                setSingleMode();
                break;
            case MODE_ALL_CIRCLE:
                setCircleMode();
                break;
        }
        requestPhicommPlayMode();
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void collect() {
        this.mMessageManager.sendMessage(4, 8, -1, null);
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void seekTo(long position) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public boolean getPlayWhenReady() {
        return false;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public long getDuration() {
        return 0;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public long getCurrentPosition() {
        return 0;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public int getBufferPercent() {
        return 0;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public boolean isPlaying() {
        return this.playState == 3;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public int getPlayStatus() {
        return this.playState;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public List<PlayItem> getItemList() {
        return this.mItemList;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void registerCallback(MusicPlayer.Callback callback2) {
        this.callback = callback2;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void setRenderer(MusicPlayer.Renderer renderer) {
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void play(int index) {
        LogMgr.d(TAG, "play:start to play index " + index);
        this.mMessageManager.sendMessage(4, 11, -1, ParcelableUtil.obtain(index));
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void addCollectMusic(String id) {
        for (PlayItem playItem : this.mItemList) {
            if (playItem.getId().equals(id)) {
                LogMgr.d(TAG, "addCollectMusic: true");
                playItem.setCollected(true);
                return;
            }
        }
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void deleteCollectMusic(String id) {
        for (PlayItem playItem : this.mItemList) {
            if (playItem.getId().equals(id)) {
                playItem.setCollected(false);
                return;
            }
        }
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void batchDeleteCollectMusic(String ids) {
        String[] result = ids.split(",");
        for (int i = 0; i < result.length; i++) {
            int j = 0;
            while (true) {
                if (j >= this.mItemList.size()) {
                    break;
                }
                PlayItem playItem = this.mItemList.get(j);
                if (playItem.getId().equals(result[i])) {
                    playItem.setCollected(false);
                    break;
                }
                j++;
            }
        }
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void setPlayList(List<PlayItem> itemList) {
        if (itemList != null && itemList.size() != 0) {
            this.mItemList.clear();
            this.mItemList.addAll(itemList);
            this.mCurrentItem = itemList.get(0);
        }
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void appendPlayList(int page, int totalPage, List<PlayItem> itemList) {
        LogMgr.d(TAG, "appendPlayList: page = " + page + ", itemList = " + itemList);
        if (itemList != null && itemList.size() > 0) {
            this.mItemList.clear();
            this.mItemList.addAll(itemList);
            JsonArray musicJson = buildMusicJson(itemList);
            if (page > this.mCurrentPage) {
                this.mCurrentPage = page;
            } else {
                this.mCurrentPage = page + 1;
            }
            JsonObject musicJsonObj = new JsonObject();
            musicJsonObj.addProperty("pageIndex", Integer.valueOf(this.mCurrentPage));
            musicJsonObj.addProperty("totalPage", Integer.valueOf(totalPage));
            musicJsonObj.addProperty("curItemId", this.mCurrentItem.getId());
            musicJsonObj.add("itemList", musicJson);
            LogMgr.d(TAG, "request page " + page + " data success, send item list to phicomm : " + musicJsonObj.toString());
            this.mPhicommIpcSender.send(4, 19, ParcelableUtil.obtain(musicJsonObj.toString()));
        }
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public PlayController.ItemPlayMode getCurrentPlayMode() {
        return this.mCurrentPlayMode;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public void setDevicePlayingType(String devicePlayingType) {
        this.mDevicePlayingType = devicePlayingType;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public String getDevicePlayingType() {
        return this.mDevicePlayingType;
    }

    @Override // com.unisound.vui.handler.session.music.CommonPlayer
    public PlayItem getCurrentItem() {
        return this.mCurrentItem;
    }

    private JsonArray buildMusicJson(List<PlayItem> itemList) {
        JsonArray musicJsonArr = new JsonArray();
        for (PlayItem playItem : itemList) {
            JsonObject musicJson = new JsonObject();
            musicJson.addProperty("itemType", Integer.valueOf(parseItemType(playItem.getType())));
            musicJson.addProperty("title", playItem.getTitle());
            musicJson.addProperty("url", playItem.getUrl());
            musicJson.addProperty("itemId", playItem.getId());
            musicJson.addProperty("album", playItem.getAlbum());
            musicJson.addProperty("artist", playItem.getArtist());
            musicJsonArr.add(musicJson);
        }
        return musicJsonArr;
    }

    /* access modifiers changed from: private */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: public */
    private void queryPlayItem(final int page) {
        boolean z = false;
        int type = -1;
        String str = this.mDevicePlayingType;
        switch (str.hashCode()) {
            case 3377875:
                if (str.equals(ANTPlayController.TYPE_NEWS)) {
                    z = false;
                    break;
                }
                z = true;
                break;
            case 93166550:
                if (str.equals("audio")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            default:
                z = true;
                break;
        }
        if(z){
            type=2;
        }else{
            type=3;
        }
        if (type != -1) {
            int finalType = type;
            this.mMainHandler.post(new Runnable() {
                /* class com.phicomm.speaker.device.custom.music.PhicommPlayer.AnonymousClass1 */

                public void run() {
                    PhicommPlayer.this.mEngine.pipeline().fireUserEventTriggered(new RequestListOutputEvent(finalType, page));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateDevicePlayingType() {
        if (this.mItemList != null && this.mItemList.size() != 0) {
            switch (this.mItemList.get(0).getType()) {
                case TYPE_AUDIO:
                    this.mDevicePlayingType = "audio";
                    return;
                case TYPE_MUSIC:
                    this.mDevicePlayingType = "music";
                    return;
                case TYPE_NEWS:
                    this.mDevicePlayingType = ANTPlayController.TYPE_NEWS;
                    return;
                default:
                    this.mDevicePlayingType = "none";
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateCurrentItem(ParcelableUtil obj) {

        List<MusicItem> list = new Gson().fromJson((String) obj.getValue(), new TypeToken<List<MusicItem>>() {}.getType());
        if (list != null && list.size() != 0) {
            final String itemId = list.get(0).getItemId();
            for (final PlayItem mCurrentItem : this.mItemList) {
                if (mCurrentItem.getId().equals(itemId)) {
                    this.mCurrentItem = mCurrentItem;
                    break;
                }
            }
        }
        LogMgr.d("PhicommPlayer", "update currentItem, item is : " + this.mCurrentItem);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean checkResponseSuccess(Object obj) {
        if (((ParcelableUtil) obj).getValue() instanceof Boolean) {
            return ((Boolean) ((ParcelableUtil) obj).getValue()).booleanValue();
        }
        LogMgr.e(TAG, "Illeage arugument!current obj is not boolean but" + ((ParcelableUtil) obj).getValue());
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateCurrentMode(ParcelableUtil obj) {
        LogMgr.d(TAG, "updateCurrentMode: " + obj.getValue());
        PhicommModeInfo modeInfo = (PhicommModeInfo) JsonTool.fromJson((String) obj.getValue(), PhicommModeInfo.class);
        if (modeInfo != null) {
            this.mCurrentPlayMode = parseCurrentMode(modeInfo.getPlayMode());
        }
    }

    private PlayController.ItemPlayMode parseCurrentMode(int playMode) {
        switch (playMode) {
            case 1:
                return PlayController.ItemPlayMode.LIST_SHUFFLED;
            case 2:
                return PlayController.ItemPlayMode.LIST_LOOP;
            case 3:
                return PlayController.ItemPlayMode.SINGLE_LOOP;
            default:
                return PlayController.ItemPlayMode.LIST_LOOP;
        }
    }

    private void requestPhicommPlayMode() {
        this.mMessageManager.sendMessage(4, 13, -1, null);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handlePlayError(ParcelableUtil obj) {
        updateCurrentItem(obj);
        this.playState = 2;
        updateDevicePlayingType();
        onStateChanged();
        this.errorTimes++;
        if (this.errorTimes <= 3) {
            playNext();
            resume();
            return;
        }
        this.mEngine.pipeline().fireUserEventTriggered(ExoConstants.DO_FINISH_ALL_INTERRUPT);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void synchronizePlayerInfo(ParcelableUtil obj) {
        LogMgr.d(TAG, "syncPlayerInfo: " + obj.getValue());
        PhicommPlayerInfo phicommPlayerInfo = (PhicommPlayerInfo) JsonTool.fromJson((String) obj.getValue(), PhicommPlayerInfo.class);
        List<MusicItem> playList = phicommPlayerInfo.getPlayList();
        if (phicommPlayerInfo.getPlayIndex() == -1 || playList == null || playList.size() == 0) {
            LogMgr.d(TAG, "empty player info, do not sync, return");
            return;
        }
        synchronizePlayList(playList);
        this.mCurrentItem = this.mItemList.get(phicommPlayerInfo.getPlayIndex());
        this.mCurrentPlayMode = parseCurrentMode(phicommPlayerInfo.getPlayMode());
        this.playState = parseCurrentState(phicommPlayerInfo.getPlayState());
    }

    private void synchronizePlayList(List<MusicItem> playList) {
        switch (playList.get(0).getItemType()) {
            case 0:
                synchronizeMusicList(playList);
                return;
            case 1:
                synchronizeAuidoList(playList);
                return;
            case 2:
                synchronizeNewsList(playList);
                return;
            default:
                return;
        }
    }

    private void synchronizeMusicList(List<MusicItem> playList) {
        List<MusicResult.Music> musicList = new ArrayList<>();
        for (MusicItem musicItem : playList) {
            MusicResult.Music music = new MusicResult.Music();
            music.setId(musicItem.getItemId());
            music.setUrl(musicItem.getUrl());
            music.setTitle(musicItem.getTitle());
            music.setAlbum(musicItem.getAlbum());
            music.setArtist(musicItem.getArtist());
            musicList.add(music);
        }
        List<PlayItem> playItemList = PlayItemAdapter.adaptMusic(musicList);
        this.mItemList.clear();
        this.mItemList.addAll(playItemList);
    }

    private void synchronizeAuidoList(List<MusicItem> playList) {
        List<AudioResult.Music> audioList = new ArrayList<>();
        for (MusicItem musicItem : playList) {
            AudioResult.Music audio = new AudioResult.Music();
            audio.setId(musicItem.getItemId());
            audio.setUrl(musicItem.getUrl());
            audio.setTitle(musicItem.getTitle());
            audioList.add(audio);
        }
        List<PlayItem> playItemList = PlayItemAdapter.adaptAudio(audioList, null, null);
        this.mItemList.clear();
        this.mItemList.addAll(playItemList);
    }

    private void synchronizeNewsList(List<MusicItem> playList) {
        List<NewsResult.NewsBean> newsList = new ArrayList<>();
        for (MusicItem musicItem : playList) {
            NewsResult.NewsBean newsBean = new NewsResult.NewsBean();
            newsBean.setId(musicItem.getItemId());
            newsBean.setAudioUrl(musicItem.getUrl());
            newsBean.setTitle(musicItem.getTitle());
            newsList.add(newsBean);
        }
        List<PlayItem> playItemList = PlayItemAdapter.adaptNews(newsList);
        this.mItemList.clear();
        this.mItemList.addAll(playItemList);
    }

    private int parseCurrentState(int playState2) {
        switch (playState2) {
            case 0:
            default:
                return 1;
            case 1:
                return 3;
            case 2:
                return 2;
        }
    }

    private int parseItemType(PlayItem.ItemType type) {
        switch (type) {
            case TYPE_AUDIO:
                return 1;
            case TYPE_MUSIC:
                return 0;
            case TYPE_NEWS:
                return 2;
            default:
                return -1;
        }
    }

    private void setRandomMode() {
        LogMgr.d(TAG, "sendMessage MSG_TYPE_PLAYER setRandomMode 7-1");
        this.mMessageManager.sendMessage(4, 7, -1, ParcelableUtil.obtain(1));
    }

    private void setCircleMode() {
        LogMgr.d(TAG, "sendMessage MSG_TYPE_PLAYER setCircleMode 7-2");
        this.mMessageManager.sendMessage(4, 7, -1, ParcelableUtil.obtain(2));
    }

    private void setSingleMode() {
        LogMgr.d(TAG, "sendMessage MSG_TYPE_PLAYER setSingleMode 7-3");
        this.mMessageManager.sendMessage(4, 7, -1, ParcelableUtil.obtain(3));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onStateChanged() {
        if (PhicommDeviceStatusProcessor.getInstance().getDeviceStatus() == 3) {
            LogMgr.d(TAG, "current device status is bluetooth, ignore play status changed event");
        } else if (this.callback != null) {
            this.callback.onPlayStateChanged(this.playState);
        }
    }

    private void resetPage() {
        this.mCurrentPage = 1;
    }

    private class PhicommReceiver implements MessageDispatchManager.MessageReceiver {
        private PhicommReceiver() {
        }

        @Override // android.os.MessageDispatchManager.MessageReceiver
        public void notifyMsg(int what, int arg1, int arg2, Object obj) {
            LogMgr.d(PhicommPlayer.TAG, "notifyMsg: " + what + "&" + arg1 + ((ParcelableUtil) obj).getValue());
            if (arg1 == 8 || arg1 == 9 || arg1 == 13 || arg1 == 16 || arg1 == 19 || arg1 == 14 || PhicommPlayer.this.checkResponseSuccess(obj)) {
                switch (arg1) {
                    case 1:
                        LogMgr.d(PhicommPlayer.TAG, "currentItem:" + PhicommPlayer.this.mCurrentItem);
                        LogMgr.d(PhicommPlayer.TAG, "notifyMsg: 我收到播放歌曲的回调了, currentItem:" + PhicommPlayer.this.mCurrentItem);
                        return;
                    case 2:
                        PhicommPlayer.this.playState = 2;
                        PhicommPlayer.this.onStateChanged();
                        LogMgr.d(PhicommPlayer.TAG, "notifyMsg: 我收到暂停播放的消息了, currentItem:" + PhicommPlayer.this.mCurrentItem);
                        return;
                    case 3:
                        PhicommPlayer.this.playState = 3;
                        PhicommPlayer.this.errorTimes = 0;
                        PhicommPlayer.this.updateDevicePlayingType();
                        PhicommPlayer.this.onStateChanged();
                        LogMgr.d(PhicommPlayer.TAG, "notifyMsg: 我收到恢复播放的消息了, currentItem:" + PhicommPlayer.this.mCurrentItem);
                        return;
                    case 4:
                        PhicommPlayer.this.playState = 1;
                        PhicommPlayer.this.mDevicePlayingType = "none";
                        PhicommPlayer.this.mItemList.clear();
                        PhicommPlayer.this.onStateChanged();
                        PhicommPlayer.this.mCurrentItem = null;
                        LogMgr.d(PhicommPlayer.TAG, "notifyMsg: 我收到停止播放的消息了");
                        return;
                    case 5:
                        LogMgr.d(PhicommPlayer.TAG, "notifyMsg: 我收到播放上一首的消息了");
                        return;
                    case 6:
                        LogMgr.d(PhicommPlayer.TAG, "notifyMsg: 我收到播放下一首的消息了");
                        return;
                    case 7:
                    case 10:
                    case 12:
                    case 15:
                    case 17:
                    case 18:
                    default:
                        LogMgr.d(PhicommPlayer.TAG, "notifyMsg: default receive" + ((ParcelableUtil) obj).getValue());
                        return;
                    case 8:
                        updateCurrentItem((ParcelableUtil) obj);
                        return;
                    case 9:
                        LogMgr.d(PhicommPlayer.TAG, "notifyMsg: 我收到播放新歌的消息了");
                        playState = 3;
                        errorTimes = 0;
                        updateCurrentItem((ParcelableUtil) obj);
                        updateDevicePlayingType();
                        onStateChanged();
                        return;
                    case 11:
                        LogMgr.d(PhicommPlayer.TAG, "notifyMsg: 我收到播放指定歌曲的消息了" + ((Boolean) ((ParcelableUtil) obj).getValue()).booleanValue());
                        PhicommPlayer.this.playState = 3;
                        return;
                    case 13:
                        LogMgr.d(PhicommPlayer.TAG, "notifyMsg: received current play mode message" + ((ParcelableUtil) obj).getValue());
                        PhicommPlayer.this.updateCurrentMode((ParcelableUtil) obj);
                        return;
                    case 14:
                        LogMgr.d(PhicommPlayer.TAG, "notifyMsg: receive play error message" + ((ParcelableUtil) obj).getValue());
                        PhicommPlayer.this.handlePlayError((ParcelableUtil) obj);
                        return;
                    case 16:
                        PhicommPlayer.this.synchronizePlayerInfo((ParcelableUtil) obj);
                        return;
                    case 19:
                        Integer page = JSON.parseObject((String) ((ParcelableUtil) obj).getValue()).getInteger("requestPageIndex");
                        LogUtils.d(PhicommPlayer.TAG, "received msg(query item list), page = " + page);
                        PhicommPlayer.this.queryPlayItem(page.intValue());
                        return;
                }
            } else {
                LogMgr.d(PhicommPlayer.TAG, "currentItem:" + PhicommPlayer.this.mCurrentItem);
            }
        }
    }
}

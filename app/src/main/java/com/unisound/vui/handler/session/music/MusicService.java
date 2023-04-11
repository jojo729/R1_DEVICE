package com.unisound.vui.handler.session.music;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.unisound.ant.device.DeviceCenterHandler;
import com.unisound.ant.device.listener.MusicStatusListener;
import com.unisound.vui.handler.session.music.PlayController;
import com.unisound.vui.handler.session.music.kuwo.MusicPlayMode;
import com.unisound.vui.handler.session.music.listener.MusicListenerWapper;
import com.unisound.vui.handler.session.music.player.MusicPlayer;
import com.unisound.vui.handler.session.music.playitem.PlayItem;
import com.unisound.vui.util.LogMgr;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class MusicService extends Service implements MusicPlayer.Callback, MusicPlayer.Renderer {
    private static final int DEFAULTTIME = 10000;
    static final byte MUSIC_LIST_LOOP = 1;
    public static final String MUSIC_LIST_LOOP_NAME = "listLoop";
    static final byte MUSIC_LIST_ORDER = 8;
    public static final String MUSIC_LIST_ORDER_NAME = "listOrder";
    static final byte MUSIC_LIST_SHUFFLED = 4;
    public static final String MUSIC_LIST_SHUFFLED_NAME = "listShuffled";
    static final byte MUSIC_SINGLE_LOOP = 2;
    public static final String MUSIC_SINGLE_LOOP_NAME = "singleLoop";
    private static final String TAG = "MusicService";
    private static Map<Byte, String> playModes = new HashMap();
    private PlayItem.ItemType currentItemType;
    private int currentPosition;
    private byte flags = 1;
    private MusicStatusListener mMusicStatusListener;
    private Iterator<MusicListenerWapper> musicListenerIterator;
    private CopyOnWriteArrayList<MusicListenerWapper> musicListeners;
    private CommonPlayer musicPlayer;
    private int offsetTime;
    private int playBackState;

    public void onCreate() {
        super.onCreate();
        this.musicListeners = new CopyOnWriteArrayList<>();
        playModes.put((byte) 1, "listLoop");
        playModes.put((byte) 2, "singleLoop");
        playModes.put((byte) 4, "listShuffled");
        playModes.put((byte) 8, "listOrder");
        setStateListener(DeviceCenterHandler.getDeviceCenterMgr());
    }

    public void setStateListener(MusicStatusListener listener) {
        this.mMusicStatusListener = listener;
    }

    /* access modifiers changed from: package-private */
    public CommonPlayer getMusicPlayer() {
        return this.musicPlayer;
    }

    /* access modifiers changed from: package-private */
    public boolean isPrepared() {
        LogMgr.d(TAG, "--->>isPrepared " + this.musicPlayer.isPrepared());
        return this.musicPlayer.isPrepared();
    }

    /* access modifiers changed from: package-private */
    public PlayItem play() {
        this.musicPlayer.play();
        return null;
    }

    /* access modifiers changed from: package-private */
    public PlayItem play(int currentPage, int totalPage) {
        this.musicPlayer.play(currentPage, totalPage);
        return null;
    }

    /* access modifiers changed from: package-private */
    public PlayItem play(String asrResult) {
        this.musicPlayer.play(asrResult);
        return null;
    }

    /* access modifiers changed from: package-private */
    public void pause() {
        LogMgr.d(TAG, "--->>pause");
        this.musicPlayer.pause();
    }

    /* access modifiers changed from: package-private */
    public void resume() {
        this.musicPlayer.resume();
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        LogMgr.d(TAG, "--->>stop");
        this.musicPlayer.stop();
    }

    /* access modifiers changed from: package-private */
    public void playPrev() {
        this.musicPlayer.playPrev();
    }

    /* access modifiers changed from: package-private */
    public void playNext() {
        LogMgr.d(TAG, "--->>stop");
        this.musicPlayer.playNext();
    }

    /* access modifiers changed from: package-private */
    public void setPlaylist(List<PlayItem> itemList) {
        if (itemList != null) {
            this.currentPosition = 0;
        }
        this.musicPlayer.setPlayList(itemList);
        this.musicListenerIterator = this.musicListeners.iterator();
        while (this.musicListenerIterator.hasNext()) {
            this.musicListenerIterator.next().fireMusicListChanged(itemList);
        }
    }

    /* access modifiers changed from: package-private */
    public void appendPlaylist(int page, int totalPage, List<PlayItem> playItemList) {
        this.musicPlayer.appendPlayList(page, totalPage, playItemList);
        this.musicListenerIterator = this.musicListeners.iterator();
        while (this.musicListenerIterator.hasNext()) {
            this.musicListenerIterator.next().fireMusicListChanged(playItemList);
        }
    }

    /* access modifiers changed from: package-private */
    public long getDuration() {
        return this.musicPlayer.getDuration();
    }

    /* access modifiers changed from: package-private */
    public boolean playWhenReady() {
        return this.musicPlayer.getPlayWhenReady();
    }

    /* access modifiers changed from: package-private */
    public void fastForward() {
        long fastForwardTime = this.musicPlayer.getCurrentPosition() + 10000;
        if (this.offsetTime != 0) {
            fastForwardTime = (long) this.offsetTime;
        }
        this.musicPlayer.seekTo(fastForwardTime);
        this.offsetTime = 0;
    }

    /* access modifiers changed from: package-private */
    public void skipToNext(boolean playWhenReady) {
        onMusicChanged(true, playWhenReady);
        LogMgr.d(TAG, "skipToNext musicIndex = %d ", Integer.valueOf(this.currentPosition));
    }

    /* access modifiers changed from: package-private */
    public void skipToPrevious(boolean playWhenReady) {
        onMusicChanged(false, playWhenReady);
        LogMgr.d(TAG, "skipToPrevious musicIndex = %d ", Integer.valueOf(this.currentPosition));
    }

    /* access modifiers changed from: package-private */
    public void skipToQueueItem(int index, boolean playWhenReady) {
        LogMgr.d(TAG, "skipToQueueItem musicIndex = %d ", Integer.valueOf(index));
        play();
    }

    /* access modifiers changed from: package-private */
    public void rewind() {
        long toPostion = this.musicPlayer.getCurrentPosition() - 10000;
        if (this.offsetTime != 0) {
            toPostion = (long) this.offsetTime;
        }
        if (toPostion < 0) {
            this.musicPlayer.seekTo(0);
        } else {
            this.musicPlayer.seekTo(toPostion);
        }
        this.offsetTime = 0;
    }

    /* access modifiers changed from: package-private */
    public void collectItem(boolean isCollected) {
        PlayItem currentItem = getCurrentItem();
        if (currentItem != null) {
            currentItem.setCollected(isCollected);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isCollected() {
        PlayItem currentItem = getCurrentItem();
        if (currentItem != null) {
            return currentItem.isCollected();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void setRepeating(boolean repeating) {
        LogMgr.d(TAG, "setRepeating to %b", Boolean.valueOf(repeating));
        setFlag(2, repeating);
        this.musicPlayer.setCurrentPlayMode(MusicPlayMode.MODE_SINGLE_CIRCLE);
    }

    /* access modifiers changed from: package-private */
    public boolean isRepeating() {
        LogMgr.d(TAG, "getRepeating to %b", Boolean.valueOf(getFlag(2)));
        return getFlag(2);
    }

    /* access modifiers changed from: package-private */
    public void setShuffled(boolean shuffled) {
        LogMgr.d(TAG, "setShuffled from %b to %b ", Boolean.valueOf(isShuffled()), Boolean.valueOf(shuffled));
        setFlag(4, shuffled);
        this.musicPlayer.setCurrentPlayMode(MusicPlayMode.MODE_ALL_RANDOM);
    }

    /* access modifiers changed from: package-private */
    public boolean isShuffled() {
        LogMgr.d(TAG, "isShuffled  %b ", Boolean.valueOf(getFlag(4)));
        return getFlag(4);
    }

    /* access modifiers changed from: package-private */
    public void setListLoop(boolean listLoop) {
        LogMgr.d(TAG, "setListLoop to %b", Boolean.valueOf(listLoop));
        setFlag(1, listLoop);
        this.musicPlayer.setCurrentPlayMode(MusicPlayMode.MODE_ALL_CIRCLE);
    }

    /* access modifiers changed from: package-private */
    public boolean isListLoop() {
        LogMgr.d(TAG, "isListLoop %b", Boolean.valueOf(getFlag(1)));
        return getFlag(1);
    }

    /* access modifiers changed from: package-private */
    public void setListOrder(boolean listOrder) {
        LogMgr.d(TAG, "setListOrder ot %b", Boolean.valueOf(listOrder));
        setFlag(8, listOrder);
        this.musicPlayer.setCurrentPlayMode(MusicPlayMode.MODE_ALL_ORDER);
    }

    /* access modifiers changed from: package-private */
    public boolean isListOrder() {
        LogMgr.d(TAG, "isListOrder %b", Boolean.valueOf(getFlag(8)));
        return getFlag(8);
    }

    /* access modifiers changed from: package-private */
    public List<PlayItem> getItemList() {
        return this.musicPlayer.getItemList();
    }

    /* access modifiers changed from: package-private */
    public PlayItem getCurrentItem() {
        return this.musicPlayer.getCurrentItem();
    }

    /* access modifiers changed from: package-private */
    public void registerMusicListener(MusicListenerWapper lis) {
        if (lis != null && !this.musicListeners.contains(lis)) {
            this.musicListeners.add(lis);
        }
    }

    /* access modifiers changed from: package-private */
    public void unRegisterListener(MusicListenerWapper lis) {
        if (this.musicListeners.contains(lis)) {
            this.musicListeners.remove(lis);
        }
    }

    /* access modifiers changed from: package-private */
    public int getPlaybackStatus() {
        return this.playBackState;
    }

    /* access modifiers changed from: package-private */
    public int getBufferPercent() {
        return this.musicPlayer.getBufferPercent();
    }

    /* access modifiers changed from: package-private */
    public byte getPlayMode() {
        return this.flags;
    }

    public String getPlayMode(byte key) {
        return playModes.get(Byte.valueOf(key));
    }

    /* access modifiers changed from: package-private */
    public void setBeginIndex(int index) {
        this.currentPosition = index;
    }

    /* access modifiers changed from: package-private */
    public void setOffsetTime(int offsetTime2) {
        this.offsetTime = offsetTime2;
    }

    private boolean getFlag(int flag) {
        return (this.flags & flag) != 0;
    }

    private void setFlag(int flag, boolean value) {
        this.flags = (byte) (this.flags & 0);
        if (value) {
            this.flags = (byte) (this.flags | flag);
        } else {
            this.flags = (byte) (this.flags & (flag ^ -1));
        }
        this.musicListenerIterator = this.musicListeners.iterator();
        while (this.musicListenerIterator.hasNext()) {
            this.musicListenerIterator.next().firePlayModeChanged(getPlayMode(this.flags));
        }
    }

    private void onMusicChanged(boolean isNext, boolean playWhenReady) {
        switch (this.flags) {
        }
        stop();
        play();
        Iterator<MusicListenerWapper> it = this.musicListeners.iterator();
        while (it.hasNext()) {
            it.next().fireMusicChange();
        }
    }

    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer.Callback
    public void onPlayStateChanged(int state) {
        LogMgr.d(TAG, "onPlayStateChanged state=" + state);
        this.playBackState = state;
        this.mMusicStatusListener.onMusicStatusChanged(state);
        this.musicListenerIterator = this.musicListeners.iterator();
        while (this.musicListenerIterator.hasNext()) {
            this.musicListenerIterator.next().fireStatusChanged(state);
        }
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer.Callback
    public void onPlayerError(String errorMessage) {
        LogMgr.d(TAG, "onPlayerError : %s", errorMessage);
        this.musicListenerIterator = this.musicListeners.iterator();
        while (this.musicListenerIterator.hasNext()) {
            this.musicListenerIterator.next().fireError(errorMessage);
        }
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer.Callback
    public void onOperateCommandChange(int operate) {
        LogMgr.d(TAG, "onOperateCommandChange : %d", Integer.valueOf(operate));
        this.musicListenerIterator = this.musicListeners.iterator();
        while (this.musicListenerIterator.hasNext()) {
            this.musicListenerIterator.next().fireItemOperateCommand(operate);
        }
    }

    public void switchTo(String itemId) {
        this.musicPlayer.play(findIndexById(itemId));
    }

    private int findIndexById(String itemId) {
        List<PlayItem> itemList = getItemList();
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId().equals(itemId)) {
                return i;
            }
        }
        return -1;
    }

    public int getPlayStatus() {
        return this.musicPlayer.getPlayStatus();
    }

    public void addCollectMusic(String id) {
        this.musicPlayer.addCollectMusic(id);
    }

    public void deleteCollectMusic(String id) {
        this.musicPlayer.deleteCollectMusic(id);
    }

    public void batchDeleteCollectMusic(String ids) {
        this.musicPlayer.batchDeleteCollectMusic(ids);
    }

    public PlayController.ItemPlayMode getPlayListMode() {
        return this.musicPlayer.getCurrentPlayMode();
    }

    public void play(List<PlayItem> itemList, int index) {
        this.musicPlayer.play(itemList, index);
    }

    public void play(List<PlayItem> itemList, int index, int currentPage, int totalPage) {
        this.musicPlayer.play(itemList, index, currentPage, totalPage);
    }

    public void setDevicePlayingType(String devicePlayingType) {
        this.musicPlayer.setDevicePlayingType(devicePlayingType);
    }

    public String getDevicePlayingType() {
        return this.musicPlayer.getDevicePlayingType();
    }

    public class MusicBinder extends Binder {
        public MusicBinder() {
        }

        public MusicService getService() {
            return MusicService.this;
        }

        public void setMusicPlayer(CommonPlayer commonPlayer) {
            MusicService.this.musicPlayer = commonPlayer;
            MusicService.this.musicPlayer.registerCallback(MusicService.this);
            MusicService.this.musicPlayer.setRenderer(MusicService.this);
        }
    }

    @Override // com.unisound.vui.handler.session.music.player.MusicPlayer.Renderer
    public MusicPlayer.Renderer.RendererType getRendererType() {
        switch (this.currentItemType) {
            case TYPE_MUSIC:
                return MusicPlayer.Renderer.RendererType.TYPE_MUSIC;
            case TYPE_AUDIO:
                return MusicPlayer.Renderer.RendererType.TYPE_AUDIO;
            case TYPE_RADIO:
                return MusicPlayer.Renderer.RendererType.TYPE_RADIO;
            default:
                return MusicPlayer.Renderer.RendererType.TYPE_MUSIC;
        }
    }
}

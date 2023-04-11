package com.unisound.ant.device.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import nluparser.scheme.AudioResult;

public class MusicData extends Parameter {
    public static final String CMD_CANCEL_COLLECT = "cancelCollect";
    public static final String CMD_CHANGE_PLAY_MODE = "changeMode";
    public static final String CMD_CHANGE_VOLUME = "changeVolume";
    public static final String CMD_COLLECT = "collect";
    public static final String CMD_EXIT = "exit";
    public static final String CMD_PAUSE = "pause";
    public static final String CMD_PLAY = "play";
    public static final String CMD_STOP = "stop";
    public static final String PLAY_MODE_LIST_ORDER = "listOrder";
    public static final String PLAY_MODE_LOOP_LIST = "listLoop";
    public static final String PLAY_MODE_LOOP_SINGLE = "singleLoop";
    public static final String PLAY_MODE_RANDOM = "listShuffled";
    public static final String PLAY_STATUS_BUFFERING = "buffering";
    public static final String PLAY_STATUS_END = "end";
    public static final String PLAY_STATUS_PAUSE = "pause";
    public static final String PLAY_STATUS_PLAYING = "playing";
    public static final String PLAY_STATUS_PREPARED = "prepared";
    public static final String PLAY_STATUS_STOP = "stop";
    private String albumId;
    private AudioResult.Music attachedInfo;
    private String controlCmd = "";
    private String itemId;
    @SerializedName("musicListId")
    @JSONField(name = "musicListId")
    private String listId;
    private String pageCount;
    private String pageNo;
    private String pageSize;
    private String playMode = "";
    private String playState;
    private String timeAsc;
    private int volume;

    public String getListId() {
        return this.listId;
    }

    public void setListId(String listId2) {
        this.listId = listId2;
    }

    public String getControlCmd() {
        return this.controlCmd;
    }

    public void setControlCmd(String controlCmd2) {
        this.controlCmd = controlCmd2;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId2) {
        this.itemId = itemId2;
    }

    public String getPlayState() {
        return this.playState;
    }

    public void setPlayState(String playState2) {
        this.playState = playState2;
    }

    public String getPlayMode() {
        return this.playMode;
    }

    public void setPlayMode(String playMode2) {
        this.playMode = playMode2;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume2) {
        this.volume = volume2;
    }

    public AudioResult.Music getAttachedInfo() {
        return this.attachedInfo;
    }

    public void setAttachedInfo(AudioResult.Music attachedInfo2) {
        this.attachedInfo = attachedInfo2;
    }

    public String getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(String pageNo2) {
        this.pageNo = pageNo2;
    }

    public String getPageSize() {
        return this.pageSize;
    }

    public String getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(String pageCount2) {
        this.pageCount = pageCount2;
    }

    public void setPageSize(String pageSize2) {
        this.pageSize = pageSize2;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId2) {
        this.albumId = albumId2;
    }

    public String getTimeAsc() {
        return this.timeAsc;
    }

    public void setTimeAsc(String timeAsc2) {
        this.timeAsc = timeAsc2;
    }
}

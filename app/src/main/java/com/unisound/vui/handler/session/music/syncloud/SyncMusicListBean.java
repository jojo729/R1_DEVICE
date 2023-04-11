package com.unisound.vui.handler.session.music.syncloud;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SyncMusicListBean {
    private List<MusicInfo> musicList;
    private String udid;

    public String getUdid() {
        return this.udid;
    }

    public void setUdid(String udid2) {
        this.udid = udid2;
    }

    public List<MusicInfo> getMusicList() {
        return this.musicList;
    }

    public void setMusicList(List<MusicInfo> musicList2) {
        this.musicList = musicList2;
    }

    public static class MusicInfo {
        @SerializedName("album")
        @JSONField(name = "album")
        String album;
        @SerializedName("artist")
        @JSONField(name = "artist")
        String artist;
        @SerializedName("duration")
        @JSONField(name = "duration")
        int duration;
        @SerializedName("errorCode")
        @JSONField(name = "errorCode")
        int errorCode;
        @SerializedName("hdImgUrl")
        @JSONField(name = "hdImgUrl")
        String hdImgUrl;
        @SerializedName("id")
        @JSONField(name = "id")
        String id;
        @SerializedName("imgUrl")
        @JSONField(name = "imgUrl")
        String imgUrl;
        @SerializedName("isCollected")
        @JSONField(name = "isCollected")
        boolean isCollected;
        @SerializedName("lyric")
        @JSONField(name = "lyric")
        String mLyric;
        @SerializedName("musicListId")
        @JSONField(name = "musicListId")
        String musicListId;
        @SerializedName("title")
        @JSONField(name = "title")
        String title;
        @SerializedName("url")
        @JSONField(name = "url")
        String url;

        public String getId() {
            return this.id;
        }

        public String getMusicListId() {
            return this.musicListId;
        }

        public String getTitle() {
            return this.title;
        }

        public String getArtist() {
            return this.artist;
        }

        public String getAlbum() {
            return this.album;
        }

        public String getUrl() {
            return this.url;
        }

        public int getDuration() {
            return this.duration;
        }

        public int getErrorCode() {
            return this.errorCode;
        }

        public String getImgUrl() {
            return this.imgUrl;
        }

        public String getHdImgUrl() {
            return this.hdImgUrl;
        }

        public String getmLyric() {
            return this.mLyric;
        }

        public void setId(String id2) {
            this.id = id2;
        }

        public void setMusicListId(String musicListId2) {
            this.musicListId = musicListId2;
        }

        public void setTitle(String title2) {
            this.title = title2;
        }

        public void setArtist(String artist2) {
            this.artist = artist2;
        }

        public void setAlbum(String album2) {
            this.album = album2;
        }

        public void setUrl(String url2) {
            this.url = url2;
        }

        public void setDuration(int duration2) {
            this.duration = duration2;
        }

        public void setErrorCode(int errorCode2) {
            this.errorCode = errorCode2;
        }

        public void setImgUrl(String imgUrl2) {
            this.imgUrl = imgUrl2;
        }

        public void setHdImgUrl(String hdImgUrl2) {
            this.hdImgUrl = hdImgUrl2;
        }

        public void setmLyric(String mLyric2) {
            this.mLyric = mLyric2;
        }

        public boolean isCollected() {
            return this.isCollected;
        }

        public void setCollected(boolean isCollected2) {
            this.isCollected = isCollected2;
        }
    }
}

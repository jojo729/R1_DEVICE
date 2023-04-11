package com.unisound.vui.handler.session.music.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import nluparser.scheme.ASR;

public class Music implements Parcelable {
    public static final Parcelable.Creator<Music> CREATOR = new Parcelable.Creator<Music>() {
        /* class com.unisound.vui.handler.session.music.entity.Music.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Music createFromParcel(Parcel source) {
            Music music = new Music();
            music.readFromParcel(source);
            return music;
        }

        @Override // android.os.Parcelable.Creator
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };
    public static final Music NONE = new Music();
    private static final String TAG = "Music";
    @SerializedName("album")
    @JSONField(name = "album")
    private Album album;
    @SerializedName("announcer")
    @JSONField(name = "announcer")
    private Announcer announcer;
    @SerializedName("artist")
    @JSONField(name = "artist")
    private String artist;
    @SerializedName("bufferPercent")
    @JSONField(name = "bufferPercent")
    private int bufferPercent;
    @SerializedName("curPostion")
    @JSONField(name = "curPostion")
    private long curPostion;
    @SerializedName("dataSource")
    @JSONField(name = "dataSource")
    private String dataSource;
    @SerializedName("errorCode")
    @JSONField(name = "errorCode")
    private int errorCode;
    @SerializedName("hdImgUrl")
    @JSONField(name = "hdImgUrl")
    private String hdImgUrl;
    @SerializedName("imgUrl")
    @JSONField(name = "imgUrl")
    private String imgUrl;
    @SerializedName(ASR.LOCAL)
    @JSONField(name = ASR.LOCAL)
    private boolean local;
    @SerializedName("lyric")
    @JSONField(name = "lyric")
    private String lyric;
    @SerializedName("mediaId")
    @JSONField(name = "mediaId")
    private String mediaId;
    @SerializedName("musicType")
    @JSONField(name = "musicType")
    private MusicType musicType;
    @SerializedName("size")
    @JSONField(name = "size")
    private long size;
    @SerializedName("time")
    @JSONField(name = "time")
    private long time;
    @SerializedName("title")
    @JSONField(name = "title")
    private String title;
    @SerializedName("url")
    @JSONField(name = "url")
    private String url;

    public enum MusicType {
        MUSIC,
        AUDIO,
        RADIO
    }

    public Music() {
    }

    public Music(String mediaId2, String title2, String artist2, Album album2, Announcer announcer2, String url2, long size2, long time2, int errorCode2, boolean local2, long curPostion2, int bufferPercent2, String imgUrl2, String hdImgUrl2, String lyric2, String dataSource2, MusicType musicType2) {
        this.mediaId = mediaId2;
        this.title = title2;
        this.artist = artist2;
        this.album = album2;
        this.announcer = announcer2;
        this.url = url2;
        this.size = size2;
        this.time = time2;
        this.errorCode = errorCode2;
        this.local = local2;
        this.curPostion = curPostion2;
        this.bufferPercent = bufferPercent2;
        this.imgUrl = imgUrl2;
        this.hdImgUrl = hdImgUrl2;
        this.lyric = lyric2;
        this.dataSource = dataSource2;
        this.musicType = musicType2;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(String mediaId2) {
        this.mediaId = mediaId2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist2) {
        this.artist = artist2;
    }

    public Album getAlbum() {
        return this.album;
    }

    public void setAlbum(Album album2) {
        this.album = album2;
    }

    public Announcer getAnnouncer() {
        return this.announcer;
    }

    public void setAnnouncer(Announcer announcer2) {
        this.announcer = announcer2;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url2) {
        this.url = url2;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size2) {
        this.size = size2;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time2) {
        this.time = time2;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode2) {
        this.errorCode = errorCode2;
    }

    public boolean isLocal() {
        return this.local;
    }

    public void setLocal(boolean local2) {
        this.local = local2;
    }

    public long getCurPostion() {
        return this.curPostion;
    }

    public void setCurPostion(long curPostion2) {
        this.curPostion = curPostion2;
    }

    public int getBufferPercent() {
        return this.bufferPercent;
    }

    public void setBufferPercent(int bufferPercent2) {
        this.bufferPercent = bufferPercent2;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl2) {
        this.imgUrl = imgUrl2;
    }

    public String getHdImgUrl() {
        return this.hdImgUrl;
    }

    public void setHdImgUrl(String hdImgUrl2) {
        this.hdImgUrl = hdImgUrl2;
    }

    public String getLyric() {
        return this.lyric;
    }

    public void setLyric(String lyric2) {
        this.lyric = lyric2;
    }

    public String getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(String dataSource2) {
        this.dataSource = dataSource2;
    }

    public MusicType getMusicType() {
        return this.musicType;
    }

    public void setMusicType(MusicType musicType2) {
        this.musicType = musicType2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mediaId);
        dest.writeString(this.title);
        dest.writeString(this.artist);
        this.album.writeToParcel(dest, flags);
        this.announcer.writeToParcel(dest, flags);
        dest.writeString(this.url);
        dest.writeLong(this.size);
        dest.writeLong(this.time);
        dest.writeInt(this.errorCode);
        dest.writeBooleanArray(new boolean[]{this.local});
        dest.writeLong(this.curPostion);
        dest.writeInt(this.bufferPercent);
        dest.writeString(this.imgUrl);
        dest.writeString(this.hdImgUrl);
        dest.writeString(this.lyric);
        dest.writeString(this.dataSource);
        dest.writeInt(this.musicType.ordinal());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void readFromParcel(Parcel in) {
        this.mediaId = in.readString();
        this.title = in.readString();
        this.artist = in.readString();
        this.album = Album.CREATOR.createFromParcel(in);
        this.announcer = Announcer.CREATOR.createFromParcel(in);
        this.url = in.readString();
        this.size = in.readLong();
        this.time = in.readLong();
        this.errorCode = in.readInt();
        boolean[] temp = new boolean[1];
        in.readBooleanArray(temp);
        this.local = temp[0];
        this.curPostion = in.readLong();
        this.bufferPercent = in.readInt();
        this.imgUrl = in.readString();
        this.hdImgUrl = in.readString();
        this.lyric = in.readString();
        this.dataSource = in.readString();
        this.musicType = MusicType.values()[in.readInt()];
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Music [title=").append(this.title).append(", artist=").append(this.artist).append(", album=").append(this.album).append(", albumId=").append(this.album.getAlbumId()).append(", url=").append(this.url).append(", size=").append(this.size).append(", time=").append(this.time).append(", errorCode=").append(this.errorCode).append(", local=").append(this.local).append(", curPostion=").append(this.curPostion).append(", bufferPercent=").append(this.bufferPercent).append(", imgUrl=").append(this.imgUrl).append(", hdImgUrl=").append(this.hdImgUrl).append(", lyric=").append(this.lyric).append(", dataSource=").append(this.dataSource).append("]");
        return builder.toString();
    }
}

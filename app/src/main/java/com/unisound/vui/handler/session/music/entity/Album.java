package com.unisound.vui.handler.session.music.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

public class Album implements Parcelable {
    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        /* class com.unisound.vui.handler.session.music.entity.Album.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Album createFromParcel(Parcel source) {
            Album album = new Album();
            album.readFromParcel(source);
            return album;
        }

        @Override // android.os.Parcelable.Creator
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
    @SerializedName("albumId")
    @JSONField(name = "albumId")
    private String albumId;
    @SerializedName("albumLogo")
    @JSONField(name = "albumLogo")
    private String albumLogo;
    @SerializedName("albumName")
    @JSONField(name = "albumName")
    private String albumName;
    @SerializedName("description")
    @JSONField(name = "description")
    private String description;
    @SerializedName("musicCount")
    @JSONField(name = "musicCount")
    private int musicCount;
    @SerializedName("publishTime")
    @JSONField(name = "publishTime")
    private long publishTime;

    public Album() {
    }

    public Album(String albumName2, String albumId2, int musicCount2, String albumLogo2, String description2, long publishTime2) {
        this.albumName = albumName2;
        this.albumId = albumId2;
        this.musicCount = musicCount2;
        this.albumLogo = albumLogo2;
        this.description = description2;
        this.publishTime = publishTime2;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName2) {
        this.albumName = albumName2;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId2) {
        this.albumId = albumId2;
    }

    public int getMusicCount() {
        return this.musicCount;
    }

    public void setMusicCount(int musicCount2) {
        this.musicCount = musicCount2;
    }

    public String getAlbumLogo() {
        return this.albumLogo;
    }

    public void setAlbumLogo(String albumLogo2) {
        this.albumLogo = albumLogo2;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description2) {
        this.description = description2;
    }

    public long getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(long publishTime2) {
        this.publishTime = publishTime2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.albumName);
        dest.writeString(this.albumId);
        dest.writeInt(this.musicCount);
        dest.writeString(this.albumLogo);
        dest.writeString(this.description);
        dest.writeLong(this.publishTime);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void readFromParcel(Parcel in) {
        this.albumName = in.readString();
        this.albumId = in.readString();
        this.musicCount = in.readInt();
        this.albumLogo = in.readString();
        this.description = in.readString();
        this.publishTime = in.readLong();
    }

    public String toString() {
        return "Album [albumName=" + this.albumName + ", albumId=" + this.albumId + ", musicCount=" + this.musicCount + ", albumLogo=" + this.albumLogo + "description=" + this.description + ", publishTime=" + this.publishTime + "]";
    }
}

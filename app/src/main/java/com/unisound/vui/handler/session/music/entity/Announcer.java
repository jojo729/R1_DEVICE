package com.unisound.vui.handler.session.music.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

public class Announcer implements Parcelable {
    public static final Parcelable.Creator<Announcer> CREATOR = new Parcelable.Creator<Announcer>() {
        /* class com.unisound.vui.handler.session.music.entity.Announcer.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Announcer createFromParcel(Parcel source) {
            Announcer announcer = new Announcer();
            announcer.readFromParacel(source);
            return announcer;
        }

        @Override // android.os.Parcelable.Creator
        public Announcer[] newArray(int size) {
            return new Announcer[size];
        }
    };
    @SerializedName("announcerId")
    @JSONField(name = "announcerId")
    private long announcerId;
    @SerializedName("avatarUrl")
    @JSONField(name = "avatarUrl")
    private String avatarUrl;
    @SerializedName("nickname")
    @JSONField(name = "nickname")
    private String nickname;
    @SerializedName("verified")
    @JSONField(name = "verified")
    private boolean verified;

    public Announcer() {
    }

    public Announcer(long announcerId2, String nickname2, String avatarUrl2, boolean verified2) {
        this.announcerId = announcerId2;
        this.nickname = nickname2;
        this.avatarUrl = avatarUrl2;
        this.verified = verified2;
    }

    public long getAnnouncerId() {
        return this.announcerId;
    }

    public void setAnnouncerId(long announcerId2) {
        this.announcerId = announcerId2;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname2) {
        this.nickname = nickname2;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl2) {
        this.avatarUrl = avatarUrl2;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public void setVerified(boolean verified2) {
        this.verified = verified2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.announcerId);
        dest.writeString(this.nickname);
        dest.writeString(this.avatarUrl);
        dest.writeBooleanArray(new boolean[]{this.verified});
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void readFromParacel(Parcel in) {
        this.announcerId = in.readLong();
        this.nickname = in.readString();
        this.avatarUrl = in.readString();
        boolean[] temp = new boolean[1];
        in.readBooleanArray(temp);
        this.verified = temp[0];
    }

    public String toString() {
        return "Announcer [id=" + this.announcerId + ", nickname=" + this.nickname + ", avatarUrl=" + this.avatarUrl + ", isVerified=" + this.verified + "]";
    }
}

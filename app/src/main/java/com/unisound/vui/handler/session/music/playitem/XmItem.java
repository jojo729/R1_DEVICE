package com.unisound.vui.handler.session.music.playitem;

public class XmItem {
    private String album;
    private String artist;
    private int currentDuration = -1;
    private int duration = -1;
    private int opr = -1;
    private int playStatus = -1;
    private String title;

    public int getCurrentDuration() {
        return this.currentDuration;
    }

    public void setCurrentDuration(int currentDuration2) {
        this.currentDuration = currentDuration2;
    }

    public int getPlayStatus() {
        return this.playStatus;
    }

    public void setPlayStatus(int playStatus2) {
        this.playStatus = playStatus2;
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

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration2) {
        this.duration = duration2;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album2) {
        this.album = album2;
    }

    public int getOpr() {
        return this.opr;
    }

    public void setOpr(int opr2) {
        this.opr = opr2;
    }
}

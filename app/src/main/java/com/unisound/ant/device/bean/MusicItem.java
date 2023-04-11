package com.unisound.ant.device.bean;

public class MusicItem {
    private String album;
    private String artist;
    private String itemId;
    private int itemType;
    private String title;
    private String url;

    public MusicItem(String title2, String url2, String itemId2, int itemType2, String album2, String artist2) {
        this.title = title2;
        this.url = url2;
        this.itemId = itemId2;
        this.itemType = itemType2;
        this.album = album2;
        this.artist = artist2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url2) {
        this.url = url2;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId2) {
        this.itemId = itemId2;
    }

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int itemType2) {
        this.itemType = itemType2;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album2) {
        this.album = album2;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist2) {
        this.artist = artist2;
    }

    public String toString() {
        return "title=" + this.title + "&url=" + this.url + "&itemId=" + this.itemId + "&itemType=" + this.itemType + "&album=" + this.album + "&artist=" + this.artist;
    }
}

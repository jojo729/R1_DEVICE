package com.unisound.vui.common.media;

public interface PlayItem {

    public enum ItemType {
        TYPE_MUSIC,
        TYPE_AUDIO,
        TYPE_RADIO
    }

    String getAlbum();

    String getArtist();

    int getDuration();

    String getHdImgUrl();

    String getId();

    String getImgUrl();

    String getTitle();

    ItemType getType();

    String getUrl();

    String getmLyric();

    boolean isCollected();

    void setCollected(boolean z);
}

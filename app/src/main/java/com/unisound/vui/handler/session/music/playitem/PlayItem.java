package com.unisound.vui.handler.session.music.playitem;

public interface PlayItem {

    public enum ItemType {
        TYPE_MUSIC,
        TYPE_AUDIO,
        TYPE_RADIO,
        TYPE_NEWS,
        TYPE_BROADCAST
    }

    String getAlbum();

    String getArtist();

    int getDuration();

    String getHdImgUrl();

    String getId();

    String getImgUrl();

    String getListId();

    String getTitle();

    ItemType getType();

    String getUrl();

    String getmLyric();

    boolean isCollected();

    void setCollected(boolean z);

    void setListId(String str);
}

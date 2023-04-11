package com.unisound.vui.handler.session.music.playitem;

import com.unisound.vui.handler.session.music.playitem.PlayItem;
import nluparser.scheme.MusicResult;

/* access modifiers changed from: package-private */
public final class MusicItem implements PlayItem {
    private final MusicResult.Music music;

    MusicItem(MusicResult.Music music2) {
        this.music = music2;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getId() {
        return this.music.getId();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getListId() {
        return this.music.getMusicListId();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public void setListId(String listId) {
        this.music.setMusicListId(listId);
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getTitle() {
        return this.music.getTitle();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getArtist() {
        return this.music.getArtist();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getAlbum() {
        return this.music.getAlbum();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getUrl() {
        return this.music.getUrl();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public int getDuration() {
        return this.music.getDuration();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getImgUrl() {
        return this.music.getImgUrl();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getHdImgUrl() {
        return this.music.getHdImgUrl();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getmLyric() {
        return this.music.getmLyric();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public PlayItem.ItemType getType() {
        return PlayItem.ItemType.TYPE_MUSIC;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public boolean isCollected() {
        return this.music.isCollected();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public void setCollected(boolean isCollected) {
        this.music.setCollected(isCollected);
    }

    public String toString() {
        return "id=" + getId() + "&title=" + getTitle() + "&artist=" + getArtist() + "&album=" + getAlbum() + "&url=" + getUrl() + "&duration=" + getDuration() + "&imgUrl=" + getImgUrl() + "&hdImgUrl=" + getHdImgUrl() + "&mLyric=" + getmLyric() + "&isCollected=" + isCollected();
    }
}

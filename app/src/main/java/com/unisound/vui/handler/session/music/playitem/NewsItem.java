package com.unisound.vui.handler.session.music.playitem;

import com.unisound.vui.handler.session.music.playitem.PlayItem;
import nluparser.scheme.NewsResult;

public class NewsItem implements PlayItem {
    private final NewsResult.NewsBean newsBean;

    NewsItem(NewsResult.NewsBean newsBean2) {
        this.newsBean = newsBean2;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getId() {
        return this.newsBean.getId();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getListId() {
        return null;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public void setListId(String listId) {
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getTitle() {
        return null;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getArtist() {
        return null;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getAlbum() {
        return this.newsBean.getTitle();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getUrl() {
        return this.newsBean.getAudioUrl();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public int getDuration() {
        return (int) this.newsBean.getDuration();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getImgUrl() {
        return this.newsBean.getImageUrl();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getHdImgUrl() {
        return null;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getmLyric() {
        return null;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public PlayItem.ItemType getType() {
        return PlayItem.ItemType.TYPE_NEWS;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public void setCollected(boolean isCollected) {
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public boolean isCollected() {
        return false;
    }

    public String toString() {
        return "NewsItem{newsBean=" + this.newsBean + '}';
    }
}

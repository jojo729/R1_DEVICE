package com.unisound.vui.handler.session.music.playitem;

import com.unisound.vui.handler.session.music.playitem.PlayItem;
import nluparser.scheme.BroadcastResult;

public class BroadcastItem implements PlayItem {
    private final BroadcastResult broadcastResult;

    public BroadcastItem(BroadcastResult broadcastResult2) {
        this.broadcastResult = broadcastResult2;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getId() {
        return String.valueOf(this.broadcastResult.getChannelId());
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
        return this.broadcastResult.getTitle();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getArtist() {
        return null;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getAlbum() {
        return null;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getUrl() {
        return this.broadcastResult.getRate64TsUrl();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public int getDuration() {
        return this.broadcastResult.getTotalTime();
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public String getImgUrl() {
        return null;
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
        return PlayItem.ItemType.TYPE_BROADCAST;
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public void setCollected(boolean isCollected) {
    }

    @Override // com.unisound.vui.handler.session.music.playitem.PlayItem
    public boolean isCollected() {
        return false;
    }
}

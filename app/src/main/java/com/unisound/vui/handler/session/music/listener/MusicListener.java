package com.unisound.vui.handler.session.music.listener;

import com.unisound.vui.handler.session.music.playitem.PlayItem;
import java.util.List;

public interface MusicListener {
    void fireError(String str);

    void fireMusicChange();

    void fireMusicEnd(PlayItem playItem);

    void fireMusicListChanged(List<PlayItem> list);

    void fireMusicPrepare(PlayItem playItem, int i);

    void fireMusicStart(PlayItem playItem, int i);
}

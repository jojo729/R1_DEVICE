package com.unisound.vui.handler.session.music.listener;

public interface MusicStatusListener {
    void fireItemOperateCommand(int i);

    void firePlayModeChanged(String str);

    void fireStatusChanged(int i);
}

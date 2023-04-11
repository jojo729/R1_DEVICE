package com.unisound.vui.handler.session.music.syncloud;

import com.unisound.ant.device.bean.UnisoundDeviceCommand;

public interface MusicStatusListener {
    void onMusicStatusUpdate(String str, UnisoundDeviceCommand unisoundDeviceCommand);
}

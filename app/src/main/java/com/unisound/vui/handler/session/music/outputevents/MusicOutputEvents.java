package com.unisound.vui.handler.session.music.outputevents;

import com.unisound.vui.transport.out.OutputEvent;

public class MusicOutputEvents extends OutputEvent<Boolean> {
    public MusicOutputEvents(boolean isEntering) {
        super(1, Boolean.valueOf(isEntering));
    }
}

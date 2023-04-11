package com.unisound.vui.handler.session.music.outputevents;

import com.unisound.vui.transport.out.OutputEvent;

public class RequestListOutputEvent extends OutputEvent<Integer> {
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_MUSIC = 1;
    public static final int TYPE_NEWS = 3;

    public RequestListOutputEvent(int type, int page) {
        super(type, Integer.valueOf(page));
    }
}

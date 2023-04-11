package com.unisound.ant.device.event;

import com.unisound.vui.transport.out.OutputEvent;

public class TurnOffWakeLightEvent extends OutputEvent {
    private static final int TYPE_TURN_OFF_WAKE_LIGHT = 4;

    public TurnOffWakeLightEvent() {
        super(4);
    }
}

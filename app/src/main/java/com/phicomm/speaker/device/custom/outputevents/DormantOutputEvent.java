package com.phicomm.speaker.device.custom.outputevents;

import com.unisound.vui.transport.out.OutputEvent;

public class DormantOutputEvent extends OutputEvent<Boolean> {
    public DormantOutputEvent(Boolean data) {
        super(1, data);
    }
}

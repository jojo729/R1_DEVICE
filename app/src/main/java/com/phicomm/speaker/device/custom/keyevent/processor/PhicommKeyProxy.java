package com.phicomm.speaker.device.custom.keyevent.processor;

import android.content.Context;
import com.unisound.vui.engine.ANTEngine;

public class PhicommKeyProxy {
    private PhicommClickProcessor doubleClickProcessor;
    private PhicommClickProcessor longClickprocessor;
    private PhicommClickProcessor oneClickProcessor;
    private PhicommClickProcessor tripleClickProcessor;

    public PhicommKeyProxy(ANTEngine engine, Context context) {
        this.oneClickProcessor = new OneClickProcessor(engine, context);
        this.doubleClickProcessor = new DoubleClickProcessor(engine, context);
        this.tripleClickProcessor = new TripleClickProcessor(engine, context);
        this.longClickprocessor = new LongClickProcessor(engine, context);
    }

    public void onClick() {
        this.oneClickProcessor.onTriggered();
    }

    public void onDoubleClick() {
        this.doubleClickProcessor.onTriggered();
    }

    public void onTripleClick() {
        this.tripleClickProcessor.onTriggered();
    }

    public void onLongClick() {
        this.longClickprocessor.onTriggered();
    }
}

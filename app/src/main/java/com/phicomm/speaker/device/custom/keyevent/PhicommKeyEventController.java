package com.phicomm.speaker.device.custom.keyevent;

import android.content.Context;
import com.phicomm.speaker.device.custom.keyevent.processor.PhicommKeyProxy;
import com.phicomm.speaker.device.utils.LogUtils;
import com.unisound.vui.custom.event.interaction.key.KeyEventController;
import com.unisound.vui.engine.ANTEngine;

public class PhicommKeyEventController implements KeyEventController {
    private static final String TAG = PhicommKeyEventController.class.getSimpleName();
    private PhicommKeyProxy keyProxy;

    public PhicommKeyEventController(ANTEngine engine, Context context) {
        this.keyProxy = new PhicommKeyProxy(engine, context);
    }

    @Override // com.unisound.vui.custom.event.interaction.key.KeyEventController
    public void onClickEvent() {
        LogUtils.d(TAG, "--------------------onClickEvent-----------------");
        this.keyProxy.onClick();
    }

    @Override // com.unisound.vui.custom.event.interaction.key.KeyEventController
    public void onDoubleClickEvent() {
        LogUtils.d(TAG, "--------------------onDoubleClickEvent-----------------");
        this.keyProxy.onDoubleClick();
    }

    @Override // com.unisound.vui.custom.event.interaction.key.KeyEventController
    public void onTripleClickEvent() {
        LogUtils.d(TAG, "--------------------onTripleClickEvent-----------------");
        this.keyProxy.onTripleClick();
    }

    @Override // com.unisound.vui.custom.event.interaction.key.KeyEventController
    public void onLongClickEvent() {
        LogUtils.d(TAG, "--------------------onLongClickEvent-----------------");
        this.keyProxy.onLongClick();
    }
}

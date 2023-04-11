package com.unisound.vui.custom.event.interaction.key;

import com.unisound.vui.custom.event.interaction.key.KeyEventController;

public abstract class AbsKeyEventProcessor {
    private KeyEventController mController;

    public AbsKeyEventProcessor(KeyEventController controller) {
        this.mController = controller;
    }

    /* access modifiers changed from: protected */
    public void onClick() {
        this.mController.onClickEvent();
    }

    /* access modifiers changed from: protected */
    public void onDoubleClick() {
        this.mController.onDoubleClickEvent();
    }

    /* access modifiers changed from: protected */
    public void onTripleClick() {
        this.mController.onTripleClickEvent();
    }

    /* access modifiers changed from: protected */
    public void onLongClick() {
        this.mController.onLongClickEvent();
    }
}

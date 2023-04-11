package com.phicomm.speaker.device.custom.handler;

import android.content.Context;
import com.phicomm.speaker.device.custom.ipc.PhicommLightController;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.session.memo.DefaultReminderHandler;

public class PhicommReminderHandler extends DefaultReminderHandler {
    private PhicommLightController mLightController;

    public PhicommReminderHandler(Context context) {
        super(context);
        this.mLightController = new PhicommLightController(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.session.memo.AbstractMemoHandler, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (!this.eventReceived) {
            return false;
        }
        if (!ctx.hasAttr(NEED_SUPPLEMENT) || !((Boolean) ctx.attr(NEED_SUPPLEMENT).getAndRemove()).booleanValue()) {
            return super.onTTSEventPlayingEnd(ctx);
        }
        reset();
        ctx.enterASR();
        this.mLightController.turnOnWakeupLastLight();
        return false;
    }
}

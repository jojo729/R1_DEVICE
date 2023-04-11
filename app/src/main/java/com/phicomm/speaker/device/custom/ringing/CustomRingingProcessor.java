package com.phicomm.speaker.device.custom.ringing;

import com.phicomm.speaker.device.custom.api.CustomApiListener;
import com.unisound.vui.handler.session.memo.DefaultMemoRingingHandler;

public class CustomRingingProcessor implements CustomApiListener {
    private DefaultMemoRingingHandler mRingingHandler;

    public CustomRingingProcessor(DefaultMemoRingingHandler ringingHandler) {
        this.mRingingHandler = ringingHandler;
    }

    @Override // com.phicomm.speaker.device.custom.api.CustomApiListener
    public void onCustomEvent(int type, Object data) {
        switch (type) {
            case 1:
                this.mRingingHandler.onRingAudition((String) data);
                return;
            default:
                return;
        }
    }
}

package com.phicomm.speaker.device.custom.keyevent;

import android.content.Context;
import android.os.MessageDispatchManager;
import com.phicomm.speaker.device.utils.PhicommMessageManager;
import com.unisound.vui.custom.event.interaction.key.AbsKeyEventProcessor;
import com.unisound.vui.custom.event.interaction.key.KeyEventController;

public class PhicommKeyEventProcessor extends AbsKeyEventProcessor implements MessageDispatchManager.MessageReceiver {
    private static final String TAG = PhicommKeyEventProcessor.class.getSimpleName();
    public static final int TYPE_CLICK = 1;
    public static final int TYPE_DOUBLE_CLICK = 2;
    public static final int TYPE_LONG_CLICK = 5;
    public static final int TYPE_TRIPLE_CLICK = 3;
    private MessageDispatchManager mMsgCenter;

    public PhicommKeyEventProcessor(KeyEventController controller, Context context) {
        super(controller);
        this.mMsgCenter = PhicommMessageManager.messageCenter(context);
    }

    public void register() {
        this.mMsgCenter.registerMessageReceiver(this, 256);
    }

    public void unregister() {
        this.mMsgCenter.unregisterMessageReceiver(this);
    }

    @Override // android.os.MessageDispatchManager.MessageReceiver
    public void notifyMsg(int type, int arg1, int arg2, Object o) {
        switch (arg1) {
            case 1:
                onClick();
                return;
            case 2:
                onDoubleClick();
                return;
            case 3:
                onTripleClick();
                return;
            case 4:
            default:
                return;
            case 5:
                onLongClick();
                return;
        }
    }
}

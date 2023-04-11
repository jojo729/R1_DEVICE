package com.phicomm.speaker.device.custom.ipc;

import android.content.Context;
import android.os.MessageDispatchManager;
import com.phicomm.speaker.device.ipc.IpcReceiver;
import com.phicomm.speaker.device.ipc.IpcRegister;
import com.phicomm.speaker.device.utils.PhicommMessageManager;
import java.util.HashMap;
import java.util.Map;

public class PhicommIpcRegister implements IpcRegister {
    private MessageDispatchManager phicommMessageManager;
    private Map<IpcReceiver, MessageDispatchManager.MessageReceiver> receiverMap = new HashMap();

    public PhicommIpcRegister(Context context) {
        this.phicommMessageManager = PhicommMessageManager.messageCenter(context);
    }

    @Override // com.phicomm.speaker.device.ipc.IpcRegister
    public void registerReceiver(IpcReceiver receiver, int domain) {
        MessageDispatchManager.MessageReceiver phicommReceiver = initPhicommReceiverByIpcReceiver(receiver);
        cacheReceiver(receiver, phicommReceiver);
        this.phicommMessageManager.registerMessageReceiver(phicommReceiver, domain);
    }

    @Override // com.phicomm.speaker.device.ipc.IpcRegister
    public void unRegisterReceiver(IpcReceiver receiver) {
        this.phicommMessageManager.unregisterMessageReceiver(this.receiverMap.get(receiver));
    }

    private MessageDispatchManager.MessageReceiver initPhicommReceiverByIpcReceiver(final IpcReceiver receiver) {
        return new MessageDispatchManager.MessageReceiver() {
            /* class com.phicomm.speaker.device.custom.ipc.PhicommIpcRegister.AnonymousClass1 */

            @Override // android.os.MessageDispatchManager.MessageReceiver
            public void notifyMsg(int what, int arg1, int arg2, Object obj) {
                receiver.onReceive(what, arg1, arg2, obj);
            }
        };
    }

    private void cacheReceiver(IpcReceiver receiver, MessageDispatchManager.MessageReceiver phicommReceiver) {
        if (!this.receiverMap.containsKey(receiver)) {
            this.receiverMap.put(receiver, phicommReceiver);
        }
    }
}

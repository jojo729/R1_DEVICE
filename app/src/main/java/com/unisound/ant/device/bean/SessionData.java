package com.unisound.ant.device.bean;

import com.unisound.ant.device.profile.DstServiceProfile;
import com.unisound.ant.device.service.ActionResponse;
import com.unisound.ant.device.sessionlayer.DialogProfile;

public class SessionData<T> {
    private ActionResponse actionResponse;
    private DialogProfile dialog;
    private DstServiceProfile<T> dstService;

    public ActionResponse getActionResponse() {
        return this.actionResponse;
    }

    public void setActionResponse(ActionResponse actionResponse2) {
        this.actionResponse = actionResponse2;
    }

    public DialogProfile getDialog() {
        return this.dialog;
    }

    public void setDialog(DialogProfile dialog2) {
        this.dialog = dialog2;
    }

    public DstServiceProfile<T> getDstService() {
        return this.dstService;
    }

    public void setDstService(DstServiceProfile<T> dstService2) {
        this.dstService = dstService2;
    }
}

package com.phicomm.speaker.device.setting;

import android.content.Context;
import android.os.PowerManager;
import com.unisound.vui.handler.session.setting.SettingInterface;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.entity.CommandInfo;

public class DealSettingMessage implements SettingInterface {
    private static final String TAG = "DealSettingMessage";
    private static DealSettingMessage dealSettingMessage;
    private Context context;
    private PowerManager mPM = null;

    private DealSettingMessage(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public static void init(Context context2) {
        if (dealSettingMessage == null) {
            dealSettingMessage = new DealSettingMessage(context2);
        }
    }

    public static DealSettingMessage getInstance() {
        return dealSettingMessage;
    }

    @Override // com.unisound.vui.handler.session.setting.SettingInterface
    public void dealMessage(Object object) {
        if (object != null && (object instanceof CommandInfo)) {
            LogMgr.d(TAG, "handleControlWakeup cmdInfo.getCommand()=" + ((CommandInfo) object).getCommand());
        }
    }
}

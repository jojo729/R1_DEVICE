package com.unisound.ant.platform.smarthome;

import com.unisound.ant.platform.smarthome.bean.SmartAction;
import com.unisound.ant.platform.smarthome.bean.SmartDeviceStatus;
import com.unisound.ant.platform.smarthome.bean.SmartStateParamter;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.util.LogMgr;
import nluparser.scheme.*;

import java.util.List;

public class SmartExecutor {
    public static final String COMMAND_EXECUTE_FAIL = "fail";
    public static final String COMMAND_EXECUTE_OK = "ok";
    private static final String TAG = "SmartExecutor";
    private ExecutorCallBack callBack;
    private ANTHandlerContext ctx;

    public interface ExecutorCallBack {
        void onControlResult(String str, SmartDeviceStatus smartDeviceStatus);
    }

    public SmartExecutor(ANTHandlerContext ctx2) {
        this.ctx = ctx2;
    }

    public void setCallBack(ExecutorCallBack callBack2) {
        this.callBack = callBack2;
    }

    public void executeCommands(List<SmartAction> actions) {
        LogMgr.d(TAG, "executeCommands action size:" + actions.size());
        for (SmartAction action : actions) {
            executeCommand(action);
        }
    }

    public void executeCommand(SmartAction action) {
        LogMgr.d(TAG, "executeCommand action:" + action);
        if ("ACT_PLAY".equals(action.getOperator())) {
            List<MusicResult.Music> musics = action.getValue();
            if (musics != null) {
                NLU nlu = new NLU();
                nlu.setService(SName.MUSIC);
                nlu.setCode(SCode.SEARCH_ARTIST);
                MusicResult result = new MusicResult();
                result.setCount(musics.size());
                result.setMusicinfo(musics);
                Data data = new Data();
                data.setResult(result);
                nlu.setData(data);
                this.ctx.pipeline().fireUserEventTriggered(nlu);
                return;
            }
            return;
        }
        updateSmartDeviceState(action);
    }

    public void updateSmartDeviceState(SmartAction action) {
        if (this.callBack != null) {
            SmartDeviceStatus deviceStatus = new SmartDeviceStatus();
            deviceStatus.setDeviceCode(action.getDeviceCode());
            deviceStatus.setDeviceName(action.getDeviceExpr());
            deviceStatus.setRoomName(action.getRoom());
            deviceStatus.setDeviceType(action.getDeviceType());
            deviceStatus.setVendorName(action.getVendorName());
            SmartStateParamter paramter = new SmartStateParamter();
            if ("OBJ_AC".equals(action.getDeviceType())) {
                paramter.setObjAC(action.getOperator());
                paramter.setAttrTemperature("28度");
                paramter.setAttrMode("送风模式");
                paramter.setAttrWindDirection("西南风");
            } else if ("OBJ_LIGHT".equals(action.getDeviceType())) {
                paramter.setAttrBrightness("20");
                paramter.setAttrColor("red");
                paramter.setObjLight(action.getOperator());
            } else if ("OBJ_CURTAIN".equals(action.getDeviceType())) {
                paramter.setAttrStatus(action.getOperator());
                paramter.setObjCurtain(action.getDeviceType());
            }
            deviceStatus.setStateInfo(paramter);
            this.callBack.onControlResult("ok", deviceStatus);
        }
    }
}

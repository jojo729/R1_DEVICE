package com.unisound.ant.device.devicelayer.button;

import com.unisound.ant.device.devicelayer.SceneModeType;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.util.LogMgr;
import java.util.ArrayList;
import java.util.List;
import nluparser.scheme.NLU;
import nluparser.scheme.Operands;
import nluparser.scheme.Operator;
import nluparser.scheme.Result;
import nluparser.scheme.SCode;
import nluparser.scheme.SName;
import nluparser.scheme.SceneModeIntent;
import nluparser.scheme.Semantic;
import nluparser.scheme.SettingExtIntent;
import nluparser.scheme.SettingIntent;

public class ButtonControl {
    protected ANTHandlerContext ctx;

    public ButtonControl(ANTHandlerContext ctx2) {
        this.ctx = ctx2;
    }

    public void nextMusic() {
        NLU nlu = getControlNlu(Operator.ACT_NEXT, new String[0]);
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    public void prevMusic() {
        NLU nlu = getControlNlu(Operator.ACT_PREV, new String[0]);
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    public void pauseMusic() {
        NLU nlu = getControlNlu(Operator.ACT_PAUSE, new String[0]);
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    public void playMusic() {
        NLU nlu = getControlNlu("ACT_PLAY", new String[0]);
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    public void collectMusic(boolean isCollect) {
        NLU nlu;
        if (isCollect) {
            nlu = getControlNlu(Operator.ACT_BOOKMARK, new String[0]);
        } else {
            nlu = getControlNlu(Operator.ACT_BOOKMARK, Operator.ACT_CANCEL);
        }
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    public void switchMusicPlayMode(String playMode) {
        NLU<SettingExtIntent, Result> nlu = getControlNlu(Operator.ACT_SWITCH_PLAY_MODE, new String[0]);
        nlu.setText("切换播放模式");
        Semantic<SettingExtIntent> semantic = nlu.getSemantic();
        if (semantic == null) {
            semantic = new Semantic<>();
        }
        SettingExtIntent intent = semantic.getIntent();
        if (intent == null) {
            intent = new SettingExtIntent();
        }
        List<SettingIntent> list = intent.getOperations();
        if (list == null) {
            SettingIntent settingIntent = new SettingIntent();
            settingIntent.setValue(playMode);
            new ArrayList<>(1).add(settingIntent);
        } else {
            list.get(0).setValue(playMode);
        }
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    public void switchTo(String itemId) {
        NLU nlu = getControlNlu(Operator.ACT_SWITCH, itemId);
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    public void playMusicListWithIndex(String musicData) {
        NLU nlu = getControlNlu(Operator.ACT_PLAY_LIST, musicData);
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    public void playAudioListWithIndex(String audioListJson) {
        NLU nlu = getControlNlu(Operator.ACT_PLAY_AUDIO_LIST, audioListJson);
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    public void startForbidenMic(boolean isOpen) {
        LogMgr.d("startForbidenMic isOpen= " + isOpen);
        NLU nlu = new NLU();
        nlu.setText("禁用Mic");
        nlu.setService(SName.SCENE_MODE);
        Semantic semantic = new Semantic();
        SceneModeIntent intent = new SceneModeIntent();
        intent.setSceneName(SceneModeType.DEVICE_SCENEMODE_SLEEP);
        intent.setOpen(isOpen);
        semantic.setIntent(intent);
        nlu.setSemantic(semantic);
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    public void enterAlertMode(boolean isOpen) {
        NLU nlu = new NLU();
        nlu.setText("警戒模式");
        nlu.setService(SName.SCENE_MODE);
        Semantic semantic = new Semantic();
        SceneModeIntent intent = new SceneModeIntent();
        intent.setSceneName(SceneModeType.DEVICE_SCENEMODE_ALERT);
        intent.setOpen(isOpen);
        semantic.setIntent(intent);
        nlu.setSemantic(semantic);
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    public void enterNightMode() {
        NLU nlu = new NLU();
        nlu.setText("夜间模式");
        nlu.setService(SName.SCENE_MODE);
        Semantic semantic = new Semantic();
        SceneModeIntent intent = new SceneModeIntent();
        intent.setSceneName(SceneModeType.DEVICE_SCENEMODE_NIGHT);
        intent.setOpen(true);
        intent.setStartTime("23:00");
        intent.setStopTime("08:30");
        semantic.setIntent(intent);
        nlu.setSemantic(semantic);
        if (this.ctx != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
        } else {
            LogMgr.e("-->>ctx is null");
        }
    }

    private NLU<SettingExtIntent, Result> getControlNlu(String operatorAction, String... param) {
        NLU<SettingExtIntent, Result> nlu = new NLU<>();
        nlu.setText("上一首");
        nlu.setService(SName.SETTING_MP);
        nlu.setCode(SCode.SETTING_EXEC);
        Semantic<SettingExtIntent> semantic = new Semantic<>();
        SettingExtIntent intent = new SettingExtIntent();
        List<SettingIntent> operators = new ArrayList<>();
        SettingIntent operator = new SettingIntent();
        operator.setOperator(operatorAction);
        if (param != null && param.length >= 1) {
            operator.setConfirm(param[0]);
        }
        operator.setDeviceType(Operands.OBJ_MEDIA_PLAYER);
        operator.setOperands(Operands.ATTR_BUTTON);
        operators.add(operator);
        intent.setOperations(operators);
        semantic.setIntent(intent);
        nlu.setSemantic(semantic);
        return nlu;
    }
}

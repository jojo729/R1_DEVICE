package com.unisound.vui.handler.session.setting;

import android.os.Build;
import android.text.TextUtils;
import cn.yunzhisheng.common.PinyinConverter;
import com.phicomm.speaker.device.R;
import com.unisound.ant.device.controlor.DefaultVolumeOperator;
import com.unisound.ant.device.nlu.MusicNluCreator;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.util.LocalizeDataUtil;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.SpeakerTTSUtil;
import com.unisound.vui.util.UserPerferenceUtil;
import com.unisound.vui.util.entity.CommandInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import nluparser.scheme.GlobalCmdIntent;
import nluparser.scheme.Intent;
import nluparser.scheme.NLU;
import nluparser.scheme.Operands;
import nluparser.scheme.Operator;
import nluparser.scheme.Result;
import nluparser.scheme.SCode;
import nluparser.scheme.SName;
import nluparser.scheme.SettingCommonIntent;
import nluparser.scheme.SettingExtIntent;
import nluparser.scheme.SettingIntent;
import nluparser.scheme.Value;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class DefaultSettingHandler<I extends Intent> extends SimpleUserEventInboundHandler<NLU<Intent, Result.NullResult>> {
    private static final int ACT_DECREASE = 1;
    private static final int ACT_INCREASE = 0;
    private static final int ACT_MAX = 2;
    private static final int ACT_MIN = 3;
    public static final String ACT_PHICOMM_CLOSE_AMBIENTLIGHT = "ACT_PHICOMM_CLOSE_AMBIENTLIGHT";
    public static final String ACT_PHICOMM_CLOSE_SOUNDEFFECT = "ACT_PHICOMM_CLOSE_SOUNDEFFECT";
    public static final String ACT_PHICOMM_OPEN_AMBIENTLIGHT = "ACT_PHICOMM_OPEN_AMBIENTLIGHT";
    public static final String ACT_PHICOMM_OPEN_SOUNDEFFECT = "ACT_PHICOMM_OPEN_SOUNDEFFECT";
    private static final int ACT_SET = 4;
    private static final int ACT_UNSET = 5;
    private static final String DATA_TYPE_INT = "int";
    private static final String DATA_TYPE_PERCENT = "percent";
    private static final int GLOBAL_CMD = 2;
    private static final int HEALTH_INFO = 3;
    private static final String OBJ_AMBIENTLIGHT = "AmbientLight";
    private static final String OBJ_SOUNDEFFECT = "SoundEffect";
    private static final int SETTING = 0;
    private static final int SETTING_AIR = 4;
    private static final int SETTING_COMMON = 1;
    private static final String TAG = "DefaultSettingHandler";
    private boolean isGlobalCancel = false;
    private SettingHandler mSettingHandler;
    private String tempMainWakeup;
    private String tempSetOperands = "";
    private int type = -1;
    private HashMap<String, Integer> volumeOperation;
    private DefaultVolumeOperator volumeOperator;

    public DefaultSettingHandler(SettingHandler settingHandler) {
        this.mSettingHandler = settingHandler;
        this.volumeOperation = new HashMap<>();
        this.volumeOperation.put("ACT_INCREASE", 0);
        this.volumeOperation.put("ACT_DECREASE", 1);
        this.volumeOperation.put("ACT_MAX", 2);
        this.volumeOperation.put("ACT_MIN", 3);
        this.volumeOperation.put("ACT_SET", 4);
        this.volumeOperation.put("ACT_UNSET", 5);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void userEventTriggered(Object evt, ANTHandlerContext ctx) throws Exception {
        if ((evt instanceof Integer) && ((Integer) evt).intValue() == 3107) {
            ctx.playTTS(SpeakerTTSUtil.getTTSString(R.string.tts_wakeupword_set_fail, -1, ctx.androidContext()));
        }
        super.userEventTriggered(evt, ctx);
    }

    /* access modifiers changed from: protected */
    @Override
    public boolean acceptInboundEvent0(NLU<Intent, Result.NullResult> evt) throws Exception {
        LogMgr.i(TAG, "acceptInboundEvent0");
        this.type = -1;
        String service = evt.getService();
        if (SName.SETTING.equals(service)) {
            this.type = 0;
            return true;
        } else if (SName.SETTING_COMMON.equals(service)) {
            this.type = 1;
            return true;
        } else if (SName.GLOBAL_CMD.equals(service)) {
            this.type = 2;
            return true;
        } else if (SName.HEALTH_INFO.equals(service)) {
            this.type = 3;
            return true;
        } else if (!SName.SETTING_AIR.equals(service)) {
            return false;
        } else {
            this.type = 4;
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onWakeupEventSetWakeupwordDone(ANTHandlerContext ctx) {
        LogMgr.i(TAG, "onWakeupEventSetWakeupwordDone eventReceived:" + this.eventReceived);
        if (this.eventReceived && (Operands.ATTR_WAKEUP_WORD.equals(this.tempSetOperands) || Operands.ATTR_NAME.equals(this.tempSetOperands))) {
            this.tempSetOperands = "";
            if (!TextUtils.isEmpty(this.tempMainWakeup)) {
                ctx.playTTS(String.format(ctx.androidContext().getString(R.string.tts_wakeupword_set_success), this.tempMainWakeup));
                return true;
            }
        }
        return super.onWakeupEventSetWakeupwordDone(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void setDomainWakeUpWordList(ANTHandlerContext ctx) {
    }

    /* access modifiers changed from: protected */
    @Override
    public void eventReceived(NLU<Intent, Result.NullResult> evt, ANTHandlerContext ctx) throws Exception {
        super.eventReceived(evt, ctx);
        LogMgr.i(TAG, "eventReceived type = " + this.type + ",evt" + evt.toString());
        String ansewr = evt.getText();
        if (this.type == 1) {
            SettingIntent intent = getSettingIntent(evt);
            String value = intent.getValue();
            String operator = intent.getOperator();
            String operands = intent.getOperands();
            this.tempSetOperands = operands;
            if (SettingCommonIntent.Operands_Common.ATTR_VOLUME.equals(operands)) {
                String dataType = intent.getDatatype();
                if (!"ACT_SET".equals(operator)) {
                    int act = this.volumeOperation.get(operator).intValue();
                    if (act == 2) {
                        if (DefaultVolumeOperator.getInstance(ctx.androidContext()).isMaxMusicVolume()) {
                            ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_adjust_volume_sorry_max, -1, ctx.androidContext());
                        } else {
                            ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_adjust_volume_max, -1, ctx.androidContext());
                        }
                    } else if (act == 3) {
                        if (DefaultVolumeOperator.getInstance(ctx.androidContext()).isMinMusicVolume()) {
                            ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_adjust_volume_sorry_min, -1, ctx.androidContext());
                        } else {
                            ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_adjust_volume_min, -1, ctx.androidContext());
                        }
                    } else if (act == 0) {
                        if (DefaultVolumeOperator.getInstance(ctx.androidContext()).isMaxMusicVolume()) {
                            ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_adjust_volume_sorry_max, -1, ctx.androidContext());
                        } else {
                            ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_adjust_volume_increase, -1, ctx.androidContext());
                        }
                    } else if (act == 1) {
                        if (DefaultVolumeOperator.getInstance(ctx.androidContext()).isMinMusicVolume()) {
                            ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_adjust_volume_sorry_min, -1, ctx.androidContext());
                        } else {
                            ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_adjust_volume_decrease, -1, ctx.androidContext());
                        }
                    }
                    adjustVolume(act, -1);
                } else if (DATA_TYPE_PERCENT.equals(dataType) || DATA_TYPE_INT.equals(dataType)) {
                    int maxVolume = this.volumeOperator.getMaxVolume();
                    int volume = Math.min(Math.max(1, Math.round(((float) maxVolume) * (Float.parseFloat(value) / 100.0f))), maxVolume);
                    if (isVolumeValid(volume)) {
                        if (volume == 1) {
                            ansewr = ctx.androidContext().getString(R.string.tts_adjust_volume_min);
                        } else if (volume == maxVolume) {
                            ansewr = ctx.androidContext().getString(R.string.tts_adjust_volume_max);
                        } else {
                            ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_volume_set_ok, -1, ctx.androidContext());
                        }
                        adjustVolume(this.volumeOperation.get(operator).intValue(), volume);
                    } else {
                        ansewr = ctx.androidContext().getString(R.string.tts_cant_help_you);
                    }
                } else {
                    ansewr = ctx.androidContext().getString(R.string.tts_cant_help_you);
                }
            } else if (!"ATTR_MODE".equals(operands) || !SettingCommonIntent.Value_Common.MODE_MUTE.equals(value)) {
                if ("OBJ_LIGHT".equals(operands)) {
                    handlePhicommSmartHomeProtocal(evt, ansewr);
                    return;
                }
            } else if (this.volumeOperation.containsKey(operator)) {
                int volumeType = this.volumeOperation.get(operator).intValue();
                if (volumeType == 5) {
                    this.volumeOperator.setMuteOff();
                } else if (volumeType == 4) {
                    if (ansewr.contains(SpeakerTTSUtil.getTTSString(R.string.tts_volume_min, 0, ctx.androidContext()))) {
                        this.volumeOperator.setVolumeMin();
                        LogMgr.d("VolumeOpr", "--set volume min--");
                    } else {
                        this.volumeOperator.setMuteOn();
                    }
                }
            }
        } else if (this.type == 0) {
            SettingIntent settingIntent = getSettingIntent(evt);
            String operator2 = settingIntent.getOperator();
            String operands2 = settingIntent.getOperands();
            this.tempSetOperands = operands2;
            if (Operator.ACT_BOOKMARK.equals(operator2)) {
                evt.setService(SName.SETTING_MP);
                ctx.pipeline().fireUserEventTriggered(evt);
                exitAfterEnterOtherSession();
                return;
            } else if (SCode.SETTING_EXEC.equals(evt.getCode())) {
                String operate = settingIntent.getConfirm();
                if (TextUtils.isEmpty(operate)) {
                    operate = settingIntent.getOperator();
                }
                String asr = evt.getText();
                if (Operator.ACT_CANCEL.equals(operate)) {
                    if (!"取消".equals(asr)) {
                        ctx.pipeline().fireUserEventTriggered(MusicNluCreator.createMusicCloseNlu(asr));
                    }
                    this.isGlobalCancel = true;
                    ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_cancel, -1, ctx.androidContext());
                } else if ("ACT_OPEN".equals(operate) || "ACT_SET".equals(operate) || "ACT_CLOSE".equals(operate) || Operator.ACT_RESTART.equals(operate) || "ACT_UNSET".equals(operate) || "ACT_QUERY".equals(operate)) {
                    String deviceType = settingIntent.getDeviceType();
                    String value2 = settingIntent.getValue();
                    if (TextUtils.isEmpty(operands2)) {
                        operands2 = deviceType;
                    }
                    if (Operands.ATTR_WAKEUP_WORD.equals(operands2) || Operands.ATTR_NAME.equals(operands2)) {
                        LogMgr.d(TAG, "value origin =" + value2);
                        ansewr = checkWakeupAndAnswer(value2);
                        if (TextUtils.isEmpty(ansewr)) {
                            List<String> newWordList = new ArrayList<>();
                            newWordList.add(this.tempMainWakeup);
                            ctx.engine().stopWakeup();
                            ctx.engine().unsafe().updateCustomWakeupWord(newWordList);
                            LogMgr.d(TAG, "updateCustomWakeupWord : " + this.tempMainWakeup);
                            return;
                        }
                        this.tempSetOperands = "";
                    } else if (Operands.OBJ_WIFI.equals(operands2) || Operands.ATTR_ORDER_NO.equals(operands2) || Operands.ATTR_ROW_NO.equals(operands2) || Operands.ATTR_PAGE_NO.equals(operands2)) {
                        ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_unsupport, -1, ctx.androidContext());
                    } else if (isPhicommSmartHome(operands2, value2)) {
                        handlePhicommSmartHomeProtocal(evt, ansewr);
                        return;
                    } else if (Operands.OBJ_BLUETOOTH.equals(operands2)) {
                        if ("ACT_OPEN".equals(operate)) {
                            this.mSettingHandler.handleOrder(Operator.ACT_PHICOMM_OPEN_BLUETOOTH, evt);
                            return;
                        } else if ("ACT_CLOSE".equals(operate)) {
                            this.mSettingHandler.handleOrder(Operator.ACT_PHICOMM_CLOSE_BLUETOOTH, evt);
                            return;
                        } else {
                            return;
                        }
                    } else if (OBJ_AMBIENTLIGHT.equals(operands2)) {
                        if ("ACT_OPEN".equals(operate)) {
                            this.mSettingHandler.handleOrder(ACT_PHICOMM_OPEN_AMBIENTLIGHT, evt);
                            return;
                        } else if ("ACT_CLOSE".equals(operate)) {
                            this.mSettingHandler.handleOrder(ACT_PHICOMM_CLOSE_AMBIENTLIGHT, evt);
                            return;
                        } else {
                            return;
                        }
                    } else if (OBJ_SOUNDEFFECT.equals(operands2)) {
                        if ("ACT_OPEN".equals(operate)) {
                            this.mSettingHandler.handleOrder(ACT_PHICOMM_OPEN_SOUNDEFFECT, evt);
                            return;
                        } else if ("ACT_CLOSE".equals(operate)) {
                            this.mSettingHandler.handleOrder(ACT_PHICOMM_CLOSE_SOUNDEFFECT, evt);
                            return;
                        } else {
                            return;
                        }
                    } else if (Operands.OBJ_SLEEP_MODE.equals(operands2) && "ACT_OPEN".equals(operate)) {
                        this.mSettingHandler.handleOrder(Operator.ACT_PHICOMM_OPEN_SLEEP_MODE, evt);
                        return;
                    } else if (Operands.OBJ_TTS_MODLE.equals(operands2)) {
                        if (Operator.ACT_CHANGE.equals(operator2)) {
                            NLU nlu = new NLU();
                            nlu.setService(SName.SHOW_SETTING_CENTER);
                            nlu.setCode("SETTING_COMMAND_CHANGE_TTS_MODLE");
                            ctx.pipeline().fireUserEventTriggered(nlu);
                        }
                    } else if (Operands.OBJ_DIALOG.equals(operands2)) {
                        if ("ACT_CLOSE".equals(operate)) {
                            ctx.pipeline().fireUserEventTriggered(MusicNluCreator.createMusicCloseNlu(asr));
                            ansewr = ctx.androidContext().getString(R.string.tts_cancel);
                        } else {
                            ansewr = evt.getGeneral().getText();
                        }
                    } else if (("ACT_OPEN".equals(operate) || "ACT_CLOSE".equals(operate)) && !TextUtils.isEmpty(deviceType)) {
                        handlePhicommSmartHomeProtocal(evt, ansewr);
                        return;
                    } else if (TextUtils.isEmpty(deviceType) && fireToMusic(asr, operator2)) {
                        exitAfterEnterOtherSession();
                        return;
                    } else if (isGlobalCancelword(evt.getText())) {
                        this.isGlobalCancel = true;
                        ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_cancel, -1, ctx.androidContext());
                    } else if (evt.getGeneral() != null) {
                        ansewr = evt.getGeneral().getText();
                    }
                } else if (Operator.ACT_LAST.equals(operator2)) {
                    ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_unsupport, -1, ctx.androidContext());
                } else if (fireToMusic(asr, operator2)) {
                    exitAfterEnterOtherSession();
                    return;
                }
            } else if (SCode.SETTING_IOT.equals(evt.getCode())) {
                handlePhicommSmartHomeProtocal(evt, ansewr);
                return;
            } else if (SCode.USER_COMMAND.equals(evt.getCode())) {
                CommandInfo info = LocalizeDataUtil.getCommandInfo(evt.getText() != null ? evt.getText().replace(PinyinConverter.PINYIN_SEPARATOR, "") : "");
                if (info != null) {
                    if (Operator.ACT_QUESTION_VERSION.equals(info.getOperator())) {
                        ansewr = ctx.androidContext().getString(R.string.tts_device_current_version) + Build.FINGERPRINT.split(":")[1].split(MqttTopic.TOPIC_LEVEL_SEPARATOR)[2];
                    } else if (Operator.ACT_PHICOMM_VERSION.equals(info.getOperator()) || Operator.ACT_PHICOMM_OTA.equals(info.getOperator()) || Operator.ACT_PHICOMM_BACKUP.equals(info.getOperator())) {
                        this.mSettingHandler.handleOrder(info.getOperator());
                    } else if (Operator.ACT_PHICOMM_CHECK_ONLINE_STATE.equals(info.getOperator())) {
                        ansewr = UserPerferenceUtil.getDeviceOnlineState(ctx.androidContext()) ? SpeakerTTSUtil.getTTSString(R.string.tts_device_online, -1, ctx.androidContext()) : SpeakerTTSUtil.getTTSString(R.string.tts_device_offline, -1, ctx.androidContext());
                    }
                }
            } else if (SCode.QUERY_SETTING.equals(evt.getCode()) && Operands.ATTR_HOME_AIRQUALITY.equals(operands2)) {
                handlePhicommSmartHomeProtocal(evt, ansewr);
                return;
            }
        } else if (this.type == 2) {
            if (Operator.ACT_CANCEL.equals(((GlobalCmdIntent) evt.getSemantic().getIntent()).getConfirm())) {
                this.isGlobalCancel = true;
                ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_cancel, -1, ctx.androidContext());
            }
        } else if (this.type == 3 || this.type == 4) {
            handlePhicommSmartHomeProtocal(evt, ansewr);
            return;
        }
        if (TextUtils.isEmpty(ansewr)) {
            ansewr = SpeakerTTSUtil.getTTSString(R.string.tts_unsupport, -1, ctx.androidContext());
        }
        ctx.playTTS(ansewr);
        sendFullLogToDeviceCenter(evt, ansewr);
    }

    private void handlePhicommSmartHomeProtocal(NLU evt, String ansewr) {
        LogMgr.d(TAG, "handlePhicommSmartHomeProtocal: ");
        this.mSettingHandler.handleOrder(Operator.ACT_PHICOMM_SMART_HOME, evt);
    }

    private boolean isPhicommSmartHome(String operands, String value) {
        LogMgr.d(TAG, "isPhicommSmartHome: " + operands + "&" + value);
        if ("ATTR_MODE".equals(operands)) {
            if (Value.MODE_GUEST.equals(value) || Value.MODE_LEAVE_HOME.equals(value) || Value.MODE_GO_HOME.equals(value) || Value.MODE_MORNING.equals(value) || Value.MODE_SLEEP.equals(value) || Value.MODE_DINING.equals(value) || Value.MODE_MOVIE.equals(value)) {
                return true;
            }
        } else if ("OBJ_LIGHT".equals(operands) || "OBJ_LIGHT".equals(operands) || "OBJ_AC".equals(operands) || Operands.OBJ_FAN.equals(operands) || "OBJ_CURTAIN".equals(operands) || Operands.OBJ_HUMIDIFIER.equals(operands) || Operands.OBJ_DEHUMIDIFIER.equals(operands) || Operands.OBJ_WATER_DISPENSER.equals(operands) || Operands.OBJ_WATER_HEATER.equals(operands) || Operands.OBJ_FRIDGE.equals(operands) || Operands.OBJ_KITCHEN_VENTILAT.equals(operands) || Operands.OBJ_OUTLET.equals(operands) || Operands.OBJ_ROUTER.equals(operands) || Operands.ATTR_HOME_AIRQUALITY.equals(operands) || Operands.OBJ_AIR_CLEANER.equals(operands) || Operands.ATTR_WIND_SPEED.equals(operands)) {
            return true;
        }
        return false;
    }

    private boolean fireToMusic(String asr, String operator) {
        if (Operator.ACT_PAUSE.equals(operator)) {
            this.ctx.pipeline().fireUserEventTriggered(MusicNluCreator.createMusicPauseNlu(asr));
            return true;
        } else if (Operator.ACT_STOP.equals(operator)) {
            this.ctx.pipeline().fireUserEventTriggered(MusicNluCreator.createMusicStopNlu(asr));
            return true;
        } else if ("ACT_CLOSE".equals(operator)) {
            this.ctx.pipeline().fireUserEventTriggered(MusicNluCreator.createMusicCloseNlu(asr));
            return true;
        } else if (Operator.ACT_PREV.equals(operator)) {
            this.ctx.pipeline().fireUserEventTriggered(MusicNluCreator.createMusicPrevNlu(asr));
            return true;
        } else if (Operator.ACT_NEXT.equals(operator)) {
            this.ctx.pipeline().fireUserEventTriggered(MusicNluCreator.createMusicNextNlu(asr));
            return true;
        } else if (!Operator.ACT_RESUME.equals(operator)) {
            return false;
        } else {
            this.ctx.pipeline().fireUserEventTriggered(MusicNluCreator.createMusicPlayNlu(asr));
            return true;
        }
    }

    private SettingIntent getSettingIntent(NLU<Intent, Result.NullResult> evt) {
        SettingExtIntent settingExtIntent = null;
        if (evt.getSemantic() != null) {
            settingExtIntent = (SettingExtIntent) evt.getSemantic().getIntent();
        }
        if (settingExtIntent == null || settingExtIntent.getOperations() == null || !settingExtIntent.getOperations().iterator().hasNext()) {
            return (SettingIntent) evt.getSemantic().getIntent();
        }
        return settingExtIntent.getOperations().iterator().next();
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx, String interruptType) {
        LogMgr.d(TAG, "doInterrupt eventReceived=" + this.eventReceived);
        if (this.eventReceived) {
            interrupt(interruptType);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        this.volumeOperator = DefaultVolumeOperator.getInstance(ctx.androidContext());
        return super.onASREventEngineInitDone(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (!this.eventReceived) {
            return super.onTTSEventPlayingEnd(ctx);
        }
        exit(true);
        return true;
    }

    private void adjustVolume(int volumeSet, int volume) {
        switch (volumeSet) {
            case 0:
                if (volume != -1) {
                    this.volumeOperator.setVoiceVolume(volume);
                    return;
                } else {
                    this.volumeOperator.setVolumeRaise();
                    return;
                }
            case 1:
                if (volume != -1) {
                    this.volumeOperator.setVoiceVolume(volume);
                    return;
                } else {
                    this.volumeOperator.setVolumeLower();
                    return;
                }
            case 2:
                this.volumeOperator.setVolumeMax();
                return;
            case 3:
                this.volumeOperator.setVolumeMin();
                return;
            case 4:
                if (volume != -1) {
                    this.volumeOperator.setVoiceVolume(volume);
                    return;
                }
                return;
            case 5:
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void onDestroy(ANTHandlerContext ctx) {
        super.onDestroy(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void reset() {
        super.reset();
    }

    public void exit(boolean isResume) {
        this.ctx.cancelTTS();
        reset();
        if (isResume) {
            fireResume(this.ctx);
        }
        this.tempSetOperands = "";
        this.isGlobalCancel = false;
        this.type = -1;
    }

    private void exitAfterEnterOtherSession() {
        reset();
        this.tempSetOperands = "";
        this.isGlobalCancel = false;
        this.type = -1;
    }

    public void interrupt(String interruptType) {
        this.ctx.cancelTTS();
        this.isGlobalCancel = false;
        reset();
    }

    private boolean isVolumeValid(int volume) {
        return volume > 0 && volume <= this.volumeOperator.getMaxVolume();
    }

    /* access modifiers changed from: protected */
    @Override
    public String getInterruptType(NLU<Intent, Result.NullResult> evt) {
        boolean isCancelOperate = isCancelOperate(evt);
        LogMgr.d(TAG, "getInterruptType isCancelOperate:" + isCancelOperate);
        if (isCancelOperate) {
            return super.getInterruptType(evt);
        }
        return super.getInterruptType(evt);
    }

    private boolean isCancelOperate(NLU nlu) {
        SettingExtIntent settingExtIntent;
        List<SettingIntent> intents;
        SettingIntent intent;
        String serviceName = nlu.getService();
        if (SName.GLOBAL_CMD.equals(serviceName)) {
            if (Operator.ACT_CANCEL.equals(((GlobalCmdIntent) nlu.getSemantic().getIntent()).getConfirm())) {
                return true;
            }
        } else if (SName.SETTING.equals(serviceName) && (settingExtIntent = (SettingExtIntent) nlu.getSemantic().getIntent()) != null && (intents = settingExtIntent.getOperations()) != null && intents.size() > 0 && (intent = intents.get(0)) != null && Operator.ACT_CANCEL.equals(intent.getConfirm())) {
            return true;
        }
        return false;
    }

    private String checkWakeupAndAnswer(String wakeup) {
        if (TextUtils.isEmpty(wakeup)) {
            return SpeakerTTSUtil.getTTSString(R.string.tts_wakeupword_unsupport, -1, this.ctx.androidContext());
        }
        String wordToCheck = wakeup;
        String wakeupwordPrefix = SpeakerTTSUtil.getTTSString(R.string.tts_wakeupword_prefix, -1, this.ctx.androidContext());
        if (wordToCheck.startsWith(wakeupwordPrefix)) {
            wordToCheck = wordToCheck.substring(wakeupwordPrefix.length(), wordToCheck.length());
        }
        if (!matchWakeUpWord(wordToCheck)) {
            return SpeakerTTSUtil.getTTSString(R.string.tts_wakeupword_unsupport, -1, this.ctx.androidContext());
        }
        String wakeup2 = wakeupwordPrefix + wordToCheck;
        String nickName = UserPerferenceUtil.getDeviceNick(this.ctx.androidContext());
        LogMgr.d(TAG, "last nickName =" + nickName + " final newWord=" + wakeup2);
        if (wakeup2.equals(nickName) || UserPerferenceUtil.getWakeupWord(this.ctx.androidContext()).contains(wakeup2)) {
            return SpeakerTTSUtil.getTTSString(R.string.tts_wakeupword_duplicate, -1, this.ctx.androidContext());
        }
        UserPerferenceUtil.setDeviceNick(this.ctx.androidContext(), wakeup2);
        this.tempMainWakeup = wakeup2;
        return null;
    }

    public boolean matchWakeUpWord(String word) {
        return Pattern.compile("^[\\u4e00-\\u9fa5]{2,3}$").matcher(word).matches();
    }

    private boolean isGlobalCancelword(String recTxt) {
        String byeString = this.ctx.androidContext().getString(R.string.globle_cancel_bye);
        String exitString = this.ctx.androidContext().getString(R.string.globle_cancel_exit);
        if (recTxt.contains(byeString) || recTxt.contains(exitString)) {
            return true;
        }
        return false;
    }
}

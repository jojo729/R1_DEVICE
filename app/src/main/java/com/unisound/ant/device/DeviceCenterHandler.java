package com.unisound.ant.device;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import com.unisound.ant.device.bean.Accelerate;
import com.unisound.ant.device.bean.CommandOperate;
import com.unisound.ant.device.bean.DstServiceName;
import com.unisound.ant.device.bean.SessionData;
import com.unisound.ant.device.bean.VoiceChatMessage;
import com.unisound.ant.device.controlor.DefaultVolumeOperator;
import com.unisound.ant.device.devicelayer.DeviceStateMgr;
import com.unisound.ant.device.devicelayer.button.ButtonControl;
import com.unisound.ant.device.listener.MusicStatusListener;
import com.unisound.ant.device.listener.VoiceConnectStateListener;
import com.unisound.ant.device.mqtt.AliveTransportChannel;
import com.unisound.ant.device.mqtt.ChannelListener;
import com.unisound.ant.device.mqtt.OnMQTTStatusChangeListener;
import com.unisound.ant.device.mqtt.bean.ChannelParams;
import com.unisound.ant.device.mqtt.bean.LconInfo;
import com.unisound.ant.device.mqtt.bean.LconRequest;
import com.unisound.ant.device.mqtt.bean.ParamConfig;
import com.unisound.ant.device.netmodule.HttpReportUtils;
import com.unisound.ant.device.netmodule.NetChangeReceiver;
import com.unisound.ant.device.receiver.AutoLocationReceiver;
import com.unisound.ant.device.receiver.InstallBroadcastReceiver;
import com.unisound.ant.device.service.ActionResponse;
import com.unisound.ant.device.service.BaseRequest;
import com.unisound.ant.device.service.ServiceProtocolUtil;
import com.unisound.ant.device.sessionlayer.BaseSessionLayer;
import com.unisound.ant.device.sessionlayer.DialogProfile;
import com.unisound.ant.device.sessionlayer.SessionUpdateCallBack;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.common.location.bean.LocationInfo;
import com.unisound.vui.common.location.listener.LocationListener;
import com.unisound.vui.common.media.UniMediaPlayer;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.engine.ANTEngineConfig;
import com.unisound.vui.engine.ANTEngineOption;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.transport.config.RuntimeConfig;
import com.unisound.vui.util.AppGlobalConstant;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.NetworkConnectChangedReceiver;
import com.unisound.vui.util.SystemUitls;
import com.unisound.vui.util.UserPerferenceUtil;
import java.util.List;
import java.util.UUID;
import logreport.FullLog;
import nluparser.MixtureProcessor;
import nluparser.scheme.ASR;
import nluparser.scheme.AudioResult;
import nluparser.scheme.LocalASR;
import nluparser.scheme.Mixture;
import nluparser.scheme.MusicResult;
import nluparser.scheme.NLU;
import nluparser.scheme.Result;
import org.json.JSONObject;

public class DeviceCenterHandler extends SimpleUserEventInboundHandler implements ChannelListener, SessionUpdateCallBack, NetChangeReceiver.NetStateListener, VoiceConnectStateListener, LocationListener, MusicStatusListener, InstallBroadcastReceiver.InstallStateListener {
    private static final String TAG = "DeviceCenterHandler";
    private static ButtonControl buttonControlMusic;
    private static DeviceCenterHandler deviceCenterMgr;
    private AliveTransportChannel aliveTransportChannel;
    private AutoLocationReceiver autoLocationReceiver;
    private ANTHandlerContext ctx;
    private int delayTime = 0;
    private DeviceStateMgr deviceStateMgr;
    private Handler handler;
    private InstallBroadcastReceiver installBroadcastReceiver;
    private boolean isInSceneControl;
    private Context mContext;
    private LocationInfo mLocationInfo;
    private int mMusicStatus = -1;
    private String mUdid;
    private MixtureProcessor mixtureProcessor;
    private NetChangeReceiver netChangeReceiver;
    private OnMQTTStatusChangeListener phicommMQTTStatausChange;
    private BaseSessionLayer sessionLayer;
    private HandlerThread taskThread;
    private int utteranceTime = 0;

    public DeviceCenterHandler(Context context, OnMQTTStatusChangeListener onMQTTStatusChangeListener) {
        LogMgr.d(TAG, "-->>DeviceCenterHandler");
        this.taskThread = new HandlerThread("deviceCenterHandler");
        this.taskThread.start();
        this.mContext = context.getApplicationContext();
        this.phicommMQTTStatausChange = onMQTTStatusChangeListener;
        this.handler = new Handler(this.taskThread.getLooper());
        this.aliveTransportChannel = AliveTransportChannel.getChannelInstance();
        this.aliveTransportChannel.setChannelListener(this);
        this.netChangeReceiver = new NetChangeReceiver();
        this.netChangeReceiver.setStateListener(this);
        this.netChangeReceiver.registerNetStateReceiver(context);
        this.installBroadcastReceiver = new InstallBroadcastReceiver();
        this.installBroadcastReceiver.setStateListener(this);
        this.installBroadcastReceiver.registerInstallStateReceiver(context);
        this.sessionLayer = new BaseSessionLayer(context, this);
        this.deviceStateMgr = new DeviceStateMgr(context);
        this.autoLocationReceiver = new AutoLocationReceiver(context, this);
        this.autoLocationReceiver.startLocation();
    }

    public static void init(Context context, OnMQTTStatusChangeListener onMQTTStatusChangeListener) {
        if (deviceCenterMgr == null) {
            synchronized (DeviceCenterHandler.class) {
                if (deviceCenterMgr == null) {
                    deviceCenterMgr = new DeviceCenterHandler(context, onMQTTStatusChangeListener);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    @Override // com.unisound.vui.engine.ANTInboundHandlerAdapter, com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void userEventTriggered(final Object evt, ANTHandlerContext ctx2) throws Exception {
        if (ANTConfigPreference.isWakeupTest) {
            List<String> wakeupList = UserPerferenceUtil.getWakeupWord(this.mContext, true);
            if ((evt instanceof LocalASR) && wakeupList.contains(((LocalASR) evt).getRecognitionResult().replaceAll("\\s", ""))) {
                ctx2.enterWakeup(false);
                return;
            }
        }
        if (evt instanceof NLU) {
            this.handler.post(new Runnable() {
                /* class com.unisound.ant.device.DeviceCenterHandler.AnonymousClass1 */

                public void run() {
                    List<AudioResult.Music> musics;
                    Mixture mixture = DeviceCenterHandler.this.mixtureProcessor.from(((NLU) evt).getAsrResult());
                    if (mixture != null && mixture.getNluList() != null) {
                        List<NLU> nluList = mixture.getNluList();
                        if (nluList.size() <= 0) {
                            LogMgr.d(DeviceCenterHandler.TAG, "nluList size is 0");
                            return;
                        }else{
                            NLU reportNlu = nluList.get(0);
                            if (reportNlu.getData() != null && reportNlu.getData().getResult() != null) {
                                Result nluResult = reportNlu.getData().getResult();
                                if (nluResult instanceof MusicResult) {
                                    List<MusicResult.Music> musicinfos = ((MusicResult) nluResult).getMusicinfo();
                                    if (musicinfos != null && musicinfos.size() > 1) {
                                        MusicResult.Music music = musicinfos.get(0);
                                        musicinfos.clear();
                                        musicinfos.add(music);
                                    }
                                } else if ((nluResult instanceof AudioResult)) {
                                    final List<AudioResult.Music> playlist = ((AudioResult)nluResult).getPlaylist();
                                    if (playlist != null && playlist.size() > 1) {
                                        final AudioResult.Music audioResult = playlist.get(0);
                                        playlist.clear();
                                        playlist.add(audioResult);
                                    }

                                }
                            }
                        }

                    }
                }
            });
        }
        if (evt instanceof FullLog) {
            this.handler.post(new Runnable() {
                /* class com.unisound.ant.device.DeviceCenterHandler.AnonymousClass2 */

                @Override
                public void run() {
                    FullLog fullLog = (FullLog) evt;
                    Mixture mixtureData = DeviceCenterHandler.this.mixtureProcessor.from(fullLog.getNlu().getAsrResult());
                    String ttsData = fullLog.getTtsData().replaceAll("<py>[\\d|\\w]+</py>", "");
                    if (mixtureData != null && mixtureData.getLocalASRList() != null) {
                        mixtureData.setTtsDataValue(ttsData);
                        DeviceCenterHandler.this.reportLogToCloud(mixtureData, CommandOperate.CMD_VAL_SYNC_LOCAL_LOG);
                    } else if (mixtureData != null && mixtureData.getNluList() != null) {
                        mixtureData.setTtsDataValue(ttsData);
                        DeviceCenterHandler.this.reportLogToCloud(mixtureData, CommandOperate.CMD_VAL_SYNC_NET_LOG);
                    }
                }
            });
        }
        super.userEventTriggered(evt, ctx2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void reportLogToCloud(Mixture evt, String cmdValue) {
        VoiceChatMessage chatMessage = new VoiceChatMessage();
        chatMessage.setLogId(UUID.randomUUID().toString());
        chatMessage.setCreateTime(SystemUitls.getTime());
        chatMessage.setMsg(JsonTool.toJson(evt));
        this.deviceStateMgr.reportLocalAsrLog(cmdValue, chatMessage);
    }

    public static DeviceCenterHandler getDeviceCenterMgr() {
        if (deviceCenterMgr != null) {
            return deviceCenterMgr;
        }
        throw new IllegalArgumentException("please init deviceCenterMgr");
    }

    public DeviceCenterHandler associateEngine(ANTEngine engine, MixtureProcessor processor) {
        this.mixtureProcessor = processor;
        return deviceCenterMgr;
    }

    private void initHardware(ANTHandlerContext mCtx) {
        buttonControlMusic = new ButtonControl(mCtx);
        this.deviceStateMgr.bindAntHandlerContext(mCtx);
        checkNetState();
    }

    private void checkNetState() {
        LogMgr.d(TAG, "--->>initHardware current device isBinded:" + UserPerferenceUtil.getDeviceBindState(this.mContext));
        this.netChangeReceiver.registerNetReceiverAndCheck(this.mContext);
    }

    public static ButtonControl getButtonControler() {
        return buttonControlMusic;
    }

    public DeviceStateMgr getDeviceStateMgr() {
        return this.deviceStateMgr;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx2) {
        LogMgr.d(TAG, "-->>onASREventEngineInitDone");
        this.ctx = ctx2;
        this.mUdid = (String) ctx2.engine().config().getOption(ANTEngineOption.GENERAL_UDID);
        LogMgr.d(TAG, "engine init done, udid is " + this.mUdid);
        AppGlobalConstant.setUdid(this.mUdid);
        if (this.mLocationInfo != null) {
            updateLocation(this.mLocationInfo);
            this.mLocationInfo = null;
        }
        initHardware(ctx2);
        return super.onASREventEngineInitDone(ctx2);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTDuplexHandler
    public boolean onWakeupResult(ANTHandlerContext ctx2, String result) {
        try {
            JSONObject localAsr = (JSONObject) new JSONObject(result).getJSONArray(ASR.LOCAL_ASR).get(0);
            this.delayTime = localAsr.getInt("delayTime");
            this.utteranceTime = localAsr.getInt("utteranceTime");
        } catch (Exception e) {
            LogMgr.d(TAG, "exception:" + e);
        }
        if (UserPerferenceUtil.getNeedInstallUpdate(this.mContext)) {
            this.deviceStateMgr.startInstallAPK();
            return false;
        }
        LogMgr.d(TAG, "Continues onWakeupEventRecognitionSuccess");
        ctx2.engine().setWakeupRecord(false);
        if (ANTConfigPreference.isWakeupAecTest) {
            LogMgr.d(TAG, "mMusicStatus = " + this.mMusicStatus + " -->music_wakeup_test");
            if (this.mMusicStatus != 3) {
                return true;
            }
            ctx2.enterWakeup(false);
            return true;
        } else if (!ANTConfigPreference.isWakeupTest) {
            return false;
        } else {
            ctx2.enterWakeup(false);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTDuplexHandler
    public boolean onTTSEventSynthesizerStart(ANTHandlerContext ctx2) {
        LogMgr.d(TAG, "-->>onTTSEventSynthesizerStart");
        if (UniMediaPlayer.getInstance().isPlaying()) {
            UniMediaPlayer.getInstance().stop();
        }
        return super.onTTSEventSynthesizerStart(ctx2);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx2) {
        LogMgr.d(TAG, "-->>onTTSEventPlayingEnd ctx.engine().getEngineState=" + ctx2.engine().getEngineState());
        if (this.eventReceived) {
            exit(true);
        }
        return super.onTTSEventPlayingEnd(ctx2);
    }

    @Override // com.unisound.ant.device.mqtt.ChannelListener
    public void onChannelConnected() {
        LogMgr.d(TAG, "-->>onChannelConnected");
        this.deviceStateMgr.onTransportChannelConencted();
        this.mContext.sendBroadcast(new Intent(NetworkConnectChangedReceiver.NET_ALIVE_CONNECTED));
    }

    @Override // com.unisound.ant.device.mqtt.ChannelListener
    public void onChannelDisConnected() {
        LogMgr.d(TAG, "-->>onChannelConnected");
    }

    @Override // com.unisound.ant.device.mqtt.ChannelListener
    public void onSendDataResult(int state, String descript) {
    }

    @Override // com.unisound.ant.device.mqtt.ChannelListener
    public void onReceivedMsg(int state, String message) {
        LogMgr.d(TAG, "-->>onReceivedMsg state:" + state + ",message:" + message);
        if (this.sessionLayer == null) {
            throw new IllegalArgumentException("sessionLayer may be null.");
        }
        this.sessionLayer.filterSessionContent(message);
    }

    @Override // com.unisound.ant.device.mqtt.ChannelListener
    public void onChannelClose() {
        LogMgr.d(TAG, "-->>onChannelClose");
    }

    @Override // com.unisound.ant.device.netmodule.NetChangeReceiver.NetStateListener
    public void onNetConnecting() {
        LogMgr.d(TAG, "-->>onChannelClose");
    }

    @Override // com.unisound.ant.device.netmodule.NetChangeReceiver.NetStateListener
    public void onNetConnected() {
        LogMgr.d(TAG, "--->>onNetConnected aliveTransportChannel:" + this.aliveTransportChannel);
        RuntimeConfig.setOnceConnectSuc(true);
        LogMgr.d(TAG, "current wifi is connected:" + RuntimeConfig.isConnectedWifi());
        if (this.aliveTransportChannel != null) {
            initLconParam(this.mContext);
            ChannelParams params = new ChannelParams(this.mContext, AppGlobalConstant.getUdid());
            params.setAppKey(ExoConstants.APP_KEY);
            params.setAppSecret(ExoConstants.APP_SECRET);
            this.aliveTransportChannel.openChannel(this.mContext, params, this.phicommMQTTStatausChange);
        }
        this.deviceStateMgr.onNetConnectedSuccess();
    }

    private void initLconParam(Context context) {
        LconRequest<LconInfo> request = new LconRequest<>();
        request.setCommand("queryLongConnInfo");
        request.setVersion(SystemUitls.getAppVersion(context));
        request.setTcl(new LconRequest.EffectiveToken(SystemUitls.getIMEI(context), ""));
        LconInfo info = new LconInfo();
        info.setAppKey(ExoConstants.APP_KEY);
        info.setDeviceType(ParamConfig.DEVICE_TYPE_GENERAL);
        info.setUdid(AppGlobalConstant.getUdid());
        request.setData(info);
        ParamConfig.setLconRequest(request);
    }

    @Override // com.unisound.ant.device.netmodule.NetChangeReceiver.NetStateListener
    public void onNetDisconnected() {
        LogMgr.d(TAG, "--->>onNetDisconnected");
        if (this.aliveTransportChannel != null) {
            this.aliveTransportChannel.closeChannel();
        }
    }

    @Override // com.unisound.ant.device.sessionlayer.SessionUpdateCallBack
    public void onSessionDataUpdate(String messageType, SessionData sessionData) {

        LogMgr.d(TAG, "取消上报会话信息");
//        BaseRequest<SessionData> reqContent = ServiceProtocolUtil.getReqContent(messageType, sessionData);
//        if (isMusicData(sessionData)) {
//            HttpReportUtils.httpReportMusicInfo(this.mUdid, reqContent);
//        } else if (isASRData(sessionData)) {
//            HttpReportUtils.httpReportASRLog(this.mUdid, reqContent);
//        } else {
//            String statusJson = JsonTool.toJson(reqContent);
//            LogMgr.d(TAG, "send to server data:" + statusJson);
//            if (this.aliveTransportChannel == null || statusJson == null) {
//                LogMgr.e(TAG, "---->>onSessionDataUpdate aliveTransportChannel is null");
//            } else {
//                this.aliveTransportChannel.sendData(statusJson);
//            }
//        }
    }

    private boolean isMusicData(SessionData data) {
        String service = data.getDialog().getDstService();
        return DstServiceName.DST_SERVICE_MUSIC.equals(service) || DstServiceName.DST_SERVICE_AUDIO.equals(service) || DstServiceName.DST_SERVICE_NEWS.equals(service);
    }

    private boolean isASRData(SessionData data) {
        Accelerate accelerate = data.getDstService().getAccelerate();
        return accelerate != null && (CommandOperate.CMD_VAL_SYNC_NET_LOG.equals(accelerate.getValuse()) || CommandOperate.CMD_VAL_SYNC_LOCAL_LOG.equals(accelerate.getValuse()));
    }

    @Override // com.unisound.ant.device.sessionlayer.SessionUpdateCallBack
    public void onCloudSessionResponse(ActionResponse actionReponse) {
    }

    @Override // com.unisound.ant.device.sessionlayer.SessionUpdateCallBack
    public void onSessionToSceneControl(String sceneFlag) {
        if ("start".equals(sceneFlag) || DialogProfile.SCENE_FLAG_PROCESSING.equals(sceneFlag)) {
            this.isInSceneControl = true;
        } else if ("end".equals(sceneFlag)) {
            this.isInSceneControl = false;
        }
    }

    @Override // com.unisound.ant.device.listener.VoiceConnectStateListener
    public void onStartVoiceConnect() {
        this.netChangeReceiver.unregisterNetStateReceiver(this.mContext);
    }

    @Override // com.unisound.ant.device.listener.VoiceConnectStateListener
    public void onVoiceConnectSuccess() {
        LogMgr.d(TAG, "--->>onVoiceConnectSuccess");
        this.netChangeReceiver.registerNetReceiverAndCheck(this.mContext);
    }

    @Override // com.unisound.ant.device.listener.VoiceConnectStateListener
    public void onVoiceConnectFailStart() {
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx2, String interruptType) {
        if (this.eventReceived && UniMediaPlayer.getInstance().isPlaying()) {
            UniMediaPlayer.getInstance().stop();
        }
    }

    @Override // com.unisound.ant.device.listener.VoiceConnectStateListener
    public void onVoiceConnectFailEnd() {
    }

    public void exit(boolean fireResume) {
        this.ctx.cancelTTS();
        reset();
        if (fireResume) {
            fireResume(this.ctx);
        }
    }

    @Override // com.unisound.ant.device.listener.MusicStatusListener
    public void onMusicStatusChanged(int status) {
        float currentBenchMark = getEffectBenchmark();
        LogMgr.d(TAG, "onMusicStatusChanged status:" + status + ",currentBenchMark" + currentBenchMark);
        ANTConfigPreference.effectWakeupBenchmark = currentBenchMark;
        this.mMusicStatus = status;
    }

    private float getCurrentMusicWakeupBenchmark() {
        int currentVolume = DefaultVolumeOperator.getInstance(this.mContext).getCurrentVolume();
        LogMgr.d(TAG, "onMusicStatusChanged music playing  volume:" + currentVolume);
        if (currentVolume < 8) {
            return ANTConfigPreference.wakeupBenchmark;
        }
        if (currentVolume < 10) {
            return ANTConfigPreference.wakeupBenchmarkForMusicPlaying;
        }
        if (currentVolume < 12) {
            return ANTConfigPreference.wakeupBenchmarkForMusicPlayingTwo;
        }
        return ANTConfigPreference.wakeupBenchmarkForMusicPlayingThree;
    }

    private float getEffectBenchmark() {
        if (this.mMusicStatus == 3) {
            return getCurrentMusicWakeupBenchmark();
        }
        return ANTConfigPreference.wakeupBenchmark;
    }

    @Override // com.unisound.ant.device.receiver.InstallBroadcastReceiver.InstallStateListener
    public void onInstallFailed() {
        LogMgr.d(TAG, "onInstallFailed()");
        this.ctx.enterWakeup(false);
    }

    @Override // com.unisound.vui.common.location.listener.LocationListener
    public void onLocationSuccess(LocationInfo locationInfo) {
        LogMgr.d(TAG, "onLocationChange info:" + locationInfo);
        if (this.ctx != null) {
            LogMgr.d(TAG, "ctx != null, update location info");
            updateLocation(locationInfo);
            this.autoLocationReceiver.onDestory();
            this.mLocationInfo = null;
            return;
        }
        LogMgr.e(TAG, "onLocationChange ANTEngineConfig ctx is null");
        this.mLocationInfo = locationInfo;
    }

    private void updateLocation(LocationInfo info) {
        ANTEngineConfig config = this.ctx.engine().config();
        config.setOption(ANTEngineOption.GENERAL_GPS, info.getLatitude() + "," + info.getLongitude());
        config.setOption(ANTEngineOption.GENERAL_CITY, info.getCity());
    }

    @Override // com.unisound.vui.common.location.listener.LocationListener
    public void onLocationFail(String locationErrorMessage) {
        LogMgr.d(TAG, "onLocationFail : " + locationErrorMessage);
    }
}

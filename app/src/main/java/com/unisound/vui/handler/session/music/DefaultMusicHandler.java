package com.unisound.vui.handler.session.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.phicomm.speaker.device.R;
import com.unisound.ant.device.DeviceCenterHandler;
import com.unisound.ant.device.bean.AudioListResult;
import com.unisound.ant.device.bean.DeviceAudioData;
import com.unisound.ant.device.bean.DeviceMusicData;
import com.unisound.ant.device.bean.RemoteAudioInfo;
import com.unisound.ant.device.bean.RemoteMusicInfo;
import com.unisound.ant.device.bean.UnisoundDeviceCommand;
import com.unisound.ant.device.event.TurnOffWakeLightEvent;
import com.unisound.ant.device.netmodule.NetChangeReceiver;
import com.unisound.ant.device.netmodule.request.RequestBody;
import com.unisound.ant.device.nlu.MusicNluCreator;
import com.unisound.ant.device.nlu.SettingNluCreator;
import com.unisound.ant.device.sessionlayer.NluSessionLayer;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.handler.session.music.MusicService;
import com.unisound.vui.handler.session.music.PlayController;
import com.unisound.vui.handler.session.music.listener.MusicListenerWapper;
import com.unisound.vui.handler.session.music.network.Api;
import com.unisound.vui.handler.session.music.network.MusicConstants;
import com.unisound.vui.handler.session.music.network.body.AudioBody;
import com.unisound.vui.handler.session.music.outputevents.MusicOutputEvents;
import com.unisound.vui.handler.session.music.outputevents.RequestListOutputEvent;
import com.unisound.vui.handler.session.music.playitem.PlayItem;
import com.unisound.vui.handler.session.music.playitem.PlayItemAdapter;
import com.unisound.vui.handler.session.music.syncloud.MusicStateMgr;
import com.unisound.vui.transport.out.SimulateWakeupEvent;
import com.unisound.vui.util.AppGlobalConstant;
import com.unisound.vui.util.AttributeKey;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.SpeakerTTSUtil;
import com.unisound.vui.util.ThreadUtils;
import com.unisound.vui.util.UserPerferenceUtil;
import com.unisound.vui.util.internal.RandomHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import nluparser.scheme.AudioIntent;
import nluparser.scheme.AudioResult;
import nluparser.scheme.BroadcastIntent;
import nluparser.scheme.BroadcastResult;
import nluparser.scheme.Intent;
import nluparser.scheme.LocalASR;
import nluparser.scheme.Mixture;
import nluparser.scheme.MusicIntent;
import nluparser.scheme.MusicResult;
import nluparser.scheme.NLU;
import nluparser.scheme.NewsIntent;
import nluparser.scheme.NewsResult;
import nluparser.scheme.Operands;
import nluparser.scheme.Operator;
import nluparser.scheme.Result;
import nluparser.scheme.SCode;
import nluparser.scheme.SName;
import nluparser.scheme.SettingExtIntent;
import nluparser.scheme.SettingIntent;
import nluparser.scheme.SettingMPIntent;
import retrofit2.Response;

public class DefaultMusicHandler<I extends Intent, T extends Result> extends SimpleUserEventInboundHandler<NLU<I, T>> implements ServiceConnection, NetChangeReceiver.NetAliveConnectListener {
    private static final int MESSAGE_TYPE_NLU_UNIIOT = 1;
    private static final String PREFIX_TENCENT = "tc";
    public static final String QUERY_ALBUM_NAME = "QUERY_ALBUM_NAME";
    private static final String TAG = "DefaultMusicHandler";
    private static final String TYPE_COMMON = "common";
    private static final String TYPE_DOWNGRADE_SINGER = "downgrade_singer";
    private static final String TYPE_DOWNGRADE_SONG = "downgrade_song";
    private final AttributeKey<Boolean> ACT_BOOKMARK = AttributeKey.valueOf(DefaultMusicHandler.class, Operator.ACT_BOOKMARK);
    private final AttributeKey<Boolean> ACT_MODE_SET = AttributeKey.valueOf(DefaultMusicHandler.class, "ACT_MODE_SET");
    private final int ACT_PLAY_COLLECTION = 7;
    private final AttributeKey<Boolean> ACT_PLAY_COLLECT_LIST = AttributeKey.valueOf(DefaultMusicHandler.class, "ACT_PLAY_COLLECT_LIST");
    private final AttributeKey<Boolean> ACT_QUERY_DETAIL = AttributeKey.valueOf(DefaultMusicHandler.class, "ACT_QUERY_DETAIL");
    private final int ACT_SET = 5;
    private final int AUDIO = 2;
    private final int BOOKMARK = 6;
    private final int BROADCAST = 5;
    private final int CLOSE = 10;
    private final AttributeKey<Boolean> EMPTY_RESULT = AttributeKey.valueOf(DefaultMusicHandler.class, "EMPTY_RESULT");
    private final AttributeKey<Boolean> EXTRA_BUTTON_CONTROL = AttributeKey.valueOf(DefaultMusicHandler.class, "EXTRA_BUTTON_CONTROL");
    private final int MODE_ALL_REPEAT = 2;
    private final int MODE_LIST_REPEAT = 4;
    private final int MODE_ORDER = 1;
    private final int MODE_REPEAT_ONCE = 3;
    private final int MODE_SHUFFLE = 0;
    private final int MUSIC = 1;
    private final AttributeKey<Boolean> MUSIC_STATUS_PAUSE = AttributeKey.valueOf(DefaultMusicHandler.class, "MUSIC_STATUS_PAUSE");
    private final AttributeKey<Boolean> MUSIC_STATUS_STOP = AttributeKey.valueOf(DefaultMusicHandler.class, "MUSIC_STATUS_STOP");
    private final int NEWS = 4;
    private final int NEXT = 1;
    private final int PAUSE = 3;
    private final int PLAY = 2;
    private final int PLAY_AUDIO_LIST_WITH_INDEX = 14;
    private final int PLAY_MUSIC_LIST_WITH_INDEX = 13;
    private final int PREV = 0;
    private final int SETTING_MP = 3;
    private final int STOP = 4;
    private final int SWITCH_PLAY_MODE = 11;
    private final int SWITCH_TO = 12;
    private Context context;
    private List<String> currentWakeupList = new ArrayList();
    private String mAlbumId;
    private CommonPlayer mCommonPlayer;
    private NLU mNLU;
    private MusicStateMgr musicStateMgr;
    private NetChangeReceiver netChangeReceiver;
    protected ANTPlayController playController;
    private HashMap<String, Integer> playMode;
    private int serviceType = 0;
    private HashMap<String, Integer> settingMap;
    private Handler taskHandler = new Handler() {
        /* class com.unisound.vui.handler.session.music.DefaultMusicHandler.AnonymousClass3 */

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    DeviceCenterHandler.getDeviceCenterMgr().onReceivedMsg(2, (String) msg.obj);
                    return;
                default:
                    return;
            }
        }
    };
//
//    /* access modifiers changed from: protected */
//    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
//    public /* bridge */ /* synthetic */ void eventReceived(Object obj, ANTHandlerContext aNTHandlerContext) throws Exception {
//        eventReceived((NLU) ((NLU) obj), aNTHandlerContext);
//    }
//
//    /* access modifiers changed from: protected */
//    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
//    public /* bridge */ /* synthetic */ String getInterruptType(Object obj) {
//        return getInterruptType((NLU) ((NLU) obj));
//    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    public DefaultMusicHandler(CommonPlayer commonPlayer) {
        this.mCommonPlayer = commonPlayer;
        this.netChangeReceiver = new NetChangeReceiver();
        this.netChangeReceiver.registerNetStateReceiver(AppGlobalConstant.getContext());
        this.netChangeReceiver.setAliveConnectListener(this);
        this.settingMap = new HashMap<>();
        this.settingMap.put(Operator.ACT_PREV, 0);
        this.settingMap.put(Operator.ACT_NEXT, 1);
        this.settingMap.put("ACT_PLAY", 2);
        this.settingMap.put(Operator.ACT_PAUSE, 3);
        this.settingMap.put(Operator.ACT_STOP, 4);
        this.settingMap.put("ACT_SET", 5);
        this.settingMap.put(Operator.ACT_BOOKMARK, 6);
        this.settingMap.put("ACT_CLOSE", 10);
        this.settingMap.put("OBJ_FAV_MUSIC_LIST", 7);
        this.settingMap.put(Operator.ACT_SWITCH_PLAY_MODE, 11);
        this.settingMap.put(Operator.ACT_SWITCH, 12);
        this.settingMap.put(Operator.ACT_PLAY_LIST, 13);
        this.settingMap.put(Operator.ACT_PLAY_AUDIO_LIST, 14);
        this.playMode = new HashMap<>();
        this.playMode.put(SettingMPIntent.Value_MP.MODE_SHUFFLE, 0);
        this.playMode.put(SettingMPIntent.Value_MP.MODE_ORDER, 1);
        this.playMode.put(SettingMPIntent.Value_MP.MODE_ALL_REPEAT, 2);
        this.playMode.put(SettingMPIntent.Value_MP.MODE_PLAYLIST_REPEAT, 4);
        this.playMode.put(SettingMPIntent.Value_MP.MODE_REPEAT_ONCE, 3);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        LogMgr.d(TAG, "DefaultMusicHandler onASREventEngineInitDone :");
        this.context = ctx.androidContext();
        this.currentWakeupList = UserPerferenceUtil.getWakeupWord(ctx.androidContext());
        this.context.bindService(new android.content.Intent(this.context, MusicService.class), this, Context.BIND_AUTO_CREATE);
        this.musicStateMgr = new MusicStateMgr(ctx);
        return super.onASREventEngineInitDone(ctx);
    }

    /* access modifiers changed from: protected */
    @Override
    public boolean acceptInboundEvent0(NLU evt) throws Exception {
        String service = evt.getService();
        LogMgr.d(TAG, "acceptInboundEvent0 service:" + service);
        if (SName.BROADCAST.equals(service)) {
            setPriority(300);
            this.serviceType = 5;
            return true;
        } else if (SName.NEWS.equals(service)) {
            setPriority(300);
            this.serviceType = 4;
            return true;
        } else if (SName.MUSIC.equals(service)) {
            this.serviceType = 1;
            setPriority(300);
            return true;
        } else if (SName.AUDIO.equals(service)) {
            setPriority(300);
            this.serviceType = 2;
            return true;
        } else if (!SName.SETTING_MP.equals(service)) {
            return false;
        } else {
            setPriority(300);
            this.serviceType = 3;
            SettingExtIntent intent = (SettingExtIntent) evt.getSemantic().getIntent();
            if (intent.getOperations().iterator().hasNext() && Operands.ATTR_BUTTON.equals(intent.getOperations().iterator().next().getOperands())) {
                this.ctx.attr(this.EXTRA_BUTTON_CONTROL).set(true);
            }
            if (!isBtnControlled(this.ctx)) {
                return true;
            }
            this.ctx.pipeline().fireUserEventTriggered(new TurnOffWakeLightEvent());
            return true;
        }
    }

    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.engine.ANTInboundHandler, com.unisound.vui.engine.ANTInboundHandlerAdapter, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void userEventTriggered(Object evt, ANTHandlerContext ctx) throws Exception {
        if (evt instanceof LocalASR) {
            String wakeupWord = ((LocalASR) evt).getRecognitionResult().replaceAll("\\s", "");
            LogMgr.d(TAG, "-->>userEventTriggered wakeupWord:" + wakeupWord);
            if (isIntervalWakeupWord(wakeupWord)) {
                if (this.playController != null) {
                    String playContinue = this.context.getString(R.string.music_wakeup_play);
                    if (!this.playController.isPlaying()) {
                        if (!playContinue.equals(wakeupWord)) {
                            LogMgr.e(TAG, "current player is not playing and wkaeupControl is not effect");
                            return;
                        }
                    } else if (playContinue.equals(wakeupWord)) {
                        LogMgr.e(TAG, "current player is playing ");
                        return;
                    }
                }
                firNluMusicWakeupOperate(wakeupWord);
                return;
            }
        } else if (evt instanceof RequestListOutputEvent) {
            requestPlayingData(((RequestListOutputEvent) evt).getType(), ((Integer) ((RequestListOutputEvent) evt).getData()).intValue());
        }
        super.userEventTriggered(evt, ctx);
    }

    private void requestPlayingData(int type, int page) {
        LogMgr.d(TAG, "requestPlayingData() called with: type = [" + type + "], page = [" + page + "]");
        switch (type) {
            case 2:
                processAudioDataReq(page);
                return;
            case 3:
                processNewsDataReq(page);
                return;
            default:
                return;
        }
    }

    private void processAudioDataReq(final int page) {
        ThreadUtils.executeInSingle(new Runnable() {
            /* class com.unisound.vui.handler.session.music.DefaultMusicHandler.AnonymousClass1 */

            public void run() {
                Exception e;
                String detailInfo;
                if (!"audio".equals(DefaultMusicHandler.this.mCommonPlayer.getDevicePlayingType())) {
                    LogMgr.w(DefaultMusicHandler.TAG, "query audio list, but now isn't playing audio, ignore.");
                    return;
                }
                try {
                    RequestBody<AudioBody> body = new RequestBody<>();
                    try {
                        body.setBusinessType("audio");
                        body.setCommand(MusicConstants.Command.COMMAND_GET_AUDIO_LIST);
                        body.setTcl(new RequestBody.ClientInfo());
                        AudioBody audioBody = null;
                        if (DefaultMusicHandler.this.mNLU != null) {
                            NLU<AudioIntent, AudioResult> audios = DefaultMusicHandler.this.mNLU;
                            audioBody = new AudioBody(AppGlobalConstant.getUdid(), audios.getCode(), audios.getSemantic().getIntent().getKeyword(), String.valueOf(page));
                        } else if (DefaultMusicHandler.this.mAlbumId != null) {
                            audioBody = new AudioBody(AppGlobalConstant.getUdid(), DefaultMusicHandler.this.mAlbumId, String.valueOf(page));
                        }
                        body.setData(audioBody);
                        try {
                            LogMgr.d(DefaultMusicHandler.TAG, "query audio list, requestUrl = " + ANTConfigPreference.getAppServerUrl() + ", body is " + JsonTool.toJson(body));
                            Response<AudioListResult> response = Api.getMusicApi().getAudio(body).execute();
                            if (response.isSuccessful()) {
                                AudioListResult result = response.body();
                                if (result == null || result.getDetailInfo() == null || result.getStatus() != 200) {
                                    StringBuilder append = new StringBuilder().append("query audio list error: ");
                                    if (result == null) {
                                        detailInfo = "result is null";
                                    } else {
                                        detailInfo = result.getDetailInfo();
                                    }
                                    LogMgr.d(DefaultMusicHandler.TAG, append.append(detailInfo).toString());
                                    return;
                                }
                                DefaultMusicHandler.this.playController.appendPlaylist(page, result.getControlInfo().getPageCount(), PlayItemAdapter.adaptAudio(result.getControlInfo().getResult(), null, null));
                                return;
                            }
                            LogMgr.e(DefaultMusicHandler.TAG, "query audio list error, code = " + response.code());
                        } catch (IOException e2) {
                            LogMgr.e(DefaultMusicHandler.TAG, "query audio list error: " + e2.getMessage());
                        }
                    } catch (Exception e3) {
                        e = e3;
                        LogMgr.e(DefaultMusicHandler.TAG, "query audio list error : " + e.getMessage());
                    }
                } catch (Exception e4) {
                    e = e4;
                    LogMgr.e(DefaultMusicHandler.TAG, "query audio list error : " + e.getMessage());
                }
            }
        });
    }

    private void processNewsDataReq(int page) {
    }

    /* access modifiers changed from: protected */
    @Override
    public void eventReceived(NLU<I, T> evt, ANTHandlerContext ctx) throws Exception {
        List<AudioResult.Music> audioInfo;
        List<MusicResult.Music> musicinfo;
        LogMgr.d(TAG, "DefaultMusicHandler evt :" + evt);
        String prompt = "";
        switch (this.serviceType) {
            case 1:
                prompt = dealMusicPlayService((NLU<MusicIntent, MusicResult>) evt);
                LogMgr.d(TAG, "eventReceived: MUSIC" + prompt);
                break;
            case 2:
                saveAudioReqData(evt);
                prompt = dealAudioPlayService((NLU<AudioIntent, AudioResult>) evt);
                LogMgr.d(TAG, "eventReceived: AUDIO" + prompt);
                break;
            case 3:
                if (isPlayingNews() && isQueryDetailAction(evt.getCode())) {
                    prompt = this.context.getString(R.string.tts_music_change_no_supported);
                    break;
                } else {
                    prompt = queryDetail(evt.getCode());
                    if (TextUtils.isEmpty(prompt)) {
                        SettingExtIntent intent = (SettingExtIntent) evt.getSemantic().getIntent();
                        if (intent.getOperations().iterator().hasNext()) {
                            SettingIntent settingIntent = intent.getOperations().iterator().next();
                            if (Operands.OBJ_MEDIA_PLAYER.equals(settingIntent.getDeviceType()) && !TextUtils.isEmpty(settingIntent.getTime()) && !TextUtils.isEmpty(settingIntent.getAnchorTime()) && !TextUtils.isEmpty(settingIntent.getOffsetTime())) {
                                evt.setService("cn.yunzhisheng.music.schedule");
                                ctx.pipeline().fireUserEventTriggered(evt);
                                exitSession(false, false);
                                return;
                            }
                        }
                        if (evt.getGeneral() != null) {
                            intent.setVoiceTip(evt.getGeneral().getText());
                        }
                        if (evt.getIotUniJson() != null) {
                            RemoteMusicInfo remoteMusicInfo = getMusicInfo(evt.getIotUniJson().toString());
                            RemoteAudioInfo remoteAudioInfo = getAudioInfo(evt.getIotUniJson().toString());
                            if (!(remoteMusicInfo == null || (musicinfo = remoteMusicInfo.getMusicinfo()) == null)) {
                                if (musicinfo.size() != 0) {
                                    ctx.pipeline().write(new MusicOutputEvents(true));
                                    this.playController.setPlaylist(PlayItemAdapter.adaptMusic(reorderMusicList(musicinfo)));
                                    this.playController.play(true);
                                } else {
                                    ctx.attr(this.EMPTY_RESULT).set(true);
                                }
                                if (!TextUtils.isEmpty(remoteMusicInfo.getVoiceTip())) {
                                    intent.setVoiceTip(remoteMusicInfo.getVoiceTip());
                                }
                            }
                            if (!(remoteAudioInfo == null || (audioInfo = remoteAudioInfo.getAudioinfo()) == null)) {
                                if (audioInfo.size() != 0) {
                                    ctx.pipeline().write(new MusicOutputEvents(true));
                                    this.playController.setPlaylist(PlayItemAdapter.adaptAudio(reorderAudioList(audioInfo), null, null));
                                    this.playController.play(true);
                                } else {
                                    ctx.attr(this.EMPTY_RESULT).set(true);
                                }
                                if (!TextUtils.isEmpty(remoteAudioInfo.getVoiceTip())) {
                                    intent.setVoiceTip(remoteAudioInfo.getVoiceTip());
                                }
                            }
                        }
                        prompt = dealMediaSettingService(intent);
                        LogMgr.d(TAG, "eventReceived:SETTING_MP " + prompt);
                        break;
                    } else {
                        ctx.attr(this.ACT_QUERY_DETAIL).set(true);
                        break;
                    }
                }
            case 4:
                saveAudioReqData(evt);
                prompt = dealNewsPlayService((NLU<NewsIntent, NewsResult>) evt);
                break;
            case 5:
                prompt = dealBroadcastPlayService((NLU<BroadcastIntent, BroadcastResult>) evt);
                break;
            default:
                LogMgr.d(TAG, "eventReceived: default" + prompt);
                break;
        }
        if (TextUtils.isEmpty(prompt)) {
            LogMgr.d(TAG, "--->>eventReceived get prompt is null");
            if (!ctx.hasAttr(this.MUSIC_STATUS_PAUSE) || !((Boolean) ctx.attr(this.MUSIC_STATUS_PAUSE).get()).booleanValue()) {
                if (!isBtnControlled(ctx)) {
                    exitSession(false, true);
                } else {
                    exitSession(false, false);
                }
                ctx.enterWakeup(false);
            } else {
                exitSession(false, false);
                ctx.enterWakeup(false);
            }
            ctx.pipeline().fireTTSEvent(2107);
            return;
        }
        if (hasNoSongResult(prompt)) {
            ctx.attr(this.EMPTY_RESULT).set(true);
        }
        ctx.playTTS(prompt);
        compressLogContent(evt);
        sendFullLogToDeviceCenter(evt, prompt);
    }

    private void compressLogContent(final NLU<I, T> nlu) {
        if(nlu.getData()!=null){
            switch (this.serviceType) {
                case 2: {
                    final AudioResult audioResult = (AudioResult)nlu.getData().getResult();
                    if (audioResult == null) {
                        break;
                    }
                    final List<AudioResult.Music> playlist = audioResult.getPlaylist();
                    if (playlist != null && playlist.size() > 1) {
                        final AudioResult.Music audioResult$Music = playlist.get(0);
                        playlist.clear();
                        playlist.add(audioResult$Music);
                        break;
                    }
                    break;
                }
                case 1: {
                    final MusicResult musicResult = (MusicResult)nlu.getData().getResult();
                    if (musicResult == null) {
                        break;
                    }
                    final List<MusicResult.Music> musicinfo = musicResult.getMusicinfo();
                    if (musicinfo != null && musicinfo.size() > 1) {
                        final MusicResult.Music music = musicinfo.get(0);
                        musicinfo.clear();
                        musicinfo.add(music);
                        break;
                    }
                    break;
                }
                case 4: {
                    final NewsResult newsResult = (NewsResult)nlu.getData().getResult();
                    if (newsResult == null) {
                        break;
                    }
                    final List<NewsResult.NewsBean>  news = newsResult.getNews();
                    if (news != null && news.size() > 1) {
                        final NewsResult.NewsBean newsBean = news.get(0);
                        news.clear();
                        news.add(newsBean);
                        break;
                    }
                    break;
                }
            }
        }

        nlu.setAsrResult("");
        final Mixture mixture = new Mixture();
        final ArrayList<NLU<I, T>> nluList = new ArrayList<NLU<I, T>>(1);
        nluList.add(nlu);
        mixture.setNluList((List)nluList);
        nlu.setAsrResult(JsonTool.toJson((Object)mixture));
    }

    private List<MusicResult.Music> reorderMusicList(List<MusicResult.Music> musicinfo) {
        int index = new Random().nextInt(musicinfo.size());
        List<MusicResult.Music> musicList = new ArrayList<>();
        for (int i = index; i < musicinfo.size(); i++) {
            musicList.add(musicinfo.get(i));
        }
        for (int i2 = 0; i2 < index; i2++) {
            musicList.add(musicinfo.get(i2));
        }
        return musicList;
    }

    private List<AudioResult.Music> reorderAudioList(List<AudioResult.Music> audioInfo) {
        int index = new Random().nextInt(audioInfo.size());
        List<AudioResult.Music> audioList = new ArrayList<>();
        for (int i = index; i < audioInfo.size(); i++) {
            audioList.add(audioInfo.get(i));
        }
        for (int i2 = 0; i2 < index; i2++) {
            audioList.add(audioInfo.get(i2));
        }
        return audioList;
    }

    private boolean hasNoSongResult(String prompt) {
        return prompt.equals(this.context.getString(R.string.tts_music_no_find_song)) || prompt.equals(this.context.getString(R.string.tts_music_no_find_audio)) || prompt.equals(this.context.getString(R.string.tts_music_no_find_news)) || prompt.equals(this.context.getString(R.string.tts_music_no_find_broadcast)) || prompt.equals(this.context.getString(R.string.tts_music_find_artist_not_match));
    }

    private boolean isBtnControlled(ANTHandlerContext ctx) {
        return ctx.hasAttr(this.EXTRA_BUTTON_CONTROL) && ((Boolean) ctx.attr(this.EXTRA_BUTTON_CONTROL).get()).booleanValue();
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx, String type) {
        LogMgr.e(TAG, "-->>doInterrupt type:" + type + ",eventReceived:" + this.eventReceived);
        ctx.cancelTTS();
        if (ExoConstants.DO_FINISH_ALL_INTERRUPT.equals(type)) {
            stopPlay();
            exitSession(false, false);
        } else if (this.playController != null && this.playController.isPlaying()) {
            this.playController.pause();
            this.shouldResume = true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doResume(ANTHandlerContext ctx) {
        LogMgr.d(TAG, "-->>doResume shouldResume:" + this.shouldResume);
        if (this.shouldResume && !this.playController.isPlaying()) {
            LogMgr.d(TAG, "doResume: =========继续播放");
            mark(true);
            startPlay();
            exitSession(false, false);
        } else if (ctx.hasAttr(this.MUSIC_STATUS_PAUSE) && ((Boolean) ctx.attr(this.MUSIC_STATUS_PAUSE).get()).booleanValue()) {
            setOperateWakeupWord(ctx);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (!this.eventReceived) {
            return super.onTTSEventPlayingEnd(ctx);
        }
        LogMgr.d(TAG, "-->>onTTSEventPlayingEnd serviceType=" + this.serviceType);
        switch (this.serviceType) {
            case 3:
                if (ctx.hasAttr(this.MUSIC_STATUS_STOP) && ((Boolean) ctx.attr(this.MUSIC_STATUS_STOP).getAndRemove()).booleanValue()) {
                    exitSession(true, true);
                    return true;
                } else if (ctx.hasAttr(this.MUSIC_STATUS_PAUSE) && ((Boolean) ctx.attr(this.MUSIC_STATUS_PAUSE).get()).booleanValue()) {
                    exitSession(false, false);
                    return true;
                } else if (ctx.hasAttr(this.ACT_BOOKMARK) && ((Boolean) ctx.attr(this.ACT_BOOKMARK).getAndRemove()).booleanValue()) {
                    exitSession(false, true);
                    return true;
                } else if (ctx.hasAttr(this.ACT_MODE_SET) && ((Boolean) ctx.attr(this.ACT_MODE_SET).getAndRemove()).booleanValue()) {
                    exitSession(false, true);
                    return true;
                } else if (ctx.hasAttr(this.ACT_QUERY_DETAIL) && ((Boolean) ctx.attr(this.ACT_QUERY_DETAIL).getAndRemove()).booleanValue()) {
                    exitSession(false, true);
                    return true;
                } else if (!ctx.hasAttr(this.ACT_PLAY_COLLECT_LIST) || !((Boolean) ctx.attr(this.ACT_PLAY_COLLECT_LIST).getAndRemove()).booleanValue()) {
                    exitSession(false, true);
                    return true;
                } else {
                    if (!ctx.hasAttr(this.EMPTY_RESULT) || !((Boolean) ctx.attr(this.EMPTY_RESULT).getAndRemove()).booleanValue()) {
                        this.shouldResume = true;
                    }
                    exitSession(false, true);
                    return true;
                }
            case 1:
            case 2:
            case 4:
            case 5:
                this.serviceType = 0;
                if (!ctx.hasAttr(this.EMPTY_RESULT) || !((Boolean) ctx.attr(this.EMPTY_RESULT).getAndRemove()).booleanValue()) {
                    this.shouldResume = true;
                    exitSession(false, true);
                    break;
                } else {
                    exitSession(true, true);
                    return true;
                }
        }
        setOperateWakeupWord(ctx);
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void onDestroy(ANTHandlerContext ctx) {
    }

    /* access modifiers changed from: protected */
    public void firNluMusicWakeupOperate(String wakeupWord) {
        LogMgr.d(TAG, "-->>firNluMusicWakeupOperate simulation music operate command nlu");
        NLU<Intent, Result> nlu = null;
        Context context2 = this.ctx.androidContext();
        PlayItem playItem = this.mCommonPlayer.getCurrentItem();
        if (context2.getString(R.string.music_wakeup_prev).equals(wakeupWord)) {
            if (!(playItem == null || playItem.getType() == PlayItem.ItemType.TYPE_NEWS)) {
                nlu = MusicNluCreator.createMusicPrevNlu(wakeupWord);
            }
        } else if (context2.getString(R.string.music_wakeup_next).equals(wakeupWord)) {
            if (!(playItem == null || playItem.getType() == PlayItem.ItemType.TYPE_NEWS)) {
                nlu = MusicNluCreator.createMusicNextNlu(wakeupWord);
            }
        } else if (context2.getString(R.string.news_wakeup_prev).contains(wakeupWord)) {
            if (playItem != null && playItem.getType() == PlayItem.ItemType.TYPE_NEWS) {
                nlu = MusicNluCreator.createMusicPrevNlu(wakeupWord);
            }
        } else if (context2.getString(R.string.news_wakeup_next).contains(wakeupWord)) {
            if (playItem != null && playItem.getType() == PlayItem.ItemType.TYPE_NEWS) {
                nlu = MusicNluCreator.createMusicNextNlu(wakeupWord);
            }
        } else if (context2.getString(R.string.music_wakeup_play).equals(wakeupWord)) {
            nlu = MusicNluCreator.createMusicPlayNlu(wakeupWord);
        } else if (context2.getString(R.string.music_wakeup_pause).equals(wakeupWord)) {
            nlu = MusicNluCreator.createMusicPauseNlu(wakeupWord);
        } else if (context2.getString(R.string.music_wakeup_stop).equals(wakeupWord)) {
            nlu = MusicNluCreator.createMusicCloseNlu(wakeupWord);
        } else if (context2.getString(R.string.music_volume_increase).equals(wakeupWord)) {
            nlu = SettingNluCreator.createVolumeIncreaseNlu(wakeupWord);
        } else if (context2.getString(R.string.music_volume_decrease).equals(wakeupWord)) {
            nlu = SettingNluCreator.createVolumeDecreaseNlu(wakeupWord);
        }
        if (nlu != null) {
            this.ctx.pipeline().fireUserEventTriggered(nlu);
            this.ctx.pipeline().fireUserEventTriggered(new SimulateWakeupEvent(wakeupWord));
        }
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        LogMgr.d(TAG, "-->>onServiceConnected ComponentName :" + name);
        MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
        binder.setMusicPlayer(this.mCommonPlayer);
        this.playController = new ANTPlayController(binder.getService());
        this.playController.setStateListener(DeviceCenterHandler.getDeviceCenterMgr());
        this.playController.registerMusicListener(new MusicListenerWapper() {
            /* class com.unisound.vui.handler.session.music.DefaultMusicHandler.AnonymousClass2 */

            @Override // com.unisound.vui.handler.session.music.listener.MusicListenerWapper
            public void onStatusChanged(int state) {
                LogMgr.d(DefaultMusicHandler.TAG, "onStatusChanged:" + state);
                if (state == 4 || state == 1) {
                    DefaultMusicHandler.this.ctx.attr(DefaultMusicHandler.this.MUSIC_STATUS_STOP).set(true);
                } else {
                    DefaultMusicHandler.this.ctx.attr(DefaultMusicHandler.this.MUSIC_STATUS_STOP).remove();
                }
            }
        });
        this.musicStateMgr.bindPlayController(this.playController);
    }

    public void onServiceDisconnected(ComponentName name) {
        LogMgr.d(TAG, "-->>onServiceDisconnected ComponentName :" + name);
        this.playController = null;
    }

    private void exitSession(boolean isNeedRecoveryWakeup, boolean isNeedFireResume) {
        LogMgr.d(TAG, "--->>exitSession isNeedRecoveryWakeup:" + isNeedRecoveryWakeup + ",isNeedFireResume:" + isNeedFireResume);
        reset();
        if (isNeedRecoveryWakeup) {
            recoveryDefaultWakeupWord(this.ctx);
        }
        if (this.ctx.hasAttr(this.EXTRA_BUTTON_CONTROL) && ((Boolean) this.ctx.attr(this.EXTRA_BUTTON_CONTROL).getAndRemove()).booleanValue()) {
            this.ctx.enterWakeup(false);
        }
        if (isNeedFireResume) {
            fireResume(this.ctx);
        }
    }

    /* access modifiers changed from: protected */
    @Override
    public String getInterruptType(NLU<I, T> evt) {
        if (isBtnControlled(this.ctx)) {
            return "";
        }
        return super.getInterruptType(evt);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void reset() {
        super.reset();
        this.serviceType = 0;
    }

    /* access modifiers changed from: protected */
    public boolean isIntervalWakeupWord(String wakeupWord) {
        if (!Arrays.asList(SpeakerTTSUtil.getTTSStringArray(R.array.music_running_wakeup_words, this.ctx.androidContext())).contains(wakeupWord)) {
            return false;
        }
        String avoidWakeNext = this.context.getString(R.string.music_avoid_wakeup_next);
        String avoidWakePre = this.context.getString(R.string.music_avoid_wakeup_prev);
        String avoidWakeRandom = this.context.getString(R.string.music_avoid_wakeup_random);
        if (avoidWakeNext.equals(wakeupWord) || avoidWakePre.equals(wakeupWord) || avoidWakeRandom.equals(wakeupWord)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void setOperateWakeupWord(ANTHandlerContext ctx) {
        List<String> listWakeup = Arrays.asList(ctx.androidContext().getResources().getStringArray(R.array.music_running_wakeup_words));
        this.currentWakeupList = UserPerferenceUtil.getEffectWakeupword(this.context);
        boolean isContain = this.currentWakeupList.containsAll(listWakeup);
        LogMgr.d(TAG, "-->>setOperateWakeupWord isContain:" + isContain);
        if (!isContain) {
            this.currentWakeupList.addAll(listWakeup);
            UserPerferenceUtil.setStartWakeupAfterSetWakeupWord(ctx.androidContext(), true);
            ctx.setWakeupWord(this.currentWakeupList, false);
        }
        LogMgr.d(TAG, "setOperateWakeupWord wakupWords:" + this.currentWakeupList);
    }

    /* access modifiers changed from: protected */
    public void recoveryDefaultWakeupWord(ANTHandlerContext ctx) {
        this.currentWakeupList = UserPerferenceUtil.getWakeupWord(ctx.androidContext());
        LogMgr.d(TAG, "recoveryDefaultWakeupWord wakeupWords:" + this.currentWakeupList);
        ctx.setWakeupWord(this.currentWakeupList, false);
    }

    private boolean seekTo(String operands, String valueDeta, String value) {
        long offset;
        long offset2;
        long currentPosition = this.playController.getCurrentPostion();
        if (SettingMPIntent.Operands_MP.ATTR_FASTF_TIME.equals(operands)) {
            if (!TextUtils.isEmpty(valueDeta)) {
                offset2 = currentPosition + ((long) formatTime(valueDeta));
            } else if (!TextUtils.isEmpty(value)) {
                offset2 = (long) formatTime(value);
            } else {
                offset2 = currentPosition + 10000;
            }
            this.playController.seekTo(offset2);
            return true;
        } else if (!SettingMPIntent.Operands_MP.ATTR_FASTB_TIME.equals(operands)) {
            return false;
        } else {
            if (!TextUtils.isEmpty(valueDeta)) {
                offset = currentPosition - ((long) formatTime(valueDeta));
            } else if (!TextUtils.isEmpty(value)) {
                offset = (long) formatTime(value);
            } else {
                offset = currentPosition - 10000;
            }
            this.playController.seekTo(offset);
            return true;
        }
    }

    private int formatTime(String time) {
        if (TextUtils.isEmpty(time)) {
            return 0;
        }
        String[] timeSplit = time.split(":");
        return ((Integer.valueOf(timeSplit[0]).intValue() * 3600) + (Integer.valueOf(timeSplit[1]).intValue() * 60) + Integer.valueOf(timeSplit[2]).intValue()) * 1000;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onWakeupEventSetWakeupwordDone(ANTHandlerContext ctx) {
        if (this.eventReceived) {
            return true;
        }
        return super.onWakeupEventSetWakeupwordDone(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher
    public boolean onWakeupEventUpdateWakeupWordFail(ANTHandlerContext ctx) {
        if (this.eventReceived) {
            return true;
        }
        return super.onWakeupEventUpdateWakeupWordFail(ctx);
    }

    private String dealMusicPlayService(NLU<MusicIntent, MusicResult> musics) {
        MusicIntent intent = musics.getSemantic().getIntent();
        String targetSong = intent.getSong();
        String targetSinger = intent.getArtist();
        MusicResult musicResult = musics.getData() != null ? musics.getData().getResult() : null;
        LogMgr.d(TAG, "dealMusicPlayService: " + musicResult);
        if (musicResult == null) {
            return this.context.getString(R.string.tts_music_no_find_song);
        }
        List<MusicResult.Music> musicList = musicResult.getMusicinfo();
        String searchType = musicResult.getSearchType();
        if (musicList.size() <= 0) {
            return dealEmptyListTTS(targetSinger, targetSong);
        }
        this.ctx.pipeline().write(new MusicOutputEvents(true));
        List<PlayItem> itemList = PlayItemAdapter.adaptMusic(musicResult.getMusicinfo());
        return dealMusicTtsAndPlay(targetSinger, targetSong, itemList.get(0).getArtist(), itemList.get(0).getTitle(), searchType, itemList);
    }

    private String dealMusicTtsAndPlay(String targetSinger, String targetSong, String realSinger, String realSong, String searchType, List<PlayItem> itemList) {

        // PPP fix songer null
        if(realSinger==null || realSinger.length()==0)realSinger=realSong;

        if (!TextUtils.isEmpty(targetSinger) && !TextUtils.isEmpty(targetSong)) {
            char c = 65535;
            switch (searchType.hashCode()) {
                case -1354814997:
                    if (searchType.equals(TYPE_COMMON)) {
                        c = 0;
                        break;
                    }
                    break;
                case -706555258:
                    if (searchType.equals(TYPE_DOWNGRADE_SINGER)) {
                        c = 1;
                        break;
                    }
                    break;
                case 995917471:
                    if (searchType.equals(TYPE_DOWNGRADE_SONG)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return realPlayMusic(realSinger, realSong, itemList, this.context.getString(R.string.tts_music_find_song_to_play, realSinger, realSong));
                case 1:
                    return realPlayMusic(realSinger, realSong, itemList, this.context.getString(R.string.tts_music_find_artist_but_not_correct_song, realSinger));
                case 2:
                    return dealDowngradeSongPlay(realSinger, realSong, itemList);
                default:
                    return this.context.getString(R.string.tts_music_no_find_song);
            }
        } else if (!TextUtils.isEmpty(targetSinger)) {
            return realPlayMusic(realSinger, realSong, itemList, this.context.getString(R.string.tts_music_find_song_to_play, realSinger, realSong));
        } else if (!TextUtils.isEmpty(targetSong)) {
            return realPlayMusic(realSinger, realSong, itemList, this.context.getString(R.string.tts_music_find_song_to_play, realSinger, realSong));
        } else {
            return realPlayMusic(realSinger, realSong, itemList, this.context.getString(R.string.tts_music_find_song_to_play, realSinger, realSong));
        }
    }

    private String dealEmptyListTTS(String targetSinger, String targetSong) {
        if (!TextUtils.isEmpty(targetSinger) && !TextUtils.isEmpty(targetSong)) {
            return this.context.getString(R.string.tts_music_no_find_song);
        }
        if (!TextUtils.isEmpty(targetSinger)) {
            return this.context.getString(R.string.tts_music_find_artist_not_match);
        }
        if (!TextUtils.isEmpty(targetSong)) {
            return this.context.getString(R.string.tts_music_no_find_song);
        }
        return this.context.getString(R.string.tts_music_no_find_song);
    }

    private boolean isTargetSinger(String targetSinger, String realSinger) {
        return !TextUtils.isEmpty(realSinger) && realSinger.contains(targetSinger);
    }

    private String realPlayMusic(String artist, String title, List<PlayItem> itemList, String prompt) {
        this.playController.setPlaylist(itemList);
        this.playController.play(true);
        if (TextUtils.isEmpty(artist) || TextUtils.isEmpty(title)) {
            prompt = RandomHelper.getRandom(R.array.tts_action_music_play_answer, this.ctx.androidContext());
        }
        if (TextUtils.isEmpty(artist) && TextUtils.isEmpty(title)) {
            return this.context.getString(R.string.tts_music_find_both_empty);
        }
        if (TextUtils.isEmpty(artist)) {
            return this.context.getString(R.string.tts_music_find_only_song_not_empty);
        }
        if (TextUtils.isEmpty(title)) {
            return this.context.getString(R.string.tts_music_find_only_artist_not_empty);
        }
        return prompt;
    }

    private String dealDowngradeSongPlay(String realSinger, String realSong, List<PlayItem> itemList) {
        if (TextUtils.isEmpty(realSinger)) {
            return this.context.getString(R.string.tts_music_find_empty_artist_prefix) + realPlayMusic(realSinger, realSong, itemList, this.context.getString(R.string.tts_music_find_empty_artist_prefix));
        }
        return realPlayMusic(realSinger, realSong, itemList, this.context.getString(R.string.tts_music_find_song_but_not_correct_artist, realSinger, realSong));
    }

    private List<MusicResult.Music> removeRepeatInfo(List<MusicResult.Music> infos) {
        LogMgr.d(TAG, "removeRepeatInfo old infos size:" + infos.size());
        Set<MusicResult.Music> set = new LinkedHashSet<>();
        set.addAll(infos);
        List<MusicResult.Music> infoSet = new ArrayList<>(set);
        LogMgr.d(TAG, "removeRepeatInfo new infos size:" + infoSet.size());
        return infoSet;
    }

    private String dealAudioPlayService(NLU<AudioIntent, AudioResult> audios) {
        AudioIntent audioIntent;
        AudioResult audioResult = null;
        String artist = "";
        String album = "";
        if (audios.getSemantic() != null) {
            audioIntent = audios.getSemantic().getIntent();
        } else {
            audioIntent = null;
        }
        if (audioIntent != null) {
            if (audioIntent.getArtist() != null) {
                artist = audioIntent.getArtist();
            }
            if (audioIntent.getAlbum() != null) {
                album = audioIntent.getAlbum();
            }
        }
        if (audios.getData() != null) {
            audioResult = audios.getData().getResult();
        }
        if (audioResult == null || audioResult.getPlaylist() == null || audioResult.getPlaylist().size() <= 0) {
            return this.context.getString(R.string.tts_music_no_find_audio);
        }
        this.ctx.pipeline().write(new MusicOutputEvents(true));
        String prompt = RandomHelper.getRandom(R.array.tts_action_music_play_answer, this.ctx.androidContext());
        this.playController.setPlaylist(PlayItemAdapter.adaptAudio(audioResult.getPlaylist(), album, artist));
        if (TextUtils.isEmpty(audioResult.getPageSize()) || !TextUtils.isEmpty(audioResult.getTotal())) {
            this.playController.play(true, 1, 2);
            return prompt;
        }
        this.playController.play(true, 1, (int) Math.ceil(Double.valueOf(audioResult.getTotal()).doubleValue() / Double.valueOf(audioResult.getPageSize()).doubleValue()));
        return prompt;
    }

    private String dealNewsPlayService(NLU<NewsIntent, NewsResult> news) {
        String prompt;
        NewsResult newsResult = news.getData() != null ? news.getData().getResult() : null;
        if (newsResult == null || newsResult.getNews() == null || newsResult.getNews().size() <= 0) {
            return this.context.getString(R.string.tts_news_not_found);
        }
        this.ctx.pipeline().write(new MusicOutputEvents(true));
        String tts = newsResult.getTts();
        if (TextUtils.isEmpty(tts)) {
            prompt = RandomHelper.getRandom(R.array.tts_action_music_play_answer, this.ctx.androidContext());
        } else {
            prompt = tts;
        }
        this.playController.setPlaylist(PlayItemAdapter.adaptNews(newsResult.getNews()));
        this.playController.play(true, news.getText());
        return prompt;
    }

    private String dealBroadcastPlayService(NLU<BroadcastIntent, BroadcastResult> broadcast) {
        BroadcastResult broadcastResult = broadcast.getData() != null ? broadcast.getData().getResult() : null;
        if (broadcastResult == null || broadcastResult.getRate64TsUrl() == null) {
            return this.context.getString(R.string.tts_music_no_find_broadcast);
        }
        this.ctx.pipeline().write(new MusicOutputEvents(true));
        String prompt = this.ctx.androidContext().getString(R.string.tts_broadcast_start_play, broadcast.getSemantic().getIntent().getStation());
        this.playController.setPlaylist(PlayItemAdapter.adaptBroadcast(broadcastResult));
        this.playController.play(true);
        return prompt;
    }

    private String dealMediaSettingService(SettingExtIntent settingExtIntent) {
        String prompt = "";
        if (settingExtIntent.getOperations().iterator().hasNext()) {
            SettingIntent settingIntent = settingExtIntent.getOperations().iterator().next();
            String operator = settingIntent.getOperator();
            String operands = settingIntent.getOperands();
            if (Operands.ATTR_BUTTON.equals(operands)) {
                this.ctx.attr(this.EXTRA_BUTTON_CONTROL).set(true);
            }
            LogMgr.d(TAG, "-->>dealMediaSettingService operator11:" + operator);
            if (this.settingMap.containsKey(operator)) {
                if (!notNeedProccessAction(operator, operands)) {
                    switch (this.settingMap.get(operator).intValue()) {
                        case 0:
                            if (isPlayingBroadcast()) {
                                return this.context.getString(R.string.tts_operatation_not_support);
                            }
                            if (!this.ctx.hasAttr(this.MUSIC_STATUS_STOP) || !((Boolean) this.ctx.attr(this.MUSIC_STATUS_STOP).get()).booleanValue()) {
                                this.playController.playPrev();
                                this.ctx.attr(this.MUSIC_STATUS_PAUSE).remove();
                                this.ctx.attr(this.MUSIC_STATUS_STOP).remove();
                                setOperateWakeupWord(this.ctx);
                                if (isBtnControlled(this.ctx)) {
                                    this.playController.resume();
                                } else {
                                    this.shouldResume = true;
                                }
                                prompt = RandomHelper.getRandom(R.array.tts_action_music_play_answer, this.ctx.androidContext());
                                break;
                            } else {
                                return this.context.getString(R.string.tts_music_not_playing);
                            }
                        case 1:
                            if (isPlayingBroadcast()) {
                                return this.context.getString(R.string.tts_operatation_not_support);
                            }
                            if (!this.ctx.hasAttr(this.MUSIC_STATUS_STOP) || !((Boolean) this.ctx.attr(this.MUSIC_STATUS_STOP).get()).booleanValue()) {
                                this.playController.playNext();
                                this.ctx.attr(this.MUSIC_STATUS_PAUSE).remove();
                                this.ctx.attr(this.MUSIC_STATUS_STOP).remove();
                                setOperateWakeupWord(this.ctx);
                                if (isBtnControlled(this.ctx)) {
                                    this.playController.resume();
                                } else {
                                    this.shouldResume = true;
                                }
                                prompt = RandomHelper.getRandom(R.array.tts_action_music_play_answer, this.ctx.androidContext());
                                break;
                            } else {
                                return this.context.getString(R.string.tts_music_not_playing);
                            }
                        case 2:
                            LogMgr.d(TAG, "dealMediaSettingService: " + operands);
                            if (!"OBJ_FAV_MUSIC_LIST".equals(operands)) {
                                if (!SettingMPIntent.Operands_MP.OBJ_FAV_AUDIO_LIST.equals(operands)) {
                                    if (this.playController.isPlaying()) {
                                        prompt = this.context.getString(R.string.tts_music_is_already_playing);
                                        break;
                                    } else {
                                        startPlay();
                                        break;
                                    }
                                } else {
                                    prompt = settingExtIntent.getVoiceTip();
                                    this.ctx.attr(this.ACT_PLAY_COLLECT_LIST).set(true);
                                    this.ctx.attr(this.MUSIC_STATUS_PAUSE).remove();
                                    this.ctx.attr(this.MUSIC_STATUS_STOP).remove();
                                    break;
                                }
                            } else {
                                prompt = settingExtIntent.getVoiceTip();
                                this.ctx.attr(this.ACT_PLAY_COLLECT_LIST).set(true);
                                this.ctx.attr(this.MUSIC_STATUS_PAUSE).remove();
                                this.ctx.attr(this.MUSIC_STATUS_STOP).remove();
                                break;
                            }
                        case 3:
                            if (!this.ctx.hasAttr(this.MUSIC_STATUS_PAUSE)) {
                                this.playController.pause();
                                this.shouldResume = false;
                                this.ctx.attr(this.MUSIC_STATUS_PAUSE).set(true);
                                prompt = this.context.getString(R.string.tts_music_control_pause);
                                break;
                            }
                            break;
                        case 4:
                        case 10:
                            this.ctx.pipeline().write(new MusicOutputEvents(false));
                            stopPlay();
                            prompt = this.context.getString(R.string.tts_music_control_stop);
                            break;
                        case 5:
                            this.ctx.attr(this.ACT_MODE_SET).set(true);
                            if (!SettingMPIntent.Operands_MP.ATTR_PLAY_MODE.equals(operands)) {
                                if (seekTo(operands, settingIntent.getValueDelta(), settingIntent.getValue())) {
                                    prompt = this.context.getString(R.string.tts_music_change_no_supported);
                                    break;
                                }
                            } else if (!isPlayingNews() && !isPlayingBroadcast()) {
                                prompt = switchPlayMode(settingIntent.getValue());
                                break;
                            } else {
                                prompt = this.context.getString(R.string.tts_music_change_no_supported);
                                break;
                            }
                            break;
                        case 6:
                            if (!isPlayingNews() && !isPlayingBroadcast()) {
                                String confirm = settingIntent.getConfirm();
                                LogMgr.d(TAG, "-->> confirm:" + confirm + " status: " + this.playController.getPlaybackStatus() + " isCollected: " + this.playController.isCollected());
                                this.ctx.attr(this.ACT_BOOKMARK).set(true);
                                if (!Operator.ACT_CANCEL.equals(confirm)) {
                                    this.playController.collect();
                                    prompt = this.context.getString(R.string.tts_music_collect);
                                    break;
                                } else {
                                    this.playController.cancelCollect();
                                    prompt = this.context.getString(R.string.tts_music_cancel_collect);
                                    break;
                                }
                            } else {
                                prompt = this.context.getString(R.string.tts_music_change_no_supported);
                                break;
                            }
                        case 11:
                            LogMgr.d(TAG, "dealMediaSettingService: SWITCH_PLAY_MODE");
                            if (!isPlayingNews() && !isPlayingBroadcast()) {
                                prompt = switchPlayMode(settingIntent.getValue());
                                break;
                            } else {
                                prompt = this.context.getString(R.string.tts_music_change_no_supported);
                                break;
                            }
                        case 12:
                            this.playController.switchTo(settingIntent.getConfirm());
                            setOperateWakeupWord(this.ctx);
                            this.ctx.attr(this.MUSIC_STATUS_PAUSE).remove();
                            this.ctx.attr(this.MUSIC_STATUS_STOP).remove();
                            break;
                        case 13:
                        case 14:
                            this.ctx.pipeline().write(new MusicOutputEvents(true));
                            List<PlayItem> itemList = null;
                            Integer operate = this.settingMap.get(operator);
                            if (13 == operate.intValue()) {
                                DeviceMusicData deviceMusicData = (DeviceMusicData) JsonTool.fromJson(settingIntent.getConfirm(), DeviceMusicData.class);
                                int index = deviceMusicData.getIndex();
                                itemList = PlayItemAdapter.adaptMusic(deviceMusicData.getMusicList());
                                this.playController.play(itemList, index);
                            } else if (14 == operate.intValue()) {
                                DeviceAudioData data = (DeviceAudioData) JsonTool.fromJson(settingIntent.getConfirm(), DeviceAudioData.class);
                                int index2 = data.getIndex();
                                saveAudioReqData(data.getAlbumId());
                                itemList = PlayItemAdapter.adaptAudio(data.getAudioList(), null, null);
                                this.playController.play(itemList, index2, data.getPageNo(), data.getPageCount());
                            }
                            if (itemList != null) {
                                setOperateWakeupWord(this.ctx);
                                this.ctx.attr(this.MUSIC_STATUS_PAUSE).remove();
                                this.ctx.attr(this.MUSIC_STATUS_STOP).remove();
                                break;
                            }
                            break;
                    }
                } else {
                    return this.context.getString(R.string.tts_music_empty_playlist);
                }
            } else {
                prompt = this.context.getString(R.string.tts_music_change_no_supported);
            }
            if (isBtnControlled(this.ctx)) {
                prompt = "";
            }
        }
        return prompt;
    }

    private void saveAudioReqData(String albumId) {
        this.mNLU = null;
        this.mAlbumId = albumId;
    }

    private void saveAudioReqData(NLU nlu) {
        this.mNLU = nlu;
        this.mAlbumId = null;
    }

    private boolean isPlayingNews() {
        PlayItem currentItem = this.mCommonPlayer.getCurrentItem();
        return currentItem != null && currentItem.getType() == PlayItem.ItemType.TYPE_NEWS;
    }

    private boolean isPlayingBroadcast() {
        PlayItem currentItem = this.mCommonPlayer.getCurrentItem();
        return currentItem != null && currentItem.getType() == PlayItem.ItemType.TYPE_BROADCAST;
    }

    private void startPlay() {
        this.playController.resume();
        setOperateWakeupWord(this.ctx);
        this.ctx.attr(this.MUSIC_STATUS_PAUSE).remove();
        this.ctx.attr(this.MUSIC_STATUS_STOP).remove();
        this.shouldResume = false;
    }

    private void stopPlay() {
        this.ctx.attr(this.MUSIC_STATUS_STOP).set(true);
        this.ctx.attr(this.MUSIC_STATUS_PAUSE).remove();
        this.playController.stop();
        this.shouldResume = false;
        reportMusicExitState();
        recoveryDefaultWakeupWord(this.ctx);
    }

    private boolean isQueryDetailAction(String code) {
        return SCode.QUERY_ARTISTNAME.equals(code) || SCode.QUERY_NAME.equals(code) || QUERY_ALBUM_NAME.equals(code) || SCode.QUERY_SINGER.equals(code);
    }

    private String queryDetail(String code) {
        if (!isQueryDetailAction(code)) {
            return "";
        }
        PlayItem playItem = this.playController.getCurrPlayItem();
        if (playItem == null) {
            return this.context.getString(R.string.tts_music_empty_playlist);
        }
        String artist = playItem.getArtist();
        String title = playItem.getTitle();
        if (TextUtils.isEmpty(artist)) {
            return String.format(this.context.getString(R.string.tts_music_query_no_artist_detail), title);
        }
        return String.format(this.context.getString(R.string.tts_music_query_detail), artist, title);
    }

    private String switchPlayMode(String value) {
        LogMgr.d(TAG, "----------switchPlayMode = " + value);
        if (!this.playMode.containsKey(value)) {
            return "";
        }
        switch (this.playMode.get(value).intValue()) {
            case 0:
                String switchPlayMode = this.context.getString(R.string.tts_music_mode_shuffle);
                this.playController.setPlayMode(PlayController.ItemPlayMode.LIST_SHUFFLED);
                return switchPlayMode;
            case 1:
            case 2:
            case 4:
                String switchPlayMode2 = this.context.getString(R.string.tts_music_mode_all_repeat);
                this.playController.setPlayMode(PlayController.ItemPlayMode.LIST_LOOP);
                return switchPlayMode2;
            case 3:
                String switchPlayMode3 = this.context.getString(R.string.tts_music_mode_repeat_once);
                this.playController.setPlayMode(PlayController.ItemPlayMode.SINGLE_LOOP);
                return switchPlayMode3;
            default:
                return "";
        }
    }

    private void reportServiceInfoToSessionLayer(String message) {
        LogMgr.d(TAG, "reportServiceInfoToSessionLayer message:" + message);
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = message;
        this.taskHandler.sendMessage(msg);
    }

    private boolean notNeedProccessAction(String operator, String operands) {
        return this.settingMap.get(operator).intValue() != 13 && this.settingMap.get(operator).intValue() != 14 && this.playController.getPlayItemList().size() == 0 && !"OBJ_FAV_MUSIC_LIST".equals(operands) && !SettingMPIntent.Operands_MP.OBJ_FAV_AUDIO_LIST.equals(operands);
    }

    public RemoteMusicInfo getMusicInfo(String iotUniJson) {
        UnisoundDeviceCommand command = NluSessionLayer.parseNluSessionContent(iotUniJson);
        if (command == null) {
            return null;
        }
        return (RemoteMusicInfo) JsonTool.fromJson((JsonObject) command.getParameter(), RemoteMusicInfo.class);
    }

    public RemoteAudioInfo getAudioInfo(String iotUniJson) {
        UnisoundDeviceCommand command = NluSessionLayer.parseNluSessionContent(iotUniJson);
        if (command == null) {
            return null;
        }
        return (RemoteAudioInfo) JsonTool.fromJson((JsonObject) command.getParameter(), RemoteAudioInfo.class);
    }

    private void reportMusicPlayingState() {
        LogMgr.d(TAG, "mqtt reconnect success report playing musicInfo");
        if (this.playController != null && this.playController.isPlaying() && this.musicStateMgr != null) {
            this.musicStateMgr.onStatusChanged(3);
        }
    }

    private void reportMusicExitState() {
        LogMgr.d(TAG, "voice control exit music service:");
        if (this.playController != null && this.musicStateMgr != null) {
            this.musicStateMgr.onStatusChanged(0);
        }
    }

    @Override // com.unisound.ant.device.netmodule.NetChangeReceiver.NetAliveConnectListener
    public void onNetAliveConnected() {
        LogMgr.d(TAG, "onNetAliveConnected");
        reportMusicPlayingState();
    }
}

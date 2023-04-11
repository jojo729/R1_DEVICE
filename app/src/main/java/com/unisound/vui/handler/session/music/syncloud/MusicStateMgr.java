package com.unisound.vui.handler.session.music.syncloud;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.gson.JsonObject;
import com.unisound.ant.device.bean.ActionStatus;
import com.unisound.ant.device.bean.AudioListResult;
import com.unisound.ant.device.bean.CollectInfo;
import com.unisound.ant.device.bean.CommandOperate;
import com.unisound.ant.device.bean.CurrentMusicList;
import com.unisound.ant.device.bean.DeviceAudioData;
import com.unisound.ant.device.bean.DeviceMusicData;
import com.unisound.ant.device.bean.DstServiceName;
import com.unisound.ant.device.bean.DstServiceState;
import com.unisound.ant.device.bean.MusicData;
import com.unisound.ant.device.bean.RequestInfo;
import com.unisound.ant.device.bean.UnisoundDeviceCommand;
import com.unisound.ant.device.controlor.DefaultVolumeOperator;
import com.unisound.ant.device.devicelayer.button.ButtonControl;
import com.unisound.ant.device.listener.VolumeListener;
import com.unisound.ant.device.service.ActionResponse;
import com.unisound.ant.device.sessionlayer.SessionRegister;
import com.unisound.vui.engine.ANTEngineOption;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.session.music.ANTPlayController;
import com.unisound.vui.handler.session.music.PlayController;
import com.unisound.vui.handler.session.music.listener.MusicListenerWapper;
import com.unisound.vui.handler.session.music.network.Api;
import com.unisound.vui.handler.session.music.playitem.PlayItem;
import com.unisound.vui.handler.session.music.playitem.PlayItemAdapter;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;
import java.util.List;
import java.util.UUID;
import nluparser.scheme.AudioResult;
import nluparser.scheme.MusicResult;
import nluparser.scheme.SettingMPIntent;
import retrofit2.Call;
import retrofit2.Response;

public class MusicStateMgr extends MusicListenerWapper implements VolumeListener {
    public static final String BUSINESS_TYPE_AUDIO = "audio";
    public static final String BUSINESS_TYPE_MUSIC = "music";
    public static final String COMMAND_AUDIO = "getAudioCurrentList";
    public static final String COMMAND_MUSIC = "getCurrentList";
    public static final String CONTROL_CANCEL_COLLECT = "cancelCollect";
    public static final String CONTROL_COLLECT = "collect";
    public static final String CONTROL_NEXT = "next";
    public static final String CONTROL_PAUSE = "pause";
    public static final String CONTROL_PLAY = "play";
    public static final String CONTROL_PREV = "prev";
    public static final String CONTROL_SWITCH = "switch";
    public static final String CONTROL_SWITCH_PLAY_MODE = "changeMode";
    public static final String CONTROL_TO_COLLECTION_LIST = "toCollectionList";
    public static final String CONTROL_TO_HOT_MUSIC_LIST = "toHotMusicList";
    public static final String CONTROL_TO_HOT_SINGER_MUSIC_LIST = "toHotSingerMusicList";
    public static final String CONTROL_TO_MUSIC_LIST = "toAppAddMusicList";
    public static final String CONTROL_TO_RECOMMAND_LIST = "toRecommendList";
    private static final int MSG_ITEM_OPERATE = 4;
    private static final int MSG_MUSIC_PLAYMODE = 2;
    private static final int MSG_MUSIC_STATE = 1;
    private static final int MSG_VOLUME = 3;
    public static final String PAGE_NO = "1";
    public static final String PAGE_SIZE = "200";
    public static final String PROTOCOL_VERSION = "2.0.0";
    public static final int SUB_SYS_ID = 9;
    private static final String TAG = "MusicStateMgr";
    private ButtonControl buttonControl;
    private Context context;
    private final String mUdid;
    private String needRespAction;
    private ANTPlayController playController;
    public ReportHandler reportHandler = new ReportHandler();

    public MusicStateMgr(ANTHandlerContext ctx) {
        this.context = ctx.androidContext();
        this.mUdid = (String) ctx.engine().config().getOption(ANTEngineOption.GENERAL_UDID);
        this.buttonControl = new ButtonControl(ctx);
        SessionRegister.associateSessionCenter(DstServiceName.DST_SERVICE_MUSIC, this);
        SessionRegister.associateSessionCenter(DstServiceName.DST_SERVICE_AUDIO, this);
    }

    public void bindPlayController(ANTPlayController playController2) {
        this.playController = playController2;
        this.playController.registerMusicListener(this);
        DefaultVolumeOperator.getInstance(this.context).registerListener(this);
    }

    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                dispatchMusicControlCommand((UnisoundDeviceCommand) msg.obj);
                return;
            case 1:
            default:
                return;
            case 2:
                LogMgr.d(TAG, "--->>need startSync action:" + this.needRespAction);
                if (!TextUtils.isEmpty(this.needRespAction)) {
                    responseCloudCommand(this.needRespAction, getActionResponse(0));
                    return;
                }
                return;
        }
    }

    private void dispatchMusicControlCommand(UnisoundDeviceCommand command) {
        MusicData musicData;
        int count;
        if (command == null) {
            LogMgr.d(TAG, "dispatchMusicControlCommand command is null");
            return;
        }
        String operation = command.getOperation();
        JsonObject parameter = (JsonObject) command.getParameter();
        LogMgr.d(TAG, "--->>dispatchMusicControlCommand operate:" + operation);
        if (CommandOperate.COMMAND_OPERATE_UPLOAD_PLAY_LIST.equals(operation)) {
            uploadPlayList();
        } else if (CommandOperate.COMMAND_OPERATE_MUSIC_COLLECT_ADD.equals(operation)) {
            this.playController.addCollectMusic(((CollectInfo) JsonTool.fromJson(parameter, CollectInfo.class)).getId());
        } else if (CommandOperate.COMMAND_OPERATE_MUSIC_COLLECT_DELETE.equals(operation)) {
            this.playController.deleteCollectMusic(((CollectInfo) JsonTool.fromJson(parameter, CollectInfo.class)).getId());
        } else if (CommandOperate.COMMAND_OPERATE_MUSIC_COLLECT_BATCH_DELETE.equals(operation)) {
            this.playController.batchDeleteCollectMusic(((CollectInfo) JsonTool.fromJson(parameter, CollectInfo.class)).getIds());
        } else if (!((!"changePlayState".equals(operation) && !CommandOperate.COMMAND_CHANGE_AUDIO_PLAY_STATE.equals(operation) && !"changeNewsPlayState".equals(operation)) || (musicData = (MusicData) JsonTool.fromJson(parameter, MusicData.class)) == null || this.buttonControl == null)) {
            String playControl = musicData.getControlCmd();
            if ("prev".equals(playControl)) {
                this.buttonControl.prevMusic();
            } else if ("play".equals(playControl)) {
                if (this.playController.isPlaying()) {
                    this.buttonControl.pauseMusic();
                } else {
                    this.buttonControl.playMusic();
                }
            } else if ("pause".equals(playControl)) {
                this.buttonControl.pauseMusic();
            } else if ("next".equals(playControl)) {
                this.buttonControl.nextMusic();
            } else if ("changeMode".equals(playControl)) {
                this.buttonControl.switchMusicPlayMode(parseMusicPlayMode(musicData.getPlayMode()));
            } else if (CONTROL_SWITCH.equals(playControl)) {
                this.buttonControl.switchTo(musicData.getItemId());
            } else if (CONTROL_TO_COLLECTION_LIST.equals(playControl)) {
                if ("changePlayState".equals(operation)) {
                    Api.getMusicApi().getCurrentMusicList(buildRequestBody("music", COMMAND_MUSIC)).enqueue(new PlayListCallback(musicData.getItemId()));
                } else if (CommandOperate.COMMAND_CHANGE_AUDIO_PLAY_STATE.equals(operation)) {
                    Api.getMusicApi().getCurrentAudioList(buildRequestBody("audio", COMMAND_AUDIO)).enqueue(new AudioPlayListCallback(musicData.getItemId(), musicData.getAlbumId()));
                }
            } else if (CONTROL_TO_RECOMMAND_LIST.equals(playControl)) {
                String pageNo = musicData.getPageNo();
                String pageSize = musicData.getPageSize();
                String albumId = musicData.getAlbumId();
                String timeAsc = musicData.getTimeAsc();
                String pageCount = musicData.getPageCount();
                if (TextUtils.isEmpty(pageCount)) {
                    count = 2;
                } else {
                    count = Integer.parseInt(pageCount);
                }
                Api.getMusicApi().getCurrentAudioList(buildRequestBody("audio", COMMAND_AUDIO, pageNo, pageSize, albumId, timeAsc)).enqueue(new AudioPlayListCallback(musicData.getItemId(), albumId, Integer.parseInt(pageNo), Integer.parseInt(pageSize), count));
            } else if (CONTROL_TO_HOT_MUSIC_LIST.equals(playControl) || CONTROL_TO_HOT_SINGER_MUSIC_LIST.equals(playControl) || CONTROL_TO_MUSIC_LIST.equals(playControl)) {
                Api.getMusicApi().getCurrentMusicList(buildRequestBody("music", COMMAND_MUSIC)).enqueue(new PlayListCallback(musicData.getItemId()));
            }
        }
        if (this.reportHandler != null && this.reportHandler.getLooper().getThread().isAlive()) {
            this.needRespAction = command.getOperation();
            responseCloudCommand(command.getOperation(), getActionResponse(0));
        }
    }

    private void uploadPlayList() {
        if (!"stop".equals(this.playController.getPlayStatus())) {
            MusicHttpUtils.uploadMusicList(this.mUdid, this.playController.getPlayItemList());
            onStatusChanged(this.playController.getPlaybackStatus());
        }
    }

    private RequestInfo buildRequestBody(String type, String command) {
        return new RequestInfo(type, command, new RequestInfo.PageInfo("1", PAGE_SIZE, this.mUdid), new RequestInfo.ClientInfo(9), "2.0.0");
    }

    private RequestInfo buildRequestBody(String type, String command, String pageNo, String pageSize, String albumId, String timeAsc) {
        return new RequestInfo(type, command, new RequestInfo.PageInfo(pageNo, pageSize, this.mUdid, albumId, timeAsc), new RequestInfo.ClientInfo(9), "2.0.0");
    }

    private String parseMusicPlayMode(String mode) {
        char c = 65535;
        switch (mode.hashCode()) {
            case -1238918832:
                if (mode.equals("listOrder")) {
                    c = 4;
                    break;
                }
                break;
            case 901590857:
                if (mode.equals("listShuffled")) {
                    c = 1;
                    break;
                }
                break;
            case 913398540:
                if (mode.equals("singleLoop")) {
                    c = 3;
                    break;
                }
                break;
            case 1345416290:
                if (mode.equals("listLoop")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 1:
                return SettingMPIntent.Value_MP.MODE_SHUFFLE;
            case 2:
                return SettingMPIntent.Value_MP.MODE_ALL_REPEAT;
            case 3:
                return SettingMPIntent.Value_MP.MODE_REPEAT_ONCE;
            case 4:
                return SettingMPIntent.Value_MP.MODE_ORDER;
            default:
                return SettingMPIntent.Value_MP.MODE_ORDER;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String parsePlayMode(PlayController.ItemPlayMode playMode) {
        switch (playMode) {
            case LIST_LOOP:
                return "listLoop";
            case LIST_ORDER:
                return "listOrder";
            case LIST_SHUFFLED:
                return "listShuffled";
            case SINGLE_LOOP:
                return "singleLoop";
            default:
                return "listLoop";
        }
    }

    private void responseCloudCommand(String action, ActionResponse response) {
        SessionRegister.getUpDownMessageManager().reponseCloudCommandWithoutAck(action, response);
    }

    @Override // com.unisound.vui.handler.session.music.listener.MusicListenerWapper
    public void onPlayModeChanged(String mode) {
        LogMgr.d(TAG, "firePlayModeChanged-->mode:" + mode);
        if (this.reportHandler != null && this.reportHandler.getLooper().getThread().isAlive()) {
            Message msg = Message.obtain();
            msg.what = 2;
            msg.obj = mode;
            this.reportHandler.handleMessage(msg);
        }
    }

    @Override // com.unisound.vui.handler.session.music.listener.MusicListenerWapper
    public void onStatusChanged(int state) {
        if (this.reportHandler != null && this.reportHandler.getLooper().getThread().isAlive()) {
            if (state == 3 || state == 2 || state == 1) {
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = Integer.valueOf(state);
                this.reportHandler.handleMessage(msg);
            }
        }
    }

    @Override // com.unisound.vui.handler.session.music.listener.MusicListenerWapper
    public void onItemOperateCommand(int operate) {
        if (this.reportHandler != null && this.reportHandler.getLooper().getThread().isAlive()) {
            Message msg = Message.obtain();
            msg.what = 4;
            msg.obj = Integer.valueOf(operate);
            this.reportHandler.handleMessage(msg);
        }
    }

    @Override // com.unisound.ant.device.listener.VolumeListener
    public void onVolumeChanged(Integer current, Integer max, Float percent) {
        LogMgr.d(TAG, "onVolumeChanged-->current:" + current + ",parcent:" + percent);
        if (this.reportHandler != null && this.reportHandler.getLooper().getThread().isAlive()) {
            Message msg = Message.obtain();
            msg.what = 3;
            msg.obj = current;
            this.reportHandler.handleMessage(msg);
        }
    }

    public class ReportHandler extends Handler {
        public ReportHandler() {
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (MusicStateMgr.this.playController == null) {
                LogMgr.e(MusicStateMgr.TAG, "-->>playController == null ");
                return;
            }
            PlayItem item = MusicStateMgr.this.playController.getCurrPlayItem();
            if (item == null) {
                LogMgr.e(MusicStateMgr.TAG, "---------error while report music status, currentItem == null");
                return;
            }
            LogMgr.d(MusicStateMgr.TAG, "update music info, title = " + item.getTitle() + ", id = " + item.getId());
            PlayItem.ItemType itemType = item.getType();
            MusicData musicData = null;
            String dstState = "";
            String operate = "none";
            switch (msg.what) {
                case 1:
                    int state = ((Integer) msg.obj).intValue();
                    operate = MusicStateMgr.this.getPlayStatusOperate(itemType);
                    dstState = MusicStateMgr.this.getPlayStatusDstState(state, itemType);
                    if (state != 3) {
                        if (state != 2) {
                            if (state == 1) {
                                musicData = MusicStatusUtils.packageDataPlayStatusStop();
                                break;
                            }
                        } else {
                            musicData = MusicStatusUtils.packageDataPlayStatusPause();
                            break;
                        }
                    } else {
                        musicData = MusicStatusUtils.packageDataAllInfo(MusicStateMgr.this.playController.getPlayStatus(state), MusicStateMgr.this.parsePlayMode(MusicStateMgr.this.playController.getPlayMode()));
                        break;
                    }
                    break;
                case 2:
                    String playMode = (String) msg.obj;
                    LogMgr.d(MusicStateMgr.TAG, "current playMode:" + playMode);
                    musicData = MusicStatusUtils.packageDataPlayMode(playMode);
                    operate = CommandOperate.COMMAND_OPERATE_MUSIC_CHANGE_PLAY_MODE;
                    break;
                case 3:
                    if (msg.obj != null) {
                        musicData = MusicStatusUtils.packageDataVolume(((Integer) msg.obj).intValue());
                    } else {
                        LogMgr.w("error while report music status, volume == null");
                    }
                    operate = "changeVolume";
                    break;
                case 4:
                    operate = CommandOperate.COMMAND_OPERATE_MUSIC_COLLECT;
                    musicData = MusicStatusUtils.packageDataCollectionStatus(((Integer) msg.obj).intValue() == 20);
                    break;
            }
            if (musicData != null) {
                musicData.setItemId(item.getId());
                if (itemType == PlayItem.ItemType.TYPE_MUSIC) {
                    String listId = item.getListId();
                    if (listId == null) {
                        listId = UUID.randomUUID().toString();
                    }
                    musicData.setListId(listId);
                }
                MusicStateMgr.this.updateMusicExecuteStatus(itemType, dstState, operate, musicData);
                return;
            }
            LogMgr.w(MusicStateMgr.TAG, "error while report music status, operate = +" + operate + ", item = " + item);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @NonNull
    private String getPlayStatusOperate(PlayItem.ItemType itemType) {
        switch (itemType) {
            case TYPE_NEWS:
                return "changeNewsPlayState";
            case TYPE_MUSIC:
                return "changePlayState";
            default:
                return "changePlayState";
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @NonNull
    private String getPlayStatusDstState(int playStatus, PlayItem.ItemType itemType) {
        if (playStatus == 3) {
            switch (itemType) {
                case TYPE_NEWS:
                    return DstServiceState.SERVICE_STATE_NEWS_PLAYING;
                case TYPE_MUSIC:
                    return DstServiceState.SERVICE_STATE_MUSIC_PLAYING;
                case TYPE_AUDIO:
                    return DstServiceState.SERVICE_STATE_AUDIO_PLAYING;
                default:
                    return DstServiceState.SERVICE_STATE_MUSIC_PLAYING;
            }
        } else if (playStatus == 2) {
            switch (itemType) {
                case TYPE_NEWS:
                    return DstServiceState.SERVICE_STATE_NEWS_PAUSE;
                case TYPE_MUSIC:
                    return DstServiceState.SERVICE_STATE_MUSIC_PAUSE;
                case TYPE_AUDIO:
                    return DstServiceState.SERVICE_STATE_AUDIO_PAUSE;
                default:
                    return DstServiceState.SERVICE_STATE_MUSIC_PAUSE;
            }
        } else if (playStatus != 0) {
            return "";
        } else {
            switch (itemType) {
                case TYPE_NEWS:
                    return DstServiceState.SERVICE_STATE_NEWS_EXIT;
                case TYPE_MUSIC:
                    return DstServiceState.SERVICE_STATE_MUSIC_EXIT;
                case TYPE_AUDIO:
                    return DstServiceState.SERVICE_STATE_AUDIO_EXIT;
                default:
                    return DstServiceState.SERVICE_STATE_MUSIC_EXIT;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateMusicExecuteStatus(PlayItem.ItemType type, String dstStatus, String operate, MusicData data) {
        if (SessionRegister.getUpDownMessageManager() == null) {
            LogMgr.d(TAG, "--->>messgaeMonitor is null");
            return;
        }
        switch (type) {
            case TYPE_NEWS:
                SessionRegister.getUpDownMessageManager().onReportNewsStatus(dstStatus, operate, data);
                return;
            case TYPE_MUSIC:
                SessionRegister.getUpDownMessageManager().onReportMusicStatus(dstStatus, operate, data);
                return;
            case TYPE_AUDIO:
                SessionRegister.getUpDownMessageManager().onReportAudioStatus(dstStatus, operate, data);
                return;
            default:
                LogMgr.w(TAG, "error report type " + type + ", cancel reporting");
                return;
        }
    }

    public ActionResponse getActionResponse(int statusCode) {
        ActionResponse response = new ActionResponse();
        response.setActionStatus(statusCode);
        response.setDetailInfo(ActionStatus.getStateDetail(statusCode));
        response.setActionResponseId(UUID.randomUUID().toString());
        response.setActionTimestamp(System.currentTimeMillis() + "");
        return response;
    }

    /* access modifiers changed from: private */
    public class PlayListCallback implements retrofit2.Callback<CurrentMusicList> {
        private String itemId;

        public PlayListCallback(String itemId2) {
            this.itemId = itemId2;
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CurrentMusicList> call, Response<CurrentMusicList> response) {
            String str;
            CurrentMusicList body = response.body();
            if (body == null || body.getStatus() != 200) {
                StringBuilder append = new StringBuilder().append("获取当前播放的音乐收藏列表失败, ");
                if (body == null) {
                    str = "responseBody == null";
                } else {
                    str = "status = " + body.getStatus() + ", detailInfo = " + body.getDetailInfo();
                }
                LogMgr.e(MusicStateMgr.TAG, append.append(str).toString());
                return;
            }
            List<MusicResult.Music> itemList = body.getControlInfo().getResult();
            if (itemList != null && itemList.size() != 0) {
                MusicStateMgr.this.buttonControl.playMusicListWithIndex(JsonTool.toJson(new DeviceMusicData(MusicStateMgr.this.findIndexById(PlayItemAdapter.adaptMusic(itemList), this.itemId), itemList)));
            }
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CurrentMusicList> call, Throwable t) {
            LogMgr.e(MusicStateMgr.TAG, "获取当前播放的音乐收藏列表失败 : " + t.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int findIndexById(List<PlayItem> itemList, String itemId) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId().equals(itemId)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public class AudioPlayListCallback implements retrofit2.Callback<AudioListResult> {
        private String albumId;
        private String itemId;
        private int pageCount = 2;
        private int pageNo = 1;
        private int pageSize = 30;

        public AudioPlayListCallback(String itemId2, String albumId2) {
            this.itemId = itemId2;
            this.albumId = albumId2;
        }

        public AudioPlayListCallback(String itemId2, String albumId2, int pageNo2, int pageSize2, int pageCount2) {
            this.itemId = itemId2;
            this.albumId = albumId2;
            this.pageNo = pageNo2;
            this.pageSize = pageSize2;
            this.pageCount = pageCount2;
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<AudioListResult> call, Response<AudioListResult> response) {
            String str;
            AudioListResult body = response.body();
            if (body == null || body.getStatus() != 200) {
                StringBuilder append = new StringBuilder().append("获取当前播放的有声读物收藏列表失败, ");
                if (body == null) {
                    str = "responseBody == null";
                } else {
                    str = "status = " + body.getStatus() + ", detailInfo = " + body.getDetailInfo();
                }
                LogMgr.e(MusicStateMgr.TAG, append.append(str).toString());
                return;
            }
            List<AudioResult.Music> itemList = body.getControlInfo().getResult();
            if (itemList != null && itemList.size() != 0) {
                MusicStateMgr.this.buttonControl.playAudioListWithIndex(JsonTool.toJson(new DeviceAudioData(MusicStateMgr.this.findIndexById(PlayItemAdapter.adaptAudio(itemList, null, null), this.itemId), this.albumId, itemList, this.pageNo, this.pageSize, this.pageCount)));
            }
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<AudioListResult> call, Throwable t) {
            LogMgr.e(MusicStateMgr.TAG, "获取当前播放的有声读物收藏列表失败 : " + t.getMessage());
        }
    }
}

package com.unisound.vui.handler.session.chat;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.yunzhisheng.common.PinyinConverter;
import com.phicomm.speaker.device.R;
import com.unisound.ant.device.DeviceCenterHandler;
import com.unisound.vui.common.media.IMediaPlayerStateListener;
import com.unisound.vui.common.media.MusicPlayer;
import com.unisound.vui.common.media.PlayItem;
import com.unisound.vui.common.media.PlayerState;
import com.unisound.vui.common.media.UniExoPlayer;
import com.unisound.vui.common.media.UniMediaPlayer;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SessionRegister;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.util.ExoConstants;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.UserPerferenceUtil;
import com.unisound.vui.util.internal.RandomHelper;
import java.util.ArrayList;
import java.util.List;
import nluparser.scheme.NLU;
import nluparser.scheme.SName;

public class DefaultChatHandler extends SimpleUserEventInboundHandler<NLU> implements IMediaPlayerStateListener, MusicPlayer.Callback, MusicPlayer.Renderer {
    private static final String CHAT_STYLE_JOKE = "joke";
    private static final String CHAT_STYLE_MAYBE_NOSIE = "maybe_noise";
    private static final String CHAT_STYLE_POEM = "poem";
    private static final String CHAT_STYLE_TRANSLATION = "translation";
    private static final String CHAT_STYLE_UNKNOW = "unknown";
    private static final String TAG = "DefaultChatHandler";
    private PlayItem.ItemType currentItemType;
    private boolean isNeedPlayAudio = false;
    private int playChatState = PlayerState.MPS_ERROR;
    private List<String> playUrls = new ArrayList();
    private UniExoPlayer player;
    private boolean resourcesFormateError = false;

    public DefaultChatHandler() {
        this.sessionName = SessionRegister.SESSION_CHAT;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    /* access modifiers changed from: protected */
    @Override
    public boolean acceptInboundEvent0(NLU evt) throws Exception {
        return SName.CHAT.equals(evt.getService());
    }

    /* access modifiers changed from: protected */
    @Override
    public void eventReceived(NLU evt, ANTHandlerContext ctx) throws Exception {
        super.eventReceived(evt, ctx);
        String playContent = RandomHelper.getRandom(ctx.androidContext().getResources().getStringArray(R.array.tts_no_voice_input));
        if (evt.getGeneral() != null && !TextUtils.isEmpty(evt.getGeneral().getText())) {
            for (String str : UserPerferenceUtil.getActuallyMainWakeupWord(ctx.androidContext())) {
                if (!TextUtils.isEmpty(evt.getText()) && evt.getText().contains(str)) {
                    this.isNeedPlayAudio = false;
                    ctx.pipeline().fireASRResult(3201, "{\"local_asr\":[{\"engine_mode\":\"wakeup\",\"result_type\":\"full\",\"recognition_result\":\"  小讯小讯   \",\"score\":6.08,\"utteranceTime\":1230,\"outRecordingTime\":212210,\"delayTime\":280}]}");
                    reset();
                    return;
                }
            }
            String style = evt.getGeneral().getStyle();
            LogMgr.d(TAG, "style = " + style + "  evt.getGeneral()=" + evt.getGeneral().toString());
            final String url = evt.getGeneral().getUrl();
            if (!TextUtils.isEmpty(style)) {
                final String audio = evt.getGeneral().getAudio();
                if ("joke".equals(style) && !TextUtils.isEmpty(url)) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        /* class com.unisound.vui.handler.session.chat.DefaultChatHandler.AnonymousClass1 */

                        public void run() {
                            DefaultChatHandler.this.playAudioResource(url);
                        }
                    }, 800);
                    playContent = ctx.androidContext().getString(R.string.tts_chat_play_positive);
                } else if ("translation".equals(style) && !TextUtils.isEmpty(audio)) {
                    playResource(audio);
                    ctx.enterWakeup(false);
                    sendFullLogToDeviceCenter(evt, evt.getGeneral().getText());
                    return;
                } else if ("poem".equals(style) && !TextUtils.isEmpty(audio)) {
                    this.resourcesFormateError = false;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        /* class com.unisound.vui.handler.session.chat.DefaultChatHandler.AnonymousClass2 */

                        public void run() {
                            DefaultChatHandler.this.playResource(audio);
                        }
                    }, 800);
                    playContent = ctx.androidContext().getString(R.string.tts_chat_play_positive);
                } else if ("unknown".equals(style)) {
                    playContent = RandomHelper.getRandom(R.array.tts_unsupport_answer, ctx.androidContext());
                } else if ("maybe_noise".equals(style)) {
                    playContent = RandomHelper.getRandom(R.array.tts_unsupport_answer, ctx.androidContext());
                } else {
                    playContent = evt.getGeneral().getText();
                }
            } else if (!TextUtils.isEmpty(url)) {
                playAudioResource(url);
                playContent = ctx.androidContext().getString(R.string.tts_chat_play_chat);
            } else {
                playContent = evt.getGeneral().getText();
            }
            if (!TextUtils.isEmpty(evt.getExtraFlag())) {
                playContent = evt.getGeneral().getText();
                DeviceCenterHandler.getDeviceCenterMgr().onSessionToSceneControl("start");
            }
        }
        String text = evt.getText();
        if ("现在几点".equals(text) || "几点".equals(text) || "在几点".equals(text) || "几点了".equals(text) || "在几点了".equals(text) || "现在几点了".equals(text) || "时间".equals(text) || "在时间".equals(text) || "现在时间".equals(text) || "现在的时间".equals(text) || "前时间".equals(text) || "当前时间".equals(text)) {
            try {
                String[] content = playContent.split(PinyinConverter.PINYIN_SEPARATOR);
                playContent = content[0].substring(0, 5) + content[1];
            } catch (Exception e) {
            }
        }
        ctx.playTTS(playContent);
        sendFullLogToDeviceCenter(evt, playContent);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onASREventEngineInitDone(ANTHandlerContext ctx) {
        return super.onASREventEngineInitDone(ctx);
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        LogMgr.d(TAG, "onTTSEventPlayingEnd-- eventReceived :" + this.eventReceived + ",isNeedPlayAudio:" + this.isNeedPlayAudio + ",playChatState:" + this.playChatState);
        if (!this.eventReceived) {
            return super.onTTSEventPlayingEnd(ctx);
        }
        if (this.resourcesFormateError) {
            this.resourcesFormateError = false;
            onChatEnd();
            return true;
        } else if (!this.isNeedPlayAudio) {
            onChatEnd();
            return true;
        } else if (this.playChatState != 5) {
            return true;
        } else {
            UniMediaPlayer.getInstance().start();
            this.isNeedPlayAudio = false;
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx, String interruptType) {
        if (this.eventReceived) {
            LogMgr.d(TAG, "doInterrupt");
            ctx.cancelTTS();
            if (!ExoConstants.DO_ONE_SHOT_INTERRUPT.equals(interruptType)) {
                ctx.cancelEngine();
            }
            this.playChatState = PlayerState.MPS_ERROR;
            reset();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void reset() {
        UniMediaPlayer.getInstance().stop();
        if (this.player != null && this.player.isPlaying()) {
            this.player.stop();
        }
        super.reset();
        this.isNeedPlayAudio = false;
        this.playChatState = PlayerState.MPS_ERROR;
    }

    @Override // com.unisound.vui.common.media.IMediaPlayerStateListener
    public void onPlayerState(int state, String playtag) {
        LogMgr.d(TAG, "onPlayerState  state :" + state + "; playtag :" + playtag + " ;eventReceived :" + this.eventReceived);
        this.playChatState = state;
        if (playtag != null && this.playUrls.contains(playtag) && state != 3) {
            if (state == 1) {
                this.ctx.enterWakeup(false);
            } else if (state == 4) {
                onChatEnd();
                this.ctx.pipeline().fireTTSEvent(2107);
            } else if (state == 5) {
                LogMgr.d(TAG, "isNeedPlayAudio : " + this.isNeedPlayAudio);
                if (this.isNeedPlayAudio) {
                    UniMediaPlayer.getInstance().start();
                    this.isNeedPlayAudio = false;
                }
            } else if (state == -1001) {
                onChatEnd();
                this.ctx.pipeline().fireTTSEvent(2107);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void playAudioResource(String url) {
        LogMgr.d(TAG, "playAudioResource url : " + url);
        if (this.playUrls != null) {
            this.playUrls.clear();
            this.playUrls.add(url);
        }
        this.isNeedPlayAudio = true;
        UniMediaPlayer.getInstance().playUrl(url, url, this, true);
    }

    @Override // com.unisound.vui.common.media.MusicPlayer.Callback
    public void onPlayStateChanged(int state) {
        LogMgr.d(TAG, "onPlayStateChanged state : " + state);
        if (state == 4) {
            onChatEnd();
            this.ctx.pipeline().fireTTSEvent(2107);
        }
    }

    @Override // com.unisound.vui.common.media.MusicPlayer.Callback
    public void onOperateCommandChange(int operate) {
        LogMgr.d(TAG, "onOperateCommandChange operate : " + operate);
    }

    @Override // com.unisound.vui.common.media.MusicPlayer.Callback
    public void onPlayerError(String errorMessage) {
        LogMgr.d(TAG, "onPlayerError errorMessage : " + errorMessage);
        if (TextUtils.isEmpty(errorMessage) || !errorMessage.contains("UnrecognizedInputFormatException")) {
            onChatEnd();
            this.ctx.pipeline().fireTTSEvent(2107);
            return;
        }
        this.resourcesFormateError = true;
        this.player.release();
        this.player = null;
        this.isNeedPlayAudio = false;
        this.ctx.playTTS(RandomHelper.getRandom(R.array.tts_unsupport_answer, this.ctx.androidContext()));
    }

    @Override // com.unisound.vui.common.media.MusicPlayer.Renderer
    public MusicPlayer.Renderer.RendererType getRendererType() {
        switch (this.currentItemType) {
            case TYPE_MUSIC:
                return MusicPlayer.Renderer.RendererType.TYPE_MUSIC;
            case TYPE_AUDIO:
                return MusicPlayer.Renderer.RendererType.TYPE_AUDIO;
            case TYPE_RADIO:
                return MusicPlayer.Renderer.RendererType.TYPE_RADIO;
            default:
                return MusicPlayer.Renderer.RendererType.TYPE_MUSIC;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void playResource(String path) {
        if (this.player == null) {
            this.player = new UniExoPlayer.ExoplayerFactory(this.ctx.androidContext()).newInstance();
            this.player.registerCallback(this);
            this.player.setRenderer(this);
            this.currentItemType = PlayItem.ItemType.TYPE_AUDIO;
        }
        this.player.play(path, true);
        this.isNeedPlayAudio = true;
    }

    private void onChatEnd() {
        this.ctx.enterWakeup(false);
        reset();
        fireResume(this.ctx);
    }
}

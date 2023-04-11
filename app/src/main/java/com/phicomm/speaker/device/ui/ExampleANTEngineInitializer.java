package com.phicomm.speaker.device.ui;

import android.content.Context;
import com.phicomm.speaker.device.custom.api.CustomApiManager;
import com.phicomm.speaker.device.custom.handler.PhicommAlarmHandler;
import com.phicomm.speaker.device.custom.handler.PhicommBindStatusHandler;
import com.phicomm.speaker.device.custom.handler.PhicommDataStatisticHandler;
import com.phicomm.speaker.device.custom.handler.PhicommExternalMusicHandler;
import com.phicomm.speaker.device.custom.handler.PhicommInitializeHandler;
import com.phicomm.speaker.device.custom.handler.PhicommInterceptHandler;
import com.phicomm.speaker.device.custom.handler.PhicommMusicHandler;
import com.phicomm.speaker.device.custom.handler.PhicommMusicScheduleHandler;
import com.phicomm.speaker.device.custom.handler.PhicommReminderHandler;
import com.phicomm.speaker.device.custom.handler.PhicommStatusHandler;
import com.phicomm.speaker.device.custom.handler.PhicommTTSHandler;
import com.phicomm.speaker.device.custom.handler.PhicommWakeupWordChangedHandler;
import com.phicomm.speaker.device.custom.lights.PhicommLightListener;
import com.phicomm.speaker.device.custom.music.PhicommExternalPlayer;
import com.phicomm.speaker.device.custom.music.PhicommPlayer;
import com.phicomm.speaker.device.custom.ringing.CustomRingingProcessor;
import com.phicomm.speaker.device.custom.ringing.RingingEventProcessor;
import com.phicomm.speaker.device.custom.setting.PhicommSettingHandler;
import com.unisound.ant.device.DeviceCenterHandler;
import com.unisound.ant.platform.illeagechat.DefaultIlleageChatHandler;
import com.unisound.ant.platform.smarthome.DefaultSmartHomeHandler;
import com.unisound.vui.engine.ANTEngine;
import com.unisound.vui.engine.ANTEngineInitializer;
import com.unisound.vui.engine.ANTEngineOption;
import com.unisound.vui.engine.ANTPipeline;
import com.unisound.vui.handler.SimpleSessionManager;
import com.unisound.vui.handler.filter.NLUDispatcher;
import com.unisound.vui.handler.launch.DefaultANTLaunchHandler;
import com.unisound.vui.handler.session.DefaultGuideHandler;
import com.unisound.vui.handler.session.DefaultUnSupportHandler;
import com.unisound.vui.handler.session.chat.DefaultChatHandler;
import com.unisound.vui.handler.session.light.DefaultLightsHandler;
import com.unisound.vui.handler.session.memo.DefaultMemoRingingHandler;
import com.unisound.vui.handler.session.memo.DefaultNoteHandler;
import com.unisound.vui.handler.session.setting.DefaultSettingHandler;
import com.unisound.vui.handler.session.stock.DefaultStockHandler;
import com.unisound.vui.handler.session.weather.DefaultWeatherHandler;
import nluparser.MixtureProcessor;

public final class ExampleANTEngineInitializer extends ANTEngineInitializer {
    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTEngineInitializer
    public void onEngineInitDone(ANTEngine engine) {
        Context context = engine.androidContext();
        ANTPipeline pipeline = engine.pipeline();
        MixtureProcessor mixtureProcessor = new MixtureProcessor.Builder().build();
        DeviceCenterHandler deviceCenterMgr = DeviceCenterHandler.getDeviceCenterMgr();
        pipeline.addLast(new NLUDispatcher(mixtureProcessor));
        pipeline.addLast(new PhicommDataStatisticHandler(context, mixtureProcessor));
        pipeline.addLast(new PhicommReminderHandler(context));
        pipeline.addLast(new PhicommAlarmHandler(context));
        DefaultMemoRingingHandler ringingHandler = new DefaultMemoRingingHandler();
        ringingHandler.setOnRingingListener(new RingingEventProcessor(engine, context));
        pipeline.addLast(ringingHandler);
        PhicommLightListener lightListener = new PhicommLightListener(context);
        pipeline.addLast(new PhicommInterceptHandler(lightListener));
        pipeline.addLast(new DefaultLightsHandler(lightListener, context));
        pipeline.addLast(new PhicommTTSHandler());
        pipeline.addLast(new SimpleSessionManager());
        pipeline.addLast(deviceCenterMgr.associateEngine(engine, mixtureProcessor));
        pipeline.addLast(new PhicommWakeupWordChangedHandler());
        pipeline.addLast(new PhicommBindStatusHandler(context, engine));
        pipeline.addLast(new DefaultANTLaunchHandler(context));
        pipeline.addLast(new DefaultWeatherHandler());
        pipeline.addLast(new DefaultStockHandler());
        pipeline.addLast(new PhicommExternalMusicHandler(new PhicommExternalPlayer(context), context));
        PhicommPlayer phicommPlayer = new PhicommPlayer(context, (String) engine.config().getOption(ANTEngineOption.GENERAL_UDID), engine);
        pipeline.addLast(new PhicommMusicHandler(phicommPlayer));
        pipeline.addLast(new PhicommMusicScheduleHandler());
        pipeline.addLast(new DefaultNoteHandler());
        pipeline.addLast(new DefaultSettingHandler(new PhicommSettingHandler(context, engine)));
        pipeline.addLast(new DefaultChatHandler());
        pipeline.addLast(new DefaultGuideHandler());
        pipeline.addLast(new DefaultIlleageChatHandler());
        pipeline.addLast(new DefaultSmartHomeHandler());
        pipeline.addLast(new DefaultUnSupportHandler(context));
        pipeline.addLast(new PhicommInitializeHandler());
        pipeline.addLast(new PhicommStatusHandler());
        new CustomApiManager(context, engine, phicommPlayer).addListener(0, new CustomRingingProcessor(ringingHandler));
    }
}

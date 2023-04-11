package com.unisound.vui.engine;

import com.unisound.vui.bootstrap.ANTELocalConfiguration;
import com.unisound.vui.transport.MessageCodec;

import java.util.List;
import java.util.Map;

public interface ANTEngineConfig {
    ANTELocalConfiguration getLocalConfiguration();

    String getMainTag();

    Map<String, List<String>> getMainVocab();

    MessageCodec getMessageCodec();

    <T> T getOption(ANTEngineOption<T> aNTEngineOption);

    Map<ANTEngineOption<?>, Object> getOptions();

    String getWakeupOneshotTag();

    String getWakeupTag();

    boolean isPrintEngineLog();

    ANTEngineConfig setLocalConfiguration(ANTELocalConfiguration aNTELocalConfiguration);

    ANTEngineConfig setMainTag(String str);

    ANTEngineConfig setMainVocab(Map<String, List<String>> map);

    ANTEngineConfig setMessageCodec(MessageCodec messageCodec);

    <T> boolean setOption(ANTEngineOption<T> aNTEngineOption, T t);

    boolean setOptions(Map<ANTEngineOption<?>, ?> map);

    ANTEngineConfig setPrintEngineLog(boolean z);

    ANTEngineConfig setWakeupOneshotTag(String str);

    ANTEngineConfig setWakeupTag(String str);
}

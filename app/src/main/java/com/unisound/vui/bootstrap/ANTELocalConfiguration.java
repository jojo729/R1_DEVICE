package com.unisound.vui.bootstrap;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public final class ANTELocalConfiguration {
    public static final int ENGINE_ASR = 1;
    public static final int ENGINE_TTS = 2;
    public static final int ENGINE_WAKEUP = 0;
    @SerializedName("localConfiguration")
    @JSONField(name = "localConfiguration")
    private HashMap<String, LocalRecognition> localConfiguration = new HashMap<>(3);

    public static final class LocalRecognition {
        @SerializedName("compileJsgfPath")
        @JSONField(name = "compileJsgfPath")
        private String compileJsgfPath;
        @SerializedName("grammerPath")
        @JSONField(name = "grammerPath")
        private String grammerPath;
        @SerializedName("localNluPath")
        @JSONField(name = "localNluPath")
        private String localNluPath;

        public LocalRecognition() {
        }

        public LocalRecognition(String grammerPath2, String compileJsgfPath2, String localNluPath2) {
            this.grammerPath = grammerPath2;
            this.compileJsgfPath = compileJsgfPath2;
            this.localNluPath = localNluPath2;
        }

        public String getCompileJsgfPath() {
            return this.compileJsgfPath;
        }

        public String getGrammerPath() {
            return this.grammerPath;
        }

        public String getLocalNluPath() {
            return this.localNluPath;
        }

        public LocalRecognition setCompileJsgfPath(String compileJsgfPath2) {
            this.compileJsgfPath = compileJsgfPath2;
            return this;
        }

        public LocalRecognition setGrammerPath(String grammerPath2) {
            this.grammerPath = grammerPath2;
            return this;
        }

        public LocalRecognition setLocalNluPath(String localNluPath2) {
            this.localNluPath = localNluPath2;
            return this;
        }
    }

    public void addConfiguration(String asrTag, LocalRecognition localRecognition) {
        this.localConfiguration.put(asrTag, localRecognition);
    }

    public boolean contentTag(String tag) {
        return this.localConfiguration.containsKey(tag);
    }

    public HashMap<String, LocalRecognition> getLocalConfiguration() {
        return this.localConfiguration;
    }

    public LocalRecognition getLocalRecognition(String tag) {
        return this.localConfiguration.get(tag);
    }

    public void setLocalConfiguration(HashMap<String, LocalRecognition> localConfiguration2) {
        this.localConfiguration = localConfiguration2;
    }
}

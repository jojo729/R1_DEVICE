package com.unisound.ant.device.sessionlayer;

public class DialogProfile {
    public static final String DIALOG_FINISH = "2";
    public static final String DIALOG_IDLE = "0";
    public static final String DIALOG_START = "1";
    public static final String SCENE_FLAG_END = "end";
    public static final String SCENE_FLAG_PROCESSING = "processing";
    public static final String SCENE_FLAG_START = "start";
    public String dstService;
    public String dstState;
    public String sceneFlag;
    public TerminalResponse sendToCloudResponse;
    public TerminalResponse sendToTerminalResponse;

    public String getDstState() {
        return this.dstState;
    }

    public void setDstState(String dstState2) {
        this.dstState = dstState2;
    }

    public String getDstService() {
        return this.dstService;
    }

    public void setDstService(String dstService2) {
        this.dstService = dstService2;
    }

    public TerminalResponse getSendToCloudResponse() {
        return this.sendToCloudResponse;
    }

    public void setSendToCloudResponse(TerminalResponse sendToCloudResponse2) {
        this.sendToCloudResponse = sendToCloudResponse2;
    }

    public TerminalResponse getSendToTerminalResponse() {
        return this.sendToTerminalResponse;
    }

    public void setSendToTerminalResponse(TerminalResponse sendToTerminalResponse2) {
        this.sendToTerminalResponse = sendToTerminalResponse2;
    }

    public static class TerminalResponse {
        private String actionDstServiceId;
        private String actionResponseId;
        private long actionTimestamp = System.currentTimeMillis();

        public TerminalResponse(String actionResponseId2) {
            this.actionResponseId = actionResponseId2;
        }

        public String getActionResponseId() {
            return this.actionResponseId;
        }

        public void setActionResponseId(String actionResponseId2) {
            this.actionResponseId = actionResponseId2;
        }

        public long getActionTimestamp() {
            return this.actionTimestamp;
        }

        public void setActionTimestamp(long actionTimestamp2) {
            this.actionTimestamp = actionTimestamp2;
        }

        public String getActionDstServiceId() {
            return this.actionDstServiceId;
        }

        public void setActionDstServiceId(String actionDstServiceId2) {
            this.actionDstServiceId = actionDstServiceId2;
        }
    }

    public String getSceneFlag() {
        return this.sceneFlag;
    }

    public void setSceneFlag(String sceneFlag2) {
        this.sceneFlag = sceneFlag2;
    }
}

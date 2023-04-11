package com.unisound.ant.device.mqtt.bean;

import java.util.List;

public class MqttMessage {
    private int costTime;
    private String message;
    private Result result;
    private String returnCode;

    public int getCostTime() {
        return this.costTime;
    }

    public void setCostTime(int costTime2) {
        this.costTime = costTime2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public String getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(String returnCode2) {
        this.returnCode = returnCode2;
    }

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result2) {
        this.result = result2;
    }

    public static class Result {
        private String clientId;
        private MqttConnection connection;
        private List<ChangeMessage> msgList;
        private int totalCount;

        public String getClientId() {
            return this.clientId;
        }

        public void setClientId(String clientId2) {
            this.clientId = clientId2;
        }

        public MqttConnection getConnection() {
            return this.connection;
        }

        public void setConnection(MqttConnection connection2) {
            this.connection = connection2;
        }

        public int getTotalCount() {
            return this.totalCount;
        }

        public void setTotalCount(int totalCount2) {
            this.totalCount = totalCount2;
        }

        public List<ChangeMessage> getMsgList() {
            return this.msgList;
        }

        public void setMsgList(List<ChangeMessage> msgList2) {
            this.msgList = msgList2;
        }

        public String toString() {
            return "Result{clientId='" + this.clientId + '\'' + ", connection=" + this.connection + ", totalCount=" + this.totalCount + ", msgList=" + this.msgList + '}';
        }
    }

    public String toString() {
        return "MqttMessage{costTime=" + this.costTime + ", message='" + this.message + '\'' + ", returnCode='" + this.returnCode + '\'' + ", result=" + this.result + '}';
    }
}

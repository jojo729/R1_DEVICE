package com.unisound.ant.device.mqtt.bean;

public class LconResponse {
    LconData data;
    String detailInfo;
    String statusCode;

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode2) {
        this.statusCode = statusCode2;
    }

    public String getDetailInfo() {
        return this.detailInfo;
    }

    public void setDetailInfo(String detailInfo2) {
        this.detailInfo = detailInfo2;
    }

    public LconData getData() {
        return this.data;
    }

    public void setData(LconData data2) {
        this.data = data2;
    }

    public static class LconData {
        String connAccessId;
        String queryIP;
        String queryPort;

        public String getQueryIP() {
            return this.queryIP;
        }

        public void setQueryIP(String queryIP2) {
            this.queryIP = queryIP2;
        }

        public String getQueryPort() {
            return this.queryPort;
        }

        public void setQueryPort(String queryPort2) {
            this.queryPort = queryPort2;
        }

        public String getConnAccessId() {
            return this.connAccessId;
        }

        public void setConnAccessId(String connAccessId2) {
            this.connAccessId = connAccessId2;
        }
    }
}

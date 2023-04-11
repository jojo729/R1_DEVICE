package com.unisound.ant.device.mqtt.bean;

public class LconRequest<T> {
    String command;
    T data;
    EffectiveToken tcl;
    String version;

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version2) {
        this.version = version2;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command2) {
        this.command = command2;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data2) {
        this.data = data2;
    }

    public EffectiveToken getTcl() {
        return this.tcl;
    }

    public void setTcl(EffectiveToken tcl2) {
        this.tcl = tcl2;
    }

    public static class EffectiveToken {
        private String clientId;
        private String token;

        public EffectiveToken(String clientId2, String token2) {
            this.clientId = clientId2;
            this.token = token2;
        }

        public String getClientId() {
            return this.clientId;
        }

        public void setClientId(String clientId2) {
            this.clientId = clientId2;
        }

        public String getToken() {
            return this.token;
        }

        public void setToken(String token2) {
            this.token = token2;
        }
    }
}

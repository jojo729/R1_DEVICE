package com.unisound.ant.device.netmodule.request;

public class RequestBody<T> {
    private String businessType;
    private String command;
    private T data;
    private ClientInfo tcl;
    private String version = "2.0.0";

    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String businessType2) {
        this.businessType = businessType2;
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

    public ClientInfo getTcl() {
        return this.tcl;
    }

    public void setTcl(ClientInfo tcl2) {
        this.tcl = tcl2;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version2) {
        this.version = version2;
    }

    public static class ClientInfo {
        private String clientId;
        private int subSysId = 9;
        private String token;

        public String getClientId() {
            return this.clientId;
        }

        public void setClientId(String clientId2) {
            this.clientId = clientId2;
        }

        public int getSubSysId() {
            return this.subSysId;
        }

        public void setSubSysId(int subSysId2) {
            this.subSysId = subSysId2;
        }

        public String getToken() {
            return this.token;
        }

        public void setToken(String token2) {
            this.token = token2;
        }
    }
}

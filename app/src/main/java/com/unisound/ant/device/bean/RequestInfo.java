package com.unisound.ant.device.bean;

public class RequestInfo {
    private String businessType;
    private String command;
    private PageInfo data;
    private ClientInfo tcl;
    private String version;

    public RequestInfo(String businessType2, String command2, PageInfo data2, ClientInfo tcl2, String version2) {
        this.businessType = businessType2;
        this.command = command2;
        this.data = data2;
        this.tcl = tcl2;
        this.version = version2;
    }

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

    public PageInfo getData() {
        return this.data;
    }

    public void setData(PageInfo data2) {
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

    public static class PageInfo {
        private String albumId;
        private String pageNo;
        private String pageSize;
        private String timeAsc;
        private String udid;

        public PageInfo(String pageNo2, String pageSize2, String udid2) {
            this.pageNo = pageNo2;
            this.pageSize = pageSize2;
            this.udid = udid2;
        }

        public PageInfo(String pageNo2, String pageSize2, String udid2, String albumId2, String timeAsc2) {
            this.pageNo = pageNo2;
            this.pageSize = pageSize2;
            this.udid = udid2;
            this.albumId = albumId2;
            this.timeAsc = timeAsc2;
        }

        public String getPageNo() {
            return this.pageNo;
        }

        public void setPageNo(String pageNo2) {
            this.pageNo = pageNo2;
        }

        public String getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(String pageSize2) {
            this.pageSize = pageSize2;
        }

        public String getUdid() {
            return this.udid;
        }

        public void setUdid(String udid2) {
            this.udid = udid2;
        }

        public String getAlbumId() {
            return this.albumId;
        }

        public void setAlbumId(String albumId2) {
            this.albumId = albumId2;
        }

        public String getTimeAsc() {
            return this.timeAsc;
        }

        public void setTimeAsc(String timeAsc2) {
            this.timeAsc = timeAsc2;
        }
    }

    public static class ClientInfo {
        private String clientId;
        private int subSysId;
        private String token;

        public ClientInfo(String clientId2, int subSysId2, String token2) {
            this.clientId = clientId2;
            this.subSysId = subSysId2;
            this.token = token2;
        }

        public ClientInfo(int subSysId2) {
            this.subSysId = subSysId2;
        }

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

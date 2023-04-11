package com.unisound.vui.handler.session.music.network.body;

public class AudioBody {
    private String albumId;
    private String code;
    private String keyword;
    private String pageNo;
    private String pageSize = "30";
    private String udid;

    public AudioBody(String udid2, String albumId2, String pageNo2) {
        this.udid = udid2;
        this.albumId = albumId2;
        this.pageNo = pageNo2;
    }

    public AudioBody(String udid2, String code2, String keyword2, String pageNo2) {
        this.udid = udid2;
        this.code = code2;
        this.keyword = keyword2;
        this.pageNo = pageNo2;
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

    public String getCode() {
        return this.code;
    }

    public void setCode(String code2) {
        this.code = code2;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword2) {
        this.keyword = keyword2;
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
}

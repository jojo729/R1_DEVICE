package com.unisound.ant.device.bean;

import java.util.List;
import nluparser.scheme.AudioResult;

public class DeviceAudioData {
    private String albumId;
    private List<AudioResult.Music> audioList;
    private int index;
    private int pageCount = 2;
    private int pageNo = 1;
    private int pageSize = 1;

    public DeviceAudioData(int index2, String albumId2, List<AudioResult.Music> audioList2) {
        this.index = index2;
        this.albumId = albumId2;
        this.audioList = audioList2;
    }

    public DeviceAudioData(int index2, String albumId2, List<AudioResult.Music> audioList2, int pageNo2, int pageSize2, int pageCount2) {
        this.index = index2;
        this.albumId = albumId2;
        this.audioList = audioList2;
        this.pageNo = pageNo2;
        this.pageSize = pageSize2;
        this.pageCount = pageCount2;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index2) {
        this.index = index2;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId2) {
        this.albumId = albumId2;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo2) {
        this.pageNo = pageNo2;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize2) {
        this.pageSize = pageSize2;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(int pageCount2) {
        this.pageCount = pageCount2;
    }

    public List<AudioResult.Music> getAudioList() {
        return this.audioList;
    }

    public void setAudioList(List<AudioResult.Music> audioList2) {
        this.audioList = audioList2;
    }
}

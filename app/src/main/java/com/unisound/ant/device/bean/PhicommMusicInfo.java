package com.unisound.ant.device.bean;

import java.util.List;

public class PhicommMusicInfo {
    private String asrResult;
    private int index;
    private List<MusicItem> itemList;
    private int pageIndex;
    private int totalPage;

    public PhicommMusicInfo(int index2, List<MusicItem> itemList2) {
        this.index = index2;
        this.itemList = itemList2;
    }

    public PhicommMusicInfo(int index2, List<MusicItem> itemList2, int pageIndex2, int totalPage2) {
        this.index = index2;
        this.itemList = itemList2;
        this.pageIndex = pageIndex2;
        this.totalPage = totalPage2;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index2) {
        this.index = index2;
    }

    public List<MusicItem> getItemList() {
        return this.itemList;
    }

    public void setItemList(List<MusicItem> itemList2) {
        this.itemList = itemList2;
    }

    public String getAsrResult() {
        return this.asrResult;
    }

    public void setAsrResult(String asrResult2) {
        this.asrResult = asrResult2;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(int pageIndex2) {
        this.pageIndex = pageIndex2;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage2) {
        this.totalPage = totalPage2;
    }
}

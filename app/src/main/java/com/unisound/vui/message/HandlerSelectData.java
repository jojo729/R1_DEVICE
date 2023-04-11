package com.unisound.vui.message;

import java.util.ArrayList;
import java.util.List;

public class HandlerSelectData<T> {
    private List<T> data;
    private String extra;
    private HandlerSelectType handlerSelectType;
    private int index;

    public enum HandlerSelectType {
        CONFIRM,
        CANCEL,
        PAGE,
        ITEM,
        LIST,
        DEFAULT
    }

    public static HandlerSelectData getCancelData() {
        HandlerSelectData handlerSelectData = new HandlerSelectData();
        handlerSelectData.setHandlerSelectType(HandlerSelectType.CANCEL);
        return handlerSelectData;
    }

    public static HandlerSelectData getConfirmData() {
        HandlerSelectData handlerSelectData = new HandlerSelectData();
        handlerSelectData.setHandlerSelectType(HandlerSelectType.CONFIRM);
        return handlerSelectData;
    }

    public static HandlerSelectData getSelectItemData(int i) {
        HandlerSelectData handlerSelectData = new HandlerSelectData();
        handlerSelectData.setHandlerSelectType(HandlerSelectType.ITEM);
        handlerSelectData.setIndex(i);
        return handlerSelectData;
    }

    public static <E> HandlerSelectData getSelectListData(E e) {
        HandlerSelectData handlerSelectData = new HandlerSelectData();
        handlerSelectData.setHandlerSelectType(HandlerSelectType.LIST);
        ArrayList arrayList = new ArrayList();
        arrayList.add(e);
        handlerSelectData.setData(arrayList);
        return handlerSelectData;
    }

    public static <E> HandlerSelectData getSelectListData(List<E> list) {
        HandlerSelectData handlerSelectData = new HandlerSelectData();
        handlerSelectData.setHandlerSelectType(HandlerSelectType.LIST);
        handlerSelectData.setData(list);
        return handlerSelectData;
    }

    public static HandlerSelectData getSelectPageData(int i) {
        HandlerSelectData handlerSelectData = new HandlerSelectData();
        handlerSelectData.setHandlerSelectType(HandlerSelectType.PAGE);
        handlerSelectData.setIndex(i);
        return handlerSelectData;
    }

    public List<T> getData() {
        return this.data;
    }

    public String getExtra() {
        return this.extra;
    }

    public HandlerSelectType getHandlerSelectType() {
        return this.handlerSelectType;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isCancelData() {
        return this.handlerSelectType == HandlerSelectType.CANCEL;
    }

    public boolean isConfirmData() {
        return this.handlerSelectType == HandlerSelectType.CONFIRM;
    }

    public void setData(List<T> list) {
        this.data = list;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public void setHandlerSelectType(HandlerSelectType handlerSelectType2) {
        this.handlerSelectType = handlerSelectType2;
    }

    public void setIndex(int i) {
        this.index = i;
    }
}

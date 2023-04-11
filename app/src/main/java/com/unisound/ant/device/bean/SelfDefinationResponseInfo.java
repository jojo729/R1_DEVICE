package com.unisound.ant.device.bean;

public class SelfDefinationResponseInfo<T> {
    private T content;
    private String operationType;
    private int status;

    public SelfDefinationResponseInfo(String operationType2, int status2) {
        this.operationType = operationType2;
        this.status = status2;
    }

    public SelfDefinationResponseInfo(String operationType2, int status2, T content2) {
        this.operationType = operationType2;
        this.status = status2;
        this.content = content2;
    }

    public String getOperationType() {
        return this.operationType;
    }

    public void setOperationType(String operationType2) {
        this.operationType = operationType2;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status2) {
        this.status = status2;
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content2) {
        this.content = content2;
    }
}

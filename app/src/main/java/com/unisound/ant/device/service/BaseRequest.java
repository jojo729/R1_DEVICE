package com.unisound.ant.device.service;

public class BaseRequest<V> {
    public static final String MESSAGE_TYPE_GD_REQUEST = "uploadGDRequest";
    public static final String MESSAGE_TYPE_INTENT_ACK = "intentAck";
    public static final String MESSAGE_TYPE_PD_REQUEST = "uploadPDRequest";
    public static final String MESSAGE_TYPE_SYNC_INFO = "synInfo";
    private V messageBody;
    private String messageType;
    private String version;

    protected BaseRequest(MessageHeader header, V messageBody2) {
        this.messageType = header.getMessageType();
        this.version = header.getVersion();
        this.messageBody = messageBody2;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public String getVersion() {
        return this.version;
    }

    public V getMessageBody() {
        return this.messageBody;
    }

    public static class Builder<E> {
        private E data;
        private MessageHeader header;

        public Builder setRequestHeader(MessageHeader header2) {
            this.header = header2;
            return this;
        }

        public Builder setData(E data2) {
            this.data = data2;
            return this;
        }

        public BaseRequest build() {
            if (this.header != null) {
                return new BaseRequest(this.header, this.data);
            }
            throw new IllegalArgumentException("header may not be null.");
        }
    }
}

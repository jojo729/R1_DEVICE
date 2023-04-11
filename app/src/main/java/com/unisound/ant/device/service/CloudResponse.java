package com.unisound.ant.device.service;

public class CloudResponse<V> {
    public static final String MESSAGE_TYPE_INTENT_ACTION = "intentAction";
    public static final String MESSAGE_TYPE_START_SYN = "startSyn";
    public static final String MESSAGE_TYPE_SYNCH_INFO = "synInfo";
    public static final String MESSAGE_TYPE_UPDATE_REPONSE = "updateAck";
    public static final String MESSAGE_TYPE_UPLOAD_PD_REPONSE = "uploadPDAck";
    private V messageBody;
    private String messageType;
    private String version;

    protected CloudResponse(MessageHeader header, V messageBody2) {
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

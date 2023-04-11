package com.unisound.ant.device.service;

import android.text.TextUtils;

public class MessageHeader {
    private String messageType;
    private String version;

    MessageHeader(String messageType2, String version2) {
        this.messageType = messageType2;
        this.version = version2;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public void setMessageType(String messageType2) {
        this.messageType = messageType2;
    }

    public String getVersion() {
        return this.version;
    }

    public static class Builder {
        private String messageType;
        private String version;

        public Builder setMessageType(String messageType2) {
            this.messageType = messageType2;
            return this;
        }

        public Builder setVersion(String version2) {
            this.version = version2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public void check() {
            if (TextUtils.isEmpty(this.messageType)) {
                throw new IllegalArgumentException("messageType may not be null.");
            } else if (TextUtils.isEmpty(this.version)) {
                throw new IllegalArgumentException("version may not be null.");
            }
        }

        public MessageHeader build() {
            check();
            return new MessageHeader(this.messageType, this.version);
        }
    }
}

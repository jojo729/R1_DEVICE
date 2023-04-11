package com.unisound.ant.device.bean;

import android.text.TextUtils;

public class UnisoundDeviceCommand<T> {
    private String capability;
    private String operation;
    private T parameter;
    private String version;

    public UnisoundDeviceCommand(String version2, String capability2, String operation2, T parameter2) {
        this.version = version2;
        this.capability = capability2;
        this.operation = operation2;
        this.parameter = parameter2;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version2) {
        this.version = version2;
    }

    public String getCapability() {
        return this.capability;
    }

    public void setCapability(String capability2) {
        this.capability = capability2;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation2) {
        this.operation = operation2;
    }

    public T getParameter() {
        return this.parameter;
    }

    public static class Builder<T> {
        private String capability;
        private String operation;
        private T parameter;
        private String version;

        public Builder setCapability(String capability2) {
            this.capability = capability2;
            return this;
        }

        public Builder setOperation(String operation2) {
            this.operation = operation2;
            return this;
        }

        public Builder setVersion(String version2) {
            this.version = version2;
            return this;
        }

        public Builder setParameter(T parameter2) {
            this.parameter = parameter2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public void check() {
            if (TextUtils.isEmpty(this.capability)) {
                throw new IllegalArgumentException("capability may not be null.");
            } else if (TextUtils.isEmpty(this.operation)) {
                throw new IllegalArgumentException("operation may not be null.");
            }
        }

        public UnisoundDeviceCommand build() {
            check();
            return new UnisoundDeviceCommand(this.version, this.capability, this.operation, this.parameter);
        }
    }
}

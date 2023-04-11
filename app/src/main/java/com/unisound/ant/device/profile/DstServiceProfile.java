package com.unisound.ant.device.profile;

import com.unisound.ant.device.bean.Accelerate;
import com.unisound.ant.device.bean.UnisoundDeviceCommand;

public class DstServiceProfile<T> {
    private Accelerate accelerate;
    private String dstServiceName;
    private String dstState;
    private T parameter;
    private UnisoundDeviceCommand<T> uniCommand;

    public String getDstState() {
        return this.dstState;
    }

    public void setDstState(String dstState2) {
        this.dstState = dstState2;
    }

    public String getDstServiceName() {
        return this.dstServiceName;
    }

    public void setDstServiceName(String dstServiceName2) {
        this.dstServiceName = dstServiceName2;
    }

    public UnisoundDeviceCommand<T> getUniCommand() {
        return this.uniCommand;
    }

    public void setUniCommand(UnisoundDeviceCommand<T> uniCommand2) {
        this.uniCommand = uniCommand2;
    }

    public Accelerate getAccelerate() {
        return this.accelerate;
    }

    public void setAccelerate(Accelerate accelerate2) {
        this.accelerate = accelerate2;
    }

    public T getParameter() {
        return this.parameter;
    }

    public void setParameter(T parameter2) {
        this.parameter = parameter2;
    }
}

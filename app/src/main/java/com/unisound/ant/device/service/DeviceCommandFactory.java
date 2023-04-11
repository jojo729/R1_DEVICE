package com.unisound.ant.device.service;

import com.unisound.ant.device.bean.UnisoundDeviceCommand;

public final class DeviceCommandFactory {
    public static <E> UnisoundDeviceCommand<E> buildCommand(String capability, String operation, E parameter) {
        return new UnisoundDeviceCommand.Builder().setVersion("1.10.0").setCapability(capability).setOperation(operation).setParameter(parameter).build();
    }
}

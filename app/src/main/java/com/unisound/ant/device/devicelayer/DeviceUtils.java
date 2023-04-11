package com.unisound.ant.device.devicelayer;

import android.content.Context;
import com.unisound.ant.device.bean.Capabilities;
import com.unisound.ant.device.bean.DeviceCapability;
import com.unisound.ant.device.bean.GeneralInfo;
import com.unisound.ant.device.bean.HardwareParameters;
import com.unisound.ant.device.bean.Operation;
import com.unisound.ant.device.profile.DeviceProfileInfo;
import com.unisound.vui.common.network.NetUtil;
import com.unisound.vui.util.AppGlobalConstant;
import com.unisound.vui.util.SystemUitls;
import java.util.ArrayList;
import java.util.List;

public class DeviceUtils {
    public static HardwareParameters getDeviceInfo(Context context) {
        HardwareParameters deviceInfo = new HardwareParameters();
        deviceInfo.setDeviceId(SystemUitls.getSn());
        deviceInfo.setDeviceType("4Mic");
        deviceInfo.setTotalMemory(SystemUitls.getRamTotalSize(context));
        deviceInfo.setAvailableMemory(SystemUitls.getRamAvailableSize(context));
        deviceInfo.setCpuSize(SystemUitls.getCpuInfo());
        deviceInfo.setRetainCamera(SystemUitls.isSupportCamera(context));
        deviceInfo.setRetainScreen(false);
        deviceInfo.setRetainSpeaker(true);
        deviceInfo.setSurpportBluetooth(false);
        deviceInfo.setSurpportIndoorGps(false);
        deviceInfo.setSurpportGps(SystemUitls.hasGPSDevice(context));
        deviceInfo.setOtherSensor(new ArrayList());
        deviceInfo.setDeviceNetState(getDeviceNetState(context));
        return deviceInfo;
    }

    public static DeviceNetState getDeviceNetState(Context context) {
        DeviceNetState netState = new DeviceNetState();
        netState.setNetType(NetUtil.getNetWorkType(context));
        netState.setInNetIp(NetUtil.getLocalIpAddress());
        netState.setOutNetIp(NetUtil.getOutNetIp());
        return netState;
    }

    private static List<DeviceCapability> getDeviceCapabilites() {
        List<DeviceCapability> capabilites = new ArrayList<>();
        DeviceCapability capabilityMusic = new DeviceCapability();
        capabilityMusic.setCapability(Capabilities.DEVICE_CAPABILITY_PLAYAUDIO);
        List<String> musicOperates = new ArrayList<>();
        musicOperates.add("update");
        musicOperates.add("prev");
        musicOperates.add("next");
        capabilityMusic.setOperations(musicOperates);
        capabilites.add(capabilityMusic);
        DeviceCapability capabilityDeviceSet = new DeviceCapability();
        capabilityMusic.setCapability("deviceManagement");
        List<String> deviceSettingOperates = new ArrayList<>();
        deviceSettingOperates.add("uploadDeviceInfo");
        capabilityDeviceSet.setOperations(deviceSettingOperates);
        capabilites.add(capabilityDeviceSet);
        new DeviceCapability().setCapability(Capabilities.DEVICE_CAPABILITY_CONTROLLER);
        List<String> ControllerOperates = new ArrayList<>();
        ControllerOperates.add(Operation.CONTROLLER_QUERY);
        ControllerOperates.add(Operation.CONTROLLER_CONTROL);
        capabilityDeviceSet.setOperations(ControllerOperates);
        capabilites.add(capabilityDeviceSet);
        return capabilites;
    }

    private static GeneralInfo getGeneralInfo(Context context) {
        GeneralInfo info = new GeneralInfo();
        info.setTotalMemory(SystemUitls.getRamTotalSize(context));
        info.setAvailableMemory(SystemUitls.getRamAvailableSize(context));
        info.setCpuSize(SystemUitls.getCpuInfo());
        info.setRetainCamera(SystemUitls.isSupportCamera(context));
        info.setRetainScreen(false);
        info.setRetainSpeaker(true);
        info.setSurpportBluetooth(false);
        info.setSurpportIndoorGps(false);
        info.setSurpportGps(SystemUitls.hasGPSDevice(context));
        return info;
    }

    public static DeviceProfileInfo getDeviceProfile(Context context) {
        DeviceProfileInfo profile = new DeviceProfileInfo();
        profile.setUdid(AppGlobalConstant.getUdid());
        profile.setCapabilites(getDeviceCapabilites());
        profile.setGeneralInfo(getGeneralInfo(context));
        return profile;
    }
}

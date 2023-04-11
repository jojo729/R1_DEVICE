package com.phicomm.speaker.device.custom.handler;

import com.phicomm.speaker.device.custom.outputevents.BluetoothOutputEvent;
import com.phicomm.speaker.device.custom.outputevents.DormantMessageEvent;
import com.phicomm.speaker.device.custom.outputevents.DormantOutputEvent;
import com.phicomm.speaker.device.custom.outputevents.NetworkConfigOutputEvent;
import com.phicomm.speaker.device.custom.status.PhicommDeviceStatusProcessor;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.engine.ANTOutboundHandlerAdapter;
import com.unisound.vui.handler.session.music.outputevents.MusicOutputEvents;

public class PhicommStatusHandler extends ANTOutboundHandlerAdapter {
    @Override // com.unisound.vui.engine.ANTOutboundHandlerAdapter, com.unisound.vui.engine.ANTOutboundHandler
    public void write(ANTHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MusicOutputEvents) {
            if (((Boolean) ((MusicOutputEvents) msg).getData()).booleanValue()) {
                onStartMusic();
                if (PhicommDeviceStatusProcessor.getInstance().getDeviceStatus() == 5) {
                    PhicommDeviceStatusProcessor.getInstance().sendStatusMessage(102);
                }
            } else {
                onStopMusic();
            }
        } else if (msg instanceof DormantOutputEvent) {
            if (((Boolean) ((DormantOutputEvent) msg).getData()).booleanValue()) {
                onStartDormant();
            } else {
                onStopDormant();
            }
        } else if (msg instanceof BluetoothOutputEvent) {
            if (((Boolean) ((BluetoothOutputEvent) msg).getData()).booleanValue()) {
                onStartBluetooth();
            } else {
                onStopBluetooth();
            }
        } else if (msg instanceof NetworkConfigOutputEvent) {
            if (((Boolean) ((NetworkConfigOutputEvent) msg).getData()).booleanValue()) {
                onStartNetConfig();
            } else {
                onStopNetConfig();
            }
        } else if (msg instanceof DormantMessageEvent) {
            if (((Boolean) ((DormantMessageEvent) msg).getData()).booleanValue()) {
                PhicommDeviceStatusProcessor.getInstance().sendStatusMessage(101);
            } else {
                PhicommDeviceStatusProcessor.getInstance().sendStatusMessage(102);
            }
        }
        super.write(ctx, msg);
    }

    private void onStopMusic() {
        PhicommDeviceStatusProcessor.getInstance().sendStatusMessage(16);
    }

    private void onStartMusic() {
        PhicommDeviceStatusProcessor.getInstance().sendStatusMessage(15);
    }

    private void onStopDormant() {
        PhicommDeviceStatusProcessor.getInstance().sendStatusMessage(14);
    }

    private void onStartDormant() {
        PhicommDeviceStatusProcessor.getInstance().sendStatusMessage(13);
    }

    private void onStopBluetooth() {
        PhicommDeviceStatusProcessor.getInstance().sendStatusMessage(20);
    }

    private void onStartBluetooth() {
        PhicommDeviceStatusProcessor.getInstance().sendStatusMessage(19);
    }

    private void onStopNetConfig() {
        PhicommDeviceStatusProcessor.getInstance().sendStatusMessage(18);
    }

    private void onStartNetConfig() {
        PhicommDeviceStatusProcessor.getInstance().sendStatusMessage(17);
    }
}

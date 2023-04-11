package com.unisound.ant.device.controlor;

public interface VolumeOperator {
    int getCurrentVolume();

    int getMaxVolume();

    void setMuteOff();

    void setMuteOn();

    void setVoiceVolume(float f);

    void setVoiceVolume(int i);

    void setVolumeLower();

    void setVolumeMax();

    void setVolumeMin();

    void setVolumeRaise();
}

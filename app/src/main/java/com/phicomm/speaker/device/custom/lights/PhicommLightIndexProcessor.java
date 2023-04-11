package com.phicomm.speaker.device.custom.lights;

public class PhicommLightIndexProcessor {
    private static final int WHITE_LIGHT_NUMBER = 24;
    private static final int WHITE_LIGHT_TAG = 15;

    public static int getIndex(int angle) {
        return getIndexFromAngle(perHandleAngle(angle));
    }

    private static int perHandleAngle(int angle) {
        if (angle < 0 || angle >= 360) {
            angle = 0;
        }
        int angle2 = Math.abs(angle - 360) + 270;
        if (angle2 >= 360) {
            return angle2 - 360;
        }
        return angle2;
    }

    private static int getIndexFromAngle(int angle) {
        int lightsIndex;
        if (angle % 15 > 7) {
            lightsIndex = 1 + (angle / 15) + 1;
        } else {
            lightsIndex = 1 + (angle / 15);
        }
        if (lightsIndex > 24) {
            return lightsIndex - 24;
        }
        return lightsIndex;
    }
}

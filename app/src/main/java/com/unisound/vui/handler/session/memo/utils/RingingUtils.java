package com.unisound.vui.handler.session.memo.utils;

import java.io.File;

public class RingingUtils {
    public static final String RINGING_DEFAULT = "happiness";
    private static final String TAG = RingingUtils.class.getSimpleName();

    public static File getRingingFile(String fileName) {
        return new File(getRingDir(), fileName + ".mp3");
    }

    public static File getRingingFile(String fileName, String defaultFile) {
        File file = new File(getRingDir(), fileName + ".mp3");
        if (!file.exists()) {
            return new File(getRingDir(), defaultFile + ".mp3");
        }
        return file;
    }

    public static String getRingDir() {
        return "system/unisound/ringing";
    }
}

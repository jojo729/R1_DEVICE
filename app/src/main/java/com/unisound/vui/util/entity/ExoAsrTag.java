package com.unisound.vui.util.entity;

public enum ExoAsrTag {
    TAG_UNIDRIVE_MAIN,
    TAG_PHONE,
    TAG_CONFIRM;
    
    private static final String CONFIRM = "confirm";
    private static final String PHONE = "phone";
    private static final String UNIDRIVE_MAIN = "unidrive_main";

    public static String getMainTag(ExoAsrTag asrTag) {
        switch (asrTag) {
            case TAG_UNIDRIVE_MAIN:
                return UNIDRIVE_MAIN;
            case TAG_CONFIRM:
                return CONFIRM;
            case TAG_PHONE:
                return "phone";
            default:
                return UNIDRIVE_MAIN;
        }
    }
}

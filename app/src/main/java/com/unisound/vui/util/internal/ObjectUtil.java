package com.unisound.vui.util.internal;

public final class ObjectUtil {
    private ObjectUtil() {
    }

    public static <T> T checkNotNull(T arg, String text) {
        if (arg != null) {
            return arg;
        }
        throw new NullPointerException(text);
    }
}

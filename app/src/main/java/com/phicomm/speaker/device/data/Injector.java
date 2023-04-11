package com.phicomm.speaker.device.data;

import android.content.Context;
import dagger.ObjectGraph;

public final class Injector {
    private static final String INJECTOR_SERVICE = "com.unisound.ant.platform.example.data.injector";

    public static ObjectGraph obtain(Context context) {
        return (ObjectGraph) context.getSystemService(INJECTOR_SERVICE);
    }

    public static boolean matchesService(String name) {
        return INJECTOR_SERVICE.equals(name);
    }

    private Injector() {
        throw new AssertionError("No instances.");
    }
}

package com.unisound.vui.engine;

import android.content.Context;
import com.unisound.vui.Ra2;
import com.unisound.vui.c;
import com.unisound.vui.e;

/* access modifiers changed from: package-private */
public interface a {

    /* renamed from: com.unisound.vui.engine.a$a  reason: collision with other inner class name */
    public interface AbstractC0007a {
        Context context();

        a getANTBuilder();
    }

    Ra2 createASRManager();

    e createNluManager();

    c createTTSManager();
}

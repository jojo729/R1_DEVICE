package com.unisound.vui.engine;

import android.content.Context;
import com.unisound.vui.Ra2;
import com.unisound.vui.auth.UNIOSCredentials;
import com.unisound.vui.c;
import com.unisound.vui.e;

public final class DefaultANTBuilder implements a {
    private final Context context;
    private final UNIOSCredentials credentials;

    public static final class Provider implements AbstractC0007a {
        private final Context context;
        private final UNIOSCredentials credentials;

        public Provider(Context context2, UNIOSCredentials uNIOSCredentials) {
            if (context2 == null) {
                throw new NullPointerException("context");
            } else if (uNIOSCredentials != null) {
                this.context = context2;
                this.credentials = uNIOSCredentials;
            } else {
                throw new NullPointerException("credentials");
            }
        }

        @Override // com.unisound.vui.engine.a.AbstractC0007a
        public Context context() {
            return this.context;
        }

        @Override // com.unisound.vui.engine.a.AbstractC0007a
        public a getANTBuilder() {
            return new DefaultANTBuilder(this.context, this.credentials);
        }
    }

    public DefaultANTBuilder(Context context2, UNIOSCredentials uNIOSCredentials) {
        this.context = context2;
        this.credentials = uNIOSCredentials;
    }

    @Override // com.unisound.vui.engine.a
    public Ra2 createASRManager() {
        return new Ra2(this.context, this.credentials.getAccessKey(), this.credentials.getSecretKey());
    }

    @Override // com.unisound.vui.engine.a
    public e createNluManager() {
        return new e(this.context, this.credentials.getAccessKey(), this.credentials.getSecretKey());
    }

    @Override // com.unisound.vui.engine.a
    public c createTTSManager() {
        return new c(this.context, this.credentials.getAccessKey(), this.credentials.getSecretKey());
    }
}

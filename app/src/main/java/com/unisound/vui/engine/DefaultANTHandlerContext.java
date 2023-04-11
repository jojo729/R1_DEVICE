package com.unisound.vui.engine;

import com.unisound.vui.util.internal.ObjectUtil;

/* access modifiers changed from: package-private */
public final class DefaultANTHandlerContext extends AbstractANTHandlerContext {
    private final ANTHandler handler;

    DefaultANTHandlerContext(DefaultANTPipeline defaultANTPipeline, String str, ANTHandler aNTHandler) {
        super(defaultANTPipeline, isInbound(aNTHandler), isOutbound(aNTHandler), str);
        ObjectUtil.checkNotNull(aNTHandler, "handler");
        this.handler = aNTHandler;
    }

    private static boolean isInbound(ANTHandler aNTHandler) {
        return aNTHandler instanceof ANTInboundHandler;
    }

    private static boolean isOutbound(ANTHandler aNTHandler) {
        return aNTHandler instanceof ANTOutboundHandler;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTHandler handler() {
        return this.handler;
    }
}

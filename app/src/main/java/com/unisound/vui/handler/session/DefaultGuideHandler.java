package com.unisound.vui.handler.session;

import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import nluparser.scheme.NLU;
import nluparser.scheme.SName;

public class DefaultGuideHandler extends SimpleUserEventInboundHandler<NLU> {
    private void a() {
        this.ctx.pipeline().fireASRResult(3201, "{\"local_asr\":[{\"engine_mode\":\"wakeup\",\"result_type\":\"full\",\"recognition_result\":\"  小讯小讯   \",\"score\":6.08,\"utteranceTime\":1230,\"outRecordingTime\":212210,\"delayTime\":280}]}");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    @Override
    public void eventReceived(NLU nlu, ANTHandlerContext aNTHandlerContext) throws Exception {
        super.eventReceived(nlu, aNTHandlerContext);
        a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    @Override
    public boolean acceptInboundEvent0(NLU nlu) throws Exception {
        return SName.WAKEUP_WORD.equals(nlu.getService());
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }
}

package com.unisound.vui.handler.session.stock;

import com.phicomm.speaker.device.R;
import com.unisound.vui.engine.ANTHandlerContext;
import com.unisound.vui.handler.SessionRegister;
import com.unisound.vui.handler.SimpleUserEventInboundHandler;
import com.unisound.vui.util.UserPerferenceUtil;
import nluparser.scheme.NLU;
import nluparser.scheme.SName;
import nluparser.scheme.StockIntent;
import nluparser.scheme.StockResult;

public class DefaultStockHandler extends SimpleUserEventInboundHandler<NLU<StockIntent, StockResult>> {
    public DefaultStockHandler() {
        this.sessionName = SessionRegister.SESSION_STOCK;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleUserEventInboundHandler
    public void initPriority() {
        setPriority(300);
    }

    /* access modifiers changed from: protected */
    @Override
    public boolean acceptInboundEvent0(NLU<StockIntent, StockResult> evt) throws Exception {
        return SName.STOCK.equals(evt.getService());
    }

    /* access modifiers changed from: protected */
    @Override
    public void eventReceived(NLU<StockIntent, StockResult> evt, ANTHandlerContext ctx) throws Exception {
        super.eventReceived(evt, ctx);
        if (evt.getSemantic() != null) {
            String stockName = evt.getSemantic().getIntent().getName();
            String mTtsContent = "";
            if (evt.getData() == null || evt.getData().getResult() == null) {
                String mTtsContent2 = ctx.androidContext().getString(R.string.tts_stock_no_result, stockName);
                ctx.stopWakeup();
                ctx.cancelASR();
                ctx.playTTS(mTtsContent2);
                sendFullLogToDeviceCenter(evt, mTtsContent2);
                return;
            }
            String stockPrice = evt.getData().getResult().getCurrentPrice();
            String stockChangeAmount = evt.getData().getResult().getChangeAmount();
            String stockChangeRate = evt.getData().getResult().getChangeRate();
            String stockChangeAmount2 = String.valueOf(Math.abs(Double.parseDouble(stockChangeAmount)));
            if (!"0.000".equals(stockPrice) || !"0.00".equals(stockChangeAmount2)) {
                double rate = Double.valueOf(stockChangeRate).doubleValue();
                if (rate > 0.0d) {
                    mTtsContent = ctx.androidContext().getString(R.string.tts_stock_rize, stockName, stockPrice, stockChangeAmount2, Double.valueOf(rate));
                } else if (rate < 0.0d) {
                    double rate2 = Math.abs(rate);
                    mTtsContent = ctx.androidContext().getString(R.string.tts_stock_fell, stockName, stockPrice, stockChangeAmount2, Double.valueOf(rate2));
                } else if (rate == 0.0d) {
                    mTtsContent = ctx.androidContext().getString(R.string.tts_stock_unchange, stockName, stockPrice);
                }
            } else {
                mTtsContent = ctx.androidContext().getString(R.string.tts_stock_suspension, stockName);
            }
            ctx.cancelEngine();
            ctx.playTTS(mTtsContent);
            sendFullLogToDeviceCenter(evt, mTtsContent);
        }
    }

    public void exit(boolean sendSessionEnd, boolean fireResume) {
        this.ctx.cancelTTS();
        this.ctx.setWakeupWord(UserPerferenceUtil.getWakeupWord(this.ctx.androidContext()), false);
        reset();
        if (fireResume) {
            fireResume(this.ctx);
        }
    }

    public void interrupt(String interruptType) {
        if (this.eventReceived) {
            reset();
            this.ctx.cancelTTS();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.engine.ANTDuplexHandler, com.unisound.vui.handler.ANTEventDispatcher, com.unisound.vui.handler.SimpleUserEventInboundHandler
    public boolean onTTSEventPlayingEnd(ANTHandlerContext ctx) {
        if (!this.eventReceived) {
            return super.onTTSEventPlayingEnd(ctx);
        }
        exit(true, true);
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.unisound.vui.handler.SimpleSessionManagementHandler
    public void doInterrupt(ANTHandlerContext ctx, String interruptType) {
        interrupt(interruptType);
    }
}

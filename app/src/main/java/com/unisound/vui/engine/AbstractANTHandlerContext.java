package com.unisound.vui.engine;

import android.content.Context;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.d;
import com.unisound.vui.util.internal.ObjectUtil;
import org.litepal.util.Const;

import java.util.List;

public abstract class AbstractANTHandlerContext extends d implements ANTHandlerContext {
    private static final String TAG = "AbstractANTHandlerContext";
    private final ANTEngine antEngine;
    private final boolean inbound;
    private final String name;
    protected AbstractANTHandlerContext next;
    private final boolean outbound;
    private final DefaultANTPipeline pipeline;
    protected AbstractANTHandlerContext prev;

    AbstractANTHandlerContext(final DefaultANTPipeline pipeline, final boolean inbound, final boolean outbound, final String name) {
        super();
        ObjectUtil.checkNotNull(name, Const.TableSchema.COLUMN_NAME);
        this.antEngine = pipeline.antEngine;
        this.pipeline = pipeline;
        this.name = name;
        this.inbound = inbound;
        this.outbound = outbound;
    }



    private AbstractANTHandlerContext findNextContextInbound() {
        AbstractANTHandlerContext abstractANTHandlerContext = this;
        do {
            abstractANTHandlerContext = abstractANTHandlerContext.next;
        } while (!abstractANTHandlerContext.inbound);
        return abstractANTHandlerContext;
    }

    private AbstractANTHandlerContext findPreContextOutbound() {
        AbstractANTHandlerContext abstractANTHandlerContext = this;
        do {
            abstractANTHandlerContext = abstractANTHandlerContext.prev;
        } while (!abstractANTHandlerContext.outbound);
        return abstractANTHandlerContext;
    }

    private static boolean inExceptionCaught(Throwable th) {
        do {
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    if (stackTraceElement == null) {
                        break;
                    } else if ("exceptionCaught".equals(stackTraceElement.getMethodName())) {
                        return true;
                    }
                }
            }
            th = th.getCause();
        } while (th != null);
        return false;
    }

    private void invokeExceptionCaught(Throwable th) {
        try {
            handler().exceptionCaught(this, th);
        } catch (Throwable th2) {
            LogMgr.e(TAG, th + "An exception was thrown by a user handler's exceptionCaught() method while handling the following exception:");
        }
    }

    private void notifyHandlerException(Throwable th) {
        if (inExceptionCaught(th)) {
            LogMgr.e(TAG, th + "An exception was thrown by a user handler while handling an exceptionCaught event");
            return;
        }
        invokeExceptionCaught(th);
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public Context androidContext() {
        return this.antEngine.androidContext();
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void cancelASR() {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).cancelASR(findPreContextOutbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void cancelEngine() {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).cancelEngine(findPreContextOutbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void cancelTTS() {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).cancelTTS(findPreContextOutbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void doPPTAction() {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).doPPTAction(findPreContextOutbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTEngine engine() {
        return this.antEngine;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void enterASR() {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).enterASR(findPreContextOutbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void enterWakeup(boolean z) {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).enterWakeup(findPreContextOutbound, z);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTHandlerContext fireASRError(int i, String str) {
        try {
            AbstractANTHandlerContext findNextContextInbound = findNextContextInbound();
            ((ANTInboundHandler) findNextContextInbound.handler()).onASRError(i, str, findNextContextInbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTHandlerContext fireASREvent(int i) {
        try {
            AbstractANTHandlerContext findNextContextInbound = findNextContextInbound();
            ((ANTInboundHandler) findNextContextInbound.handler()).onASREvent(i, findNextContextInbound);
        } catch (Throwable th) {
            th.printStackTrace();
            notifyHandlerException(th);
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTHandlerContext fireASRResult(int i, String str) {
        try {
            AbstractANTHandlerContext findNextContextInbound = findNextContextInbound();
            ((ANTInboundHandler) findNextContextInbound.handler()).onASRResult(i, str, findNextContextInbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTHandlerContext fireExceptionCaught(Throwable th) {
        if (th != null) {
            ObjectUtil.checkNotNull(th, "cause");
            this.next.invokeExceptionCaught(th);
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTHandlerContext fireNLUError(int i, String str) {
        try {
            AbstractANTHandlerContext findNextContextInbound = findNextContextInbound();
            ((ANTInboundHandler) findNextContextInbound.handler()).onNLUError(i, str, findNextContextInbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTHandlerContext fireNLUEvent(int i) {
        try {
            AbstractANTHandlerContext findNextContextInbound = findNextContextInbound();
            ((ANTInboundHandler) findNextContextInbound.handler()).onNLUEvent(i, findNextContextInbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTHandlerContext fireNLUResult(int i, String str) {
        try {
            AbstractANTHandlerContext findNextContextInbound = findNextContextInbound();
            ((ANTInboundHandler) findNextContextInbound.handler()).onNLUResult(i, str, findNextContextInbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTHandlerContext fireTTSError(int i, String str) {
        try {
            AbstractANTHandlerContext findNextContextInbound = findNextContextInbound();
            ((ANTInboundHandler) findNextContextInbound.handler()).onTTSError(i, str, findNextContextInbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTHandlerContext fireTTSEvent(int i) {
        try {
            AbstractANTHandlerContext findNextContextInbound = findNextContextInbound();
            ((ANTInboundHandler) findNextContextInbound.handler()).onTTSEvent(i, findNextContextInbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTHandlerContext fireUserEventTriggered(Object obj) {
        try {
            AbstractANTHandlerContext findNextContextInbound = findNextContextInbound();
            ((ANTInboundHandler) findNextContextInbound.handler()).userEventTriggered(obj, findNextContextInbound);
        } catch (Exception e) {
            e.printStackTrace();
            notifyHandlerException(e);
        }
        return this;
    }

    public AbstractANTHandlerContext getNext() {
        return this.next;
    }

    public AbstractANTHandlerContext getPrev() {
        return this.prev;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void initializeMode() {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).initializeMode(findPreContextOutbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void initializeSdk() {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).initializeSdk(findPreContextOutbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public String name() {
        return this.name;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public ANTPipeline pipeline() {
        return this.pipeline;
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void playBuffer(byte[] bArr) {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).playBuffer(findPreContextOutbound, bArr);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void playTTS(String str) {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).playTTS(findPreContextOutbound, str);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void setWakeupWord(List<String> list, boolean z) {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).setWakeupWord(findPreContextOutbound, list, z);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void stopASR() {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).stopASR(findPreContextOutbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void stopWakeup() {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).stopWakeup(findPreContextOutbound);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void updateWakeupWord(List<String> list) {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).updateWakeupWord(findPreContextOutbound, list);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }

    @Override // com.unisound.vui.engine.ANTHandlerContext
    public void write(Object obj) {
        try {
            AbstractANTHandlerContext findPreContextOutbound = findPreContextOutbound();
            ((ANTOutboundHandler) findPreContextOutbound.handler()).write(findPreContextOutbound, obj);
        } catch (Throwable th) {
            notifyHandlerException(th);
        }
    }
}

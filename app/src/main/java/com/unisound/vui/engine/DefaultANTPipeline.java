package com.unisound.vui.engine;

import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.internal.ObjectUtil;
import com.unisound.vui.util.internal.c;
import org.litepal.util.Const;

import java.util.*;

final class DefaultANTPipeline implements ANTPipeline {
    static final /* synthetic */ boolean $assertionsDisabled = (!DefaultANTPipeline.class.desiredAssertionStatus());
    private static final WeakHashMap<Class<?>, String>[] NAME_CACHES = new WeakHashMap[Runtime.getRuntime().availableProcessors()];
    private static final String TAG = "DefaultANTPipeline";
    final ANTEngine antEngine;
    final AbstractANTHandlerContext head;
    private final Map<String, ANTHandlerContext> name2ctx = new HashMap(4);
    final AbstractANTHandlerContext tail;

    static final class a extends AbstractANTHandlerContext implements ANTOutboundHandler {

        /* renamed from: a  reason: collision with root package name */
        private static final String f306a = DefaultANTPipeline.generateName0(a.class);
        private final ANTEngine.Unsafe b;

        a(DefaultANTPipeline defaultANTPipeline) {
            super(defaultANTPipeline, false, true, f306a);
            this.b = defaultANTPipeline.engine().unsafe();
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void cancelASR(ANTHandlerContext aNTHandlerContext) throws Exception {
            this.b.cancelASR();
        }

        @Override // com.unisound.vui.engine.ANTHandlerContext, com.unisound.vui.engine.AbstractANTHandlerContext
        public void cancelEngine() {
            this.b.cancelEngine();
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void cancelEngine(ANTHandlerContext aNTHandlerContext) throws Exception {
            this.b.cancelEngine();
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void cancelTTS(ANTHandlerContext aNTHandlerContext) throws Exception {
            this.b.cancelTTS();
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void doPPTAction(ANTHandlerContext aNTHandlerContext) throws Exception {
            this.b.cancelASR();
            this.b.enterASR();
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void enterASR(ANTHandlerContext aNTHandlerContext) throws Exception {
            this.b.enterASR();
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void enterWakeup(ANTHandlerContext aNTHandlerContext, boolean z) throws Exception {
            this.b.enterWakeup(z);
        }

        @Override // com.unisound.vui.engine.ANTHandler
        public void exceptionCaught(ANTHandlerContext aNTHandlerContext, Throwable th) throws Exception {
            aNTHandlerContext.fireExceptionCaught(th);
        }

        @Override // com.unisound.vui.engine.ANTHandlerContext
        public ANTHandler handler() {
            return this;
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void initializeMode(ANTHandlerContext aNTHandlerContext) throws Exception {
            this.b.initializeMode();
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void initializeSdk(ANTHandlerContext aNTHandlerContext) throws Exception {
            this.b.initializeSdk();
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void playBuffer(ANTHandlerContext aNTHandlerContext, byte[] bArr) throws Exception {
            this.b.playBuffer(bArr);
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void playTTS(ANTHandlerContext aNTHandlerContext, String str) throws Exception {
            this.b.playTTS(str);
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void setWakeupWord(ANTHandlerContext aNTHandlerContext, List<String> list, boolean z) throws Exception {
            this.b.setWakeupWord(list, z);
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void stopASR(ANTHandlerContext aNTHandlerContext) throws Exception {
            this.b.stopASR();
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void stopWakeup(ANTHandlerContext aNTHandlerContext) throws Exception {
            this.b.stopWakeup();
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void updateWakeupWord(ANTHandlerContext aNTHandlerContext, List<String> list) throws Exception {
            this.b.updateWakeupWord(list);
        }

        @Override // com.unisound.vui.engine.ANTOutboundHandler
        public void write(ANTHandlerContext aNTHandlerContext, Object obj) throws Exception {
            this.b.write(obj);
        }
    }

    static final class b extends AbstractANTHandlerContext implements ANTInboundHandler {

        /* renamed from: a  reason: collision with root package name */
        private static final String f307a = DefaultANTPipeline.generateName0(b.class);

        b(DefaultANTPipeline defaultANTPipeline) {
            super(defaultANTPipeline, true, false, f307a);
        }

        @Override // com.unisound.vui.engine.ANTHandler
        public void exceptionCaught(ANTHandlerContext aNTHandlerContext, Throwable th) throws Exception {
            th.printStackTrace();
            LogMgr.e(TAG, "exceptionCaught:" + th.toString());
        }

        @Override // com.unisound.vui.engine.ANTHandlerContext
        public ANTHandler handler() {
            return this;
        }

        @Override // com.unisound.vui.engine.ANTInboundHandler
        public void onASRError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        }

        @Override // com.unisound.vui.engine.ANTInboundHandler
        public void onASREvent(int i, ANTHandlerContext aNTHandlerContext) throws Exception {
        }

        @Override // com.unisound.vui.engine.ANTInboundHandler
        public void onASRResult(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        }

        @Override // com.unisound.vui.engine.ANTInboundHandler
        public void onNLUError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        }

        @Override // com.unisound.vui.engine.ANTInboundHandler
        public void onNLUEvent(int i, ANTHandlerContext aNTHandlerContext) throws Exception {
        }

        @Override // com.unisound.vui.engine.ANTInboundHandler
        public void onNLUResult(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        }

        @Override // com.unisound.vui.engine.ANTInboundHandler
        public void onTTSError(int i, String str, ANTHandlerContext aNTHandlerContext) throws Exception {
        }

        @Override // com.unisound.vui.engine.ANTInboundHandler
        public void onTTSEvent(int i, ANTHandlerContext aNTHandlerContext) throws Exception {
        }

        @Override // com.unisound.vui.engine.ANTInboundHandler
        public void userEventTriggered(Object obj, ANTHandlerContext aNTHandlerContext) throws Exception {
        }
    }

    static {
        int i = 0;
        while (true) {
            WeakHashMap<Class<?>, String>[] weakHashMapArr = NAME_CACHES;
            if (i < weakHashMapArr.length) {
                weakHashMapArr[i] = new WeakHashMap<>();
                i++;
            } else {
                break;
            }
        }
    }

    public DefaultANTPipeline(ANTEngine aNTEngine) {
        super();
        if (aNTEngine != null) {
            this.antEngine = aNTEngine;
            this.head = new a(this);
            b bVar = new b(this);
            this.tail = bVar;
            this.head.next = bVar;
            this.tail.prev = this.head;
            return;
        }
        throw new NullPointerException("antEngine");
    }

    private void addAfter0(String str, AbstractANTHandlerContext abstractANTHandlerContext, AbstractANTHandlerContext abstractANTHandlerContext2) {
        checkDuplicateName(str);
        abstractANTHandlerContext2.prev = abstractANTHandlerContext;
        abstractANTHandlerContext2.next = abstractANTHandlerContext.next;
        abstractANTHandlerContext.next.prev = abstractANTHandlerContext2;
        abstractANTHandlerContext.next = abstractANTHandlerContext2;
        this.name2ctx.put(str, abstractANTHandlerContext2);
    }

    private void addBefore0(String str, AbstractANTHandlerContext abstractANTHandlerContext, AbstractANTHandlerContext abstractANTHandlerContext2) {
        abstractANTHandlerContext2.prev = abstractANTHandlerContext.prev;
        abstractANTHandlerContext2.next = abstractANTHandlerContext;
        abstractANTHandlerContext.prev.next = abstractANTHandlerContext2;
        abstractANTHandlerContext.prev = abstractANTHandlerContext2;
        this.name2ctx.put(str, abstractANTHandlerContext2);
    }

    private void addFirst0(String str, AbstractANTHandlerContext abstractANTHandlerContext) {
        AbstractANTHandlerContext abstractANTHandlerContext2 = this.head.next;
        abstractANTHandlerContext.prev = this.head;
        abstractANTHandlerContext.next = abstractANTHandlerContext2;
        this.head.next = abstractANTHandlerContext;
        abstractANTHandlerContext2.prev = abstractANTHandlerContext;
        this.name2ctx.put(str, abstractANTHandlerContext);
    }

    private void addLast0(String str, AbstractANTHandlerContext abstractANTHandlerContext) {
        AbstractANTHandlerContext abstractANTHandlerContext2 = this.tail.prev;
        abstractANTHandlerContext.prev = abstractANTHandlerContext2;
        abstractANTHandlerContext.next = this.tail;
        abstractANTHandlerContext2.next = abstractANTHandlerContext;
        this.tail.prev = abstractANTHandlerContext;
        this.name2ctx.put(str, abstractANTHandlerContext);
    }

    private void checkDuplicateName(String str) {
        if (this.name2ctx.containsKey(str)) {
            throw new IllegalArgumentException("Duplicate handler name: " + str);
        }
    }

    private String generateName(ANTHandler aNTHandler) {
        String str;
        WeakHashMap<Class<?>, String> weakHashMap = NAME_CACHES[(int) (Thread.currentThread().getId() % ((long) NAME_CACHES.length))];
        Class<?> cls = aNTHandler.getClass();
        synchronized (weakHashMap) {
            str = weakHashMap.get(cls);
            if (str == null) {
                str = generateName0(cls);
                weakHashMap.put(cls, str);
            }
        }
        synchronized (this) {
            if (this.name2ctx.containsKey(str)) {
                int i = 1;
                String substring = str.substring(0, str.length() - 1);
                while (true) {
                    str = substring + i;
                    if (!this.name2ctx.containsKey(str)) {
                        break;
                    }
                    i++;
                }
            }
        }
        return str;
    }

    /* access modifiers changed from: private */
    public static String generateName0(Class<?> cls) {
        return c.a(cls) + "#0";
    }

    private AbstractANTHandlerContext getContextOrDie(ANTHandler aNTHandler) {
        AbstractANTHandlerContext abstractANTHandlerContext = (AbstractANTHandlerContext) context(aNTHandler);
        if (abstractANTHandlerContext != null) {
            return abstractANTHandlerContext;
        }
        throw new NoSuchElementException(aNTHandler.getClass().getName());
    }

    private AbstractANTHandlerContext getContextOrDie(String str) {
        AbstractANTHandlerContext abstractANTHandlerContext = (AbstractANTHandlerContext) context(str);
        if (abstractANTHandlerContext != null) {
            return abstractANTHandlerContext;
        }
        throw new NoSuchElementException(str);
    }

    private AbstractANTHandlerContext remove(AbstractANTHandlerContext abstractANTHandlerContext) {
        if ($assertionsDisabled || !(abstractANTHandlerContext == this.head || abstractANTHandlerContext == this.tail)) {
            synchronized (this) {
                remove0(abstractANTHandlerContext);
            }
            return abstractANTHandlerContext;
        }
        throw new AssertionError();
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline addAfter(String str, String str2, ANTHandler aNTHandler) {
        synchronized (this) {
            AbstractANTHandlerContext contextOrDie = getContextOrDie(str);
            checkDuplicateName(str);
            addAfter0(str2, contextOrDie, new DefaultANTHandlerContext(this, str2, aNTHandler));
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline addBefore(String str, String str2, ANTHandler aNTHandler) {
        synchronized (this) {
            AbstractANTHandlerContext contextOrDie = getContextOrDie(str);
            checkDuplicateName(str2);
            addBefore0(str2, contextOrDie, new DefaultANTHandlerContext(this, str2, aNTHandler));
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline addFirst(String str, ANTHandler aNTHandler) {
        synchronized (this) {
            checkDuplicateName(str);
            addFirst0(str, new DefaultANTHandlerContext(this, str, aNTHandler));
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline addFirst(ANTHandler... aNTHandlerArr) {
        ObjectUtil.checkNotNull(aNTHandlerArr, "handlers");
        if (aNTHandlerArr.length != 0 && aNTHandlerArr[0] != null) {
            int i = 1;
            while (i < aNTHandlerArr.length && aNTHandlerArr[i] != null) {
                i++;
            }
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                ANTHandler aNTHandler = aNTHandlerArr[i];
                addFirst(generateName(aNTHandler), aNTHandler);
            }
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline addLast(String str, ANTHandler aNTHandler) {
        synchronized (this) {
            checkDuplicateName(str);
            addLast0(str, new DefaultANTHandlerContext(this, str, aNTHandler));
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline addLast(ANTHandler... aNTHandlerArr) {
        ObjectUtil.checkNotNull(aNTHandlerArr, "handlers");
        for (ANTHandler aNTHandler : aNTHandlerArr) {
            if (aNTHandler == null) {
                break;
            }
            addLast(generateName(aNTHandler), aNTHandler);
        }
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void cancelASR() {
        this.tail.cancelASR();
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void cancelTTS() {
        this.tail.cancelTTS();
    }

    public void clearPipeline() {
        AbstractANTHandlerContext abstractANTHandlerContext = this.tail;
        do {
            abstractANTHandlerContext = abstractANTHandlerContext.prev;
        } while (abstractANTHandlerContext != this.head);
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTHandlerContext context(ANTHandler aNTHandler) {
        ObjectUtil.checkNotNull(aNTHandler, "handler");
        AbstractANTHandlerContext abstractANTHandlerContext = this.head;
        do {
            abstractANTHandlerContext = abstractANTHandlerContext.next;
            if (abstractANTHandlerContext == null) {
                return null;
            }
        } while (abstractANTHandlerContext.handler() != aNTHandler);
        return abstractANTHandlerContext;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTHandlerContext context(String str) {
        ANTHandlerContext aNTHandlerContext;
        ObjectUtil.checkNotNull(str, Const.TableSchema.COLUMN_NAME);
        synchronized (this) {
            aNTHandlerContext = this.name2ctx.get(str);
        }
        return aNTHandlerContext;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void doPPTAction() {
        this.tail.doPPTAction();
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTEngine engine() {
        return this.antEngine;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void enterASR() {
        this.tail.enterASR();
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void enterWakeup(boolean z) {
        this.tail.enterWakeup(z);
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline fireASRError(int i, String str) {
        this.head.fireASRError(i, str);
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline fireASREvent(int i) {
        this.head.fireASREvent(i);
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline fireASRResult(int i, String str) {
        this.head.fireASRResult(i, str);
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline fireNLUError(int i, String str) {
        this.head.fireNLUError(i, str);
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline fireNLUEvent(int i) {
        this.head.fireNLUEvent(i);
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline fireNLUResult(int i, String str) {
        this.head.fireNLUResult(i, str);
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline fireTTSError(int i, String str) {
        this.head.fireTTSError(i, str);
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline fireTTSEvent(int i) {
        this.head.fireTTSEvent(i);
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline fireUserEventTriggered(Object obj) {
        this.head.fireUserEventTriggered(obj);
        return this;
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void initializeMode() {
        this.tail.initializeMode();
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void initializeSdk() {
        this.tail.initializeSdk();
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void playTTS(String str) {
        this.tail.playTTS(str);
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTHandler remove(String str) {
        return remove(getContextOrDie(str)).handler();
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public ANTPipeline remove(ANTHandler aNTHandler) {
        remove(getContextOrDie(aNTHandler));
        return this;
    }

    /* access modifiers changed from: package-private */
    public void remove0(AbstractANTHandlerContext abstractANTHandlerContext) {
        AbstractANTHandlerContext abstractANTHandlerContext2 = abstractANTHandlerContext.prev;
        AbstractANTHandlerContext abstractANTHandlerContext3 = abstractANTHandlerContext.next;
        abstractANTHandlerContext2.next = abstractANTHandlerContext3;
        abstractANTHandlerContext3.prev = abstractANTHandlerContext2;
        this.name2ctx.remove(abstractANTHandlerContext.name());
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void setWakeupWord(List<String> list, boolean z) {
        this.tail.setWakeupWord(list, z);
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void stopASR() {
        this.tail.stopASR();
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void stopWakeup() {
        this.tail.stopWakeup();
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void updateWakeupWord(List<String> list) {
        this.tail.updateWakeupWord(list);
    }

    @Override // com.unisound.vui.engine.ANTPipeline
    public void write(Object obj) {
        this.tail.write(obj);
    }
}

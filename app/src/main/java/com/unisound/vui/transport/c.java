package com.unisound.vui.transport;

import com.alibaba.fastjson.TypeReference;
import com.unisound.client.SpeechConstants;
import com.unisound.vui.transport.out.OptionContent;
import com.unisound.vui.transport.out.SdkCreatorInfo;
import com.unisound.vui.transport.out.VocabContent;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class c {

    /* renamed from: a  reason: collision with root package name */
    static final Type f307a = new TypeReference<SdkCreatorInfo>() {
        /* class com.unisound.vui.transport.c.AnonymousClass1 */
    }.getType();
    static final Type b = new TypeReference<VocabContent>() {
        /* class com.unisound.vui.transport.c.AnonymousClass2 */
    }.getType();
    static final Type c = new TypeReference<VocabContent>() {
        /* class com.unisound.vui.transport.c.AnonymousClass3 */
    }.getType();
    static final Map<Integer, Type> d;
    private final Map<Integer, Type> e;
    private final Type f;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(4108, f307a);
        hashMap.put(Integer.valueOf((int) SpeechConstants.VPR_BLUETOOTH_ENABLED), b);
        hashMap.put(4000, c);
        d = Collections.unmodifiableMap(hashMap);
    }

    public c(int i, Type type) {
        if (type != null) {
            this.e = new HashMap(i);
            this.f = type;
            return;
        }
        throw new NullPointerException("defaultValue");
    }

    public c(Type type) {
        this(2, type);
    }

    public Type a(Integer num) {
        Type type = d.get(num);
        return type == null ? this.f : type;
    }
}

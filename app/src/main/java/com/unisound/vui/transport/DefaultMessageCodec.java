package com.unisound.vui.transport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.unisound.vui.transport.a;
import java.lang.reflect.Type;

public final class DefaultMessageCodec implements MessageCodec {
    private static final Type DEFAULT_TYPE = new TypeReference<Object>() {
        /* class com.unisound.vui.transport.DefaultMessageCodec.AnonymousClass1 */
    }.getType();
    private final a classifier = new a.C0007a(new c(DEFAULT_TYPE));

    @Override // com.unisound.vui.transport.MessageCodec
    public b decode(String str) {
        if (str == null) {
            return null;
        }
        return (b) JSON.parseObject(str, this.classifier.a(str), new Feature[0]);
    }

    @Override // com.unisound.vui.transport.MessageCodec
    public String encode(b bVar) {
        return JSON.toJSONString(bVar);
    }
}

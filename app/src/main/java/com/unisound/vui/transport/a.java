package com.unisound.vui.transport;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* access modifiers changed from: package-private */
public interface a {

    /* renamed from: com.unisound.vui.transport.a$a  reason: collision with other inner class name */
    public static final class C0007a implements a {

        /* renamed from: a  reason: collision with root package name */
        static final Pattern f306a = Pattern.compile("\\W\"type\"\\s*:\\s*(-?\\d+)", 2);
        final c b;

        public C0007a(c cVar) {
            if (cVar != null) {
                this.b = cVar;
                return;
            }
            throw new NullPointerException("typeMapping");
        }

        @Override // com.unisound.vui.transport.a
        public Type a(String str) {
            Matcher matcher = f306a.matcher(str);
            if (matcher.find()) {
                return this.b.a(Integer.valueOf(Integer.parseInt(matcher.group(1))));
            }
            throw new IllegalArgumentException(str);
        }
    }

    Type a(String str);
}

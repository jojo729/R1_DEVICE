package com.unisound.vui.util;

public final class AttributeKey<T> extends a<AttributeKey<T>> {
    private static final c<AttributeKey<Object>> POOL = new c<AttributeKey<Object>>() {
        /* class com.unisound.vui.util.AttributeKey.AnonymousClass1 */

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public AttributeKey<Object> b(int i, String str) {
            return new AttributeKey<>(i, str);
        }
    };

    private AttributeKey(int id, String name) {
        super(id, name);
    }

    public static boolean exists(String name) {
        return POOL.b(name);
    }

    public static <T> AttributeKey<T> newInstance(String name) {
        return (AttributeKey<T>) POOL.c(name);
    }

    public static <T> AttributeKey<T> valueOf(Class<?> firstNameComponent, String secondNameComponent) {
        return (AttributeKey<T>) POOL.a(firstNameComponent, secondNameComponent);
    }

    public static <T> AttributeKey<T> valueOf(String name) {
        return (AttributeKey<T>) POOL.a(name);
    }
}

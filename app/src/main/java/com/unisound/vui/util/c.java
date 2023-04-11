package com.unisound.vui.util;

import com.unisound.vui.util.internal.ObjectUtil;
import java.util.HashMap;
import java.util.Map;
import org.litepal.util.Const;

public abstract class c<T extends b<T>> {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, T> f442a = new HashMap();
    private int b = 1;

    public T a(Class<?> cls, String str) {
        if (cls == null) {
            throw new NullPointerException("firstNameComponent");
        } else if (str != null) {
            return a(cls.getName() + '#' + str);
        } else {
            throw new NullPointerException("secondNameComponent");
        }
    }

    public T a(String str) {
        T t;
        if (str == null) {
            throw new NullPointerException(Const.TableSchema.COLUMN_NAME);
        } else if (str.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        } else {
            synchronized (this.f442a) {
                t = this.f442a.get(str);
                if (t == null) {
                    t = b(this.b, str);
                    this.f442a.put(str, t);
                    this.b++;
                }
            }
            return t;
        }
    }

    /* access modifiers changed from: protected */
    public abstract T b(int i, String str);

    public boolean b(String str) {
        boolean containsKey;
        ObjectUtil.checkNotNull(str, Const.TableSchema.COLUMN_NAME);
        synchronized (this.f442a) {
            containsKey = this.f442a.containsKey(str);
        }
        return containsKey;
    }

    public T c(String str) {
        T b2;
        if (str == null) {
            throw new NullPointerException(Const.TableSchema.COLUMN_NAME);
        } else if (str.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        } else {
            synchronized (this.f442a) {
                if (this.f442a.get(str) == null) {
                    b2 = b(this.b, str);
                    this.f442a.put(str, b2);
                    this.b++;
                } else {
                    throw new IllegalArgumentException(String.format("'%s' is already in use", str));
                }
            }
            return b2;
        }
    }
}

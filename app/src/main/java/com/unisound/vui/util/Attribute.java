package com.unisound.vui.util;

public interface Attribute<T> {
    boolean compareAndSet(T t, T t2);

    T get();

    T getAndRemove();

    T getAndSet(T t);

    AttributeKey<T> key();

    void remove();

    void set(Object t);

    T setIfAbsent(T t);

}

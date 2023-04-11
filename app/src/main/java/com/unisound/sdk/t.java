package com.unisound.sdk;

import cn.yunzhisheng.asr.*;
import android.os.*;

class t implements ar
{
    final /* synthetic */ o a;

    private t(final o a) {
        this.a = a;
    }

    t(final o o, final p p2) {
        this(o);
    }

    public void a() {
        this.a.i.b();
        this.a.k.c();
        this.a.l();
    }

    public void a(final int n) {
        this.a.i.a(n);
        this.a.b(n);
    }

    public void a(final int n, final int n2, final Object o) {
        this.a.a(n, n2, o);
    }

    public void a(final VAD vad) {
        com.unisound.common.y.c(new Object[] { "FixRecognizerInterface onVADTimeout" });
        this.a.i.e();
        this.a.a(vad);
    }

    public void a(final String s, final boolean b, final int n, final int n2) {
        this.a.i.a(s, b);
        this.a.a(s, b, n, n2);
    }

    public void a(final String s, final boolean b, final int n, final long n2, final long n3, final int n4, final int n5) {
        this.a.a(s, b, n, n2, n3, n4, n5);
    }

    public boolean a(final Message message) {
        return this.a.a(message);
    }

    public void b() {
        this.a.m();
    }

    public void b(final int n) {
        this.a.i.b(n);
        this.a.c(n);
        this.a.k.a(n);
    }

    public void b(final boolean b, final byte[] array, final int n, final int n2) {
        this.a.a(b, array, n, n2);
    }

    public void c() {
        this.a.i.c();
        this.a.k.b();
        this.a.n();
    }

    public void c(final int n) {
    }

    public void d() {
        this.a.o();
    }

    public void d(final int n) {
        this.a.a(n);
    }

    public void e() {
        this.a.p();
    }

    public void f() {
        this.a.j();
    }

    public void n() {
        this.a.i.d();
        this.a.h();
    }

    public void o() {
        this.a.i();
    }
}

package com.unisound.sdk;

class bk implements com.unisound.sdk.ad {
    final com.unisound.sdk.bg a;

    bk(com.unisound.sdk.bg a0) {
        this.a = a0;
    }

    public void a() {
    }

    public void a(int i) {
    }

    public void a(int i, int i0, Object a0) {
    }

    public void a(com.unisound.sdk.al a0) {
        com.unisound.sdk.bg.a(this.a, true);
        this.a.a(true);
        String s = a0.b();
        if (!com.unisound.sdk.bg.f(this.a).equals((Object)s)) {
            com.unisound.common.v.a();
            this.a.v.clear();
        }
        com.unisound.sdk.bg.a(this.a, s);
        com.unisound.sdk.bg.a(this.a, a0);
        com.unisound.sdk.bg.b(this.a, a0);
        com.unisound.sdk.bg.c(this.a, a0);
        com.unisound.sdk.bg.a(this.a, false);
    }

    public void a(boolean b, byte[] a0, int i, int i0) {
    }

    public void b() {
    }

    public void b(int i) {
        com.unisound.sdk.bg.a(this.a, true);
        com.unisound.sdk.bg a0 = this.a;
        com.unisound.sdk.bg.b(a0, a0.o.f());
        if (i != 0) {
            this.a.b.Q(i);
            if (i == -69001) {
                com.unisound.c.a.a(true);
                this.a.refreshActivate();
            }
            com.unisound.sdk.bg.b(this.a, i);
            if (com.unisound.sdk.bg.g(this.a)) {
                this.a.e.b();
            }
        }
        if (com.unisound.sdk.bg.h(this.a) != null) {
            com.unisound.sdk.bg.b(this.a, true);
            com.unisound.sdk.bg.i(this.a).sendEmptyMessage(7);
            com.unisound.sdk.bg.j(this.a);
        }
        com.unisound.common.y.a(this.a.l, i != 0, this.a.x, com.unisound.sdk.bg.k(this.a), i, com.unisound.client.ErrorCode.toMessage(i));
        com.unisound.sdk.bg.a(this.a, false);
    }

    public void c() {
        if (com.unisound.sdk.bg.i(this.a) != null) {
            com.unisound.sdk.bg.i(this.a).sendEmptyMessage(25);
        }
    }

    public void c(int i) {
        this.a.l(i);
    }

    public void d() {
    }

    public void d(int i) {
        if (com.unisound.sdk.bg.h(this.a) != null) {
            com.unisound.sdk.bg.b(this.a, i);
        }
    }

    public void e() {
    }

    public void f() {
    }

    public void g() {
    }

    public void h() {
        if (com.unisound.sdk.bg.h(this.a) != null) {
            com.unisound.sdk.bg.i(this.a).sendEmptyMessage(30);
        }
    }
}

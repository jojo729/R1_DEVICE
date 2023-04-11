//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.sdk;

public class al {
    public int a;
    public long b;
    public long c;
    private String d;
    private String e;
    private boolean f = false;
    private boolean g = false;
    private am h;
    private float i;
    private int j;
    private int k;

    public al() {
    }

    public String a() {
        return this.d;
    }

    public void a(float var1) {
        this.i = var1;
    }

    public void a(int var1) {
        this.j = var1;
    }

    public void a(long var1) {
        this.b = var1;
    }

    public void a(am var1) {
        this.h = var1;
    }

    public void a(String var1) {
        this.d = var1;
    }

    public void a(boolean var1) {
        this.f = var1;
    }

    public String b() {
        return this.e;
    }

    public void b(int var1) {
        this.k = var1;
    }

    public void b(long var1) {
        this.c = var1;
    }

    public void b(String var1) {
        this.e = var1;
    }

    public void b(boolean var1) {
        this.g = var1;
    }

    public void c(int var1) {
        this.a = var1;
    }

    public boolean c() {
        return this.f;
    }

    public boolean d() {
        return this.g;
    }

    public am e() {
        return this.h;
    }

    public int f() {
        return this.j;
    }

    public int g() {
        return this.k;
    }

    public float h() {
        return this.i;
    }

    public int i() {
        return this.a;
    }

    public long j() {
        return this.b;
    }

    public long k() {
        return this.c;
    }

    public String toString() {
        return "resultType=" + this.h + "; recognitionResult=" + this.d + "; sessionId=" + this.e + "; isVarialbe=" + this.g + "; isLast=" + this.f + "; utterranceStartTime=" + this.j + "; utteranceEndTime=" + this.k + "; utteranceTime=" + this.a + "; recordingTime=" + this.b + "; delayTime=" + this.c + "; score=" + this.i;
    }
}

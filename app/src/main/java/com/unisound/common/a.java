//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class a {
    private String a = "117.121.55.35";
    private int b = 80;
    private String c = "";
    private String d = "117.121.55.35";
    private int e = 80;
    private boolean f = false;
    private boolean g = true;

    public a(a var1) {
        this.a = var1.a;
        this.b = var1.b;
        this.d = var1.d;
        this.e = var1.e;
        this.g = true;
    }

    public a(String var1, int var2, String var3, int var4) {
        this.a = var1;
        this.b = var2;
        this.e = var4;
        this.d = var3;
        this.g = true;
    }

    private void f() {
        if (!this.f) {
            try {
                this.c = InetAddress.getByName(this.a).getHostAddress();
                this.f = true;
            } catch (UnknownHostException var2) {
                y.a("InetAddress.getByName fail");
            }
        }

    }

    public String a() {
        this.f();
        String var1;
        if (this.f) {
            var1 = this.c;
        } else {
            var1 = this.d;
        }

        return var1;
    }

    public void a(int var1) {
        this.e = var1;
        this.g = true;
    }

    public void a(a var1) {
        this.a = var1.a;
        this.b = var1.b;
        this.d = var1.d;
        this.e = var1.e;
        this.f = false;
        this.g = true;
    }

    public void a(String var1) {
        this.d = var1;
        this.g = true;
    }

    public void a(boolean var1) {
        this.g = var1;
    }

    public String b() {
        return this.a;
    }

    public void b(int var1) {
        this.b = var1;
    }

    public void b(String var1) {
        this.a = var1;
        this.f = false;
        this.g = true;
    }

    public int c() {
        return this.b;
    }

    public boolean c(String var1) {
        boolean var2 = false;
        boolean var3;
        if (var1 == null) {
            var3 = var2;
        } else {
            String[] var5 = var1.split(":");
            var3 = var2;
            if (var5.length == 2) {
                var3 = var2;
                if (var5[0].length() != 0) {
                    try {
                        this.b = Integer.valueOf(var5[1]);
                        this.a = var5[0];
                        this.d = var5[0];
                        this.e = this.b;
                        this.g = true;
                    } catch (Exception var4) {
                        var4.printStackTrace();
                        var3 = var2;
                        return var3;
                    }

                    var3 = true;
                }
            }
        }

        return var3;
    }

    public boolean d() {
        return this.g;
    }

    public void e() {
        this.f = false;
    }
}

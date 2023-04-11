//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.sdk;

import com.unisound.common.y;

class bn implements Runnable {
    private bg a;
    bn(bg var1) {
        this.a = var1;
    }

    public void run() {
        y.c(new Object[]{"USC", "Runnable refresh activate"});
        this.a.refreshActivate();
    }
}

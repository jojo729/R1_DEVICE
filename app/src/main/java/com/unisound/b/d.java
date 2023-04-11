package com.unisound.b;

import android.os.Message;
import java.util.Map;

class d extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f133a;
    private int b = 0;

    private static boolean isActiveRun = false;

    public d(b bVar, int i) {
        this.f133a = bVar;
        this.b = i;
    }

    public void run() {
        synchronized (d.class){
            if(isActiveRun){
                i.d("ActivatorInterface Active Running");
                return;
            }else {
                isActiveRun=true;
            }
        }
        Map map;
        String str;
        i.d("ActivatorInterface HttpThreadSn start jsonStr= " + com.unisound.b.b.x());
        com.unisound.b.b.a(this.f133a);
        this.f133a.n(com.unisound.a.a.a(com.unisound.b.b.b(this.f133a), com.unisound.b.b.c(this.f133a)));
        i.c("ActivatorInterface HttpThreadSn init over");
        j.b(com.unisound.b.b.b(this.f133a));
        if (j.a()) {
            try {
                if (this.b == 0) {
                    //  get new token
                    str = this.f133a.getA();
                    map = com.unisound.b.b.e(this.f133a);
                } else if (this.b == 1) {
                    // re new token
                    str = com.unisound.b.b.f(this.f133a);
                    map =  com.unisound.b.b.g(this.f133a);
                } else {
                    map = null;
                    str = "";
                }
                i.d("ActivatorInterface", "url = " + str);
                l a2 = f.a(str, map, 5000);
                String a3 = a2.a();
                if (a2.b() != 0) {
                    com.unisound.b.b.a(this.f133a,(a2.b() - a2.d()));
                }
                i.d("result = " + a3);
                if (a3.equals("") || a3.equals("{}")) {
                    com.unisound.b.b.h(this.f133a).sendEmptyMessage(106);
                    i.d("activate HttpResponse result is null");
                } else {
                    Message message = new Message();
                    message.obj = a3;
                    message.what = 102;
                    com.unisound.b.b.h(this.f133a).sendMessage(message);
                }
            } catch (Exception e) {
                this.f133a.D = c.FINISH;
                e.printStackTrace();
                this.f133a.B.sendEmptyMessage(107);
                i.a("activate Exception exception :" + e.getMessage());
                return;
            }
        } else {
            this.f133a.B.sendEmptyMessage(109);
            i.a("activate No Network");
        }
        this.f133a.D = c.FINISH;
        isActiveRun = false;
    }
}

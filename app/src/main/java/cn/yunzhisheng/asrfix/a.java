package cn.yunzhisheng.asrfix;

import com.unisound.common.y;
import com.unisound.sdk.*;
import com.unisound.common.*;

class a extends Thread
{
    final /* synthetic */ JniAsrFix a;
    private com.unisound.sdk.w b;

    public a(JniAsrFix a,com.unisound.sdk.w b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        while (true) {
            y.g(new Object[] { "Recognizer.loadModel Reset thread start" });
            y.c(new Object[] { "Recognizer.loadModel Reset thread start" });
            this.a.a(true);
            Label_0098_Outer:
            while (true) {
                Label_0420: {
                    Label_0391:
                    while (true) {
                        String s2 = null;
                        int a = 0;
                        final String s4 = null;
                        Label_0327: {
                            while (true) {
                                Label_0281: {
                                    synchronized (JniAsrFix.w()) {
                                        JniAsrFix.b(this.a, false);
                                        if (JniAsrFix.a(this.a)) {
                                            break;
                                        }
                                        if (!JniAsrFix.b(this.a)) {
                                            JniAsrFix.a(this.a, 1503);
                                            JniAsrFix.d(this.a).a(JniAsrFix.c(this.a));
                                            while (!this.a.B.isEmpty()) {
                                                final String s = (String)this.a.B.poll();
                                                s2 = (String)this.a.B.poll();
                                                final String s3 = (String)this.a.B.poll();
                                                y.c(new Object[] { "Recognizer.loadModel reseting ", s, " ", s2 });
                                                a = JniAsrFix.a(this.a, s, "");
                                                JniAsrFix.a(this.a, 1501);
                                                if (a != 0) {
                                                    break Label_0327;
                                                }
                                                JniAsrFix.d(this.a).a(JniAsrFix.c(this.a));
                                                if ("wakeup" != s2) {
                                                    break Label_0281;
                                                }
                                                JniAsrFix.d(this.a).a(3105, (int)System.currentTimeMillis());
                                                y.c(new Object[] { "Recognizer.loadModel reset ok" });
                                            }
                                            break Label_0391;
                                        }
                                        break Label_0420;
                                    }
                                }
                                if ("command" == s2) {
                                    this.b.a(s4, true);
                                    JniAsrFix.d(this.a).a(1130, (int)System.currentTimeMillis());
                                    continue;
                                }
                                y.a("Recognizer.loadModel no cmd type error");
                                continue;
                            }
                        }
                        if ("command" == s2) {
                            this.b.a(s4, false);
                        }
                        JniAsrFix.d(this.a).b(1300, -63506);
                        y.a("Recognizer.loadModel reset error:" + a);
                        continue;
                    }
                    JniAsrFix.a(this.a, false);
                    JniAsrFix.b(this.a, true);
                    JniAsrFix.a(this.a, 1501);
                    try {
                        Thread.sleep(50L);
                        continue Label_0098_Outer;
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                        continue Label_0098_Outer;
                    }
                }
                y.c(new Object[] { "Recognizer.loadModel Reset thread stop" });
                y.g(new Object[] { "Recognizer.loadModel Reset thread stop" });
                continue Label_0098_Outer;
            }
        }
        // monitorexit(jniAsrFix)

    }
}

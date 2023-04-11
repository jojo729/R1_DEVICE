package com.unisound.sdk;

import android.content.*;
import android.os.*;
import com.unisound.client.*;

public class cb extends com.unisound.common.ab {
    private static final int a = 100;
    private static final int b = 101;
    private static final int c = 102;
    private TextUnderstanderListener d;
    private cm e;
    private cl f;
    private Context g;
    private ck h;

    protected cb(final Context g, final String s, final String s2) {
        super();
        this.h = (ck)new cc(this);
        this.g = g;
        (this.f = new cl(s, s2)).f(com.unisound.c.a.s);
    }

    public void a(final String s) {
        final cm e = this.e;
        if (e != null) {
            e.c();
        }
        (this.e = new cm(this.f)).a(s);
        this.e.a(this.h);
        this.e.start();
    }

    protected void cancel() {
        final cm e = this.e;
        if (e != null) {
            e.c();
            this.e = null;
        }
    }

    protected Object getOption(final int n) {
        Object o = null;
        Label_0161: {
            if (n != 1036) {
                switch (n) {
                    default: {
                        switch (n) {
                            default: {
                                o = null;
                                break Label_0161;
                            }
                            case 1033: {
                                o = this.f.f();
                                break Label_0161;
                            }
                            case 1032: {
                                o = this.f.m();
                                break Label_0161;
                            }
                            case 1031: {
                                o = this.f.j();
                                break Label_0161;
                            }
                            case 1030: {
                                o = this.f.i();
                                break Label_0161;
                            }
                        }
                    }
                    case 1023: {
                        o = this.f.x();
                        break;
                    }
                    case 1022: {
                        o = this.f.t();
                        break;
                    }
                    case 1021: {
                        o = this.f.n();
                        break;
                    }
                }
            }
            else {
                o = com.unisound.c.a.a(this.f.b());
            }
        }
        return o;
    }

    public void handleMessage(final Message message) {
        switch (message.what) {
            case 102: {
                if (this.d != null) {
                    final int intValue = (int)message.obj;
                    this.d.onError(intValue, ErrorCode.toMessage(intValue));
                    break;
                }
                break;
            }
            case 101: {
                if (this.d != null) {
                    this.d.onEvent((int)message.obj);
                    break;
                }
                break;
            }
            case 100: {
                final String s = (String)message.obj;
                final TextUnderstanderListener d = this.d;
                if (d != null) {
                    d.onResult(1000, s);
                    break;
                }
                break;
            }
        }
    }

    protected int init(final String s) {
        try {
            com.unisound.c.a.a(this.g);
        }
        catch (Exception ex) {
            if (this.d != null) {
                this.sendMessage(102, (Object)(-68001));
            }
            ex.printStackTrace();
        }
        return 0;
    }

    protected void setListener(final TextUnderstanderListener d) {
        this.d = d;
    }

    protected void setOption(int int1, final Object o) {
        String s = null;
        switch (int1) {
            default: {
                return;
            }
            case 1033: {
                try {
                    this.f.e((String)o);
                    return;
                }
                catch (Exception ex) {
                    s = "set gps Error.";
                    break;
                }
            }
            case 1032: {
                try {
                    this.f.j((String)o);
                    return;
                }
                catch (Exception ex2) {
                    s = "set voiceID Error.";
                    break;
                }
            }
            case 1031: {
                try {
                    this.f.i((String)o);
                    return;
                }
                catch (Exception ex3) {
                    s = "set city Error.";
                    break;
                }
            }
            case 1030: {
                try {
                    this.f.h((String)o);
                    return;
                }
                catch (Exception ex4) {
                    s = "set history Error.";
                    break;
                }
            }
            case 1025: {
                try {
                    this.f.f(String.valueOf(o));
                    return;
                }
                catch (Exception ex5) {
                    s = "set nlu_appver Error.";
                    break;
                }
            }
            case 1024: {
                try {
                    this.f.d(String.valueOf(o));
                    return;
                }
                catch (Exception ex6) {
                    s = "set nlu_ver Error.";
                    break;
                }
            }
            case 1023: {
                try {
                    this.f.a((cl)o);
                    return;
                }
                catch (Exception ex7) {
                    s = "set nlu_params Error.";
                    break;
                }
            }
            case 1022: {
                try {
                    final String s2 = (String)o;
                    if (s2 != null && s2.contains(":")) {
                        final String[] split = s2.split(":");
                        final String s3 = split[0];
                        try {
                            int1 = Integer.parseInt(split[1]);
                            this.f.a(s3, int1);
                            return;
                        }
                        catch (NumberFormatException ex8) {}
                    }
                    com.unisound.common.y.a("nlu server set Error.");
                    return;
                }
                catch (Exception ex9) {
                    s = "set nlu_server_address Error.";
                    break;
                }
            }
            case 1021: {
                try {
                    this.f.k((String)o);
                    return;
                }
                catch (Exception ex10) {
                    s = "set nlu_scenario Error.";
                }
                break;
            }
        }
        com.unisound.common.y.a(s);
    }

    protected void setText(final String s) {
        if (s != null && s.length() != 0) {
            this.a(s);
        }
        else {
            this.sendMessage(100, (Object)"");
        }
    }
}

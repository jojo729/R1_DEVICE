package com.unisound.vui;

import com.unisound.client.*;
import android.content.*;
import com.unisound.vui.util.*;

public class c
{
    private static final String a;
    private final SpeechSynthesizerListener b;
    private final SpeechSynthesizer c;
    private d d;

    static {
        a = c.class.getSimpleName();
    }

    public c(final Context context, final String s, final String s2) {
        super();
        this.b = (SpeechSynthesizerListener)new SpeechSynthesizerListener6(this);
        (this.c = new SpeechSynthesizer(context, s, s2)).setTTSListener(this.b);
    }

    static /* synthetic */ d a(final c c) {
        return c.d;
    }

    private void c(String s) {
        final boolean contains = s.contains("lingling");
        final String s2 = "sweet";
        if (contains) {
            s = "lzl";
        }
        else if (s.contains("tangtang")) {
            s = "tangtang";
        }
        else if (s.contains("tiantian")) {
            s = "boy";
        }
        else if (s.contains("xiaofeng")) {
            s = "xiaoming";
        }
        else if (s.contains("xiaowen")) {
            s = "xiaoli";
        }
        else if (s.contains("xuanxuan")) {
            s = s2;
        }
        else {
            final String a = com.unisound.vui.c.a;
            final StringBuilder sb = new StringBuilder();
            sb.append("Local tts speaker name : '");
            sb.append(s);
            sb.append("' is invalid, 'sweet' tts speaker is set for online");
            LogMgr.w(a, sb.toString());
            s = s2;
        }
        this.c.setOption(2005, (Object)s);
    }

    private boolean d(final String s) {
        return this.e(s) && !this.f(s);
    }

    private boolean e(final String s) {
        return s.matches(".*[a-zA-Z]{2,}.*");
    }

    private boolean f(final String s) {
        return s.startsWith("<PCM>") || s.startsWith("<WAV>");
    }

    public int a(final String s) {
        return this.c.init(s);
    }

    public Object a(final int n) {
        return this.c.getOption(n);
    }

    public void a(final int n, final Object o) {
        if (n == 2031 || n == 2032) {
            LogMgr.d(com.unisound.vui.c.a, "Setting local tts speaker, switch online tts speaker also");
            this.c(o.toString());
        }
        this.c.setOption(n, o);
    }

    public void a(final int n, final String s) {
        this.c.release(n, s);
    }

    public void a(final d d) {
        this.d = d;
    }

    public void a(final byte[] array) {
        this.c.playBuffer(array);
    }

    public boolean a() {
        return this.c.isPlaying();
    }

    public int b() {
        return this.c.cancel();
    }

    public int b(final String s) {
        final int intValue = (int)this.c.getOption(2020);
        final String a = com.unisound.vui.c.a;
        final StringBuilder sb = new StringBuilder();
        sb.append("ttsServiceMode=");
        sb.append(intValue);
        LogMgr.d(a, sb.toString());
        int n;
        if (this.d(s)) {
            n = 3;
            if (intValue == 3) {
                return this.c.playText(s);
            }
        }
        else {
            n = 1;
            if (intValue == 1) {
                return this.c.playText(s);
            }
        }
        this.c.setOption(2020, (Object)n);
        return this.c.playText(s);
    }

    public void c() {
        this.c.stop();
    }

    class SpeechSynthesizerListener6 implements SpeechSynthesizerListener {
        final c a;

        SpeechSynthesizerListener6(final c a) {
            this.a = a;
        }

        public void onError(final int n, final String s) {
            if (a(this.a) != null) {
                a(this.a).onError(n, s);
            }
        }

        public void onEvent(final int n) {
            if (a(this.a) != null) {
                a(this.a).onEvent(n);
            }
        }
    }
}

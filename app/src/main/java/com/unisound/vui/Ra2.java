package com.unisound.vui;

import com.unisound.client.*;
import android.content.*;
import com.unisound.vui.util.*;
import java.util.*;

public class Ra2
{
    private SpeechUnderstanderListener a;
    private final SpeechUnderstander b;
    private final Context c;
    private b d;

    public Ra2(final Context c, final String s, final String s2) {
        this.a = new SpeechUnderstanderListener4(this);
        LogMgr.d("SpeechUnderstanderManager", "appKey = %s", new Object[] { s });
        this.c = c;
        (this.b = new SpeechUnderstander(c, s, s2)).setListener(this.a);
    }

    static /* synthetic */ b a(final Ra2 Ra2) {
        return Ra2.d;
    }

    public int a(final int n, final String s) {
        return this.b.release(n, s);
    }

    public int a(final String s, final String s2) {
        return this.b.loadCompiledJsgf(s, s2);
    }

    public int a(final Map<String, List<String>> map, final String s) {
        return this.b.insertVocab((Map)map, s);
    }

    public Object a(final int n) {
        return this.b.getOption(n);
    }

    public String a(final List<String> onlineWakeupWord) {
        return this.b.setOnlineWakeupWord((List)onlineWakeupWord);
    }

    public void a() {
        this.b.stop();
    }

    public void a(final int n, final Object o) {
        if (n == 1058) {
            final StringBuilder sb = new StringBuilder();
            sb.append("value:");
            sb.append(o);
            LogMgr.e("SpeechUnderstanderManager", sb.toString());
        }
        this.b.setOption(n, o);
    }

    public void a(final b d) {
        this.d = d;
    }

    public void a(final String s) {
        this.b.init(s);
    }

    public void a(final Map<Integer, List<String>> map) {
        this.b.uploadUserData((Map)map);
    }

    public int b(final String s, final String s2) {
        return this.b.loadGrammar(s, s2);
    }

    public int b(final Map<String, List<String>> wakeupWord) {
        return this.b.setWakeupWord((Map)wakeupWord);
    }

    public void b() {
        this.b.cancel();
    }

    public void b(final String s) {
        this.b.start(s);
    }

    public int c(final String s) {
        return this.b.unloadGrammar(s);
    }

    public String c() {
        return this.b.getVersion();
    }

    class SpeechUnderstanderListener4 implements SpeechUnderstanderListener {
        final Ra2 Ra2;

        SpeechUnderstanderListener4(final Ra2 Ra2) {
            this.Ra2 = Ra2;
        }

        public void onError(final int n, final String s) {
            if (Ra2.a(this.Ra2) != null) {
                Ra2.a(this.Ra2).onError(n, s);
            }
        }

        public void onEvent(final int n, final int n2) {
            if (Ra2.a(this.Ra2) != null) {
                Ra2.a(this.Ra2).onEvent(n, n2);
            }
        }

        public void onResult(final int n, final String s) {
            if (Ra2.a(this.Ra2) != null) {
                Ra2.a(this.Ra2).onResult(n, s);
            }
        }
    }
}

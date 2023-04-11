package com.unisound.vui;

import com.unisound.client.*;
import android.content.*;

public class e
{
    private TextUnderstanderListener a;
    private TextUnderstander b;
    private f c;

    public e(final Context context, final String s, final String s2) {
        super();
        this.a = (TextUnderstanderListener)new TextUnderstanderListener2(this);
        (this.b = new TextUnderstander(context, s, s2)).setListener(this.a);
    }

    static /* synthetic */ f a(final e e) {
        return e.c;
    }

    public int a(final String s) {
        final TextUnderstander b = this.b;
        int init;
        if (b == null) {
            init = -1;
        }
        else {
            init = b.init(s);
        }
        return init;
    }

    public void a(final int n, final Object o) {
        final TextUnderstander b = this.b;
        if (b != null) {
            b.setOption(n, o);
        }
    }

    class TextUnderstanderListener2 implements TextUnderstanderListener {
        final e a;

        TextUnderstanderListener2(final e a) {
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

        public void onResult(final int n, final String s) {
            if (a(this.a) != null) {
                a(this.a).onResult(n, s);
            }
        }
    }
}

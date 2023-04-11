package com.unisound.client;

import android.content.Context;
import com.unisound.sdk.cb;

public class TextUnderstander extends cb {
    public TextUnderstander(Context context, String str, String str2) {
        super(context, str, str2);
    }

    @Override // com.unisound.sdk.cb
    public void cancel() {
        super.cancel();
    }

    @Override // com.unisound.sdk.cb
    public Object getOption(int i) {
        return super.getOption(i);
    }

    @Override // com.unisound.sdk.cb
    public int init(String str) {
        return super.init(str);
    }

    @Override // com.unisound.sdk.cb
    public void setListener(TextUnderstanderListener textUnderstanderListener) {
        super.setListener(textUnderstanderListener);
    }

    @Override // com.unisound.sdk.cb
    public void setOption(int i, Object obj) {
        super.setOption(i, obj);
    }

    @Override // com.unisound.sdk.cb
    public void setText(String str) {
        super.setText(str);
    }
}

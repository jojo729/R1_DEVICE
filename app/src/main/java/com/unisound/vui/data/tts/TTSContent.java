package com.unisound.vui.data.tts;

import android.content.Context;
import android.text.TextUtils;

import java.util.Map;

public class TTSContent extends a {
    public TTSContent(Context context) {
        super(context);
    }

    public String getLocalAnswerPath(String ttsContent) {
        if (isLocalAnswer(ttsContent)) {
            return (String) this.ttsContentMap.get(ttsContent);
        }
        throw new IllegalArgumentException(ttsContent + " is not in map !!");
    }

    public boolean isLocalAnswer(String ttsContent) {
        if (TextUtils.isEmpty(ttsContent)) {
            return false;
        }
        for (Map.Entry entry : this.ttsContentMap.entrySet()) {
            if (ttsContent.equals(entry.getKey())) {
                return true;
            }
        }
        return false;
    }
}

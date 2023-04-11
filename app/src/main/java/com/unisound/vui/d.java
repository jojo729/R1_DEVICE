package com.unisound.vui;

import com.unisound.client.*;

public interface d extends SpeechSynthesizerListener
{
    void onError(final int p0, final String p1);

    void onEvent(final int p0);
}

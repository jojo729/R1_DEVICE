package com.unisound.vui;

import com.unisound.client.*;

public interface f extends TextUnderstanderListener
{
    void onError(final int p0, final String p1);

    void onEvent(final int p0);

    void onResult(final int p0, final String p1);
}

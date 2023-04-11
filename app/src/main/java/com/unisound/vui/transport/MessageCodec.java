package com.unisound.vui.transport;

public interface MessageCodec {
    b decode(String str);

    String encode(b bVar);
}

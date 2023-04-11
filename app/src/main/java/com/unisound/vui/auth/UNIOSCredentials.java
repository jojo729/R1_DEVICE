package com.unisound.vui.auth;

public interface UNIOSCredentials {
    boolean equals(Object obj);

    String getAccessKey();

    String getSecretKey();

    int hashCode();
}

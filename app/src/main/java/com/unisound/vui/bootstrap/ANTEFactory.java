package com.unisound.vui.bootstrap;

import com.unisound.vui.engine.ANTEngine;

public interface ANTEFactory<T extends ANTEngine> {
    T newANTEngine();
}

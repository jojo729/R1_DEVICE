package com.unisound.vui.handler.filter;

import java.util.ArrayList;
import java.util.List;
import nluparser.scheme.SName;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private static List<String> f426a = new ArrayList();

    static {
        f426a.add(SName.SETTING_MP);
        f426a.add(SName.CHAT);
        f426a.add(SName.ERROR_REPORT);
    }

    public static boolean a(String str) {
        return f426a.contains(str);
    }
}

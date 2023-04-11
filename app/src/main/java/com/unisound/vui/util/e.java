package com.unisound.vui.util;

import java.util.ArrayList;

public class e {

    /* renamed from: a  reason: collision with root package name */
    private static ArrayList<String> f444a = new ArrayList<>();

    static {
        f444a.add("-90002");
        f444a.add("-91002");
        f444a.add("-91007");
        f444a.add("-91008");
        f444a.add("-91009");
        f444a.add("-91003");
        f444a.add("-91004");
        f444a.add("-90005");
        f444a.add("-91742");
    }

    public static boolean a(String str) {
        return f444a.contains(str);
    }
}

package cn.yunzhisheng.nlu;

public class OfflineNlu {
    static {
        System.loadLibrary("offlinenlujni");
    }

    public String a(String str, String str2) {
        return getNluJson(str, str2);
    }

    public void a() {
        clear();
    }

    public int b(String str, String str2) {
        return loadConf(str, str2);
    }

    public native void clear();

    public native String getNluJson(String str, String str2);

    public native int loadConf(String str, String str2);
}

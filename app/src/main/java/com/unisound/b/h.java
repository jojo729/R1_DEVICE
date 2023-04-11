package com.unisound.b;

import cn.yunzhisheng.asr.JniUscClient;
import java.lang.reflect.Type;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class h {
    public static double a(JSONObject jSONObject, String str, double d) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return d;
        }
        try {
            return jSONObject.getDouble(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return d;
        }
    }

    public static int a(JSONObject jSONObject, String str, int i) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return i;
        }
        try {
            return jSONObject.getInt(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return i;
        }
    }

    public static <T> Object a(String str, Class<T> cls) {
        return null;
    }

    public static Object a(String str, Type type) {
        return null;
    }

    public static String a(Object obj) {
        return null;
    }

    public static String a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return str2;
        }
        try {
            String string = jSONObject.getString(str);
            return (string == null || string.equals("") || string.equals(JniUscClient.az)) ? str2 : string;
        } catch (JSONException e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static JSONObject a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            Object nextValue = new JSONTokener(str).nextValue();
            return (nextValue == null || !(nextValue instanceof JSONObject)) ? jSONObject : (JSONObject) nextValue;
        } catch (Exception e) {
            return jSONObject;
        }
    }

    public static JSONObject a(JSONArray jSONArray, int i) {
        if (jSONArray != null) {
            try {
                if (i < jSONArray.length()) {
                    return jSONArray.getJSONObject(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static JSONObject a(JSONObject jSONObject, String str) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getJSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void a(JSONObject jSONObject, String str, Object obj) {
        if (obj != null) {
            try {
                if (!"".equals(obj)) {
                    jSONObject.put(str, obj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean a(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return z;
        }
        try {
            return jSONObject.getBoolean(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return z;
        }
    }

    public static String b(JSONObject jSONObject, String str) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static JSONArray b(String str) {
        JSONArray jSONArray = new JSONArray();
        if (str == null || "".equals(str)) {
            return jSONArray;
        }
        try {
            Object nextValue = new JSONTokener(str).nextValue();
            if (nextValue == null) {
                return jSONArray;
            }
            if (!(nextValue instanceof JSONObject)) {
                return nextValue instanceof JSONArray ? (JSONArray) nextValue : jSONArray;
            }
            jSONArray.put((JSONObject) nextValue);
            return jSONArray;
        } catch (Exception e) {
            return jSONArray;
        }
    }

    public static void b(JSONObject jSONObject, String str, String str2) {
        if (str2 != null) {
            try {
                if (!"".equals(str2)) {
                    jSONObject.put(str, str2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static JSONArray c(JSONObject jSONObject, String str) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getJSONArray(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

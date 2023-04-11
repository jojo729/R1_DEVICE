package com.unisound.vui.util;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonTool {
    public static final String TAG = "JsonTool";

    private JsonTool() {
    }

    public static <T> Object fromJson(JsonArray json, Type type) {
        return new Gson().fromJson(json, type);
    }

    public static <T> Object fromJson(JsonObject json, Class<T> typeOfT) {
        return new Gson().fromJson((JsonElement) json, (Class) typeOfT);
    }

    public static <T> Object fromJson(JsonObject json, Type type) {
        return new Gson().fromJson(json, type);
    }

    public static <T> Object fromJson(String json, Class<T> classOfT) {
        return new Gson().fromJson(json, (Type) classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return (T) new Gson().fromJson(json, typeOfT);
    }

    public static Object fromJsonWithFastJson(String json, Type typeOfT) {
        return JSON.parseObject(json, typeOfT, new Feature[0]);
    }

    public static JSONObject getJSONObject(JSONArray jsonArr, int index) {
        if (jsonArr == null) {
            return null;
        }
        try {
            if (index < jsonArr.length()) {
                return jsonArr.getJSONObject(index);
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getJSONObject(JSONObject jsonObj, String name) {
        if (jsonObj == null || !jsonObj.has(name)) {
            return null;
        }
        try {
            return jsonObj.getJSONObject(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getJsonArray(JSONObject jsonObj, String key) {
        if (jsonObj != null && jsonObj.has(key)) {
            try {
                return jsonObj.getJSONArray(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static double getJsonDoubleValue(JSONObject json, String key) {
        if (json != null && json.has(key)) {
            try {
                return json.getDouble(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return -1.0d;
    }

    public static int getJsonIntValue(JSONObject json, String key) {
        if (json != null && json.has(key)) {
            try {
                return json.getInt(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static double getJsonValue(JSONObject json, String key, double defValue) {
        if (json == null || !json.has(key)) {
            return defValue;
        }
        try {
            return json.getDouble(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return defValue;
        }
    }

    public static int getJsonValue(JSONObject json, String key, int defValue) {
        if (json == null || !json.has(key)) {
            return defValue;
        }
        try {
            return json.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return defValue;
        }
    }

    public static String getJsonValue(JSONObject json, String key) {
        if (json != null && json.has(key)) {
            try {
                return json.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String getJsonValue(JSONObject json, String key, String defValue) {
        if (json == null || !json.has(key)) {
            return defValue;
        }
        try {
            String defValue2 = json.getString(key);
            return TextUtils.isEmpty(defValue2) ? defValue : defValue2;
        } catch (JSONException e) {
            e.printStackTrace();
            return defValue;
        }
    }

    public static boolean getJsonValue(JSONObject json, String key, boolean defValue) {
        if (json == null || !json.has(key)) {
            return defValue;
        }
        try {
            return json.getBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return defValue;
        }
    }

    public static JSONArray parseToJSONOArray(String json) {
        JSONArray jSONArray = null;
        JSONArray jSONArray2 = new JSONArray();
        if (json == null || "".equals(json)) {
            return jSONArray2;
        }
        try {
            Object nextValue = new JSONTokener(json).nextValue();
            if (nextValue != null) {
                if (nextValue instanceof JSONObject) {
                    jSONArray2.put((JSONObject) nextValue);
                    jSONArray = jSONArray2;
                } else if (nextValue instanceof JSONArray) {
                    jSONArray = (JSONArray) nextValue;
                }
                return jSONArray;
            }
            jSONArray = jSONArray2;
            return jSONArray;
        } catch (Exception e) {
            return jSONArray2;
        }
    }

    public static JSONObject parseToJSONObject(String json) {
        JSONObject jSONObject = new JSONObject();
        try {
            Object nextValue = new JSONTokener(json).nextValue();
            return (nextValue == null || !(nextValue instanceof JSONObject)) ? jSONObject : (JSONObject) nextValue;
        } catch (Exception e) {
            return jSONObject;
        }
    }

    public static void putJsonObjecValue(JSONObject objc, String key, Object value) {
        try {
            objc.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String toJson(Object src) {
        Gson gson = new Gson();
        return src == null ? gson.toJson((JsonElement) JsonNull.INSTANCE) : gson.toJson(src);
    }

    public static String toJsonWithFastJson(Object object) {
        return JSON.toJSONString(object);
    }
}

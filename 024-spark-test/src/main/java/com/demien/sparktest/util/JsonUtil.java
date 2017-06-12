package com.demien.sparktest.util;

import com.google.gson.Gson;

public class JsonUtil {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static Object toObject(String json, Class<?> cl) {
        return new Gson().fromJson(json, cl);
    }
}

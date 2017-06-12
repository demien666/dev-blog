package com.demien.ssecurity.testapp.utils;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonHelper {

    private static ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("rawtypes")
    public static Object Json2Object(String json, Class cl) throws JsonHelperException {
        return Json2ObjectMain(json, cl, false);
    }

    @SuppressWarnings("rawtypes")
    public static Object Json2ObjectList(String json, Class cl) throws JsonHelperException {
        return Json2ObjectMain(json, cl, true);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Object Json2ObjectMain(String json, Class cl, boolean asList) throws JsonHelperException {
        if (json == null || json.length() == 0) {
            return null;
        }
        Object result = null;
        try {
            if (asList) {
                result = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, cl));
            } else {
                result = mapper.readValue(json, cl);
            }
        } catch (Exception e) {
            throw new JsonHelper.JsonHelperException("Exception in Json2Object. Exception:" + e.getMessage() + ". JSON=" + json);
        }

        return result;
    }

    public static String object2json(Object object) throws JsonHelperException {
        String result = "";
        try {
            result = mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonHelper.JsonHelperException("Exception in object2json. Exception:" + e.getMessage() + ".");
        }
        return result;
    }

    public static class JsonHelperException extends Exception {

        private static final long serialVersionUID = -7568626836534766197L;

        public JsonHelperException(String message) {
            super(message);
        }
    }
}

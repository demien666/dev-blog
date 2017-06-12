package com.demien.cxfspringrestjs.utils;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonHelper {
	
	private static ObjectMapper mapper = new ObjectMapper(); 
	
	public static Object Json2Object(String json, Class cl) throws JsonHelperException {	
		return Json2ObjectMain(json, cl, false);
	}	
	
	public static Object Json2ObjectList(String json, Class cl) throws  JsonHelperException{
		return Json2ObjectMain(json, cl, true);
	}
	
	private static Object Json2ObjectMain(String json, Class cl, boolean asList) throws JsonHelperException {
		if (json==null || json.length()==0) {
			return null;
		}
		Object result=null;
		try {
			if (asList) {
				result=mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, cl));
			} else {
				result=mapper.readValue(json, cl);	
			}
		} catch (Exception e) {
			throw new JsonHelper.JsonHelperException("Exception in Json2Object. Exception:"+e.getMessage()+". JSON="+json);
		}
		
		return result;
	}
	
	public static String object2json(Object object) throws JsonGenerationException, JsonMappingException, IOException {
		String result=mapper.writeValueAsString(object);
		return result;
	}
	
	public static class JsonHelperException extends Exception {
		public JsonHelperException(String message) {
			super(message);
		}
	}
}

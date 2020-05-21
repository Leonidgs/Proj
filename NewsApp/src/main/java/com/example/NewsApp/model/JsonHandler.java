package com.example.NewsApp.model;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {

	private static ObjectMapper obj = getObj();
	
	
	private static ObjectMapper getObj() {
		ObjectMapper obj = new ObjectMapper();
		
		return obj;
	}
	
	public static <A> A readJson(String str,Class<A> abstObj) throws JsonMappingException, JsonProcessingException {
		
		var sm = obj.readValue(str, abstObj);
		
		return sm;
		
	}
	
	public static JsonNode parse(URL urlSrc) throws JsonMappingException, JsonProcessingException {
		
		try {
			return obj.readTree(urlSrc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static JsonNode parse(String src) throws JsonMappingException, JsonProcessingException {
		
		try {
			return obj.readTree(src);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static <A> A getObjectFromJson(JsonNode node,Class<A> abstObj) throws JsonProcessingException {
		
		return obj.treeToValue(node, abstObj);
		
	}
	
	
}

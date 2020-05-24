package com.example.NewsApp.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestHandler implements IRequestHandler {

	public <T> T requestForObject(String url, Class<T> clazz) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("X-Lenta-Media-Type", "1");
		
		HttpEntity<?> entity = new HttpEntity<T>(headers);
		
		ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, clazz);
		return response.getBody();
	}
	
}

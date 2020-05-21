package com.example.NewsApp.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestHandler implements IRequestHandler {

	public <T> T requestForObject(String url, Class<T> clazz) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, clazz);
	}
	
}

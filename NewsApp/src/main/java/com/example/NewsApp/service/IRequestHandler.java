package com.example.NewsApp.service;

public interface IRequestHandler {
	
	<T> T requestForObject(String url, Class<T> clazz);

}

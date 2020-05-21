package com.example.NewsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.NewsApp.model.Articles;
import com.example.NewsApp.service.IRequestHandler;


@RestController
public class WebController {
	
	@Value("${lenta.url}")
	private String url;
	
	@Autowired
	private IRequestHandler handler;
	
	@GetMapping("/index")
	public Articles index() {
		Articles article = handler.requestForObject(url, Articles.class);
		return article;
	}

}

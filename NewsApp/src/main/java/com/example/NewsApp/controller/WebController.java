package com.example.NewsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.NewsApp.service.IRequestHandler;

@Controller
public class WebController {
	
	@Value("${lenta.url}")
	private String url;
	
	@Autowired
	private IRequestHandler handler;
	
	
	@GetMapping("/news")
	public String getMainPage() {
		return "main";
	}

}

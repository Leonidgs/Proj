package com.example.NewsApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebController {
	@GetMapping("/index")
	public String index() {
		return "Hello, world";
	}
	

}

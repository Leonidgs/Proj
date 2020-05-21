package com.example.NewsApp.controller;


import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NewsApp.model.Articles;
import com.example.NewsApp.model.JsonHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;


@RestController
public class WebController {
	@GetMapping("/index")
	public String index() throws JsonMappingException, JsonProcessingException {
		
		try {
			var src = new URL("https://api.lenta.ru/lists/latest");

			JsonNode node = JsonHandler.parse(src);
			
			Articles articles = JsonHandler.getObjectFromJson(node, Articles.class);

			System.out.println(articles);
			
			return articles.toString();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		return "Hello, world";
	}
	

}

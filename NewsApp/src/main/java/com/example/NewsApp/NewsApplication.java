package com.example.NewsApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.NewsApp.model.Articles;

@SpringBootApplication
public class NewsApplication {

	public static void main(String[] args) {
		
		var articles = new Articles();
		
	
		SpringApplication.run(NewsApplication.class, args);
	}

}

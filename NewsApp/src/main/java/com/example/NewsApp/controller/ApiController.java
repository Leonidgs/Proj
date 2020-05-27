package com.example.NewsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.NewsApp.dto.ArticleDto;
import com.example.NewsApp.model.articles.Articles;
import com.example.NewsApp.model.singleArticle.SingleArticle;
import com.example.NewsApp.service.ArticleConverter;
import com.example.NewsApp.service.IRequestHandler;


@RestController
public class ApiController {
	
	@Value("${lenta.url}")
	private String url;
	
	@Autowired
	private IRequestHandler handler;
	
	@Autowired
	private ArticleConverter articleConverter;
	
	@GetMapping("/index")
	public Articles index() {
		Articles article = handler.requestForObject(url, Articles.class);
		return article;
	}
	
	@GetMapping("/our") 
	public ArticleDto our() {
		SingleArticle sa = handler.requestForObject("https://api.lenta.ru/news/2020/05/24/borec/", SingleArticle.class);
		return articleConverter.convertArticle(sa);
	}
}

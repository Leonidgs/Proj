package com.example.NewsApp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NewsApp.dto.ArticleDto;
import com.example.NewsApp.model.articles.Articles;
import com.example.NewsApp.model.singleArticle.BodyElement;
import com.example.NewsApp.model.singleArticle.SingleArticle;
import com.example.NewsApp.service.IRequestHandler;


@RestController
public class ApiController {
	
	@Value("${lenta.url}")
	private String url;
	
	@Autowired
	private IRequestHandler handler;
	
	@GetMapping("/index")
	public Articles index() {
		Articles article = handler.requestForObject(url, Articles.class);
		return article;
	}
	
	
	private ArticleDto convertArticle(SingleArticle singleArticle) {
		var dto = new ArticleDto();
		var headline = singleArticle.getTopic().getHeadline();
		var info = headline.getInfo();
		dto.setId(info.getId());
		dto.setTitle(info.getTitle());
		// сoбираем content 
		var content = "";
		for (BodyElement be : singleArticle.getTopic().getBody()) {
			if (be.getContent() instanceof String) {
				content += be.getContent() + "<br/>";
			}
		}
		dto.setContent(content);
		dto.setDate(new Date(info.getModified() * 1000));
		dto.setImage(headline.getTitleImage().getUrl());
		return dto;
	}
	
	
	@GetMapping("/our") 
	public ArticleDto our() {
		SingleArticle sa = handler.requestForObject("https://api.lenta.ru/news/2020/05/24/borec/", SingleArticle.class);
		return convertArticle(sa);
	}

}

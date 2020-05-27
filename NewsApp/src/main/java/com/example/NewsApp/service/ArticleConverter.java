package com.example.NewsApp.service;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.NewsApp.dto.ArticleDto;
import com.example.NewsApp.model.singleArticle.BodyElement;
import com.example.NewsApp.model.singleArticle.SingleArticle;

@Component
public class ArticleConverter {
	
	public ArticleDto convertArticle(SingleArticle singleArticle) {
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

}

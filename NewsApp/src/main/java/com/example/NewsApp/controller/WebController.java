package com.example.NewsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.NewsApp.model.articles.Articles;
import com.example.NewsApp.model.page.Page;
import com.example.NewsApp.model.page.PageParam;
import com.example.NewsApp.service.IRequestHandler;

@Controller
public class WebController {
	
	@Value("${lenta.url}")
	private String url;
	
	@Autowired
	private IRequestHandler handler;
	
	@GetMapping("/news")
	public String getMainPage(Model model, @RequestParam(name = "page", required = false) Integer pageNum) {
		Articles articles = handler.requestForObject(url, Articles.class);
		var pageParam = new PageParam(articles, pageNum);
		var page = new Page(pageNum, pageParam.getStep(), pageParam.getCountArticles());
		articles.setHeadlines(articles.getHeadlines().subList(pageParam.getFirstItem(), pageParam.getLastItem()));
		model.addAttribute("articles", articles);
		model.addAttribute("page", page);
		return "main";
	}

}

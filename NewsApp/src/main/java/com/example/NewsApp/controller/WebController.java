package com.example.NewsApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.NewsApp.model.articles.Article;
import com.example.NewsApp.model.articles.Articles;
import com.example.NewsApp.model.page.Page;
import com.example.NewsApp.model.page.PageParam;
import com.example.NewsApp.model.singleArticle.SingleArticle;
import com.example.NewsApp.service.ArticleConverter;
import com.example.NewsApp.service.IRequestHandler;

@Controller
public class WebController {
	
	@Value("${lenta.url}")
	private String url;
	
	@Autowired
	private IRequestHandler handler;
	
	@Autowired
	private ArticleConverter converter;
	
	private Map<String, Article> news = new HashMap<>();
	
	@GetMapping("/news")
	public String getMainPage(Model model, @RequestParam(name = "page", required = false) Integer pageNum) {
		Articles articles = handler.requestForObject(url, Articles.class);
		List<Article> articlesList = articles.getHeadlines();
		for (Article a : articlesList) {
			news.put(a.getInfo().getId(), a);
		}
		var pageParam = new PageParam(articles, pageNum);
		var page = new Page(pageNum, 5, pageParam.getCountArticles());
		articles.setHeadlines(articles.getHeadlines().subList(pageParam.getFirstItem(), pageParam.getLastItem()));
		model.addAttribute("articles", articles);
		model.addAttribute("page", page);
		return "main";
	}
	
	@GetMapping("/article")
	public String getOneArticle(Model model, @RequestParam(name="id") String id) {
		var article = news.get(id);
		SingleArticle singleArticle = handler.requestForObject(article.getLinks().getSelf(), SingleArticle.class);
		var dtoArticle = converter.convertArticle(singleArticle);
		model.addAttribute("dtoArticle", dtoArticle);
		return "oneArticle";
	}

}

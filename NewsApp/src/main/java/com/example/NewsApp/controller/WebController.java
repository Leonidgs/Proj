package com.example.NewsApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.NewsApp.dto.Cookie;
import com.example.NewsApp.dto.RequestFormPassword;
import com.example.NewsApp.model.articles.Article;
import com.example.NewsApp.model.articles.Articles;
import com.example.NewsApp.model.page.Page;
import com.example.NewsApp.model.page.PageParam;
import com.example.NewsApp.model.singleArticle.SingleArticle;
import com.example.NewsApp.model.user.User;
import com.example.NewsApp.service.ArticleConverter;
import com.example.NewsApp.service.IRequestHandler;
import com.example.NewsApp.service.SecurityHolder;

@Controller
public class WebController {
	
	@Value("${lenta.url}")
	private String url;
	
	@Autowired
	private IRequestHandler handler;
	
	@Autowired
	private ArticleConverter converter;
	
	// инжектим Supplier, который получает нужный бин
	@Autowired
	private Supplier<SecurityHolder> supplier;
	
	private Map<String, Article> news = new HashMap<>();
	
	private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>() {{
		put("vasya", new User("vasya", "vasya@yandex.ru", "v123"));
		put("sasha", new User("sasha", "sasha@yandex.ru", "s123"));	
	}};
	private List<String> emails = new ArrayList<String>();
	
//	@PostMapping("/search")
//	public String getSearchArticles(Model model, @RequestParam(name = "query") String query) {
//		List<Article> afterSearch = new ArrayList<Article>();
//		for (Article a : news.values()) {
//			if (a.getInfo().getTitle().toLowerCase().matches(".*(^|\\W)" + query.toLowerCase() + "($|\\W).*")) {
//				afterSearch.add(a);
//			}
//		}
//		var pageParam = new PageParam(afterSearch, null);
//		var page = new Page(null, 5, pageParam.getCountArticles());
//		Articles articles = new Articles();
//		articles.setHeadlines(afterSearch);
//		model.addAttribute("articles", articles);
//		model.addAttribute("page", page);
//		model.addAttribute("cookie", supplier.get().getCookie());
//		model.addAttribute("search", query);
//		return "search";
//	}
//	
	@GetMapping("/news")
	public String getMainPage(Model model, @RequestParam(name = "page", defaultValue = "1") Integer pageNum,@RequestParam(name = "query",required = false) String query) {
		
		Articles articles = new Articles();
		articles = handler.requestForObject(url, Articles.class);

		List<Article> articlesList = articles.getHeadlines();
		
		for (Article a : articlesList) {
			news.put(a.getInfo().getId(), a);
		}
		
		//System.out.println(pageNum);
		
		PageParam pageParam;
		Page page;
		
		
		if(query!=null && query!="") {
			
			List<Article> afterSearch = new ArrayList<Article>();

			for (Article a : news.values()) {
				if (a.getInfo().getTitle().toLowerCase().matches(".*(^|\\W)" + query.toLowerCase() + "($|\\W).*")) {
					afterSearch.add(a);
				}
			}
			
			if(afterSearch.size()>0) {
				//System.out.println(pageNum);
				articles.setHeadlines(afterSearch);
				
				System.out.println(articles.getHeadlines().size());
				pageParam = new PageParam(articles, pageNum);
				page = new Page(pageNum, 5, pageParam.getCountArticles(),pageParam.getStep());
				articles.setHeadlines(articles.getHeadlines().subList(pageParam.getFirstItem(), pageParam.getLastItem()));
				
				model.addAttribute("search", query);
			}else {

				model.addAttribute("cookie", supplier.get().getCookie());
				model.addAttribute("pageName", "news");
				model.addAttribute("search", query);
				return "main";
			}
		
		}else {

			pageParam = new PageParam(articles, pageNum);
			
			page = new Page(pageNum, 5, pageParam.getCountArticles(),pageParam.getStep());

			articles.setHeadlines(articles.getHeadlines().subList(pageParam.getFirstItem(), pageParam.getLastItem()));
		}
		
		model.addAttribute("articles", articles);
		model.addAttribute("page", page);
		model.addAttribute("cookie", supplier.get().getCookie());
		model.addAttribute("pageName", "news");
		
		return "main";
	}
	

	
	@GetMapping("/article")
	public String getOneArticle(Model model, @RequestParam(name="id") String id) {
		var article = news.get(id);
		SingleArticle singleArticle = handler.requestForObject(article.getLinks().getSelf(), SingleArticle.class);
		var dtoArticle = converter.convertArticle(singleArticle);
		model.addAttribute("dtoArticle", dtoArticle);
		model.addAttribute("cookie", supplier.get().getCookie());
		return "article";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("form", new RequestFormPassword());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginHandlerPage(Model model, @ModelAttribute RequestFormPassword form) {
		if (users.containsKey(form.getLogin()) && users.get(form.getLogin()).getPassword().equals(form.getPassword())) {
			var cookie = new Cookie(form.getLogin());
			// из supplier с помощью get получаем  SecurityHolde и в нем с помощью set сохраняем cookie
			supplier.get().setCookie(cookie);
			//model.addAttribute("cookie", cookie);
			return "redirect:news";
		} else {
			model.addAttribute("form", new RequestFormPassword());
			model.addAttribute("error", true);
			return "login";
		}
	}
	
	@GetMapping("/reg")
	public String registering(Model model) {
		model.addAttribute("form", new RequestFormPassword());
		return "registration";
	}
	
	@PostMapping("/reg")
	public String registeringPage(Model model, @ModelAttribute RequestFormPassword form) {
		model.addAttribute("form", new RequestFormPassword());
		System.out.println(form.getLogin());
		System.out.println(form.getEmail());
						
		if (users.containsKey(form.getLogin())) {

			model.addAttribute("errorik", true);
			return "registration";
		} else if (users.values().stream().map(User::getEmail).anyMatch(form.getEmail()::equals)){
			model.addAttribute("errorik1", true);

			return "registration";
		} else {
			users.put(form.getLogin(), new User(form.getLogin(), form.getEmail(), form.getPassword()));

			var cookie = new Cookie(form.getLogin());

			supplier.get().setCookie(cookie);

			return "redirect:news";
		}	
	}
	
//	@GetMapping("/blogs")
//	public String blogsPage(Model model) {
//		model.addAttribute("cookie", supplier.get().getCookie());
//		model.addAttribute("pageName", "blogs");
//		return "blogs";
//	}
}

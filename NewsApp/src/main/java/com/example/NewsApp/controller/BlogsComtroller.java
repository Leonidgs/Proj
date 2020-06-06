package com.example.NewsApp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.NewsApp.dto.Blog;
import com.example.NewsApp.service.SecurityHolder;

public class BlogsComtroller {
	
	@Autowired
	private Supplier<SecurityHolder> supplier;
	
	private Map<String, Blog> blogs = new HashMap<>();
	
	@GetMapping("/blogs")
	public String getBlogs(Model model) {
		model.addAttribute("blogs", blogs.values());
		model.addAttribute("cookie", supplier.get().getCookie());
		return "blogs";
	}
	
	@PostMapping("/newBlog")
	public String getBlogs(Model model, @ModelAttribute Blog blog) {
		String id = UUID.randomUUID().toString();
		blog.setIdBlog(id);
		blogs.put(id, blog);
		return "redirect:blogs";
	}
	
	@GetMapping("/singleBlog")
	public String getSingleBlog(Model model, @RequestParam(name="id") String id) {
		var blog = blogs.get(id);
		model.addAttribute("singleBlog", blog);
		model.addAttribute("cookie", supplier.get().getCookie());
		return "singleBlog";
	}
	
	@GetMapping("/createBlog")
	public String createBlog(Model model) {
		var blog = new Blog();
		model.addAttribute("emptyBlog", blog);
		model.addAttribute("cookie", supplier.get().getCookie());
		return "createBlog";
	}
}

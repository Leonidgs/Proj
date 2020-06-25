package com.example.NewsApp.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.NewsApp.dto.Blog;
import com.example.NewsApp.dto.RequestNewBlogForm;
import com.example.NewsApp.service.SecurityHolder;

@Controller
public class BlogsComtroller {

	@Autowired
	private Supplier<SecurityHolder> supplier;

	private Map<String, Blog> blogs = new HashMap<>();

	@GetMapping("/blogs")
	public String getBlogs(Model model, @RequestParam(name = "flag", required = false) String flag) {
		if (flag != null) {
			model.addAttribute("form", new RequestNewBlogForm());
		}
		model.addAttribute("blogs", blogs.values());
		model.addAttribute("cookie", supplier.get().getCookie());
		model.addAttribute("pageName", "blogs");

		return "blogs";
	}

	@PostMapping("/newBlog")
	public String getBlogs(Model model, @ModelAttribute RequestNewBlogForm form) throws IOException {
		
		//System.out.println("dfdf" + form.getTitle().length());
		if(!form.getTitle().isEmpty() && !form.getDescription().isEmpty()) {
		
			String id = UUID.randomUUID().toString();
			
			var imageSrc="";

			var target = "/uploads/";
			imageSrc = target + id + ".jpg";
			var uploadDir = System.getProperty("user.dir") + imageSrc;

			BufferedImage input = ImageIO.read(form.getImage().getInputStream());  
			File targetDir = new File(System.getProperty("user.dir") + target);
			if (!targetDir.exists()) {
				targetDir.mkdir();
			}
			File outputFile = new File(uploadDir);
			if(input!=null) {
				ImageIO.write(input, "jpg", outputFile);
				var blog = new Blog(form.getTitle(), form.getDescription(), new Date(), imageSrc, id,  supplier.get().getCookie().getALogin());
				blogs.put(id, blog);
			
			}else {
				imageSrc = "";
				var blog = new Blog(form.getTitle(), form.getDescription(), new Date(), imageSrc, id,  supplier.get().getCookie().getALogin());
				blogs.put(id, blog);
			}
			
			model.addAttribute("pageName", "blogs");
			
		}
		
		return "redirect:blogs";

	}

	@GetMapping("/blog")
	public String getSingleBlog(Model model, @RequestParam(name = "id") String id) {
		var blog = blogs.get(id);
		model.addAttribute("blog", blog);
		model.addAttribute("cookie", supplier.get().getCookie());
		model.addAttribute("pageName", "blogs");
		return "blog";
	}

	@GetMapping("/createBlog")
	public String createBlog(Model model) {
		var blog = new Blog();
		model.addAttribute("emptyBlog", blog);
		model.addAttribute("cookie", supplier.get().getCookie());
		return "createBlog";
	}
}

package com.example.NewsApp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
	
	private String blogTitle;
	private String blogText;
	private Date blogDate;
	private String blogImage;
	private String idBlog;
	private String loginAutor;
	
	public String getSubTitle() {
		if(blogText.length()>100)
			return blogText.substring(0, 100);
		else {
			return blogText;
		}
	
	}
}

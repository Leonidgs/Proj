package com.example.NewsApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
	
	private Info info;
	private Links links;
	private Rubric rubric;
	private TagNumber[] tags;
	private Image title_image;
	private String type;
	
}




package com.example.NewsApp.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
	
	private Info info;
	private Links links;
	private Rubric rubric;
	private List<TagNumber> tags;
	private Image title_image;
	private String type;
	
}




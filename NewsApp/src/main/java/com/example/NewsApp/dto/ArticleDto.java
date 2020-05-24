package com.example.NewsApp.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto {

	private String id;
	private String title;
	private String content;
	private String image;
	private Date date;
}

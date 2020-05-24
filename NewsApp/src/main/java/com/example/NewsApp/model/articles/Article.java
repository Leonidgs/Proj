package com.example.NewsApp.model.articles;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article {
	
	private String type;
	private Info info;
	private Links links;
	private Rubric rubric;
	private List<TagNumber> tags;
	@JsonProperty("title_image")
	private Image titleImage;
	
	
}




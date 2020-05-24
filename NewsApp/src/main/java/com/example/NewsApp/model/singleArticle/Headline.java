package com.example.NewsApp.model.singleArticle;

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
public class Headline {
	
	private String type;
	private Info info;
	private Links links;
	private Rubric rubric;
	private List<Tags> tags;
	@JsonProperty("title_image")
	private TitleImage titleImage;

}

package com.example.NewsApp.model.articles;

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
public class Image {
	
	private String credits;
	private String	url;
	private String caption;
	private String	alt;

}
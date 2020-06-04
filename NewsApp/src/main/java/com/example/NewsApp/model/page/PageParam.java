package com.example.NewsApp.model.page;

import java.util.List;

import com.example.NewsApp.model.articles.Article;
import com.example.NewsApp.model.articles.Articles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageParam {
	
	private int firstItem;
	private int lastItem;
	private int countArticles;
	private int step;
	
	public PageParam(List<Article> listArticles,Integer pageNum ) {
		this.countArticles = listArticles.size();
		this.firstItem = 0;
		this.lastItem = 5;
		this.step = 5;
		init(pageNum);
	}
	
	public PageParam(Articles articles, Integer pageNum) {
		this.countArticles = articles.getHeadlines().size();
		this.firstItem = 0;
		this.lastItem = 5;
		this.step = 5;

		init(pageNum);
	}
	
    public void init(Integer pageNum) {
			
		firstItem = (pageNum * step) - step;
		lastItem = (pageNum * step);
		
		if (firstItem >= countArticles) {
			
			if (step > countArticles) {
				firstItem = 0;
			} else {
				firstItem = countArticles - step;
			}
			
		}

		if (lastItem > countArticles) {
			lastItem = countArticles;
		}
			
	}

}

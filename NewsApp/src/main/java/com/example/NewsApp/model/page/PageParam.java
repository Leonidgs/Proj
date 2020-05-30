package com.example.NewsApp.model.page;

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
	
	public PageParam(Articles articles, Integer pageNum) {
		this.countArticles = articles.getHeadlines().size();
		this.firstItem = 0;
		this.lastItem = 10;
		this.step = 10;
		init(pageNum);
	}
	
    public void init(Integer pageNum) {
		if (pageNum != null && pageNum > 0) {
			firstItem = (pageNum * step) - step + 1;
			lastItem = (pageNum * step) + 1;
			if (firstItem > countArticles) {
				if (step > countArticles) {
					firstItem = 0;
				} else {
					firstItem = countArticles - 10;
				}
			}

			if (lastItem > countArticles) {
				lastItem = countArticles;
			}
			
		}
/*		
		System.out.println("firstItem " + firstItem);
		System.out.println("lastItem " + lastItem);
*/
	}

}

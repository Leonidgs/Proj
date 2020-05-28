package com.example.NewsApp.model.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Page {
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	private int currentPage;
	private int startAt;
	private int endAt;
	private int pageStep;
	private int pagesCount;
	private int itemsCount;
	
	public Page(Integer currentPage, Integer pageStep, Integer itemsCount) {
		this.hasNextPage = true;
		this.hasPreviousPage = false;
		this.currentPage = 1;
		this.endAt = 5;
		this.pageStep = pageStep;
		this.itemsCount = itemsCount;
		this.pagesCount = itemsCount / pageStep;
		initPage();
	}
	
	public void initPage() {
		if (currentPage / pageStep < 1) {
			this.startAt = 1;
			hasPreviousPage = false;

		} else {
			this.startAt = currentPage / pageStep * pageStep;
			hasPreviousPage = true;

			if (startAt > pagesCount) {
				this.startAt = pagesCount / pageStep * pageStep;
			}
		}

		this.endAt = startAt + pageStep;

		if (endAt > pagesCount) {
			endAt = pagesCount;
			hasNextPage = false;
		}
	}
}

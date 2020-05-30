package com.example.NewsApp.model.page;

import java.util.ArrayList;
import java.util.List;

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
	private List<Integer> items;
	
	public Page(Integer currentPage, Integer pageStep, Integer itemsCount) {
		this.hasNextPage = true;
		this.hasPreviousPage = false;
		if (currentPage != null) {
			this.currentPage = currentPage;
		} else {
			this.currentPage = 1;
		}
		
		this.endAt = 5;
		this.pageStep = pageStep;
		this.itemsCount = itemsCount;
		this.pagesCount = itemsCount / 10;
		initPage();
	}
	
	public void initPage() {
		if (currentPage / pageStep < 1) {
			this.startAt = 1;
			
		} else {
			this.startAt = currentPage / pageStep * pageStep;
			hasPreviousPage = true;

			if (startAt > pagesCount) {
				this.startAt = pagesCount / pageStep * pageStep;
			}
		}
		
		if (currentPage > 1) {
			hasPreviousPage = true;
		}
		
		if (startAt == 1) {
			endAt = startAt + pageStep - 1;
		} else {
			endAt = startAt + pageStep;
		}

		if (endAt > pagesCount) {
			endAt = pagesCount;
			hasNextPage = false;
		}
		
		items = new ArrayList<>();
		for (int i = startAt; i <= endAt; i++) {
			items.add(i);
		}
	}
}

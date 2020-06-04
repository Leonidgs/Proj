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
	private int paginationStep;
	private int paginationPagesCount;
	private int itemsCount;
	private int itemsStep;

	private List<Integer> items;
	
	public Page(Integer currentPage, Integer paginationStep, Integer itemsCount,Integer itemsStep) {
		
		this.hasPreviousPage = false;
		this.hasNextPage = true;
		this.currentPage = currentPage;
		this.paginationStep = paginationStep;
		this.itemsCount = itemsCount;
		
		if(itemsCount % itemsStep ==0) {
			this.paginationPagesCount = itemsCount / itemsStep;
		}else {
			this.paginationPagesCount = itemsCount / itemsStep + 1;
		}
		initPage();
	}
	
	public void initPage() {
		
		if(currentPage%paginationStep==0) {
			this.startAt = currentPage-paginationStep+1;
			this.endAt = startAt + paginationStep - 1;			
			
		}else {
			
			this.startAt = currentPage/paginationStep * paginationStep +1;
			this.endAt = startAt + paginationStep - 1;			
		}
		

		if (startAt > paginationPagesCount) {
			this.startAt = paginationPagesCount / paginationStep * paginationStep;
			this.endAt = paginationPagesCount;
			hasNextPage = false;
		}
		
		if (endAt >= paginationPagesCount) {
			this.endAt = paginationPagesCount;
			hasNextPage = false;
		}	
		
		if (currentPage > 1) {
			hasPreviousPage = true;
		}
		
		items = new ArrayList<>();
		for (int i = startAt; i <= endAt; i++) {
			items.add(i);
		}
		/*
		System.out.println("hasPreviousPage " + hasPreviousPage);
		System.out.println("hasNextPage " + hasNextPage);
		System.out.println("startAt " + startAt);
		System.out.println("endAt " + endAt);
		System.out.println("currentPage " + currentPage);
		*/

	}
}

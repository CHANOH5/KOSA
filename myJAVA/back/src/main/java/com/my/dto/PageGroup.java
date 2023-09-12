package com.my.dto;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageGroup<T> {
	
	public static final int CNT_PER_PAGE = 3;	// 한 페이지당 보여줄 목록 수
	
	public static final int CNT_PER_PAGEGROUP = 2; 

//	private List<Product> list; 
// 다양한 곳에서 그룹핑쓰기 위해 Product로 하지않고 타입제네릭 T로 하자
	private List<T> list;
	
	private int totalCnt;		// 총 상품수 15
	
	private int currentPage;	// 현재 페이지 	1 2	3 4
	
	private int totalPage;		// 총 페이지수	5
	
	private int startPage;		// 시작 페이지 	1 1 2 2
	
	private int	endPage;		// 끝 페이지 	2 2 4 4
	
	// 매개변수로 전달해서 생성을하면 각각 계산이 되도록
	public PageGroup(List<T> list, int currentPage, int totalPage) {
		this.list = list;
		this.currentPage = currentPage;
		this.totalCnt = totalCnt;
		
		// 계산
		// 총 페이지수 계산
		
//		this.totalPage = (totalCnt + CNT_PER_PAGEGROUP - 1)/CNT_PER_PAGEGROUP;
		this.totalPage = (int)Math.ceil((double)totalCnt/CNT_PER_PAGE);
		if(currentPage < totalPage) {
			// 시작 페이지와 끝 페이지 계산
			this.startPage = (currentPage-1)/CNT_PER_PAGEGROUP*CNT_PER_PAGEGROUP;
			this.endPage = startPage + CNT_PER_PAGEGROUP -1;
					
			if(endPage > totalPage) {
				endPage = totalPage;
			} // innter-if
		} //if
		
	} // constructor
	
	
	// ==========================테스트 ==============
	public static void main(String[] args) {
		
		int cp = 2;	// 현재 보려는 페이지
		int tc = 15;	// 총 상품 수
		PageGroup pg = new PageGroup(null, cp, tc);
		System.out.println(pg.getTotalPage()); 	// 5
		System.out.println(pg.getStartPage()); 	// 1
		System.out.println(pg.getEndPage());	// 2
		
	} // test-main
	
} // end class

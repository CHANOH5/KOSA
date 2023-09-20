package com.my.product.dto;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageGroup<T> {
	
	   // 상수는 주로 대문자로 표기함
	public static final int CNT_PER_PAGE = 3; // 페이지당 보여 줄 상품의 수
	public static final int CNT_PER_PAGEGROUP = 2; // 페이지 그룹의 수
	   
	private List<T> list; // 상품 목록들
	private int totalCnt; // 카운트 함수로 전체 행수 얻어온거 14
	private int currentPage; // 현재 페이지 1 2 3 4 5 6
	private int totalPage; // 총 페이지 4
	private int startPage; // 시작 페이지 1 1 3 3 5 5
	private int endPage;   // 끝 페이지 2 2 4 4 6 6
	   
	// 생성자
	public PageGroup(List<T> list, int currentPage, int totalCnt) {
	      
		this.list = list;
		this.currentPage = currentPage;
		this.totalCnt = totalCnt;
		      
		// TODO 총 페이지 수 계산
		// 전체 상품 수를 페이지당 상품 수로 나눔 
		this.totalPage = (int) Math.ceil((float) totalCnt / CNT_PER_PAGE);

		if(currentPage < totalPage) {
			// TODO 시작, 끝 페이지 수 계산
			//현재페이지 기반으로 페이징 그룹의 시작페이지 계산
			// currentPage - 1 : 현재페이지에서 -1을함 자바 배열의 인덱스는 0부터 시작하기 때문
			// /CNT_PER_PAGEGROUP : 현재페이지에서 한페이지 그룹의 수를나눔 -> 현재 페이지가 몇번째 페이징 그룹에 속하는지 알기 위해 
			// ex) 페이징 그룹의 수가 10이고 현재 페이지가 15라면 15를 10으로 나누면 1.5가 나오므로 현재 페이지가 2번째 페이지라는 것을 알 수 있음
			// * CNT_PER_PAGEGROUP : 현재 페이징 그룹의 마지막 페이지의 번호를 얻을 수 있음 
			// + 1 : 마지막으로 계산된 값에 1을 더함으로써 현재 페이징 그룹의 시작페이지를 알 수 있음 
			this.startPage = ((currentPage - 1) / CNT_PER_PAGEGROUP) * CNT_PER_PAGEGROUP + 1;
			this.endPage = startPage + CNT_PER_PAGEGROUP - 1;
		          
			if(endPage > totalPage) {
				endPage = totalPage;
			} // inner-if
		         
		} // outer-if
	      
	} // constructor

	
	
	// ==========================테스트 ==============
	public static void main(String[] args) {
		
		int cp = 5;	// 현재 보려는 페이지
		int tc = 16;	// 총 상품 수
		PageGroup pg = new PageGroup(null, cp, tc);
		System.out.println(pg.getTotalPage()); 	// 5
		System.out.println(pg.getStartPage()); 	// 1
		System.out.println(pg.getEndPage());	// 2
		
	} // test-main
	
} // end class
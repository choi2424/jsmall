package com.jsmall.dto;

import lombok.Data;

@Data
public class PageDTO {
	
	private int startPage; // 각 블럭에서 출력할 첫페이지 번호
	private int endPage; // 각 블럭에서 출력할 끝페이지 번호
	
	private boolean prev,next; // 이전 , 다음  표시여부
	
	private int total; // 테이블의 총데이터 개수	
	
	private Criteria cri; //1)페이징 목적 : pageNum , amount , 2)검색 목적 : type , keyword
	
	public PageDTO(Criteria cri,int total) {
		
		this.cri = cri;  
		this.total = total;
		
		int pageSize = 10; // 블록당 보여줄 페이지의 개수.		1  2  3  4  5  6  7  8  9  10
		int endPageInfo = pageSize - 1; // 10 - 1 = 9
		
//		this.endPage = (int)(Math.ceil( 1 / 10.0 )) * 10;
		this.endPage = (int) (Math.ceil(cri.getPageNum() / (double)pageSize)) * pageSize;
		
//		this.startPage = 10 - 9;
		this.startPage = this.endPage - endPageInfo;
		
//		int readEnd = (int) (Math.ceil((13 * 1.0) / 5));
		int readEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		if(readEnd <= this.endPage) {
			this.endPage = readEnd;
		}
		
		this.prev = this.startPage > 1;
		
		this.next = this.endPage < readEnd;
		
//		pageSize ,startPage , endPage , prev , next
	}
	
}

package com.jsmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jsmall.domain.ProductVO;
import com.jsmall.dto.Criteria;

public interface AdProductMapper {
	
	// 상품 등록
	void pro_insert(ProductVO vo);
	
	// 상품목록
	List<ProductVO> pro_list(Criteria cri);
	
	// 상품목록 페이징 을위한 상품 총갯수
	int getTotelCount(Criteria cri);
	
	// 체크상품 수정
	void pro_checked_modify1(
			@Param("pro_num") Integer pro_num,
			@Param("pro_price") Integer pro_price,
			@Param("pro_buy") String pro_buy
	);
	
	// 체크상품 삭제
	void delete(Integer pro_num);
	
	// 상품 수정 폼
	ProductVO pro_edit_page(Integer pro_num);
	
	// 상품수정
	void pro_edit(ProductVO vo);
}

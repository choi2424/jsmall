package com.jsmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jsmall.domain.ProductVO;
import com.jsmall.dto.Criteria;

public interface AdProductMapper {
	
	void pro_insert(ProductVO vo);
	
	List<ProductVO> pro_list(Criteria cri);
	
	int getTotelCount(Criteria cri);
	
	void pro_checked_modify1(
			@Param("pro_num") Integer pro_num,
			@Param("pro_price") Integer pro_price,
			@Param("pro_buy") String pro_buy);
}

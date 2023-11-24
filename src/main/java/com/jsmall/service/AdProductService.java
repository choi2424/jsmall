package com.jsmall.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jsmall.domain.ProductVO;
import com.jsmall.dto.Criteria;

public interface AdProductService {
	
	void pro_insert(ProductVO vo);
	
	List<ProductVO> pro_list(Criteria cri);
	
	int getTotelCount(Criteria cri);
	
	void pro_checked_modify1(List<Integer> pro_num_arr,List<Integer> pro_price_arr,List<String> pro_buy_arr);
	
	void delete(Integer pro_num);
	
	ProductVO pro_edit_page(Integer pro_num);
	
	void pro_edit(ProductVO vo);
}

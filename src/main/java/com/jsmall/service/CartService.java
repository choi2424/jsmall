package com.jsmall.service;

import java.util.List;

import com.jsmall.domain.CartVO;
import com.jsmall.dto.CartDTOList;

public interface CartService {
	
	void cart_add(CartVO c_vo);
	
	List<CartDTOList> cart_list(String member_id);
	
	void cart_list_del(Long cart_code);
	
	void amount_change(Long cart_code, int cart_amount);
	
	void cart_checked_del(List<Long> cart_code_arr);
}

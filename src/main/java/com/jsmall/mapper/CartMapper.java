package com.jsmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jsmall.domain.CartVO;
import com.jsmall.dto.CartDTOList;

public interface CartMapper {
	
	void cart_add(CartVO c_vo);
	
	List<CartDTOList> cart_list(String member_id);
	
	void cart_list_del(Long cart_code);
	
	void amount_change(@Param("cart_code") Long cart_code,@Param("cart_amount") int cart_amount);
	
	void cart_checked_del(Long cart_code);
}

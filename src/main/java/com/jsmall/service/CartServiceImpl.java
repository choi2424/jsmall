package com.jsmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsmall.domain.CartVO;
import com.jsmall.dto.CartDTOList;
import com.jsmall.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	
	private final CartMapper cartMapper;

	@Override
	public void cart_add(CartVO c_vo) {
		// TODO Auto-generated method stub
		cartMapper.cart_add(c_vo);
	}

	@Override
	public List<CartDTOList> cart_list(String member_id) {
		// TODO Auto-generated method stub
		return cartMapper.cart_list(member_id);
	}

	@Override
	public void cart_list_del(Long cart_code) {
		// TODO Auto-generated method stub
		cartMapper.cart_list_del(cart_code);
	}

	@Override
	public void amount_change(Long cart_code, int cart_amount) {
		// TODO Auto-generated method stub
		cartMapper.amount_change(cart_code, cart_amount);
	}

	@Override
	public void cart_checked_del(List<Long> cart_code_arr) {
		// TODO Auto-generated method stub
		for (int i = 0; i < cart_code_arr.size(); i++) {
			cartMapper.cart_checked_del(cart_code_arr.get(i));
		}
	}


}

package com.jsmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsmall.domain.OrderDetailProductVO;
import com.jsmall.domain.OrderVO;
import com.jsmall.dto.Criteria;
import com.jsmall.mapper.AdOrderMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdOrderServiceImpl implements AdOrderService {
	
	private final AdOrderMapper adOrderMapper;

	@Override
	public List<OrderVO> order_list(Criteria cri, String start_date, String end_date) {
		// TODO Auto-generated method stub
		return adOrderMapper.order_list(cri, start_date, end_date);
	}

	@Override
	public int getTotalCount(Criteria cri, String start_date, String end_date) {
		// TODO Auto-generated method stub
		return adOrderMapper.getTotalCount(cri, start_date, end_date);
	}

	@Override
	public List<OrderDetailProductVO> order_detail_info(Long ord_code) {
		// TODO Auto-generated method stub
		return adOrderMapper.order_detail_info(ord_code);
	}

	@Override
	public void order_product_delete(Long ord_code, Integer pro_num) {
		// TODO Auto-generated method stub
		adOrderMapper.order_product_delete(ord_code, pro_num);
	}
	
	
}

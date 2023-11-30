package com.jsmall.service;

import com.jsmall.domain.OrderVO;
import com.jsmall.domain.PaymentVO;

public interface OrderService {
	
	int getOrderseq();
	
	// 주문하기 결제정보저장
	void order_insert(OrderVO o_vo,PaymentVO p_vo); 
}

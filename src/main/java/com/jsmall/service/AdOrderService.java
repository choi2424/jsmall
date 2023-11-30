package com.jsmall.service;

import java.util.List;

import com.jsmall.domain.OrderDetailProductVO;
import com.jsmall.domain.OrderVO;
import com.jsmall.dto.Criteria;

public interface AdOrderService {
	
	List<OrderVO> order_list(Criteria cri, String start_date, String end_date );
	
	int getTotalCount(Criteria cri, String start_date, String end_date);
	
	List<OrderDetailProductVO> order_detail_info(Long ord_code);
	
	void order_product_delete(Long ord_code,Integer pro_num);

}

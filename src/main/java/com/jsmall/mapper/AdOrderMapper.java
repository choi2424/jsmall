package com.jsmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jsmall.domain.OrderDetailProductVO;
import com.jsmall.domain.OrderVO;
import com.jsmall.dto.Criteria;



public interface AdOrderMapper {
	
	List<OrderVO> order_list(@Param("cri") Criteria cri,@Param("start_date") String start_date,@Param("end_date") String end_date);
	
	int getTotalCount(@Param("cri") Criteria cri,@Param("start_date") String start_date,@Param("end_date") String end_date);
	
	List<OrderDetailProductVO> order_detail_info(Long ord_code);
	
	void order_product_delete(@Param("ord_code")Long ord_code,@Param("pro_num")Integer pro_num);
}

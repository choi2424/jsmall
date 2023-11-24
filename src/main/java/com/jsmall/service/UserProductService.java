package com.jsmall.service;

import java.util.List;

import com.jsmall.domain.ProductVO;
import com.jsmall.dto.Criteria;

public interface UserProductService {
	
	List<ProductVO> pro_list(Integer cg_code,Criteria cri);
	
	int getTotalCount(Integer cg_code);
}

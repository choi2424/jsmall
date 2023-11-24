package com.jsmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jsmall.domain.ProductVO;
import com.jsmall.dto.Criteria;


public interface UserProductMapper {
	
	List<ProductVO> pro_list(
			@Param("cg_code") Integer cg_code,
			@Param("cri") Criteria cri
	);
	
	int getTotalCount(Integer cg_code);
	
}

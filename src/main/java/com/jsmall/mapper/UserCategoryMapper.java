package com.jsmall.mapper;

import java.util.List;

import com.jsmall.domain.CategoryVO;



public interface UserCategoryMapper {
	
	List<CategoryVO> secondCategoryList(Integer cg_parent_code);
}

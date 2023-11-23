package com.jsmall.service;

import java.util.List;

import com.jsmall.domain.CategoryVO;


public interface AdCategoryService {
	
	List<CategoryVO> firstCategoryList();
	
	List<CategoryVO> secondCategoryList(Integer cg_parent_code);
	
	CategoryVO get(Integer cg_code);
}

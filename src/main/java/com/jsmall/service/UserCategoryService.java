package com.jsmall.service;

import java.util.List;

import com.jsmall.domain.CategoryVO;

public interface UserCategoryService {
	
	List<CategoryVO> secondCategoryList(Integer cg_parent_code);
	
}

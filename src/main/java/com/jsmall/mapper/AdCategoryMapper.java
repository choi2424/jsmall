package com.jsmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jsmall.domain.CategoryVO;



public interface AdCategoryMapper {
	
	List<CategoryVO> firstCategoryList();
	
	List<CategoryVO> secondCategoryList(Integer cg_parent_code);
	
	CategoryVO get(Integer cg_code);
	
	void addFirstCategory(String cg_name);
	
	void addSecondCategory(@Param("cg_name") String cg_name,@Param("cg_parent_code") Integer cg_parent_code);
	
	void removeCategory(Integer cg_code);
}

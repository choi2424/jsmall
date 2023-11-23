package com.jsmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsmall.domain.CategoryVO;
import com.jsmall.mapper.AdCategoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdCategoryServiceImpl implements AdCategoryService {
	
	private final AdCategoryMapper adCategoryMapper;

	@Override
	public List<CategoryVO> firstCategoryList() {
		// TODO Auto-generated method stub
		return adCategoryMapper.firstCategoryList();
	}

	@Override
	public List<CategoryVO> secondCategoryList(Integer cg_parent_code) {
		// TODO Auto-generated method stub
		return adCategoryMapper.secondCategoryList(cg_parent_code);
	}

	@Override
	public CategoryVO get(Integer cg_code) {
		// TODO Auto-generated method stub
		return adCategoryMapper.get(cg_code);
	}
	
	
}

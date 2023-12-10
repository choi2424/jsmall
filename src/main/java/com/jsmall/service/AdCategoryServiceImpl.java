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

	@Override
	public void addFirstCategory(String cg_name) {
		// TODO Auto-generated method stub
		adCategoryMapper.addFirstCategory(cg_name);
	}

	@Override
	public void addSecondCategory(String cg_name, Integer cg_parent_code) {
		// TODO Auto-generated method stub
		adCategoryMapper.addSecondCategory(cg_name, cg_parent_code);
	}

	@Override
	public void removeCategory(Integer cg_code) {
		// TODO Auto-generated method stub
		adCategoryMapper.removeCategory(cg_code);
	}
	
	
}

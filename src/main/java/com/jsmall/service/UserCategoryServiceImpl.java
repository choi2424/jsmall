package com.jsmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsmall.domain.CategoryVO;
import com.jsmall.mapper.UserCategoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCategoryServiceImpl implements UserCategoryService {
	
	private final UserCategoryMapper userCategoryMapper;

	@Override
	public List<CategoryVO> secondCategoryList(Integer cg_parent_code) {
		// TODO Auto-generated method stub
		return userCategoryMapper.secondCategoryList(cg_parent_code);
	} 
}

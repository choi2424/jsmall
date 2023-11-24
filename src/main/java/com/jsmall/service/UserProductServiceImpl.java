package com.jsmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsmall.domain.ProductVO;
import com.jsmall.dto.Criteria;
import com.jsmall.mapper.UserProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProductServiceImpl implements UserProductService {
	
	private final UserProductMapper userProductMapper;

	@Override
	public List<ProductVO> pro_list(Integer cg_code, Criteria cri) {
		// TODO Auto-generated method stub
		return userProductMapper.pro_list(cg_code, cri);
	}

	@Override
	public int getTotalCount(Integer cg_code) {
		// TODO Auto-generated method stub
		return userProductMapper.getTotalCount(cg_code);
	}
}

package com.jsmall.service;

import org.springframework.stereotype.Service;

import com.jsmall.domain.ProductVO;
import com.jsmall.mapper.AdProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdProductServiceImpl implements AdProductService {
	
	private final AdProductMapper adProductMapper;

	@Override
	public void pro_insert(ProductVO vo) {
		// TODO Auto-generated method stub
		adProductMapper.pro_insert(vo);
	}
	
}

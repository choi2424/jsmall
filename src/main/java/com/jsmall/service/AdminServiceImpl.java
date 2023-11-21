package com.jsmall.service;

import org.springframework.stereotype.Service;

import com.jsmall.domain.AdminVO;
import com.jsmall.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	private final AdminMapper adminMapper;

	@Override
	public AdminVO ad_login(String admin_id) {
		// TODO Auto-generated method stub
		return adminMapper.ad_login(admin_id);
	}
}

package com.jsmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsmall.domain.AdminVO;
import com.jsmall.domain.MemberVO;
import com.jsmall.dto.AdCountDTO;
import com.jsmall.dto.AdOrderDTO;
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

	@Override
	public void admin_visit_date(String admin_id) {
		// TODO Auto-generated method stub
		adminMapper.admin_visit_date(admin_id);
	}

	@Override
	public List<AdOrderDTO> order_list() {
		
	
		
		return adminMapper.order_list();
	}

	@Override
	public List<MemberVO> adMember() {
		// TODO Auto-generated method stub
		return adminMapper.adMember();
	}

	@Override
	public AdCountDTO adCount() {
		// TODO Auto-generated method stub
		return adminMapper.adCount();
	}
	
	
}

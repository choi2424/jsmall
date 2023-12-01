package com.jsmall.service;

import java.util.List;

import com.jsmall.domain.AdminVO;
import com.jsmall.domain.MemberVO;
import com.jsmall.dto.AdCountDTO;
import com.jsmall.dto.AdOrderDTO;

public interface AdminService {
	
	AdminVO ad_login(String admin_id);
	
	void admin_visit_date(String admin_id);
	
	List<AdOrderDTO> order_list();
	
	List<MemberVO> adMember();
	
	AdCountDTO adCount();
}

package com.jsmall.mapper;

import java.util.List;

import com.jsmall.domain.AdminVO;
import com.jsmall.domain.MemberVO;
import com.jsmall.dto.AdCountDTO;
import com.jsmall.dto.AdOrderDTO;

public interface AdminMapper {
	
	// 어드민 로그인
	AdminVO ad_login(String admin_id);
	
	// 어드민 접속시간 업데이트
	void admin_visit_date(String admin_id);
	
	List<AdOrderDTO> order_list();
	
	List<MemberVO> adMember();
	
	AdCountDTO adCount();
}

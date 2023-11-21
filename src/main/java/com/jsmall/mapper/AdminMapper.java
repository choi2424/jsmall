package com.jsmall.mapper;

import com.jsmall.domain.AdminVO;

public interface AdminMapper {
	
	// 어드민 로그인
	AdminVO ad_login(String admin_id);
	
	// 어드민 접속시간 업데이트
	void admin_visit_date(String admin_id);
}

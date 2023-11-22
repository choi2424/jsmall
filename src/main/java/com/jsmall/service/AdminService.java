package com.jsmall.service;

import com.jsmall.domain.AdminVO;

public interface AdminService {
	
	AdminVO ad_login(String admin_id);
	
	void admin_visit_date(String admin_id);
}

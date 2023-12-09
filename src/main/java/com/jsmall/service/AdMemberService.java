package com.jsmall.service;

import java.util.List;

import com.jsmall.domain.MemberVO;
import com.jsmall.dto.Criteria;

public interface AdMemberService {
	
	List<MemberVO> member_list(Criteria cri, String start_date, String end_date );
	
	int getTotalCount(Criteria cri, String start_date, String end_date);
}

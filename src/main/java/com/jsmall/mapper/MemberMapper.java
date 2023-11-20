package com.jsmall.mapper;

import com.jsmall.domain.MemberVO;

public interface MemberMapper {
	
	String idCheck(String member_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String member_id);
	
	
}

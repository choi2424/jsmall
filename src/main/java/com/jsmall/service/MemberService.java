package com.jsmall.service;

import com.jsmall.domain.MemberVO;

public interface MemberService {
	
	String idCheck(String member_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String member_id);	
	
	void modify(MemberVO vo);
	
	void delMember(String member_id);
}

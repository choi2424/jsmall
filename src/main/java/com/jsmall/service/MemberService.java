package com.jsmall.service;

import com.jsmall.domain.MemberVO;

public interface MemberService {

	String idCheck(String member_id);

	void join(MemberVO vo);

	MemberVO login(String member_id);

	void modify(MemberVO vo);

	void delMember(String member_id);

	String find_id(String member_name, String member_email);

	MemberVO find_pw(String member_id, String member_email);

	void change_password(String member_id, String member_password);
}

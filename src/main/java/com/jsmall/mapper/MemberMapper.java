package com.jsmall.mapper;

import com.jsmall.domain.MemberVO;

public interface MemberMapper {
	
	// 회원가입 아이디 중복검사
	String idCheck(String member_id);
	
	// 회원가입
	void join(MemberVO vo);
	
	// 로그인
	MemberVO login(String member_id);	
	
	// 회원수정
	void modify(MemberVO vo);
	
	// 회원탈퇴
	void delMember(String member_id);
}

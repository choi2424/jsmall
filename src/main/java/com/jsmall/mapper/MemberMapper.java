package com.jsmall.mapper;

import org.apache.ibatis.annotations.Param;

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
	
	// 아이디찾기
	String find_id(
			@Param("member_name") String member_name ,
			@Param("member_email") String member_email
	);
	
	// 비밀번호찾기
	MemberVO find_pw(
			@Param("member_id") String member_id ,
			@Param("member_email") String member_email
	);
	
	// 비밀번호변경
	void change_password(
			@Param("member_id") String member_id ,
			@Param("member_password") String member_password 
	);
}

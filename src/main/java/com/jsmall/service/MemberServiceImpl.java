package com.jsmall.service;

import org.springframework.stereotype.Service;

import com.jsmall.domain.MemberVO;
import com.jsmall.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;

	@Override
	public String idCheck(String member_id) {
		// TODO Auto-generated method stub
		return memberMapper.idCheck(member_id);
	}

	@Override
	public void join(MemberVO vo) {
		// TODO Auto-generated method stub
		memberMapper.join(vo);
	}

	@Override
	public MemberVO login(String member_id) {
		// TODO Auto-generated method stub
		
		return memberMapper.login(member_id);
	}

	@Override
	public void modify(MemberVO vo) {
		// TODO Auto-generated method stub
		memberMapper.modify(vo);
	}

	@Override
	public void delMember(String member_id) {
		// TODO Auto-generated method stub
		memberMapper.delMember(member_id);
	}
	
	@Override
	public String find_id(String member_name,String member_email) {
		// TODO Auto-generated method stub
		return memberMapper.find_id(member_name, member_email);
	}

	@Override
	public MemberVO find_pw(String member_id, String member_email) {
		// TODO Auto-generated method stub
		return memberMapper.find_pw(member_id, member_email);
	}

	@Override
	public void change_password(String member_id,String member_password) {
		// TODO Auto-generated method stub
		memberMapper.change_password(member_id,member_password);
	}
}

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
}

package com.jsmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsmall.domain.MemberVO;
import com.jsmall.dto.Criteria;
import com.jsmall.mapper.AdMemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdMemberServiceImpl implements AdMemberService {
	
	private final AdMemberMapper adMemberMapper;

	@Override
	public List<MemberVO> member_list(Criteria cri, String start_date, String end_date) {
		// TODO Auto-generated method stub
		return adMemberMapper.member_list(cri, start_date, end_date);
	}

	@Override
	public int getTotalCount(Criteria cri, String start_date, String end_date) {
		// TODO Auto-generated method stub
		return adMemberMapper.getTotalCount(cri, start_date, end_date);
	}
	
}

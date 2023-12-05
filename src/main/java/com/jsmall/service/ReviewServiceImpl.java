package com.jsmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsmall.domain.ReviewVO;
import com.jsmall.dto.Criteria;
import com.jsmall.mapper.ReviewMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	
	private final ReviewMapper reviewMapper;

	@Override
	public void review_insert(ReviewVO vo) {
		// TODO Auto-generated method stub
		reviewMapper.review_insert(vo);
	}

	@Override
	public List<ReviewVO> list(Integer pro_num, Criteria cri) {
		// TODO Auto-generated method stub
		return reviewMapper.list(pro_num, cri);
	}

	@Override
	public int listCount(Integer pro_num) {
		// TODO Auto-generated method stub
		return reviewMapper.listCount(pro_num);
	}

	@Override
	public void delete(Long rew_num) {
		// TODO Auto-generated method stub
		reviewMapper.delete(rew_num);
	}

	@Override
	public void review_modify(ReviewVO vo) {
		// TODO Auto-generated method stub
		reviewMapper.review_modify(vo);
	} 
}

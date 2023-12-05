package com.jsmall.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jsmall.domain.ReviewVO;
import com.jsmall.dto.Criteria;

public interface ReviewService {
	
	// 리뷰 추가
		void review_insert(ReviewVO vo);
		
		// 리뷰 상품후기 목록 데이터
		List<ReviewVO> list( Integer pro_num, Criteria cri);
		
		// 페이징 데이터
		int listCount(Integer pro_num);
		
		// 리뷰삭제
		void delete(Long rew_num);
		
		// 리뷰수정
		void review_modify(ReviewVO vo);
	
}

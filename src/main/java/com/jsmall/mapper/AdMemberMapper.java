package com.jsmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jsmall.domain.MemberVO;
import com.jsmall.dto.Criteria;

public interface AdMemberMapper {
	
	List<MemberVO> member_list(@Param("cri") Criteria cri,@Param("start_date") String start_date,@Param("end_date") String end_date);
	
	int getTotalCount(@Param("cri") Criteria cri,@Param("start_date") String start_date,@Param("end_date") String end_date);
}

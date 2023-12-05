package com.jsmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsmall.domain.MemberVO;
import com.jsmall.domain.ReviewVO;
import com.jsmall.dto.Criteria;
import com.jsmall.dto.PageDTO;
import com.jsmall.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequiredArgsConstructor
@Log4j
@RequestMapping("/user/review/*")
public class ReviewController {
	
	private final ReviewService reviewService; 
	
	// @RequestBody : JSON 데이터를 ReviewVO객체로 변환해주는 기능 ... 참고> 반대의미 어노테이션 @ResponseBody
	@PostMapping(value = "/new", consumes = "application/json" , produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> review_insert(@RequestBody ReviewVO vo, HttpSession session) throws Exception  {
		
//		log.info("리뷰vo : " + vo);
		
		ResponseEntity<String> entity = null;
		
		String member_id = ((MemberVO)session.getAttribute("loginOn")).getMember_id();
		
		vo.setMember_id(member_id);
		
		reviewService.review_insert(vo);
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
	}
	
	// 전통적인 형태의 주소 list?pro_num=10&page=1  -> Rest API개발형태주소 list/10/1
	@GetMapping("/list/{pro_num}/{page}")
	public ResponseEntity<Map<String, Object>>list(@PathVariable("pro_num") Integer pro_num , @PathVariable("page") Integer page) throws Exception {
				
		// ResponseEntity<Map<String, Object>> : 1) 상품후기목록 데이터  - List<ReviewVO> 2) 페이징 데이터 - PageDTO
		// ResponseEntityList<ReviewVO> : 1) 상품후기목록 데이터  - List<ReviewVO>
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 1) 상품후기 목록 데이터
		Criteria cri = new Criteria();
		
		cri.setAmount(5);
		cri.setPageNum(page);
		
		
		List<ReviewVO> list = reviewService.list(pro_num, cri);
		
		// 2) 페이징 데이터
		int listCount = reviewService.listCount(pro_num);
		PageDTO pageMaker = new PageDTO(cri, listCount);
		
		map.put("list", list);
		map.put("pageMaker", pageMaker);
		
		// jackson-databind 라이브러리에 의하여 map -> json으로 변환되어 ajax호출한 쪽으로 리턴값이 보내진다
		entity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
		return entity;
	}
	
	// 상품후기 삭제
	@DeleteMapping("/delete/{rew_num}")
	public ResponseEntity<String> delete(@PathVariable("rew_num") Long rew_num) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		// db연동작업
		reviewService.delete(rew_num);
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
	}
	
	@PutMapping(value = "/modify", consumes = "application/json" , produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> review_modify(@RequestBody ReviewVO vo, HttpSession session) throws Exception  {
		
//		log.info("리뷰vo : " + vo);
		
		ResponseEntity<String> entity = null;
		
		String member_id = ((MemberVO)session.getAttribute("loginOn")).getMember_id();
		
		vo.setMember_id(member_id);
		
		reviewService.review_modify(vo);
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
	}
	
}

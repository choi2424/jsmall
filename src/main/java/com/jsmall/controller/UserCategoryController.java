package com.jsmall.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsmall.domain.CategoryVO;
import com.jsmall.service.UserCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequiredArgsConstructor
@RequestMapping("/category/*")
public class UserCategoryController {
	
	private final UserCategoryService userCategoryService;
	
	// 2차 카테고리 정보
	@GetMapping("/secondCategory/{cg_parent_code}")
	public ResponseEntity<List<CategoryVO>> secondCategory(@PathVariable("cg_parent_code")Integer cg_parent_code) throws Exception{
			
//			log.info("1차카테고리 코드 : " + cg_parent_code);
			
		ResponseEntity<List<CategoryVO>> entity = null;
			
		entity = new ResponseEntity<List<CategoryVO>>(userCategoryService.secondCategoryList(cg_parent_code), HttpStatus.OK);
			
		// List<CategoryVO> list = adCategoryService.secondCategoryList(cg_parent_code)
		// list객체 -> JSON 로 변환하는 라이브러리.(jackson-databind 라이브러리:pom.xml참고)
			
		return entity;
			
	}
}

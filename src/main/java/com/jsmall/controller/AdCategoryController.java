package com.jsmall.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsmall.domain.CategoryVO;
import com.jsmall.service.AdCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequiredArgsConstructor
@RequestMapping("/admin/category/")
public class AdCategoryController {
	
	private final AdCategoryService adCategoryService;  
	
	// 1차 카테고리 선택에 따른 2차카테고리 정보를 클라이언트에게 제공.
	@ResponseBody
	@GetMapping("/secondCategory/{cg_parent_code}")
	public ResponseEntity<List<CategoryVO>> secondCategory(@PathVariable("cg_parent_code")Integer cg_parent_code) throws Exception{
		
//			log.info("1차카테고리 코드 : " + cg_parent_code);
		
		ResponseEntity<List<CategoryVO>> entity = null;
		
		entity = new ResponseEntity<List<CategoryVO>>(adCategoryService.secondCategoryList(cg_parent_code), HttpStatus.OK);
		
		// List<CategoryVO> list = adCategoryService.secondCategoryList(cg_parent_code)
		// list객체 -> JSON 로 변환하는 라이브러리.(jackson-databind 라이브러리:pom.xml참고)
		
		return entity;
	}
	
	// 카테고리 관리 폼
	@GetMapping("/category_list")
	public void category_list() {
		
	}
}

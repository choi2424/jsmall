package com.jsmall.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jsmall.domain.CategoryVO;
import com.jsmall.service.AdCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@ControllerAdvice(basePackages = {"com.jsmall.controller"})
public class GlobalControllerAdvice {

	private final AdCategoryService adCategoryService;
	
	@ModelAttribute
	public void firstCategoryList(Model model) {
//		log.info("1차 카테고리 리스트");
		
		List<CategoryVO> firstCategoryList = adCategoryService.firstCategoryList();
		
		model.addAttribute("firstCategoryList", firstCategoryList);
	}
}

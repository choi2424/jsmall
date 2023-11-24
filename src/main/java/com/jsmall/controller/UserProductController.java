package com.jsmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsmall.domain.ProductVO;
import com.jsmall.dto.Criteria;
import com.jsmall.dto.PageDTO;
import com.jsmall.service.UserProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pruduct/*")
@Log4j
public class UserProductController {
	
	private final UserProductService userProductService;
	
	// 메인및썸네일 이미지업로드 폴더경로 주입작업
	@Resource(name = "uploadPath") // servlet-context.xml 의 uploadPath bean이름 참조를 해야 함.
	private String uploadPath;
	
	public String pro_list(Criteria cri ,@ModelAttribute("cg_code") Integer cg_code, @ModelAttribute("cg_name") String cg_name, Model model) throws Exception {
		
		cri.setAmount(5);
		
		List<ProductVO> pro_list = userProductService.pro_list(cg_code, cri);
		pro_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		model.addAttribute("pro_list", pro_list);
		
		int totalCount = userProductService.getTotalCount(cg_code);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
		
		return "/user/product/pro_list";
	}
}

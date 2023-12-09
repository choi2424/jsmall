package com.jsmall.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsmall.domain.MemberVO;
import com.jsmall.dto.Criteria;
import com.jsmall.dto.PageDTO;
import com.jsmall.service.AdMemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member/*")
public class AdMemberController {
	
	private final AdMemberService adMemberService;
	
	@GetMapping("/member_list")
	public void member_list(Criteria cri,Model model,@ModelAttribute("start_date")String start_date,
			@ModelAttribute("end_date")String end_date) {
		
		List<MemberVO> member_list = adMemberService.member_list(cri, start_date, end_date);
		
		model.addAttribute("member_list", member_list);
		
		int totalCount = adMemberService.getTotalCount(cri, start_date, end_date);
		
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
}

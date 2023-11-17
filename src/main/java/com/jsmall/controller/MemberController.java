package com.jsmall.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsmall.domain.MemberVO;
import com.jsmall.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	
	private final PasswordEncoder passwordEncoder;
	
	// 회원가입 폼
	@GetMapping("join")
	public void join() {
		
	}
	
	@GetMapping("idCheck")
	public ResponseEntity<String> idCheck(String member_id) {
		
		ResponseEntity<String> entity = null;
		
		String id_check = "";
		
		if(memberService.idCheck(member_id) == null) {
			id_check = "yes";
		}else {
			id_check = "no";
		}
		
		entity = new ResponseEntity<String>(id_check , HttpStatus.OK);
		
		return entity;
	}
	
	// 회원가입
	@PostMapping("join")
	public String join(MemberVO vo,RedirectAttributes rttr) {
		
		vo.setMember_password(passwordEncoder.encode(vo.getMember_password())); 
		
		memberService.join(vo);
		
		return "redirect:/";
	}
}

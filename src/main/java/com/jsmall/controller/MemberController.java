package com.jsmall.controller;

import java.io.Console;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/member/**")
public class MemberController {
	
	private final MemberService memberService;
	
	private final PasswordEncoder passwordEncoder;
	
	// 회원가입 폼
	@GetMapping("/join")
	public void join() {
		
	}
	
	// 회원가입 중복아이디 체크
	@GetMapping("/idCheck")
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
	@PostMapping("/join")
	public String join(MemberVO vo,RedirectAttributes rttr) {
		
		vo.setMember_password(passwordEncoder.encode(vo.getMember_password())); 
		
		memberService.join(vo);
		
		return "redirect:/";
	}
	
	// 로그인 폼 페이지
	@GetMapping("/login")
	public void login() {
		
	}
	
	// 로그인
	@PostMapping("/login")
	public String login(RedirectAttributes rttr , HttpSession session ,String member_id , String member_password) {
		
		MemberVO vo = memberService.login(member_id);
		
		String url = "";
		
		if(vo != null) {
			
			if(passwordEncoder.matches(member_password, vo.getMember_password())) {
				session.setAttribute("loginOn", vo);
				url = "/";
			}else {
				
				url = "/member/login";
				rttr.addFlashAttribute("msg", "비밀번호가 일치하지않습니다");
			}
		}else {
			
			url = "/member/login";
			rttr.addFlashAttribute("msg", "아이디가 일치하지 않습니다");
		}
		return "redirect:" + url;
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 회원수정 인증 폼
	@GetMapping("/confirmModify")
	public void confirmModify() {
		log.info("회원수정 전 confirm 확인");
	}
	
	// 회원수정 인증 인증
	@PostMapping("/confirmModify")
	public String confirmModify(RedirectAttributes rttr , HttpSession session ,String member_id , String member_password) {
		
		log.info("아이디확인 : " + member_id);
		
		MemberVO vo = memberService.login(member_id);
		
		String url = "";
		
		if(vo != null) {
			
			if(passwordEncoder.matches(member_password, vo.getMember_password())) {
				url = "/member/modify";
			}else {
				
				url = "/member/confirmModify";
				rttr.addFlashAttribute("msg", "비밀번호가 일치하지않습니다");
			}
		}else {
			
			url = "/member/confirmModify";
			rttr.addFlashAttribute("msg", "아이디가 일치하지 않습니다");
		}
		return "redirect:" + url;
	}
	
	// 회원수정 폼
	@GetMapping("/modify")
	public void modify(HttpSession session,Model model) {
		MemberVO m_vo = (MemberVO)session.getAttribute("loginOn");
		
//		log.info("멤버 vo" + m_vo);
		
		model.addAttribute("memberVO", m_vo);
	}
	
	// 회원수정
	@PostMapping("/modify")
	public String modify(MemberVO vo, HttpSession session) throws Exception {
		
		String m_id = ((MemberVO)session.getAttribute("loginOn")).getMember_id();
		
		memberService.modify(vo);
		
		MemberVO db_vo = memberService.login(m_id);
		
		session.setAttribute("loginOn", db_vo);
		
		return "redirect:/";
	}
	
	// 마이페이지로 이동전 인증 확인 폼
	@GetMapping("/confirmMypage")
	public void confirmPw2() {
		
	}
	
	// 회원수정 인증 인증
	@PostMapping("/confirmMypage")
	public String confirmMypage(RedirectAttributes rttr , HttpSession session ,String member_id , String member_password) {
		
//		log.info("아이디확인 : " + member_id);
			
		MemberVO vo = memberService.login(member_id);
		
		String url = "";
		
		if(vo != null) {
			
			if(passwordEncoder.matches(member_password, vo.getMember_password())) {
				url = "/member/mypage";
			}else {
				
				url = "/member/confirmMypage";
				rttr.addFlashAttribute("msg", "비밀번호가 일치하지않습니다");
			}
		}else {
			
			url = "/member/confirmMypage";
			rttr.addFlashAttribute("msg", "아이디가 일치하지 않습니다");
		}
		return "redirect:" + url;
	}
	
	// 마이페이지
	@GetMapping("/mypage")
	public void mypage(HttpSession session,Model model) {
		
		MemberVO m_vo = (MemberVO)session.getAttribute("loginOn");
		
		
		model.addAttribute("memberVO", m_vo);
	}
	
	// 회원탈퇴 폼
	@GetMapping("/delMember")
	public void delMember() {
		
	}
	
	// 회원탈퇴
	@PostMapping("/delMember")
	public String delMember(HttpSession session,String member_id , String member_password , RedirectAttributes rttr) {
		
		MemberVO vo = memberService.login(member_id);
		
		String url = "";
		
		if(vo != null) {
			
			if(passwordEncoder.matches(member_password, vo.getMember_password())) {
				url = "/";
				session.invalidate();
				memberService.delMember(member_id);
			}else {
				
				url = "/member/delMember";
				rttr.addFlashAttribute("msg", "비밀번호가 일치하지않습니다");
			}
		}else {
			
			url = "/member/delMember";
			rttr.addFlashAttribute("msg", "아이디가 일치하지 않습니다");
		}
		return "redirect:" + url;
	}
}

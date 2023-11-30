package com.jsmall.controller;

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
	
	// 아이디 찾기 폼
	@GetMapping("/find_id")
	public void find_id() {
		
	}
	
	// 아이디 찾기
	@PostMapping("/find_id")
	public String find_id(String member_name,String member_email,RedirectAttributes rttr) {
		String msg = "";
		String url = "";
		String result = memberService.find_id(member_name, member_email);
		if(result == null) {
			msg = "이름이나 이메일이 정확하지 않습니다";
			url = "/member/find_id";
			rttr.addFlashAttribute("msg", msg);
		}else {
			msg = "아이디는" + result + "입니다";
			url = "/member/login";
			rttr.addFlashAttribute("msg", msg);
		}
		return "redirect:" + url;
	}
	
	// 비밀번호 찾기 폼
	@GetMapping("/find_pw")
	public void find_pw() {
		
	}
	
	// 비밀번호 찾기
	@PostMapping("/find_pw")
	public String find_pw(String member_id,String member_email,RedirectAttributes rttr,HttpSession session) {
		String msg = "";
		String url = "";
		MemberVO result = memberService.find_pw(member_id, member_email);
		if(result == null) {
			msg = "아이디나 이메일이 정확하지 않습니다";
			url = "/member/find_pw";
			rttr.addFlashAttribute("msg", msg);
		}else {
			msg = "비밀번호를 변경해주세요";
			url = "/member/change_password";			
			rttr.addFlashAttribute("msg", msg);
			session.setAttribute("change_password", result);
		}
		
		
		return "redirect:" + url;
	}
	
	// 비밀번호 변경 폼
	@GetMapping("/change_password")
	public void change_password() {
		
	}
	
	// 비밀번호 변경
	@PostMapping("/change_password")
	public String change_password(HttpSession session,String member_id,String member_password,RedirectAttributes rttr) {
		
		MemberVO vo = (MemberVO)session.getAttribute("change_password");
		
		member_id = vo.getMember_id();
		
		// db저장
		memberService.change_password(member_id, passwordEncoder.encode(member_password));
		
		session.removeAttribute("change_password");
		
		return "redirect:/member/login";
		
		
	}
}

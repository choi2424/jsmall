package com.jsmall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsmall.domain.AdminVO;
import com.jsmall.dto.AdOrderDTO;
import com.jsmall.service.AdOrderService;
import com.jsmall.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@Log4j
@RequestMapping("/admin/*")
public class AdminController {
	
	private final AdminService adminservice;
	private final PasswordEncoder passwordEncoder;
	private final AdOrderService adOrderService;
	
	// 어드민 로그인 폼
	@GetMapping("/ad_login")
	public void adminLogin() {
		
	}
	
	// 어드민 로그인 인증
	@PostMapping("/ad_login")
	public String ad_login(AdminVO a_vo,HttpSession session,RedirectAttributes rttr) throws Exception{
		
		AdminVO db_vo = adminservice.ad_login(a_vo.getAdmin_id());
		
		
		String url = "";
		String msg = "";
		
		// 아이디가 존재하면 true 존재하지 않으면 false
		if(db_vo != null) {
			// 사용자가 입력한 비밀번호(평문 텍스트) 와 DB에서 가져온 암호화된 비밀번호 일치여부 검사.  
			if(passwordEncoder.matches(a_vo.getAdmin_pw(), db_vo.getAdmin_pw())) {
				// 로그인 성공결과로 서버측의 메모리를 사용하는 세션형태작업
				session.setAttribute("adminStatus", db_vo);
				// 로그인 시간 업데이트
				
				adminservice.admin_visit_date(a_vo.getAdmin_id());
				
				url = "/admin/admin_main"; //관리자 메인페이지 주소
			}else {
				// 비밀번호가 일치하지 않음
				url = "/admin/ad_login"; // 로그인 폼주소
				msg = "failPW";
				rttr.addFlashAttribute("msg", msg); // 로그인 폼 jsp파일에서 사용목적
			}
		}else {
			// 아이디가 일치하지 않음
			url = "/admin/ad_login"; // 로그인 폼주소
			msg = "failID";
			rttr.addFlashAttribute("msg", msg);
		}
		
		return "redirect:" + url;
	}
	
	// 어드민 메인페이지 폼
	@GetMapping("/admin_main")
	public void admin_main(Model model) {
		
		List<AdOrderDTO> adOrderDTO = adminservice.order_list();
		
		model.addAttribute("adOrderDTO", adOrderDTO);
	}
	
	// 어드민 로그아웃
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/admin/ad_login";
	}
	
}

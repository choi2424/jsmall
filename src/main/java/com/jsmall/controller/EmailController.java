package com.jsmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsmall.dto.EmailDTO;
import com.jsmall.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
	
	private final EmailService emailService; 
	
	@GetMapping("/authCode")
	public ResponseEntity<String> authSend(EmailDTO dto,HttpSession session) {
		
		ResponseEntity<String> entity = null;
		
		
		String authCode = "";
		for (int i = 0; i < 6; i++) {
			authCode += String.valueOf((int)(Math.random() * 10));
		}
		
		log.info("인증코드 : " + authCode);
		
		// 사용자에게 메일로 발급해준 인증코드를 입력시 비교목적으로 세션형태를 미리 저장해둔다.
		session.setAttribute("authCode", authCode);
		
		try {
			emailService.sendMail(dto, authCode); // 메일보내기
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
		
		return entity;
	}
	
	// 인증코드 확인 = 세션형태로 저장한 정보를 이용
		@GetMapping("/confirmAuthCode")
		public ResponseEntity<String> confirmAuthcode(String authCode,HttpSession session) {
				
			ResponseEntity<String> entity = null;
				
			
			//String sAuthCode = "";
			if(session.getAttribute("authCode") != null) {
				// 인증일치 여부
				if(authCode.equals(session.getAttribute("authCode"))) {
					entity = new ResponseEntity<String>("success",HttpStatus.OK); 
				}else {
					entity = new ResponseEntity<String>("fail",HttpStatus.OK);
				}
			}else {
				// 세션이 소멸되었을때
				entity = new ResponseEntity<String>("request",HttpStatus.OK);
			}
			
			
			
			return entity;
		}
}

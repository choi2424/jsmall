package com.jsmall.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/email")
public class EmailController {
	
	public ResponseEntity<String> authCode(String member_email) {
		
		ResponseEntity<String> entity = null;
		
		
		
		return entity;
	}
}

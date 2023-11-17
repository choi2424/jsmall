package com.jsmall.service;

import com.jsmall.dto.EmailDTO;

public interface EmailService {
	
	void sendMail(EmailDTO dto, String message);
}

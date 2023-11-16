package com.jsmall.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {

	private String member_id;
	private String member_name;
	private String member_email;
	private String member_password;
	private String member_zipcode;
	private String member_addr;
	private String member_deaddr;
	private String member_phone;
	private int member_point;
	private Date member_lastlogin;
	private Date member_datesub;
	private Date member_updatedate;
	
}

package com.jsmall.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentVO {

	private Integer pay_code;
	private Long 	odr_code;
	private String 	member_id;
	private String 	pay_method;
	private Date 	pay_date;
	private Integer pay_tot_price;
	private Integer pay_nobank_price;
	private String 	pay_nobank_user;
	private String 	pay_nobank;
	private String 	pay_memo;
	private String 	pay_bank_account;
}

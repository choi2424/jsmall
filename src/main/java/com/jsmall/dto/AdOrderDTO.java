package com.jsmall.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AdOrderDTO {
	
	private Long ord_code;  // 주문번호
	private String member_id; // 주문자명
	private String ord_name; // 수령자명
	private String ord_tel; // 전화번호
	private String pay_method; // 결제방법
	private int ord_price; // 총주문금액
	private Date ord_regdate; // 주문일시
	
}

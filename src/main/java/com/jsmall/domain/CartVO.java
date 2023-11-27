package com.jsmall.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartVO {

	private Long cart_code;
	
	private Integer pro_num; 
	private String	member_id;
	
	private int cart_amount;
}

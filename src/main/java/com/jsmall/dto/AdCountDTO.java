package com.jsmall.dto;

import lombok.Data;

@Data
public class AdCountDTO {
	
	int total_orders;
	String total_order_amount;
	
	int this_month_orders;
	String this_month_order_amount;
	
	int last_month_orders;
	String last_month_order_amount;
}

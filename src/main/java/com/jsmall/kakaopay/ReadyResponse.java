package com.jsmall.kakaopay;

import lombok.Data;

// 결제준비요청(1차) 의 응답결과를 저장하기위한 클래스.

@Data
public class ReadyResponse {
	/*
	tid	String	결제 고유 번호, 20자
	next_redirect_app_url	String	요청한 클라이언트(Client)가 모바일 앱일 경우
	카카오톡 결제 페이지 Redirect URL
	next_redirect_mobile_url	String	요청한 클라이언트가 모바일 웹일 경우
	카카오톡 결제 페이지 Redirect URL
	next_redirect_pc_url	String	요청한 클라이언트가 PC 웹일 경우
	카카오톡으로 결제 요청 메시지(TMS)를 보내기 위한 사용자 정보 입력 화면 Redirect URL
	android_app_scheme	String	카카오페이 결제 화면으로 이동하는 Android 앱 스킴(Scheme)
	ios_app_scheme	String	카카오페이 결제 화면으로 이동하는 iOS 앱 스킴
	created_at	Datetime	결제 준비 요청 시간
	*/
	
	private String tid; // 결제 고유 번호, 20자
	
	// 요청한 클라이언트가 PC 웹일 경우 카카오톡으로 결제 요청 메시지(TMS)를 보내기 위한 사용자 정보 입력 화면 Redirect URL
	private String next_redirect_pc_url;
	
	private String partner_order_id; // 가맹점(쇼핑몰)의 주문번호
}

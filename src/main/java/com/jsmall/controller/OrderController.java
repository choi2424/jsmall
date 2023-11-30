package com.jsmall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.jsmall.domain.CartVO;
import com.jsmall.domain.MemberVO;
import com.jsmall.domain.OrderVO;
import com.jsmall.domain.PaymentVO;
import com.jsmall.dto.CartDTOList;
import com.jsmall.kakaopay.ReadyResponse;
import com.jsmall.service.CartService;
import com.jsmall.service.KakaoPayServiceImpl;
import com.jsmall.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@RequestMapping("/user/order/*")
@Log4j
@Controller
public class OrderController {
	
	private final OrderService orderService;
	private final CartService cartService;
	private final KakaoPayServiceImpl kakaoPayServiceImpl;
	
	@GetMapping("/order_info")
	public void order_info(Model model,HttpSession session) {
		
		String member_id = ((MemberVO)session.getAttribute("loginOn")).getMember_id();
		
		List<CartDTOList> order_info = cartService.cart_list(member_id);
		
		// 장바구니 물품 총액
		double order_price = 0;
		
		for (CartDTOList vo : order_info) {
		    vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		    order_price += ((double)vo.getPro_price() - (vo.getPro_price() * vo.getPro_discount() / 100)) * (double)vo.getCart_amount();
		}
		
		model.addAttribute("order_info", order_info);
		model.addAttribute("order_price", order_price);
	}
	
	// 상품상세에서 주문하기
	@GetMapping("/order_ready")
	public String order_ready(CartVO vo, HttpSession session) throws Exception {
		
		String member_id = ((MemberVO)session.getAttribute("loginOn")).getMember_id();
		vo.setMember_id(member_id);
		
		cartService.cart_add(vo);
		
		return "redirect:/user/order/order_info";
	}
	
	// 카카오페이 결제선택을 진행했을 경우
	// 1)결제 준비 요청
	@GetMapping(value = "/orderPay" , produces = "application/json")
	public @ResponseBody ReadyResponse payReady(String paymethod, OrderVO o_vo,PaymentVO p_vo,
			int totalprice, HttpSession session ) throws Exception {	
		
		String member_id = ((MemberVO)session.getAttribute("loginOn")).getMember_id();
		o_vo.setMember_id(member_id); // 아이디값 할당
		
		// 시퀀스를 주문번호로 사용 : 동일한 주문번호값이 사용.
		// 주문테이블 저장 
		
		Long ord_code = (long)orderService.getOrderseq();
		o_vo.setOrd_code(ord_code); // 주문번호 저장
		
//		log.info("결제방법 : " + paymethod);
//		log.info("주문정보 : " + o_vo);
//		log.info("결제 정보 : " + p_vo);
		
		o_vo.setOrd_status("주문완료");
		o_vo.setPayment_status("결제완료");
		
		p_vo.setPay_method("카카오페이");
		p_vo.setOdr_code(ord_code);
		p_vo.setMember_id(member_id);
		p_vo.setPay_tot_price(totalprice);
		
		List<CartDTOList> cart_list = cartService.cart_list(member_id);
		
		String itemName = cart_list.get(0).getPro_name() + "외" + String.valueOf(cart_list.size()-1) + "건";
		
		//주문,주문상세 정보저장 , 장바구니 삭제
		orderService.order_insert(o_vo, p_vo);
		
		
		// 1)kakao Pay 결제 준비 요청
		ReadyResponse readyResponse = kakaoPayServiceImpl.payReady(o_vo.getOrd_code(), itemName, cart_list.size(), member_id, totalprice);
		
//		log.info("결제고유번호 : " + readyResponse.getTid());
//		log.info("결제요청URL : " + readyResponse.getNext_redirect_pc_url());
		
		// 카카오페이 결제승인 요청작업에 필요한 정보준비
		session.setAttribute("tid", readyResponse.getTid());
		session.setAttribute("oder_code", o_vo.getOrd_code());
		
		return readyResponse;
	}
	
	// 결제 승인 요청 작업 http://localhost:9090/user/order/orderApproval
	@GetMapping("/orderApproval")
	public String orderApproval(@RequestParam("pg_token") String pg_token, HttpSession session) {
			
		// 2)kakao Pay 결제승인요청 작업
		Long oder_code = (Long)session.getAttribute("oder_code");
		String tid = (String)session.getAttribute("tid");
		String member_id = ((MemberVO)session.getAttribute("loginOn")).getMember_id();
		
		kakaoPayServiceImpl.payApprove(oder_code, tid, pg_token, member_id);
		
		session.removeAttribute("oder_code");
		session.removeAttribute("tid");
		
		return "redirect:/user/order/orderComplete";
		
	}
	
	// 결제 완료페이지
	@GetMapping("/orderComplete")
	public void orderComplete() {
		
	}
	
	// 결제 취소시	http://localhost:9090/user/order/orderCancel
	@GetMapping("/orderCancel")
	public void orderCancel() {
		
	}
	
	
	// 결제 실패시	http://localhost:9090/user/order/orderFail
	@GetMapping("/orderFail")
	public void orderFail() {
		
	}
	
	// 무통장 입금
	@GetMapping("/nobank")
	public ResponseEntity<String> nobank(String paymethod, OrderVO o_vo,PaymentVO p_vo, int totalprice, HttpSession session) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		String member_id = ((MemberVO)session.getAttribute("loginOn")).getMember_id();
		o_vo.setMember_id(member_id); // 아이디값 할당
		
		// 시퀀스를 주문번호로 사용 : 동일한 주문번호값이 사용.
		// 주문테이블 저장 
		
		Long odr_code = (long)orderService.getOrderseq();
		o_vo.setOrd_code(odr_code); // 주문번호 저장
		
//			log.info("결제방법 : " + paymethod);
//			log.info("주문정보 : " + o_vo);
//			log.info("결제 정보 : " + p_vo);
		
		o_vo.setOrd_status("주문완료");
		o_vo.setPayment_status("결제완료");
		
		p_vo.setPay_method("무통장입금");
		p_vo.setOdr_code(odr_code);
		p_vo.setMember_id(member_id);
		p_vo.setPay_tot_price(totalprice);
		p_vo.setPay_nobank_price(totalprice);
		
		
		//주문,주문상세 정보저장 , 장바구니 삭제
		orderService.order_insert(o_vo, p_vo);
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
	}
}

package com.jsmall.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsmall.domain.CartVO;
import com.jsmall.domain.MemberVO;
import com.jsmall.dto.CartDTOList;
import com.jsmall.service.CartService;
import com.jsmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
@Controller
@RequestMapping("/user/cart/*")
public class CartController {
	
	private final CartService cartService;
	
	// 메인및 썸네일 이미지 업로드 폴더경로 주입작업
	@Resource(name = "uploadPath") // servlet-context.xml 에서 bean이름 참조
	private String uploadPath;
	
	
	// 장바구니에 물품추가
	@PostMapping("/cart_add")
	public ResponseEntity<String> cart_add(CartVO c_vo,HttpSession session) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		String member_id = ((MemberVO)session.getAttribute("loginOn")).getMember_id();
		c_vo.setMember_id(member_id);
		
		cartService.cart_add(c_vo);
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
	}
	
	// 장바구니 목록
	@GetMapping("/cart_list")
	public void cart_list(HttpSession session , Model model) {
		
		String member_id = ((MemberVO)session.getAttribute("loginOn")).getMember_id();
		
		List<CartDTOList> cart_list = cartService.cart_list(member_id);
		
		// 장바구니 물품 총액
		double cart_total_price = 0;
		
		for (CartDTOList vo : cart_list) {
		    vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		    cart_total_price += ((double)vo.getPro_price() - (vo.getPro_price() * vo.getPro_discount() / 100)) * (double)vo.getCart_amount();
		}
		
		model.addAttribute("cart_list", cart_list);
		model.addAttribute("cart_total_price", cart_total_price);
	}
	
	// 장바구니 이미지
	@ResponseBody
	@GetMapping("/imageDisplay") // /user/cart/imageDisplay
	public ResponseEntity<byte[]> imgDisplay(String dateFolderName, String fileName) throws Exception {
				
			return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	// 장바구니 물품삭제
	@PostMapping("/cart_list_del")
	public ResponseEntity<String> cart_list_del(Long cart_code) {
		
		ResponseEntity<String> entity = null;
		
		cartService.cart_list_del(cart_code);
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
	}
	
	// 장바구니 물품 수량 변경
	@PostMapping("/amount_change")
	public ResponseEntity<String> amount_change(Long cart_code, int cart_amount) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		cartService.amount_change(cart_code, cart_amount);
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
	}
	
	// 체크된상품 삭제
	@ResponseBody
	@PostMapping("/cart_checked_del")
	public ResponseEntity<String> cart_checked_del(@RequestParam("cart_code_arr[]") List<Long>cart_code_arr) {
		
		log.info("카트코드 리스트" + cart_code_arr);
		
		ResponseEntity<String> entity = null;
		
		cartService.cart_checked_del(cart_code_arr);
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
	}
}

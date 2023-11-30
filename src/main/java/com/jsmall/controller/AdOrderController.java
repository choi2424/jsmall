package com.jsmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsmall.domain.OrderDetailProductVO;
import com.jsmall.domain.OrderVO;
import com.jsmall.dto.Criteria;
import com.jsmall.dto.PageDTO;
import com.jsmall.service.AdOrderService;
import com.jsmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@RequestMapping("/admin/order/*")
@Log4j
@Controller
public class AdOrderController {
	
	private final AdOrderService adOrderService;
	
	// 메인및썸네일 이미지업로드 폴더경로 주입작업
	@Resource(name = "uploadPath") // servlet-context.xml 의 bean이름 참조를 해야 함.
	private String uploadPath;
	
	// 상품리스트
	@GetMapping("/order_list")
	public void order_list(Criteria cri,Model model,@ModelAttribute("start_date")String start_date,
			@ModelAttribute("end_date")String end_date) {
		
		cri.setAmount(5);
		
		List<OrderVO> order_list = adOrderService.order_list(cri, start_date, end_date);
		
		model.addAttribute("order_list", order_list);
		
		int totalCount = adOrderService.getTotalCount(cri, start_date, end_date);
		
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	
	// 주문상세 페이지
	@GetMapping("/order_detail_info2/{ord_code}")
	public String order_detail_info(@PathVariable("ord_code") Long ord_code, Model model) {
		
		List<OrderDetailProductVO> orderProductList = adOrderService.order_detail_info(ord_code);
		
		orderProductList.forEach(vo -> {
			   vo.getProductVO().setPro_up_folder(vo.getProductVO().getPro_up_folder().replace("\\", "/"));
		   });
		
		model.addAttribute("orderProductList", orderProductList);
		
		return "/admin/order/order_detail_product";
	}
	
	// 상품 리스트에서 보여줄 이미지 
    @ResponseBody
    @GetMapping("/imageDisplay") 
    public ResponseEntity<byte[]> imgDisplay(String dateFolderName, String fileName) throws Exception {
      
       return FileUtils.getFile(uploadPath + dateFolderName, fileName);
    }
	
    // 주문 상세내역에서 개별상품 삭제
    @GetMapping("/order_product_delete")
    public String order_product_delete(Criteria cri,Long ord_code,Integer pro_num) throws Exception {
		   
    	// 주문상세 개별삭제
		   
    	// log.info("주문번호" + ord_code);
		// log.info("상품번호" + pro_num);
		   
		adOrderService.order_product_delete(ord_code, pro_num);
		   
		return "redirect:/admin/order/order_list" + cri.getListLink();
	}
}

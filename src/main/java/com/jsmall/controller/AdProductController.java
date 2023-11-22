package com.jsmall.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.jsmall.domain.ProductVO;
import com.jsmall.service.AdProductService;
import com.jsmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
@Controller
@RequestMapping("/admin/product/*")
public class AdProductController {
	
	private final AdProductService adProductService;
	
	// 메인및 썸네일 이미지 업로드 폴더경로 주입작업
	@Resource(name = "uploadPath") // servlet-context.xml 에서 bean이름 참조
	private String uploadPath;
	
	// CKEditor에서 사용되는 업로드 폴더경로
	@Resource(name = "uploadCKPath") // servlet-context.xml 에서 bean이름 참조
	private String uploadCKPath;
	
	
	// 어드민 상품등록 폼
	@GetMapping("/pro_insert")
	public void pro_insert() {
		
	}
	
	// 어드민 상품 등록
	@PostMapping("/pro_insert")
	public String pro_insert(ProductVO vo,MultipartFile uploadFile) throws Exception {
		
		// 1)파일업로드 작업 . 선수작업 필요 : FileUtils 클래스 작업
		String dateFolder = FileUtils.getDateFolder();
		String savedFileName = FileUtils.uploadFile(uploadPath, dateFolder, uploadFile);
		
		vo.setPro_img(savedFileName);
		vo.setPro_up_folder(dateFolder);
		
		// 2)상품정보 저장
		adProductService.pro_insert(vo);
		
		return "redirect:/admin/product/pro_list";
	}
	
	// 관리자 상품목록
	@GetMapping("/pro_list")
	public void pro_list() {
		
	}
}

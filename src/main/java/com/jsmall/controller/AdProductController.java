package com.jsmall.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsmall.domain.CategoryVO;
import com.jsmall.domain.ProductVO;
import com.jsmall.dto.Criteria;
import com.jsmall.dto.PageDTO;
import com.jsmall.service.AdCategoryService;
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
	private final AdCategoryService adCategoryService;
	
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
	
	// 상품등록 폼 이미지 등록
	@PostMapping("/imageUpload")
	public void imageUpload(HttpServletRequest req, HttpServletResponse res, MultipartFile upload) {
		
		OutputStream out = null;
		PrintWriter printWriter = null; 
				
		// 클라이언트에게 보내는 응답설정
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		try {
			
			// 1) 파일 업로드 작업
			String fileName = upload.getOriginalFilename(); // 클라이언트에서 전송한 파일이름
			byte[] bytes = upload.getBytes(); // 업로드 한 파일을 byte배열로 읽어들임.
			
			String ckUploadPath = uploadCKPath + fileName;
			
			log.info("CKEditor파일경로 : " + ckUploadPath);
			
			out = new FileOutputStream(new File(ckUploadPath)); // 0kb 파일생성
			
			out.write(bytes); // 출력스트림 작업
			out.flush();
			
			// 2) 파일 업로드 작업후 파일정보를 CKEditor로 보내는 작업
			printWriter = res.getWriter();
			
			// 브라우저의 CKEditor에서 사용한 파일정보를 참조하는 경로설정
	        String fileUrl = "/ckupload/" + fileName;

	        printWriter.println("{\"filename\":\"" +  fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
	        printWriter.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(printWriter != null) printWriter.close();
		}
	}
	
	// 관리자 상품목록
	@GetMapping("/pro_list")
	public void pro_list(Criteria cri,Model model) throws Exception {
		cri.setAmount(5);
		
		List<ProductVO> pro_list = adProductService.pro_list(cri);
		
		pro_list.forEach(vo ->{
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		model.addAttribute("pro_list",pro_list);
		
		int totelCount = adProductService.getTotelCount(cri);
		model.addAttribute("pageMaker",new PageDTO(cri, totelCount));
	}
	
	// 상품 리스트에서 보여줄 이미지 <img sec="매핑주소">
	@ResponseBody
	@GetMapping("/imageDisplay") // /admin/product/imageDisplay
	public ResponseEntity<byte[]> imgDisplay(String dateFolderName, String fileName) throws Exception {
			
		return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	// 상품 리스트에서 체크된 상품 수정
	@ResponseBody
	@PostMapping("pro_checked_modify1")
	public ResponseEntity<String> pro_checked_modify1(
			@RequestParam("pro_num_arr[]") List<Integer> pro_num_arr,
			@RequestParam("pro_price_arr[]") List<Integer> pro_price_arr,
			@RequestParam("pro_buy_arr[]") List<String> pro_buy_arr
			) throws Exception {
		
		log.info("상품번호목록" + pro_buy_arr);
		
		ResponseEntity<String> entity = null;
		
		adProductService.pro_checked_modify1(pro_num_arr, pro_price_arr, pro_buy_arr);
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
		
	}
	
	// 어드민 상품목록 삭제
	@DeleteMapping("/delete/{pro_num}")
	public ResponseEntity<String> delete(@PathVariable("pro_num") Integer pro_num) {
		
		ResponseEntity<String> entity = null;
		
		adProductService.delete(pro_num);
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
	}
	
	// 상품수정 폼
	@GetMapping("/pro_edit")
	public void pro_edit(@ModelAttribute("cri") Criteria cri,Integer pro_num,Model model) {
		
		
		ProductVO p_vo = adProductService.pro_edit_page(pro_num);
		
		p_vo.setPro_up_folder(p_vo.getPro_up_folder().replace("\\", "/")); // 이스케이프 시퀀스 문자
		model.addAttribute("productVO", p_vo);
		
		CategoryVO firstCategory = adCategoryService.get(p_vo.getCg_code());
		model.addAttribute("first_category", firstCategory);
		
		// 1차카테고리를 부모로 둔 2차카테고리 정보. 예 > PANTS(2)
		model.addAttribute("second_categoryList", adCategoryService.secondCategoryList(firstCategory.getCg_parent_code()));
		
		
	}
	
	// 상품수정
	@PostMapping("/pro_edit")
	public String pro_edit(Criteria cri,ProductVO vo,MultipartFile uploadFile ,RedirectAttributes rttr) throws Exception{
		
		// 상품 리스트에서 사용할 정보(검색 페이징정보)
		log.info("검색 페이징 정보" + cri);
		// 상품 수정내용
		log.info("상품수정내용" + vo);
		
		vo.setPro_up_folder(vo.getPro_up_folder().replace("/", "\\"));

		if(!uploadFile.isEmpty()) {
			
			// 1)기존 이미지 파일 삭제 작업
			FileUtils.deleteFile(uploadPath, vo.getPro_up_folder(), vo.getPro_img());
			// 2)업로드 작업
			String dateFolder = FileUtils.getDateFolder();
			String savedFileName = FileUtils.uploadFile(uploadPath, dateFolder, uploadFile);
			
			// 3)DB에 저장할 새로운 날짜폴더명 및 이미지명 변경 작업 
			vo.setPro_img(savedFileName);
			vo.setPro_up_folder(dateFolder);
		}
		
		// DB연동작업
		adProductService.pro_edit(vo);
		
		
		
		return "redirect:/admin/product/pro_list" + cri.getListLink();
	}

}

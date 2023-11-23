package com.jsmall.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

// 파일 업로드 관련 필요한 메서드를 구성
public class FileUtils {
	
	// 날짜폴더 생성을 위한 날짜정보
	public static String getDateFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator); 
		
	}
	
	// 서버에 파일업로드 기능 및 업로드
	public static String uploadFile(String uploadFolder,String dateFolder,MultipartFile uploadFile) {
		
		String realUploadFileName = ""; // 실제 업로드만 파일이름(파일이름 중복방지)
		
		// File : 파일과 폴더관련작업하는 기능
		File file = new File(uploadFolder,dateFolder); // 예 " C:/dev/upload" , "20203/11/02"
		
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		String clientFileName = uploadFile.getOriginalFilename();
		
		// 파일명을 중복방지를 위하여 고유한 문자열을 만들어주는 클래스(UUID) 
		UUID uuid = UUID.randomUUID();
		
		// bd590e3c-8098-404a-9967-f04027fd7821_download.jpg
		realUploadFileName = uuid.toString() + "_" + clientFileName;
		
		try {
			//file : "C:/dev/upload"/2023/11/02" + realUploadFileName : 업로드할 파일명
			File saveFile = new File(file,realUploadFileName);
			//파일 업로드 (파일복사)
			uploadFile.transferTo(saveFile);
			
			// Thumbnail 작업
			if(checkImageType(saveFile)) { // 첨부된 파일이 이미지일 경우라면 
				
				// 파일출력스트림 클래스
				FileOutputStream thumbnail = new FileOutputStream(new File(file,"s_" + realUploadFileName));
				
				// 원본이지미파일의 내용을 스트링방식으로 읽어서 , 썸네일 이미지파일에 쓰는작업
				Thumbnailator.createThumbnail(uploadFile.getInputStream(),thumbnail,100,100);
				
				thumbnail.close();
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
						
		return realUploadFileName;
	}
	// 업로드되는 파일구분 (이미지 파일 또는 일반파일 구분)
	private static boolean checkImageType(File saveFile) {
		
		boolean isImageType = false; // 변수의값이 true면 이미지파일이고 false면 일반파일 구분작업
		
		try {
			// MIME : text/html, text/plain , image/jpeg
			String contentType = Files.probeContentType(saveFile.toPath());
			
			// 변수의 값을 image라는 문자열로 시작하고 있는지 검사 return true, false 
			isImageType = contentType.startsWith("image");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isImageType;
	}
	
	// 프로젝트 외부폴더에서 관리되고 있는 상품이미지를 브라우저의 <img src="test.jpg"> 이미지 태그로부터 요청이 들어왔을때 바이트 배열로 보내주는 작업.
	// String uploadPath : 업로드 폴더 경로
	// String fuleName : 날자 폴더경로를 포함한 파일명.
	// ResponseEntity 클래스 - 1) 헤더(header) 2) 바디(body)-데이터 3) 상태코드
	public static ResponseEntity<byte[]> getFile(String uploadPath, String fullName) throws Exception {
		
		ResponseEntity<byte[]> entity = null;
		
		File file = new File(uploadPath, fullName); // 상품이미지 파일을 참조하는 파일객체
		
		if(!file.exists()) {
			return entity; // 파일이 해당 경로에 존재하지 않는다면 null로 리턴.
		}
		
		// 1)Header
		// Files.probeContentType(file.toPath()) : image/jpg
		// file : 918b3d4e-8e4d-4f67-8243-c96a3f725120_KakaoTalk_20230816_112308017.jpg
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", Files.probeContentType(file.toPath()));
		
		entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		
		return entity;
		
	}
	
	// 파일삭제
	// String uploadPath : 업로드 폴더명  servlet-context-xml에 uploadPath bean정보 사용.
	// String folderName : 날짜폴더명 
	// String fileName : 파일명
	public static void deleteFile(String uploadPath, String folderName, String fileName) {
		
		// File.separatorChar : 배포된 서버의 운영체제에서 사용하는 파일의 경로구분자를 반환
		// 예 > window : \ 리눅스 : /
		
		//1) 원본이미지 삭제
		new File((uploadPath + folderName + "\\" + fileName).replace('\\', File.separatorChar)).delete();
		//2) 썸네일 이미지 삭제
		new File((uploadPath + folderName + "\\" + "s_" + fileName).replace('\\', File.separatorChar)).delete();
	}
	
}

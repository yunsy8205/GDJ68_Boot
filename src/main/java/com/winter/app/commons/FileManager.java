package com.winter.app.commons;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	//file 저장 후 파일명 리턴
	public String save(String path, MultipartFile multipartFile)throws Exception{
		//어디에?, 어떤파일을?
		//1. 파일 객체 생성
		File file = new File(path);
		//폴더가 없으면 만들어 준다. 존재하는지 확인 exists()
		if(!file.exists()) {//존재하지 않는다면
			file.mkdirs();//파일을 만들어 달라는 의미
		}
		//2. 저장할 파일명 생성
		String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
		//3. 파일을 저장
		//FileCopyUtils.copy
		//FileCopyUtils.copy(multipartFile.getBytes(), file);
		//transferTo             둘 중 하나를 이용하면 된다.
		file = new File(file, fileName); // 이 경로, 이름
		multipartFile.transferTo(file); //이 파일을 저장
		
		return fileName;
	}


}

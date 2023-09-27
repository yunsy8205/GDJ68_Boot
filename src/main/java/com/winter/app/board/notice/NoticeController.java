package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board") // 모든 메서드에 포함되어 있다.
	public String getBoard() {
		return "notice";
	}
	
	//ModelAndView, void, String
	@GetMapping("list")
	public String getList(Pager pager, Model model)throws Exception{
		List<BoardVO> ar = noticeService.getList(pager);
		model.addAttribute("list", ar);
		
		//ERROR, WARN, INFO, DEBUG, TRACE 단계
		log.error("getList 실행");//INFO 단계 이상만 콘솔에 찍힘
		return "board/list";
	}
	
	@GetMapping("fileDown")
	public String getFileDown(FileVO fileVO, Model model) throws Exception{
		fileVO = noticeService.getFileDetail(fileVO);//파일네임 리턴
		model.addAttribute("fileVO", fileVO);		
		return "fileDownView";//같은 bean 객체가 있다면 그것을 실행
	}
	
	@GetMapping("add")
	public String add()throws Exception{
		return "board/add";
	}
	
	@PostMapping("add")
	public String addTest(NoticeVO noticeVO, MultipartFile [] files)throws Exception{
		//log.info("NoticeVO : {}", noticeVO);//{}사이에 노티스VO를 넣는다. +(더하기)보다 성능이 좋다.
		//log.info(files); 에러발생
		//log.info("files : {}",files);
		//log.info("================================================");
		//log.info("files : {}", files[0].getOriginalFilename());
		int result = noticeService.add(noticeVO, files);
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public String getDetail(NoticeVO noticeVO, Model model)throws Exception{
		noticeVO = (NoticeVO)noticeService.getDetail(noticeVO);
		model.addAttribute("boardVO", noticeVO);
		
		return "board/detail";
	}
}

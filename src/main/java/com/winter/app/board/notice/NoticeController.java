package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	//ModelAndView, void, String
	@GetMapping("list")
	public String getList(Pager pager, Model model)throws Exception{
		List<BoardVO> ar = noticeService.getList(pager);
		model.addAttribute("list", ar);
		
		//ERROR, WARN, INFO, DEBUG, TRACE 단계
		log.error("getList 실행");//INFO 단계 이상만 콘솔에 찍힘
		return "board/list";
	}
	
	@GetMapping("add")
	public String add()throws Exception{
		return "board/add";
	}
	
	@PostMapping("add")
	public String add(NoticeVO noticeVO)throws Exception{
		//log.info("NoticeVO : {}", noticeVO);//{}사이에 노티스VO를 넣는다. +(더하기)보다 성능이 좋다.
		int result = noticeService.add(noticeVO);
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public String getDetail(BoardVO boardVO, Model model)throws Exception{
		NoticeVO noticeVO = new NoticeVO();
		noticeVO = (NoticeVO)noticeService.getDetail(noticeVO);
		model.addAttribute("dto", noticeVO);
		
		return "board/detail";
	}
}

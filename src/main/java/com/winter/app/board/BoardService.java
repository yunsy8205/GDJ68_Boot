package com.winter.app.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.Pager;

public interface BoardService {
	//조회수 업데이트는 제외한다.
	//getcount 제외
	
	public List<BoardVO> getList(Pager pager) throws Exception;
	
	public int add(BoardVO boardVO, MultipartFile[] files)throws Exception;
	
	public BoardVO getDetail(BoardVO boardVO)throws Exception;
	
	public int setUpdate(BoardVO boardVO)throws Exception;
	
	public int setDelete(BoardVO boardVO)throws Exception;
	
	public FileVO getFileDetail(FileVO fileVO)throws Exception;	

}

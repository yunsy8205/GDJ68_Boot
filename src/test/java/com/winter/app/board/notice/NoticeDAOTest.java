package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;
import com.winter.app.board.notice.NoticeDAO;
import com.winter.app.commons.Pager;

@SpringBootTest
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	//@Test
	void addTest()throws Exception{
		
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardTitle("title");
			boardVO.setBoardWriter("writer");
			boardVO.setBoardContents("contents");
			int result = noticeDAO.add(boardVO);
			assertEquals(1,result);
	}
	
	@Test
	void getCountTest()throws Exception{
		Pager pager = new Pager();
		pager.setKind("1");//제목검색
		pager.setSearch("20");
		Long count = noticeDAO.getCount(pager);
		assertEquals(2L, count);
	}
	
	//@Test
	void getListTest() throws Exception {
		Pager pager = new Pager();
		pager.setStartRow(0L);
		pager.setLastRow(10L);
		pager.setKind("1");
		pager.setSearch("20");
		
		List<BoardVO> ar = noticeDAO.getList(pager);
		assertEquals(2, ar.size());
	}
	
}

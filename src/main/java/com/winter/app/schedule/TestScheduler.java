package com.winter.app.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winter.app.board.BoardVO;
import com.winter.app.board.notice.NoticeDAO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestScheduler {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	//특정 주기로 반복
	//@Scheduled(fixedDelay = 1000, initialDelayString = "2000")//밀리세컨드 단위, 서버가 시작되고 2초뒤
	public void useFixed()throws Exception{
		//메서드의 실행하고 종료할때까지 4초가 걸린다.
		log.info("============================== Fixed Rate ===============================");
		
	}
	
	//@Scheduled(fixedRateString = "2000", initialDelayString = "1000")//fixedRate=2000와 같다.
	public void useFixedDelay()throws Exception{
		log.info("============================== Fixed Delay ===============================");
		
	}
	
	@Scheduled(cron = "*/10 * * * * *")//50분 일때 마다
	public void useCron()throws Exception{
		log.info("============================== Fixed Delay ===============================");
		Pager pager = new Pager();
		List<BoardVO> ar = noticeDAO.getList(pager);
		log.info("List : {} ", ar);
	}
}

package com.winter.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.winter.app.board.notice.NoticeDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestInterceptor implements HandlerInterceptor{
	
	@Autowired
	private NoticeDAO noticeDAO;// DAO가야할 때
	
	@Override //리턴이 트루면 컨트롤러 진입, false면 어디로 가야할지 지정해 주어야 한다.
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("===== Pre Controller 진입 전 =====");
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("===== Post Controller 나가기 전 =====");
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("===== After 만든 후 =====");
	}

}

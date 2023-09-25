package com.winter.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/** 
 * Member Update 시
 * Password Valid 제외
 **/

@Component
public class MemberValidInterceptor implements HandlerInterceptor{
	//컨트롤러 진입전
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//parameter를 생성해서 추가 하는 메서드가 존재 하지 않는다.
		return true;
		
	}
	
	
}

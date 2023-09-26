package com.winter.app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecuritySuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("============= Authentication : {} ============", authentication);
		
		log.info("============= Pathinfo : {} ============", request.getPathInfo());
		log.info("============= RequestURI : {} ============", request.getRequestURI());
		log.info("============= RequestURL : {} ============", request.getRequestURL());
		
		response.sendRedirect("/");
		
		// 로그인 성공시에 다른 작업을 하고 싶다면 SecuritySuccessHandler을 만들어서 추가해 준다.
		
	}
	
}

package com.winter.app.main;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	
	@GetMapping("/")
	public String getIndex(HttpSession session)throws Exception{
		Enumeration<String> en = session.getAttributeNames();
		
//		while(en.hasMoreElements()) {
//			String name = en.nextElement();
//		}
		
//		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		SecurityContext context = SecurityContextHolder.getContext();
		org.springframework.security.core.Authentication b = context.getAuthentication();
		
		
		log.info("============ GetName : {} =============", b.getName());	
		log.info("============ principle : {} =============", b.getPrincipal());	
		log.info("============ Object : {} =============", b.getAuthorities());	
		
		return "index";
	}
}

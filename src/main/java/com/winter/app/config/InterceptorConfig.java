package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.winter.app.interceptor.TestInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	private TestInterceptor testInterceptor;
	
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;//MessageConfig에서 만든 객체를 넣어줌
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(testInterceptor)
				.addPathPatterns("/notice/list");//여러개를 리스트로 넣는 방법, 쉼표로 넣는 방법 두가지가 있다.
		
		registry.addInterceptor(localeChangeInterceptor)
				.addPathPatterns("/**");
	}
}

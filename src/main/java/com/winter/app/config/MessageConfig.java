package com.winter.app.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer{
	
	//<bean class="">web servlet으로 만듬
	@Bean//라이브러리 객체 수정
	public LocaleResolver localeResolver() {
		
		//두가지 방식이 있음
		//1. session
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);//아무것도 안오면 기본적으로 보여줄 언어
		
		//2. Cookie
		//CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		//cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		//cookieLocaleResolver.setCookieName("lang");//이 키로 구분을 할 수 있다.(어떤 언어를 담을 것인지의 키)
		
		//return cookieLocaleResolver;
		return resolver;
	}
	
	//Message Interceptor 객체 생성
	@Bean
	public LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		
		interceptor.setParamName("lang");
		//parameter를 받아서 언어 구분
		//url?lang=en
		//url?lang=ko
		return interceptor;
	}
	
}

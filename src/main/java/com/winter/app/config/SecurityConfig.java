package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean//api라서 빈으로 만들어준다.
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web
				.ignoring()//무시해라
				//계속 연결하는 형식을 methodchaining이라고 한다.
				.antMatchers("/css/**")//css 경로, **(별두개): 폴더 포함
				.antMatchers("/img/**")
				.antMatchers("/js/**")
				.antMatchers("/vendor/**")
				;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.cors()//외부 서버에서 넘어올 때 에러를 막아줌
			.and()
			.csrf()
			.disable()//실행하지 않겠다.
			.authorizeHttpRequests()
				//.antMatchers("/notice/add").authenticated()//로그인한 사람만
				.antMatchers("/notice/add").hasRole("ADMIN")//ROLE_ADMIN에서 ROLE_제외
				.antMatchers("/manager/*").hasAnyRole("ADMIN", "MANAGER")// 둘 중 하나만 가지고 있으면 가능!
				.antMatchers("/").permitAll()//모든 걸 허용하겠다는 의미, 맨마지막에 위치하는게 좋음
				.and()
			//form 관련 설정/ 우리가 만든 로그인창 사용
			.formLogin()
				.loginPage("/member/login")//내장된 로그인폼을 사용하지 안고, 개발자가 만든 폼 사용 -> 로그인처리 주소
				.permitAll()//누구나 허용 (여기다 해줘도 괜찮고, .authorizeHttpRequests()에 해줘도 됨)
				;
			
		return httpSecurity.build();
	}
	
}

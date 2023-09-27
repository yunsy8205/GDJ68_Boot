package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityPasswordEncoder {
	
	@Bean// 패스워드를 암호화해주는 객체
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

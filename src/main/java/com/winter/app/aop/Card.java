package com.winter.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class Card {
	
	//advice
	@Around("execution(* com.winter.app.aop.Transfer.*())")
	public Object cardCheck(ProceedingJoinPoint joinPoint)throws Throwable{
		//ProceedingJoinPoint는 point cut을 객체로 만든 것 (메서드를 객체화 시킴)
		log.info("================================================");
		log.info("Card Check 타기");
		
		Object [] args=joinPoint.getArgs();//매개변수가 나옴
		log.info("Args : {}", args);
		
		Object obj=joinPoint.proceed(); //이쪽으로 가지고 와서 실행하라는 의미 (exception보다 더 상위 객체)
		
		log.info("Card Check 내리기");
		log.info("================================================");
	
		return obj;
	}
	
}

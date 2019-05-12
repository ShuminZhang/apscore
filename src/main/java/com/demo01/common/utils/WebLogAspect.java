package com.demo01.common.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;


@Aspect
@Component
@Slf4j
public class WebLogAspect {

	@Pointcut("execution(public * com.apscore..*.*(..))")
	public void webLog() {
		
	}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable{
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		log.info("URL : " + request.getRequestURL().toString());
		log.info("HTTP_METHOD : " + request.getMethod());
		log.info("IP : " + request.getRemoteAddr());
		
		Enumeration<String> enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			log.info("name:{},value:{}",name,request.getParameter(name));
		}
	}
	
	
	@AfterReturning(returning="ret",pointcut="webLog()")
	public void doAfterReturing(Object ret) throws Throwable{
		log.info("RESPONSE : " + ret);
	}
}


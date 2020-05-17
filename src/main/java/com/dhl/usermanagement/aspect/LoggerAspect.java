package com.dhl.usermanagement.aspect;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggerAspect {

	Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
	
	
	@Before("controllerBean()")
	public void logRequest(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		logger.info("*****************Request Begin*****************");
		logger.info("Entering in Method        :{}  " + joinPoint.getSignature().getName());
		logger.info("Class Name                :{}  " + joinPoint.getSignature().getDeclaringTypeName());
		logger.info("Request Body              :{}  " + Arrays.toString(joinPoint.getArgs()));

		if (null != request) {
			logger.info("URI           : {}"+request.getRequestURI());
			logger.debug("Method       : {}", request.getMethod());
			logger.info("Request Body  : {}"+ Arrays.toString(joinPoint.getArgs()));

			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				String headerValue = request.getHeader(headerName);
				logger.debug("Headers Name     : {}: " + headerName + "Headers Value    : {}" + headerValue);
			}
		}
		logger.info("*****************request End*****************");
	}
	
	@AfterReturning(pointcut = "controllerBean() && args(..,response)", returning = "result")
	public void logReqestResponse(JoinPoint joinPoint, Object result,HttpServletResponse response) throws Throwable {
		   logger.info("*****************Response begin*****************");
		   logger.info("Response code     : {}: "+response.getStatus());
		   logger.info("Response body     : {}"+ result);
		   logger.info("Content type  : {}", response.getContentType());
		   logger.info("*****************Response End*****************");
	}
	
	@AfterThrowing(pointcut = "controllerBean()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
		logger.error("Cause : " + exception.getCause());
	}
	
	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerBean() {}
}

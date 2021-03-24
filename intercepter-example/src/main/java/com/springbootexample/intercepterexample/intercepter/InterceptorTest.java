package com.springbootexample.intercepterexample.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class InterceptorTest implements AsyncHandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) throws Exception {
		log.info("preHandle...");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		log.info("postHandle...");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
		Object handler, Exception ex) throws Exception {
		log.info("afterCompletion...");
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
		log.info("afterConcurrentHandlingStarted...");
	}
}

package com.springbootexample.intercepterexample.config;

import com.springbootexample.intercepterexample.intercepter.InterceptorTest;
import com.springbootexample.intercepterexample.intercepter.InterceptorTest2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final InterceptorTest interceptorTest;
	private final InterceptorTest2 interceptorTest2;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorTest)
			.addPathPatterns("/example/**")
			.excludePathPatterns("/example/hello2");

		registry.addInterceptor(interceptorTest2)
			.addPathPatterns("/example/**")
			.excludePathPatterns("/example/hello2");
	}
}

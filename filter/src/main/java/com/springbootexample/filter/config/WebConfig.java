package com.springbootexample.filter.config;


import com.springbootexample.filter.filter.FilterRegistrationBeanTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean testFilter(FilterRegistrationBeanTest filterRegistrationBeanTest) {

		FilterRegistrationBean filterReg = new FilterRegistrationBean();
		filterReg.setFilter(filterRegistrationBeanTest);

		filterReg.addUrlPatterns("/example/filterRegistrationBean");
		return filterReg;
	}
}

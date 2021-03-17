package com.springbootexample.filterexample.config;


import com.springbootexample.filterexample.filter.FilterRegistrationBeanTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean testFilter(FilterRegistrationBeanTest filterRegistrationBeanTest) {
		FilterRegistrationBean filterReg = new FilterRegistrationBean();
		filterReg.setFilter(filterRegistrationBeanTest);

		// List<String> urlPatternList = Arrays.asList("/test/*", "/api/*");
		// filterRegistrationBean.setUrlPatterns(urlPatternList);

		filterReg.addUrlPatterns("/example/filterRegistrationBean");
		return filterReg;
	}
}

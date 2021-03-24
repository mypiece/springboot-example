package com.springbootexample.filter.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
public class FilterController {

	@GetMapping("/hello")
	public String hello(){
		return "Hello Example!!!";
	}

	@GetMapping("/webfilter")
	public String webFilterTest(){
		return "webfiltertest!!!";
	}

	@GetMapping("/filterRegistrationBean")
	public String filterRegistrationBeanTest(){
		return "filterRegistrationBeanTest!!!";
	}

}

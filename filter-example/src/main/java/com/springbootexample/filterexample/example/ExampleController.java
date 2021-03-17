package com.springbootexample.filterexample.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
public class ExampleController {

	@GetMapping("/hello")
	public String hello(){
		return "Hello Exaample!!!";
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

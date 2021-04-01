package com.springbootexample.springsecurityjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ExController {

	@GetMapping("/hello")
	public String hello(){
		return "Hello Spring Security!!";
	}

}

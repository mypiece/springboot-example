package com.springbootexample.intercepter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
public class IntercepterController {

	@GetMapping("/hello")
	public String hello(){
		return "Hello Example!!!";
	}

	@GetMapping("/hello2")
	public String hello2(){
		return "Hello Example2!!! ";
	}

}

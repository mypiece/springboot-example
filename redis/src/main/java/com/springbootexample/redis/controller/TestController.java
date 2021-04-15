package com.springbootexample.redis.controller;

import com.springbootexample.redis.service.TestService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class TestController {

	private final TestService testService;
	private final StringRedisTemplate redisTemplate;

	@GetMapping("hello")
	public String hello(){

		return Optional.ofNullable(testService.test("hello")).orElse("aaaa");
	}

	@GetMapping("hello2")
	public String hello2(){

		ValueOperations values = redisTemplate.opsForValue();


		log.info(values.get("test"));

		return "aaa";
	}

}


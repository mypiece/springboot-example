package com.springbootexample.redis.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class TestVO {

	private String name;
	private int age;

	@Builder
	public TestVO(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

package com.springbootexample.springsecurityjwt.security.utils;

import static org.assertj.core.api.BDDAssertions.then;

import com.springbootexample.springsecurityjwt.model.entity.USER_INFO.UserInfo.UserRole;
import com.springbootexample.springsecurityjwt.security.model.dto.UserDTO;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Log4j2
public class JWTUtilTest {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	public void generateTokenTest() {

		//given
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority(UserRole.USER.name()));

		UserDTO userDTO = new UserDTO(
			"test",
			"password",
			"test@test.com",
			roles
		);

		//when
		String jwt = JWTUtil.generateToken(userDTO);

		//then
		then(jwt).isNotBlank();
	}

	@Test
	public void isValidTokenTest() {

		//given
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority(UserRole.USER.name()));

		UserDTO userDTO = new UserDTO(
			"test",
			"password",
			"test@test.com",
			roles
		);

		//when
		String jwt = JWTUtil.generateToken(userDTO);
		boolean isValidToken = JWTUtil.isValidToken(jwt);

		//then
		then(isValidToken).isTrue();

	}
}

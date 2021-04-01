package com.springbootexample.springsecurityjwt.security.model.dto;

import com.springbootexample.springsecurityjwt.model.entity.USER_INFO.UserInfo;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@ToString
@EqualsAndHashCode(callSuper = true)
@Getter
public class UserDTO extends User {

	private String email;

	public UserDTO(String username, String password, String email,
		Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.email = email;
	}

	public static UserDTO of(UserInfo userInfo){

		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority(userInfo.getUserRole().name()));

		return new UserDTO(
			userInfo.getUserId(),
			userInfo.getPassword(),
			userInfo.getEmail(),
			roles
		);

	}
}

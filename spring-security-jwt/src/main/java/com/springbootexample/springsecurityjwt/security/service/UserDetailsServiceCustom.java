package com.springbootexample.springsecurityjwt.security.service;

import com.springbootexample.springsecurityjwt.dao.ExDAO;
import com.springbootexample.springsecurityjwt.model.entity.USER_INFO.UserInfo;
import com.springbootexample.springsecurityjwt.security.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceCustom implements UserDetailsService {

	private final ExDAO exDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserInfo userInfo = exDAO.getUserInfo(username)
			.orElseThrow(() -> new UsernameNotFoundException(username + "is not found"));

		return (UserDetails) UserDTO.of(userInfo);
	}
}

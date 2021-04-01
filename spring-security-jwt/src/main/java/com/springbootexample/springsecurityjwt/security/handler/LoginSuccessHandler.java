package com.springbootexample.springsecurityjwt.security.handler;

import com.springbootexample.springsecurityjwt.security.model.dto.UserDTO;
import com.springbootexample.springsecurityjwt.security.utils.JWTUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) {

		String jwt = JWTUtil.generateToken((UserDTO) authentication.getPrincipal());

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json;charset=utf-8");

		JSONObject json = new JSONObject();
		PrintWriter out;

		try {
			json.put("jwt", jwt);
			out = response.getWriter();
			out.print(json);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}
}

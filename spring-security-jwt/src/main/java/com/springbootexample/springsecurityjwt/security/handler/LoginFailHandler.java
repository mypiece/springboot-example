package com.springbootexample.springsecurityjwt.security.handler;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Log4j2
public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json;charset=utf-8");

		JSONObject json = new JSONObject();
		PrintWriter out;

		try {
			json.put("errMsg", exception.getMessage());
			out = response.getWriter();
			out.print(json);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}

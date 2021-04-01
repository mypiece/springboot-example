package com.springbootexample.springsecurityjwt.security.filter;

import com.springbootexample.springsecurityjwt.security.utils.JWTUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Log4j2
public class TokenValidFilter extends OncePerRequestFilter {

	public static final String AUTH_HEADER = "Authorization";
	public static final String AUTH_TYPE = "Bearer ";


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws IOException, ServletException {

		String authHeader = request.getHeader(AUTH_HEADER);
		boolean isValid = false;
		String errMsg = "Authorization Header does not exist.";

		if(StringUtils.hasText(authHeader) && authHeader.startsWith(AUTH_TYPE)){
			try{
				isValid = JWTUtil.isValidToken(authHeader.substring(AUTH_TYPE.length()));
			}catch(Exception e){
				errMsg = e.getMessage();
			}
		}

		if(isValid){
			filterChain.doFilter(request, response);
		}else{
			invalidTokenProc(response, errMsg);
		}
	}

	private void invalidTokenProc(HttpServletResponse response, String errMsg) {

		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json;charset=UTF-8");

		JSONObject json = new JSONObject();
		PrintWriter out;

		try {
			json.put("errMsg", errMsg);
			out = response.getWriter();
			out.print(json);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

}

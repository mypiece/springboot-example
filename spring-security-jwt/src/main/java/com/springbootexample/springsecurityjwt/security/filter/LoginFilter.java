package com.springbootexample.springsecurityjwt.security.filter;

import com.springbootexample.springsecurityjwt.common.utils.ObjectMapperUtils;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

@Log4j2
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	public LoginFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
		HttpServletResponse response) throws IOException {

		Map<String, Object> loginInfo = ObjectMapperUtils.deserialization(request.getInputStream(), Map.class);

		String id = (String)loginInfo.get("id");
		String pw = (String)loginInfo.get("pw");

		UsernamePasswordAuthenticationToken authToken =
			new UsernamePasswordAuthenticationToken(id, pw);

		return getAuthenticationManager().authenticate(authToken);
	}

}

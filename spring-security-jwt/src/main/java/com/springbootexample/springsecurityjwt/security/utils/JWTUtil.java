package com.springbootexample.springsecurityjwt.security.utils;

import com.springbootexample.springsecurityjwt.security.model.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;

@Log4j2
public class JWTUtil {

	private static final String secretKey = "test";
	private static final long expire = 60 * 30 * 1000L; //30분

	public static String generateToken(UserDTO userDTO) {

		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT");
		header.put("alg", SignatureAlgorithm.HS256.getValue());

		Object[] roles = userDTO.getAuthorities().stream()
			.map(authority -> authority.getAuthority())
			.toArray();
		Map<String, Object> additionalInfo = new HashMap<>();
		additionalInfo.put("role", roles);

		return Jwts.builder()
			.setHeader(header)
			.setIssuedAt(new Date())
			.setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
			.setClaims(additionalInfo)
			.setSubject(userDTO.getUsername()) //반드시 setClaims 이후에 정의해야 한다.
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	public static boolean isValidToken(String tokenStr) {

		String username = Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(tokenStr)
			.getBody()
			.getSubject();

		return StringUtils.hasLength(username);

	}

}

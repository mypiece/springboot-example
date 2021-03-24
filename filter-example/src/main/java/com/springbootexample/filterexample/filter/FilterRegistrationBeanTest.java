package com.springbootexample.filterexample.filter;


import com.springbootexample.filterexample.example.FilterService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class FilterRegistrationBeanTest extends OncePerRequestFilter {

	private final FilterService filterService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		log.info("FilterRegistrationBeanTest!!!!!!!!");
		log.info(filterService.serviceTest());

		filterChain.doFilter(request, response);
	}
}

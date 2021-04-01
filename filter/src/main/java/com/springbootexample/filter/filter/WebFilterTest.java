package com.springbootexample.filter.filter;


import com.springbootexample.filter.controller.FilterService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;

@WebFilter({"/example/webfilter"})
@Log4j2
@RequiredArgsConstructor
public class WebFilterTest extends OncePerRequestFilter {

	private final FilterService filterService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		log.info("WebFilterTest!!!!!!!!");
		log.info(filterService.serviceTest());

		filterChain.doFilter(request, response);

	}
}

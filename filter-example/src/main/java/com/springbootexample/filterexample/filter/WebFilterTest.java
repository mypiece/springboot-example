package com.springbootexample.filterexample.filter;


import com.springbootexample.filterexample.example.ExampleService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

@WebFilter({"/example/webfilter"})
@Slf4j
@RequiredArgsConstructor
public class WebFilterTest extends OncePerRequestFilter {

	private final ExampleService exampleService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		log.info("WebFilterTest!!!!!!!!");
		log.info(exampleService.serviceTest());

		filterChain.doFilter(request, response);

	}
}

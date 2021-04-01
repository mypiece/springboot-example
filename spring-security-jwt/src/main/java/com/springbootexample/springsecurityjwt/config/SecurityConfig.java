package com.springbootexample.springsecurityjwt.config;

import com.springbootexample.springsecurityjwt.security.filter.LoginFilter;
import com.springbootexample.springsecurityjwt.security.filter.TokenValidFilter;
import com.springbootexample.springsecurityjwt.security.handler.LoginFailHandler;
import com.springbootexample.springsecurityjwt.security.handler.LoginSuccessHandler;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
@EnableWebSecurity 이게 무슨 역할읋 하는걸까?
책에서는 WebSecurityConfigurerAdapter 만 상속받는데
이것도 필요한건가?
스프링 시큐리티를 사용하기 위한 어노테이션이라고 하는데..
책에서는 이거 없이도 잘 사용하고 있는데...
일단 함께 정의해보고...마지막에 빼보자...문제 없는지...
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
			.csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증할것이므로 세션필요없으므로 생성안함.
			.and()
			.authorizeRequests() // 요청에 대한 사용권한 체크
//			.antMatchers("/admin/**").hasRole("ADMIN")
//			.antMatchers("/user/**").hasRole("USER")
			.anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능
//			.anyRequest().fullyAuthenticated()
//			.anyRequest().hasRole("USER")
			.and()
			.addFilterBefore(loginFilter(),
				UsernamePasswordAuthenticationFilter.class);

		/*
		인증된 유저만 접근이 가능해야 하냐? 아니지 않아?
		왜냐면 시큐리티에서 인식하는 인증은 로그인 하나이기 때문
		그런데 세션을 사용하지 않기 때문에 그 인증이 유지되지 않는다.
		다음 요청을 해도 여전히 접근이 안될거임.
		우리는 접근 자체를 jwt로 진행할거다.
		이에 사용권한 체크를 여기서 하면 안된다.
		권한 체크는 jwt 필터에서 진행한다.

		로그인 페이지를 제외하고 모두 토큰 유효성 검증을 진행한다.

		 */

	}

	public LoginFilter loginFilter() throws Exception{

		LoginFilter loginFilter =  new LoginFilter("/login");
		loginFilter.setAuthenticationManager(authenticationManager());
		loginFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
		loginFilter.setAuthenticationFailureHandler(new LoginFailHandler());

		return loginFilter;
	}


	@Bean
	public FilterRegistrationBean tokenValidFilterReg(TokenValidFilter tokenValidFilter) {

		FilterRegistrationBean filterReg = new FilterRegistrationBean();
		filterReg.setFilter(tokenValidFilter);
		filterReg.addUrlPatterns("/api/*");

		return filterReg;
	}

}

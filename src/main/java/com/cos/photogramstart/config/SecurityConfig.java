package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당파일로 시큐리티를 활성화
@Configuration // IOC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http);
		http.csrf().disable(); // csrf 비활성화
		http.authorizeRequests().antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**")
				.authenticated() // 인증이 필요해
				.anyRequest().permitAll().and().formLogin().loginPage("/auth/signin") // GET방식
				.loginProcessingUrl("/auth/signin") // POST방식 -> 스프링 시큐리티가 로그인 프로세스를 진행한다.
				.defaultSuccessUrl("/");
	}
}

package com.meruichi.yoyang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean // IoC가 됨(return값을 spring이 관리, 필요할때마다 쓰면 됨)
	public BCryptPasswordEncoder encodePWD() { // 암호화 해주는 메소드
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	return authenticationConfiguration.getAuthenticationManager();
	}

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/","/auth/**","/js/**","/css/**","/image/**").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당주소에서의 로그인을 가로챔
			.defaultSuccessUrl("/") // 로그인 성공시 url
			// .failureUrl("fail") // 실패시
			;			
		return http.build();
	}
}

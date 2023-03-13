package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// @configuration : Bean 등록 (스프링 컨테이너에서 객체를 관리할 수 있게 하는것)

@Configuration
@EnableWebSecurity // Security 필터가 등록이됨(여기서 설정 가능)
@EnableGlobalMethodSecurity(prePostEnabled = true) // prePostEnabled : 특정주소로 접근시 권한 및 인증을 미리체크
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean // IoC가 됨(return값을 spring이 관리, 필요할때마다 쓰면 됨)
	public BCryptPasswordEncoder encodePWD() { // 암호화 해주는 메소드
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신로그인(패스워드가로치기) 하는데, 해당패스워드가 뭘로 hash가 되었는지 알아야
	// 같은 hash로 암호화되어있는DB의 hash와 비교가능함
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable() // csrf 토큰 비활성화(테스트시)
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당주소에서의 로그인을 가로챔
				.defaultSuccessUrl("/") // 로그인 성공시 url
				// .failureUrl("fail") // 실패시
				;
		
	}

}

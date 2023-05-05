package com.meruichi.yoyang.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.meruichi.yoyang.model.User;

import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails {

	private User user; // 객체를 직접 들고오는것 : composition(컴푀지션)

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정만료를 확인
		if (user.isUserWithdrawn()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정잠김을 확인
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 비밀번호 만료를 확인
		return true;
	}

	@Override
	public boolean isEnabled() { // 계정활성화 확인
		return true;
	}

	// 계정의 권한(role)을 설정
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors = new ArrayList<>();
		/*
		 * collectors.add(new GrantedAuthority() {
		 * 
		 * @Override public String getAuthority() { return "ROLE_" + user.getRole(); //
		 * 스프링의 규칙 : ROLE_을 붙여야함 } });
		 */

		collectors.add(() -> {
			return "ROLE_" + user.getRole();
		});

		return collectors;
	}
}

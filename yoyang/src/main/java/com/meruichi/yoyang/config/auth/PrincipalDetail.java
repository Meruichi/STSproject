package com.meruichi.yoyang.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.meruichi.yoyang.model.User;

import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails {

	private User user; // ��ü�� ���� �����°� : composition(��ǣ����)

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
	public boolean isAccountNonExpired() { // �������Ḧ Ȯ��
		if (user.isUserWithdrawn()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // ��������� Ȯ��
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // ��й�ȣ ���Ḧ Ȯ��
		return true;
	}

	@Override
	public boolean isEnabled() { // ����Ȱ��ȭ Ȯ��
		return true;
	}

	// ������ ����(role)�� ����
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors = new ArrayList<>();
		/*
		 * collectors.add(new GrantedAuthority() {
		 * 
		 * @Override public String getAuthority() { return "ROLE_" + user.getRole(); //
		 * �������� ��Ģ : ROLE_�� �ٿ����� } });
		 */

		collectors.add(() -> {
			return "ROLE_" + user.getRole();
		});

		return collectors;
	}
}

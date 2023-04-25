package com.meruichi.yoyang.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meruichi.yoyang.model.User;
import com.meruichi.yoyang.repository.UserRepository;

@Service // Bean���
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	// username�� DB�� �ִ����� Ȯ��(password�� �˾Ƽ� ��)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("�ش� ����ڸ� ã�� �� �����ϴ�(" + username + ")" );
				});
		return new PrincipalDetail(principal); // ��ť��Ƽ���ǿ� �������� ����
	}

}

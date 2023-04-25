package com.meruichi.yoyang.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meruichi.yoyang.model.User;
import com.meruichi.yoyang.repository.UserRepository;

@Service // Bean등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	// username이 DB에 있는지만 확인(password는 알아서 함)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다(" + username + ")" );
				});
		return new PrincipalDetail(principal); // 시큐리티세션에 유저정보 저장
	}

}

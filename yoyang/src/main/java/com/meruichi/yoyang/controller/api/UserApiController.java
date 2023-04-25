package com.meruichi.yoyang.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meruichi.yoyang.config.auth.PrincipalDetail;
import com.meruichi.yoyang.dto.ResponseDto;
import com.meruichi.yoyang.model.User;
import com.meruichi.yoyang.service.UserService;


@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
		
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : saveȣ���");
		userService.ȸ������(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // javaObject�� json���� ��ȯ�ؼ�����
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.ȸ������(user);
		// ���� ���� �� ���� session �ٲ㼭 �ڵ��α���
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);	
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@PutMapping("/user/connectForm/{id}")
	public ResponseDto<Integer> connect(@AuthenticationPrincipal PrincipalDetail principal, @PathVariable int id){
		userService.ȸ����������(principal.getUser(), id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@PutMapping("/user/managementForm/{id}")
	public ResponseDto<Integer> roleUpdate(@PathVariable int id){
		userService.���Ѻ���(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
}

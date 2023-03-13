package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor  // 생성자
@NoArgsConstructor // 빈 생성자
@Data  //@Getter,@Setter와 같음
@Getter //java버전이 낮아서 적용안되나?
@Setter //java버전이 낮아서 적용안되나?
public class Member {
	
	// private를 쓰는이유
	// private를 쓰지않으면 변수에 접근해서 값 수정가능 => 객체지향에 맞지않음
	// 함수를 이용해 객체에 접근해서 값을 변화시킴 => 객체지향언어
	
	private int id;
	private String username;
	private String password;
	private String email;
	
	
	

	

	
}

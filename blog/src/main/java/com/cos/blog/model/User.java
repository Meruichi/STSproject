package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java(다른언어들 포함) 의 Object를 테이블로 매핑해주는 기술

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity   // User클래스가 MySQL에 테이블생성이 됨.
// @DynamicInsert // insert시 null인 필드를 제외시켜줌
public class User {

	@Id // P키
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다 
	private int id; // auto_increment, oracle의경우 sequence. 사용자가 많아지면 단위 long으로?
	
	// 아이디 최대 100자(카카오 로그인이용자는 아이디가 길어질 예정)
	@Column(nullable = false, length = 100, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 100자로 하는이유 : 비밀번호를 해쉬로 암호화할거기때문
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	  
	// DB는 RoleType이라는게 없으니 해당Enum은 String임을 알려줘야함
	@Enumerated(EnumType.STRING)
	private RoleType role; //관리자, 매니저, 일반유저 등등. Enum을 쓰는게 좋다. 
	// Enum : 도메인설정(String중에서 몇개만 가능하도록 하는 전략)
	
	// 사용자가 카카오로그인유저인지 일반유저인지 확인
	private String oauth; // 일반 : null, 카카오 : "kakao"
	
	
	@CreationTimestamp // 시간이 자동입력됨
	private Timestamp createDate; // updateDate : 회원정보 수정시 날짜


	
	
}

package com.meruichi.yoyang.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private int userNumber; // auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; // ���̵�
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 20)
	private String userRealname; // �̸�
	
	@Column(nullable = false, length = 50)
	private String userEmail;
	  
	@Column(nullable = false, length = 20)
	private String userPhoneNumber;
	
	@ManyToOne 
	@JoinColumn(name="branch")
	private Branch branch;
	
	@Enumerated(EnumType.STRING)
	private UserRoleType role; //������, �Ŵ���, �Ϲ�����
	
	@CreationTimestamp 
	private Timestamp createDate; 
	
	@Column(nullable = false, columnDefinition="boolean default false")
	private boolean userWithdrawn; // ȸ�� Ż�𿩺�

	
	
}

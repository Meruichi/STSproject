package com.meruichi.yoyang.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
public class Board { // 공지사항

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	//@Lob 인식안됨 왜?
	@Column(columnDefinition = "LONGTEXT")
	private String content; // 섬머노트(라이브러리. <html>태그가 섞여서 디자인이됨.)를 씀
	
	@ManyToOne(fetch = FetchType.EAGER) // 하나의 유저(즉 admin)은 여러개의 글을 쓸 수 있다.
	@JoinColumn(name="user_id")
	private User user; 

	@CreationTimestamp
	private Timestamp createDate;
	
}

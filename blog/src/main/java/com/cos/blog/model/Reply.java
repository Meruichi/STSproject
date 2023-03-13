package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.dto.ReplySaveRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 200)
	private String content;

	@ManyToOne // 연관관계 하나의 개시글에 여러개의 답변이 있을 수 있다.
	@JoinColumn(name = "boardId") // userId = 테이블명
	private Board board;

	@ManyToOne // 연관관계 한명의 유저는 여러개의 답글을 달수있다
	@JoinColumn(name = "userId") // userId = 테이블명
	private User user;

	@CreationTimestamp
	private Timestamp createDate;

	/*
	 * public void replyBuilder(User user, Board board, String content) {
	 * setUser(user); setBoard(board); setContent(content); }
	 */

}

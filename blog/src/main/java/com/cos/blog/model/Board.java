package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터 longtext라는단위
	private String content; // 섬머노트(라이브러리. <html>태그가 섞여서 디자인이됨.)를 씀
	
	@ColumnDefault("0") // int라서 홑따옴표 없음
	private int count; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // 연관관계  주체인 board가 Many, user가 one. 한명의 유저는 여러개의 게시글을 쓸 수 있다.
	@JoinColumn(name="userId") // userId = 테이블명
	private User user; // DB는 오브젝트를 저장할수없다. F키(외래키),자바는 오브젝트를 저장할 수 있다.
	
	// JoinColumn은 필요없음
	// CascadeType.REMOVE : 게시글을 지우면 그 게시글에 달린 댓글들은 다 지우겠다.
	@JsonIgnoreProperties({"board"}) // 무한참조 방지. 이걸로 board로 reply를 뽑을때 board를 참조하지않음
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)  // mappedBy => 연관관계의 주인이아니다(FK가 아니다), DB에 컬럼 없음
	@OrderBy("id desc")
	private List<Reply> replys;

	@CreationTimestamp
	private Timestamp createDate;
	
}

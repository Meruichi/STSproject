package com.cos.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
	// public 안에서는 public 생략가능
	// createDate도 자동으로들어가서 생략가능
	// nativeQuery : 영속화 시켜서 할 필요 없음
	@Modifying
	@Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
	int mSave(int userId, int boardId, String content); // 업데이트 된 행의 개수를 리턴하기떄문에 int
	// 한건만 등록하므로 1이 리턴

}

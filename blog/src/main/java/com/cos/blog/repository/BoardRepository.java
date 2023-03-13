package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	@Modifying
	@Query(value = "update Board set count = count + 1 where id = ?1", nativeQuery = true)
	int updateCount(int boardId);
}

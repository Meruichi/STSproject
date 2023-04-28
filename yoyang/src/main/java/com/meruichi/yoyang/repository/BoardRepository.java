package com.meruichi.yoyang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meruichi.yoyang.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}

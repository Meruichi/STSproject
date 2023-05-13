package com.meruichi.yoyang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.meruichi.yoyang.model.Board;
import com.meruichi.yoyang.model.User;
import com.meruichi.yoyang.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void �۾���(Board board, User user) {
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> �۸��(Pageable pageable) {

		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board �ۻ󼼺���(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("�� �󼼺��� ����(���̵� ã�� �� ����)");
		});
	}

	@Transactional
	public void �ۻ����ϱ�(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void �ۼ����ϱ�(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("�� ã�� ����(���̵� ã�� �� ����)");
		});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
	}

}

package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

@Service
public class BoardService {

	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {

		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패(아이디를 찾을 수 없음)");
			
		});

	}

	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패(아이디를 찾을 수 없음)");
		});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
	}

	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {

		/* 영속화
		 * User user =
		 * userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
		 * return new IllegalArgumentException("댓글 찾기 실패(유저 아이디를 찾을 수 없음)"); }); Board
		 * board =
		 * boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() ->
		 * { return new IllegalArgumentException("댓글 찾기 실패(게시글 아이디를 찾을 수 없음)"); });
		 * 
		 * Reply reply = Reply.builder().user(user).board(board).content(replySaveRequestDto.getContent()).build();
		 */

		

		// 위의 방식이 귀찮다면 model reply에서 함수하나 만들면됨
		// Reply reply = new Reply();
		// reply.replyBuilder(user, board, replySaveRequestDto.getContent());

		replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
	}

	@Transactional
	public void 댓글삭제하기(int replyId) {
		replyRepository.deleteById(replyId);
	}
	
	@Transactional
	public int 조회수증가(int id) {
		return this.boardRepository.updateCount(id);
	}
}
package com.meruichi.yoyang.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meruichi.yoyang.config.auth.PrincipalDetail;
import com.meruichi.yoyang.dto.ResponseDto;
import com.meruichi.yoyang.model.Board;
import com.meruichi.yoyang.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		
		boardService.�۾���(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.�ۻ����ϱ�(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		
		/*
		 * System.out.println("BoardApiController : update id" + id);
		 * System.out.println("BoardApiController : update boardtitle" +
		 * board.getTitle());
		 * System.out.println("BoardApiController : update boardcontent" +
		 * board.getContent());
		 */
		 
		boardService.�ۼ����ϱ�(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
}
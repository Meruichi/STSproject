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
import com.meruichi.yoyang.model.Branch;
import com.meruichi.yoyang.model.Item;
import com.meruichi.yoyang.model.ItemReport;
import com.meruichi.yoyang.service.ItemService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ItemApiController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/api/addItem")
	public ResponseDto<Integer> addItem(@RequestBody Item item, @AuthenticationPrincipal PrincipalDetail principal) {
		
		itemService.아이템등록(item, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@PutMapping("/api/updateItem/{id}")
	public ResponseDto<Integer> updateItem(@PathVariable int id, @RequestBody ItemReport itemReport) {
		
		itemService.아이템수정(id, itemReport);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@DeleteMapping("/api/deleteItem/{id}")
	public ResponseDto<Integer> deleteItem(@PathVariable int id) {
		
		itemService.아이템삭제(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}

	
}

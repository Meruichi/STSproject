package com.meruichi.yoyang.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meruichi.yoyang.config.auth.PrincipalDetail;
import com.meruichi.yoyang.dto.ResponseDto;
import com.meruichi.yoyang.model.Branch;
import com.meruichi.yoyang.service.BranchService;

@RestController
public class BranchApiController {
	
	@Autowired
	private BranchService branchService;
	
	@PostMapping("/api/branch")
	public ResponseDto<Integer> add(@RequestBody Branch branch, @AuthenticationPrincipal PrincipalDetail principal) {
		
		branchService.지점등록(branch);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
}

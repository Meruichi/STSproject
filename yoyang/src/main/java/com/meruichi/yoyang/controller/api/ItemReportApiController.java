package com.meruichi.yoyang.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.meruichi.yoyang.config.auth.PrincipalDetail;
import com.meruichi.yoyang.dto.ResponseDto;
import com.meruichi.yoyang.model.ItemReport;
import com.meruichi.yoyang.service.ItemReportService;

@RestController
public class ItemReportApiController {

	@Autowired
	private ItemReportService itemReportService;

	@PostMapping("/api/addReport/{id}")
	public ResponseDto<Integer> addReport(@PathVariable int id, @RequestBody ItemReport itemReport,
			@AuthenticationPrincipal PrincipalDetail principal) {
		itemReportService.아이템보고(id, itemReport, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/deleteItemReport/{id}")
	public ResponseDto<Integer> deleteItemReport(@PathVariable int id) {
		itemReportService.아이템보고삭제(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}

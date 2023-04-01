package com.meruichi.yoyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meruichi.yoyang.service.BranchService;


@Controller
public class BranchController {
	
	@Autowired
	private BranchService branchService;

	@GetMapping("/branch/addBranchForm")
	public String addBranchForm() {
		return "branch/addBranchForm";
	}
	
	@RequestMapping("/branch/branchManagementForm")
	public void branchManagementForm(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("branches", branchService.지점목록(pageable));
	}

}

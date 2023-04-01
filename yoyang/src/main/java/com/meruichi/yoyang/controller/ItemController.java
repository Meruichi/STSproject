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
import com.meruichi.yoyang.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	public ItemService itemService;
	
	@Autowired
	public BranchService branchService;
	
	@RequestMapping("/item/addItemForm")
	public String addItemForm(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("branches", branchService.지점목록(pageable));
		return "item/addItemForm";
	}
	
	@RequestMapping("/item/itemManagementForm")
	public void itemManagementForm(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("items", itemService.아이템목록(pageable));
	}

}

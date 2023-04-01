package com.meruichi.yoyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meruichi.yoyang.service.ItemReportService;
import com.meruichi.yoyang.service.ItemService;

@Controller
public class ItemReportController {
	
	@Autowired
	public ItemService itemService;
	
	@Autowired
	public ItemReportService itemReportService;
	
	@RequestMapping("/item/itemReportForm")
	public void itemReportForm(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("items", itemService.아이템목록(pageable));
	}
	
	@RequestMapping("/item/itemReportManagementForm")
	public void itemReportManagementForm(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("items", itemService.아이템목록(pageable));
		model.addAttribute("itemReports", itemReportService.아이템보고목록(pageable));
	}
	
}

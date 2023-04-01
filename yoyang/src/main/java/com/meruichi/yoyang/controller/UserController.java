package com.meruichi.yoyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meruichi.yoyang.service.BranchService;
import com.meruichi.yoyang.service.UserService;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BranchService branchService;
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
    @GetMapping("/username")
    public String getUserName(@AuthenticationPrincipal UserDetails UserDetails) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
    
	@RequestMapping("/user/managementForm")
	public void managementForm(Model model,
			@PageableDefault(size = 10, sort = "userNumber", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("users", userService.유저목록(pageable));
	}
	
	@RequestMapping("/user/connectForm")
	public void connectForm(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("branches", branchService.지점목록(pageable));
	}

}

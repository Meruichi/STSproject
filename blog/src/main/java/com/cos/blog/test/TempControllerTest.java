package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	// tempHome, tempImg의 경우 application.yml에서 spring의 mvc를 주석처리하고 사용
	//   http://localhost:9090/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		
		System.out.println("tempHome()");
		// 파일리턴 기본경로 : src/main/resources/static
		// 리턴명 : /home.html
		// 풀경로 : src/main/resources/static/home.html
		return "/home.html";   //  정적인 파일
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		
		return "/1121.png";   // 정적인 파일
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix : /WEB-INF/views/
		// suffix : .jsp
		// 풀네임 : return "/test.jsp";의 경우 /WEB-INF/view//test.jsp.jsp
		// 그러므로 return "test"; (return "/test";도 spring이 처리해주는듯)
		return "test";   // 동적인 파일(컴파일이 일어나야 하는 파일인데 static에선 인식못함)
	}						  // src/main

}

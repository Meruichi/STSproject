package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //사용자가 요청하면 데이터를 응답해줌
// HTML파일을 응답해주는 컨트롤러는 @Controller
public class HttpControllerTest {

	//  http://localhost:8080/http/get  //(select)
	@GetMapping("/http/get")
	public String getTest(@RequestParam int id, @RequestParam String username) {
		return "get 요청 : " + id + ", " + username;
	}
	/*  같은 방법
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword()
		+ ", " + m.getEmail();
	}
	*/
	
	
	
	
	// 인터넷(http)요청은 get요청밖에 안된다. (405에러)
	/*
	@PostMapping("/http/post")     //(insert)
	public String postTest() {
		return "post 요청";
	}
	
	@PostMapping("/http/post")  // body의 x-www-form-unlencoded
	public String postTest(Member m) {
		return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword()
		+ ", " + m.getEmail();
	}
	@PostMapping("/http/post") // body의 raw/text  text/tplain : 평범한 메모(글)
	
	public String postTest(@RequestBody String text) {
		return "post 요청 : " + text;
	}*/
	@PostMapping("/http/post") // body의 raw/json  application/json
	// application/json : 객체값을 메모 { "id" : 1, "username" : cos } 등등
	// MessageConverter 라는 애가 자동으로 이렇게 일을 해줌
	public String postTest(@RequestBody Member m) {
		return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword()
		+ ", " + m.getEmail();
	}
	/*
	@PutMapping("/http/put")    // (update)
	public String putTest() {
		return "put 요청";
	}
	*/
	@PutMapping("/http/put")    // (update)
	// body의 raw/json을 이용
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword()
		+ ", " + m.getEmail();
	}
	
	@DeleteMapping("/http/delete")     //(delte)
	public String deleteTest() {
		return "delete 요청";
	}
	
}

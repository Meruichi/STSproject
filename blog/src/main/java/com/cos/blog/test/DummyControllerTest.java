package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController // Data를 리턴해주는 컨트롤러
public class DummyControllerTest {

	// Autowired : DummyControllerTest가 작동할 때, 같이 메모리에 뜸  (의존성주입)
	@Autowired
	private UserRepository userRepository;
	
	// 방법 1
	// {id} : 주소로 파라미터를 전달받음
	// @PathVariable : URL경로에 변수를 넣어주는것
	/*
	 * @GetMapping("/dummy/user/{id}") public User detail(@PathVariable int id) { //
	 * findById는 Optional (id를 찾지못하면 null이 리턴이 될것이다. 그럼 프로그램에 문제가 생길 수 있다. // 그러므로
	 * optional로 감싸서 가져오고, null인지 아닌지 판단해서 return하시오) User user =
	 * userRepository.findById(id).orElseGet(new Supplier<User>() {
	 * 
	 * @Override public User get() { return new User(); } } ); // orElseGet : null이면
	 * 아무 객체나 넣어서 null이 아니게 만듬 return user; }
	 */
	
	// 방법 2
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		}
				);
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한 페이지 당 2건의 데이터를 리턴받음
	@GetMapping("/dummy/user") // /dummy/user?page=0 , /dummy/user?page=1 등등
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	// save함수 : id를전달하지않거나 전달했어도 해당 데이터가없으면 insert
	// id를 전달하고 해당id에 대한 데이터가 있으면 update
	// post와 put다르면 url같아도 충돌안되고 구분이 됨
	@Transactional // 함수종료시 자동 commit이됨
	@PutMapping("dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// userRepository.save(user);
		// Transactional : save를 사용하지 않고 update함
		// 더티 체킹
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return "없는 id입니다";
		}
		return "삭제되었습니다. id : "+ id;
	}
	
	
	
	
	
	// http://localhost:9090/blog/dummy/join
	// http의 body에 username, password, email데이터를 가져옴
	/*
	 * @PostMapping("/dummy/join") public String join(String username, String
	 * password, String email) { // @RequestParam("username") String username, 등등
	 * 이런식으로 적어도 됨 // 이렇게 적으면 String뒤의 username을 다르게 적어도 됨.
	 * 
	 * System.out.println("username : " + username);
	 * System.out.println("password : " + password); System.out.println("email : " +
	 * email); return "회원가입 완료"; }
	 */
	@PostMapping("/dummy/join")
	public String join(User user) {
		// @RequestParam("username") String username, 등등 이런식으로 적어도 됨
		// 이렇게 적으면 String뒤의 username을 다르게 적어도 됨.
		
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
	}
}

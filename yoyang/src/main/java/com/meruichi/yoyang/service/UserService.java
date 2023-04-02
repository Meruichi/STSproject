package com.meruichi.yoyang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meruichi.yoyang.model.UserRoleType;
import com.meruichi.yoyang.repository.BranchRepository;
import com.meruichi.yoyang.repository.UserRepository;
import com.meruichi.yoyang.model.Board;
import com.meruichi.yoyang.model.Branch;
import com.meruichi.yoyang.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword); // 해쉬화
		user.setPassword(encPassword);
		user.setRole(UserRoleType.EMPLOYEE);
		userRepository.save(user);
		// 오류시 GlobalExceptionHandler가 처리
	}
	/*
	 * @Transactional(readOnly = true) // select할때 트랜잭션 시작, 서비스종료시에 트랜잭션 종료 public
	 * User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */

	@Transactional
	public void 회원수정(User user) {
		User persistance = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> {
			return new IllegalArgumentException("회원찾기 실패");
		});

		// Validate체크(카카오 유저가 아닌 경우에만 비밀번호해쉬화,수정가능)
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setUserEmail(user.getUserEmail());
		persistance.setUserRealname(user.getUserRealname());
		persistance.setUserPhoneNumber(user.getUserPhoneNumber());

		// persistance객체의 변화가 감지되면 update문을 날림
	}

	@Transactional(readOnly = true)
	public Page<User> 유저목록(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Transactional
	public void 회원지점연결(User user, int id) {
		Branch persistance = branchRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("회원찾기 실패");
		});
		user.setBranch(persistance);
		userRepository.save(user);
	}

	public void 권한변경(int id) {
		User persistance = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("회원찾기 실패");
		});
		if (persistance.getRole().toString() == "MANAGER") {
			persistance.setRole(UserRoleType.EMPLOYEE);
		} else if (persistance.getRole().toString() == "EMPLOYEE") {
			persistance.setRole(UserRoleType.MANAGER);
		} else {
			System.out.println(persistance.getRole().toString());
		}
		userRepository.save(persistance);
	}

}

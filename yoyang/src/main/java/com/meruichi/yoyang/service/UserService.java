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
	public void ȸ������(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword); // �ؽ�ȭ
		user.setPassword(encPassword);
		user.setRole(UserRoleType.EMPLOYEE);
		userRepository.save(user);
	}

	@Transactional
	public void ȸ������(User user) {
		User persistance = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> {
			return new IllegalArgumentException("ȸ��ã�� ����");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setUserEmail(user.getUserEmail());
		persistance.setUserRealname(user.getUserRealname());
		persistance.setUserPhoneNumber(user.getUserPhoneNumber());
	}

	@Transactional(readOnly = true)
	public Page<User> �������(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Transactional
	public void ȸ����������(User user, int id) {
		Branch persistance = branchRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("����ã�� ����");
		});
		user.setBranch(persistance);
		userRepository.save(user);
	}

	@Transactional
	public void ���Ѻ���(int id) {
		User persistance = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("ȸ��ã�� ����");
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

	@Transactional
	public void ȸ��Ż��(int id) {
		User persistance = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("ȸ��ã�� ����");
		});
		persistance.setUserWithdrawn(true);
		userRepository.save(persistance);
	}
}

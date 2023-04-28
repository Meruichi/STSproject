package com.meruichi.yoyang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meruichi.yoyang.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);	// SELECT * FROM user WHERE username = ?;
	Optional<User> findByUserNumber(Integer userNumber);
}

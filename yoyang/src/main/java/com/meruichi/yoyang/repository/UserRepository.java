package com.meruichi.yoyang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meruichi.yoyang.model.User;

//DAO
//�ڵ����� bean����� �ȴ�.
//@Repository ���� ����
public interface UserRepository extends JpaRepository<User, Integer>{

	
	Optional<User> findByUsername(String username);	// SELECT * FROM user WHERE username = ?;

	Optional<User> findByUserNumber(Integer userNumber);
	
	
}
//User���̺��� �����ϴ� repository, User���̺��� PŰ�� Integer��.


//User findByUsernameAndPassword(String username, String password);
//SELECT * FROM user WHERE username = ? and password = ?;
//�޼ҵ� �̸��� ������ ������ ���� SELECT���� �����(JPA Naming Query����)

/* �Ǵٸ����
@Query(value="SELECT * FROM user WHERE username = ? and password = ?;", nativeQuery = true)
User login(String username, String password);


*/
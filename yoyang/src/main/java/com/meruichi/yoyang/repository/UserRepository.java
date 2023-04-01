package com.meruichi.yoyang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meruichi.yoyang.model.User;

//DAO
//자동으로 bean등록이 된다.
//@Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer>{

	
	Optional<User> findByUsername(String username);	// SELECT * FROM user WHERE username = ?;

	Optional<User> findByUserNumber(Integer userNumber);
	
	
}
//User테이블이 관리하는 repository, User테이블의 P키는 Integer임.


//User findByUsernameAndPassword(String username, String password);
//SELECT * FROM user WHERE username = ? and password = ?;
//메소드 이름을 저렇게 지으면 위의 SELECT문이 실행됨(JPA Naming Query전략)

/* 또다른방법
@Query(value="SELECT * FROM user WHERE username = ? and password = ?;", nativeQuery = true)
User login(String username, String password);


*/
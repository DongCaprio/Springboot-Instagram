package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
//어노테이션이 없어도 IOC 등록이 자동으로 된다
public interface UserRepository extends JpaRepository<User, Integer>{

	//내가만드는 쿼리(JPA named 쿼리이용)
	//이게 알아서 대문자소문자 Username에 의해서 select쿼리가 짜짐
	User findByUsername(String username);
	
}

package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	
	@Modifying //DB에 변경을 주는 쿼리에는 Modifying이 필요하다(INSERT, DELETE UPDATE를 네이티브쿼리로 작성시 이 어노테이션 필수!!)
	@Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
	void mSubscribe(int fromUserId, int toUserId); //성공시 n 리턴, 실패시 -1리턴 (정해져있는것)

	@Modifying //DB에 변경을 주는 쿼리에는 Modifying이 필요하다
	@Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId =:toUserId", nativeQuery = true)
	void mUnSubscribe(int fromUserId, int toUserId);
	
	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId =:principalId AND toUserId = :pageUserId", nativeQuery = true)
	int mSubscribeState(int principalId, int pageUserId);
	
	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
	int mSubscribeCount(int pageUserId);
}

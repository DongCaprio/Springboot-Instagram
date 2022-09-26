package com.cos.photogramstart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {
	
	private final SubscribeRepository subscribeRepository;
	private final EntityManager em; // Repository는 전부 얘를 기본적으로 구현해서 만들어짐
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId){
		//레파지토리가 아닌 여기서 쿼리를 짜는이유
		//레파지토리 ex)subscriberepository는 return을 subscirbe 타입으로만 할 수 있으므로
		// 내가 join 등등을 이용한 짬뽕쿼리문은 리턴할수 없다.
		// 그래서 EntityManager를 통해 직접 구현해주어야 한다.
		
		//쿼리준비
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
		sb.append("if((SELECT true FROM subscribe WHERE fromuserId= ? AND toUserId = u.id), 1, 0) subscribeState, ");
		sb.append("if((?= u.id),1,0) AS equalUserState ");
		sb.append("FROM user u JOIN subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("WHERE s.fromUserId = ? "); //세미콜론 첨부X
		
		//쿼리 완성
		//**자바 퍼시스턴스 쿼리(임포트타입)
		
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
		
		//쿼리 실행 //qlrm 라이브러리 필요 => Dto에 DB결과를 매핑하기 위해
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDto> subscirbeDtos = result.list(query, SubscribeDto.class);
		return subscirbeDtos;
	}
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId);
		} catch (Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다.");
		}
	}

	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) { //삭제는 구독하기와 다르게 오류가 거의 안남
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}
}

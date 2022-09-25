package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional(readOnly = true)
	public UserProfileDto 회원프로필(int pageUserId, int pricipalId) {
		UserProfileDto dto = new UserProfileDto();

		User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
			throw new CustomException("회원을 찾을 수 없습니다");
		});
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId == pricipalId); // 1은 페이지주인, -1은 주인아님
		dto.setImageCount(userEntity.getImages().size());
		return dto;
	}

	@Transactional
	public User 회원수정(int id, User user) {
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new CustomValidationException("찾을수없는 id입니다");
		});
		userEntity.setName(user.getName());
		userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		return userEntity;
	}
}

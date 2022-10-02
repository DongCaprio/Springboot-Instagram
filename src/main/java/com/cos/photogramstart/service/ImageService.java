package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	@Transactional(readOnly = true)
	public Page<Image> 이미지스토리(int principalId, Pageable pageable){
		Page<Image> images = imageRepository.mStory(principalId, pageable);
		
		//images에 좋아요 상태 담기
		images.forEach((image)->{
			image.getLikes().forEach(like ->{
				if(like.getUser().getId() == principalId) { //해당 이미지에 좋아요한 사람들을 찾아서 현재 로그인한 사람이 좋아요 한것인지 비교
					image.setLikeState(true);
				}
			});
		});
		return images;
	}
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		//UUID 쓰는 이유 -> 실제 파일명이 같으면 ex) 1.jpg가 2개 저장이 되는순간 원래 있던것이 나중에 저장되는것으로 덮어씌워진다
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); //1.jpg
		System.out.println("이미지 파일이름 : "+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		//통신이 일어나거나 업로드 등에는 예외발생가능 -> try catch로 묶어라
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
			//1. 파일path 2.실제이미지파일(바이트화 해서 넣어야한다) 3.원래있는데 생략가능
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
//		System.out.println(imageEntity);
	}
}

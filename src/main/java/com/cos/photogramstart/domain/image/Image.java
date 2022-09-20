package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String caption;
	private String postImageUrl; //사진을 특정 폴더에 저장 후 DB에 그 저장된 경로를 insert함
	
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
	
	// 이미지 좋아요
	
	//댓글
	
	
	private LocalDateTime createTime;
	
	@PrePersist
	public void createDate() {
		this.createTime = LocalDateTime.now();
	}

}
